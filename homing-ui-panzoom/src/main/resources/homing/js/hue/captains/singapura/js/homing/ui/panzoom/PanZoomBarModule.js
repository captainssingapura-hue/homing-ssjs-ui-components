// =============================================================================
// PanZoomBar — the buttons that drive a pan-zoom view, and the zoom read out
// between them: zoom out, the zoom, zoom in, fit the whole. A composed component
// on the style manager (RFC 0071): it takes a branch of its own and makes on it
// its row and its parts - three of the house's plain buttons, each cast as what
// it does, and the house's readout cast as the value - each placed along its
// slot by its own reference. It drives the one view it is wired to, once and for
// good, as a handler is set: what it is wired to never changes.
//
//   var bar = new PanZoomBar(branch.createBranch("zoom"), new FixedPanZoomBar(), me.slot(SLOT_…));
//   bar.drive(view)    the view it drives: zoomIn, zoomOut, fit, view, onChange - once
//   bar.root           its row
//   bar.dispose()      stops following the view, and dissolves its branch
//
// Its casting has no look of its own: its row is the layout's.
// =============================================================================

const _panZoomBarOwner = Object.freeze({ toString: () => "pan-zoom-bar" });

/** A press on one of the bar's buttons: the view told to zoom out, zoom in or fit. */
class PanZoomPress {
    constructor(bar, act) { this._bar = bar; this._act = act; Object.freeze(this); }
    canHandle(type) { return type === EventClick; }
    handle() { if (this._bar._view) this._bar._view[this._act](); }
}

class PanZoomBar extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = MarkBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[PanZoomBar] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_panZoomBarOwner);
        super(branch.elementMaker("bar"), fixed, FixedPanZoomBar, place ?? managePanZoomBar);
        this.branch = branch;
        this._view = null;
        this._off = null;
        css.addClass(this.el, pz_bar);
        this.el.setAttribute("role", "group");
        this.el.setAttribute("aria-label", "Zoom");
        this._out = this._button("out", "−", "Zoom out", SLOT_PAN_ZOOM_BAR_PLAIN_BUTTON_ZOOM_OUT, new PanZoomPress(this, "zoomOut"));
        // the zoom, read out by the house's readout - an output, which says its changes to whoever cannot see them
        this._readout = new Readout(branch.elementMaker("readout"), new FixedReadout(), this._slot(SLOT_PAN_ZOOM_BAR_READOUT_VALUE));
        css.addClass(this._readout.el, pz_readout);
        this.el.appendChild(this._readout.el);
        this._in = this._button("in", "+", "Zoom in", SLOT_PAN_ZOOM_BAR_PLAIN_BUTTON_ZOOM_IN, new PanZoomPress(this, "zoomIn"));
        this._fit = this._button("fit", "Fit", "Fit the whole", SLOT_PAN_ZOOM_BAR_PLAIN_BUTTON_FIT, new PanZoomPress(this, "fit"));
        this.root = this.el;
    }

    /** The view it drives, once and for good: zoomIn, zoomOut, fit, view, onChange. */
    drive(view) {
        if (this._view) throw new Error("[PanZoomBar] drives its view already: wired once, it stays");
        if (!view || typeof view.zoomIn !== "function" || typeof view.onChange !== "function")
            throw new Error("[PanZoomBar] a view to drive: zoomIn, zoomOut, fit, view, onChange");
        var self = this;
        this._view = view;
        this._off = view.onChange(function (v) { self._follow(v); });
        this._follow(view.view());
        return this;
    }

    dispose() {
        if (this._off) this._off();
        this.branch.dissolve();
    }

    _button(name, label, said, slot, press) {
        var button = new PlainButton(this.branch.elementMaker(name), new FixedPlainButton(), this._slot(slot));
        button.label = label;
        button.size = -1;
        button.setOnClick(press);
        button.el.setAttribute("aria-label", said);
        this.el.appendChild(button.el);
        return button;
    }

    _follow(v) {
        this._readout.text = Math.round(v.zoom * 100) + "%";
        this._out.enabled = !v.least;
        this._fit.enabled = !v.fitted;
        this._in.enabled = !v.most;
    }
}
