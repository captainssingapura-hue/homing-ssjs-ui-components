// =============================================================================
// TabTear — the arithmetic of tearing a tab off its strip, with no element in
// sight: a browser's model. Pressed and dragged, a chip stays on its RAIL —
// it moves along the row and nowhere else — while its centre is within a band
// around the strip, however far the hand wanders sideways. Past the band, the
// tab is TORN: a window is made for it there, at the BREACH. Torn, the chip is
// CAPTURED the moment its centre comes back within a narrower band: on the
// rail again at once, mid-drag. The gap between the two bands is what keeps a
// chip on the line from tearing and landing by turns. The hand is usually
// moving fast at that moment, so the window does not chase it: the velocity
// at the breach is taken as the flight's own, and while the hand keeps it the
// window waits where it was made. A MATERIAL CHANGE in the velocity, held for
// a moment — the hand slowing to aim, stopping, turning — ends the flight:
// the window is moved to the hand, the SETTLE, and follows it from then on.
// A tear slower than the floor has no flight: it settles where it breaches.
// Headless, so a strip or a lab draws what this says and it is tested on
// numbers.
//
// THE CENTRE is what both bands measure: where the chip's centre is, or would
// be under the hand — on the rail the chip stays put while the hand roams,
// and in flight it waits at the breach — so where on the chip it was taken
// does not make one tab tear sooner than another.
//
//   new TabTear(opts?)    reusable across gestures; opts as TabTear.DEFAULTS:
//     escape   px beyond the strip, above and below, the centre may go and the chip stay on its rail
//     capture  px beyond the strip within which a torn chip's centre is captured, back onto the rail;
//              a capture wider than the escape is read as the escape
//     change   the material change: the velocity's departure from the breach's, as a share of it
//     hold     ms the change must last before it counts — one noisy sample is not a change
//     span     ms of the hand's path the velocity is measured over
//     idle     ms with no move before a frame says the hand is still: a browser sends a
//              hand's moves about once a frame, so a frame between two of them says
//              nothing about it — only a hand gone quiet for this long is at rest
//     floor    px/ms: a breach slower than this is a slow tear, settled at once
//     measure  how the departure is read:
//              "speed"     the speed either way, |v| against |v0|
//              "slowdown"  the speed falling only, so a hand that speeds up flies on
//              "velocity"  the vector against the vector, so a turn at one speed counts too
//   tear.set(opts)                    any of them, live
//   tear.press(x, y, t, band, at?)    the gesture starts; band = { top, bottom }, the strip's
//                                     extent in the coordinates the points come in; at:
//       centre  { dx, dy }: the chip's centre from the hand at the press; the hand's own unless said
//       afloat  the chip is a window already, dragged by its bar: the gesture starts settled
//               (why "afloat"), and a capture puts it on the rail
//       land    (cx, cy, d) → a band, or null: where a torn chip's centre would be captured, for a
//               host with more than one strip — the band of the strip that takes a centre at
//               (cx, cy) within d of it, sideways too, as { top, bottom, ... } with whatever else
//               the host needs back. The captured band is the rail from then on, and its escape
//               is measured from it. Without it, the press's band alone captures, by height only
//   tear.move(x, y, t)     → step     the hand moved
//   tear.tick(t)           → step     a frame: a hand that stops sends no moves, so this is
//                                     what ends a flight then — once it has been quiet for idle
//   tear.release(x, y, t)  → step     the end: a release in flight settles where it lets go
//   step: { phase, changed, anchor, speed, ratio, breach, settle, captured }
//     phase   "rail" → "flight" → "follow" → "done", and back to "rail" from either of the
//             torn two by a capture; changed when this step moved it on
//     anchor  where the window's grip is: null on the rail, the breach in flight, the hand after
//     speed   px/ms now; ratio the departure from the breach's (null before it)
//     breach  { x, y, t, vx, vy, speed } or null;  settle { x, y, t, why } or null,
//             why "change", "slow", "release" or "afloat"; both cleared by a capture
//     captured  { x, y, t, band }, the hand at the last capture and the band it was captured into, or null
//   tear.phase() .breach() .settle() .torn() .inBand(y) .step()
//
// Times are milliseconds on one clock — an event's timeStamp and an animation
// frame's are the same one. Pure: it imports nothing and touches no DOM.
// =============================================================================

class TabTear {
    static DEFAULTS = Object.freeze({ escape: 24, capture: 8, change: 0.5, hold: 40, span: 60, idle: 50, floor: 0.1, measure: "speed" });
    static MEASURES = Object.freeze(["speed", "slowdown", "velocity"]);

    constructor(opts) {
        this._o = Object.assign({}, TabTear.DEFAULTS);
        this.set(opts);
        this._phase = "idle";
    }

    set(opts) {
        var o = opts || {};
        for (var k in TabTear.DEFAULTS) if (o[k] != null) this._o[k] = k === "measure" ? String(o[k]) : Number(o[k]);
        if (TabTear.MEASURES.indexOf(this._o.measure) < 0) throw new Error("[TabTear] measure is one of " + TabTear.MEASURES.join(", ") + ": " + this._o.measure);
        return this;
    }
    options() { return Object.assign({}, this._o); }

