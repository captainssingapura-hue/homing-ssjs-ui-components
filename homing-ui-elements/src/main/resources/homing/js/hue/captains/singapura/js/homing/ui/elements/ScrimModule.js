// =============================================================================
// Scrim — the veil over the page behind a dialog. A simple component
// (SimpleComponent) on a <div>, in the scrim's look - how the page behind is
// dimmed, blurred or left as it is, is its style's. It shows no words; it is
// held by the hand - its press, and its release:
//
//   var s = new Scrim(branch.elementMaker("scrim"), new FixedScrim(), me.place(FixedScrim));
//   s.onChange(StateHeld, listener)   held down by the hand, and let go - its press
//                           and its release, StateHeld's named changes
//   s.held                  "up" at rest, "down" while the hand holds it
//
// Where it is - over the whole page, beneath the dialog - is its owner's, by its
// own placement class.
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class Scrim extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        held: Object.freeze({ rest: "up", state: StateHeld })   // its behaviour's: down by the hand, up as it lets go; no class
    });
    static BEHAVIOR = ScrimBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedScrim, place ?? manageScrim); }

    /** Whether the hand holds it: "up", or "down". */
    get held() { return this._get("held"); }

    /** The hand coming down on it, as its behaviour hears it: held down. */
    _pressed() { this._change("held", "down"); }

    /** The hand let go, wherever it is, as its behaviour hears it: up again. */
    _released() { this._change("held", "up"); }
}

