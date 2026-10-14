// =============================================================================
// ItemBehavior — what an item does: a press, while it is usable, is its click,
// dressed from the DOM's and fired to its handler; and the pointer coming onto
// an item that can be pointed at points at it - it becomes the one its owner's
// cursor is on, fired as the change of its Pointed state with the DOM event
// that made it, so its owner can tell the pointer from its own setter. Shared
// by the menu's item, the list's row and the choice's option. It reads the
// state through the item, keeps none of its own, and never touches the look.
//
//   new ItemBehavior(el, item)
// =============================================================================

class ItemBehavior {
    constructor(el, item) {
        var axes = item.constructor.AXES || {};
        el.addEventListener("click", function (ev) { if (!("enabled" in axes) || item._get("enabled")) item._fire(EVENT.CLICK, ev); });
        if ("pointed" in axes) el.addEventListener("pointerenter", function (ev) { item._change("pointed", true, ev); });
    }
}
