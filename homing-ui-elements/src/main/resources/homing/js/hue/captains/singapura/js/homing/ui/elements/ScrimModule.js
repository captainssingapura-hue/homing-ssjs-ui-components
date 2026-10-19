// =============================================================================
// Scrim — the veil over the page behind a dialog. A simple component
// (SimpleComponent) on a <div>, in the scrim's look - how the page behind is
// dimmed, blurred or left as it is, is its style's. It shows no words and has
// no state; it offers a press:
//
//   var s = new Scrim(branch.elementMaker("scrim"), new FixedScrim(), me.slot(SLOT_…));
//   s.setOnPress(handler)   the hand came down on it: handler.handle(ComponentEvent)
//
// Where it is - over the whole page, beneath the dialog - is its owner's, by its
// own placement class.
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class Scrim extends SimpleComponent {
    static TAG = "div";
    static EVENTS = Object.freeze([EventPress]);
    static BEHAVIOR = ScrimBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedScrim, place ?? manageScrim); }

    /** The hand came down on it. */
    setOnPress(handler) { this._setOn(EventPress, handler); }

    /** The hand coming down, as its behaviour hears it: its press. */
    _pressed(ev) { this._fire(EventPress, ev); }
}

