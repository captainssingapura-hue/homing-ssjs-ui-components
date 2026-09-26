// =============================================================================
// TabSource — where a new tab comes from. A page knows what it can mount; a
// pane knows how to hold a tab; between them there is one rule nobody owned:
// a tab opened on the desk, and a widget made for THE DOCK IT IS GOING INTO
// rather than the one it was written for. That rule is here, so every way of
// asking for a tab — a control, the strip's own button, a menu, a restore
// from a checkpoint — asks for it the same way.
//
//   new TabSource(branch, { kinds, desk })
//     branch: the source's own
//     desk:   the Desk its tabs are TAB-PANES of (RFC 0066 E3, appendix
//             "tab-panes"): each opened in desk.register — which names it and
//             its branch, and owns it — and put in its pane by desk.move, which
//             says it arrived. The id is the register's, "tab-3": it says which
//             tab, and never what it holds or how it was opened, since a tab
//             may become something else. The kind is in the title, "Books 2"
//     kinds:  [ { id, label, title?, listed?, make(branch, params) } ]
//             make returns a widget by the pane's law — a member of the branch
//             handed in params.focus, answering activate(). It is given the
//             branch, that membership, the id and title the source settled on,
//             the tab-pane's own handle as params.tab, and THE PANE it is going
//             into: most widgets ignore the pane and the tab, and one that is
//             about the tab itself — an opener that becomes what you pick —
//             cannot do its job without them.
//             listed:false keeps a kind out of kinds() while add still knows
//             it: the opener is asked for by the plus, not chosen from a list
//             of things to open, and it must not offer itself.
//
//   source.kinds()              → [ { id, label, title } ], frozen: a dropdown's rows.
//                               The listed ones only; add takes any of them
//   source.has(kindId)
//   source.canAdd(pane)         → room on the desk — its budget, the only limit there is —
//                               asked before anything is made. NOT the pane's own canAdd(),
//                               which is about the strip's plus
//   source.add(pane, kindId, how?) → { tab, index }: addTo, with the tab-pane it made
//   source.addTo(pane, kindId, how?)  → the index it landed at, or −1 when the pane
//                               would not take it. HOW a tab arrives is the third thing
//                               a caller must say, beside which pane and which kind:
//                                 "quiet" — put in the strip and left alone
//                                 "front" — shown, the keys staying where they were
//                                 "focus" — shown, and the keys go into it
//                               "front" unless said. A tab somebody asked for should be
//                               the one they are looking at; taking their KEYS as well is
//                               another matter, and only right when the asking was done
//                               in the strip itself — the plus — rather than in some
//                               control they are still standing in.
//   source.show(pane, tab, how) the same three endings, for a caller that placed the tab itself
//   source.modes()              → [ { id, label, says } ]: the three, worded, for a control
//                               that would let its user pick one
//   source.become(tabId, kindId) → the index in the host it is in: the
//                               tab-pane under that id becomes one of that kind IN PLACE —
//                               the same tab, chip and pane, a new widget, the kind's name —
//                               and is shown with the keys: what an opener does with what
//                               you pick, finding its own tab by the handle it was built with
//   source.dispose()
//
// THE SOURCE DECIDES NOTHING ABOUT WHERE. Which pane is the caller's; a
// control asks its user, the strip's button would name its own pane, and a
// restore names the one in the record. The source is asked for a tab and says
// what a tab is.
//
// Nothing here touches the DOM: a widget's constructor does, and that is the
// kind's. What is made is checked against the law before it is handed on, so a
// kind that returns the wrong thing is caught where it was written rather than
// three frames later inside a pane.
// =============================================================================

const _sourceOwner = Object.freeze({ toString: () => "tabSource" });

/**
 * How a new tab ARRIVES. Adding one and showing one are different acts, and
 * showing one and handing it the keys are different again: a page seeding a
 * workspace wants neither, a control standing somewhere else wants the tab in
 * front but not the keys off the hand that is using it, and a plus at the end
 * of the strip — where the asking and the answering are the same place — wants
 * both.
 */
const _MODES = Object.freeze([
    Object.freeze({ id: "quiet", label: "quietly",                says: "put in the strip and left alone" }),
    Object.freeze({ id: "front", label: "in front",               says: "shown; the keys stay where they were" }),
    Object.freeze({ id: "focus", label: "in front, with the keys", says: "shown, and the keys go into it" })
]);
const _BY_MODE = _MODES.reduce(function (m, x) { m[x.id] = x; return m; }, {});

class TabSource {
    /** The page makes one: a branch of its own, and the kinds it can mount. */
    constructor(branch, opts) {
        if (!branch) throw new Error("[TabSource] a branch of its own is required");
        var o = opts || {};
        if (!o.desk || !o.desk.register || typeof o.desk.move !== "function") throw new Error("[TabSource] the desk its tabs are opened on is required");
        branch.activate(_sourceOwner);
        this.branch = branch;
        this._kinds = [];
        this._by = {};
        this._made = {};      // per kind, how many have been made: titles count up and never come back
        this._disposed = false;
        this._desk = o.desk;
        (o.kinds || []).forEach(this._declare, this);
        if (this._kinds.length === 0) throw new Error("[TabSource] a source with no kinds can make nothing");
    }

