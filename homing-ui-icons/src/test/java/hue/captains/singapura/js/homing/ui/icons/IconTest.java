package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.IconsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
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
 * The Icon component over a fake element (RFC 0067, D52): the icon handed in, one of ICON - each a leaf of the Icon
 * branch - its box and its casting's class worn and taken off; anything not an icon of the instance's refused; a token
 * looked up for a caller handed one as data. Over the jar's own instance: the house's fifteen icons.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class IconTest extends UiIconsJsTest {

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
        var ic_base = "ic_base";
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", SHIM);
        // the instance's icons, and its look: the castings, the trees of states and events, the manifest, the leaf's classes and the look
        js.eval("js", String.join("\n", IconsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");
        loadModule(P + "IconModule.js");
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void itWearsItsBoxAndItsCastingsClass_aRoleThatPlacesItTheSame_disposedNothingLeft() {
        eval("var e = el(); var icon = new Icon(e, ICON.CHECK_ICON);");
        assertEquals("ic_base styled-check-icon", eval("e.classes()").asString(), "its box and its casting's class: its picture its look");
        assertEquals("true", eval("e.attr('aria-hidden')").asString(), "an icon is not a label");
        assertTrue(eval("icon.icon === ICON.CHECK_ICON").asBoolean());
        eval("var f = el(); new Icon(f, ICON.CLOSE_ICON, ROLE.FACE);");
        assertEquals("ic_base styled-close-icon", eval("f.classes()").asString(), "a role an icon plays places it: its look its own, whatever the role");
        eval("icon.dispose();");
        assertEquals("", eval("e.classes()").asString(), "disposed: nothing of the icon's left on the caller's element");
        assertTrue(eval("e.attr('aria-hidden') === undefined").asBoolean());
    }

    @Test
    void anythingNotAnIconOfTheInstancesIsRefused_aTokenLookedUp() {
        var notOne = assertThrows(PolyglotException.class, () -> eval("new Icon(el(), 'check')"));
        assertTrue(notOne.getMessage().contains("an icon is one of ICON"), notOne.getMessage());
        assertTrue(eval("Icon.of('disclose-icon') === ICON.DISCLOSE_ICON").asBoolean());
        var none = assertThrows(PolyglotException.class, () -> eval("Icon.of('sparkle-icon')"));
        assertTrue(none.getMessage().contains("no icon 'sparkle-icon'"), none.getMessage());
        assertEquals(15, eval("Object.keys(ICON).length").asInt(), "the jar's instance: the house's fifteen");
        assertFalse(eval("'COLUMN_ICON' in ICON").asBoolean(), "the panes' icons are the panes' module's");
        assertEquals("span", eval("Icon.TAG").asString());
        assertThrows(PolyglotException.class, () -> eval("new Icon(null, ICON.ADD_ICON)"));
    }
}
