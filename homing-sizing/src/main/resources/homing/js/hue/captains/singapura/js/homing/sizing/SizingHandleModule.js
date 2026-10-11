// =============================================================================
// SizingHandle — a sizable boundary's behaviour (RFC 0068, appendix C §2):
// the hand takes it and moves it, the keys step it, and it says where it
// stands. Sizing's, the same wherever a rail is applied. It is armed on the
// line a layout drew and dressed, and brings no look of its own: the line is
// the layout's part, in the layout's look, and the handle only writes the
// reflections that look answers - held (data-dragging) from the press to the
// release, not sizable now (aria-disabled). What it brings is behaviour: the
// cursor, the reach of the hand a little past the line either way, the hand
// and the keys, and the rail's arithmetic.
//
//   new SizingHandle(el, { axis, index, rail, measure, onMove, onDone, step?, leap? })
//     el        the boundary's line, made and worn by its layout
//     axis      the rail's: "horizontal" - the line moves along x - or "vertical"
//     index     the boundary after box `index` of the rail
//     rail()    the rail as it is now
//     measure() → { room, least: [px, ...] }: the room the rail's shares divide,
//               and each box's least - read when a move begins
//     onMove(rail)  a new rail while it moves: its layout lays it out
//     onDone(rail)  the rail it was left at, once, when a move ends having changed it
//     step      the keys' step in px, 16 unless said; leap, with Shift, 64
//
//   handle.key(ev)      a keydown handed in by whoever holds the keys: the arrows
//                       along its axis step it, Shift a leap, Home and End as far as
//                       it goes; true when taken. No keydown listener of its own -
//                       how the keys reach a handle is its layout's to say.
//   handle.enabled(on?) sizable now, or not: aria-disabled, the hand and the keys refused
//   handle.reflect()    aria-valuenow, -valuemin, -valuemax: where the boundary stands
//                       and how far it can go, in percent of the rail - its layout
//                       calls it once the line is laid out; a move calls it itself
//   handle.dispose()    unarmed; a move under way ends where it is, unreported
//
// A move is reckoned from where it began - the rail and the pointer at the
// press - so it never drifts; a cancelled move puts the rail back and says
// nothing. `css` is injected with the styles import.
// =============================================================================

var _KEYS = Object.freeze({
    horizontal: Object.freeze({ ArrowLeft: -1, ArrowRight: 1, Home: -Infinity, End: Infinity }),
    vertical: Object.freeze({ ArrowUp: -1, ArrowDown: 1, Home: -Infinity, End: Infinity })
});

class SizingHandle {
    constructor(el, opts) {
        if (!el) throw new Error("[SizingHandle] the boundary's element is required");
        if (!opts || (opts.axis !== "horizontal" && opts.axis !== "vertical")) throw new Error("[SizingHandle] axis is 'horizontal' or 'vertical'");
        if (typeof opts.rail !== "function" || typeof opts.measure !== "function") throw new Error("[SizingHandle] rail() and measure() are required");
        this.el = el;
        this._axis = opts.axis;
        this._index = opts.index | 0;
        this._rail = opts.rail;
        this._measure = opts.measure;
        this._onMove = typeof opts.onMove === "function" ? opts.onMove : function () {};
        this._onDone = typeof opts.onDone === "function" ? opts.onDone : function () {};
        this._step = opts.step == null ? 16 : Math.max(1, +opts.step);
        this._leap = opts.leap == null ? 64 : Math.max(1, +opts.leap);
        this._on = true;
        this._end = null;      // ends a move under way
        css.addClass(el, sz_handle, opts.axis === "horizontal" ? sz_handle_h : sz_handle_v);
        var self = this;
        this._down = function (ev) { self._press(ev); };
        el.addEventListener("pointerdown", this._down);
    }

    _along(ev) { return this._axis === "horizontal" ? ev.clientX : ev.clientY; }

    _press(down) {
        if (down.button !== 0 || !this._on || this._end) return;
        var self = this, el = this.el;
        var m = this._measure(), from = this._rail(), last = from, start = this._along(down);
        el.setAttribute("data-dragging", "");
        try { el.setPointerCapture(down.pointerId); } catch (err) {}
        function onMove(ev) {
            var r = Rail.move(from, self._index, self._along(ev) - start, m.room, m.least);
            if (Rail.equals(r, last)) return;
            last = r;
            self._onMove(r);
        }
        function onEnd(ev) { end(ev && ev.type === "pointerup", true); }
        function end(commit, report) {
            el.removeEventListener("pointermove", onMove);
            el.removeEventListener("pointerup", onEnd);
            el.removeEventListener("pointercancel", onEnd);
            el.removeAttribute("data-dragging");
            try { el.releasePointerCapture(down.pointerId); } catch (err) {}
            self._end = null;
            if (!report) return;
            if (!commit) { if (!Rail.equals(last, from)) self._onMove(from); }
            else if (!Rail.equals(last, from)) self._onDone(last);
            self.reflect();
        }
        this._end = end;
        el.addEventListener("pointermove", onMove);
        el.addEventListener("pointerup", onEnd);
        el.addEventListener("pointercancel", onEnd);
    }

    key(ev) {
        if (!this._on || !ev) return false;
        var sign = _KEYS[this._axis][ev.key];
        if (sign == null) return false;
        var m = this._measure(), from = this._rail();
        var d = sign === Infinity || sign === -Infinity ? sign : sign * (ev.shiftKey ? this._leap : this._step);
        var r = Rail.move(from, this._index, d, m.room, m.least);
        if (!Rail.equals(r, from)) { this._onMove(r); this._onDone(r); }
        this.reflect();
        return true;
    }

    enabled(on) {
        if (arguments.length === 0) return this._on;
        this._on = !!on;
        if (this._on) this.el.removeAttribute("aria-disabled");
        else { this.el.setAttribute("aria-disabled", "true"); if (this._end) this._end(false, true); }
        return this;
    }

    reflect() {
        var r = this._rail(), m = this._measure(), i = this._index;
        var now = Rail.at(r, i), lo = 0, hi = Rail.WHOLE;
        if (m.room > 0) {
            lo = Rail.at(Rail.move(r, i, -Infinity, m.room, m.least), i);
            hi = Rail.at(Rail.move(r, i, Infinity, m.room, m.least), i);
        }
        this.el.setAttribute("aria-valuenow", SizingHandle._percent(now));
        this.el.setAttribute("aria-valuemin", SizingHandle._percent(lo));
        this.el.setAttribute("aria-valuemax", SizingHandle._percent(hi));
        return this;
    }

    static _percent(millionths) { return String(Math.round(millionths / 1000) / 10); }

    dispose() {
        if (this._end) this._end(false, false);
        this.el.removeEventListener("pointerdown", this._down);
    }
}
