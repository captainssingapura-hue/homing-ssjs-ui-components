package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.graalvm.polyglot.PolyglotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The text on files (RFC 0067, appendix Two Classes per Leaf, phase 5): every text leaf a constructor of its own -
 * code and the badge of each tone among them - each wearing its casting's one class, on its own or in the role its
 * owner passes, refused in a role the taxonomy never casts it in.
 */
class TextTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        function el(tag) {
            var classes = new Set();
            return { tagName: tag.toUpperCase(), textContent: "", attrs: {},
                setAttribute: function (k, v) { this.attrs[k] = String(v); },
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                classes: function () { return Array.from(classes); },
                addEventListener: function () {} };
        }
        var css = { cls: function (name) { return { name: name }; }, addClass: function (e, h) { e.classList.add(h.name); },
                    removeClass: function (e, h) { e.classList.remove(h.name); }, size: function (e, s) { e.size = s; } };
        var el_text = { name: 'el_text' };
        var branch = { createElement: function (n, tag) { return el(tag); } };
        function worn(x) { return x.el.classes().filter(function (c) { return c !== 'el_text'; }).join(','); }
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
        for (String m : new String[] {"SimpleComponentModule.js", "TextBehaviorModule.js", "TextModule.js", "LabelModule.js", "HeadingModule.js",
                                      "Heading1Module.js", "Heading4Module.js", "CodeModule.js", "SuccessBadgeModule.js",
                                      "WarningBadgeModule.js", "DangerBadgeModule.js", "KickerModule.js", "CaptionModule.js",
                                      "LedeModule.js", "ReadoutModule.js", "BadgeModule.js", "PillModule.js", "WordmarkModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private String str(String src) { return js.eval("js", "String(" + src + ")").asString(); }

    @Test
    void everyTextLeaf_wearsItsCastingsClass_onItsOwn() {
        assertEquals("styled-code", str("worn(new Code(CodeFactory.create(branch, 'c'), { text: 'mvn' }))"));
        assertEquals("CODE", str("new Code(CodeFactory.create(branch, 'c'), {}).el.tagName"), "code on its own element");
        assertEquals("styled-success-badge", str("worn(new SuccessBadge(SuccessBadgeFactory.create(branch, 's'), { text: 'Done' }))"));
        assertEquals("styled-warning-badge", str("worn(new WarningBadge(WarningBadgeFactory.create(branch, 'w'), { text: 'Careful' }))"));
        assertEquals("styled-danger-badge", str("worn(new DangerBadge(DangerBadgeFactory.create(branch, 'd'), { text: 'Gone' }))"));
        assertEquals("styled-heading1", str("worn(new Heading1(Heading1Factory.create(branch, 'h'), { text: 'Title' }))"));
        assertEquals("Done", str("new SuccessBadge(SuccessBadgeFactory.create(branch, 's'), { text: 'Done' }).text"));
    }

    @Test
    void inARole_itsCastingsClassAlone() {
        assertEquals("styled-kicker_title", str("worn(new Kicker(KickerFactory.create(branch, 'k'), { text: 'Guide' }, ROLE.TITLE))"));
        assertEquals("styled-heading4_title", str("worn(new Heading4(Heading4Factory.create(branch, 'h'), { text: 'A card' }, ROLE.TITLE))"));
        assertEquals("styled-badge_tag", str("worn(new Badge(BadgeFactory.create(branch, 'b'), { text: 'new' }, ROLE.TAG))"));
        assertEquals("styled-wordmark_name", str("worn(new Wordmark(WordmarkFactory.create(branch, 'w'), { text: 'Homing' }, ROLE.NAME))"));
        var refused = assertThrows(PolyglotException.class, () -> str("new Code(CodeFactory.create(branch, 'c'), {}, ROLE.TITLE)"));
        assertTrue(refused.getMessage().contains("[Code] is never cast as title"), refused.getMessage());
    }
}
