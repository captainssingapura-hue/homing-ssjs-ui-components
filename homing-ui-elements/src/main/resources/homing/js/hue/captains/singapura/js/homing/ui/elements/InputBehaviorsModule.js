// =============================================================================
// The behaviours of the browser's own inputs. Each rides on its native element:
// the element keeps the value and the tick, and fires the events; the
// behaviour dresses what the element does as its component's, the DOM event
// carried. They keep no state of their own and never touch the look.
//
//   new EditBehavior(el, input)    a text box's, a range's: input - its INPUT;
//                                  change - its COMMIT
//   new PickBehavior(el, select)   a select's: change - its COMMIT
//   new CheckBehavior(el, box)     a checkbox's: change - its checked, as the
//                                  element has it, then its COMMIT
// =============================================================================

class EditBehavior {
    constructor(el, input) {
        el.addEventListener("input", function (ev) { input._fire(EVENT.INPUT, ev); });
        el.addEventListener("change", function (ev) { input._fire(EVENT.COMMIT, ev); });
    }
}

class PickBehavior {
    constructor(el, select) {
        el.addEventListener("change", function (ev) { select._fire(EVENT.COMMIT, ev); });
    }
}

class CheckBehavior {
    constructor(el, box) {
        el.addEventListener("change", function (ev) {
            box._change("checked", !!el.checked, ev);
            box._fire(EVENT.COMMIT, ev);
        });
    }
}
