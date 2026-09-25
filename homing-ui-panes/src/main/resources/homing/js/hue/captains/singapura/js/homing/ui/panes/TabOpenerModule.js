// =============================================================================
// TabOpener — what a new tab holds until you say what it should hold. The
// strip's plus cannot know which of a page's kinds you meant, and guessing one
// is worse than asking: so the plus opens a tab with this in it, and picking
// from it TURNS THAT TAB INTO the thing you picked. The chip you made is the
// chip you keep, in the place you made it.
//
//   new TabOpener(branch, { focus, source, tab, prompt? })
//     focus:  the dock's branch, as any tab's widget joins it
//     source: the TabSource; its listed kinds are the choices, which is why
//             the opener's own kind is declared listed:false and never offers
//             itself among them
//     tab:    its own tab-pane's handle: the tab it turns, in place, into what you pick
//
//   opener.open(kindId)   → the index the tab is at, or −1. What you pick
//                         takes the keys, because you were holding them here
//
// IT IS REPLACED RATHER THAN CONTAINS. An opener that mounted the chosen
// thing inside itself would be a tab that is always an opener, wearing
// somebody else's name. The source has the tab-pane swap its widget in place:
// the opener is disposed, the kind's widget made on the same tab, chip and
// pane — and the source's rule for making it is the same rule the standalone
// control uses, so a tab opened here and a tab added there are the same kind
// of thing.
//
// The choices are NATIVE buttons inside a logical member: the widget holds the
// keys for the dock, the button that has the browser's focus holds them for
// the widget — said as `lent`, which is what a design answers — and an Escape
// the button did not want comes back to the widget rather than out of the
// room. That is the §14 seam, wired here once so a page need not wire it.
// =============================================================================

const _openerOwner = Object.freeze({ toString: () => "tabOpener" });

class TabOpener {
    constructor(branch, opts) {
        if (!branch) throw new Error("[TabOpener] a branch of its own is required");
        var o = opts || {}, self = this;
        if (!o.source) throw new Error("[TabOpener] a TabSource is what it opens from");
        if (!o.tab) throw new Error("[TabOpener] its own tab's handle: it turns that tab into what you pick");
        if (!o.focus) throw new Error("[TabOpener] the dock's focus branch, as any tab's widget wants");
        branch.activate(_openerOwner);
        this.branch = branch;
        this._source = o.source;
        this._tab = o.tab;
        this._picks = [];
        this._disposed = false;

        var root = branch.createElement("opener", "div");
        css.addClass(root, mtp_opener);
        var note = branch.createElement("note", "p");
        css.addClass(note, mtp_opener_note);
        note.textContent = o.prompt == null ? "What should this tab hold?" : String(o.prompt);
        root.appendChild(note);
        var grid = branch.createElement("grid", "div");
        css.addClass(grid, mtp_opener_grid);
        root.appendChild(grid);
        this._source.kinds().forEach(function (k) {
            var pick = branch.createElement("open-" + k.id, "button");
            css.addClass(pick, mtp_opener_pick);
            pick.setAttribute("type", "button");
            pick.textContent = k.label;
            pick.addEventListener("click", function () { self.open(k.id); });
            grid.appendChild(pick);
            self._picks.push(pick);
        });
        this.root = root;

        // the seam: an Escape the button let through blurs it, and the widget — the member since the press — has the keys again
        root.addEventListener("keydown", function (ev) {
            if (ev.key !== "Escape" || ev.target === root || !root.contains(ev.target)) return;
            ev.target.blur();
            ev.preventDefault();
            ev.stopPropagation();
            self._mark();
        });
        root.addEventListener("focusin", function () { if (self.root.getAttribute("data-keys")) self._mark(); });
        root.addEventListener("focusout", function () { setTimeout(function () { if (!self._disposed && self.root.getAttribute("data-keys")) self._mark(); }, 0); });

        this.focus = o.focus.join(branch.name, this);
        this._off = Keys.claimOn(root, this.focus);
    }

    /**
     * Pick one: this tab becomes that, IN PLACE — nothing leaves, so nothing
     * can vanish, and no budget is asked for a second tab. This widget is
     * disposed inside that call. THE KEYS WERE HERE: you were standing in this
     * tab when you picked, so what you picked takes them.
     */
    open(kindId) { return this._source.has(kindId) ? this._source.become(this._tab.id, kindId) : -1; }

    /** The law's: a press or the keys arriving make it the holder, and the first choice takes the browser's focus so the keys work at once. */
    activate() { Keys.claim(this.focus); }
    granted() { var first = this._picks[0]; if (first) { try { first.focus(); } catch (e) {} } this._mark(); }
    taken() { this.root.removeAttribute("data-keys"); }
    offered() { if (this.root.getAttribute("data-keys") === null) this.root.setAttribute("data-keys", "candidate"); }
    withdrawn() { if (this.root.getAttribute("data-keys") === "candidate") this.root.removeAttribute("data-keys"); }

    /** Nothing of its own is natively focused and the keys are the widget's: Escape gives them back to the dock. */
    keyDown(ev) { if (ev.key === "Escape") { Keys.yield(this.focus); return true; } return false; }

    /** Held, or lent while a choice has the browser's focus: one attribute, which the design answers. */
    _mark() {
        var a = typeof document === "undefined" ? null : document.activeElement;
        this.root.setAttribute("data-keys", a && a !== document.body && this.root.contains(a) ? "lent" : "held");
    }

    dispose() {
        if (this._disposed) return;
        this._disposed = true;
        this._picks = [];
        if (this._off) { this._off(); this._off = null; }
        if (this.focus && this.focus.in) this.focus.leave();
    }
}
