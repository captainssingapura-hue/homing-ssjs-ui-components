// =============================================================================
// FloatLayer — the floor floating panes are stacked on: a desk of its own,
// recessed below the page. Ported (RFC 0071, owner 23 Oct). Its floaters are
// its caller's to open and close - add(floater), remove(id) - as a multi-tab
// pane's tabs are; each is empty but for what its caller mounts in it: a
// widget's root, which the widget alone manages, and the widget's own branch of
// the page, grafted. It never makes a widget, nor disposes one. A floater opened
// is placed where its caller says, else in a cascade, and put in front.
//
// What is its own is the stack: a press on a floater, or the focus arriving in
// it, puts it in front - the one in use, the others not - and its caller hears
// the stack as one order, bottom to top, its top the one in use (FloatOrder). A
// floater's close is passed on, by the floater (LayerCloseControl): what a close
// is, its caller decides. Where a floater is moved to, or how it is sized, is the
// floater's own, told to none: its caller reads it - bounds(id). The keys, and
// handing a floater to a dock, wait.
//
//   var layer = new FloatLayer(branch.createBranch("desk"), new FixedFloatLayer(), place?);
//   layer.add({ id, title?, closable?, at?: { x, y }, size?: { w, h } })   a floater opened, empty: in front
//   layer.mount(id, root, party?)   what it shows: a widget's root in its body, its branch of the page grafted
//   layer.remove(id)   a floater closed by its caller: what it shows let go - the widget's still
//   layer.raise(id)    put in front, by its caller   layer.order   layer.closes   layer.bounds(id)   layer.keys
//   layer.onChange(StateFloatOrder, listener)   layer.onChange(StateLayerCloseControl, listener)
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _layerOwner = Object.freeze({ toString: () => "floatLayer" });
var _CASCADE = 24;                                     // each floater opened where none is said, this far on from the last
var _SIZE = Object.freeze({ w: 320, h: 220 });         // and this big, where none is said

/** A floater's close, as its layer hears it: passed on, as that floater's. */
class LayerFloatClose {
    constructor(layer, id) { this.layer = layer; this.id = id; Object.freeze(this); }
    canHandle(on) { return on === StateFloatCloseControl; }
    handle() { this.layer._closeAsked(this.id); }
}

class FloatLayer extends SimpleComponent {
    static TAG = "div";
    static ID = /^[a-z0-9]+(-[a-z0-9]+)*$/;
    static AXES = Object.freeze({
        keys: Object.freeze({ values: StateKeyboard.states, rest: StateKeyboard.rest, state: StateKeyboard }),   // the steward's to tell
        order: Object.freeze({ rest: StateFloatOrder.rest, state: StateFloatOrder }),            // bottom to top: no class
        closes: Object.freeze({ rest: StateLayerCloseControl.rest, state: StateLayerCloseControl })   // passed on: no class
    });
    static BEHAVIOR = FloatLayerBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[FloatLayer] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_layerOwner);
        super(branch.elementMaker("desk"), fixed, FixedFloatLayer, place ?? manageFloatLayer);
        this.branch = branch;
        css.addClass(this.el, fp_desk);
        this._floats = new Map();   // id → { floater, closes: a count, or none for one that cannot be closed }
        this._stack = [];           // ids, bottom to top
        this._top = 0;
        this._opened = 0;
        this.root = this.el;
    }

    /** A floater opened by its caller: empty, placed where it says or in the cascade, in front. */
    add(spec) {
        var id = spec && spec.id;
        if (typeof id !== "string" || !FloatLayer.ID.test(id)) throw new TypeError("[FloatLayer] a floater's id is lower case words joined by a dash, not " + id);
        if (this._floats.has(id)) throw new Error("[FloatLayer] holds a floater " + id + " already");
        var closable = spec.closable !== false, k = this._opened++ % 8;
        var floater = new FloatingPane(this.branch.createBranch("float-" + id),
            new FixedFloatingPane(new FixedTitleBar(new FixedLabel(), null, closable ? new FixedCloseButton() : null)), this._place(FixedFloatingPane, RoleFloat));
        floater.title = spec.title == null ? id : String(spec.title);
        if (closable) floater.onChange(StateFloatCloseControl, new LayerFloatClose(this, id));
        this.el.appendChild(floater.root);
        var size = spec.size || _SIZE, at = spec.at || { x: _CASCADE * (k + 1), y: _CASCADE * (k + 1) };
        floater.size(size.w, size.h);
        floater.at(at.x, at.y);
        this._floats.set(id, { floater: floater, closes: closable ? 0 : null });
        this._stack.push(id);
        this._closesNow();
        this._front(id);
    }

    /** What a floater shows, mounted by its caller: a widget's root in its body, its branch of the page grafted. */
    mount(id, root, party) { this._entry(id).floater.mount(root, party); }

    /** A floater closed by its caller: what it shows let go, the floater gone; the one now on top in use. */
    remove(id) {
        var e = this._entry(id);
        e.floater.dispose();
        this._floats.delete(id);
        this._stack.splice(this._stack.indexOf(id), 1);
        this._closesNow();
        this._ordered();
    }

    /** A floater put in front, by its caller. */
    raise(id) { this._entry(id); this._front(id); }

    /** How its floaters are stacked: their ids, bottom to top - the top the one in use. */
    get order() { return this._get("order"); }

    /** How many times each floater's close was asked, by its id - none for one that cannot be closed. */
    get closes() { return this._get("closes"); }

    /** Where a floater is and how big, as the floater has it: read by its caller, told to none. */
    bounds(id) { return this._entry(id).floater.bounds; }

    /** Where the page's keys are on it - the steward's to say. */
    get keys() { return this._get("keys"); }

    /** It and everything on its branch go together; what its floaters show is its caller's: let go, first. */
    dispose() { this._floats.forEach(e => e.floater.dispose()); this._floats.clear(); this.branch.dissolve(); }

    /** The pointer down, or the focus arriving, as its behaviour hears it: the floater it is in put in front. */
    _touched(node) {
        for (var [id, e] of this._floats) if (e.floater.root.contains(node)) { if (this._stack[this._stack.length - 1] !== id) this._front(id); return; }
    }

    /** A floater's close asked, as it hears it: one more, passed on. */
    _closeAsked(id) { var e = this._floats.get(id); if (e && e.closes !== null) { e.closes++; this._closesNow(); } }

    _entry(id) { var e = this._floats.get(id); if (!e) throw new Error("[FloatLayer] holds no floater " + id); return e; }

    _front(id) {
        this._stack.splice(this._stack.indexOf(id), 1);
        this._stack.push(id);
        this._floats.get(id).floater.z = ++this._top;
        this._ordered();
    }

    /** The stack told, and the floater on top the one in use, the others not. */
    _ordered() {
        var top = this._stack[this._stack.length - 1];
        this._floats.forEach((e, id) => { if (e.floater.current !== (id === top)) e.floater.current = id === top; });
        this._change("order", this._stack.slice());
    }

    _closesNow() {
        var view = {};
        this._floats.forEach((e, id) => { view[id] = e.closes; });
        this._change("closes", Object.freeze(view));
    }
}
