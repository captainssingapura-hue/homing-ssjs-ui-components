// =============================================================================
// PanZoomBar — the controls of a pan-zoom view, as a BRANCH component: zoom
// out, the zoom said as a readout, zoom in, and back to fit - the elements'
// buttons, small and plain. It drives the view it is given and follows it: the
// readout says the zoom as a share of fit; out is off at the least, fit at fit,
// in at the most. A view is anything with zoomIn(), zoomOut(), fit(), view()
// and onChange(fn) → off - an SvgPanZoom, or a view of another kind of content.
//
//   var bar = new PanZoomBar(branch.createBranch("zoomBar"), view)
//   bar.root   bar.dispose()
// =============================================================================

const _panZoomBarOwner = Object.freeze({ toString: () => "pan-zoom-bar" });

/** A press on one of the bar's buttons: the view told to zoom out, zoom in or fit. */
class PanZoomPress {
    constructor(view, act) { this._view = view; this._act = act; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() { this._view[this._act](); }
}

class PanZoomBar {
    static LEAF = "pan-zoom-bar";

    constructor(branch, view) {
        if (!branch) throw new Error("[PanZoomBar] a branch of its own is required");
        if (!view || typeof view.zoomIn !== "function" || typeof view.onChange !== "function") {
            throw new Error("[PanZoomBar] a view to drive is required: zoomIn, zoomOut, fit, view, onChange");
        }
        var self = this;
        branch.activate(_panZoomBarOwner);
        this.branch = branch;
        var root = branch.createElement("bar", "div");
        css.addClass(root, pz_bar);
        new Look(PanZoomBar, ROLE.STANDALONE).wear(root);   // its casting: no look of its own, its row the layout's
        root.setAttribute("role", "group");
        root.setAttribute("aria-label", "Zoom");
        this._out = this._button(root, "out", "−", "Zoom out", ROLE.ZOOM_OUT, new PanZoomPress(view, "zoomOut"));
        // the zoom, read out by the house's readout - an output, which says its changes to whoever cannot see them
        this._readout = new Readout(ReadoutFactory.create(branch, "readout"), {}, ROLE.VALUE);
        css.addClass(this._readout.el, pz_readout);
        root.appendChild(this._readout.el);
        this._in = this._button(root, "in", "+", "Zoom in", ROLE.ZOOM_IN, new PanZoomPress(view, "zoomIn"));
        this._fit = this._button(root, "fit", "Fit", "Fit the whole", ROLE.FIT, new PanZoomPress(view, "fit"));
        this.root = root;
        this._off = view.onChange(function (v) { self._follow(v); });
        this._follow(view.view());
    }

    dispose() {
        this._off();
        this.branch.dissolve();
    }

    _button(root, name, label, said, role, press) {
        var button = new PlainButton(PlainButtonFactory.create(this.branch, name), { label: label, size: -1 }, role);
        button.setOnClick(press);
        button.el.setAttribute("aria-label", said);
        root.appendChild(button.el);
        return button;
    }

    _follow(v) {
        this._readout.text = Math.round(v.zoom * 100) + "%";
        this._out.enabled = !v.least;
        this._fit.enabled = !v.fitted;
        this._in.enabled = !v.most;
    }
}
