package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.IconsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A button as a simple component on the new manager: its state kept by the button and changed only
 * by its setters, which write the reflection and nothing else; its look the semantic classes of its
 * casting, worn once - a class for each target, a treatment for each state its component is treated
 * in - named, never a design word; its behaviour told after; its degrees and its words; its
 * castings, refused when the taxonomy has no slot for them.
 */
class ButtonTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        var log = [], ops = [];
        function el(tag) {
            var classes = new Set(), listeners = {};
            return { tagName: tag.toUpperCase(), type: "", disabled: false, textContent: "", attrs: {},
                setAttribute: function (k, v) { this.attrs[k] = String(v); }, kids: [], appendChild: function (c) { this.kids.push(c); }, getAttribute: function (k) { return k in this.attrs ? this.attrs[k] : null; },
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                classes: function () { return Array.from(classes); },
                addEventListener: function (t, fn) { (listeners[t] = listeners[t] || []).push(fn); },
                click: function () { (listeners.click || []).forEach(function (fn) { fn({}); }); } };
        }
        var css = {
            cls: function (name) { return { name: name }; },
            addClass: function (e, h) { ops.push("+" + h.name); e.classList.add(h.name); },
            removeClass: function (e, h) { ops.push("-" + h.name); e.classList.remove(h.name); },
            size: function (e, s) { e.size = s; }, extent: function (e, t) { e.extent = t; }, aspect: function (e, a) { e.aspect = a; } };
        // handlers: objects of classes, each saying what it handles
        class Saved { canHandle(t) { return t === EVENT.CLICK; } handle(e) { log.push("saved"); } }
        class Told { constructor(tag) { this.tag = tag; }
                     canHandle(t) { return t === EVENT.CLICK || t === STATE.ENABLED.changed || t === STATE.PRESSED.changed; }
                     handle(e) { log.push(this.tag + ":" + e.type + (e instanceof StateChange ? " was " + e.previous + (e.raw ? " by a press" : " by a setter") : "")); } }
        var go = new PrimaryButton(PrimaryButtonFactory.create({ createElement: function (n, tag) { return el(tag); } }, "go"), { label: "Save" });
        go.setOnClick(new Saved());
        var plain = new PlainButton(el("button"), { label: "Open", size: -1 });
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet'; if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };"); loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        // the icon an icon button holds: the real Icon, its classes handles as the shim's css takes them
        js.eval("js", "var ic_base = { name: 'ic_base' };");   // the icon's box
        js.eval("js", String.join("\n", hue.captains.singapura.js.homing.ui.look.IconsModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        js.eval("js", "var el_icon_button = { name: 'el_icon_button' };");   // the icon button's face, laid out
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");   // the look, the design's: beside the elements, not one of them
        for (String m : new String[] {"SimpleComponentModule.js", "ButtonBehaviorModule.js", "ButtonModule.js",
                                      "PlainButtonModule.js", "PrimaryButtonModule.js", "DangerButtonModule.js",
                                      "ToggleButtonBehaviorModule.js", "ToggleButtonModule.js", "CloseButtonModule.js", "IconButtonModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String str(String src) { return eval("String(" + src + ")").asString(); }
    private boolean has(String el, String cls) { return eval(el + ".el.classList.contains('" + cls + "')").asBoolean(); }

    @Test
    void wornAtOnce_itsLeafsClassAlone() {
        assertEquals("styled-plain-button", str("plain.el.classes()"), "its leaf's style-aware class, and nothing else: no class per target");
        assertEquals("standalone", str("plain.role"), "Standalone unless given");
        assertEquals("button", str("go.el.type"), "a button inside a form does not submit it");
        assertEquals("Save", str("go.label"));
    }

    @Test
    void aState_writesItsReflection_andHoldsItsClassWhileItHolds() {
        eval("plain.enabled = false;");
        assertEquals("true", str("plain.el.disabled"));
        assertTrue(has("plain", "styled-plain-button--disabled"), "off, its state's class (D12, D21)");
        eval("plain.enabled = true;");
        assertEquals("false", str("plain.el.disabled"));
        assertFalse(has("plain", "styled-plain-button--disabled"), "on again, the rest: no class");
    }

    @Test
    void aPressActs_onlyWhileEnabled() {
        eval("go.el.click(); go.enabled = false; go.el.click(); go.enabled = true; go.el.click();");
        assertEquals("saved,saved", str("log"));
    }

    @Test
    void theBehaviour_isToldAfterTheReflectionIsWritten() {
        eval("""
            class Probe { constructor(el, c) { this.c = c; } changed(axis, v) { log.push(axis + "=" + v + " disabled:" + this.c.el.disabled); } }
            class Probed extends PlainButton { static BEHAVIOR = Probe; }
            var probed = new Probed(el("button"), {});
            probed.enabled = false;
            """);
        assertEquals("enabled=false disabled:true", str("log"));
    }

    @Test
    void degrees_clampedAndWrittenOnlyAwayFromRest_andOnlyWhereTheLeafHasThem() {
        assertEquals("-1", str("plain.size"));
        assertEquals("-1", str("plain.el.size"));
        eval("plain.size = 0");
        assertEquals("null", str("plain.el.size"), "at rest it writes nothing");
        eval("var d = new DangerButton(el('button'), { extent: 3 })");
        assertEquals("1", str("d.extent"), "clamped");
        assertEquals("null", str("d.el.extent"), "1 is the extent's rest");
        eval("d.extent = -0.4");
        assertEquals("-0.4", str("d.el.extent"));
        assertFalse(eval("'extent' in plain").asBoolean(), "plain is no semantic word: it has no extent");
    }

    @Test
    void words() {
        eval("plain.label = 'Open it'");
        assertEquals("Open it", str("plain.el.textContent"));
        eval("plain.label = null");
        assertEquals("", str("plain.label"));
    }

    @Test
    void castings_onlyThoseTheTaxonomyHasASlotFor_aFunctionalRoleLookingAsTheLeafOnItsOwn() {
        var e = assertThrows(PolyglotException.class, () -> eval("new DangerButton(el('button'), {}, ROLE.GO)"));
        assertTrue(e.getMessage().contains("is never cast as go: only as standalone"), e.getMessage());
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), {}, 'go')"), "a role is one of ROLE");
        assertEquals("plain-button", str("new Look(PlainButton, { token: 'window', functional: true }).token"),
                "a functional role only places its part: the leaf on its own");
    }

    /** A toggle: a press turns it over while enabled and says so; on, it wears the press its style holds. */
    @Test
    void aToggle_isTurnedOverByAPress_saysSo_andWearsItsPressedTreatments() {
        eval("var t = new ToggleButton(el('button'), { label: 'Bold' }); t.setOnPressedChanged(new Told('t')); t.setOnClick(new Told('t'));");
        assertEquals("false", str("t.el.getAttribute('aria-pressed')"), "not pressed at rest, and it says so");
        eval("t.el.click();");
        assertEquals("true", str("t.pressed"));
        assertEquals("true", str("t.el.getAttribute('aria-pressed')"), "its reflection");
        assertTrue(has("t", "styled-toggle-button--pressed"), "on, its state's class, while it holds (D12)");
        assertEquals("t:pressed-changed was false by a press,t:click", str("log"), "turned over by the press that made it, then the press itself");
        eval("log.length = 0; t.enabled = false; t.el.click(); t.enabled = true; t.pressed = false;");
        assertEquals("t:pressed-changed was true by a setter", str("log"), "a press while off does nothing; a setter's change is fired too, with no press");
        assertEquals("false", str("t.el.getAttribute('aria-pressed')"));
        assertFalse(has("t", "styled-toggle-button--pressed"), "off again: the rest, no class");
        assertThrows(PolyglotException.class, () -> eval("new ToggleButton(el('button'), { pressed: 'yes' })"), "pressed is true or false");
    }

    /** The close button: its face the close icon - the style's own cross - never words; its words its name. */
    @Test
    void aCloseButton_isAMark_namedForWhoeverCannotSeeIt() {
        eval("var c = new CloseButton(el('button'), { face: function (tag) { return el(tag); } });");
        assertTrue(eval("c.face.icon === ICON.CLOSE_ICON && c.el.kids[0] === c.face.el && c.face.el.classList.contains('styled-close-icon')").asBoolean(),
                "its face the close icon, in it, its look its own");
        assertThrows(PolyglotException.class, () -> eval("new CloseButton(el('button'), {})"), "its face is made on the room its owner gives it");
        assertEquals("Close", str("c.el.getAttribute('aria-label')"), "Close unless given");
        assertEquals("", str("c.el.textContent"), "no words on its face");
        assertTrue(has("c", "styled-close-button"), "its leaf's class");
        assertEquals("Close", str("c.el.getAttribute('title')"), "and shown to whoever points at it: the browser's own tooltip");
        eval("c.label = 'Close the dialog'");
        assertEquals("Close the dialog", str("c.label"));
        assertEquals("Close the dialog", str("c.el.getAttribute('title')"), "the tooltip follows its name");
    }

    /** The icon button: holds whatever icon it is handed - which icon is the icon's business - and is named for whoever cannot see it. */
    @Test
    void anIconButton_holdsWhicheverIconItIsHanded_andNeverAsksItsName() {
        eval("var plus = new Icon(el('span'), ICON.ADD_ICON, ROLE.FACE); var b = new IconButton(el('button'), { icon: plus, label: 'Add a tab' });");
        assertTrue(eval("b.icon === plus && b.el.kids[0] === plus.el").asBoolean(), "its face: the icon, in it");
        assertEquals("Add a tab", str("b.el.getAttribute('aria-label')"));
        assertEquals("Add a tab", str("b.el.getAttribute('title')"), "its tooltip: the browser's own");
        assertEquals("", str("b.el.textContent"), "no words on its face");
        assertTrue(eval("b.icon.icon === ICON.ADD_ICON").asBoolean(), "which icon is the icon's: the button is not asked");
        assertTrue(has("b", "styled-icon-button"));
        assertTrue(has("b", "el_icon_button"), "its face laid out: the icon centred in its box, whatever the icon");
        assertThrows(PolyglotException.class, () -> eval("new IconButton(el('button'), { label: 'Add' })"), "its face is an icon");
        assertThrows(PolyglotException.class, () -> eval("new IconButton(el('button'), { icon: el('span') })"), "an Icon, not any element");
    }

    /** Its companion makes the element it takes, and nothing else, on its owner's branch under its owner's name. */
    @Test
    void itsCompanion_makesTheElementItTakes() {
        eval("var made = []; var branch = { createElement: function (n, tag) { made.push(n + ':' + tag); return el(tag); } };"
           + "var b = new CloseButton(CloseButtonFactory.create(branch, 'x'), { face: function (tag) { made.push('face:' + tag); return el(tag); } });");
        assertEquals("x:button,face:span", str("made"), "its element, then its face's");
        assertEquals("button", str("CloseButtonFactory.tag"));
        assertTrue(eval("Object.isFrozen(PlainButtonFactory) && PlainButtonFactory instanceof SimpleComponentFactory").asBoolean(), "a singleton, beside its class");
    }

    /** A handler: asked first what it handles, an object of a class, once and for good - and an event dressed, carrying the DOM's. */
    @Test
    void handlers_saySoFirst_areObjectsOfClasses_andStay() {
        eval("var b = new PlainButton(el('button'), { label: 'Go' });");
        var e = assertThrows(PolyglotException.class, () -> eval("b.setOnClick({ canHandle: function () { return false; }, handle: function () {} })"));
        assertTrue(e.getMessage().contains("says it handles click"), e.getMessage());
        e = assertThrows(PolyglotException.class, () -> eval("b.setOnClick({ canHandle: function () { return true; }, handle: function () {} })"));
        assertTrue(e.getMessage().contains("never a closure"), "a bare object of functions: " + e.getMessage());
        assertThrows(PolyglotException.class, () -> eval("b.setOnClick(function () {})"), "a function");
        eval("b.setOnClick(new Told('b'))");
        e = assertThrows(PolyglotException.class, () -> eval("b.setOnClick(new Told('again'))"));
        assertTrue(e.getMessage().contains("set once, it stays"), e.getMessage());
        e = assertThrows(PolyglotException.class, () -> eval("b._setOn(STATE.PRESSED.changed, new Told('b'))"));
        assertTrue(e.getMessage().contains("offers no pressed-changed"), "a plain button has no pressed: " + e.getMessage());
        e = assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), { onClick: function () {} })"));
        assertTrue(e.getMessage().contains("a handler is set by its setter"), e.getMessage());
        eval("var raw = { x: 3 }; var got; class Kept { canHandle(t) { return t === EVENT.CLICK; } handle(ev) { got = ev; } }"
           + "var k = new PlainButton(el('button'), {}); k.setOnClick(new Kept());");
        eval("k._fire(EVENT.CLICK, raw)");
        assertTrue(eval("got instanceof ComponentEvent && got.type === EVENT.CLICK && got.source === k && got.raw === raw && Object.isFrozen(got)").asBoolean(),
                "typed, its component, the raw event carried");
    }

    /** A state's change is fired from a setter too, with the previous value and no DOM event; the same value again is no change. */
    @Test
    void aSettersChange_isFired_withWhatItWas() {
        eval("var b = new PlainButton(el('button'), {}); b.setOnEnabledChanged(new Told('b')); b.enabled = false; b.enabled = false; b.enabled = true;");
        assertEquals("b:enabled-changed was true by a setter,b:enabled-changed was false by a setter", str("log"));
    }

    @Test
    void refused_theBase_anotherTag_aValueOffItsAxis() {
        assertThrows(PolyglotException.class, () -> eval("new Button(el('button'), {})"), "a button is one of its leaves");
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('div'), {})"), "the element minted with its tag");
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), { enabled: 'yes' })"), "enabled is true or false");
    }
}
