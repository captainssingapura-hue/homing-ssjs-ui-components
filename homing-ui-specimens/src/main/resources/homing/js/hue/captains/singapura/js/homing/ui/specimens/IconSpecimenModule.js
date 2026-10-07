// =============================================================================
// IconSpecimen — the house's icon in action: one mark, its word said beside it,
// stepped through the whole vocabulary - the same mark, the same width - and
// cleared to a blank that keeps its width, so what stands beside it stays
// where it was. A word the vocabulary lacks is refused, and that is shown too.
//
//   new IconSpecimen(branch, { leaf, say })   leaf: "icon"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _iconSpecimen = Object.freeze({ toString: () => "iconSpecimen" });

class IconSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_iconSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);

        var shown = branch.createElement("shown", "div");
        css.addClass(shown, sp_row);
        this._icon = new Icon(branch.createElement("icon", Icon.TAG), { name: Icon.NAMES[0] });
        shown.appendChild(this._icon.el);
        var word = branch.createElement("word", "span");
        css.addClass(word, sp_text);
        shown.appendChild(word);
        root.appendChild(shown);

        var at = 0;
        var tell = function () { word.textContent = self._icon.name() == null ? "(blank)" : self._icon.name(); };
        tell();
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var next = new ButtonBuilder().label("The next word").plain().onClick(function () {
            at = (at + 1) % Icon.NAMES.length;
            self._icon.set(Icon.NAMES[at]);
            tell();
            say("the mark is \"" + Icon.NAMES[at] + "\" - word " + (at + 1) + " of " + Icon.NAMES.length);
        });
        row.appendChild(next.build(branch.createElement("next", next.tag)).el);
        var clear = new ButtonBuilder().label("Clear it").plain().onClick(function () {
            self._icon.clear();
            tell();
            say("blank, at its width: what stands beside it stays where it was");
        });
        row.appendChild(clear.build(branch.createElement("clear", clear.tag)).el);
        var unknown = new ButtonBuilder().label("Ask for a word it lacks").plain().onClick(function () {
            try { self._icon.set("no-such-word"); say("taken - which it should not have been"); }
            catch (e) { say("refused: " + e.message); }
        });
        row.appendChild(unknown.build(branch.createElement("unknown", unknown.tag)).el);
        root.appendChild(row);

        this.root = root;
        say(Icon.NAMES.length + " words in the vocabulary: step through them, clear the mark, or ask for one it lacks");
    }

    /** An icon varies along no axis. */
    extent(axis, v) {}

    /** It takes no keys: its buttons take theirs natively. */
    key(ev) { return false; }

    dispose() { try { this.branch.dissolve(); } catch (e) {} }
}
