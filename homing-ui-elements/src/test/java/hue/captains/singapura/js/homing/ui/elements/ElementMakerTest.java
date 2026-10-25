package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.graalvm.polyglot.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A simple component makes its own element, with the single-use maker its owner's branch issues:
 * the owner never holds a bare element. The element is the component's tag, the branch's under the
 * owner's name; a function made anywhere else is refused, whatever it would return.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class ElementMakerTest extends UiElementsJsTest {

    private static final String CORE = "/homing/js/hue/captains/singapura/js/homing/core/js/";
    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String DOM = """
        globalThis.document = {
            createElement: function (tag) {
                var classes = new Set();
                return { tagName: String(tag).toUpperCase(), disabled: false, textContent: "", attrs: {}, remove: function () {},
                    setAttribute: function (k, v) { this.attrs[k] = String(v); }, getAttribute: function (k) { return k in this.attrs ? this.attrs[k] : null; },
                    classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                    addEventListener: function () {} };
            }
        };
        var css = { cls: function (name) { return { name: name }; }, addClass: function (e, h) { e.classList.add(h.name); },
                    removeClass: function (e, h) { e.classList.remove(h.name); }, size: function () {}, extent: function () {}, aspect: function () {} };
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval(Source.newBuilder("js", DOM, "dom.js").buildLiteral());
        loadModule(CORE + "DomOpsPartyBaseModule.js");
        loadModule(CORE + "DomOpsPartyModule.js");
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet'; if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };"); loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");   // the look, the design's: beside the elements, not one of them
        for (String m : new String[] {"SimpleComponentModule.js", "ButtonBehaviorModule.js", "ButtonModule.js", "PlainButtonModule.js"})
            loadModule(DIR + m);
        js.eval("js", """
                var owner = { toString: function () { return 'owner'; } };
                var branch = domOpsParty.createBranch('page');
                branch.activate(owner);
                function fails(f) { try { f(); return null; } catch (e) { return e.message; } }
                """);
    }

    private String str(String expr) { return js.eval("js", "String(" + expr + ")").asString(); }

    @Test
    void aSimpleComponent_makesItsOwnElement_withItsOwnersMaker() {
        js.eval("js", "var go = new PlainButton(branch.elementMaker('go'), { label: 'Go' });");
        assertEquals("BUTTON", str("go.el.tagName"), "its own tag, made by the component");
        assertEquals("true", str("branch.getElement('go') === go.el"), "the branch's, under the owner's name");
        assertEquals("Go", str("go.label"));
    }

    @Test
    void aFunctionMadeElsewhere_isRefused_andAMakerServesOnce() {
        assertTrue(str("fails(() => new PlainButton(function (tag) { return document.createElement(tag); }, {}))").contains("no other function"));
        js.eval("js", "var make = branch.elementMaker('once'); new PlainButton(make, {});");
        assertTrue(str("fails(() => new PlainButton(make, {}))").contains("spent"));
    }
}
