// =============================================================================
// TabPane — one open tab, whole: the TAB, the chip that names it on a strip,
// and the PANE, the body that holds its widget. Both are minted once, on a
// branch of the tab-pane's own that its desk's register made for it, and
// they travel together from host to host: the same elements, the same look,
// wherever it is and in flight. A branch cannot move, so the branch is under
// the desk for the tab-pane's whole life; its focus membership is under the
// host that shows it (RFC 0066 E3, appendix "tab-panes").
//
// Made by TabRegister.open, never by hand:
//   new TabPane(branch, { id, title?, icon?, pinned?, closable?, focus, make, onCloseRequested? }, register)
//     id      the owner's name for it, any string. It is never a name on the party.
//     focus   the focus branch the widget joins: the host's, by the law.
//     make(branch, tab) → the widget, built on a sub-branch of the tab-pane's
//             own and handed the tab's handle, { id, name, focus, title(text?), icon(el?) },
//             by which it names itself on its own tab. The widget is by the law: a
//             member of the focus branch it was handed, as widget.focus, answering
//             activate(), with a root, which goes in the pane once. Its branch is
//             named as the tab-pane is, fresh and unique on its desk, and tab.name
//             says it again: a widget that joins under its branch's name, as the
//             stack's do, never meets another of that name in a host — a focus
//             branch refuses a name twice.
//     onCloseRequested(tp)  a close ASKED for — the cross, the tab menu — goes here and
//             not to close(): for an owner whose close is its own, done in its own
//             order (a workspace's core: unmount, then close). None, and it is a close.
//
//   tp.id  tp.chip  tp.pane  tp.widget  tp.branch  tp.pinned  tp.closable
//                     the parts, the same for its whole life
//   tp.title(text?)   the name on its chip: the label, the tooltip, what the
//                     cross says it closes. Read back with no argument. A new
//                     name is told to the host it is in, host.renamed(tp),
//                     which says so; a replace's name is the become's, not a rename
//   tp.icon(el?)      the holder's element before the label; null for none.
//                     Read back with no argument.
//   tp.shown(on)      the pane shown or hidden: its host says which one shows
//   tp.replace(make, title?)   the widget swapped IN PLACE: the old one disposed and its
//                     branch dissolved, a new one made by make(branch, tab) on a branch of
//                     the same name, joining the focus branch of the host it is in (or
//                     where it rests), its root in the same pane; the chip the same chip,
//                     renamed when a title is given. "This tab becomes that", with
//                     nothing leaving — what an opener does with what you pick
//   tp.host()         the host it is in, or null
//   tp.closed()       true from the moment its close begins: the host letting it
//                     go then knows it is a close, not a move
//   tp.close()        the widget disposed and its membership left, its host
//                     told to let it go, its register's entry gone, its branch
//                     dissolved: the one dissolve in its life, as the register's
//                     open is the one mint. A second close is nothing.
//   tp.requestClose() → true when its owner's onCloseRequested took the asking, which
//                     closes it when it will; false when there is none, and the asker
//                     closes it: what the cross and the tab menu do
//
// THE HOST'S SIDE. A host calls tp._hostedBy(host) when it takes the tab-pane
// in, and _hostedBy(null) when it lets it go; let go, and not closing, the
// widget's membership goes back to rest in its register's focus branch, when
// there is one, since the host it left may be about to go. What the chip is asked goes to
// the host it is in: a press is host.select(tp); a right-click, the menu key
// or Shift+F10 is host.menu(tp, at, byKey) → boolean, true when a menu
// opened. The cross is the tab-pane's own and closes it; a close disposes
// the widget and then tells the host, host.letGo(tp), with closed() true. With no host, a press and a menu do
// nothing. `css` is injected with the styles import.
// =============================================================================

const _tabPaneOwner = Object.freeze({ toString: () => "tabPane" });

