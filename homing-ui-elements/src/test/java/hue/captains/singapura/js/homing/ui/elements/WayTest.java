package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The ways on files (RFC 0067, appendix Two Classes per Leaf, phase 5): a link, a crumb and the brand, each wearing its
 * casting's one class - on its own, or in the role its owner passes - and the crumb holding its Current state's class
 * while it is the page; the brand wearing its casting whole, its mark a brand mark of its own cast as its symbol, no
 * slice of a look anywhere.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class WayTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        var warned = [];
        console.warn = function (m) { warned.push(String(m)); };
        function el(tag) {
            var classes = new Set();
            return { tagName: tag.toUpperCase(), textContent: "", attrs: {}, kids: [],
                setAttribute: function (k, v) { this.attrs[k] = String(v); }, removeAttribute: function (k) { delete this.attrs[k]; },
                getAttribute: function (k) { return k in this.attrs ? this.attrs[k] : null; },
                appendChild: function (c) { this.kids.push(c); },
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                classes: function () { return Array.from(classes); },
                addEventListener: function () {} };
        }
        var css = {
            cls: function (name) { return { name: name }; },
            addClass: function (e, h) { e.classList.add(h.name); },
            removeClass: function (e, h) { e.classList.remove(h.name); },
            size: function (e, s) { e.size = s; } };
        var el_text = { name: 'el_text' }, el_brand = { name: 'el_brand' }, el_brand_mark = { name: 'el_brand_mark' };
        var HrefManagerInstance = { set: function (e, to) { e.attrs.href = to; } };
        var branch = { createElement: function (n, tag) { return el(tag); }, activate: function () {} };
        var link = new Link(LinkFactory.create(branch, "more"), { text: "More", to: "/more" });
        var open = new Link(LinkFactory.create(branch, "open"), { text: "Open", to: "/report" }, ROLE.OPEN);
        var crumb = new Crumb(CrumbFactory.create(branch, "c"), { text: "Gallery", to: "/gallery" });
        var step = new Crumb(CrumbFactory.create(branch, "s"), { text: "Here", current: true }, ROLE.STEP);
        var brand = new Brand(branch, { label: "Homing" });
        var home = new Brand(branch, { label: "Homing", home: "/" }, ROLE.HOME);
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
        for (String m : new String[] {"SimpleComponentModule.js", "LinkBehaviorModule.js", "LinkModule.js", "CrumbModule.js",
                                      "MarkBehaviorModule.js", "BrandMarkModule.js", "TextBehaviorModule.js", "TextModule.js",
                                      "WordmarkModule.js", "BrandModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String str(String src) { return eval("String(" + src + ")").asString(); }

    @Test
    void aLink_wearsItsCastingsClassAlone_onItsOwnOrInItsRole() {
        assertEquals("styled-link", str("link.el.classes()"), "on its own");
        assertEquals("styled-link_open", str("open.el.classes()"), "cast as Open, as the summary card casts it: its casting's class alone");
        assertEquals("/report", str("open.el.attrs.href"));
    }

    @Test
    void aCrumb_holdsItsCurrentClass_whileItIsThePage() {
        assertEquals("styled-crumb", str("crumb.el.classes()"));
        assertEquals("styled-crumb_step,styled-crumb_step--current", str("step.el.classes()"),
                "the trail's step that is the page: its casting's class, and its Current state's (D12)");
        assertEquals("page", str("step.el.attrs['aria-current']"), "the reflection written too");
        eval("step.current = false;");
        assertFalse(eval("step.el.classList.contains('styled-crumb_step--current')").asBoolean(), "the rest: no class");
        eval("crumb.current = true;");
        assertTrue(eval("crumb.el.classList.contains('styled-crumb--current')").asBoolean());
    }

    @Test
    void theBrand_wearsItsCastingWhole_itsMarkABrandMarkOfItsOwn() {
        assertEquals("el_brand,styled-brand", str("brand.root.classes()"), "its casting whole: no slice");
        assertEquals("el_brand,styled-brand_home", str("home.root.classes()"), "cast as Home, as the site bar casts it");
        assertEquals("SPAN", str("home.root.kids[0].tagName"));
        assertEquals("styled-brand-mark_symbol,el_brand_mark", str("home.root.kids[0].classes()"),
                "its mark a brand mark cast as its symbol: its colour the mark's own class");
        assertEquals("true", str("home.root.kids[0].attrs['aria-hidden']"), "the name says whose site it is");
        assertEquals("Homing", str("home.root.kids[1].textContent"));
        assertEquals("", str("warned.join(';')"), "no slice of a look worn, so nothing said on the console");
    }
}
