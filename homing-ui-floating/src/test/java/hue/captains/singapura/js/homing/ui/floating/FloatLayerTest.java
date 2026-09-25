package hue.captains.singapura.js.homing.ui.floating;

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
 * The desk over a fake DOM: panes open on a cascade and on top, raise on a
 * press or on focus, close on the cross, on Escape and by the method, hold
 * a widget by the base's contract, and report every mutation as data —
 * once, when it happened, never per pixel. A pane's branch is the desk's,
 * named fresh at every opening; its id, the holder's, may be any string. The
 * fake party holds the real one's rule for a name.
 */
class FloatLayerTest extends JsModuleTestBase {

    private static final String EVENTS = "/homing/js/hue/captains/singapura/js/homing/ui/floating/FloatEventsModule.js";
    private static final String PANE   = "/homing/js/hue/captains/singapura/js/homing/ui/floating/FloatingPaneModule.js";
    private static final String DESK   = "/homing/js/hue/captains/singapura/js/homing/ui/floating/FloatLayerModule.js";

    // Elements that know their children, classes, attributes and inline
    // properties; a desk 800 by 600; a party branch; a css manager over
    // classList; the typed class names as the strings the page resolves them to.
    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            var node = { tag: tag, children: [], parentNode: null, listeners: {}, textContent: "",
                style: { setProperty: function (k, v) { props[k] = v; }, removeProperty: function (k) { delete props[k]; }, getPropertyValue: function (k) { return props[k] == null ? "" : props[k]; } },
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); },
                             remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); },
                             contains: function (c) { return classes.has(c); },
                             toggle: function (c, f) { if (f) classes.add(c); else classes.delete(c); } },
                appendChild: function (c) { if (c.parentNode) c.parentNode.removeChild(c); this.children.push(c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                removeEventListener: function (t, fn) { var l = this.listeners[t] || []; var i = l.indexOf(fn); if (i >= 0) l.splice(i, 1); },
                contains: function (c) { return c === this; },
                fire: function (t, ev) { var e = ev || {}; e.type = t; e.stopPropagation = e.stopPropagation || function () {}; e.preventDefault = e.preventDefault || function () {}; (this.listeners[t] || []).slice().forEach(function (fn) { fn(e); }); },
                has: function (c) { return classes.has(c); },
                prop: function (k) { return props[k]; } };
            return node;
        }
        // the party's rule: a name of letters, digits, _ and -, free on its branch until dissolved
        var VALID = /^[A-Za-z0-9_-]+$/;
        function fakeBranch(name) {
            return { name: name, dissolved: [], kids: new Map(), names: new Set(),
                createElement: function (n, tag) { if (!VALID.test(n) || this.names.has(n)) throw new RangeError('createElement: "' + n + '" is not a free, valid name'); this.names.add(n); return el(tag); },
                createBranch: function (n) { if (!VALID.test(n) || this.kids.has(n)) throw new RangeError('createBranch: "' + n + '" is not a free, valid name'); var b = fakeBranch(n); b.parent = this; this.kids.set(n, b); return b; },
                dissolve: function () { this.dissolved.push(name); log.push("dissolved:" + name); if (this.parent) this.parent.kids.delete(name); },
                activate: function (owner) { this.owner = String(owner); } };
        }
        var crypto = { randomUUID: (function () { var n = 0; return function () { return "u" + (++n); }; })() };   // in order, so a test can name them
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, f) { e.classList.toggle(c, f); },
                    hasClass: function (e, c) { return e.classList.contains(c); },
                    extent: function (e, t) { if (t == null) e.style.removeProperty("--extent"); else e.style.setProperty("--extent", String(t)); },
                    size: function (e, s) { if (s == null) e.style.removeProperty("--size"); else e.style.setProperty("--size", String(s)); } };
        var fp_desk = "fp_desk", fp_desk_layer = "fp_desk_layer", fp_frame = "fp_frame", fp_hoverable = "fp_hoverable", fp_held = "fp_held", fp_active = "fp_active", fp_head = "fp_head", fp_head_held = "fp_head_held",
            fp_icon = "fp_icon", fp_icon_on = "fp_icon_on", fp_title = "fp_title", fp_close = "fp_close", fp_body = "fp_body", fp_grip = "fp_grip";
        var console = { error: function (m, e) { log.push("error:" + m); } };
        var host = el("div");
        var branch = fakeBranch("page");
        function widget(key) {
            return function (b, params) {
                this.root = el("w-" + key);
                this.setActive = function (on) { log.push(key + ":" + (on ? "on" : "off")); };
                this.dispose = function () { log.push(key + ":disposed"); };
            };
        }
        var events = [];
        var members = {}, kbLog = [];
        var kb = { enroll: function (root, id) { root._kb = id; return function () { delete root._kb; }; }, memberAt: function (el) { for (var x = el; x; x = x.parentNode) if (x._kb) return x._kb; return null; }, join: function (id, h) { members[id] = h; kbLog.push("join:" + id); return id; }, leave: function (id) { delete members[id]; kbLog.push("leave:" + id); }, claim: function (id) { kbLog.push("claim:" + id); }, release: function () {} };
        var desk = new FloatLayer(branch.createBranch("desk"), { host: host, keyboard: kb, onEvent: function (ev) {
            events.push(ev);
            switch (ev.kind) {
                case "Opened":  log.push("opened:" + ev.id + "@" + ev.x + "," + ev.y + ":" + ev.w + "x" + ev.h); break;
                case "Moved":   log.push("moved:" + ev.id + "@" + ev.x + "," + ev.y); break;
                case "Resized": log.push("resized:" + ev.id + ":" + ev.w + "x" + ev.h); break;
                case "Raised":  log.push("raised:" + ev.id); break;
                case "Closed":  log.push("closed:" + ev.id); break;
                default: log.push("?" + ev.kind);
            } } });
        desk.root.clientWidth = 800; desk.root.clientHeight = 600;
        function frames() { return desk.root.children.map(function (f) { return f.getAttribute("aria-label") + (f.has("fp_active") ? "*" : "") + "@z" + f.prop("--fp-z"); }).join(","); }
        function headOf(id) { return desk.pane(id).root.children[0]; }
        function gripOf(id) { return desk.pane(id).root.children[2]; }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeysModule.js");
        loadModule(EVENTS);
        loadModule(PANE);
        loadModule(DESK);
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    @Test
    void panesOpenOnACascade_onTop_andTheNewestIsActive() {
        eval("desk.open({ title: 'A' }); desk.open({ title: 'B', w: 400, h: 300 });");
        assertEquals("opened:pane-1@24,24:320x220 raised:pane-1 opened:pane-2@52,52:400x300 raised:pane-2", log());
        assertEquals("A@z1,B*@z2", eval("frames()").asString());
        assertEquals("pane-1,pane-2", eval("desk.panes().join(',')").asString());
        assertEquals("pane-2", eval("desk.active()").asString());
        assertEquals("region", eval("desk.pane('pane-1').root.getAttribute('role')").asString());
    }

    @Test
    void aPressOrFocusRaises_andRaisingTheTopIsNotReported() {
        eval("desk.open({ id: 'a', title: 'A' }); desk.open({ id: 'b', title: 'B' }); log.length = 0;");
        eval("desk.pane('a').root.fire('pointerdown', { button: 0 });");
        assertEquals("raised:a", log());
        assertEquals("A*@z3,B@z2", eval("frames()").asString(), "DOM order stays; the stack is z");
        assertEquals("b,a", eval("desk.panes().join(',')").asString());
        eval("log.length = 0; desk.pane('a').root.fire('focusin', {});");
        assertEquals("", log(), "already on top: nothing to report");
        eval("desk.pane('b').root.fire('focusin', {});");
        assertEquals("raised:b", log());
    }

    @Test
    void aWidgetIsHeldByTheContract_toldOfActivity_andDisposedOnClose() {
        eval("desk.open({ id: 'a', title: 'A', widget: widget('wa') }); desk.open({ id: 'b', title: 'B', widget: widget('wb') });");
        assertEquals("opened:a@24,24:320x220 wa:on raised:a opened:b@52,52:320x220 wa:off wb:on raised:b", log());
        assertEquals("w-wa", eval("desk.pane('a').body.children[0].tag").asString());
        eval("log.length = 0; desk.close('b');");
        assertEquals("wb:off wb:disposed dissolved:float-u2 closed:b wa:on raised:a", log());
        assertEquals("A*@z1", eval("frames()").asString());
        assertFalse(eval("desk.has('b')").asBoolean());
    }

    @Test
    void theActivePanesWidgetGetsTheKeysFirst_thenTheDesk() {
        eval("""
            var w = { root: el("w-k"), key: function (ev) { log.push("w:" + ev.key); return ev.key === "ArrowUp"; } };
            desk.open({ id: "k", title: "K", widget: w }); desk.open({ id: "n", title: "N" }); log.length = 0;
            """);
        assertFalse(eval("members.desk.keyDown({ key: 'ArrowUp' })").asBoolean(), "the active pane has no widget with keys, and ArrowUp is not the desk's");
        eval("desk.raise('k'); log.length = 0;");
        assertTrue(eval("members.desk.keyDown({ key: 'ArrowUp' })").asBoolean(), "the active pane's widget took it");
        assertFalse(eval("members.desk.keyDown({ key: 'Enter' })").asBoolean(), "left by the widget, not the desk's");
        assertTrue(eval("members.desk.keyDown({ key: 'Escape' })").asBoolean(), "left by the widget: the desk closes the pane");
        assertEquals("w:ArrowUp w:Enter w:Escape dissolved:float-u1 closed:k raised:n", log());
        eval("kbLog = []; desk.dispose()");
        assertEquals("leave:desk", eval("kbLog.join(' ')").asString());
    }

    @Test
    void theCrossAndEscapeClose_EscapeOnlyTheActiveClosableOne() {
        eval("desk.open({ id: 'a', title: 'A', closable: false }); desk.open({ id: 'b', title: 'B' }); log.length = 0;");
        assertEquals("join:desk", eval("kbLog.join(' ')").asString(), "the desk joined the party as its branch's name; the convention on its floor");
        assertEquals("0", eval("String((desk.pane('b').root.listeners.keydown || []).length)").asString(), "no keydown listener on a pane: the keys come through the party");
        assertTrue(eval("members.desk.keyDown({ key: 'Escape' })").asBoolean(), "Escape, from the steward: the active pane closes");
        assertEquals("dissolved:float-u2 closed:b raised:a", log());
        eval("log.length = 0;");
        assertFalse(eval("members.desk.keyDown({ key: 'Escape' })").asBoolean(), "a pane that cannot be closed leaves Escape");
        assertEquals("", log(), "a pane that cannot be closed ignores Escape");
        assertEquals(2, eval("headOf('a').children.length").asInt(), "the icon's slot and the title: no cross on a pane that cannot be closed");
        eval("desk.open({ id: 'c', title: 'C' }); log.length = 0; headOf('c').children.slice(-1)[0].fire('click', {});   // the cross, last in the head");
        assertEquals("dissolved:float-u3 closed:c raised:a", log());
    }

    @Test
    void aDragOnTheHeadMoves_clampedToTheDesk_reportedOnceWhenTheHandLetsGo() {
        eval("desk.open({ id: 'a', title: 'A' }); log.length = 0;");
        String head = "headOf('a')";
        String lift = "var f = desk.pane('a').root; f.has('fp_held') ? 'held' + (f.has('fp_hoverable') ? '+hover' : '') : f.has('fp_hoverable') ? 'hoverable' : 'rest'";
        eval(head + ".fire('pointerenter', {});");
        assertEquals("hoverable", eval(lift).asString(), "over the head: the frame wears the interactive word");
        eval(head + ".fire('pointerdown', { button: 0, pointerId: 1, clientX: 100, clientY: 100 });");
        assertTrue(eval(head + ".has('fp_head_held')").asBoolean(), "held while the hand is on it");
        assertEquals("held", eval(lift).asString(), "in the hand: the frame wears Dragging, not Interactive");
        assertEquals("0.6", eval(head + ".prop('--extent')").asString());
        eval(head + ".fire('pointermove', { clientX: 150, clientY: 130 }); " + head + ".fire('pointermove', { clientX: 2000, clientY: 2000 });");
        assertEquals("", log(), "nothing per pixel");
        assertEquals("752,552", eval("var b = desk.pane('a').bounds(); b.x + ',' + b.y").asString(), "clamped: 48px of the head stays on the desk");
        eval(head + ".fire('pointerup', { clientX: 2000, clientY: 2000 });");
        assertEquals("moved:a@752,552", log());
        assertFalse(eval(head + ".has('fp_head_held')").asBoolean());
        assertEquals("hoverable", eval(lift).asString(), "let go, still over the head: back to hover");
        eval(head + ".fire('pointerleave', {});");
        assertEquals("rest", eval(lift).asString(), "off the head: nothing");
        assertEquals("752px", eval("desk.pane('a').root.prop('--fp-x')").asString());
    }

    @Test
    void aCancelledDragGoesBack_andADragOnTheGripResizes() {
        eval("desk.open({ id: 'a', title: 'A', x: 10, y: 20 }); log.length = 0;");
        eval("headOf('a').fire('pointerdown', { button: 0, pointerId: 1, clientX: 0, clientY: 0 }); headOf('a').fire('pointermove', { clientX: 40, clientY: 40 }); headOf('a').fire('pointercancel', {});");
        assertEquals("10,20", eval("var b = desk.pane('a').bounds(); b.x + ',' + b.y").asString());
        assertEquals("", log());
        eval("gripOf('a').fire('pointerdown', { button: 0, pointerId: 2, clientX: 0, clientY: 0 }); gripOf('a').fire('pointermove', { clientX: 80, clientY: -300 }); gripOf('a').fire('pointerup', {});");
        assertEquals("resized:a:400x96", log(), "grown inline, held at the least block");
        eval("log.length = 0; desk.pane('a').resizeTo(5000, 5000);");
        assertEquals("resized:a:800x600", log(), "no bigger than the desk");
        eval("log.length = 0; desk.pane('a').moveTo(10, 20); desk.pane('a').moveTo(-1000, -50);");
        assertEquals("moved:a@-752,0", log(), "a move that changes nothing is not reported; a move off the desk is clamped");
    }

    /** A frame with no head — a float's, whose one bar is its host's strip — is moved by a handle its holder gives it, where the holder says. */
    @Test
    void aFrameWithNoHead_isMovedByAHandleOfItsHolders_whereTheHolderSays() {
        eval("var f = desk.open({ id: 'h', head: false, closable: false, title: 'H' }); var bar = el('bar'), knob = el('knob'); f.body.appendChild(bar); bar.appendChild(knob);"
           + "f.handle(bar, function (ev) { return ev.target === bar; }); log.length = 0;");
        assertTrue(eval("f.head === null && f.root.children.length === 2 && f.root.children[0] === f.body").asBoolean(), "the body and the grip: no head");
        assertEquals("H", eval("f.root.getAttribute('aria-label') + ''").asString(), "the title, the frame's name for a reader");
        eval("var ic = el('i'); f.icon(ic);");
        assertTrue(eval("f.icon() === ic").asBoolean(), "an icon is kept, and shown nowhere");
        eval("bar.fire('pointerenter', {});");
        assertTrue(eval("f.root.has('fp_hoverable')").asBoolean(), "over the handle, the frame lifts as over a head");
        eval("bar.fire('pointerdown', { button: 0, pointerId: 1, clientX: 100, clientY: 100, target: knob });");
        assertFalse(eval("f.root.has('fp_held')").asBoolean(), "a press the holder says no to moves nothing");
        eval("bar.fire('pointerdown', { button: 0, pointerId: 1, clientX: 100, clientY: 100, target: bar });");
        assertTrue(eval("f.root.has('fp_held')").asBoolean());
        eval("bar.fire('pointermove', { clientX: 150, clientY: 130 }); bar.fire('pointerup', { type: 'pointerup', clientX: 150, clientY: 130 });");
        assertEquals("moved:h@74,54", log(), "moved by the handle, reported once");
        eval("bar.fire('pointerleave', {});");
        assertFalse(eval("f.root.has('fp_hoverable') || f.root.has('fp_held')").asBoolean());
    }

    /** A frame that is not one tab — a float of many — is never offered: its drags are not the holder's to watch. */
    @Test
    void aFrameNotOffered_isNotWatchedWhileItMoves() {
        eval("var seen = []; var d2 = new FloatLayer(branch.createBranch('d2'), { host: el('div'), onDragMove: function (p) { seen.push('move:' + p.id); }, onDragEnd: function (p) { seen.push('end:' + p.id); } });"
           + "d2.open({ id: 'one', title: 'One' }); d2.open({ id: 'many', title: 'Many', offered: false });"
           + "['one', 'many'].forEach(function (id) { var h = d2.pane(id).root.children[0]; h.fire('pointerdown', { button: 0, pointerId: 2, clientX: 10, clientY: 10, target: h }); h.fire('pointermove', { clientX: 20, clientY: 20 }); h.fire('pointerup', { type: 'pointerup', clientX: 20, clientY: 20 }); });");
        assertEquals("move:one end:one", eval("seen.join(' ')").asString());
    }

    /** The bug: the pane's branch was named with the id, so an id the party will not take as a name could not float. */
    @Test
    void aPanesBranchIsTheDesks_freshAtEveryOpening_andItsIdMayBeAnyString() {
        eval("desk.open({ id: 'picker:3', title: 'P' }); desk.open({ id: 'a b/c.d', title: 'Q' });");
        assertEquals("picker:3,a b/c.d", eval("desk.panes().join(',')").asString());
        assertEquals("float-u1,float-u2", eval("Array.from(desk.branch.kids.keys()).join(',')").asString());
        eval("desk.close('picker:3'); desk.open({ id: 'picker:3', title: 'P' }); desk.release('a b/c.d'); desk.open({ id: 'a b/c.d', title: 'Q' });");
        assertEquals("float-u3,float-u4", eval("Array.from(desk.branch.kids.keys()).join(',')").asString(), "the same ids again, each opening under a new name");
        assertEquals("picker:3,a b/c.d", eval("desk.panes().join(',')").asString());
    }

    /** Where randomUUID is missing — a page served over plain http, not localhost — the uuid is made from getRandomValues. */
    @Test
    void outsideASecureContextTheUuidIsMadeFromRandomValues() {
        String u = eval("var kept = crypto; crypto = { getRandomValues: function (a) { for (var i = 0; i < a.length; i++) a[i] = (i * 37 + 11) & 255; return a; } };"
                + "var u = FloatLayer._uuid(); crypto = kept; u").asString();
        assertTrue(u.matches("[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"), u);
    }

    @Test
    void theDeskRefusesADuplicate_andDisposeClosesEverything() {
        eval("desk.open({ id: 'a', title: 'A', widget: widget('wa') }); desk.open({ id: 'b', title: 'B' });");
        var ex = assertThrows(PolyglotException.class, () -> eval("desk.open({ id: 'a', title: 'again' })"));
        assertTrue(ex.getMessage().contains("already open"), ex.getMessage());
        eval("log.length = 0; desk.dispose();");
        assertEquals("wa:disposed dissolved:float-u1 closed:a dissolved:float-u2 closed:b dissolved:desk", log());
        assertEquals(0, eval("host.children.length").asInt());
    }
}
