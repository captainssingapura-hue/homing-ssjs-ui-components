// =============================================================================
// FloatLayerBehavior — what a float layer does: the pointer down on one of its
// floaters, or the focus arriving in one, told to it - heard first, in the
// capture, so the floater it is in is put in front before anything in it acts.
// It keeps no state of its own and never touches the look.
//
//   new FloatLayerBehavior(el, layer)
// =============================================================================

class FloatLayerBehavior {
    constructor(el, layer) {
        el.addEventListener("pointerdown", function (ev) { layer._touched(ev.target); }, true);
        el.addEventListener("mousedown", function (ev) { layer._touched(ev.target); }, true);
        el.addEventListener("focusin", function (ev) { layer._touched(ev.target); });
    }
}
