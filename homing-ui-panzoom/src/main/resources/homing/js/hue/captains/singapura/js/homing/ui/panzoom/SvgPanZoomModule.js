// =============================================================================
// SvgPanZoom — a drawing in a viewport that zooms and pans, and the zoom's bar at
// its foot, where it is made with one (owner, 23 Oct). A composed component on the
// style manager (RFC 0071): it takes a branch of its own and makes on it its frame,
// its viewport and the canvas the drawing moves on - rooms of its own - and its
// bar, the house's pan-zoom bar, its part: the view keeps its bar's readout and
// buttons right itself, and hears its presses; nothing is told outside. The
// drawing is its caller's, mounted. It never learns where it is placed: it reads
// its box. A box that wraps the drawing - in the flow of a doc - fits it at its own
// size, and zoomed in keeps that height and scrolls; a box its host sizes - a
// stage - is filled, the drawing scaled to fit it whole. Either way it scrolls
// natively. The hand, its behaviour's: the wheel zooms about the pointer - with
// Ctrl or ⌘ held, or a trackpad's pinch, unless its wheel is plain; a drag pans,
// once zoomed in; a double press zooms in there, or back to fit. Its keys,
// natively, while its viewport has the focus: + and − zoom, 0 fits, the arrows
// pan. Its zoom's bounds, in percent of the fit, and its wheel are its fixed
// object's. The arithmetic is PanZoom's; the sizes go by the sheet's variables.
//
//   var z = new SvgPanZoom(branch.createBranch("zoom"),
//               new FixedSvgPanZoom(new FixedPanZoomBar(), { zoom: { least: 25, most: 800 }, wheel: "plain" }), me.place(FixedSvgPanZoom));
//   z.mount(svg)  z.unmount()   the drawing: its caller's
//   z.label = "Module graph"    its name, said to the browser
//   z.zoomIn()  z.zoomOut()  z.fit()  z.measure()  z.view()   z.root  z.dispose()
// =============================================================================

const _panZoomOwner = Object.freeze({ toString: () => "svg-pan-zoom" });
var _ZOOM_STEP = 1.25;
var _PAN_STEP = 40;

/** A press on its bar's buttons, as its bar passes it on: zoomed out, in, or fitted. */
class SvgPanZoomPress {
    constructor(view) { this._view = view; Object.freeze(this); }
    canHandle(type) { return type === StateActionControl; }
    handle(change) {
        var before = change.before || {}, after = change.after;
        for (var id in after) if (after[id] > (before[id] || 0)) this._view._pressed(id);
    }
}

