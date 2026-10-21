// =============================================================================
// The behaviours of the browser's own inputs. Each rides on its native element:
// the element keeps the value and the tick, and fires the events; the
// behaviour dresses what the element does as its component's, the DOM event
// carried - each told to its component, which answers with its own events (RFC
// 0071: a behaviour names no node). They keep no state of their own and never
// touch the style.
//
//   new EditBehavior(el, input)    a text box's, a range's: input - its INPUT;
//                                  change - its COMMIT
//   new PickBehavior(el, select)   a select's: change - its COMMIT
//   new CheckBehavior(el, box)     a checkbox's: change - its checked, as the
//                                  element has it, then its COMMIT
//   new TurnBehavior(el, sw)       a switch's: a press - turned, then its COMMIT;
//                                  its groove turned with it
// =============================================================================

class EditBehavior {
    constructor(el, input) {
        el.addEventListener("input", function (ev) { input._input(ev); });
        el.addEventListener("change", function (ev) { input._commit(ev); });
    }
}

class PickBehavior {
    constructor(el, select) {
        el.addEventListener("change", function () { select._picked(); });
    }
}

class TurnBehavior {
    constructor(el, sw) {
        this._switch = sw;
        el.addEventListener("click", function () { sw._turned(); });
    }

    /** Its groove turned with it: on as the switch is. */
    changed(axis, value) { if (axis === "checked" && this._switch._groove) this._switch._groove.checked = value; }
}

class CheckBehavior {
    constructor(el, box) {
        el.addEventListener("change", function () { box._ticked(!!el.checked); });
    }
}