    _declare(k) {
        if (!k || !k.id) throw new Error("[TabSource] every kind wants an id");
        if (typeof k.make !== "function") throw new Error("[TabSource] kind '" + k.id + "': make(branch, params) is how one is built");
        if (this._by[k.id]) throw new Error("[TabSource] kind '" + k.id + "' is declared twice");
        var kind = Object.freeze({ id: String(k.id), label: String(k.label == null ? k.id : k.label), listed: k.listed !== false,
                                   title: String(k.title == null ? (k.label == null ? k.id : k.label) : k.title), make: k.make });
        this._by[kind.id] = kind;
        this._kinds.push(kind);
        this._made[kind.id] = 0;
    }

    /** What can be CHOSEN, in the order it was declared: a row apiece for whatever asks. Kinds declared listed:false are not among them. */
    kinds() {
        return this._kinds.filter(function (k) { return k.listed; })
                   .map(function (k) { return Object.freeze({ id: k.id, label: k.label, title: k.title }); });
    }

    has(kindId) { return !!this._by[kindId]; }

    /** The three ways a tab may arrive, for anyone who would rather name them than spell them. */
    static get MODES() { return Object.freeze(_MODES.map(function (x) { return x.id; })); }

    /**
     * The same three, with a word apiece and what each one does — rows for a
     * control that would let its user choose. The vocabulary belongs to the
     * source, so a control that offers it asks the source rather than keeping
     * a list of its own that could fall behind.
     */
    modes() { return _MODES.map(function (x) { return Object.freeze({ id: x.id, label: x.label, says: x.says }); }); }

    _how(how) {
        var m = how == null ? "front" : String(how);
        if (!_BY_MODE[m]) throw new Error("[TabSource] '" + m + "' is not how a tab arrives: " + TabSource.MODES.join(", "));
        return m;
    }

    /**
     * Whether there is ROOM for another — the desk's budget, since a pane has
     * none of its own. Asked before a widget is made, so a refusal costs
     * nothing and leaves nothing behind.
     *
     * NOT the pane's own canAdd(), which answers a different question: whether
     * the STRIP shows a plus. A dock built with addable:false has no button of
     * its own and still takes tabs all day — a drop from the desk, a merge, a
     * re-dock — so reading that flag here would mean a workspace could not
     * offer one way of adding without offering the other. A page that wants a
     * particular dock left alone keeps it out of the list it hands the control.
     */
    canAdd(pane) { return !!pane && this._desk.register.room() > 0; }

    /**
     * The whole gesture: ask the pane, make the tab, put it in, and end it the
     * way the caller says — quiet, in front, or in front with the keys. −1
     * when the pane has no room for it, and then nothing was made and nothing
     * has to be undone. The mode is checked BEFORE anything is built, so a
     * caller that names one wrongly is told so rather than leaving a tab
     * behind in a state nobody asked for.
     */
    addTo(pane, kindId, how) { return this.add(pane, kindId, how).index; }

    /**
     * The ending on its own, for a caller that placed the tab itself. "quiet" is a
     * tab in the strip and nothing more; "front" shows it, which a switch
     * already refuses to repeat if it is the one showing; "focus" shows it and
     * asks the WIDGET to activate, which is the widget's own word for taking
     * the keys and is the only thing here that touches them.
     */
    show(pane, tab, how) {
        var mode = this._how(how);
        if (mode === "quiet") return this;
        pane.switchTab(tab.id);
        if (mode === "focus" && tab.widget && typeof tab.widget.activate === "function") tab.widget.activate();
        return this;
    }

    /** The next of a kind's titles, counted up and never back. */
    _title(kind) {
        var n = ++this._made[kind.id];
        return n === 1 ? kind.title : kind.title + " " + n;
    }

    add(pane, kindId, how) {
        var mode = this._how(how), kind = this._by[kindId];
        if (!kind) throw new Error("[TabSource] no kind '" + kindId + "'");
        if (!this.canAdd(pane)) return { tab: null, index: -1 };
        var title = this._title(kind), at;
        var tab = this._desk.register.open({ title: title,   // no id: the register's own names it; the law is the tab-pane's to hold
                                             make: function (b, t) { return kind.make(b, { focus: t.focus, id: t.id, title: title, pane: pane, tab: t }); } });
        try { at = this._desk.move(tab, pane); }
        catch (e) { tab.close(); throw e; }   // refused where it was going: nothing left behind
        this.show(pane, tab, mode);
        return { tab: tab, index: at };
    }

    become(tabId, kindId) {
        var kind = this._by[kindId], tp = this._desk.register.get(tabId);
        if (!kind) throw new Error("[TabSource] no kind '" + kindId + "'");
        if (!tp) throw new Error("[TabSource] no tab-pane '" + tabId + "' on the desk");
        var host = tp.host(), title = this._title(kind);
        tp.replace(function (b, t) { return kind.make(b, { focus: t.focus, id: t.id, title: title, pane: host, tab: t }); }, title);
        if (host) this.show(host, tp, "focus");
        return host ? host.tabIndexOf(tabId) : -1;
    }

    /** The source and its branch. Its tabs are the desk's, and go with it. */
    dispose() {
        if (this._disposed) return;
        this._disposed = true;
        this._kinds = [];
        this._by = {};
        try { this.branch.dissolve(); } catch (e) {}
    }
}
