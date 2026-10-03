// =============================================================================
// DeskHand — the hand on a chip, across a desk: one gesture from the press to
// the release, wherever the chip goes — along a dock's rail, torn off into a
// float of its own, captured onto a dock's rail again — exactly as a naked
// chip goes in the tear lab, TabTear deciding (RFC 0066 E3, appendix
// "tab-panes"). The desk makes one and hands it to every host it holds; a
// host gives it a press on a chip, and the rest of the gesture is the hand's.
//
//   new DeskHand(desk, { tear? })       tear: TabTear's options
//   hand.press(tp, ev, host) → true     a press on tp's chip in host: the gesture is the hand's
//   hand.held()                         → the tab-pane in the hand, or null
//   hand.tick(t)                        a frame, by call: what the animation frames do
//   hand.tear                           the TabTear it asks, whose options may be set live
//
// On a DOCK's chip the gesture starts on the rail: past the drag threshold the
// chip slides along the strip, by the strip's own hand, driven by call. Past
// the escape it is TORN: the slide called off, a float made at the breach and
// the tab-pane moved into it (TabMoved), the frame placed so the chip is where
// the hand crossed; it waits there through the flight, goes to the hand at the
// settle and follows it. On a FLOAT's chip the gesture starts afloat, the frame
// following the hand. Torn or afloat, a chip whose centre comes within the
// capture of a dock's strip — over it, sideways — is CAPTURED: moved into that
// dock where its centre is along the strip (TabMoved), the float it leaves
// gone, and the slide goes on in that strip. Let go on a rail: it lands where
// it is nearest. Let go torn: the float stays where it is, said moved once.
//
// The pointer is captured on the desk's own box, which stays put while the
// chip goes from strip to float to strip: a capture on the chip is lost the
// moment the chip changes hands. Every sample the browser gathered is fed in
// at its own time, and an animation frame ticks the gesture, so a still hand
// is heard. It mints nothing: the floats are the desk's, the slides the strips'.
// =============================================================================

var _HAND_THRESHOLD = 4;

class DeskHand {
    constructor(desk, opts) {
        this._desk = desk;
        this.tear = new TabTear(opts && opts.tear);
        this._g = null;
    }

    held() { return this._g ? this._g.tp : null; }

    press(tp, ev, host) {
        if (this._g || ev.button !== 0) return false;
        var self = this, f = this._desk._floaters.get(host) || null, x = ev.clientX, y = ev.clientY, r = DeskHand._rect(tp.chip);
        var g = this._g = { tp: tp, host: f ? null : host, float: null, pid: ev.pointerId, start: { x: x, y: y }, hand: { x: x, y: y }, moved: false, run: null,
                            grip: { x: x - r.left, y: y - r.top }, centre: { dx: r.left + r.width / 2 - x, dy: r.top + r.height / 2 - y } };
        if (f) this._carry(f);
        this.tear.press(x, y, ev.timeStamp, DeskHand._band(host.bar()),
                        { centre: g.centre, afloat: !!f, land: function (cx, cy, d) { return self._land(cx, cy, d); } });
        this._listen();
        return true;
    }

    tick(t) { if (this._g && this._g.moved) this._act(this.tear.tick(t)); }

    // ── the gesture ──────────────────────────────────────────────────────
    _listen() {
        var self = this, g = this._g, box = this._desk._host, win = typeof window !== "undefined" ? window : null;
        function move(e) { if (e.pointerId === g.pid) self._move(e); }
        function end(e) { if (e.pointerId == null || e.pointerId === g.pid) self._end(e); }
        try { box.setPointerCapture(g.pid); } catch (err) {}
        box.addEventListener("pointermove", move);
        ["pointerup", "pointercancel", "lostpointercapture"].forEach(function (k) { box.addEventListener(k, end); });
        if (win) ["pointerup", "pointercancel", "blur"].forEach(function (k) { win.addEventListener(k, end); });
        g.off = function () {
            box.removeEventListener("pointermove", move);
            ["pointerup", "pointercancel", "lostpointercapture"].forEach(function (k) { box.removeEventListener(k, end); });
            if (win) ["pointerup", "pointercancel", "blur"].forEach(function (k) { win.removeEventListener(k, end); });
            if (g.raf && typeof cancelAnimationFrame === "function") cancelAnimationFrame(g.raf);
            try { box.releasePointerCapture(g.pid); } catch (err) {}
        };
        this._frame();
    }

