// =============================================================================
// Heading — words that say what a container holds, read first. A Text (a simple component) on a <div>, in the heading's look.
//
//   var x = new Heading(HeadingFactory.create(branch, "heading"), { text: "…", level: 3 }, ROLE.STANDALONE);
//   x.text  x.size
//   x.level = 3          which heading it is in the page's outline, 1 to 6, 2 unless given:
//                        a heading by its role and its level, whatever element it is on
// =============================================================================

class Heading extends Text {
    static LEAF = "heading";
    static TAG = "div";
    static AXES = Object.freeze({
        level: Object.freeze({
            values: Object.freeze([1, 2, 3, 4, 5, 6]),
            rest: 2,
            reflect: function (el, n) { el.setAttribute("role", "heading"); el.setAttribute("aria-level", String(n)); }
        })
    });

    get level() { return this._get("level"); }
    set level(n) { this._set("level", Number(n)); }
}

/** Its companion: makes the <div> it is realized on, on its owner's branch, under its owner's name. */
const HeadingFactory = new SimpleComponentFactory(Heading);
