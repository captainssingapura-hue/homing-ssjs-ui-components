// =============================================================================
// FloatingPane — one pane raised above the desk: a head that names it and
// moves it, a body that holds what it shows, a grip that sizes it. A branch
// component: the desk makes a sub-branch for it and hands it in; dispose()
// dissolves it. In the house's look - a card's frame, a shadow over what it
// floats on, its head a title bar - and a larger, movable card in the hand:
// its place and its measure are its user's, carried in --fp-x, --fp-y, --fp-w,
// --fp-h on the frame, never the design's. A dialog's window is one.
//
//   new FloatingPane(branch, { id, title, icon?, head?, x, y, w, h, z, closable?, onEvent?, minW?, minH?,
//                              onDragMove?(pane, clientX, clientY), onDragEnd?(pane, clientX, clientY, ok) })
//     branch   the pane's own, handed unactivated
//     x, y     its place within the desk, in px; w, h its measure; z its place on the stack
//     head     false: no head at all, the body the whole frame — for a holder that gives
//              the frame a handle of its own, as a float whose one bar is its host's strip.
//              The title is then only the frame's name for a reader, and there is no icon
//
//   pane.root                the frame, appended by the desk; never natively focused — a
//                            container, focused logically through what it holds (RFC 0066 E3,
//                            keyboard §17.1), so it has no tabindex
//   pane.body                the bounded region a widget's root goes in
//   pane.head                the bar; the desk listens on it and on the frame; null when there is none
//   pane.handle(el, accept?) el moves the frame as the head does: a press that accept(ev)
//                            says yes to, and the drag after it. The frame's hover and
//                            hand are the same; the head's own light is the head's
//   pane.id
//   pane.title(text?)        read, or set
//   pane.icon(el?)           read, or set: the holder's element before the title, or null for none
//   pane.moveTo(x, y)        clamped to the desk; reports Moved when it changed
//   pane.place(x, y)         clamped to the desk, and NOT reported: for a holder carrying the pane by a
//                            gesture of its own, frame by frame
//   pane.movedFrom(x, y)     Moved, said once, when the pane is elsewhere than (x, y): that gesture over
//   pane.resizeTo(w, h)      clamped to the least and the desk; reports Resized when it changed
//   pane.bounds()            { x, y, w, h }
//   pane.raise(z)            its place on the stack; the desk's to call
//   pane.setActive(on)       the ring drawn now, on the active one
//   pane.size(s)             the size axis, on the head and its parts
//   pane.grab(pointerId, clientX, clientY)   take over a drag already under way — a chip
//                            pulled off a strip — as if the head had been pressed there
//   pane.dispose()           dissolves the branch
//
// The hand: over the head, the whole frame is lit from there (data-highlighted)
// and lifts as the design has it; a press on the head and a drag moves it,
// live, the frame and its head in the hand (data-dragging), and reports Moved
// once when it lets go. The active pane is the current one (aria-current). A press on the grip and a
// drag sizes it, live, and reports Resized once. A press on the cross reports
// nothing: it asks the desk, through `onClose`, to close it. While a drag is
// on, onDragMove is told every position and onDragEnd the last, with whether
// the hand let go (true) or the drag was cancelled — for a holder that offers
// the pane to a dock under the pointer; neither is an event.
// Every report is one FloatEvents object on one sink, onEvent(ev).
// =============================================================================

const _floatingOwner = Object.freeze({ toString: () => "floatingPane" });

/** The head's cross pressed: the press kept to the cross, and the pane's holder asked to close it. */
class FloatingPaneClose {
    constructor(pane) { this._pane = pane; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle(event) {
        if (event.raw) event.raw.stopPropagation();
        if (this._pane._onClose) this._pane._onClose(this._pane);
    }
}
var _MIN_W = 160, _MIN_H = 96;
var _KEEP = 48;          // how much of the head must stay within the desk, inline

/** A flag of the pane's or its head's: reflected by its attribute, and its state's class (D12). */
function _fpFlag(name, value, state) { return Object.freeze({ values: Object.freeze([false, true]), rest: false, state: state,
    reflect: function (el, on) { if (on) el.setAttribute(name, value); else el.removeAttribute(name); } }); }
/** A floating pane's head: the house's title bar, which nothing realizes on its own - the pane draws it here; in the hand while the pane is moved by it. */
const _TITLE_BAR = Object.freeze({ LEAF: "title-bar", AXES: Object.freeze({ dragging: _fpFlag("data-dragging", "", STATE.DRAGGING) }) });

class FloatingPane {
    static LEAF = "floating-pane";
    static AXES = Object.freeze({ current: _fpFlag("aria-current", "true", STATE.CURRENT), highlighted: _fpFlag("data-highlighted", "", STATE.HIGHLIGHTED),
                                  dragging: _fpFlag("data-dragging", "", STATE.DRAGGING) });