class TabPane {
    constructor(branch, spec, register) {
        if (!branch) throw new Error("[TabPane] a branch of its own is required: TabRegister.open makes one");
        var s = spec || {};
        if (typeof s.make !== "function") throw new Error("[TabPane] '" + s.id + "': make(branch, tab) is how its widget is built");
        if (!s.focus) throw new Error("[TabPane] '" + s.id + "': the focus branch its widget joins is required");
        branch.activate(_tabPaneOwner);
        var self = this;
        this.branch = branch;
        this.id = s.id;
        this.pinned = !!s.pinned;
        this.closable = s.closable !== false && !this.pinned;
        this._register = register || null;
        this._onCloseRequested = typeof s.onCloseRequested === "function" ? s.onCloseRequested : null;
        this._host = null;
        this._closed = false;
        this._title = s.title == null ? s.id : String(s.title);
        this._icon = s.icon || null;
        this.chip = TabChip.mint(branch, { id: s.id, title: this._title, icon: this._icon, pinned: this.pinned, closable: this.closable }, {
            onSelect: function () { if (self._host) self._host.select(self); },
            onClose: function () { if (!self.requestClose()) self.close(); },
            onMenu: function (at, byKey) { return self._host ? !!self._host.menu(self, at, byKey) : false; }
        });
        this.pane = branch.createElement("pane", "div");
        css.addClass(this.pane, mtp_tab_content, mtp_tab_content_hidden);
        this.pane.setAttribute("role", "tabpanel");
        this._home = s.focus;        // where its widget first joined
        this.widget = TabPane._build(this, s.make, s.focus);
        this.pane.appendChild(this.widget.root);
    }

    /** The widget, made on a sub-branch of the tab-pane's own, and held to the law before anything is shown. */
    static _build(tp, make, focus) {
        var tab = Object.freeze({
            id: tp.id,
            name: tp.branch.name,
            focus: focus,
            title: function (t) { if (arguments.length) tp.title(t); return tp._title; },
            icon: function (el) { if (arguments.length) tp.icon(el); return tp._icon; }
        });
        tp._widgetBranch = tp.branch.createBranch(tp.branch.name);
        var made = make(tp._widgetBranch, tab);   // "made", not "widget": that name is an injected binding
        if (!made || typeof made !== "object" || !made.root) {
            throw new Error("[TabPane] '" + tp.id + "': make returned no widget with a root");
        }
        if (!PaneKeys.law(made)) {
            TabPane._dispose(made);
            throw new Error("[TabPane] '" + tp.id + "': its widget is not logically focusable - it must join the focus branch "
                          + "it was handed (tab.focus), expose it as widget.focus, and answer activate()");
        }
        return made;
    }

    replace(make, title) {
        if (this._closed) throw new Error("[TabPane] '" + this.id + "' is closed");
        if (typeof make !== "function") throw new Error("[TabPane] replace wants make(branch, tab)");
        var rest = this._register ? this._register.focus : null;
        var focus = this._host ? this._host.focus : (rest || this._home);
        TabPane._dispose(this.widget);
        this._widgetBranch.dissolve();   // its elements out of the pane, and its name free for the next
        this.widget = TabPane._build(this, make, focus);
        this.pane.appendChild(this.widget.root);
        if (title != null) this._name(title);
        return this;
    }

    title(text) {
        if (arguments.length === 0) return this._title;
        var was = this._title;
        this._name(text);
        if (this._title !== was && this._host && typeof this._host.renamed === "function") this._host.renamed(this);
        return this;
    }

    _name(text) {
        this._title = text == null ? "" : String(text);
        TabChip.retitle(this.chip, this._title);
    }

    icon(el) {
        if (arguments.length === 0) return this._icon;
        this._icon = el || null;
        TabChip.reicon(this.chip, this._icon);
        return this;
    }

    shown(on) {
        css.toggleClass(this.pane, mtp_tab_content_hidden, !on);
        return this;
    }

    host() { return this._host; }

    closed() { return this._closed; }

    /** The host's to call: when it takes the tab-pane in, and with null when it lets it go. */
    _hostedBy(host) {
        this._host = host || null;
        var rest = this._register ? this._register.focus : null, m = this.widget.focus;
        if (!host && !this._closed && rest && m && m.in && m.in !== rest) rest.adopt(m);
    }

    requestClose() {
        if (this._closed || !this._onCloseRequested) return false;
        try { this._onCloseRequested(this); }
        catch (e) { console.error("[TabPane] '" + this.id + "': onCloseRequested threw:", e); }
        return true;
    }

    close() {
        if (this._closed) return this;
        this._closed = true;
        TabPane._dispose(this.widget);   // first, as a close always was: the host reports it gone with its widget already disposed
        if (this._host) this._host.letGo(this);
        this._host = null;
        if (this._register) this._register._forget(this);
        this.branch.dissolve();
        return this;
    }

    /** The widget disposed, and its membership left if it forgot to: a closed tab's widget is out of the tree. */
    static _dispose(w) {
        if (typeof w.dispose === "function") {
            try { w.dispose(); } catch (e) { console.error("[TabPane] widget.dispose threw:", e); }
        }
        if (w.focus && w.focus.in && typeof w.focus.leave === "function") w.focus.leave();
    }
}
