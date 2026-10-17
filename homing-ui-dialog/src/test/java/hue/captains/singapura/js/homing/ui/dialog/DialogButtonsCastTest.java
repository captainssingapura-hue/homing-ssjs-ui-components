package hue.captains.singapura.js.homing.ui.dialog;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The buttons as the dialog casts them, its action bar's (RFC 0067, D51: back in their owner's jar, over the jar's own
 * instance, where the castings are): the default action as Go, the rest as Action - each its casting's class alone.
 */
class DialogButtonsCastTest extends UiDialogJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        function el(tag) {
            var classes = new Set(), listeners = {};
            return { tagName: tag.toUpperCase(), type: "", disabled: false, textContent: "", attrs: {},
                setAttribute: function (k, v) { this.attrs[k] = String(v); }, kids: [], appendChild: function (c) { this.kids.push(c); }, getAttribute: function (k) { return k in this.attrs ? this.attrs[k] : null; },
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                classes: function () { return Array.from(classes); },
                addEventListener: function (t, fn) { (listeners[t] = listeners[t] || []).push(fn); } };
        }
        var css = {
            cls: function (name) { return { name: name }; },
            addClass: function (e, h) { e.classList.add(h.name); },
            removeClass: function (e, h) { e.classList.remove(h.name); },
            size: function (e, s) { e.size = s; }, extent: function (e, t) { e.extent = t; }, aspect: function (e, a) { e.aspect = a; } };
        var go = new PrimaryButton(el("button"), { label: "Save" }, ROLE.GO);
        var action = new PlainButton(el("button"), { label: "Later" }, ROLE.ACTION);
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet'; if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };");
        loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");
        for (String m : new String[] {"SimpleComponentModule.js", "ButtonBehaviorModule.js", "ButtonModule.js", "PlainButtonModule.js", "PrimaryButtonModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private String str(String src) { return js.eval("js", "String(" + src + ")").asString(); }

    @Test
    void theDefaultActionAsGo_theRestAsAction_eachItsCastingsClassAlone() {
        assertEquals("styled-primary-button_go", str("go.el.classes()"), "the primary button as Go: its casting's class alone - the component and the role, the owner never in it");
        assertEquals("styled-plain-button_action", str("action.el.classes()"), "the plain button as Action: its casting's class");
    }
}
