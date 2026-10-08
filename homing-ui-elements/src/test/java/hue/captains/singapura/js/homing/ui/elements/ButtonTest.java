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
 * A button as a simple component: its state kept by the button and changed only by its setters,
 * its look swapped by its UI for the whole state point at once with the reflection written, its
 * behaviour told after; its degrees and its words; its castings, refused when the taxonomy has no
 * slot for them.
 */
class ButtonTest extends JsModuleTestBase {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String CLASSES = """
        ["el_button", "el_button_on", "el_button_off", "el_button_plain", "el_button_primary", "el_button_secondary",
         "el_button_danger", "el_button_warning", "el_button_success"].forEach(function (c) { globalThis[c] = c; });
        """;

    private static final String SHIM = """
        var log = [], ops = [];
        function el(tag) {
            var classes = new Set(), listeners = {};
            return { tagName: tag.toUpperCase(), type: "", disabled: false, textContent: "",
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                classes: function () { return Array.from(classes).sort().join(" "); },
                addEventListener: function (t, fn) { (listeners[t] = listeners[t] || []).push(fn); },
                click: function () { (listeners.click || []).forEach(function (fn) { fn({}); }); } };
        }
        var CssClassManagerInstance = {
            addClass: function (e, c) { ops.push("+" + c); e.classList.add(c); },
            removeClass: function (e, c) { ops.push("-" + c); e.classList.remove(c); },
            size: function (e, s) { e.size = s; }, extent: function (e, t) { e.extent = t; }, aspect: function (e, a) { e.aspect = a; } };
        var go = new PrimaryButton(el("button"), { label: "Save", onClick: function () { log.push("saved"); } }, ROLE.GO);
        var plain = new PlainButton(el("button"), { label: "Open", size: -1 });
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", CLASSES);
        for (String m : new String[] {"ComponentUiModule.js", "SimpleComponentModule.js", "ButtonBehaviorModule.js", "ButtonModule.js",
                                      "PlainButtonModule.js", "PrimaryButtonModule.js", "DangerButtonModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String str(String src) { return eval("String(" + src + ")").asString(); }

    @Test
    void madeAtRest_wearingItsLookAndTheRestOfEachAxis() {
        assertEquals("el_button el_button_on el_button_primary", str("go.el.classes()"));
        assertEquals("true", str("go.enabled"));
        assertEquals("false", str("go.el.disabled"));
        assertEquals("button", str("go.el.type"), "a button inside a form does not submit it");
        assertEquals("Save", str("go.label"));
        assertEquals("go", str("go.role"));
        assertEquals("standalone", str("plain.role"), "Standalone unless given");
    }

    @Test
    void aState_isSwappedWhole_reflected_andTheSameValueAgainDoesNothing() {
        eval("ops = []; go.enabled = false;");
        assertEquals("el_button el_button_off el_button_primary", str("go.el.classes()"));
        assertEquals("true", str("go.el.disabled"), "the reflection written with the look");
        assertEquals("-el_button_on,+el_button,+el_button_primary,+el_button_off", str("ops"), "what the point leaves comes off; the point is put on");
        eval("ops = []; go.enabled = false;");
        assertEquals("", str("ops"), "the same value again does nothing");
        eval("go.enabled = true;");
        assertEquals("el_button el_button_on el_button_primary", str("go.el.classes()"));
        assertEquals("false", str("go.el.disabled"));
    }

    @Test
    void aPressActs_onlyWhileEnabled() {
        eval("go.el.click(); go.enabled = false; go.el.click(); go.enabled = true; go.el.click();");
        assertEquals("saved,saved", str("log"));
    }

    @Test
    void theBehaviour_isToldAfterTheStateIsShown() {
        eval("""
            class Probe { constructor(el, c) { this.c = c; } changed(axis, v) { log.push(axis + "=" + v + " " + this.c.el.classes()); } }
            class Probed extends PlainButton { static BEHAVIOR = Probe; }
            var probed = new Probed(el("button"), {});
            probed.enabled = false;
            """);
        assertEquals("enabled=false el_button el_button_off el_button_plain", str("log"));
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
    void castings_onlyThoseTheTaxonomyHasASlotFor() {
        assertTrue(eval("new PlainButton(el('button'), {}, ROLE.CHOICE).role === ROLE.CHOICE").asBoolean(), "a picker's choice");
        var e = assertThrows(PolyglotException.class, () -> eval("new DangerButton(el('button'), {}, ROLE.GO)"));
        assertTrue(e.getMessage().contains("is never cast as go: only as standalone"), e.getMessage());
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), {}, 'go')"), "a role is one of ROLE");
    }

    @Test
    void refused_theBase_anotherTag_aValueOffItsAxis() {
        assertThrows(PolyglotException.class, () -> eval("new Button(el('button'), {})"), "a button is one of its leaves");
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('div'), {})"), "the element minted with its tag");
        assertThrows(PolyglotException.class, () -> eval("new PlainButton(el('button'), { enabled: 'yes' })"), "enabled is true or false");
    }
}