class SvgPanZoom extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = SvgPanZoomBehavior;

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[SvgPanZoom] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_panZoomOwner);
        super(branch.elementMaker("view"), fixed, FixedSvgPanZoom, place ?? manageSvgPanZoom);
        var zoom = this.fixed.zoom;
        if (!(zoom.least > 0 && zoom.least <= 100 && zoom.most >= 100)) throw new RangeError("[SvgPanZoom] its zoom's least is above 0 and at most 100, its most at least 100: " + JSON.stringify(zoom));
        this.branch = branch;
        this._math = new PanZoom({ least: zoom.least / 100, most: zoom.most / 100 });
        this._plain = this.fixed.wheel === "plain";
        this._natural = { w: 0, h: 0, vh: 0, flow: true };
        this._stale = true;       // its own size, and its box, to be read again
        this._dressed = false;    // laid out as zoomed: sized, scaled, pinned when in the flow
        this._drag = null;
        this._svg = null;
        css.addClass(this.el, pz_frame);
        var viewport = branch.createElement("viewport", "div");
        this._room(ROOM_VIEWPORT, viewport);
        css.addClass(viewport, pz_view);
        viewport.setAttribute("tabindex", "0");
        viewport.setAttribute("role", "group");
        var canvas = branch.createElement("canvas", "div");
        this._room(ROOM_CANVAS, canvas);
        css.addClass(canvas, pz_canvas);
        viewport.appendChild(canvas);
        this.el.appendChild(viewport);
        this._viewport = viewport;
        this._canvas = canvas;
        this._bar = null;
        if (this.fixed.foot !== null) {   // its bar, at its foot: kept right by the view, its presses heard
            this._bar = new PanZoomBar(branch.createBranch("bar"), this.fixed.foot, this._place(this.fixed.foot));
            this._bar.onChange(StateActionControl, new SvgPanZoomPress(this));
            this.el.appendChild(this._bar.el);
        }
        this._behavior.hear(viewport);
        var self = this;
        viewport.addEventListener("keydown", function (ev) { if (self._key(ev)) ev.preventDefault(); });   // its keys, natively, on its own element: declared by it
        this._resized = new ResizeObserver(function () { self._stale = true; self.measure(); });
        this._resized.observe(viewport);
        this.label = "A drawing";
        this.root = this.el;
        this._follow(this._math.view());
    }

    /** The drawing it shows, its caller's: an <svg>, on its canvas; once at a time. */
    mount(svg) {
        if (!svg || svg.localName !== "svg") throw new TypeError("[SvgPanZoom] what it shows is an <svg>");
        if (this._svg !== null) throw new Error("[SvgPanZoom] shows a drawing already");
        css.addClass(svg, pz_svg);
        this._canvas.appendChild(svg);
        this._svg = svg;
        this._stale = true;
        this.measure();
    }

    /** The drawing let go: still its caller's. */
    unmount() {
        var svg = this._svg;
        if (svg === null) return null;
        this._dress(false);
        this._dressed = false;
        css.removeClass(svg, pz_svg);
        this._canvas.removeChild(svg);
        this._svg = null;
        return svg;
    }

    /** Its name, said to the browser with its keys. */
    get label() { return this._label; }
    set label(words) {
        this._label = words == null ? "" : String(words);
        this._viewport.setAttribute("aria-label", this._label + ": + and − zoom, 0 fits, the arrows pan");
    }

    view() { return this._math.view(); }
    zoomIn() { this._size(); return this._set(this._math.zoomBy(_ZOOM_STEP)); }
    zoomOut() { this._size(); return this._set(this._math.zoomBy(1 / _ZOOM_STEP)); }
    fit() { this._size(); return this._set(this._math.fit()); }
    measure() { return this._set(this._size()); }

    dispose() {
        this._resized.disconnect();
        this.branch.dissolve();
    }

    /** One of its bar's actions pressed, as its listener hears it. */
    _pressed(id) {
        if (id === "in") this.zoomIn();
        else if (id === "out") this.zoomOut();
        else if (id === "fit") this.fit();
    }

    /** A keydown on its viewport, as its behaviour hears it: true when taken - a key it has no use for is the page's. */
    _key(ev) {
        if (ev.ctrlKey || ev.metaKey || ev.altKey) return false;
        var v = this._size();
        switch (ev.key) {
            case "+": case "=": if (v.most) return false; this.zoomIn(); return true;
            case "-": case "_": if (v.least) return false; this.zoomOut(); return true;
            case "0": if (v.fitted) return false; this.fit(); return true;
            case "ArrowLeft": return this._pan(_PAN_STEP, 0);
            case "ArrowRight": return this._pan(-_PAN_STEP, 0);
            case "ArrowUp": return this._pan(0, _PAN_STEP);
            case "ArrowDown": return this._pan(0, -_PAN_STEP);
            default: return false;
        }
    }

    /**
     * Measured into the arithmetic, quietly - before every act, so none waits on a resize being seen: what is visible of
     * the viewport, and where it is scrolled. Its box read again when it may have changed; a box of another kind - the
     * flow left for a host's, or back - fitted to.
     */
    _size() {
        var vp = this._viewport;
        if (this._svg === null || !vp.clientWidth || !vp.clientHeight) return this._math.view();
        var flow = this._natural.flow;
        if (this._stale) this._readBox();
        var cs = getComputedStyle(vp);
        var w = vp.clientWidth - parseFloat(cs.paddingLeft) - parseFloat(cs.paddingRight);
        var h = vp.clientHeight - parseFloat(cs.paddingTop) - parseFloat(cs.paddingBottom);
        this._math.measure({ w: w, h: h }, this._natural, this._natural.flow ? 1 : 0);
        if (this._natural.flow !== flow) return this._math.fit();
        return this._math.moveTo(-vp.scrollLeft, -vp.scrollTop);
    }

    /** The box, read with the drawing laid out as it is: its own size, the viewport's height, and whether the box wraps it. */
    _readBox() {
        var vp = this._viewport, left = vp.scrollLeft, top = vp.scrollTop;
        this._dress(false);
        var r = this._svg.getBoundingClientRect(), cs = getComputedStyle(vp);
        var room = vp.clientHeight - parseFloat(cs.paddingTop) - parseFloat(cs.paddingBottom);
        this._natural = { w: r.width, h: r.height, vh: vp.offsetHeight, flow: Math.abs(room - r.height) < 1 };
        this._dress(this._dressed);
        vp.scrollLeft = left;
        vp.scrollTop = top;
        this._stale = false;
    }

    /** Laid out as zoomed, or as it is: the canvas sized, the drawing scaled - and, in the flow, the viewport held at its height. */
    _dress(on) {
        css.toggleClass(this._viewport, pz_pinned, on && this._natural.flow);
        css.toggleClass(this._canvas, pz_zoomed, on);
        if (this._svg !== null) css.toggleClass(this._svg, pz_scaled, on);
    }

    /** A pan that moved the drawing: what an arrow takes; one that cannot move it is the page's. */
    _pan(dx, dy) {
        var before = this._math.view(), after = this._math.panBy(dx, dy);
        this._set(after);
        return after.x !== before.x || after.y !== before.y;
    }

    /** Scrolled, by the bars, a wheel, a trackpad or a touch: the offset follows. */
    _scrolled() { if (this._dressed) this._follow(this._math.moveTo(-this._viewport.scrollLeft, -this._viewport.scrollTop)); }

    _wheel(ev) {
        if (!this._plain && !ev.ctrlKey && !ev.metaKey) return;
        ev.preventDefault();
        this._size();
        var unit = ev.deltaMode === 1 ? 16 : ev.deltaMode === 2 ? 400 : 1;
        this._set(this._math.zoomBy(Math.exp(-ev.deltaY * unit * 0.0015), this._at(ev)));
    }

    /** A drag by a mouse or a pen; a touch scrolls the viewport natively. */
    _press(ev) {
        if (ev.button !== 0 || ev.pointerType === "touch" || !this._size().pannable) return;
        this._drag = { id: ev.pointerId, x: ev.clientX, y: ev.clientY };
        try { this._viewport.setPointerCapture(ev.pointerId); } catch (e) { /* a pointer no longer down: the drag goes on uncaptured */ }
    }

    _move(ev) {
        var d = this._drag;
        if (!d || ev.pointerId !== d.id) return;
        this._set(this._math.panBy(ev.clientX - d.x, ev.clientY - d.y));
        d.x = ev.clientX;
        d.y = ev.clientY;
    }

    _release(ev) {
        if (!this._drag || ev.pointerId !== this._drag.id) return;
        this._drag = null;
        try { if (this._viewport.hasPointerCapture(ev.pointerId)) this._viewport.releasePointerCapture(ev.pointerId); } catch (e) { /* none held */ }
    }

    _double(ev) {
        ev.preventDefault();
        this._set(this._size().fitted ? this._math.zoomBy(2, this._at(ev)) : this._math.fit());
    }

    /** Where a pointer is, in what is visible of the viewport's content box. */
    _at(ev) {
        var vp = this._viewport, r = vp.getBoundingClientRect(), cs = getComputedStyle(vp);
        return { x: ev.clientX - r.left - vp.clientLeft - parseFloat(cs.paddingLeft), y: ev.clientY - r.top - vp.clientTop - parseFloat(cs.paddingTop) };
    }

    /** A view applied: at its own scale in the flow, the drawing as it lays out; at any other, or in a host's box, the sizes set, then where it is scrolled. */
    _set(v) {
        var n = this._natural, scaled = this._svg !== null && (!n.flow || Math.abs(v.scale - 1) > 1e-6);
        if (scaled) {
            this._viewport.style.setProperty("--pz-vh", n.vh.toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-w", (n.w * v.scale).toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-h", (n.h * v.scale).toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-sw", n.w.toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-scale", v.scale.toFixed(4));
        }
        this._dressed = scaled;
        this._dress(scaled);
        css.toggleClass(this._viewport, pz_pannable, v.pannable);
        if (scaled) {
            this._viewport.scrollLeft = Math.max(0, -v.x);
            this._viewport.scrollTop = Math.max(0, -v.y);
        }
        return this._follow(v);
    }

    /** Its bar kept right: what it says, and what it can do now. */
    _follow(v) { if (this._bar !== null) this._bar.show(v); return v; }
}
