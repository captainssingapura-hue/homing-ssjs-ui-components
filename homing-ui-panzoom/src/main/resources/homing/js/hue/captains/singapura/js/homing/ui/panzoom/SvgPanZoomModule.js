// =============================================================================
// SvgPanZoom — an SVG in a viewport that zooms and pans, as a BRANCH component:
// the caller makes a sub-branch for it and hands it in, with the <svg> to show.
// Wherever it is placed it is the same. In the flow of a doc the viewport is the
// drawing's own size and opens fitted; zoomed in, it keeps that height and
// scrolls - natively: its scroll bars say where the drawing is, and a trackpad
// or a touch pans it as any scrolled box.
//   The hand: the wheel zooms about the pointer - with Ctrl or ⌘ held, or a
//   trackpad's pinch, unless the wheel is said to be the view's alone ("plain");
//   a plain wheel scrolls - the drawing while it has somewhere to go, then the
//   page. A drag pans, once zoomed in; a double press zooms in there, or back
//   to fit.
//   The keys, natively, while the viewport has the focus: + and − zoom, 0 fits,
//   the arrows pan; a key it has no use for is left to the page.
// Zoomed, the drawing keeps the size it fitted at and is scaled from its corner
// on a canvas as large as it is drawn, which is what the viewport scrolls over;
// the sizes go by the sheet's variables, never a style of its own. The
// arithmetic is PanZoom's. The viewport is the caller's to dress - a plate, a
// frame - and it wears a control's ring when focused.
//
//   var z = new SvgPanZoom(branch.createBranch("zoom"), { svg, label?, wheel?: "modified" | "plain", most? })
//   z.root                the viewport: what the caller appends
//   z.view()              PanZoom's view;  z.onChange(fn(view)) → off()
//   z.zoomIn()  z.zoomOut()  z.fit()   z.key(ev) → taken
//   z.measure()           re-measure: it measures itself before every act, and on every resize it sees
//   z.dispose()
// =============================================================================

const _panZoomOwner = Object.freeze({ toString: () => "svg-pan-zoom" });
var _ZOOM_STEP = 1.25;
var _PAN_STEP = 40;

class SvgPanZoom {
    constructor(branch, opts) {
        var o = opts || {}, self = this;
        if (!branch) throw new Error("[SvgPanZoom] a branch of its own is required");
        if (!o.svg || o.svg.localName !== "svg") throw new Error("[SvgPanZoom] the <svg> to show is required");
        branch.activate(_panZoomOwner);
        this.branch = branch;
        this._plain = o.wheel === "plain";
        this._math = new PanZoom({ most: o.most });
        this._natural = { w: 0, h: 0, vh: 0 };
        this._heard = [];
        this._drag = null;
        var root = branch.createElement("viewport", "div");
        css.addClass(root, pz_view);
        root.setAttribute("tabindex", "0");
        root.setAttribute("role", "group");
        root.setAttribute("aria-label", (o.label || "A drawing") + ": + and − zoom, 0 fits, the arrows pan");
        var canvas = branch.createElement("canvas", "div");
        css.addClass(canvas, pz_canvas);
        css.addClass(o.svg, pz_svg);
        canvas.appendChild(o.svg);
        root.appendChild(canvas);
        this.root = root;
        this._canvas = canvas;
        this._svg = o.svg;
        root.addEventListener("wheel", function (ev) { self._wheel(ev); }, { passive: false });
        root.addEventListener("scroll", function () { self._scrolled(); });
        root.addEventListener("pointerdown", function (ev) { self._press(ev); });
        root.addEventListener("pointermove", function (ev) { self._move(ev); });
        root.addEventListener("pointerup", function (ev) { self._release(ev); });
        root.addEventListener("pointercancel", function (ev) { self._release(ev); });
        root.addEventListener("dblclick", function (ev) { self._double(ev); });
        root.addEventListener("keydown", function (ev) { if (self.key(ev)) ev.preventDefault(); });
        this._resized = new ResizeObserver(function () { self.measure(); });
        this._resized.observe(root);
    }

    view() { return this._math.view(); }

    onChange(fn) {
        var heard = this._heard;
        heard.push(fn);
        return function () { var i = heard.indexOf(fn); if (i >= 0) heard.splice(i, 1); };
    }

    zoomIn() { this._size(); return this._set(this._math.zoomBy(_ZOOM_STEP)); }

    zoomOut() { this._size(); return this._set(this._math.zoomBy(1 / _ZOOM_STEP)); }

