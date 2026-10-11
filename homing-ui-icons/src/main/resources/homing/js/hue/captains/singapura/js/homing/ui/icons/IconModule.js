// =============================================================================
// Icon — a mark that shows a word of the house's lexicon, as an ELEMENT
// component: the caller mints a span on its branch and hands it in; the icon
// dresses it with the mark's box and the class that shows the word named, and
// the style worn writes the word - a glyph of its own, in the ink the mark
// stands on - on the mark's ::before. Nothing here is a picture, and a name
// the lexicon lacks is refused: ICONS is the table generated from it, with
// the words of every module on the classpath that adds its own.
//
//   var el = branch.createElement(name, Icon.TAG);
//   var icon = new Icon(el, { name: "check" });
//     icon.el                 the element
//     icon.name()             the word it shows, or null when blank
//     icon.set(name)          another word; the same mark, the same width
//     icon.clear()            blank, at its width: labels beside it stay aligned
//     icon.dispose()          the classes off; the element is the caller's
//   Icon.NAMES                the words, in the lexicon's order
//   Icon.has(name)            whether a word is known
//
// The mark is aria-hidden: what it means is said by the text beside it, or
// by the control's own label — an icon alone is not a label. Its box is a
// provided class, put on through `css`, injected with the styles import; the
// word's class is the lexicon sheet's - derived, the theme's, written again on
// a switch - so the icon puts it on itself, as a look puts on its own.
// =============================================================================
class Icon {
    static TAG = "span";
    static NAMES = Object.freeze(Object.keys(ICONS));
    static has(name) { return Object.prototype.hasOwnProperty.call(ICONS, name); }

    constructor(el, props) {
        if (!el) throw new Error("[Icon] the element is required: mint it with Icon.TAG on your branch");
        this.el = el;
        this._name = null;
        css.addClass(el, ic_base);
        el.setAttribute("aria-hidden", "true");
        if (props && props.name != null) this.set(props.name);
    }

    name() { return this._name; }

    set(name) {
        if (!Icon.has(name)) throw new Error("[Icon] no icon word '" + name + "'; one of " + Icon.NAMES.join(", "));
        if (this._name === name) return this;
        if (this._name) this.el.classList.remove(ICONS[this._name]);
        this._name = name;
        this.el.classList.add(ICONS[name]);
        return this;
    }

    clear() {
        if (this._name) this.el.classList.remove(ICONS[this._name]);
        this._name = null;
        return this;
    }

    dispose() {
        this.clear();
        css.removeClass(this.el, ic_base);
        this.el.removeAttribute("aria-hidden");
    }
}
