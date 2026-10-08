// =============================================================================
// Button — the base every button leaf extends: an element dressed as a
// button, labelled, at an extent and a size, on or off. A leaf binds its look
// alone, and is the one a caller makes:
//
//   class DangerButton extends Button {
//       constructor(el, props) { super(el, props, { cls: el_button_danger, scales: true }); }
//   }
//   var b = new DangerButton(branch.createElement("delete", DangerButton.TAG), { label: "Delete", extent: 0.6, onClick: fn });
//
//   props:
//     label                         the text
//     extent                        −1 … 1, default 1: the number that scales the
//                                   leaf's word — 1 the word as the design binds it,
//                                   0 the design's neutral, −1 the meaning turned the
//                                   other way (safety for danger, calm for warning,
//                                   failure for success); a leaf whose look does not
//                                   scale (plain) takes none
//     size                          −1 the smallest, 0 regular (the default), 1 the
//                                   biggest — every length the design gives the
//                                   button grows by its own ratio to the power of it
//     onClick
//
//   the instance, adjusted live:
//     .el                           the element
//     .extent(t)  .size(s)          as above
//     .label(text?)                 read, or set
//     .setOn(on)                    on lifts and presses as the design has it; off is inert and says so
//
// A look is a semantic surface, complete: its surface, the ink on it and its
// edge move together with the extent, each along the anchors the design
// gives that target. The element's type is "button" unless the caller says
// otherwise afterwards, so a button inside a form does not submit it by
// accident. Never made on its own: a button is one of its leaves. `css` is
// injected with the styles import.
// =============================================================================

class Button {
    static TAG = "button";

    /** The leaf's: the element minted with the tag, the caller's properties, and the leaf's look - { cls, scales }. */
    constructor(el, props, look) {
        if (new.target === Button || !look) throw new Error("[Button] a button is one of its leaves: PlainButton, PrimaryButton, SecondaryButton, DangerButton, WarningButton, SuccessButton");
        if (!el || String(el.tagName).toLowerCase() !== Button.TAG) throw new Error("[" + new.target.name + "] wants the element minted with its tag, <" + Button.TAG + ">");
        var p = props || {};
        this.el = el;
        this._scales = !!look.scales;
        this._extent = 1;
        this._size = 0;
        el.type = "button";
        css.addClass(el, el_button);
        css.addClass(el, el_button_on);
        css.addClass(el, look.cls);
        this.label(p.label == null ? "" : p.label);
        this.extent(p.extent == null ? 1 : p.extent);
        this.size(p.size == null ? 0 : p.size);
        if (typeof p.onClick === "function") el.addEventListener("click", p.onClick);
    }

    extent(t) {
        var n = Math.max(-1, Math.min(1, Number(t)));
        this._extent = Number.isFinite(n) ? n : 1;
        css.extent(this.el, this._scales ? this._extent : null);
        return this;
    }

    size(s) {
        var n = Math.max(-1, Math.min(1, Number(s)));
        this._size = Number.isFinite(n) ? n : 0;
        css.size(this.el, this._size === 0 ? null : this._size);
        return this;
    }

    label(text) {
        if (text !== undefined) this.el.textContent = String(text);
        return this.el.textContent;
    }

    setOn(on) {
        this.el.disabled = !on;
        css.toggleClass(this.el, el_button_on, on);
        css.toggleClass(this.el, el_button_off, !on);
        return this;
    }
}
