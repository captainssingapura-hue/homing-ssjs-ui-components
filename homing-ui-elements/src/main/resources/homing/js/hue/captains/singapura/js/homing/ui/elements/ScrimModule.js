// =============================================================================
// Scrim — the veil over the page behind a dialog. A simple component
// (SimpleComponent) on a <div>, in the scrim's look - how the page behind is
// dimmed, blurred or left as it is, is its style's. It shows no words and has
// no state; it offers a press:
//
//   var s = new Scrim(ScrimFactory.create(branch, "scrim"), {}, ROLE.VEIL);
//   s.setOnPress(handler)   the hand came down on it: handler.handle(ComponentEvent)
//
// Where it is - over the whole page, beneath the dialog - is its owner's, by its
// own placement class.
// =============================================================================

class Scrim extends SimpleComponent {
    static LEAF = "scrim";
    static TAG = "div";
    static EVENTS = Object.freeze([EVENT.PRESS]);
    static BEHAVIOR = ScrimBehavior;

    /** The hand came down on it. */
    setOnPress(handler) { this._setOn(EVENT.PRESS, handler); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const ScrimFactory = new SimpleComponentFactory(Scrim);
