// =============================================================================
// CloseButton — the button that closes what holds it: a Button (a simple
// component) whose face is a mark, never words. The mark is its look's - each
// style draws its own cross on the button's ::before - so nothing here is a
// picture; its words are its name, said to whoever cannot see the mark, and
// shown by the browser to whoever points at it: its tooltip.
//
//   var c = new CloseButton(CloseButtonFactory.create(branch, "close"), { label: "Close the dialog" }, ROLE.STANDALONE);
//   c.label = "Close"    its name: aria-label, and its tooltip; "Close" unless given
//   c.enabled  c.size
//   c.setOnClick(handler)  a press, while it is enabled
// =============================================================================

class CloseButton extends Button {
    static LEAF = "close-button";

    get label() { return this.el.getAttribute("aria-label") || ""; }
    set label(words) {
        var name = words == null || words === "" ? "Close" : String(words);
        this.el.setAttribute("aria-label", name);
        this.el.setAttribute("title", name);   // the browser's own tooltip: no caption of the house's to draw
    }
}

/** Its companion: makes the <button> it is realized on, on its owner's branch, under its owner's name. */
const CloseButtonFactory = new SimpleComponentFactory(CloseButton);
