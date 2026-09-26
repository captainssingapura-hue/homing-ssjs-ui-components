package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A pane's keys, headless, over a fake pane's surface: the container's own
 * scheme (arrows, Home/End, Shift to reorder and detach, Enter into the tab,
 * Escape kept, the menu keys), the browser's (Ctrl+Tab and its kin, wrapping,
 * landing), the list a pane holds of them, what the walk is offered, which of
 * the four the keys are in, and the law at the door.
 */
class PaneKeysTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/panes/";

    private static final String SHIM = """
        var calls = [];
        function member(name, into) { return { name: name, in: into || {}, leave: function () {} }; }
        function widget(name) { var w = { root: {}, activate: function () { calls.push("activate:" + name); } }; w.focus = member(name); return w; }
        var widgets = { a: widget("a"), b: widget("b"), c: widget("c") };
        function pane(ids, active) {
            return { slotId: "s", _ids: ids.slice(), _active: active === undefined ? (ids[0] || null) : active,
                focus: { members: [] },
                tabs: function () { return this._ids.slice(); }, activeTab: function () { return this._active; },
                has: function (id) { return this._ids.indexOf(id) >= 0; }, count: function () { return this._ids.length; },
                switchTab: function (id) { calls.push("switch:" + id); this._active = id; },
                moveTab: function (id, to) { calls.push("move:" + id + ">" + to); },
                requestDetach: function () { calls.push("detach"); },
                menuByKey: function () { calls.push("menu"); return true; },
                land: function (id) { calls.push("land:" + id); },
                widgetOf: function (id) { return widgets[id] || null; } };
        }
        function key(k, mods) { return Object.assign({ key: k, shiftKey: false, ctrlKey: false, altKey: false, metaKey: false }, mods || {}); }
        function run(scheme, p, keys) { calls = []; var took = keys.map(function (k) { return scheme.keyDown(p, k); }); return took.join(",") + " | " + calls.join(" "); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(P + "PaneKeysModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void theArrowsAndHomeEnd_moveTheActiveTabAtOnce_withoutWrapping() {
        assertEquals("true,true,true | switch:b switch:c switch:c",
                eval("run(PaneKeys, pane(['a','b','c'], 'a'), [key('ArrowRight'), key('ArrowRight'), key('ArrowRight')])").asString(), "the end: no wrap");
        assertEquals("true,true | switch:c switch:a", eval("run(PaneKeys, pane(['a','b','c'], 'b'), [key('End'), key('Home')])").asString());
        assertEquals("true | switch:a", eval("run(PaneKeys, pane(['a','b','c'], null), [key('ArrowLeft')])").asString(), "nothing active: the first");
        assertEquals("true,true | ", eval("run(PaneKeys, pane([], null), [key('ArrowRight'), key('Home')])").asString(), "no tabs: taken, and nothing to switch to");
    }

    @Test
    void shiftReordersAndDetaches_EnterGoesIntoTheTab_EscapeIsKept() {
        assertEquals("true,true | move:b>0 move:b>2", eval("run(PaneKeys, pane(['a','b','c'], 'b'), [key('ArrowLeft', { shiftKey: true }), key('ArrowRight', { shiftKey: true })])").asString(),
                "one slot along the rail; the pane clamps");
        assertEquals("true | detach", eval("run(PaneKeys, pane(['a','b'], 'a'), [key('ArrowDown', { shiftKey: true })])").asString());
        assertEquals("true | activate:b", eval("run(PaneKeys, pane(['a','b'], 'b'), [key('Enter')])").asString(), "the widget activates itself: a claim of its own");
        assertEquals("true | ", eval("run(PaneKeys, pane(['a','b'], 'a'), [key('Escape')])").asString(), "taken, and nothing done: the dock is the floor");
        assertEquals("true,true | ", eval("run(PaneKeys, pane([], null), [key('ArrowDown', { shiftKey: true }), key('Enter')])").asString(), "nothing active: taken, nothing asked");
    }

    @Test
    void theMenuKeys_askThePane_andEverythingElseIsLeft() {
        assertEquals("true,true | menu menu", eval("run(PaneKeys, pane(['a'], 'a'), [key('ContextMenu'), key('F10', { shiftKey: true })])").asString());
        assertEquals("false,false,false,false | ", eval("run(PaneKeys, pane(['a','b'], 'a'), [key('ArrowRight', { ctrlKey: true }), key('ArrowRight', { altKey: true }), key('x'), key('ArrowUp', { shiftKey: true })])").asString(),
                "a chord with a modifier, and a key it has no word for");
        assertFalse(eval("PaneKeys.chord(pane(['a'], 'a'), key('ArrowRight'))").asBoolean(), "an arrow belongs to whatever is focused");
        assertTrue(eval("PaneKeys.keeps()").asBoolean(), "the bar is somewhere the keys rest");
    }

    @Test
    void theBrowsersScheme_wrapsAndLands_andAnswersBothDoors() {
        assertEquals("true,true | switch:a land:a switch:c land:c", eval("run(BrowserKeys, pane(['a','b','c'], 'c'), [key('Tab', { ctrlKey: true }), key('Tab', { ctrlKey: true, shiftKey: true })])").asString(),
                "past the last is the first, and back again; each time it lands in the tab");
        assertEquals("true,true,true,true | switch:b land:b switch:a land:a switch:b land:b switch:a land:a",
                eval("run(BrowserKeys, pane(['a','b','c'], 'a'), [key('PageDown', { ctrlKey: true }), key('PageUp', { ctrlKey: true }), key('ArrowRight', { ctrlKey: true, shiftKey: true }), key('ArrowLeft', { ctrlKey: true, shiftKey: true })])").asString(),
                "the pages, and the chord no browser keeps");
        assertEquals("false,false,false,false | ", eval("run(BrowserKeys, pane(['a','b'], 'a'), [key('Tab'), key('Tab', { ctrlKey: true, altKey: true }), key('PageDown', { ctrlKey: true, shiftKey: true }), key('x', { ctrlKey: true })])").asString(),
                "not a Ctrl chord of its own: left");
        assertEquals("true | ", eval("run(BrowserKeys, pane([], null), [key('Tab', { ctrlKey: true })])").asString(), "on, with nowhere to go: taken all the same");
        assertTrue(eval("calls = []; BrowserKeys.chord(pane(['a','b'], 'a'), key('Tab', { ctrlKey: true })) && calls.join(' ') === 'switch:b land:b'").asBoolean(), "through the chord too");
        assertFalse(eval("BrowserKeys.keeps()").asBoolean(), "a road to the tab, never a place");
    }

    @Test
    void aPanesListOfSchemes_theFirstTakerWins_andTheFirstSaysWhetherItKeeps() {
        assertEquals("1,true", eval("var l = PaneSchemes.of(null); l.length + ',' + (l[0] === PaneKeys)").asString(), "nothing given: the container's own");
        assertEquals("1", eval("String(PaneSchemes.of(BrowserKeys).length)").asString(), "one given: a list of one");
        assertThrows(PolyglotException.class, () -> eval("PaneSchemes.of([PaneKeys, { keyDown: function () {} }])"), "a scheme answers both doors and keeps");
        assertFalse(eval("PaneSchemes.keeps(PaneSchemes.of([BrowserKeys, PaneKeys]), pane(['a']))").asBoolean(), "the first is the pane's character");
        assertTrue(eval("PaneSchemes.keeps(PaneSchemes.of([PaneKeys, BrowserKeys]), pane(['a']))").asBoolean());
        assertEquals("true | switch:b land:b", eval("calls = []; var took = PaneSchemes.keyDown([PaneKeys, BrowserKeys], pane(['a','b'], 'a'), key('Tab', { ctrlKey: true })); took + ' | ' + calls.join(' ')").asString(),
                "the container's scheme leaves Ctrl+Tab; the browser's takes it");
        assertEquals("true | switch:b", eval("calls = []; var took = PaneSchemes.keyDown([PaneKeys, BrowserKeys], pane(['a','b'], 'a'), key('ArrowRight')); took + ' | ' + calls.join(' ')").asString(), "the first taker has it");
        assertFalse(eval("PaneSchemes.chord([PaneKeys], pane(['a','b'], 'a'), key('Tab', { ctrlKey: true }))").asBoolean());
    }

    @Test
    void theWalkIsOfferedOnlyTheTabOnShow_andWhatIsNotATabIsNotThePanes() {
        assertTrue(eval("PaneKeys.wouldOffer(pane(['a','b'], 'a'), { component: widgets.a })").asBoolean(), "the tab on show");
        assertFalse(eval("PaneKeys.wouldOffer(pane(['a','b'], 'a'), { component: widgets.b })").asBoolean(), "behind it");
        assertTrue(eval("PaneKeys.wouldOffer(pane(['a','b'], 'a'), { component: {} })").asBoolean(), "not a tab's widget: not the pane's business");
    }

    @Test
    void theKeysAreInOneOfFour_inThatOrder() {
        assertEquals("held,lent,candidate,null", eval("[{ _holds: true, _inside: true, _offered: true }, { _inside: true, _offered: true }, { _offered: true }, {}].map(function (p) { return String(PaneKeys.keysState(p)); }).join(',')").asString());
    }

    @Test
    void theLaw_aWidgetIsAMemberWithActivate() {
        assertTrue(eval("PaneKeys.law(widget('x'))").asBoolean());
        assertFalse(eval("var w = widget('x'); delete w.activate; PaneKeys.law(w)").asBoolean(), "no activate");
        assertFalse(eval("var w = widget('x'); w.focus.in = null; PaneKeys.law(w)").asBoolean(), "a membership that has left");
        assertFalse(eval("PaneKeys.law({ root: {}, activate: function () {} })").asBoolean(), "no membership");
        assertFalse(eval("PaneKeys.law(null)").asBoolean());
    }

    @Test
    void theDoor_refusesWithTheNameOfWhatIsWrong() {
        eval("var p = pane(['a'], 'a'); function t(id, w) { return { id: id, widget: w === undefined ? widget(id) : w }; }");
        assertTrue(refusal("PaneKeys.admit(p, t(''))").contains("non-empty string"));
        assertTrue(refusal("PaneKeys.admit(p, t('a'))").contains("already in slot 's'"));
        assertTrue(refusal("PaneKeys.admit(p, t('x', { activate: function () {} }))").contains("has no widget with a root"));
        assertTrue(refusal("var w = widget('x'); delete w.activate; PaneKeys.admit(p, t('x', w))").contains("not logically focusable"));
        assertTrue(refusal("p.focus.members = [{ name: 'same' }]; var w = widget('same'); PaneKeys.admit(p, t('x', w))").contains("member name 'same'"));
        eval("p.focus.members = []; PaneKeys.admit(p, t('x'))");   // by the law, with room: no refusal
        eval("var mine = widget('same'); mine.focus.in = p.focus; p.focus.members = [mine.focus]; PaneKeys.admit(p, t('y', mine))");   // already in this dock's branch: its own name is no clash
    }

    private String refusal(String src) {
        return assertThrows(PolyglotException.class, () -> eval(src)).getMessage();
    }
}
