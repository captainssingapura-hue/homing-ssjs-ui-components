// =============================================================================
// PanZoom — where content sits in its viewport, as numbers: a scale and an
// offset, kept so the content is never lost. Fit is the scale at which the
// content is whole in the viewport; the scale stays between fit and `most`
// times fit. On an axis where the content is smaller than the viewport it is
// centred; where it is larger it pans until its edge meets the viewport's, and
// no further. Knows no DOM: its host measures, applies and listens.
//
//   var pz = new PanZoom({ most: 8 })
//   pz.measure(viewport, content)   { w, h } each: re-measured on a resize, the zoom kept, the offset re-clamped
//   pz.fit()                        the content whole, centred
//   pz.zoomBy(factor, at?)          about a point of the viewport { x, y }; its centre unless said
//   pz.zoomTo(zoom, at?)            to a zoom: a multiple of fit
//   pz.panBy(dx, dy)
//   each → pz.view(): { scale, x, y, zoom, fitted, most, pannable }
//     scale, x, y: the content's scale and its offset in the viewport; zoom: scale ÷ fit;
//     fitted: at fit; most: at the most; pannable: larger than the viewport on an axis
// =============================================================================

class PanZoom {
    constructor(opts) {
        var o = opts || {};
        this._most = Number(o.most) > 1 ? Number(o.most) : 8;
        this._vw = 0; this._vh = 0;
        this._cw = 0; this._ch = 0;
        this._fit = 1; this._s = 1;
        this._x = 0; this._y = 0;
    }

    measure(viewport, content) {
        var zoom = this._s / this._fit;
        this._vw = PanZoom._size(viewport && viewport.w);
        this._vh = PanZoom._size(viewport && viewport.h);
        this._cw = PanZoom._size(content && content.w);
        this._ch = PanZoom._size(content && content.h);
        this._fit = this._vw && this._vh && this._cw && this._ch ? Math.min(this._vw / this._cw, this._vh / this._ch) : 1;
        this._s = this._fit * zoom;
        return this._clamp();
    }

    fit() {
        this._s = this._fit;
        return this._clamp();
    }

    zoomBy(factor, at) { return this.zoomTo(this._s / this._fit * Number(factor), at); }

    zoomTo(zoom, at) {
        var p = at || { x: this._vw / 2, y: this._vh / 2 };
        var s = this._fit * Math.min(this._most, Math.max(1, Number(zoom) || 1));
        var r = s / this._s;
        this._x = p.x - (p.x - this._x) * r;
        this._y = p.y - (p.y - this._y) * r;
        this._s = s;
        return this._clamp();
    }

    panBy(dx, dy) {
        this._x += Number(dx) || 0;
        this._y += Number(dy) || 0;
        return this._clamp();
    }

    view() {
        var zoom = this._s / this._fit;
        return Object.freeze({
            scale: this._s, x: this._x, y: this._y, zoom: zoom,
            fitted: zoom <= 1 + 1e-6,
            most: zoom >= this._most - 1e-6,
            pannable: this._measured() && (this._cw * this._s > this._vw + 0.5 || this._ch * this._s > this._vh + 0.5)
        });
    }

    /** A viewport with no size - hidden, not yet laid out - is not measured: nothing is placed in it. */
    _measured() { return this._vw > 0 && this._vh > 0; }

    _clamp() {
        if (!this._measured()) { this._x = 0; this._y = 0; return this.view(); }
        this._x = PanZoom._axis(this._x, this._vw, this._cw * this._s);
        this._y = PanZoom._axis(this._y, this._vh, this._ch * this._s);
        return this.view();
    }

    /** An offset on one axis: centred when the content is the smaller, else kept between the two edges meeting. */
    static _axis(at, room, size) {
        if (size <= room) return (room - size) / 2;
        return Math.min(0, Math.max(room - size, at));
    }

    static _size(n) { var v = Number(n); return v > 0 && isFinite(v) ? v : 0; }
}
