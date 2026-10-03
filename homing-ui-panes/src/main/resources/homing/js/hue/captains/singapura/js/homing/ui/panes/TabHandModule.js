// =============================================================================
// TabHand — the hand on a strip's chip: the press, the drag along the row,
// and the letting go. The strip arms each chip with it and keeps the state
// — which chip is held — and the classes; the hand reads the rectangles,
// does the arithmetic through TabDrag, writes the chip's position as a
// custom property, and tells the strip what happened. One hand per strip;
// it holds at most one chip.
//
//   new TabHand(strip)
//     hand.arm(chip, closeBtn?)   the chip answers the hand from now on
//     hand.disarm(chip)           and no longer: a tab-pane's chip, leaving for another strip
//     hand.held()                 → the chip in the hand, or null
//     hand.slide(chip, x, grabX?) → a slide along the rail begun by call, the hand at x and
//         grabX into the chip (where x is over it, unless said): { place(x), end(commit, leaving?) }.
//         end(true) settles the chip where it is nearest and tells the strip it landed;
//         end(false) eases it back where it was, said nowhere; end(false, true) puts it back
//         at once — a chip about to leave the strip, which nothing should be seen easing.
//         What a desk's hand drives, whose gesture began elsewhere
//
// A press the strip's takeover(chip, ev) answers true for is not the hand's:
// a desk holds the gesture then, and drives the slide by call.
//
// The row is a rail. The chip goes where the hand goes along it, at the
// press's offset within the chip, kept within the slots — never before a
// pinned one — and never off the rail: the hand's height is nothing. The
// slot the chip is nearest is its destination, and the strip is told who
// steps aside. Let go, the chip settles onto its slot from where the hand
// left it, eased as the design eases a selectable, and the strip is told
// where it landed. Nothing is positioned by hand: --mtp-drag-x on the chip,
// which the strip's class reads.
//
// The pointer is captured at the press, not at the first move: a capture
// asked for mid-gesture is not always granted, and without it the chip
// hears the hand only while the hand is over it — it lags, a quick hand
// escapes it, and a release elsewhere is never heard, so the chip keeps
// following a hand that let go. Should the capture be lost all the same,
// the window hears the release and the drag ends there.
// =============================================================================

var _DRAG_THRESHOLD = 4;

class TabHand {
    constructor(strip) {
        this._strip = strip;
        this._held = null;
        this._armed = new Map();   // chip → its press listener, so a chip that leaves can be disarmed
    }
    held() { return this._held; }

    disarm(c) {
        var press = this._armed.get(c);
        if (press) { c.removeEventListener("pointerdown", press); this._armed.delete(c); }
    }

    arm(c, closeBtn) {
        var self = this, strip = this._strip;
        if (this._armed.has(c)) return;
        var press = function (down) {
            if (down.button !== 0 || self._held) return;
            if (closeBtn && closeBtn.contains(down.target)) return;
            if (strip._takeover && strip._takeover(c, down)) return;   // a desk's gesture: it drives the slide by call
            var startX = down.clientX, run = null;
            var win = typeof window !== "undefined" ? window : null;
            try { c.setPointerCapture(down.pointerId); } catch (err) {}
            function letGo() {
                c.removeEventListener("pointermove", onMove);
                c.removeEventListener("pointerup", onEnd);
                c.removeEventListener("pointercancel", onEnd);
                c.removeEventListener("lostpointercapture", onEnd);
                if (win) { win.removeEventListener("pointerup", onEnd); win.removeEventListener("pointercancel", onEnd); win.removeEventListener("blur", onEnd); }
                if (run) try { c.releasePointerCapture(down.pointerId); } catch (err) {}
            }
            function onMove(e) {
                if (!run) {
                    if (Math.abs(e.clientX - startX) < _DRAG_THRESHOLD) return;
                    run = self.slide(c, startX);
                }
                run.place(e.clientX);
            }
            function onEnd(e) {
                letGo();
                if (run) run.end(e.type === "pointerup");
            }
            c.addEventListener("pointermove", onMove);
            c.addEventListener("pointerup", onEnd);
            c.addEventListener("pointercancel", onEnd);
            c.addEventListener("lostpointercapture", onEnd);
            if (win) { win.addEventListener("pointerup", onEnd); win.addEventListener("pointercancel", onEnd); win.addEventListener("blur", onEnd); }
        };
        this._armed.set(c, press);
        c.addEventListener("pointerdown", press);
    }

    /** A slide along the rail, by call: the chip lifted where it lies, under the hand at x, held grabX into it; the others stepping aside. */
    slide(c, x, grabX) {
        var self = this, strip = this._strip;
        var seated = strip._seated(), slots = seated.map(TabHand._rect), from = seated.indexOf(c);
        if (from < 0) throw new Error("[TabHand] the chip is not on this strip's rail");
        var origin = slots[from], lo = strip._pinned.size, dest = from, left = origin.left, done = false;
        var grab = grabX == null ? x - origin.left : grabX;
        this._held = c;
        strip._grip(c);
        function place(x) {
            left = TabDrag.clamp(x - grab, slots, lo);
            c.style.setProperty("--mtp-drag-x", (left - origin.left) + "px");
            var d = TabDrag.dest(left, slots, lo);
            if (d !== dest) { dest = d; strip._stepAside(from, dest, TabDrag.pitch(slots)); }
        }
        place(x);
        return {
            place: place,
            end: function (commit, leaving) {
                if (done) return dest;
                done = true;
                var landed = commit && dest !== from, slotLeft = slots[landed ? dest : from].left;
                self._held = null;
                c.style.removeProperty("--mtp-drag-x");
                strip._stepAside(from, from, 0);
                strip._release(c);
                if (landed && strip._onDrop) strip._onDrop(c, dest);
                if (!leaving) TabHand._settle(c, left - slotLeft);
                return landed ? dest : from;
            }
        };
    }

    /** From where the hand left it onto its slot, eased as the design eases the chip: the last leg, drawn once the row is arranged. */
    static _settle(c, dx) {
        if (!(Math.abs(dx) > 0.5) || typeof c.animate !== "function") return;
        var ms = TabHand._easeMs(c);
        if (!(ms > 0)) return;
        try { c.animate([{ translate: dx + "px 0" }, { translate: "0 0" }], { duration: ms, easing: "ease" }); } catch (err) {}
    }
    /** The design's ease for the chip, in milliseconds: the first duration of its transition. */
    static _easeMs(c) {
        if (typeof getComputedStyle !== "function") return 0;
        var d = String(getComputedStyle(c).transitionDuration || "").split(",")[0].trim();
        if (!d) return 0;
        return d.endsWith("ms") ? parseFloat(d) : parseFloat(d) * 1000;
    }
    static _rect(el) {
        var r = el.getBoundingClientRect();
        return { left: r.left, top: r.top, width: r.width, height: r.height };
    }
}