    _frame() {
        var self = this, g = this._g;
        if (typeof requestAnimationFrame !== "function") return;
        g.raf = requestAnimationFrame(function (ts) { if (self._g !== g) return; self.tick(ts); self._frame(); });
    }

    _move(e) {
        var g = this._g, self = this, s = null;
        // every sample the browser gathered since the last event, each at its own time: the velocity wants the hand's path
        var all = typeof e.getCoalescedEvents === "function" ? e.getCoalescedEvents() : [];
        if (!all.length) all = [e];
        all.forEach(function (q) { s = self.tear.move(q.clientX, q.clientY, q.timeStamp); });
        g.hand = { x: e.clientX, y: e.clientY };
        if (!g.moved) {
            if (Math.hypot(g.hand.x - g.start.x, g.hand.y - g.start.y) < _HAND_THRESHOLD) return;
            g.moved = true;
            if (g.host) g.run = g.host.slide(g.tp, g.start.x, g.grip.x);
        }
        this._act(s);
    }

    /** What TabTear said, done: the slide along a rail — captured onto one, if the chip was off — or the float waiting, or at the hand. */
    _act(s) {
        var g = this._g;
        if (s.phase === "rail") {
            if (!g.host) this._dock(s);
            if (g.run) g.run.place(g.hand.x);
            return;
        }
        if (!g.float) this._lift(s);
        g.float.frame.place(s.anchor.x - g.frameGrip.x - g.origin.x, s.anchor.y - g.frameGrip.y - g.origin.y);
    }

    /** Torn: the slide called off, and the tab-pane into a float of its own at the breach. */
    _lift(s) {
        var g = this._g;
        if (g.run) { g.run.end(false, true); g.run = null; }
        var f = this._desk.detach(g.tp, { x: s.breach.x, y: s.breach.y });
        g.host = null;
        this._carry(f);
    }

    /** Captured: into the dock its centre came over, where the centre is along the strip; the float it leaves goes; the slide goes on there. */
    _dock(s) {
        var g = this._g, into = s.captured.band.host;
        this._desk.move(g.tp, into, into.indexAt(g.hand.x + g.centre.dx));
        into.switchTab(g.tp.id);
        g.float = null;
        g.host = into;
        g.run = into.slide(g.tp, g.hand.x, g.grip.x);
    }

    /** A float in the hand: where its frame is held from — the grip on the chip, and the chip's place in the frame — and where it was. */
    _carry(f) {
        var g = this._g, fr = DeskHand._rect(f.frame.root), cr = DeskHand._rect(g.tp.chip), lr = DeskHand._rect(this._desk.layer.root);
        g.float = f;
        g.from = f.frame.bounds();
        g.frameGrip = { x: g.grip.x + cr.left - fr.left, y: g.grip.y + cr.top - fr.top };
        g.origin = { x: lr.left, y: lr.top };
    }

    /** The strip a torn chip's centre would be captured onto: a dock's — never a float's — over it sideways and within d of it, that would take the tab-pane. */
    _land(cx, cy, d) {
        var g = this._g, desk = this._desk, docks = desk.docks();
        for (var i = 0; i < docks.length; i++) {
            var h = docks[i];
            if (desk._floaters.has(h) || typeof h.slide !== "function") continue;
            var r = DeskHand._rect(h.bar());
            if (cx < r.left || cx > r.left + r.width || cy < r.top - d || cy > r.top + r.height + d) continue;
            if (!h.admits(g.tp)) continue;
            return { top: r.top, bottom: r.top + r.height, left: r.left, right: r.left + r.width, host: h };
        }
        return null;
    }

    _end(e) {
        var g = this._g;
        g.off();
        this._g = null;
        var s = this.tear.release(e.clientX == null ? g.hand.x : e.clientX, e.clientY == null ? g.hand.y : e.clientY, e.timeStamp);
        if (g.run) { g.run.end(e.type === "pointerup"); return; }
        if (!g.float) return;
        if (s.anchor) g.float.frame.place(s.anchor.x - g.frameGrip.x - g.origin.x, s.anchor.y - g.frameGrip.y - g.origin.y);
        g.float.frame.movedFrom(g.from.x, g.from.y);
    }

    static _rect(el) {
        var r = el && typeof el.getBoundingClientRect === "function" ? el.getBoundingClientRect() : null;
        return r ? { left: r.left, top: r.top, width: r.width, height: r.height } : { left: 0, top: 0, width: 0, height: 0 };
    }
    static _band(el) { var r = DeskHand._rect(el); return { top: r.top, bottom: r.top + r.height }; }
}
