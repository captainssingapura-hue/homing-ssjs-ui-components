// =============================================================================
// TabRegister — a desk's register of its tab-panes. It opens them, each on a
// branch of its own named fresh, keeps them by id, and forgets them when they
// close. It is the only owner of a desk's tab-panes: a host holds them and
// never owns them (RFC 0066 E3, appendix "tab-panes", §3 and §6).
//
//   new TabRegister(branch, { focus?, budget?, onCount? })   the register's own branch, handed unactivated;
//                             every tab-pane's branch is a sub-branch of it.
//                             focus: the desk's focus branch, where a tab-pane's widget
//                             RESTS while no host holds it — it joins there when it is
//                             opened, unless open says otherwise, and comes back there
//                             when a host lets it go — so a membership outlives any host
//                             budget: the most tab-panes it holds at once, the DESK'S limit — a
//                             pane has none of its own; none, and there is no limit.
//                             onCount(count): told after every open and every close
//   register.open({ id?, title?, icon?, pinned?, closable?, focus?, make }) → the TabPane
//       focus: the branch its widget joins, the register's own unless said.
//       id: any non-empty string, unique in this register; none, and the
//       register names it, "tab-1", "tab-2" and on, never one it holds — an
//       id says WHICH tab, not what it holds nor how it was opened. A second tab-pane
//       under an id already open is refused before anything is made. Its
//       branch is "tabpane-" and a fresh uuid, never the id. Whole or not at
//       all: a make that throws, or a widget not by the law, leaves nothing
//       behind, and the id free. With the budget spent it is refused before
//       anything is made.
//   register.get(id) → the TabPane, or null      register.has(id)
//   register.ids()   → the ids, in the order opened      register.count()
//   register.budget() → the limit, Infinity for none      register.room() → how many more it takes
//   register.freeId(base) → base when no tab-pane here holds it, else
//       base-2, base-3 and on: the first that none holds
//   register.dispose()    every tab-pane closed, in the order opened, and the branch dissolved
//
// Ids are per register, which is per desk: two desks on a page may hold the
// same id, and a tab-pane never leaves the desk whose register made it, since
// a branch cannot move.
// =============================================================================

const _registerOwner = Object.freeze({ toString: () => "tabRegister" });

class TabRegister {
    constructor(branch, opts) {
        if (!branch) throw new Error("[TabRegister] a branch of its own is required");
        branch.activate(_registerOwner);
        this.branch = branch;
        this.focus = (opts && opts.focus) || null;
        this._budget = opts && opts.budget != null ? Math.max(1, opts.budget | 0) : Infinity;
        this._onCount = opts && typeof opts.onCount === "function" ? opts.onCount : null;
        this._tabs = new Map();        // id → TabPane, in the order opened
        this._named = 0;               // the ids it names itself count up from here
        this._disposed = false;
    }

    open(spec) {
        var s = spec || {};
        if (this._disposed) throw new Error("[TabRegister] disposed: nothing more is opened");
        if (s.id == null) s = Object.assign({}, s, { id: this._name() });
        if (typeof s.id !== "string" || !s.id) throw new Error("[TabRegister] a tab-pane's id is a non-empty string");
        if (this._tabs.has(s.id)) throw new Error("[TabRegister] a tab-pane is already open as '" + s.id + "'");
        if (this.room() <= 0) throw new Error("[TabRegister] the budget of " + this._budget + " tabs is spent");
        var branch = this.branch.createBranch("tabpane-" + TabRegister._uuid());
        var tp;
        try {
            tp = new TabPane(branch, s.focus || !this.focus ? s : Object.assign({}, s, { focus: this.focus }), this);
        } catch (e) {
            try { branch.dissolve(); } catch (x) {}
            throw e;
        }
        this._tabs.set(s.id, tp);
        this._counted();
        return tp;
    }

    get(id) { return this._tabs.get(id) || null; }
    has(id) { return this._tabs.has(id); }
    ids() { return Array.from(this._tabs.keys()); }
    count() { return this._tabs.size; }
    budget() { return this._budget; }
    room() { return this._budget - this._tabs.size; }

    freeId(base) {
        var b = String(base);
        if (!this._tabs.has(b)) return b;
        for (var n = 2; ; n++) if (!this._tabs.has(b + "-" + n)) return b + "-" + n;
    }

    /** An id of the register's own: counted up, never one it holds, never one it named before. */
    _name() {
        var id;
        do { id = "tab-" + (++this._named); } while (this._tabs.has(id));
        return id;
    }

    /** The tab-pane's own close calls this: it is gone from the register, and its id is free. */
    _forget(tp) { if (this._tabs.get(tp.id) === tp) { this._tabs.delete(tp.id); this._counted(); } }

    _counted() {
        if (!this._onCount) return;
        try { this._onCount(this._tabs.size); } catch (e) { console.error("[TabRegister] onCount threw:", e); }
    }

    dispose() {
        if (this._disposed) return;
        this._disposed = true;
        Array.from(this._tabs.values()).forEach(function (tp) { tp.close(); });
        this.branch.dissolve();
    }

    /**
     * RFC 4122 v4: crypto.randomUUID where the page is a secure context, else
     * the same from getRandomValues. The float layer makes its own the same way,
     * until the desk that holds both makes the one.
     */
    static _uuid() {
        if (typeof crypto.randomUUID === "function") return crypto.randomUUID();
        var b = crypto.getRandomValues(new Uint8Array(16)), h = "";
        b[6] = (b[6] & 0x0f) | 0x40;
        b[8] = (b[8] & 0x3f) | 0x80;
        for (var i = 0; i < 16; i++) h += (b[i] + 0x100).toString(16).slice(1);
        return h.slice(0, 8) + "-" + h.slice(8, 12) + "-" + h.slice(12, 16) + "-" + h.slice(16, 20) + "-" + h.slice(20);
    }
}
