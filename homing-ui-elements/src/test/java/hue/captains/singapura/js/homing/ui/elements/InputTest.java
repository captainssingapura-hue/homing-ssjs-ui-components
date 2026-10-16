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
 * The browser's own inputs as components (RFC 0067, appendix Two Classes per Leaf, phase 5): each a constructor of its
 * own, its state, operations and events riding on its native element - the value and the tick the element's, the
 * element's events dressed as the component's - and its casting's class worn, a state's held while it holds.
 */
class InputTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), listeners = {};
            return { tagName: tag.toUpperCase(), type: "", value: "", checked: false, disabled: false, placeholder: "",
                min: "", max: "", step: "", options: [], attrs: {},
                setAttribute: function (k, v) { this.attrs[k] = String(v); },
                add: function (o) { this.options.push(o); },
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                classes: function () { return Array.from(classes); },
                addEventListener: function (t, fn) { (listeners[t] = listeners[t] || []).push(fn); },
                fire: function (t) { var ev = { type: t }; (listeners[t] || []).forEach(function (fn) { fn(ev); }); } };
        }
        function Option(text, value) { return { text: text, value: value }; }
        var css = { cls: function (name) { return { name: name }; }, addClass: function (e, h) { e.classList.add(h.name); },
                    removeClass: function (e, h) { e.classList.remove(h.name); }, size: function (e, s) { e.size = s; } };
        var el_checkbox = { name: 'el_checkbox' };
        var branch = { createElement: function (n, tag) { return el(tag); } };
        class Told { constructor(tag, types) { this.tag = tag; this.types = types; }
                     canHandle(t) { return this.types.indexOf(t) >= 0; }
                     handle(e) { log.push(this.tag + ":" + e.type + (e instanceof StateChange ? " was " + e.previous + (e.raw ? " by the user" : " by a setter") : "")); } }
        var name = new TextBox(TextBoxFactory.create(branch, "name"), { value: "Ada", placeholder: "Your name" });
        var agree = new Checkbox(CheckboxFactory.create(branch, "agree"), {});
        var volume = new Range(RangeFactory.create(branch, "volume"), { min: 0, max: 100, step: 5, value: 40 });
        var kind = new Select(SelectFactory.create(branch, "kind"), { options: [{ value: "a", label: "Apples" }, "pears"], value: "pears" });
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
        for (String m : new String[] {"SimpleComponentModule.js", "InputBehaviorsModule.js", "TextBoxModule.js", "CheckboxModule.js",
                                      "RangeModule.js", "SelectModule.js"})
            loadModule(DIR + m);
        js.eval("js", SHIM);
    }

    private String str(String src) { return js.eval("js", "String(" + src + ")").asString(); }
    private boolean is(String src) { return js.eval("js", src).asBoolean(); }

    @Test
    void aTextBox_itsWordsTheElements_itsEventsTheElementsDressed() {
        assertEquals("styled-text-box", str("name.el.classes()"));
        assertEquals("text", str("name.el.type"));
        assertEquals("Ada", str("name.value"));
        assertEquals("Your name", str("name.el.placeholder"));
        js.eval("js", "name.setOnInput(new Told('typed', [EVENT.INPUT])); name.setOnCommit(new Told('done', [EVENT.COMMIT]));"
                + " name.el.value = 'Ada L'; name.el.fire('input'); name.el.fire('change');");
        assertEquals("Ada L", str("name.value"), "what the user typed is the element's, never copied");
        assertEquals("typed:" + str("EVENT.INPUT") + ",done:" + str("EVENT.COMMIT"), str("log"));
        js.eval("js", "name.enabled = false;");
        assertTrue(is("name.el.disabled"));
        assertTrue(is("name.el.classList.contains('styled-text-box--disabled')"), "off, its state's class (D21)");
    }

    @Test
    void aCheckbox_itsTickTheElements_theUsersTurnTakenAsItsState() {
        assertEquals("styled-checkbox,el_checkbox", str("agree.el.classes()"));
        assertEquals("checkbox", str("agree.el.type"));
        js.eval("js", "agree.setOnCheckedChanged(new Told('ticked', [STATE.CHECKED.changed])); agree.setOnCommit(new Told('done', [EVENT.COMMIT]));"
                + " agree.el.checked = true; agree.el.fire('change');");
        assertTrue(is("agree.checked"), "the user's tick, taken from the element");
        assertTrue(is("agree.el.classList.contains('styled-checkbox--checked')"), "its Checked state's class, while it holds");
        assertEquals("ticked:" + str("STATE.CHECKED.changed") + " was false by the user,done:" + str("EVENT.COMMIT"), str("log"));
        js.eval("js", "agree.checked = false;");
        assertFalse(is("agree.el.checked"), "a setter turns the element's");
        assertFalse(is("agree.el.classList.contains('styled-checkbox--checked')"));
    }

    @Test
    void aRange_itsBoundsAndValueTheElements() {
        assertEquals("styled-range", str("volume.el.classes()"), "on its own: a role is cast only where a slot casts it, as the scale widget does with the preferences");
        assertEquals("range", str("volume.el.type"));
        assertEquals("0 100 5 40", str("volume.min + ' ' + volume.max + ' ' + volume.step + ' ' + volume.value"));
    }

    @Test
    void aSelect_itsOptionsWrittenByTheElementsOwnOperations() {
        assertEquals("styled-select", str("kind.el.classes()"));
        assertEquals("a:Apples,pears:pears", str("kind.options.map(function (o) { return o.value + ':' + o.label; })"), "a bare word its own label");
        assertEquals("pears", str("kind.value"));
        js.eval("js", "kind.setOnCommit(new Told('picked', [EVENT.COMMIT])); kind.el.value = 'a'; kind.el.fire('change');");
        assertEquals("a", str("kind.value"));
        assertEquals("picked:" + str("EVENT.COMMIT"), str("log"));
    }
}
