// =============================================================================
// FloatingPane — a pane over the page: its head, which names it and moves it,
// and its body, where what it shows is mounted by its caller. Ported (RFC
// 0071) into the panes, beside the multi-tab pane and the chip it shares with
// it: top-level, made with its own binding, or a part of an owner that places
// it. Its place and its measure are its own, carried in --fp-x, --fp-y,
// --fp-w, --fp-h and its place on the stack in --fp-z, written by the class
// that places it; set by its caller - at(x, y), size(w, h), z - and by the
// hand: the head held and moved, the pane moves with it, live, in the hand
// (Dragging) - kept so that some of its head stays within what holds it - and
// nothing is told of where it went. Its close, on its head, is passed on - its
// FloatCloseControl going up - and what a close is, its caller decides. The
// current one (Current) is its caller's to say; under the hand it is lit
// (Highlighted). A grip to size it comes with its own step.
//
//   var pane = new FloatingPane(branch.createBranch("notes"), new FixedFloatingPane(new FixedTitleBar(new FixedLabel(), null, new FixedCloseButton())));
//   pane.title = "Notes"     its head's title, or its head's chip's name
//   pane.at(40, 30)  pane.size(320, 200)  pane.z = 2  pane.bounds → { x, y, w, h }
//   pane.mount(root, party?)  what it shows: a widget's root in its body, the widget's branch of the page grafted
//   pane.unmount()            let go: the root and the branch, still the widget's
//   pane.onChange(StateFloatCloseControl, listener)   its close pressed: the count up by one
//   pane.onChange(StateDragging, listener)            in the hand, and let go
//   pane.current  pane.highlighted  pane.dragging  pane.closes  pane.root  pane.dispose()
// A composed component on the style manager (RFC 0071): it takes a branch of its
// own and makes its parts on it; its module stamped with its header (D19). Its
// own classes only place. `css` is injected with the styles import.
// =============================================================================

const _floatingPaneOwner = Object.freeze({ toString: () => "floating-pane" });

/** Its head held, or let go: the pane grabbed by the hand, or let go of. */
class FloatingPaneHeld {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateHeld; }
    handle(change) { if (change.after === "down") this._pane._grabbed(); else this._pane._letGo(); }
}

/** Its head's close pressed: the pane's own count of the closes asked of it, up by one. */
class FloatingPaneClose {
    constructor(pane) { this._pane = pane; Object.freeze(this); }
    canHandle(type) { return type === StateFloatCloseControl; }
    handle() { this._pane._closeAsked(); }
}

