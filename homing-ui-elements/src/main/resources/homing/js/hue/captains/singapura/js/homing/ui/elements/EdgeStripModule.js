// =============================================================================
// EdgeStrip — a strip of controls that lies OVER the foot of its host, shown
// when the hand comes to that edge. The host keeps all its room but a thin
// lip at its foot, the strip's own: the hand on the lip shows the strip over
// the bottom of the host, and the hand off both hides it again. Nothing the
// host holds is covered until it is asked for.
//
//   EdgeStripBuilder — the properties, set progressively, each returning the builder:
//     .label(text)          what the strip is called, for whoever cannot see it
//     .host(el)             appended there on build: a POSITIONED box, whose foot
//                           the lip takes and over whose foot the strip lies
//     .build(branch)        the EdgeStrip, on a sub-branch the caller made
//
//   EdgeStrip — the instance:
//     .root                 the strip: the caller appends its controls to it
//     .lip                  the band at the host's foot the hand comes to
//     .hold(on)             kept shown while on — something on it a person must
//                           know; off, it goes once the hand and the keys have
//     .isShown()
//     .dispose()            the strip and the lip, with their branch
//
// Shown while the hand is on the lip or on the strip, while the keys are in
// it — a control focused from the keyboard — and while it is held. A press
// focuses a button too, so a focus the pointer made keeps nothing shown. The
// hand may cross from the lip onto the strip, or step off it for a moment,
// without the strip going: it goes a little after the hand has left both.
//
// It takes no keys of its own and claims nothing: what is in it is the
// caller's, as a panel's slot is.
// =============================================================================

const _stripOwner = Object.freeze({ toString: () => "edgeStrip" });

/** How long the hand may be off both before the strip goes: long enough to cross from the lip onto it. */
var _EDGE_STRIP_GRACE = 250;

class EdgeStrip {
    /** The builder's; a caller makes a strip through EdgeStripBuilder. */
    constructor(branch, props) {
        if (!branch) throw new Error("[EdgeStrip] a branch of its own is required");
        var p = props || {}, self = this;
        branch.activate(_stripOwner);
        this.branch = branch;
        this._hand = false;
        this._keys = false;
        this._held = false;
        this._timer = null;

        var lip = branch.createElement("lip", "div");
        css.addClass(lip, el_strip_lip);
        var strip = branch.createElement("strip", "div");
        css.addClass(strip, el_strip);
        strip.setAttribute("role", "group");
        if (p.label != null) strip.setAttribute("aria-label", String(p.label));
        this.lip = lip;
        this.root = strip;

        // the hand: on the lip or the strip shows it; off both, it goes after the grace
        var on = function () { self._cancel(); self._hand = true; self._paint(); };
        var off = function () {
            self._cancel();
            self._timer = setTimeout(function () { self._timer = null; self._hand = false; self._paint(); }, _EDGE_STRIP_GRACE);
        };
        // the keys: a control in it focused from the keyboard keeps it shown, until the focus leaves it
        var into = function (ev) {
            var t = ev ? ev.target : null;
            self._keys = !!(t && typeof t.matches === "function" && t.matches(":focus-visible"));
            self._paint();
        };
        var away = function (ev) {
            var to = ev ? ev.relatedTarget : null;
            if (to && strip.contains(to)) return;
            self._keys = false;
            self._paint();
        };
        lip.addEventListener("pointerenter", on);
        lip.addEventListener("pointerleave", off);
        strip.addEventListener("pointerenter", on);
        strip.addEventListener("pointerleave", off);
        strip.addEventListener("focusin", into);
        strip.addEventListener("focusout", away);

        this._paint();
        if (p.host) { p.host.appendChild(lip); p.host.appendChild(strip); }
    }

    /** Kept shown while on: something on it a person must know. Off, it goes once the hand and the keys have. */
    hold(on) {
        this._held = !!on;
        this._paint();
        return this;
    }

    isShown() { return this._hand || this._keys || this._held; }

    _cancel() {
        if (this._timer) { clearTimeout(this._timer); this._timer = null; }
    }

    _paint() { css.toggleClass(this.root, el_strip_hidden, !this.isShown()); }

    dispose() {
        this._cancel();
        this.branch.dissolve();
    }
}

class EdgeStripBuilder {
    constructor() { this._props = {}; }
    label(text)    { this._props.label = text; return this; }
    host(el)       { this._props.host = el; return this; }
    build(branch)  { return new EdgeStrip(branch, this._props); }
}
