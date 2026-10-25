// =============================================================================
// MenuBehavior — what a level of a context menu does with the pointer: the
// browser's own menu never over it; the pointer over an off row - which takes no
// cursor - and the pointer gone from it, told to it. Its rows' own pointing is
// their behaviour's. It keeps no state of its own and never touches the style.
//
//   new MenuBehavior(el, menu)
// =============================================================================

class MenuBehavior {
    constructor(el, menu) {
        el.addEventListener("contextmenu", function (ev) { ev.preventDefault(); });
        el.addEventListener("pointerover", function (ev) {
            var row = ev.target && typeof ev.target.closest === "function" ? ev.target.closest("[data-id]") : null;
            if (row && el.contains(row) && row.getAttribute("aria-disabled") === "true") menu._pointerOff();
        });
        el.addEventListener("pointerleave", function () { menu._left(); });
    }
}
