package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The grid over a fake DOM: the tree rendered as rows and columns with a
 * line between neighbours - a splitter where the grid is resizable, a seam
 * where it is not; a cell's element minted once and kept through
 * every re-arrangement, with what the owner put in it; subdivide beside a
 * cell — a sibling when the orientation matches, a nested split when not —
 * and remove, the room to the neighbour, a split of one giving way; the
 * tracks re-shared by a drag and by the method, a part held at its least -
 * the cells across it, where it holds a split; every change of arrangement
 * reported once, as data. The fake branch holds a name as the party does, so
 * a cell back under a gone one's id, or under an id of the same safe spelling
 * as another's, is a new cell and not a refused name.
 */
class SplitGridTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/splitgrid/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            return { tag: tag, tagName: String(tag).toUpperCase(), children: [], parentNode: null, listeners: {}, size: { width: 1000, height: 600 },
                     // a line is a pixel thick; anything else the size of the whole
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); },
                             remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); },
                             contains: function (c) { return classes.has(c); }, toggle: function (c, f) { if (f) classes.add(c); else classes.delete(c); },
                             [Symbol.iterator]: function () { return classes.values(); } },
                style: { setProperty: function (k, v) { props[k] = v; }, getPropertyValue: function (k) { return props[k] || ""; } },
                get firstChild() { return this.children[0] || null; },
                appendChild: function (c) { if (c.parentNode) c.parentNode.removeChild(c); this.children.push(c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                removeAttribute: function (k) { delete attrs[k]; }, hasAttribute: function (k) { return k in attrs; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                removeEventListener: function (t, fn) { var l = this.listeners[t] || []; var i = l.indexOf(fn); if (i >= 0) l.splice(i, 1); },
                getBoundingClientRect: function () { return classes.has("sg_line") ? { width: 1, height: 1, left: 0, top: 0 } : { width: this.size.width, height: this.size.height, left: 0, top: 0 }; },
                setPointerCapture: function () {}, releasePointerCapture: function () {},
                fire: function (t, ev) { ev.type = t; (this.listeners[t] || []).slice().forEach(function (fn) { fn(ev); }); },
                has: function (c) { return classes.has(c); } };
        }
        // as the party: a name, of an element or a branch, is held until the branch dissolves; a maker single-use, and known for one
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) {
            var elements = new Set(), branches = new Set();
            return { name: name,
                     createElement: function (n, tag) { if (elements.has(n)) throw new RangeError('name "' + n + '" is already in use on branch "' + name + '"'); elements.add(n); return el(tag); },
                     elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                     createBranch: function (n) { if (branches.has(n)) throw new RangeError('a branch named "' + n + '" already exists on "' + name + '"'); branches.add(n); return fakeBranch(n); },
                     dissolve: function () { log.push("dissolved:" + name); }, activate: function () {} };
        }
        var css = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, f) { e.classList.toggle(c, f); }, hasClass: function (e, c) { return e.classList.contains(c); } };
        var CssClassManagerInstance = css;
        var sg_root = "sg_root", sg_split = "sg_split", sg_split_h = "sg_split_h", sg_split_v = "sg_split_v", sg_child = "sg_child",
            sg_child_h = "sg_child_h", sg_child_v = "sg_child_v", sg_cell = "sg_cell", sg_line = "sg_line", sg_line_fixed = "sg_line_fixed",
            sz_handle = "sz_handle", sz_handle_h = "sz_handle_h", sz_handle_v = "sz_handle_v";
        var console = { error: function (m) { log.push("error:" + m); } };
        var host = el("div"), branch = fakeBranch("page");
        var LAYOUT = { kind: "split", orientation: "horizontal", children: [
            { node: { kind: "cell", id: "nav" }, ratio: 1 },
            { node: { kind: "split", orientation: "vertical", children: [
                { node: { kind: "cell", id: "demo" }, ratio: 3 },
                { node: { kind: "cell", id: "explain" }, ratio: 1 } ] }, ratio: 3 } ] };
        var grid = new SplitGrid(branch.createBranch("grid"), { host: host, layout: LAYOUT, minCellPx: 100, resizable: true,
            onEvent: function (ev) {
                if (ev.kind === "TracksChanged") log.push("tracks:" + ev.path + ":" + ev.ratios.map(function (r) { return r.toFixed(2); }).join(","));
                else if (ev.kind === "Subdivided") log.push("subdivided:" + ev.cellId + "+" + ev.newCellId + ":" + ev.side);
                else log.push("removed:" + ev.cellId);
            } });
        function root() { return grid.el.children[0]; }
        function shape(s) {
            if (s.has("sg_cell")) return s.getAttribute("data-cell");
            return (s.has("sg_split_h") ? "row[" : "col[") + s.children.map(function (c) { return c.has("sg_line") ? "|" : shape(c.children[0]); }).join(" ") + "]";
        }
        function shares(s) { return s.children.filter(function (c) { return c.has("sg_child"); }).map(function (c) { return (+c.style.getPropertyValue("--sg-ratio")).toFixed(2); }).join(","); }
        function divider(s, i) { return s.children.filter(function (c) { return c.has("sg_line"); })[i]; }
        function drag(d, from, to, end) { d.fire("pointerdown", { button: 0, clientX: from.x, clientY: from.y, pointerId: 1 }); d.fire("pointermove", { clientX: to.x, clientY: to.y }); d.fire(end || "pointerup", { type: end || "pointerup" }); }
        var content = el("widget"); grid.cell("demo").appendChild(content);
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(P + "SplitGridEventsModule.js");
        loadModule(P + "SplitGridTreeModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/sizing/RailModule.js");
        js.eval("js", "var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); } }; var sz_handle = 'sz_handle', sz_handle_h = 'sz_handle_h', sz_handle_v = 'sz_handle_v';");
        loadModule("/homing/js/hue/captains/singapura/js/homing/sizing/SizingHandleModule.js");
        loadModule(P + "SplitGridGeometryModule.js");
        // each line the house's boundary line: its castings, its states, its look and the simple component it is
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet', ICONS = {};");
        loadModule("/homing/js/hue/captains/singapura/server/StyledLeaves.js".replace("singapura/server", "singapura/js/homing/server"));
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");
        for (String m : new String[] {"SimpleComponentModule.js", "MarkBehaviorModule.js", "BoundaryLineModule.js"})
            loadModule("/homing/js/hue/captains/singapura/js/homing/ui/elements/" + m);
        loadModule(P + "SplitGridModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    @Test
    void theTreeIsRenderedAsRowsAndColumns_theCellsTheOwnersToFill() {
        assertEquals("row[nav | col[demo | explain]]", eval("shape(root())").asString());
        assertEquals("0.25,0.75", eval("shares(root())").asString());
        assertEquals("0.75,0.25", eval("shares(root().children[2].children[0])").asString());
        assertEquals("nav,demo,explain", eval("grid.cells().join(',')").asString());
        assertTrue(eval("grid.cell('demo').children[0] === content").asBoolean(), "the owner's content is in the cell");
        assertTrue(eval("grid.cell('nope') === null").asBoolean());
        assertEquals("100px", eval("grid.el.style.getPropertyValue('--sg-min')").asString());
    }

    @Test
    void aLineInTheHand_holdsItsDraggingState_fromThePressToTheRelease_throughItsHandle() {
        eval("var l = divider(root(), 0); l.fire('pointerdown', { button: 0, clientX: 250, clientY: 10, pointerId: 1 })");
        assertTrue(eval("l.has('styled-boundary-line_splitter--dragging') && l.hasAttribute('data-dragging')").asBoolean(), "taken: its Dragging state's class held");
        eval("l.fire('pointerup', { clientX: 250, clientY: 10, pointerId: 1 })");
        assertTrue(eval("!l.has('styled-boundary-line_splitter--dragging') && !l.hasAttribute('data-dragging')").asBoolean(), "let go: off again");
        assertTrue(eval("l.has('styled-boundary-line_splitter--vertical')").asBoolean(), "upright between cells side by side: its Orientation's class");
    }

    @Test
    void theLinesAreTheGridsPart_aSplitterWhereItIsResizable_aSeamWhereItIsNot() {
        assertTrue(eval("var l = divider(root(), 0); l.has('styled-boundary-line_splitter') && l.has('sz_handle') && l.has('sz_handle_h') && !l.has('sg_line_fixed')"
                + " && l.getAttribute('role') === 'separator' && l.getAttribute('aria-orientation') === 'vertical'").asBoolean(), "the hand moves it");
        assertEquals("25", eval("divider(root(), 0).getAttribute('aria-valuenow')").asString(), "it says where it stands");
        assertTrue(eval("divider(root().children[2].children[0], 0).has('sz_handle_v')").asBoolean(), "the line lying down moves up and down");
        eval("var fixed = new SplitGrid(branch.createBranch('fixed'), { host: el('div'), layout: LAYOUT });");
        assertTrue(eval("var f = divider(fixed.el.children[0], 0); f.has('styled-boundary-line') && f.has('sg_line_fixed') && !f.has('sz_handle') && f.getAttribute('aria-valuenow') === null").asBoolean(),
                "not resizable: a seam, no hand on it");
        assertTrue(eval("grid.resizable() && !fixed.resizable()").asBoolean());
        var ex = assertThrows(PolyglotException.class, () -> eval("fixed.setRatios('', [1, 1])"));
        assertTrue(ex.getMessage().contains("not resizable"), ex.getMessage());
        assertEquals("0.25,0.75", eval("shares(fixed.el.children[0])").asString(), "laid out in the proportions it was given");
    }

    @Test
    void aPartHoldingASplit_isAsLeastAsTheCellsAcrossIt() {
        assertEquals("100px", eval("root().children[2].style.getPropertyValue('--sg-least')").asString(), "a column of two: one cell across");
        eval("grid.subdivide('demo', 'right')");
        assertEquals("200px", eval("root().children[2].style.getPropertyValue('--sg-least')").asString(), "two cells across now");
        eval("log.length = 0; drag(divider(root(), 0), { x: 250, y: 0 }, { x: 2000, y: 0 });");
        assertEquals("tracks::0.80,0.20", log().replaceAll("dissolved:\\S+ subdivided:\\S+ ", ""), "the drag stops where the column would be narrower than its two cells and its line");
    }

    @Test
    void subdivide_besideACell_aSiblingWhenTheOrientationMatches_aNestWhenNot() {
        // right of demo, in a column: not the column's orientation → demo becomes a row of two
        assertEquals("cell-4", eval("grid.subdivide('demo', 'right')").asString());
        assertEquals("row[nav | col[row[demo | cell-4] | explain]]", eval("shape(root())").asString());
        assertEquals("dissolved:arrangement-1 subdivided:demo+cell-4:right", log(), "the old arrangement is dissolved, the cells kept");
        assertTrue(eval("grid.cell('demo').children[0] === content").asBoolean(), "the cell kept what the owner put in it");
        // below explain, in the column: the column's orientation → a sibling, the room halved
        eval("log.length = 0; grid.subdivide('explain', 'bottom', 'notes');");
        assertEquals("row[nav | col[row[demo | cell-4] | explain | notes]]", eval("shape(root())").asString());
        assertEquals("0.75,0.13,0.13", eval("shares(root().children[2].children[0])").asString());
        assertEquals("dissolved:arrangement-2 subdivided:explain+notes:bottom", log());
        // left of nav, at the root row: a sibling first
        eval("log.length = 0; grid.subdivide('nav', 'left', 'rail');");
        assertEquals("row[rail | nav | col[row[demo | cell-4] | explain | notes]]", eval("shape(root())").asString());
        assertEquals("0.13,0.13,0.75", eval("shares(root())").asString());
        assertEquals("rail,nav,demo,cell-4,explain,notes", eval("grid.cells().join(',')").asString());
        assertEquals("split", eval("grid.layout().kind").asString());
    }

    @Test
    void subdivide_theOnlyCell_makesTheRootASplit() {
        eval("var one = new SplitGrid(branch.createBranch('one'), { host: el('div'), layout: { kind: 'cell', id: 'a' } }); one.subdivide('a', 'bottom', 'b');");
        assertEquals("col[a | b]", eval("shape(one.el.children[0])").asString());
        assertEquals("a,b", eval("one.cells().join(',')").asString());
    }

    @Test
    void remove_givesTheRoomToTheNeighbour_andASplitOfOneGivesWay() {
        eval("var gone = grid.remove('explain');");
        assertEquals("dissolved:arrangement-1 removed:explain", log());
        assertEquals("row[nav | demo]", eval("shape(root())").asString(), "the column of one gave way to demo");
        assertEquals("0.25,0.75", eval("shares(root())").asString());
        assertTrue(eval("gone.has('sg_cell') && gone.parentNode === null").asBoolean(), "the cell's element is handed back, detached");
        assertTrue(eval("grid.cell('explain') === null && grid.cell('demo').children[0] === content").asBoolean());
        eval("log.length = 0; grid.remove('nav');");
        assertEquals("demo", eval("shape(root())").asString(), "the root row of one gave way to demo");
        assertEquals("dissolved:arrangement-2 removed:nav", log());
        var ex = assertThrows(PolyglotException.class, () -> eval("grid.remove('demo')"));
        assertTrue(ex.getMessage().contains("last cell"), ex.getMessage());
    }

    @Test
    void splitters_areThePanesTheRoomCanGoToWhole() {
        // nav's one neighbour is a column of two, so the divider beside it is shared: nothing there can take its room whole
        assertEquals("", eval("grid.splitters('nav').map(function (s) { return s.side + ':' + s.id; }).join(',')").asString());
        assertEquals("after:explain", eval("grid.splitters('demo').map(function (s) { return s.side + ':' + s.id; }).join(',')").asString());
        // a same-axis nesting, which a removal leaves behind: the pane facing the divider is found through it, from either side
        eval("grid.subdivide('explain', 'right', 'note'); grid.remove('demo');");
        assertEquals("row[nav | row[explain | note]]", eval("shape(root())").asString());
        assertEquals("after:explain", eval("grid.splitters('nav').map(function (s) { return s.side + ':' + s.id; }).join(',')").asString(),
                     "the divider is the outer row's, and explain alone faces it");
        assertEquals("after:note,before:nav", eval("grid.splitters('explain').map(function (s) { return s.side + ':' + s.id; }).join(',')").asString(),
                     "the same divider from the other side: found by climbing, not by looking at its own siblings");
        assertEquals("before:explain", eval("grid.splitters('note').map(function (s) { return s.side + ':' + s.id; }).join(',')").asString());
    }

    @Test
    void remove_towardACell_givesItTheRoomWholeAcrossASplitter_orLeansToTheNeighbourHoldingIt() {
        // the neighbour holding the target takes the room, though the one before it is what an unnamed removal would pick
        eval("grid.subdivide('demo', 'top', 'head'); grid.remove('demo', 'explain');");
        assertEquals("0.38,0.63", eval("shares(root().children[2].children[0])").asString(), "explain took it, not head");
        // and across a splitter of their own, the pane facing it takes the whole room: note keeps the size it had
        eval("grid.subdivide('explain', 'right', 'note'); grid.remove('head');");
        assertEquals("row[nav | row[explain | note]]", eval("shape(root())").asString());
        assertEquals("0.25,0.75", eval("shares(root())").asString());
        eval("log.length = 0; var went = grid.remove('nav', 'explain');");
        assertEquals("row[explain | note]", eval("shape(root())").asString(), "the row of one gave way");
        assertEquals("0.63,0.38", eval("shares(root())").asString(), "nav's quarter went to explain alone");
        assertTrue(eval("went.has('sg_cell') && went.parentNode === null").asBoolean(), "the cell's element is handed back, detached");
        assertEquals("dissolved:arrangement-5 removed:nav", log());
    }

    /** The bug: a fresh id was counted from the cells there now, so a gone cell's came back, and its element's name was still held. */
    @Test
    void aFreshIdIsNoneACellOfTheGridHasHad_soAGoneCellsNeverComesBack() {
        assertEquals("cell-4", eval("grid.subdivide('demo', 'right')").asString());
        eval("grid.remove('cell-4')");
        assertEquals("cell-5", eval("grid.subdivide('demo', 'right')").asString(), "not cell-4 again");
        assertEquals("row[nav | col[row[demo | cell-5] | explain]]", eval("shape(root())").asString());
        // an id the owner gave is held too: the grid does not hand it out after
        eval("grid.subdivide('explain', 'bottom', 'cell-6'); grid.remove('cell-6');");
        assertEquals("cell-7", eval("grid.subdivide('nav', 'bottom')").asString());
        assertEquals("row[col[nav | cell-7] | col[row[demo | cell-5] | explain]]", eval("shape(root())").asString());
        assertTrue(eval("grid.cell('demo').children[0] === content").asBoolean(), "nothing was lost on the way");
    }

    @Test
    void anOwnerMayNameACellAfterOneItRemoved_andGetsANewCell() {
        eval("var first = grid.cell(grid.subdivide('demo', 'right', 'side')); first.appendChild(el('old')); var gone = grid.remove('side');");
        eval("log.length = 0; grid.subdivide('demo', 'right', 'side');");
        assertEquals("subdivided:demo+side:right", log().replaceAll("dissolved:\\S+ ", ""));
        assertEquals("row[nav | col[row[demo | side] | explain]]", eval("shape(root())").asString());
        assertTrue(eval("grid.cell('side') !== gone && grid.cell('side').children.length === 0").asBoolean(), "a new, empty cell, not the one handed back");
        eval("grid.remove('explain')");
        assertEquals("row[nav | row[demo | side]]", eval("shape(root())").asString(), "and the grid goes on re-arranging");
    }

    @Test
    void twoIdsOfOneSafeSpellingAreTwoCells() {
        eval("grid.subdivide('demo', 'right', 'a:b'); grid.subdivide('explain', 'right', 'a_b');");
        assertEquals("row[nav | col[row[demo | a:b] | row[explain | a_b]]]", eval("shape(root())").asString());
        assertTrue(eval("grid.cell('a:b') !== grid.cell('a_b')").asBoolean());
    }

    @Test
    void theTracksAreReSharedByADragAndByTheMethod_reportedOnce() {
        eval("log.length = 0; drag(divider(root(), 0), { x: 250, y: 0 }, { x: 500, y: 0 });");
        assertEquals("tracks::0.50,0.50", log());
        assertEquals("0.50,0.50", eval("shares(root())").asString());
        eval("log.length = 0; grid.setRatios('1', [1, 1]);");
        assertEquals("tracks:1:0.50,0.50", log());
        eval("log.length = 0; drag(divider(root(), 0), { x: 500, y: 0 }, { x: 900, y: 0 }, 'pointercancel');");
        assertEquals("", log(), "a cancelled drag goes back and says nothing");
        assertEquals("0.50,0.50", eval("shares(root())").asString());
        // after a re-arrangement the dividers are new and still drag the right split
        eval("grid.subdivide('nav', 'left', 'rail'); log.length = 0; drag(divider(root(), 1), { x: 500, y: 0 }, { x: 400, y: 0 });");
        assertEquals("tracks::0.25,0.15,0.60", log());
    }
}
