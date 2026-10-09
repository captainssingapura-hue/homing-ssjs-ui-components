// =============================================================================
// Button — what the button leaves share, as declarations: the element they
// take, the axis they are on, the degree and the words they have, and the
// behaviour that is theirs (ButtonBehavior). A simple component
// (SimpleComponent); never made on its own - a button is one of its leaves,
// and wears its leaf's look in its role (Look):
//
//   var b = new DangerButton(branch.createElement("delete", DangerButton.TAG),
//                            { label: "Delete", extent: 0.6, onClick: fn }, ROLE.STANDALONE);
//   b.enabled = false    off: inert, and it says so - the element disabled, which
//                        the look's disabled treatments are shown by
//   b.size = -1          −1 the smallest … 1 the biggest; 0 regular, the rest
//   b.label = "Delete"   the words on it
//   b.extent = 0.6       a coloured leaf's: how much of its word it wears, −1 the
//                        meaning turned the other way; the plain button has none
//   onClick              in the props: the behaviour's, called on a press while enabled
//
// What a button looks like is its theme's answer for its casting, never a word
// it names: the leaf declares what it is, and Look wears the classes for it.
// =============================================================================

class Button extends SimpleComponent {
    static TAG = "button";
    static AXES = Object.freeze({
        enabled: Object.freeze({
            values: Object.freeze([true, false]),
            rest: true,
            reflect: function (el, on) { el.disabled = !on; }
        })
    });
    static DEGREES = Object.freeze(["size"]);
    static WORDS = Object.freeze(["label"]);
    static BEHAVIOR = ButtonBehavior;

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    get label() { return this.el.textContent; }
    set label(words) { this._look.text(this.el, words == null ? "" : String(words)); }
}
