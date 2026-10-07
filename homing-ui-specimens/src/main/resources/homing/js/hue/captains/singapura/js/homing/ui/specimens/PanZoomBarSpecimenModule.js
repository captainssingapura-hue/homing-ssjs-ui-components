// =============================================================================
// PanZoomBarSpecimen — the house's pan-zoom bar in action: the controls of a
// view it is given - zoom out, the zoom read out as a share of fit, zoom in,
// and back to fit - driving it and following it: out goes off at the least,
// fit at fit, in at the most. Here it drives a drawing; zoom the drawing by
// the wheel with Ctrl or ⌘ held and the bar follows. What the bar reads is
// said as it changes.
//
//   new PanZoomBarSpecimen(branch, { leaf, say })   leaf: "pan-zoom-bar"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _panZoomBarSpecimen = Object.freeze({ toString: () => "panZoomBarSpecimen" });

class PanZoomBarSpecimen {
    constructor(branch, params) {
        var p = params || {};
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_panZoomBarSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        root.appendChild(row);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);
        var svg = new DOMParser().parseFromString(SPECIMEN_DRAWINGS.stages, "image/svg+xml").documentElement;   // the drawing is data
        this._view = new SvgPanZoom(branch.createBranch("view"), { svg: svg, label: "From draft to published" });
        css.addClass(this._view.root, sp_fill);
        host.appendChild(this._view.root);
        this._bar = new PanZoomBar(branch.createBranch("bar"), this._view);
        row.appendChild(this._bar.root);
        var last = null, bar = this._bar;
        // the readout is the bar's one live region among its own children; read it, not its buttons
        var readout = Array.prototype.find.call(bar.root.children, function (c) { return c.getAttribute("aria-live"); }) || bar.root;
        this._off = this._view.onChange(function () {
            var reads = readout.textContent.replace(/\s+/g, " ").trim();
            if (reads !== last) { last = reads; say("the bar reads " + reads); }
        });
        this.root = root;
        say("zoom with the bar's buttons, or the drawing with Ctrl or ⌘ and the wheel: the bar follows");
    }

    /** The bar varies along no axis. */
    extent(axis, v) {}

    /** Its buttons take their keys natively; the drawing's are its own while it has the focus. */
    key(ev) { return false; }

    dispose() {
        if (this._off) { try { this._off(); } catch (e) {} this._off = null; }
        try { this._bar.dispose(); } catch (e) {}
        try { this._view.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