    constructor(branch, opts) {
        if (!branch) throw new Error("[FloatingPane] a branch of its own is required");
        if (!opts || !opts.id) throw new Error("[FloatingPane] opts.id is required");
        var self = this;
        branch.activate(_floatingOwner);
        this.branch = branch;
        this.id = String(opts.id);
        this._sink = typeof opts.onEvent === "function" ? opts.onEvent : null;
        this._onClose = typeof opts.onClose === "function" ? opts.onClose : null;
        this._onDragMove = typeof opts.onDragMove === "function" ? opts.onDragMove : null;
        this._onDragEnd = typeof opts.onDragEnd === "function" ? opts.onDragEnd : null;
        this._minW = opts.minW == null ? _MIN_W : Math.max(24, opts.minW | 0);
        this._minH = opts.minH == null ? _MIN_H : Math.max(24, opts.minH | 0);
        this._x = 0; this._y = 0; this._w = this._minW; this._h = this._minH;
        this._size = 0;
        this._over = false; this._held = false; this._active = false;
        this._label = "";
        this._handle = null;   // an element of the holder's that moves the frame, when there is no head

        var frame = branch.createElement("frame", "section");
        css.addClass(frame, fp_frame);
        (this._look = new Look(FloatingPane, ROLE.STANDALONE)).wear(frame);   // a pane that floats - a dialog's window is one - in the house's look
        frame.setAttribute("role", "region");

        this._icon = null; this._title = null; this._close = null; this._closer = null;
        this._iconEl = null;
        var head = opts.head === false ? null : this._mintHead(branch, opts);
        if (head) frame.appendChild(head);

        var body = branch.createElement("body", "div");
        css.addClass(body, fp_body);
        frame.appendChild(body);

        var grip = branch.createElement("grip", "div");
        css.addClass(grip, fp_grip);
        grip.setAttribute("aria-hidden", "true");
        frame.appendChild(grip);

        this.root = frame;
        this.head = head;
        this.body = body;
        this._parts = head ? [head] : [];
        this.title(opts.title == null ? "" : opts.title);
        this.icon(opts.icon || null);
        this.raise(opts.z == null ? 1 : opts.z);
        this._set(opts.x == null ? 0 : opts.x, opts.y == null ? 0 : opts.y, opts.w == null ? 320 : opts.w, opts.h == null ? 220 : opts.h, false);
        if (head) this._armMove(head, null);
        this._armResize(grip);
    }

    /** The head: the icon's slot, always there and shown only with an icon; the title; the cross, when it can be closed. */
    _mintHead(branch, opts) {
        var self = this, head = branch.createElement("head", "header");
        css.addClass(head, fp_head);
        (this._headLook = new Look(_TITLE_BAR, ROLE.STANDALONE)).wear(head);   // its head, the house's title bar
        this._icon = branch.createElement("icon", "span");
        css.addClass(this._icon, fp_icon);
        this._icon.setAttribute("aria-hidden", "true");
        head.appendChild(this._icon);
        // its title is the house's label, cast as its title: placed by the pane, looking as a label does
        this._title = new Label(LabelFactory.create(branch, "title"), {}, ROLE.TITLE);
        css.addClass(this._title.el, fp_title);
        head.appendChild(this._title.el);
        if (opts.closable !== false) {
            // the house's close button, cast as what closes its owner: its cross and its frame are the style's
            var x = new CloseButton(CloseButtonFactory.create(branch, "close"), { face: branch.elementMaker("close-face"), label: "Close" }, ROLE.CLOSE);
            x.setOnClick(new FloatingPaneClose(self));
            head.appendChild(x.el);
            this._close = x.el;
            this._closer = x;
        }
        return head;
    }