    fit() { this._size(); return this._set(this._math.fit()); }

    /** A keydown, its own or handed on by whoever holds the keys for it: true when taken. */
    key(ev) {
        if (ev.ctrlKey || ev.metaKey || ev.altKey) return false;
        var v = this._size();
        switch (ev.key) {
            case "+": case "=": if (v.most) return false; this.zoomIn(); return true;
            case "-": case "_": if (v.fitted) return false; this.zoomOut(); return true;
            case "0": if (v.fitted) return false; this.fit(); return true;
            case "ArrowLeft": return this._pan(_PAN_STEP, 0);
            case "ArrowRight": return this._pan(-_PAN_STEP, 0);
            case "ArrowUp": return this._pan(0, _PAN_STEP);
            case "ArrowDown": return this._pan(0, -_PAN_STEP);
            default: return false;
        }
    }

    measure() { return this._set(this._size()); }

    dispose() {
        this._resized.disconnect();
        this._heard = [];
        this.branch.dissolve();
    }

    /**
     * Measured into the arithmetic, quietly - before every act, so none waits on a resize being seen.
     * Fitted, the drawing is laid out as it is, and its size, and the viewport's height, are taken as
     * its own; zoomed, the viewport is what is visible of it, and where it is scrolled is the offset.
     */
    _size() {
        var root = this.root, cs = getComputedStyle(root);
        if (!root.clientWidth || !root.clientHeight) return this._math.view();
        if (this._math.view().fitted) {
            var r = this._svg.getBoundingClientRect();
            this._natural = { w: r.width, h: r.height, vh: root.offsetHeight };
        }
        var w = root.clientWidth - parseFloat(cs.paddingLeft) - parseFloat(cs.paddingRight);
        var h = root.clientHeight - parseFloat(cs.paddingTop) - parseFloat(cs.paddingBottom);
        this._math.measure({ w: w, h: h }, this._natural, 1);
        return this._math.view().fitted ? this._math.view() : this._math.moveTo(-root.scrollLeft, -root.scrollTop);
    }

    /** A pan that moved the drawing: what an arrow takes; one that cannot move it is the page's. */
    _pan(dx, dy) {
        var before = this._math.view(), after = this._math.panBy(dx, dy);
        this._set(after);
        return after.x !== before.x || after.y !== before.y;
    }

    /** Scrolled, by the bars, a wheel, a trackpad or a touch: the offset follows. */
    _scrolled() {
        if (this._math.view().fitted) return;
        this._tell(this._math.moveTo(-this.root.scrollLeft, -this.root.scrollTop));
    }

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
        try { this.root.setPointerCapture(ev.pointerId); } catch (e) { /* a pointer no longer down: the drag goes on uncaptured */ }
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
        if (this.root.hasPointerCapture(ev.pointerId)) this.root.releasePointerCapture(ev.pointerId);
    }

    _double(ev) {
        ev.preventDefault();
        this._set(this._size().fitted ? this._math.zoomBy(2, this._at(ev)) : this._math.fit());
    }

    /** Where a pointer is, in what is visible of the viewport's content box. */
    _at(ev) {
        var r = this.root.getBoundingClientRect(), cs = getComputedStyle(this.root);
        return { x: ev.clientX - r.left - this.root.clientLeft - parseFloat(cs.paddingLeft),
                 y: ev.clientY - r.top - this.root.clientTop - parseFloat(cs.paddingTop) };
    }

    /** A view applied: fitted, the drawing as it lays out; zoomed, the sizes set, then where it is scrolled. */
    _set(v) {
        var zoomed = !v.fitted, n = this._natural;
        if (zoomed) {
            this.root.style.setProperty("--pz-vh", n.vh.toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-w", (n.w * v.scale).toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-h", (n.h * v.scale).toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-sw", n.w.toFixed(2) + "px");
            this._canvas.style.setProperty("--pz-scale", v.scale.toFixed(4));
        }
        css.toggleClass(this.root, pz_pinned, zoomed);
        css.toggleClass(this._canvas, pz_zoomed, zoomed);
        css.toggleClass(this._svg, pz_scaled, zoomed);
        css.toggleClass(this.root, pz_pannable, v.pannable);
        if (zoomed) {
            this.root.scrollLeft = Math.max(0, -v.x);
            this.root.scrollTop = Math.max(0, -v.y);
        }
        return this._tell(v);
    }

    _tell(v) {
        this._heard.slice().forEach(function (fn) { fn(v); });
        return v;
    }
}
