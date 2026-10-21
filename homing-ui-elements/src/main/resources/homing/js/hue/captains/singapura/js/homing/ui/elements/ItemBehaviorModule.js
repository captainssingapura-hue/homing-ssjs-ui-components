// =============================================================================
// ItemBehavior — what an item does: a press, while it is usable, is told to it,
// whose click it is (RFC 0071: a behaviour names no node); and the pointer coming onto
// an item that can be pointed at points at it - it becomes the one its owner's
// cursor is on, fired as the change of its Pointed state with the DOM event
// that made it, so its owner can tell the pointer from its own setter. Shared
// by the menu's item, the list's row and the choice's option. It reads the
// state through the item, keeps none of its own, and never touches the style.
//
//   new ItemBehavior(el, item)
// =============================================================================

class ItemBehavior {
    constructor(el, item) {
        var axes = item.constructor.AXES || {};
        el.addEventListener("click", function (ev) { if (!("enabled" in axes) || item._get("enabled")) item._clicked(ev); });
        if ("pointed" in axes) el.addEventListener("pointerenter", function (ev) { item._change("pointed", true); });
    }
}