    title(text) {
        if (text !== undefined) {
            this._label = String(text);
            if (this._title) this._title.text = this._label;
            this.root.setAttribute("aria-label", this._label);
        }
        return this._label;
    }

    /** An element of the holder's that moves the frame as a head would: pressed where accept says, and dragged. */
    handle(el, accept) {
        this._handle = el;
        this._armMove(el, typeof accept === "function" ? accept : null);
        return this;
    }

    /** The icon before the title: set with an element, or null for none; read with no argument. */
    icon(el) {
        if (el !== undefined) {
            this._iconEl = el || null;
            if (!this._icon) return this._iconEl;   // no head, no slot: kept, and shown nowhere
            while (this._icon.firstChild) this._icon.removeChild(this._icon.firstChild);
            if (this._iconEl) this._icon.appendChild(this._iconEl);
            css.toggleClass(this._icon, fp_icon_on, !!this._iconEl);
        }
        return this._iconEl;
    }

    bounds() { return { x: this._x, y: this._y, w: this._w, h: this._h }; }

    moveTo(x, y) {
        var before = this._x + "," + this._y;
        this._set(x, y, this._w, this._h, true);
        if (this._x + "," + this._y !== before) this._fire(FloatEvents.Moved(this.id, this._x, this._y));
        return this;
    }

    place(x, y) { this._set(x, y, this._w, this._h, true); return this; }

    movedFrom(x, y) {
        if (this._x !== Math.round(Number(x) || 0) || this._y !== Math.round(Number(y) || 0)) this._fire(FloatEvents.Moved(this.id, this._x, this._y));
        return this;
    }

    resizeTo(w, h) {
        var before = this._w + "," + this._h;
        this._set(this._x, this._y, w, h, true);
        if (this._w + "," + this._h !== before) this._fire(FloatEvents.Resized(this.id, this._w, this._h));
        return this;
    }

    raise(z) {
        this._z = z | 0;
        this.root.style.setProperty("--fp-z", String(this._z));
        return this;
    }

    setActive(on) { this._active = !!on; this._lift(); return this; }

    size(s) {
        var n = Math.max(-1, Math.min(1, Number(s)));
        this._size = Number.isFinite(n) ? n : 0;
        var v = this._size === 0 ? null : this._size;
        this._parts.forEach(function (el) { css.size(el, v); });
        if (this._title) this._title.size = this._size;     // its title and its cross, by their own size
        if (this._closer) this._closer.size = this._size;
        return this;
    }

    dispose() { try { this.branch.dissolve(); } catch (e) {} }

    // ── the frame's place and measure, clamped to the desk it is on ─────────
    _deskSize() {
        var p = this.root.parentNode;
        var w = p && typeof p.clientWidth === "number" ? p.clientWidth : 0;
        var h = p && typeof p.clientHeight === "number" ? p.clientHeight : 0;
        return { w: w > 0 ? w : Infinity, h: h > 0 ? h : Infinity };
    }

    _set(x, y, w, h, clamp) {
        var d = clamp ? this._deskSize() : { w: Infinity, h: Infinity };
        w = Math.max(this._minW, Math.round(Number(w) || 0));
        h = Math.max(this._minH, Math.round(Number(h) || 0));
        if (d.w !== Infinity) w = Math.min(w, d.w);
        if (d.h !== Infinity) h = Math.min(h, d.h);
        x = Math.round(Number(x) || 0);
        y = Math.round(Number(y) || 0);
        if (d.w !== Infinity) x = Math.max(_KEEP - w, Math.min(x, d.w - _KEEP));
        if (d.h !== Infinity) y = Math.max(0, Math.min(y, d.h - _KEEP));
        y = Math.max(0, y);
        this._x = x; this._y = y; this._w = w; this._h = h;
        var s = this.root.style;
        s.setProperty("--fp-x", x + "px");
        s.setProperty("--fp-y", y + "px");
        s.setProperty("--fp-w", w + "px");
        s.setProperty("--fp-h", h + "px");
    }

