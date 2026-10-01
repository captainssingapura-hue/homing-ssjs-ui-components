// =============================================================================
// PanZoomBar — the controls of a pan-zoom view, as a BRANCH component: zoom
// out, the zoom said as a readout, zoom in, and back to fit - the elements'
// buttons, small and plain. It drives the view it is given and follows it: the
// readout says the zoom as a share of fit; out and fit are off at fit, in is
// off at the most. A view is anything with zoomIn(), zoomOut(), fit(), view()
// and onChange(fn) → off - an SvgPanZoom, or a view of another kind of content.
//
//   var bar = new PanZoomBar(branch.createBranch("zoomBar"), view)
//   bar.root   bar.dispose()
// =============================================================================

const _panZoomBarOwner = Object.freeze({ toString: () => "pan-zoom-bar" });

class PanZoomBar {
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
        root.setAttribute("role", "group");
        root.setAttribute("aria-label", "Zoom");
        this._out = this._button(root, "out", "−", "Zoom out", function () { view.zoomOut(); });
        this._readout = branch.createElement("readout", "span");
        css.addClass(this._readout, pz_readout);
        this._readout.setAttribute("aria-live", "polite");
        root.appendChild(this._readout);
        this._in = this._button(root, "in", "+", "Zoom in", function () { view.zoomIn(); });
        this._fit = this._button(root, "fit", "Fit", "Fit the whole", function () { view.fit(); });
        this.root = root;
        this._off = view.onChange(function (v) { self._follow(v); });
        this._follow(view.view());
    }

    dispose() {
        this._off();
        this.branch.dissolve();
    }

    _button(root, name, label, said, onClick) {
        var b = new ButtonBuilder().label(label).plain().size(-1).onClick(onClick);
        var button = b.build(this.branch.createElement(name, b.tag));
        button.el.setAttribute("aria-label", said);
        root.appendChild(button.el);
        return button;
    }

    _follow(v) {
        this._readout.textContent = Math.round(v.zoom * 100) + "%";
        this._out.setOn(!v.fitted);
        this._fit.setOn(!v.fitted);
        this._in.setOn(!v.most);
    }
}
