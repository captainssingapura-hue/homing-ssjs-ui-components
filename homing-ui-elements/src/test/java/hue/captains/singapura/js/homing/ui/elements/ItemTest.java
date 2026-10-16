package hue.captains.singapura.js.homing.ui.elements;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The items (RFC 0067, appendix Two Classes per Leaf, phase 5): a menu's item, a list's row and a choice's option, each
 * a component of its own wearing its casting's class and holding its states as their classes; one behaviour for the
 * three - a press while usable is the click, the pointer coming onto an item that can be pointed at points at it.
 */
class ItemTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tagName: tag.toUpperCase(), children: [], listeners: {}, textContent: "", type: "",
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                has: function (c) { return classes.has(c); },
                appendChild: function (c) { this.children.push(c); return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return k in attrs ? attrs[k] : null; },
                removeAttribute: function (k) { delete attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                fire: function (t) { (this.listeners[t] || []).forEach(function (fn) { fn({ type: t, target: node }); }); } };
            return node;
        }
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) { return { name: name, createElement: function (n, tag) { return el(tag); },
                                             elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                                             createBranch: function (n) { return fakeBranch(n); }, activate: function () {} }; }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); } };
        ["el_menu_item", "el_menu_item_label", "el_menu_item_hint", "el_menu_item_disclose", "el_choice_option", "el_choice_option_note"].forEach(function (c) { globalThis[c] = c; });
        class Told { constructor(tag, type) { this.tag = tag; this.type = type; } canHandle(t) { return t === this.type; }
                     handle(e) { log.push(this.tag + (e.raw ? " by " + e.raw.type : " by a setter")); } }
        var page = fakeBranch("page");
        var item = new MenuItem(page.createBranch("rotate"), { label: "Rotate", icon: "rotate", hint: "a quarter turn" });
        var opener = new MenuItem(page.createBranch("animal"), { label: "Animal", expanded: false });
        var row = new ListRow(page.elementMaker("row"), { text: "Appearance" });   // on its own: cast as a choice only where the preferences are
        var option = new ChoiceOption(page.createBranch("dark"), { label: "Dark", note: "for the night" });
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", "var ic_base = 'ic_base', ICONS = { rotate: 'ic_rotate', disclose: 'ic_disclose', check: 'ic_check' };");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        TextsInJs.load(js, this::loadModule);   // a hint and a note are the house's captions
        for (String m : new String[] {"ItemBehaviorModule.js", "MenuItemModule.js", "ListRowModule.js", "ChoiceOptionModule.js"})
            loadModule(DIR + m);
        js.eval("js", TextsInJs.withCss(SHIM));
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String str(String src) { return eval("String(" + src + ")").asString(); }
    private boolean is(String src) { return eval(src).asBoolean(); }

    @Test
    void theMenusItem_itsPartsTheHouses_andThePointerPointsAtIt() {
        assertTrue(is("item.el.has('el_menu_item') && item.el.getAttribute('role') === 'menuitem'"));
        assertTrue(is("item.el.children[0] === item.mark.el && item.mark.name() === 'rotate'"), "its glyph first, the house's icon");
        assertTrue(is("item.el.children[2].has('styled-caption_hint') && item.el.children[2].has('el_menu_item_hint')"), "its hint, a caption cast as a hint");
        assertEquals("Rotate,null", str("[item.label, String(item.el.getAttribute('aria-haspopup'))]"), "it opens nothing");
        assertEquals("menu false", str("opener.el.getAttribute('aria-haspopup') + ' ' + opener.el.getAttribute('aria-expanded')"));
        assertTrue(is("opener.el.children[opener.el.children.length - 1].has('el_menu_item_disclose')"), "one that opens rows ends in the disclose mark");
        eval("item.setOnPointedChanged(new Told('pointed', STATE.POINTED.changed)); item.el.fire('pointerenter')");
        assertTrue(is("item.pointed && item.el.getAttribute('data-highlighted') === 'true'"));
        assertEquals("pointed by pointerenter", str("log.join()"), "the pointer's own event, so its menu can tell it from its own setter");
        eval("log = []; item.pointed = false");
        assertEquals("pointed by a setter", str("log.join()"));
    }

    @Test
    void anItemOff_takesNoClick_andSaysSo() {
        eval("item.setOnClick(new Told('picked', EVENT.CLICK)); item.el.fire('click')");
        assertEquals("picked by click", str("log.join()"));
        eval("log = []; item.enabled = false; item.el.fire('click')");
        assertEquals("", str("log.join()"), "off, a press does nothing");
        assertTrue(is("item.el.getAttribute('aria-disabled') === 'true'"));
        eval("item.checked = true");
        assertEquals("true", str("item.el.getAttribute('aria-checked')"));
        eval("item.checked = null");
        assertEquals("null", str("item.el.getAttribute('aria-checked')"), "neither: no check said");
    }

    @Test
    void theListsRow_pickedCurrentAndMarkedWhereTheKeysAre() {
        assertTrue(is("row.el.has('styled-list-row') && row.el.textContent === 'Appearance'"));
        eval("row.selected = true; row.current = true; row.keys = 'lent'");
        assertTrue(is("row.el.has('styled-list-row--selected') && row.el.has('styled-list-row--current') && row.el.has('styled-list-row--lent')"));
        assertEquals("true true lent", str("row.el.getAttribute('aria-selected') + ' ' + row.el.getAttribute('aria-current') + ' ' + row.el.getAttribute('data-keys')"));
        eval("row.keys = null");
        assertFalse(is("row.el.has('styled-list-row--lent') || row.el.getAttribute('data-keys') !== null"), "away: no mark");
    }

    @Test
    void theChoicesOption_aButton_itsNoteAtItsEnd_theOnePicked() {
        assertEquals("BUTTON button option", str("option.el.tagName + ' ' + option.el.type + ' ' + option.el.getAttribute('role')"));
        assertTrue(is("option.el.has('styled-choice-option') && option.el.children[1].has('styled-caption_note') && option.el.children[1].has('el_choice_option_note')"));
        assertEquals("Dark,for the night", str("[option.label, option.el.children[1].textContent]"));
        eval("option.selected = true");
        assertTrue(is("option.el.has('styled-choice-option--selected') && option.el.getAttribute('aria-selected') === 'true'"));
    }
}
