// =============================================================================
// TabStripSlide — a chip slid along its bar, the bar's own: lifted where it lies,
// it goes where the hand goes along the row, the chips it passes stepping aside,
// eased; let go, it settles onto the place it is nearest, still lifted, and is put
// at rest only there. Made by the bar for each slide; nothing outside it holds
// one. Its own classes only place. `css` is injected with the styles import.
//
//   new TabStripSlide(strip, row, from, x)   slide.to(x)   slide.end()   slide.settle()   slide.rest()
//   TabStripSlide._ease(el)   TabStripSlide._eased(el)
// =============================================================================

/**
 * A chip slid along its bar, as a browser's tab bar slides one: lifted where it lies, it goes where the hand goes along the
 * row - the press kept as an offset within it - never before the first chip's place nor past the last's; the place it is
 * nearest is where it will land, and the chips between its own place and that one step aside, one place over, eased by
 * their look. Let go, they are back at once - the row is about to be arranged, and none must be seen sliding to where it
 * already is - and the chip settles onto its place from where the hand left it, eased as its look eases it, still lifted
 * and above the others; only there is it put at rest (owner, 22 Oct: "moved to position first then put to at-rest") -
 * at once, where there is nothing to ease, or when another chip is pressed or the tabs change meanwhile. Nothing is
 * placed by hand: the chip in the hand carries --mtp-drag-x, a chip stepping aside --mtp-shift-x, each read by the class
 * that places it. The bar's alone.
 */
class TabStripSlide {
    /**
     * @param strip the bar
     * @param row   its chips, as they stand
     * @param from  the place of the chip slid
     * @param x     where the hand went down on it
     */
    constructor(strip, row, from, x) {
        this._strip = strip;
        this._row = row;
        row.forEach(function (c) { TabStripSlide._eased(c.el); });   // the row where it stands: any gap still closing, closed
        this._slots = row.map(function (c) { var r = c.el.getBoundingClientRect(); return { left: r.left, top: r.top, width: r.width, height: r.height }; });
        this.from = from;
        this.dest = from;
        this._grab = x - this._slots[from].left;
        this._left = this._slots[from].left;
        this._chip = row[from];
        this._easing = null;   // its settling, while it eases onto its place
        this._rested = false;
        this._chip.dragging = true;
        css.addClass(this._chip.el, mtp_chip_dragging);   // lifted over the row along it: the bar's placing
        css.addClass(strip.el, mtp_strip_loose);   // nothing clipped meanwhile
    }

    /** The hand at x: the chip under it, within the row; the chips between its place and the one it is nearest, aside. */
    to(x) {
        var slots = this._slots;
        this._left = TabDrag.clamp(x - this._grab, slots, 0);
        this._chip.el.style.setProperty("--mtp-drag-x", (this._left - slots[this.from].left) + "px");
        var d = TabDrag.dest(this._left, slots, 0);
        if (d !== this.dest) { this.dest = d; this._aside(d, false); }
    }

    /** Let go: no longer under the hand, the others back at once - but still lifted, above them; the place it lands at. */
    end() {
        this._chip.el.style.removeProperty("--mtp-drag-x");
        this._aside(this.from, true);
        return this.dest;
    }

    /**
     * Once the row is arranged: from where the hand left it onto its place, eased as its look eases it - still lifted,
     * above the others - and put at rest once there; at rest at once where there is nothing to ease.
     */
    settle() {
        var el = this._chip.el, dx = this._left - this._slots[this.dest].left, self = this;
        var ms = TabStripSlide._ease(el);
        if (!(Math.abs(dx) > 0.5) || !(ms > 0) || typeof el.animate !== "function") { this.rest(); return; }
        this._easing = el.animate([{ translate: dx + "px 0" }, { translate: "0 0" }], { duration: ms, easing: "ease" });
        this._easing.onfinish = function () { self._easing = null; self.rest(); };
    }

    /** At rest, once: out of the hand, its lift and its place over the row given up, the bar no longer loose - its easing, if any is left, over at once. */
    rest() {
        if (this._rested) return;
        this._rested = true;
        if (this._easing !== null) { this._easing.onfinish = null; this._easing.cancel(); this._easing = null; }
        css.removeClass(this._chip.el, mtp_chip_dragging);
        css.removeClass(this._strip.el, mtp_strip_loose);
        this._chip.dragging = false;
    }

    /** The chips between its place and `to`, one place over; the rest where they stand - at once, the drag over. */
    _aside(to, over) {
        var pitch = TabDrag.pitch(this._slots);
        for (var j = 0; j < this._row.length; j++) {
            if (j === this.from) continue;
            var s = TabDrag.shift(j, this.from, to), el = this._row[j].el;
            if (s !== 0) { css.addClass(el, mtp_chip_shifted); el.style.setProperty("--mtp-shift-x", (s * pitch) + "px"); continue; }
            css.removeClass(el, mtp_chip_shifted);
            el.style.removeProperty("--mtp-shift-x");
            if (over && typeof el.getAnimations === "function") el.getAnimations().forEach(function (a) { a.cancel(); });   // whatever it eased towards, it is there now
        }
    }

    /** How long a chip's look eases it, in milliseconds: the first of its transitions. */
    static _ease(el) {
        var d = String(getComputedStyle(el).transitionDuration || "").split(",")[0].trim();
        return d.endsWith("ms") ? parseFloat(d) : parseFloat(d) * 1000;
    }

    /** A chip where it is going: whatever of its own it eases - a gap closing - over at once. */
    static _eased(el) {
        if (typeof el.getAnimations !== "function") return;
        el.getAnimations().forEach(function (a) { if (typeof CSSTransition === "undefined" || !(a instanceof CSSTransition)) a.finish(); });
    }
}
