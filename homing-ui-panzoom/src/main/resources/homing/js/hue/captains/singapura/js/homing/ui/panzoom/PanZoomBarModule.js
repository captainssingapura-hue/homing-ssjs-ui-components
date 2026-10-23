// =============================================================================
// PanZoomBar — the buttons of a pan-zoom view, and the zoom read out between
// them: zoom out, the zoom, zoom in, fit the whole - its view's part only (owner,
// 23 Oct). A composed component on the style manager (RFC 0071): it takes a
// branch of its own and makes on it its row and its parts - three of the house's
// plain buttons, each cast as what it does, and the house's readout cast as the
// value - each placed along its slot by its own reference. It shows what its view
// says, and passes its presses on, counted by the action (ActionControl): what an
// action does, its view decides.
//
//   var bar = new PanZoomBar(branch.createBranch("bar"), new FixedPanZoomBar(), me.place(FixedPanZoomBar));
//   bar.show(view)     its view's zoom read out, each button on or off as the view can go
//   bar.onChange(StateActionControl, listener)   an action pressed - out, in or fit: its count up by one
//   bar.presses  bar.root  bar.dispose()
//
// Its casting has no look of its own: its row is the layout's.
// =============================================================================

const _panZoomBarOwner = Object.freeze({ toString: () => "pan-zoom-bar" });

/** A press on one of the bar's buttons: that action's count up by one, passed on. */
class PanZoomPress {
    constructor(bar, id) { this._bar = bar; this._id = id; Object.freeze(this); }
    canHandle(type) { return type === StateClicks; }
    handle() { this._bar._pressed(this._id); }
}

class PanZoomBar extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        presses: Object.freeze({ rest: StateActionControl.rest, state: StateActionControl })   // by the action, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[PanZoomBar] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_panZoomBarOwner);
        super(branch.elementMaker("bar"), fixed, FixedPanZoomBar, place);   // a part only: placed by its view
        this.branch = branch;
        css.addClass(this.el, pz_bar);
        this.el.setAttribute("role", "group");
        this.el.setAttribute("aria-label", "Zoom");
        this._out = this._button("out", "−", "Zoom out", RoleZoomOut, new PanZoomPress(this, "out"));
        // the zoom, read out by the house's readout - an output, which says its changes to whoever cannot see them
        this._readout = new Readout(branch.elementMaker("readout"), new FixedReadout(), this._place(FixedReadout));
        css.addClass(this._readout.el, pz_readout);
        this.el.appendChild(this._readout.el);
        this._in = this._button("in", "+", "Zoom in", RoleZoomIn, new PanZoomPress(this, "in"));
        this._fit = this._button("fit", "Fit", "Fit the whole", RoleFit, new PanZoomPress(this, "fit"));
        this._change("presses", Object.freeze({ out: 0, in: 0, fit: 0 }));
        this.root = this.el;
    }

    /** Its view's zoom, as its view shows it: read out, each button on or off as the view can go. */
    show(v) {
        this._readout.text = Math.round(v.zoom * 100) + "%";
        this._out.enabled = !v.least;
        this._fit.enabled = !v.fitted;
        this._in.enabled = !v.most;
    }

    /** How many times each action was pressed, by its name - out, in, fit. */
    get presses() { return this._get("presses"); }

    dispose() { this.branch.dissolve(); }

    /** One of its buttons pressed, as its listener hears it: that action's count up by one. */
    _pressed(id) {
        var counts = Object.assign({}, this.presses);
        counts[id] = (counts[id] || 0) + 1;
        this._change("presses", Object.freeze(counts));
    }

    _button(name, label, said, role, press) {
        // its three buttons each a plain button, so each placed in the role it plays
        var button = new PlainButton(this.branch.elementMaker(name), new FixedPlainButton(), this._place(FixedPlainButton, role));
        button.label = label;
        button.size = -1;
        button.onChange(StateClicks, press);   // a press, its clicks counted up
        button.el.setAttribute("aria-label", said);
        this.el.appendChild(button.el);
        return button;
    }

}
