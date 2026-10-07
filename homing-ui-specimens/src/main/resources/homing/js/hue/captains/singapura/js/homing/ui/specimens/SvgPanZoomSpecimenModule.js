// =============================================================================
// SvgPanZoomSpecimen — the house's pan-zoom view in action: a drawing in a box
// its host sizes, fitted to it whole. The wheel with Ctrl or ⌘ held - or a
// trackpad's pinch - zooms about the pointer; a drag pans once zoomed in; a
// double press zooms in there, or back to fit; while it has the focus, + and −
// zoom, 0 fits, the arrows pan. The buttons zoom by call. Every change of the
// zoom is said, as a share of fit.
//
//   new SvgPanZoomSpecimen(branch, { leaf, say })   leaf: "svg-pan-zoom"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _svgPanZoomSpecimen = Object.freeze({ toString: () => "svgPanZoomSpecimen" });

class SvgPanZoomSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_svgPanZoomSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);
        var svg = new DOMParser().parseFromString(SPECIMEN_DRAWINGS.flow, "image/svg+xml").documentElement;   // the drawing is data
        this._view = new SvgPanZoom(branch.createBranch("view"), { svg: svg, label: "Orders, stock and shipping" });
        css.addClass(this._view.root, sp_fill);
        host.appendChild(this._view.root);
        var last = null;
        this._off = this._view.onChange(function (view) {
            var share = view && typeof view.zoom === "number" ? Math.round(100 * view.zoom) + "% of fit" + (view.fitted ? ", fitted" : "") : "changed";
            if (share !== last) { last = share; say("zoom: " + share); }
        });

        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var b = function (name, label, fn) {
            var x = new ButtonBuilder().label(label).plain().onClick(fn);
            row.appendChild(x.build(branch.createElement(name, x.tag)).el);
        };
        b("in", "Zoom in", function () { self._view.zoomIn(); });
        b("out", "Zoom out", function () { self._view.zoomOut(); });
        b("fit", "Fit it", function () { self._view.fit(); });
        root.appendChild(row);

        this.root = root;
        say("Ctrl or ⌘ and the wheel, a pinch, a double press, or the buttons; drag to pan once zoomed in");
    }

    /** The view varies along no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on: + and − zoom, 0 fits, the arrows pan. */
    key(ev) { return this._view.key(ev); }

    dispose() {
        if (this._off) { try { this._off(); } catch (e) {} this._off = null; }
        try { this._view.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
