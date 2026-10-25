package hue.captains.singapura.js.homing.ui.elements;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The cards and the summary (RFC 0067, appendix Two Classes per Leaf, phase 5; the owner, 14 Oct: the summary card
 * ditched). A card is a container: the plain one a frame its caller fills, still; the clickable one a button - its click
 * by the hand or by Enter or Space through the keyboard party, never a keydown listener of its own. The summary is a
 * view of its own, its texts and its link the house's in their roles, shown on any container.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class CardTest extends UiElementsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tagName: tag.toUpperCase(), children: [], parentNode: null, listeners: {}, textContent: "", tabIndex: -1,
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                has: function (c) { return classes.has(c); },
                appendChild: function (c) { this.children.push(c); c.parentNode = this; return c; },
                contains: function (o) { for (var x = o; x; x = x.parentNode) if (x === this) return true; return false; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return k in attrs ? attrs[k] : null; },
                removeAttribute: function (k) { delete attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                removeEventListener: function (t, fn) { var l = this.listeners[t] || []; var i = l.indexOf(fn); if (i >= 0) l.splice(i, 1); },
                click: function () { (this.listeners.click || []).forEach(function (fn) { fn({ type: "click", target: node }); }); },
                listening: function (t) { return (this.listeners[t] || []).length; } };
            return node;
        }
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) { return { name: name, createElement: function (n, tag) { var e = el(tag); e.name = n; return e; },
                                             elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                                             createBranch: function (n) { return fakeBranch(n); },
                                             dissolve: function () { log.push("dissolved:" + name); }, activate: function () {} }; }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    size: function (e, v) { e._size = v; }, extent: function () {}, aspect: function (e, v) { e._aspect = v; } };
        var HrefManagerInstance = { set: function (e, to) { e.setAttribute("href", to); } };
        ["el_card", "el_summary", "el_summary_head", "el_summary_title", "el_summary_tag", "el_summary_text", "el_summary_foot"].forEach(function (c) { globalThis[c] = c; });
        var members = {}, kbLog = [], enrolled = {};
        var kb = { enroll: function (root, id, marks) { root._kb = id; enrolled[id] = marks; return function () { delete root._kb; }; },
                   memberAt: function (e) { for (var x = e; x; x = x.parentNode) if (x._kb) return x._kb; return null; },
                   join: function (id, h) { members[id] = h; kbLog.push("join:" + id); return id; }, leave: function (id) { delete members[id]; kbLog.push("leave:" + id); },
                   claim: function (id) { kbLog.push("claim:" + id); }, release: function (id) { kbLog.push("release:" + id); } };
        class Told { constructor(tag) { this.tag = tag; } canHandle(t) { return t === EVENT.CLICK; } handle(e) { log.push(this.tag + (e.raw ? " by " + e.raw.type : "")); } }
        var page = fakeBranch("page");
        var plain = new PlainCard(page.elementMaker("plain"), { size: 0.5, aspect: -1 });
        var card = new ClickableCard(page.createBranch("act"), { keyboard: kb });
        card.setOnClick(new Told("clicked"));
        var inner = new ClickableCard(page.createBranch("inner"), {});
        inner.setOnClick(new Told("inner"));
        var sum = new SummaryView(page.createBranch("summary"), { title: "Grid", tag: "new", text: "Cells and their ranges.", link: { to: "/grid", label: "Open" }, size: 0.5 });
        plain.el.appendChild(sum.root);
        var head = sum.root.children[0], text = sum.root.children[1], foot = sum.root.children[2];
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        TextsInJs.load(js, this::loadModule);   // the summary's texts are the house's
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeysModule.js");
        for (String m : new String[] {"MarkBehaviorModule.js", "LinkBehaviorModule.js", "LinkModule.js", "CardBehaviorModule.js",
                                      "PlainCardModule.js", "ClickableCardModule.js", "SummaryViewModule.js"})
            loadModule(DIR + m);
        js.eval("js", TextsInJs.withCss(SHIM));
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String str(String src) { return eval("String(" + src + ")").asString(); }
    private boolean is(String src) { return eval(src).asBoolean(); }
    private String log() { return str("log.join(' ')"); }

    @Test
    void thePlainCard_aFrameItsCallerFills_stillUnderTheHand() {
        assertTrue(is("plain.el.tagName === 'DIV' && plain.el.has('styled-plain-card') && plain.el.has('el_card')"), "its casting's class, and its own place");
        assertEquals("null 0 0", str("plain.el.getAttribute('role') + ' ' + plain.el.listening('click') + ' ' + plain.el.listening('pointerdown')"),
                "no role, nothing listened for: a card that does nothing when pressed is no button");
        assertEquals("0.5 -1", str("plain._degrees.size + ' ' + plain.aspect"));
        assertTrue(is("plain.el.children[0] === sum.root"), "what is in it, its caller's");
        assertThrows(Exception.class, () -> eval("new PlainCard(page.elementMaker('x'), { onClick: function () {} })"), "a plain card takes no action");
    }

    @Test
    void theClickableCard_aButton_itsClickByTheHand() {
        assertTrue(is("card.root.has('styled-clickable-card') && card.root.has('el_card')"));
        assertEquals("button 0", str("card.root.getAttribute('role') + ' ' + card.root.tabIndex"), "a button to the browser too");
        eval("card.root.click()");
        assertEquals("clicked by click", log());
        assertThrows(Exception.class, () -> eval("card.setOnClick(new Told('again'))"), "its handler set once, for good");
    }

    @Test
    void noKeydownOfItsOwn_enterOrSpaceOnTheCardIsItsClick() {
        assertEquals("0", str("card.root.listening('keydown')"));
        assertTrue(is("card.key({ key: 'Enter', target: card.root })"));
        assertTrue(is("card.key({ key: ' ', target: card.root })"));
        assertEquals("clicked by click clicked by click", log());
        assertFalse(is("card.key({ key: 'Escape', target: card.root })"), "another key is left");
        assertFalse(is("card.key({ key: 'Enter', target: { parentNode: card.root } })"), "Enter on something inside the card is not the card's");
    }

    @Test
    void handedTheSteward_joinsAndClaimsByTheConvention_itsMarksItsCastings_leavesOnDispose() {
        assertEquals("join:act", str("kbLog.join(' ')"), "the card with the steward joined as its branch's name; the other did not");
        assertEquals("clickable-card keyboard", str("enrolled.act.leaf.token + ' ' + enrolled.act.state"), "the keys' marks, its own casting's Keyboard state");
        assertEquals("1,0,0", str("[card.root.listening('pointerdown'), card.root.listening('focusin'), card.root.listening('focusout')].join()"),
                "the convention is one press; native focus moves nothing");
        eval("kbLog = []; members.act.keyDown({ key: 'Enter', target: card.root })");
        assertEquals("clicked by click", log(), "the member's handler is key()");
        eval("card.dispose()");
        assertEquals("leave:act", str("kbLog.join(' ')"));
        assertEquals("0", str("card.root.listening('pointerdown')"));
        assertTrue(log().endsWith("dissolved:act"));
    }

    @Test
    void aCardInsideAHolderIsBuiltWithout_andTakesKeysByCall() {
        assertEquals("0", str("inner.root.listening('pointerdown')"), "no convention: its holder hands it the keys");
        assertTrue(is("inner.key({ key: 'Enter', target: inner.root })"));
        assertEquals("inner by click", log());
    }

    @Test
    void theSummaryView_aViewOfItsOwn_itsTextsAndLinkInTheirRoles() {
        assertTrue(is("sum.root.has('styled-summary-view') && sum.root.has('el_summary')"), "its casting, a view's, and its layout");
        assertTrue(is("head.has('el_summary_head') && head.children[0].has('styled-heading4_title') && head.children[0].has('el_summary_title')"), "its title, a fourth-level heading cast as a title");
        assertTrue(is("head.children[1].has('styled-badge_tag') && head.children[1].has('el_summary_tag')"), "its tag, a badge at the head's end");
        assertTrue(is("text.has('styled-caption_summary') && text.has('el_summary_text')"), "its line of summary, a caption");
        assertTrue(is("foot.has('el_summary_foot') && foot.children[0].has('styled-link_open') && foot.children[0].getAttribute('href') === '/grid'"),
                "its way on, the house's link cast as what opens the thing, its address through the href manager");
        assertEquals("Grid,new,Cells and their ranges.,Open", str("[head.children[0].textContent, head.children[1].textContent, text.textContent, foot.children[0].textContent]"));
        assertEquals("Grid / Board", str("sum.title() + ' / ' + sum.title('Board')"));
    }

    @Test
    void theSummarysSize_onItsRowsAndEveryPartItMinted() {
        assertEquals("0.5,0.5,0.5", str("[sum.root._size, head._size, foot._size]"), "its room between its parts, by its size");
        eval("sum.size(0)");
        assertEquals("null,null,null", str("[sum.root._size, head._size, foot._size].map(String)"), "at rest nothing written");
        assertThrows(Exception.class, () -> eval("new SummaryView(page.createBranch('bad'), { badge: 'old' })"), "a tag, not a badge: what it is to the summary");
        eval("var bare = new SummaryView(page.createBranch('bare'), { title: 'Only' })");
        assertEquals("1 1", str("bare.root.children.length + ' ' + bare.root.children[0].children.length"), "the tag, the line and the way on, each only when given");
    }
}
