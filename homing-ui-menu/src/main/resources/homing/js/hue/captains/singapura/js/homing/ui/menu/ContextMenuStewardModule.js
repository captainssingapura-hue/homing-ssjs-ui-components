// =============================================================================
// ContextMenuSteward — one per page: the registry of kinds, the one instance
// per kind, the layer they show in, and the one menu active — the
// fundamental invariant. Lazy: a kind's instance is minted at the kind's first
// open, and nothing is listened to while no menu is open. Its layer is its own
// element, in the page only while a menu is open. A composed component on a
// branch of its own - ported largely unchanged (RFC 0071, owner 23 Oct): the
// page's holder makes a sub-branch for it and hands it in, then its kinds and
// its keys, and hands the steward to the components that offer menus. Each
// kind's instance is a context menu, level 0, cast as its popup; it hears the
// menu's picks by the row, through the menu's count.
//
//   new ContextMenuSteward(branch, new FixedContextMenuSteward(), place?)
//   steward.hold(types)              kinds → { kind, nodes }: a Java ContextMenuRegistry's MENUS; a kind twice refused
//   steward.keys(keyboard, id?)      the page's KeyboardSteward: the steward joins the keyboard party as
//              id (its branch's name, unless said) and CLAIMS the keys for
//              a menu while one is open — by call, on open, evicting whoever held —
//              gives them back to who held before when it closes, and closes the
//              menu when they are taken from it: losing the keys is losing the
//              screen, as on blur. Without a steward a menu has no keys
//   steward.define(kind)             a kind declared in JS, the same record, held
//                                    to the same rules (MenuTree.check):
//       .row(id, label, { icon?, hint?, checkable? }?)   a row; icon is an Icon word; checkable, a row checked or not
//       .divider()                           the rows after are a new section
//       .sub(id, label, opts?, sub => sub.row(...))   a row with rows, three levels at most
//       .done() → the steward
//   steward.specimen(kind, host, object?) → a static instance of the kind mounted
//                                    in the host, every level open, bound to the
//                                    object through the kind's handler; for a gallery
//   steward.undefine(kind)
//   steward.has(kind)   steward.kinds()
//   steward.handle(kind, { pick?(itemId, object), state?(itemId, object), close?(reason, object) })
//   steward.open(kind, object, at, { keyboard?, anchor? }?) → true when taken:
//       the kind is known; the component prevents the browser's menu on true
//       only. anchor is the element the menu is for, when there is one: a
//       scroll of a box holding it closes the menu, as a scroll of the page does
//   steward.close(reason?)           by the owner; reason defaults to "owner"
//   steward.bound()                  the object while a menu is open, else null
//   steward.active()                 the kind open, else null
//   steward.on(fn) → off             another listener for the events; off() removes it —
//                                    a shell that makes the one steward hands it to its
//                                    pages, and each listens for what it shows
//   steward.dispose()
//
// open closes whatever is open (replaced), binds the object to the kind's
// instance, shows it at the point the geometry gives, takes the focus and
// installs the listeners. A press outside closes it (outside) and is
// swallowed whole — the press, and the release and click of the same
// gesture — so nothing under the pointer is acted on; the contextmenu that
// follows a right-press is let through, so the next menu opens in the same
// gesture. Escape closes (escape); a scroll of the page, or of a box that
// holds the anchor, closes (scroll) — a scroll elsewhere, a log filling
// behind the menu, does not; a resize closes (scroll); the window losing
// focus closes (blur); the keys taken by another claim close it (taken). The
// keys come through the party while open — the holder is asked first, so the
// page behind sees none it takes — and Tab is held; no keydown listener of
// its own, no capture on the document. Closing returns the focus to what had
// it, and the keys to who held them. Every open, pick and close is one MenuEvents object on the
// sink; a pick is Picked then Closed(pick), and the handler acts last, so
// what it opens keeps the focus. `css` is injected with the styles import.
// =============================================================================

const _stewardOwner = Object.freeze({ toString: () => "contextMenus" });

/** A kind's menu picked a row, as the steward hears it: the row whose count went up, while that menu is the one open. */
class KindPicked {
    constructor(steward, menu) { this.steward = steward; this.menu = menu; Object.freeze(this); }
    canHandle(on) { return on === StateMenuPickControl; }
    handle(change) {
        var before = change.before || {}, after = change.after || {};
        for (var id in after) if (before[id] != null && after[id] !== before[id] && this.steward._menu === this.menu) { this.steward._pick(id); return; }
    }
}
var _pages = new WeakSet();          // the documents that have a steward: one each

