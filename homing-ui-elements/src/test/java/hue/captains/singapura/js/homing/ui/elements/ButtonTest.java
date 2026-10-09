package hue.captains.singapura.js.homing.ui.elements;

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
 * A button as a simple component on the new manager: its state kept by the button and changed only
 * by its setters, which write the reflection and nothing else; its look the semantic classes of its
 * casting, worn once - a class for each target, a treatment for each state its component is treated
 * in - named, never a design word; its behaviour told after; its degrees and its words; its
 * castings, refused when the taxonomy has no slot for them.
 */
class ButtonTest extends JsModuleTestBase {

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
        var go = new PrimaryButton(el("button"), { label: "Save", onClick: function () { log.push("saved"); } }, ROLE.GO);
        var plain = new PlainButton(el("button"), { label: "Open", size: -1 });
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var look_sheet = 'look-sheet';");
        // the icon an icon button holds: the real Icon, its classes handles as the shim's css takes them
        js.eval("js", "var ic_base = { name: 'ic_base' };");
        for (var w : hue.captains.singapura.js.homing.ui.icons.IconStyles.worded())
            js.eval("js", "var " + w.getClass().getSimpleName() + " = { name: '" + w.getClass().getSimpleName() + "' };");
        js.eval("js", String.join("\n", hue.captains.singapura.js.homing.ui.icons.IconsModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        for (String m : new String[] {"LookModule.js", "SimpleComponentModule.js", "ButtonBehaviorModule.js", "ButtonModule.js",
                                      "PlainButtonModule.js", "PrimaryButtonModule.js", "DangerButtonModule.js",
                                      "ToggleButtonBehaviorModule.js", "ToggleButtonModule.js", "CloseButtonModule.js", "IconButtonModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String str(String src) { return eval("String(" + src + ")").asString(); }
    private boolean has(String el, String cls) { return eval(el + ".el.classList.contains('" + cls + "')").asBoolean(); }

    @Test
    void wornAtOnce_itsCastingsLookOnEveryTarget_andItsComponentsTreatments() {
        assertTrue(has("go", "primary-button_go__color-background"), "the casting's look: the primary button as Go");
        assertTrue(has("go", "primary-button_go__type-weight"));
        assertTrue(has("go", "primary-button__effect-opacity--disabled"), "the treatment: the component's, common to every role");
        assertTrue(has("go", "primary-button__shape-shadow--hover"));
        assertFalse(has("go", "primary-button__color-background"), "not the button on its own: it is cast as Go");
        int targets = eval("LOOK_TARGETS.length").asInt(), treated = eval("TREATED['primary-button'].length").asInt();
        assertEquals(targets + treated, eval("go.el.classes().length").asInt(), "a class for each target, a treatment for each the sheet treats it in");
        assertFalse(eval("go.el.classes().some(function (c) { return c.indexOf('el-button') === 0 || c.indexOf('el_button') === 0; })").asBoolean(),
                "no design word: the look is named by the casting");
        assertTrue(has("plain", "plain-button__color-background"), "Standalone unless given");
        assertEquals("standalone", str("plain.role"));
        assertEquals("button", str("go.el.type"), "a button inside a form does not submit it");
        assertEquals("Save", str("go.label"));
    }

    @Test
    void aState_writesItsReflection_andNoClassChanges() {
        eval("ops = []; go.enabled = false;");
        assertEquals("", str("ops"), "the treatment classes are worn already: the reflection shows them");
        assertEquals("true", str("go.el.disabled"));
        eval("go.enabled = true;");
        assertEquals("false", str("go.el.disabled"));
        eval("ops = []; go.enabled = true;");
        assertEquals("", str("ops"), "the same value again does nothing");
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
        eval("var choice = new PlainButton(el('button'), {}, ROLE.CHOICE);");
        assertTrue(has("choice", "plain-button_choice__color-background"), "a picker's choice");
        var e = assertThrows(PolyglotException.class, () -> eval("new DangerButton(el('button'), {}, ROLE.GO)"));
        assertTrue(e.getMessage().contains("is never cast as go: only as standalone"), e.getMessage());
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), {}, 'go')"), "a role is one of ROLE");
        assertEquals("plain-button", str("new Look(PlainButton, { token: 'window', functional: true }).casting"),
                "a functional role only places its part: the leaf on its own");
    }

    /** A toggle: a press turns it over while enabled and says so; on, it wears the press its style holds. */
    @Test
    void aToggle_isTurnedOverByAPress_saysSo_andWearsItsPressedTreatments() {
        eval("var said = []; var t = new ToggleButton(el('button'), { label: 'Bold', onToggle: function (on) { said.push(on); } });");
        assertEquals("false", str("t.el.getAttribute('aria-pressed')"), "off at rest, and it says so");
        eval("t.el.click();");
        assertEquals("true", str("t.on"));
        assertEquals("true", str("t.el.getAttribute('aria-pressed')"), "the reflection the pressed treatments are shown by");
        eval("t.enabled = false; t.el.click(); t.enabled = true; t.on = false;");
        assertEquals("true", str("said"), "a press while off does nothing; a value set in code is not a press");
        assertEquals("false", str("t.el.getAttribute('aria-pressed')"));
        assertTrue(has("t", "toggle-button__shape-shadow--pressed"), "the style's press, held while on");
        assertTrue(has("t", "toggle-button__color-background--pressed"), "the palette's tint");
        assertTrue(has("t", "toggle-button__shape-shadow--hover"), "and every button's treatments besides");
        assertThrows(PolyglotException.class, () -> eval("new ToggleButton(el('button'), { on: 'yes' })"), "on is true or false");
    }

    /** The close button: a mark, the style's own cross, never words; its words its name. */
    @Test
    void aCloseButton_isAMark_namedForWhoeverCannotSeeIt() {
        eval("var c = new CloseButton(el('button'), {});");
        assertEquals("Close", str("c.el.getAttribute('aria-label')"), "Close unless given");
        assertEquals("", str("c.el.textContent"), "no words on its face");
        assertTrue(has("c", "close-button__type-glyph"), "the mark: the glyph its style draws on ::before");
        assertTrue(has("c", "close-button__shape-border"), "its frame: Base's bare one, unless its style frames it");
        assertEquals("Close", str("c.el.getAttribute('title')"), "and shown to whoever points at it: the browser's own tooltip");
        eval("c.label = 'Close the dialog'");
        assertEquals("Close the dialog", str("c.label"));
        assertEquals("Close the dialog", str("c.el.getAttribute('title')"), "the tooltip follows its name");
        eval("var closing = new CloseButton(el('button'), {}, ROLE.CLOSE);");
        assertTrue(has("closing", "close-button_close__shape-border"), "cast as what closes its owner");
    }

    /** The icon button: holds whatever icon it is handed - which icon is the icon's business - and is named for whoever cannot see it. */
    @Test
    void anIconButton_holdsWhicheverIconItIsHanded_andNeverAsksItsName() {
        eval("var plus = new Icon(el('span'), { name: 'add' }); var b = new IconButton(el('button'), { icon: plus, label: 'Add a tab' });");
        assertTrue(eval("b.icon === plus && b.el.kids[0] === plus.el").asBoolean(), "its face: the icon, in it");
        assertEquals("Add a tab", str("b.el.getAttribute('aria-label')"));
        assertEquals("Add a tab", str("b.el.getAttribute('title')"), "its tooltip: the browser's own");
        assertEquals("", str("b.el.textContent"), "no words on its face");
        eval("b.icon.set('remove')");
        assertEquals("remove", str("b.icon.name()"), "another icon is the icon's to show; the button is not asked");
        assertTrue(has("b", "icon-button__shape-border"));
        assertThrows(PolyglotException.class, () -> eval("new IconButton(el('button'), { label: 'Add' })"), "its face is an icon");
        assertThrows(PolyglotException.class, () -> eval("new IconButton(el('button'), { icon: el('span') })"), "an Icon, not any element");
    }

    @Test
    void refused_theBase_anotherTag_aValueOffItsAxis() {
        assertThrows(PolyglotException.class, () -> eval("new Button(el('button'), {})"), "a button is one of its leaves");
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('div'), {})"), "the element minted with its tag");
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), { enabled: 'yes' })"), "enabled is true or false");
    }
}
