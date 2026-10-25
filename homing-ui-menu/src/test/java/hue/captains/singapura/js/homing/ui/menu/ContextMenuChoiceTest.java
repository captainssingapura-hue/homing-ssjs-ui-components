package hue.captains.singapura.js.homing.ui.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The menu item as the context menu casts it, its choice (RFC 0067, D51: back in its owner's jar, over the jar's own
 * instance, where the casting is): its casting's class, and the class of each state while it holds.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class ContextMenuChoiceTest extends UiMenuJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tagName: tag.toUpperCase(), children: [], listeners: {}, textContent: "", type: "",
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                has: function (c) { return classes.has(c); },
                appendChild: function (c) { this.children.push(c); return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); return c; },
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
        ["el_menu_item", "el_menu_item_label", "el_menu_item_hint", "el_menu_item_disclose", "el_menu_item_lead"].forEach(function (c) { globalThis[c] = c; });
        var item = new MenuItem(fakeBranch("rotate"), { label: "Rotate", icon: ICON.ROTATE_ICON, hint: "a quarter turn" }, ROLE.CHOICE);
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        TextsInJs.load(js, this::loadModule);
        for (String m : new String[] {"ItemBehaviorModule.js", "MenuItemModule.js"}) loadModule(DIR + m);
        js.eval("js", TextsInJs.withCss(SHIM));
    }

    private boolean is(String src) { return js.eval("js", src).asBoolean(); }

    @Test
    void theContextMenusChoice_wearsItsCasting_andEachStateItHolds() {
        assertTrue(is("item.el.has('styled-menu-item_choice') && item.el.has('el_menu_item') && item.el.getAttribute('role') === 'menuitem'"),
                "cast by the context menu as its choice: the casting's class");
        js.eval("js", "item.el.fire('pointerenter')");
        assertTrue(is("item.pointed && item.el.has('styled-menu-item_choice--pointed') && item.el.getAttribute('data-highlighted') === 'true'"),
                "pointed at: the state's class while it holds");
        js.eval("js", "item.enabled = false");
        assertTrue(is("item.el.has('styled-menu-item_choice--disabled') && item.el.getAttribute('aria-disabled') === 'true'"),
                "off: its class while it holds");
    }
}
