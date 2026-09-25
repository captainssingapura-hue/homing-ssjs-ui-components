package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The tab-pane and its register over a fake DOM, before any host (RFC 0066
 * E3, appendix "tab-panes", the sequence's step 1): a tab-pane minted once on
 * a branch of its own under the register, named fresh and never by its id; the
 * id any string and unique in its register; open whole or not at all; the chip
 * and the pane the same elements for the tab-pane's whole life, its title and
 * icon changed in place, by the holder or by the widget through its handle;
 * what the chip is asked going to the host it is in, the cross its own; close
 * the one dissolve. The fake party holds the real one's rule for a name, and
 * dissolves as it does: its elements out of the document, its name free.
 */
class TabPaneTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/panes/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tag: tag, children: [], parentNode: null, listeners: {}, textContent: "", title: "",
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                get firstChild() { return this.children[0] || null; },
                appendChild: function (c) { if (c.parentNode) c.parentNode.removeChild(c); this.children.push(c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                remove: function () { if (this.parentNode) this.parentNode.removeChild(this); },
                contains: function (t) { for (var x = t; x; x = x.parentNode) if (x === this) return true; return false; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                fire: function (t, ev) { var e = ev || {}; e.defaultPrevented = false; e.preventDefault = function () { e.defaultPrevented = true; }; e.stopPropagation = function () {};
                                         (this.listeners[t] || []).slice().forEach(function (fn) { fn(e); }); return e; },
                has: function (c) { return classes.has(c); } };
            return node;
        }
        // the party's rule: a name of letters, digits, _ and -, free on its branch until it dissolves; a dissolve
        // takes its elements out of the document, its sub-branches with it, and frees its name
        var VALID = /^[A-Za-z0-9_-]+$/;
        function fakeBranch(name) {
            return { name: name, kids: new Map(), els: new Map(), dissolved: false, parent: null,
                activate: function (owner) { this.owner = String(owner); },
                createElement: function (n, tag) { if (!VALID.test(n) || this.els.has(n)) throw new RangeError('createElement: "' + n + '" is not a free, valid name'); var e = el(tag); this.els.set(n, e); return e; },
                createBranch: function (n) { if (!VALID.test(n) || this.kids.has(n)) throw new RangeError('createBranch: "' + n + '" is not a free, valid name'); var b = fakeBranch(n); b.parent = this; this.kids.set(n, b); return b; },
                dissolve: function () { Array.from(this.kids.values()).forEach(function (k) { k.dissolve(); }); this.els.forEach(function (e) { e.remove(); }); this.els.clear();
                                        this.dissolved = true; if (this.parent) this.parent.kids.delete(name); } };
        }
        var crypto = { randomUUID: (function () { var n = 0; return function () { return "u" + (++n); }; })() };
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    toggleClass: function (e, c, f) { if (f) e.classList.add(c); else e.classList.remove(c); } };
        var mtp_chip = "mtp_chip", mtp_chip_seated = "mtp_chip_seated", mtp_chip_icon = "mtp_chip_icon", mtp_chip_icon_on = "mtp_chip_icon_on",
            mtp_chip_label = "mtp_chip_label", mtp_chip_mark = "mtp_chip_mark", mtp_chip_close = "mtp_chip_close",
            mtp_tab_content = "mtp_tab_content", mtp_tab_content_hidden = "mtp_tab_content_hidden";
        var console = { error: function (m) { log.push("error:" + m); } };
        // a focus branch: members join by name and leave
        function focusBranch(name) {
            var b = { name: name, members: [] };
            b.join = function (n, w) { var m = { name: n, in: b, leave: function () { var i = m.in ? m.in.members.indexOf(m) : -1; if (i >= 0) m.in.members.splice(i, 1); m.in = null; } }; b.members.push(m); return m; };
            b.adopt = function (m) { var i = m.in.members.indexOf(m); if (i >= 0) m.in.members.splice(i, 1); m.in = b; b.members.push(m); return m; };
            return b;
        }
        var dock = focusBranch("dock");
        // a widget by the law: it activates the branch it is handed, joins the focus branch on its handle, answers activate()
        var handles = {};
        function widget(key) {
            return function (branch, tab) {
                branch.activate("widget");
                handles[key] = { branch: branch, tab: tab };
                var w = { root: branch.createElement("root", "div"), activate: function () {}, dispose: function () { log.push(key + ":disposed"); } };
                w.focus = tab.focus.join(key, w);
                return w;
            };
        }
        function host(name) {
            return { select: function (tp) { log.push(name + ":select:" + tp.id); },
                     menu: function (tp, at, byKey) { log.push(name + ":menu:" + tp.id); return true; },
                     letGo: function (tp) { log.push(name + ":letGo:" + tp.id); } };
        }
        var page = fakeBranch("page");
        var register = new TabRegister(page.createBranch("tabs"));
        function names(b) { return Array.from(b.els.keys()).join(","); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(P + "PaneKeysModule.js");
        loadModule(P + "TabChipModule.js");
        loadModule(P + "TabPaneModule.js");
        loadModule(P + "TabRegisterModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    /** The branch is the register's to name, fresh, and no name anywhere is made from the id. */
    @Test
    void aTabPaneIsMintedOnceOnABranchOfItsOwn_namedFresh_neverByItsId() {
        eval("register.open({ id: 'a:b', focus: dock, make: widget('w1') }); register.open({ id: 'doc/intro.md', title: 'Intro', focus: dock, make: widget('w2') });"
           + "register.open({ id: 'x y', focus: dock, make: widget('w3') });");
        assertEquals("tabpane-u1,tabpane-u2,tabpane-u3", eval("Array.from(register.branch.kids.keys()).join(',')").asString());
        assertEquals("a:b,doc/intro.md,x y", eval("register.ids().join(',')").asString(), "any string, in the order opened");
        assertEquals("chip,icon,label,mark,close,pane", eval("names(register.get('a:b').branch)").asString(), "the tab and the pane, and nothing named after the id");
        assertEquals("tabpane-u1", eval("Array.from(register.get('a:b').branch.kids.keys()).join(',')").asString(), "the widget on a sub-branch of the tab-pane's own, named as the tab-pane is");
        assertTrue(eval("handles.w1.branch.parent === register.get('a:b').branch").asBoolean());
        assertTrue(eval("register.get('doc/intro.md').pane.children[0] === register.get('doc/intro.md').widget.root").asBoolean(), "its root in the pane");
        assertEquals("Intro", eval("register.get('doc/intro.md').chip._label.textContent").asString());
        assertEquals("a:b", eval("register.get('a:b').chip._label.textContent").asString(), "no title: the id names it");
        assertEquals("w1,w2,w3", eval("dock.members.map(function (m) { return m.name; }).join(',')").asString(), "each widget joined the focus branch it was handed");
    }

    @Test
    void idsAreUniqueInARegister_aSecondIsRefusedBeforeAnythingIsMade_andAnotherDeskMayHoldIt() {
        eval("register.open({ id: 'a', focus: dock, make: widget('w1') });");
        var ex = assertThrows(PolyglotException.class, () -> eval("register.open({ id: 'a', focus: dock, make: widget('w2') })"));
        assertTrue(ex.getMessage().contains("already open as 'a'"), ex.getMessage());
        assertEquals(1, eval("register.branch.kids.size").asInt(), "no branch was made for the refused one");
        assertFalse(eval("'w2' in handles").asBoolean(), "and no widget");
        eval("var other = new TabRegister(page.createBranch('tabs2')); other.open({ id: 'a', focus: dock, make: widget('w3') });");
        assertTrue(eval("other.has('a') && register.has('a') && other.get('a') !== register.get('a')").asBoolean(), "ids are per desk");
        assertThrows(PolyglotException.class, () -> eval("register.open({ id: '', focus: dock, make: widget('w4') })"));
    }

    @Test
    void openIsWholeOrNotAtAll() {
        var ex = assertThrows(PolyglotException.class, () -> eval("register.open({ id: 'a', focus: dock, make: function () { throw new Error('no widget today'); } })"));
        assertTrue(ex.getMessage().contains("no widget today"), ex.getMessage());
        assertFalse(eval("register.has('a')").asBoolean());
        assertEquals(0, eval("register.branch.kids.size").asInt(), "its branch dissolved");
        // not by the law: joined, but no activate()
        eval("var lawless = function (b, tab) { b.activate('w'); var w = { root: b.createElement('root', 'div'), dispose: function () { log.push('lawless:disposed'); } }; w.focus = tab.focus.join('lawless', w); return w; };");
        ex = assertThrows(PolyglotException.class, () -> eval("register.open({ id: 'a', focus: dock, make: lawless })"));
        assertTrue(ex.getMessage().contains("not logically focusable"), ex.getMessage());
        assertEquals("lawless:disposed", log());
        assertEquals(0, eval("dock.members.length").asInt(), "its membership left");
        assertEquals(0, eval("register.branch.kids.size").asInt());
        eval("register.open({ id: 'a', focus: dock, make: widget('w1') });");
        assertTrue(eval("register.has('a')").asBoolean(), "the id was left free");
    }

    @Test
    void theChipAndThePaneAreTheSameElements_theNameAndIconChangedInPlace_byTheHolderOrTheWidget() {
        eval("var tp = register.open({ id: 't', title: 'Old', focus: dock, make: widget('w1') }); var chip = tp.chip, pane = tp.pane;");
        eval("tp.title('New');");
        assertEquals("New|New|Close New", eval("chip._label.textContent + '|' + chip.title + '|' + chip._close.getAttribute('aria-label')").asString());
        eval("handles.w1.tab.title('Named by the widget');");
        assertEquals("Named by the widget", eval("chip._label.textContent").asString(), "the widget names itself on its own tab");
        assertEquals("Named by the widget", eval("handles.w1.tab.title() + ''").asString());
        eval("var ic = el('img'); tp.icon(ic);");
        assertTrue(eval("chip._icon.children[0] === ic && chip._icon.has('mtp_chip_icon_on') && tp.icon() === ic").asBoolean());
        eval("tp.icon(null);");
        assertTrue(eval("chip._icon.children.length === 0 && !chip._icon.has('mtp_chip_icon_on') && tp.icon() === null").asBoolean());
        assertTrue(eval("tp.chip === chip && tp.pane === pane").asBoolean(), "the same elements throughout");
        assertTrue(eval("pane.has('mtp_tab_content_hidden')").asBoolean(), "hidden until a host shows it");
        eval("tp.shown(true);");
        assertFalse(eval("pane.has('mtp_tab_content_hidden')").asBoolean());
        eval("tp.shown(false);");
        assertTrue(eval("pane.has('mtp_tab_content_hidden')").asBoolean());
    }

    @Test
    void whatTheChipIsAskedGoesToTheHostItIsIn_andTheCrossIsItsOwn() {
        eval("var tp = register.open({ id: 't', focus: dock, make: widget('w1') });");
        eval("tp.chip.fire('pointerdown', { button: 0, target: tp.chip });");
        assertFalse(eval("tp.chip.fire('contextmenu', { clientX: 5, clientY: 6 }).defaultPrevented").asBoolean(), "no host: nothing asked, the browser's own menu");
        assertEquals("", log());
        eval("var h = host('h'); tp._hostedBy(h);");
        assertTrue(eval("tp.host() === h").asBoolean());
        eval("tp.chip.fire('pointerdown', { button: 0, target: tp.chip });");
        assertTrue(eval("tp.chip.fire('contextmenu', { clientX: 5, clientY: 6 }).defaultPrevented").asBoolean(), "the host opened a menu: the browser's is suppressed");
        assertEquals("h:select:t h:menu:t", log());
        eval("log.length = 0; tp.chip._close.fire('click', {});");
        assertEquals("w1:disposed h:letGo:t", log(), "the cross closes the tab-pane: the widget disposed, then its host told");
        assertFalse(eval("register.has('t')").asBoolean());
    }

    /** Close is the one dissolve: the parts out of wherever they were, the membership left, the id free for a new one. */
    @Test
    void closeIsTheOneDissolve_andASecondIsNothing() {
        eval("var tp = register.open({ id: 't', focus: dock, make: widget('w1') }); var strip = el('strip'), content = el('content');"
           + "strip.appendChild(tp.chip); content.appendChild(tp.pane); var branch = tp.branch;");
        eval("tp.close();");
        assertEquals("w1:disposed", log());
        assertTrue(eval("branch.dissolved && tp.chip.parentNode === null && tp.pane.parentNode === null").asBoolean(), "the parts out of the document");
        assertEquals(0, eval("dock.members.length").asInt(), "the membership left");
        assertEquals(0, eval("register.branch.kids.size").asInt());
        eval("tp.close();");
        assertEquals("w1:disposed", log(), "a second close is nothing");
        eval("var again = register.open({ id: 't', focus: dock, make: widget('w2') });");
        assertEquals("tabpane-u2", eval("again.branch.name").asString(), "the same id again: a new tab-pane on a new name");
    }

    /** A register with a focus branch of the desk's: a widget joins there when opened, under the tab-pane's own name, and comes back there when a host lets it go. */
    @Test
    void aWidgetRestsInItsDesksFocusBranch_whileNoHostHoldsIt() {
        eval("var desk = focusBranch('desk'), dock2 = focusBranch('dock2'); var resting = new TabRegister(page.createBranch('tabs2'), { focus: desk });"
           + "var tp = resting.open({ id: 'r', make: widget('wr') });");
        assertTrue(eval("tp.widget.focus.in === desk").asBoolean(), "opened with no focus of its own: it joins the desk's");
        assertEquals("tabpane-u1", eval("handles.wr.tab.name").asString(), "the name it may join under: the tab-pane's own");
        eval("dock2.adopt(tp.widget.focus); tp._hostedBy(host('h')); tp._hostedBy(null);");
        assertTrue(eval("tp.widget.focus.in === desk").asBoolean(), "let go: back to rest, whatever becomes of the host it left");
        eval("tp.close();");
        assertEquals(0, eval("desk.members.length + dock2.members.length").asInt(), "closed: gone from both");
    }

    @Test
    void aPinnedTab_orOneThatCannotBeClosed_hasNoCross() {
        eval("var p = register.open({ id: 'p', pinned: true, focus: dock, make: widget('w1') }); var k = register.open({ id: 'k', closable: false, focus: dock, make: widget('w2') });");
        assertTrue(eval("p.chip._close === null && k.chip._close === null && !p.closable && !k.closable && p.pinned").asBoolean());
    }

    @Test
    void freeIdIsTheFirstNoTabPaneHolds() {
        eval("register.open({ id: 'note', focus: dock, make: widget('w1') }); register.open({ id: 'note-2', focus: dock, make: widget('w2') });");
        assertEquals("note-3", eval("register.freeId('note')").asString());
        assertEquals("plate", eval("register.freeId('plate')").asString());
    }

    @Test
    void disposeClosesEveryTabPane_inTheOrderOpened() {
        eval("register.open({ id: 'a', focus: dock, make: widget('wa') }); register.open({ id: 'b', focus: dock, make: widget('wb') }); register.dispose();");
        assertEquals("wa:disposed wb:disposed", log());
        assertTrue(eval("register.branch.dissolved && register.count() === 0").asBoolean());
        assertThrows(PolyglotException.class, () -> eval("register.open({ id: 'c', focus: dock, make: widget('wc') })"));
    }

    /** Where randomUUID is missing — a page served over plain http, not localhost — the uuid is made from getRandomValues. */
    @Test
    void outsideASecureContextTheUuidIsMadeFromRandomValues() {
        String u = eval("var kept = crypto; crypto = { getRandomValues: function (a) { for (var i = 0; i < a.length; i++) a[i] = (i * 37 + 11) & 255; return a; } };"
                + "var u = TabRegister._uuid(); crypto = kept; u").asString();
        assertTrue(u.matches("[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"), u);
    }
}
