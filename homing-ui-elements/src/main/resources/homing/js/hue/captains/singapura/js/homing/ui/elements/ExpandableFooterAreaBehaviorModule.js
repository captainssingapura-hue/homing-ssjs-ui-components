// =============================================================================
// ExpandableFooterAreaBehavior — what an expandable footer area does with the
// pointer: onto it - its footer or its open area - and off it, and a press on its
// status footer, told to it. What it makes of them is its own. It keeps no state
// of its own and never touches the look.
//
//   new ExpandableFooterAreaBehavior(el, area)
// =============================================================================

class ExpandableFooterAreaBehavior {
    constructor(el, area) {
        el.addEventListener("pointerenter", function () { area._pointerOn(); });
        el.addEventListener("pointerleave", function () { area._pointerOff(); });
        el.addEventListener("pointerdown", function (ev) { if (area.status && area.status.el.contains(ev.target)) area._pressed(); });
    }
}
