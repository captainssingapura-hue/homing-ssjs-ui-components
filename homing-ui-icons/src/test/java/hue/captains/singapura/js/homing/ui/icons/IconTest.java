package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import hue.captains.singapura.js.homing.ui.look.IconsModule;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The Icon component over a fake element: the box and the word's class it wears and takes off, the names it refuses, the table it reads. */
class IconTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/icons/";

    private static final String SHIM = """
        function el() {
            var classes = new Set(), attrs = {};
            return { classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); },
                                  remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); },
                                  contains: function (c) { return classes.has(c); }, [Symbol.iterator]: function () { return classes.values(); } },
                     setAttribute: function (k, v) { attrs[k] = String(v); }, removeAttribute: function (k) { delete attrs[k]; },
                     has: function (c) { return classes.has(c); }, attr: function (k) { return attrs[k]; }, classes: function () { return Array.from(classes).sort().join(' '); } };
        }
        var css = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    hasClass: function (e, c) { return e.classList.contains(c); } };
        var CssClassManagerInstance = css, style_sheet = 'style-sheet', palette_sheet = 'palette-sheet';
        var ic_base = "ic_base", lexicon_sheet = {};
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", SHIM);
        // the generated table itself, the sheet it imports a constant in the shim
        js.eval("js", String.join("\n", IconsModule.INSTANCE.selfContent(null)));
        // its look the house's: the castings, the trees of states and events, the manifest, the leaf's classes and the look
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");
        loadModule(P + "IconModule.js");
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void itWearsTheBaseAndTheWordsClass_setChangesTheWord_clearLeavesTheBase() {
        eval("var e = el(); var icon = new Icon(e, { name: 'check' });");
        assertEquals("ic_base styled-icon word-check", eval("e.classes()").asString(), "its box, its casting's class and its word's");
        assertEquals("true", eval("e.attr('aria-hidden')").asString(), "a mark is not a label");
        assertEquals("check", eval("icon.name()").asString());
        eval("icon.set('rotate');");
        assertEquals("ic_base styled-icon word-rotate", eval("e.classes()").asString(), "the old word off, the new on");
        eval("icon.set('rotate');");
        assertEquals("ic_base styled-icon word-rotate", eval("e.classes()").asString(), "the same word again: nothing changes");
        eval("icon.clear();");
        assertEquals("ic_base styled-icon", eval("e.classes()").asString(), "blank at its width");
        assertTrue(eval("icon.name() === null").asBoolean());
        eval("new Icon(el())");
        assertEquals("ic_base styled-icon_keys word-check", eval("var k = el(); new Icon(k, { name: 'check' }, ROLE.KEYS); k.classes()").asString(),
                "cast by its owner as the keys mark");
        eval("icon.set('pin'); icon.dispose();");
        assertEquals("", eval("e.classes()").asString(), "disposed: nothing of the icon's left on the caller's element");
        assertTrue(eval("e.attr('aria-hidden') === undefined").asBoolean());
    }

    @Test
    void aWordTheLexiconLacksIsRefused_andTheTableIsTheLexicon() {
        var ex = assertThrows(PolyglotException.class, () -> eval("new Icon(el(), { name: 'sparkle' })"));
        assertTrue(ex.getMessage().contains("no icon word 'sparkle'"), ex.getMessage());
        assertEquals(String.join(",", IconsModule.INSTANCE.names()), eval("Icon.NAMES.join(',')").asString());
        assertTrue(eval("Icon.has('disclose') && !Icon.has('toString')").asBoolean());
        assertFalse(eval("Icon.has('sparkle')").asBoolean());
        assertEquals("span", eval("Icon.TAG").asString());
        assertThrows(PolyglotException.class, () -> eval("new Icon(null)"));
    }
}