    _fire(ev) { if (this._sink) this._sink(ev); }

    /** Take over a drag already under way, as if the head had been pressed at this point: the pane follows this hand until it lets go. */
    grab(pointerId, clientX, clientY) {
        this._over = true;
        this._beginMove({ pointerId: pointerId, clientX: clientX, clientY: clientY });
        return this;
    }

    // ── the hand on the head: move ──────────────────────────────────────────
    _lift() {   // in the hand: dragging; under the hand, through its head: lit from there; the current one ringed
        this._look.show(this.root, { current: this._active, highlighted: this._over && !this._held, dragging: this._held });
    }
    _armMove(el, accept) {
        var self = this;
        el.addEventListener("pointerenter", function () { self._over = true; self._lift(); });
        el.addEventListener("pointerleave", function () { self._over = false; self._lift(); });
        el.addEventListener("pointerdown", function (down) {
            if (down.button !== 0 || self._held) return;
            if (self._close && (down.target === self._close || (self._close.contains && self._close.contains(down.target)))) return;
            if (accept && !accept(down)) return;
            self._beginMove(down, el);
            if (down.preventDefault) down.preventDefault();
        });
    }
    _beginMove(down, by) {
        var self = this, head = by || this.head || this._handle, lit = head === this.head;   // the head lights as it is held; a holder's handle is its own
        var x0 = this._x, y0 = this._y, cx = down.clientX, cy = down.clientY, moved = false;
        this._held = true;
        this._lift();
        if (lit) this._headLook.show(head, { dragging: true });
        try { head.setPointerCapture(down.pointerId); } catch (err) {}
        function onMove(e) {
            var nx = x0 + (e.clientX - cx), ny = y0 + (e.clientY - cy);
            self._set(nx, ny, self._w, self._h, true);
            moved = true;
            if (self._onDragMove) self._onDragMove(self, e.clientX, e.clientY);
        }
        function onEnd(e) {
            head.removeEventListener("pointermove", onMove);
            head.removeEventListener("pointerup", onEnd);
            head.removeEventListener("pointercancel", onEnd);
            self._held = false;
            self._lift();
            if (lit) self._headLook.show(head, { dragging: false });
            try { head.releasePointerCapture(down.pointerId); } catch (err) {}
            var ok = e.type === "pointerup";
            if (!ok) self._set(x0, y0, self._w, self._h, false);
            if (self._onDragEnd) self._onDragEnd(self, e.clientX, e.clientY, ok);   // a holder may take the pane off the desk here: docked, it has not "moved"
            if (ok && moved && self.root.parentNode && (self._x !== x0 || self._y !== y0)) self._fire(FloatEvents.Moved(self.id, self._x, self._y));
        }
        head.addEventListener("pointermove", onMove);
        head.addEventListener("pointerup", onEnd);
        head.addEventListener("pointercancel", onEnd);
    }

    // ── the hand on the grip: resize ────────────────────────────────────────
    _armResize(grip) {
        var self = this;
        grip.addEventListener("pointerdown", function (down) {
            if (down.button !== 0) return;
            var w0 = self._w, h0 = self._h, cx = down.clientX, cy = down.clientY, sized = false;
            try { grip.setPointerCapture(down.pointerId); } catch (err) {}
            function onMove(e) {
                self._set(self._x, self._y, w0 + (e.clientX - cx), h0 + (e.clientY - cy), true);
                sized = true;
            }
            function onEnd(e) {
                grip.removeEventListener("pointermove", onMove);
                grip.removeEventListener("pointerup", onEnd);
                grip.removeEventListener("pointercancel", onEnd);
                try { grip.releasePointerCapture(down.pointerId); } catch (err) {}
                if (e.type !== "pointerup") { self._set(self._x, self._y, w0, h0, false); return; }
                if (sized && (self._w !== w0 || self._h !== h0)) self._fire(FloatEvents.Resized(self.id, self._w, self._h));
            }
            grip.addEventListener("pointermove", onMove);
            grip.addEventListener("pointerup", onEnd);
            grip.addEventListener("pointercancel", onEnd);
            if (down.preventDefault) down.preventDefault();
        });
    }
}