    press(x, y, t, band, at) {
        if (!band || !(band.bottom >= band.top)) throw new Error("[TabTear] press wants the strip's band: { top, bottom }");
        var c = at && at.centre ? at.centre : null;
        this._band = { top: Number(band.top), bottom: Number(band.bottom) };
        this._centre = { dx: c ? Number(c.dx) || 0 : 0, dy: c ? Number(c.dy) || 0 : 0 };
        this._lands = at && typeof at.land === "function" ? at.land : null;
        this._samples = [{ x: x, y: y, t: t }];
        this._moved = t;
        this._v = { x: 0, y: 0 };
        this._breach = null;
        this._settle = null;
        this._captured = null;
        this._since = null;
        this._ratio = null;
        this._phase = "rail";
        this._torn = false;
        if (at && at.afloat) { this._phase = "follow"; this._torn = true; this._settle = { x: x, y: y, t: t, why: "afloat" }; }
        this._changed = true;
        return this.step();
    }

    move(x, y, t) {
        this._need();
        this._changed = false;
        this._sample(x, y, t);
        this._moved = this._hand().t;
        var cy = y + this._centre.dy, into;
        if (this._phase === "rail") { if (!this._within(cy, this._o.escape)) this._tear(t); }
        else if ((into = this._landing(x + this._centre.dx, cy))) this._capture(t, into);
        else this._read(t);
        return this.step();
    }

    tick(t) {
        this._need();
        this._changed = false;
        var h = this._hand();
        if (t > h.t && t - this._moved >= this._o.idle) this._sample(h.x, h.y, t);   // quiet long enough: the hand is at rest, said as a sample
        this._read(t);
        return this.step();
    }

    release(x, y, t) {
        this._need();
        this._changed = false;
        this._sample(x, y, t);
        if (this._phase === "flight") this._land("release", t);
        this._phase = "done";
        this._changed = true;
        return this.step();
    }

    phase() { return this._phase; }
    breach() { return this._breach; }
    settle() { return this._settle; }
    torn() { return this._torn; }
    /** Whether a hand at this height keeps the chip's centre within the escape band, on its rail. */
    inBand(y) { return this._within(y + this._centre.dy, this._o.escape); }

    step() {
        var h = this._hand(), b = this._breach, anchor = null;
        if (this._phase === "flight") anchor = { x: b.x, y: b.y };
        else if (this._torn) anchor = { x: h.x, y: h.y };
        return { phase: this._phase, changed: this._changed, anchor: anchor, speed: TabTear._len(this._v), ratio: this._ratio,
                 breach: b, settle: this._settle, captured: this._captured };
    }

    // ── the hand's path, and its velocity over the last span ─────────────
    _need() { if (this._phase === "idle" || this._phase === "done") throw new Error("[TabTear] no gesture: press first"); }
    _hand() { return this._samples[this._samples.length - 1]; }
    _within(cy, d) { return cy >= this._band.top - d && cy <= this._band.bottom + d; }
    /** The band a torn centre would be captured into: the host's word when it has more than one strip, else the press's own. */
    _landing(cx, cy) {
        var d = Math.min(this._o.capture, this._o.escape);
        if (this._lands) return this._lands(cx, cy, d) || null;
        return this._within(cy, d) ? this._band : null;
    }
    _sample(x, y, t) {
        var s = this._samples;
        if (t < s[s.length - 1].t) t = s[s.length - 1].t;   // one clock, never backwards
        s.push({ x: x, y: y, t: t });
        // keep the span, and the one sample before it, so a sparse path still has two ends
        var from = t - this._o.span, i = 0;
        while (i < s.length - 2 && s[i + 1].t <= from) i++;
        if (i) s.splice(0, i);
        var a = s[0], z = s[s.length - 1], dt = z.t - a.t;
        if (dt > 0) this._v = { x: (z.x - a.x) / dt, y: (z.y - a.y) / dt };
    }

    // ── out of the band: torn, and the flight watched ────────────────────
    _tear(t) {
        var h = this._hand(), v = this._v;
        this._breach = { x: h.x, y: h.y, t: t, vx: v.x, vy: v.y, speed: TabTear._len(v) };
        this._settle = null;
        this._phase = "flight";
        this._torn = true;
        this._changed = true;
        this._ratio = 0;
        if (this._breach.speed < this._o.floor) this._land("slow", t);
    }

    /** The departure read whenever there is a breach to read it against; acted on only in flight. */
    _read(t) {
        if (!this._breach) return;
        var r = this._ratio = TabTear.departure(this._o.measure, this._v, this._breach);
        if (this._phase !== "flight") return;
        if (r <= this._o.change) { this._since = null; return; }
        if (this._since === null) this._since = t;
        if (t - this._since >= this._o.hold) this._land("change", t);
    }

    /** Back within the capture: on the rail again, as if never torn — a later escape tears it anew. */
    _capture(t, band) {
        var h = this._hand();
        this._band = band;
        this._captured = { x: h.x, y: h.y, t: t, band: band };
        this._breach = null;
        this._settle = null;
        this._since = null;
        this._ratio = null;
        this._phase = "rail";
        this._torn = false;
        this._changed = true;
    }

    _land(why, t) {
        var h = this._hand();
        this._settle = { x: h.x, y: h.y, t: t, why: why };
        this._phase = "follow";
        this._changed = true;
    }

    /** How far a velocity has departed from the breach's, as a share of the breach's speed, by the measure named. */
    static departure(measure, v, b) {
        var s0 = b.speed, s = TabTear._len(v);
        if (!(s0 > 0)) return Infinity;
        if (measure === "slowdown") return Math.max(0, s0 - s) / s0;
        if (measure === "velocity") return Math.hypot(v.x - b.vx, v.y - b.vy) / s0;
        return Math.abs(s - s0) / s0;
    }

    static _len(v) { return Math.hypot(v.x, v.y); }
}