class ContextMenuSteward extends SimpleComponent {
    static TAG = "div";
    static BEHAVIOR = ContextMenuStewardBehavior;   // the page heard while a menu is open, and only then

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[ContextMenuSteward] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        if (typeof document !== "undefined" && _pages.has(document)) throw new Error("[ContextMenuSteward] this page has a steward already: one per page");
        branch.activate(_stewardOwner);
        super(branch.elementMaker("layer"), fixed, FixedContextMenuSteward, place ?? manageContextMenuSteward);
        if (typeof document !== "undefined") _pages.add(document);
        this._branch = branch;
        css.addClass(this.el, cm_layer);
        this._sinks = [];
        this._types = {};
        this._handlers = {};
        this._menus = {};
        this._specimens = [];
        this._specimenCount = 0;
        this._kind = null;
        this._menu = null;
        this._object = null;
        this._restoreTo = null;
        this._anchor = null;
        this._kb = null; this._kbId = null; this._restoreKeys = null;
    }

    // ── handed in ─────────────────────────────────────────────────────────
    /** The kinds a Java registry stamps - kind → { kind, nodes } - each held to the tree's rules; a kind twice refused. */
    hold(types) {
        for (var k in types) {
            if (!Object.prototype.hasOwnProperty.call(types, k)) continue;
            if (this._types[k]) throw new Error("[ContextMenuSteward] kind declared twice: " + k);
            MenuTree.check(k, types[k].nodes);
            this._types[k] = types[k];
        }
        return this;
    }
    /** The page's keyboard steward, once: the steward a member of its party, claiming the keys for an open menu. */
    keys(keyboard, id) {
        if (this._kb) throw new Error("[ContextMenuSteward] in the keyboard party already");
        var self = this;
        this._kb = keyboard;
        this._kbId = keyboard.join(id != null ? String(id) : this._branch.name, {
            keyDown: function (ev) { return self._key(ev); },
            taken: function () { if (self._menu) { self._restoreKeys = null; self._close("taken"); } }
        });
        return this;
    }

    // ── the registry ──────────────────────────────────────────────────────
    define(kind) {
        var self = this, nodes = [];
        if (this._types[kind]) throw new Error("[ContextMenuSteward] kind declared twice: " + kind);
        function node(id, label, o, section) {
            var n = { id: id, label: label, section: section };
            if (o && o.icon) n.icon = o.icon;
            if (o && o.hint) n.hint = o.hint;
            if (o && o.checkable) n.checkable = true;
            return n;
        }
        function into(list, depth) {
            var section = 0;
            var b = {
                row: function (id, label, o) { list.push(node(id, label, o, section)); return b; },
                divider: function () { section++; return b; },
                sub: function (id, label, o, fill) {
                    if (typeof o === "function") { fill = o; o = null; }
                    if (depth >= MenuTree.MAX_DEPTH) throw new Error("[ContextMenuSteward] " + kind + ": " + id + " is at the last level and lists rows");
                    var n = node(id, label, o, section);
                    n.nodes = [];
                    fill(into(n.nodes, depth + 1));
                    list.push(n);
                    return b;
                }
            };
            return b;
        }
        var b = into(nodes, 1);
        b.done = function () { MenuTree.check(kind, nodes); self._types[kind] = { kind: kind, nodes: nodes }; return self; };
        return b;
    }
    specimen(kind, host, object) {
        if (!this.has(kind)) throw new Error("[ContextMenuSteward] no kind '" + kind + "'");
        var h = this._handlers[kind] || {};
        var sub = this._branch.createBranch("specimen-" + kind.replace(/[^A-Za-z0-9_-]/g, "_") + "-" + (++this._specimenCount));
        var menu = new ContextMenu(sub, new FixedContextMenu(), this._place(FixedContextMenu, RoleSpecimen));
        menu.hold(this._types[kind].nodes, kind);
        css.addClass(host, cm_specimen);
        menu.bind(object === undefined ? null : object, h.state ? function (id, o) { return h.state(id, o); } : null);
        menu.mount(host);
        this._specimens.push(menu);
        return menu;
    }
    undefine(kind) {
        if (this._kind === kind) this._close("owner");
        if (this._menus[kind]) { this._menus[kind].dispose(); delete this._menus[kind]; }
        delete this._types[kind];
        delete this._handlers[kind];
    }
    has(kind) { return Object.prototype.hasOwnProperty.call(this._types, kind); }
    kinds() { return Object.keys(this._types); }
    handle(kind, handler) { this._handlers[kind] = handler || {}; return this; }

    // ── one menu open ─────────────────────────────────────────────────────
    open(kind, object, at, opts) {
        if (!this.has(kind)) return false;
        if (this._menu) this._close("replaced");
        var menu = this._menuFor(kind), h = this._handlers[kind] || {};
        this._kind = kind;
        this._menu = menu;
        this._object = object;
        this._restoreTo = document.activeElement;
        this._anchor = opts && opts.anchor ? opts.anchor : null;
        menu.bind(object, h.state ? function (id, o) { return h.state(id, o); } : null);
        document.body.appendChild(this.el);
        var p = menu.show(this.el, { x: at.x, y: at.y }, { w: window.innerWidth, h: window.innerHeight }, !!(opts && opts.keyboard));
        this._listen(true);
        if (this._kb) { this._restoreKeys = this._kb.holder() === this._kbId ? this._restoreKeys : this._kb.holder(); this._kb.claim(this._kbId); }
        this._fire(MenuEvents.Opened(kind, p.x, p.y));
        return true;
    }
    close(reason) { if (this._menu) this._close(reason || "owner"); }
    bound() { return this._object; }
    active() { return this._kind; }

    _close(reason) {
        if (!this._menu) return;
        var kind = this._kind, menu = this._menu, object = this._object, h = this._handlers[kind] || {};
        this._listen(false);
        menu.hide();
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
        this._menu = null; this._kind = null; this._object = null; this._anchor = null;
        var back = this._restoreTo;
        this._restoreTo = null;
        if (back && back.isConnected !== false && typeof back.focus === "function") { try { back.focus({ preventScroll: true }); } catch (e) {} }
        if (this._kb && reason !== "replaced") {   // the keys back to who held before the menu, when still a member and not taken meanwhile
            var keys = this._restoreKeys;
            this._restoreKeys = null;
            if (this._kb.holder() === this._kbId) { if (keys && this._kb.has(keys)) this._kb.claim(keys); else this._kb.release(this._kbId); }
        }
        if (typeof h.close === "function") { try { h.close(reason, object); } catch (e) { console.error("[ContextMenuSteward] close handler threw:", e); } }
        this._fire(MenuEvents.Closed(kind, reason));
    }
    _pick(itemId) {
        var kind = this._kind, object = this._object, h = this._handlers[kind] || {};
        this._fire(MenuEvents.Picked(kind, itemId));
        this._close("pick");
        if (typeof h.pick === "function") { try { h.pick(itemId, object); } catch (e) { console.error("[ContextMenuSteward] pick handler threw:", e); } }
    }

    // ── minted once, lazily ───────────────────────────────────────────────
    _menuFor(kind) {
        if (!this._menus[kind]) {
            var sub = this._branch.createBranch("type-" + kind.replace(/[^A-Za-z0-9_-]/g, "_"));
            var menu = new ContextMenu(sub, new FixedContextMenu(), this._place(FixedContextMenu, RolePopup));
            menu.hold(this._types[kind].nodes, kind);
            menu.onChange(StateMenuPickControl, new KindPicked(this, menu));
            this._menus[kind] = menu;
        }
        return this._menus[kind];
    }

    // ── while open: the document and the window ───────────────────────────
    _listen(on) { this._behavior.listen(on); }
    /** A scroll its behaviour heard: closed, where it moves what the menu was opened for. */
    _scrolled(target) { if (this._scrollMoves(target)) this._close("scroll"); }
    /** Whether a scroll of this target moves what the menu was opened for: the page itself, or a box that holds the anchor. */
    _scrollMoves(target) {
        if (!this._menu || this._menu.contains(target)) return false;
        if (target === document || target === document.documentElement || target === document.body) return true;
        return !!(this._anchor && target && typeof target.contains === "function" && target.contains(this._anchor));
    }
    /** A press its behaviour heard: outside the open menu, it closes it - and its behaviour swallows the rest of the gesture. */
    _outside(target) {
        if (!this._menu || this._menu.contains(target)) return false;
        this._close("outside");
        return true;
    }
    /** A keydown while a menu holds the keys: Escape closes, the menu takes its own, Tab is held; true when taken. */
    _key(e) {
        if (!this._menu) return false;
        if (e.key === "Escape") { this._close("escape"); return true; }
        return !!(this._menu.key(e) || e.key === "Tab");
    }
    /** Another listener for the events; the function returned removes it. */
    on(fn) {
        if (typeof fn !== "function") throw new Error("[ContextMenuSteward] on wants a function");
        var sinks = this._sinks;
        sinks.push(fn);
        return function () { var i = sinks.indexOf(fn); if (i >= 0) sinks.splice(i, 1); };
    }
    _fire(ev) {
        var sinks = this._sinks.slice();
        for (var i = 0; i < sinks.length; i++) {
            try { sinks[i](ev); } catch (e) { console.error("[ContextMenuSteward] onEvent threw on " + ev.kind + ":", e); }
        }
    }

    dispose() {
        if (this._menu) this._close("owner");
        for (var k in this._menus) if (Object.prototype.hasOwnProperty.call(this._menus, k)) this._menus[k].dispose();
        this._menus = {};
        for (var s = 0; s < this._specimens.length; s++) this._specimens[s].dispose();
        this._specimens = [];
        if (this._kb) { this._kb.leave(this._kbId); this._kb = null; }
        if (typeof document !== "undefined") _pages.delete(document);
        this._branch.dissolve();
    }
}
