// =============================================================================
// ActionBar — the standard footer (owner, 23 Oct): the row of actions that ends a
// matter, at the foot of what it ends - a dialog widget's, a form's, a settings
// page's. Its main action (Go), where it was made with one, at its end, and its
// plain actions before it, how many fixed for its life. The widget that holds it
// names and labels them, once, and turns any off or on; each press is counted,
// by the action, and passed on (ActionControl): what an action does, the widget
// decides. A component of its own, top-level.
//
//   var bar = new ActionBar(branch.createBranch("foot"), new FixedActionBar(new FixedPrimaryButton(), [new FixedPlainButton()]), place?);
//   bar.name({ go: { id: "save", label: "Save" }, actions: [{ id: "cancel", label: "Cancel" }] })   once
//   bar.label("save", "Save as")   bar.enable("cancel", false)   bar.enabled("cancel")   bar.presses
//   bar.onChange(StateActionControl, listener)   an action pressed: its count up by one
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _footerOwner = Object.freeze({ toString: () => "actionBar" });

/** An action's button pressed, as the footer hears it: that action's press, passed on. */
class ActionPressed {
    constructor(bar, id) { this.bar = bar; this.id = id; Object.freeze(this); }
    canHandle(on) { return on === StateClicks; }
    handle() { this.bar._pressed(this.id); }
}

class ActionBar extends SimpleComponent {
    static TAG = "div";
    static ID = /^[a-z0-9]+(-[a-z0-9]+)*$/;
    static AXES = Object.freeze({
        presses: Object.freeze({ rest: StateActionControl.rest, state: StateActionControl })   // by the action, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;   // the presses are its buttons'

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[ActionBar] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_footerOwner);
        super(branch.elementMaker("bar"), fixed, FixedActionBar, place ?? manageActionBar);
        this.branch = branch;
        css.addClass(this.el, ft_bar);
        this._plain = this.fixed.action.map((f, i) => new PlainButton(branch.elementMaker("action-" + i), f, this._place(FixedPlainButton, RoleAction)));
        this._go = this.fixed.go === null ? null : new PrimaryButton(branch.elementMaker("go"), this.fixed.go, this._place(FixedPrimaryButton, RoleGo));
        for (var b of this._plain) this.el.appendChild(b.el);
        if (this._go !== null) this.el.appendChild(this._go.el);   // the main action at its end
        this._named = new Map();    // each action's name to its button, once named
        this._counts = new Map();   // each action's name to how many times it was pressed
        this.root = this.el;
    }

    /** Its actions named and labelled by its holder, once: the main one, where it was made with one, and each plain one, in order. */
    name(spec) {
        if (this._named.size) throw new Error("[ActionBar] named already: its actions' names are fixed for its life");
        var go = spec ? spec.go : null, actions = (spec && spec.actions) || [];
        if ((go != null) !== (this._go !== null)) throw new TypeError("[ActionBar] made " + (this._go !== null ? "with a main action: name it" : "with no main action: name none"));
        if (actions.length !== this._plain.length) throw new TypeError("[ActionBar] made with " + this._plain.length + " plain actions, named " + actions.length);
        var pairs = actions.map((a, i) => [a, this._plain[i]]);
        if (go != null) pairs.push([go, this._go]);
        var names = new Set();
        for (var [a] of pairs) {
            if (!a || typeof a.id !== "string" || !ActionBar.ID.test(a.id)) throw new TypeError("[ActionBar] an action's name is lower case words joined by a dash, not " + (a && a.id));
            if (names.has(a.id)) throw new Error("[ActionBar] two actions named " + a.id);
            names.add(a.id);
        }
        for (var [a, b] of pairs) {
            b.label = a.label == null ? a.id : String(a.label);
            b.onChange(StateClicks, new ActionPressed(this, a.id));
            this._named.set(a.id, b);
            this._counts.set(a.id, 0);
            this._countsNow();   // one change each
        }
    }

    /** An action's label, its holder's. */
    label(id, words) { this._button(id).label = words; }

    /** An action usable, or off: an off one is not pressed. */
    enable(id, on) { this._button(id).enabled = !!on; }
    enabled(id) { return this._button(id).enabled; }

    /** How many times each action was pressed, by its name. */
    get presses() { return this._get("presses"); }

    /** It and everything on its branch go together. */
    dispose() { this.branch.dissolve(); }

    _button(id) { var b = this._named.get(id); if (!b) throw new Error("[ActionBar] has no action " + id); return b; }

    /** An action pressed, as it hears it: one more press of it - and nothing else. */
    _pressed(id) { this._counts.set(id, this._counts.get(id) + 1); this._countsNow(); }

    _countsNow() {
        var view = {};
        this._counts.forEach((n, id) => { view[id] = n; });
        this._change("presses", Object.freeze(view));
    }
}
