package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The switch (RFC 0067, appendix Two Classes per Leaf, phase 5): a component on its own button, its parts the toggle's
 * groove and knob - parts of their own, apart from the slider's - each wearing its casting's class; its state riding on
 * its button, a press turning it and its groove with it, dressed as its Commit.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class SwitchTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), listeners = {}, attrs = {};
            return { tagName: tag.toUpperCase(), type: "", disabled: false, textContent: "", children: [],
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return k in attrs ? attrs[k] : null; },
                removeAttribute: function (k) { delete attrs[k]; },
                appendChild: function (c) { this.children.push(c); },
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                has: function (c) { return classes.has(c); },
                addEventListener: function (t, fn) { (listeners[t] = listeners[t] || []).push(fn); },
                click: function () { (listeners.click || []).forEach(function (fn) { fn({ type: "click" }); }); } };
        }
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) { return { name: name, createElement: function (n, tag) { return el(tag); },
                                             elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                                             activate: function () {} }; }
        var css = { cls: function (name) { return { name: name }; }, addClass: function (e, h) { e.classList.add(h.name || h); },
                    removeClass: function (e, h) { e.classList.remove(h.name || h); } };
        var el_switch = 'el_switch', el_switch_groove = 'el_switch_groove', el_switch_knob = 'el_switch_knob';
        class Told { constructor(tag, types) { this.tag = tag; this.types = types; }
                     canHandle(t) { return this.types.indexOf(t) >= 0; }
                     handle(e) { log.push(this.tag + (e instanceof StateChange ? " was " + e.previous + (e.raw ? " by a press" : " by a setter") : "")); } }
        var sw = new Switch(fakeBranch("dark"), { text: "Off" });
        var groove = sw.root.children[0], knob = groove.children[0], word = sw.root.children[1];
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet', ICONS = {}; if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };");
        loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");
        for (String m : new String[] {"SimpleComponentModule.js", "MarkBehaviorModule.js", "InputBehaviorsModule.js", "ToggleGrooveModule.js",
                                      "ToggleKnobModule.js", "SwitchModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private String str(String src) { return js.eval("js", "String(" + src + ")").asString(); }
    private boolean is(String src) { return js.eval("js", src).asBoolean(); }

    @Test
    void aSwitch_onItsOwnButton_itsGrooveAndKnobTheTogglesParts() {
        assertTrue(is("sw.root.has('styled-switch') && sw.root.has('el_switch')"));
        assertEquals("BUTTON button switch false", str("sw.root.tagName + ' ' + sw.root.type + ' ' + sw.root.getAttribute('role') + ' ' + sw.root.getAttribute('aria-checked')"));
        assertTrue(is("groove.has('styled-toggle-groove_span') && knob.has('styled-toggle-knob_thumb')"),
                "the toggle's groove as its span, its knob as its thumb: parts of their own, apart from the slider's");
        assertEquals("Off", str("word.textContent"));
    }

    @Test
    void aPress_turnsIt_itsGrooveWithIt_dressedAsItsCommit() {
        js.eval("js", "sw.setOnCheckedChanged(new Told('turned', [STATE.CHECKED.changed])); sw.setOnCommit(new Told('done', [EVENT.COMMIT])); sw.root.click();");
        assertTrue(is("sw.checked && sw.root.getAttribute('aria-checked') === 'true'"));
        assertTrue(is("sw.root.has('styled-switch--checked') && groove.has('styled-toggle-groove_span--checked')"), "its groove turned with it");
        assertEquals("turned was false by a press,done", str("log"));
        js.eval("js", "sw.checked = false;");
        assertFalse(is("groove.has('styled-toggle-groove_span--checked')"), "a setter turns its groove too");
        js.eval("js", "log = []; sw.enabled = false; sw.root.click();");
        assertTrue(is("sw.root.disabled && sw.root.has('styled-switch--disabled')"));
        assertEquals("", str("log"), "off, a press does nothing");
    }
}