class FloatingPane extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "true"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        }),
        highlighted: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateHighlighted }),
        dragging: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateDragging }),
        closes: Object.freeze({ rest: 0, state: StateFloatCloseControl })   // its head's close's presses, passed on: no class
    });
    static BEHAVIOR = FloatingPaneBehavior;

    /** How far the hand moves the head before the pane is in the hand, in pixels: a press that wanders less is a press. */
    static DRAG_FROM = 3;
    /** How much of its head stays within what holds it, inline, and how little it may be, in pixels. */
    static KEEP = 48;
    static LEAST = Object.freeze({ w: 160, h: 96 });

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[FloatingPane] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_floatingPaneOwner);
        super(branch.elementMaker("frame"), fixed, FixedFloatingPane, place ?? manageFloatingPane);
        this.branch = branch;
        css.addClass(this.el, fp_frame);
        // its head, where its fixed object holds one: the house's title bar, which it moves by
        this._head = null;
        if (this.fixed.head !== null) {
            this._head = new TitleBar(branch.createBranch("head"), this.fixed.head, this._place(this.fixed.head));
            this._head.onChange(StateHeld, new FloatingPaneHeld(this));
            this._head.onChange(StateFloatCloseControl, new FloatingPaneClose(this));
            this.el.appendChild(this._head.el);
        }
        // its body: a room of its own, where its caller mounts what it shows
        this._body = branch.createElement("body", "div");
        this._room(ROOM_BODY, this._body);
        css.addClass(this._body, fp_body);
        this.el.appendChild(this._body);
        this._root = null;
        this._grafted = false;
        this._box = { x: 0, y: 0, w: 360, h: 240 };
        this._z = 1;
        this._grab = null;   // where the hand took it, while its head is held
        this._write();
        this.root = this.el;
    }

    /** What names it: its head's title, or its head's chip's name. */
    get title() { return this._head !== null ? this._head.title : ""; }
    set title(words) { if (this._head !== null) this._head.title = words; }

    /** Its place, and its measure, as they are. */
    get bounds() { return Object.freeze(Object.assign({}, this._box)); }

    /** Its place within what holds it, kept so that some of its head stays within it. */
    at(x, y) { this._box.x = Number(x) || 0; this._box.y = Number(y) || 0; this._keep(); this._write(); return this; }

    /** Its measure, never under its least. */
    size(w, h) {
        this._box.w = Math.max(FloatingPane.LEAST.w, Number(w) || 0);
        this._box.h = Math.max(FloatingPane.LEAST.h, Number(h) || 0);
        this._keep();
        this._write();
        return this;
    }

    /** Its place on the stack: its caller's to say. */
    get z() { return this._z; }
    set z(n) { this._z = Math.max(0, Math.floor(Number(n) || 0)); this._write(); }

    /** The current one: its caller's to say. */
    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** Lit while the hand is over it. */
    get highlighted() { return this._get("highlighted"); }

    /** In the hand: moved by its head, from past the start of the press to the release. */
    get dragging() { return this._get("dragging"); }

    /** How many times its close was asked, from none. */
    get closes() { return this._get("closes"); }

    /** What it shows, mounted by its caller: a widget's root in its body - its own, which it alone manages - and its branch of the page, where it has one, grafted on the pane's. One at a time. */
    mount(root, party) {
        if (this._root !== null) throw new Error("[FloatingPane] shows something already: unmount it first");
        if (!root || root.nodeType !== 1) throw new TypeError("[FloatingPane] what it shows is a widget's root, an element");
        if (party != null) { this.branch.graft("widget", party); this._grafted = true; }
        this._root = root;
        this._body.appendChild(root);
    }

    /** What it shows let go: the root out of its body, the branch detached - both still the widget's. */
    unmount() {
        if (this._root === null) return;
        this._body.removeChild(this._root);
        if (this._grafted) this.branch.detach("widget");
        this._root = null;
        this._grafted = false;
    }

    /** It and everything on its branch go together; what it shows is its caller's: let go, first. */
    dispose() { this._behavior.drop(); this.unmount(); this.branch.dissolve(); }

    /** Its head held: where the hand took it kept, its moves heard until it lets go. */
    _grabbed() {
        this._grab = { x: this._box.x, y: this._box.y, from: null };
        this._behavior.grab();
    }

    /** The hand moved, its head held - by how much since it went down: past its start, it is in the hand and moves with it. */
    _moved(dx, dy) {
        var g = this._grab;
        if (g === null) return;
        if (!this.dragging) {
            if (Math.abs(dx) < FloatingPane.DRAG_FROM && Math.abs(dy) < FloatingPane.DRAG_FROM) return;
            this._change("dragging", true);
            if (this._head !== null) this._head.dragging = true;
        }
        this._box.x = g.x + dx;
        this._box.y = g.y + dy;
        this._keep();
        this._write();
    }

    /** Its head let go: out of the hand, where it is. */
    _letGo() {
        this._grab = null;
        this._behavior.drop();
        if (this.dragging) {
            if (this._head !== null) this._head.dragging = false;
            this._change("dragging", false);
        }
    }

    /** The hand over it, or gone: lit, or not. */
    _hovered(on) { this._change("highlighted", !!on); }

    /** Its head's close pressed, as its listener hears it: one more close asked. */
    _closeAsked() { this._change("closes", this.closes + 1); }

    /** Its place kept so that some of its head stays within what holds it: KEEP of it inline, its head's top within - once what holds it has a size. */
    _keep() {
        var holder = this.el.offsetParent;
        if (!holder || !(holder.clientWidth > 0) || !(holder.clientHeight > 0)) return;
        var W = holder.clientWidth, H = holder.clientHeight, b = this._box, keep = Math.min(FloatingPane.KEEP, b.w);
        b.x = Math.max(keep - b.w, Math.min(W - keep, b.x));
        b.y = Math.max(0, Math.min(Math.max(0, H - 24), b.y));
    }

    /** Its place, measure and stack written as the numbers the class that places it reads. */
    _write() {
        var s = this.el.style, b = this._box;
        s.setProperty("--fp-x", b.x + "px");
        s.setProperty("--fp-y", b.y + "px");
        s.setProperty("--fp-w", b.w + "px");
        s.setProperty("--fp-h", b.h + "px");
        s.setProperty("--fp-z", String(this._z));
    }
}
