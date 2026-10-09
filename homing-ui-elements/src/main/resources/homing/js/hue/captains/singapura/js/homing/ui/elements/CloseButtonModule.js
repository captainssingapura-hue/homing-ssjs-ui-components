// =============================================================================
// CloseButton — the button that closes what holds it: a Button (a simple
// component) whose face is a mark, never words. The mark is its look's - each
// style draws its own cross on the button's ::before - so nothing here is a
// picture; its words are its name, said to whoever cannot see the mark.
//
//   var c = new CloseButton(branch.createElement("close", CloseButton.TAG), { label: "Close the dialog", onClick: fn }, ROLE.STANDALONE);
//   c.label = "Close"    its name: aria-label; "Close" unless given
//   c.enabled  c.size
//   onClick              in the props: called on a press while enabled
// =============================================================================

class CloseButton extends Button {
    static LEAF = "close-button";

    get label() { return this.el.getAttribute("aria-label") || ""; }
    set label(words) { this.el.setAttribute("aria-label", words == null || words === "" ? "Close" : String(words)); }
}
