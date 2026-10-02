package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The dock grid over a fake split grid, fake docks and a fake desk: what the
 * grid itself decides — a region per cell, each dock on the desk and in its
 * cell with the strip's menu; a part makes an empty region; a merge is
 * PaneMerge's plan, all of it or none; close goes to the heir; the ground
 * menu's rows and picks; one sink.
 */
class DockGridTest extends JsModuleTestBase {

    private static final String SHIM = """
        var log = [], events = [];
        function fakeBranch(name) {
            var kids = new Set();
            return { name: name, active: false, dissolved: false,
                activate: function () { if (this.active) throw new Error("activated twice: " + name); this.active = true; },
                createBranch: function (n) { if (kids.has(n)) throw new Error("branch " + n + " taken on " + name); kids.add(n); return fakeBranch(n); },
                dissolve: function () { this.dissolved = true; } };
        }
        // the grid: cells in order, a table of what lies across each splitter and who inherits a room
        var across = {}, heirs = {}, n = 0;
        class SplitGrid {
            constructor(branch, o) { this.o = o; this._cells = []; this._els = {};
                var self = this; (function walk(t) { if (t.kind === "cell") self._cells.push(t.id); else t.children.forEach(function (c) { walk(c.node); }); })(o.layout); }
            cell(id) { return this._els[id] || (this._els[id] = { cell: id }); }
            cells() { return this._cells.slice(); }
            subdivide(id, side) { var made = "c" + (++n); this._cells.splice(this._cells.indexOf(id) + 1, 0, made); log.push("subdivide:" + id + ":" + side); this.o.onEvent({ kind: "Subdivided", cellId: id, side: side, newId: made }); return made; }
            splitters(id) { return across[id] || []; }
            heirs(id) { return heirs[id] || []; }
            remove(id, toward) { this._cells.splice(this._cells.indexOf(id), 1); log.push("remove:" + id + ":" + toward); this.o.onEvent({ kind: "Removed", cellId: id }); }
            dispose() { log.push("grid:disposed"); }
        }
        class MultiTabPane {
            constructor(branch, o) { this.o = o; this.slotId = o.slotId; this._tabs = []; }
            tabs() { return this._tabs.slice(); }
            tabPaneOf(id) { return this._tabs.indexOf(id) >= 0 ? { id: id, from: this } : null; }
            dispose() { log.push("disposed:" + this.slotId); }
        }
        var desk = { docks: [],
            addDock: function (d) { this.docks.push(d); log.push("addDock:" + d.slotId); },
            removeDock: function (d) { this.docks.splice(this.docks.indexOf(d), 1); log.push("removeDock:" + d.slotId); },
            move: function (tp, to) { tp.from._tabs.splice(tp.from._tabs.indexOf(tp.id), 1); to._tabs.push(tp.id); log.push("move:" + tp.id + "->" + to.slotId); } };
        var menus = { handlers: {}, handle: function (kind, h) { this.handlers[kind] = h; return this; } };
        function two(extra) {
            return new DockGrid(fakeBranch("docks"), Object.assign({ host: {}, desk: desk, menus: menus, dock: { addable: false },
                onEvent: function (ev) { events.push(ev.kind); },
                layout: { kind: "split", orientation: "horizontal", children: [ { node: { kind: "cell", id: "left" } }, { node: { kind: "cell", id: "right" } } ] } }, extra || {}));
        }
        function dockOf(dg, id) { return dg.region(id).dock; }
        function row(id, pane) { return menus.handlers.split.state(id, { pane: pane }); }
        function pick(id, pane) { menus.handlers.split.pick(id, { pane: pane }); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/PaneMergeModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/docking/DockGridModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void aRegionPerCell_eachDockOnTheDeskAndInItsCell_withTheStripsMenu() {
        eval("var dg = two()");
        assertEquals("left,right", eval("dg.regions().map(function (r) { return r.id; }).join()").asString());
        assertEquals("addDock:left addDock:right", eval("log.join(' ')").asString());
        Value o = eval("dockOf(dg, 'left').o");
        assertTrue(eval("dockOf(dg, 'left').o.host === dg.grid.cell('left')").asBoolean(), "its cell is its host");
        assertEquals("left", o.getMember("slotId").asString());
        assertEquals("split", o.getMember("stripMenu").asString(), "the ground menu is the grid's");
        assertFalse(o.getMember("addable").asBoolean(), "and what every dock is made with, passed through");
        assertEquals("dock-1,dock-2", eval("dg.regions().map(function (r) { return r.dock.o.focusName; }).join()").asString(), "named by a count, never by a cell's id");
        assertTrue(eval("dg.regionOf(dockOf(dg, 'right')).id === 'right'").asBoolean());
        assertEquals("main", eval("new DockGrid(fakeBranch('one'), { host: {}, desk: desk }).regions()[0].id").asString(), "one cell unless said");
    }

    @Test
    void aPart_isAnEmptyRegionInANewCell() {
        eval("var dg = two(); log.length = 0; var made = dg.part('left', 'right')");
        assertEquals("subdivide:left:right addDock:c1", eval("log.join(' ')").asString());
        assertEquals("left,right,c1", eval("dg.regions().map(function (r) { return r.id; }).join()").asString());
        assertEquals(0, eval("made.dock.tabs().length").asInt());
        assertThrows(Exception.class, () -> eval("dg.part('nowhere', 'right')"));
    }

    @Test
    void aMerge_movesEveryTabInThePlansOrder_thenTheDockAndTheCellGo() {
        eval("var dg = two(); dockOf(dg, 'left')._tabs = ['a', 'b']; dockOf(dg, 'right')._tabs = ['c']; log.length = 0; var p = dg.merge('left', 'right')");
        assertTrue(eval("p.ok").asBoolean());
        assertEquals("move:a->right move:b->right removeDock:left disposed:left remove:left:right", eval("log.join(' ')").asString(),
                "every tab first, then the dock off the desk and gone, then the cell - its room toward the one named");
        assertEquals("c,a,b", eval("dockOf(dg, 'right').tabs().join()").asString());
        assertEquals("right", eval("dg.regions().map(function (r) { return r.id; }).join()").asString());
    }

    @Test
    void aMergeThePlanRefuses_movesNothing_andTheLastRegionStays() {
        eval("var dg = two(); dockOf(dg, 'left')._tabs = ['a']; dockOf(dg, 'right')._tabs = ['a']; log.length = 0; var p = dg.merge('left', 'right')");
        assertFalse(eval("p.ok").asBoolean(), "the same tab on both: refused");
        assertEquals("", eval("log.join(' ')").asString(), "and nothing moved");
        eval("dockOf(dg, 'left')._tabs = []; dg.merge('left', 'right'); var last = dg.close('right')");
        assertEquals("last", eval("last.reason").asString(), "no heir: nowhere for its room to go");
        assertEquals("right", eval("dg.regions().map(function (r) { return r.id; }).join()").asString(), "the last region stays");
    }

    @Test
    void close_isTheMergeIntoTheHeir() {
        eval("var dg = two(); heirs.left = ['right']; dockOf(dg, 'left')._tabs = ['a']; log.length = 0; dg.close('left')");
        assertEquals("move:a->right removeDock:left disposed:left remove:left:right", eval("log.join(' ')").asString());
    }

    @Test
    void theGroundMenu_offersWhatThereIs_andItsPicksAreTheGrids() {
        eval("var dg = two(); var L = dockOf(dg, 'left'), R = dockOf(dg, 'right'); across.left = [{ side: 'after', axis: 'horizontal', id: 'right' }]; across.right = [{ side: 'before', axis: 'horizontal', id: 'left' }]");
        assertTrue(eval("row('merge-left', L).hidden && !row('merge-right', L).hidden").asBoolean(), "a direction with nothing across a splitter of its own is not offered");
        assertTrue(eval("row('merge-into', L).hidden").asBoolean(), "no chooser handed in: no Merge into…");
        assertFalse(eval("row('close', L).disabled").asBoolean());
        assertTrue(eval("row('beside', { slotId: 'afloat' }).disabled").asBoolean(), "a dock afloat is not a region");
        eval("L._tabs = ['a']; R._tabs = ['a']");
        assertTrue(eval("row('merge-right', L).disabled").asBoolean(), "offered, and refused: the plan says why");
        eval("R._tabs = []; log.length = 0; pick('beside', L)");
        assertEquals("subdivide:left:right addDock:c1", eval("log.join(' ')").asString());
        eval("log.length = 0; pick('merge-right', L)");
        assertEquals("move:a->right removeDock:left disposed:left remove:left:right", eval("log.join(' ')").asString());
        assertTrue(eval("row('close', R).disabled === false && row('below', R).disabled === false").asBoolean());
    }

    @Test
    void mergeInto_asksTheHolderWhich() {
        eval("var asked = null; var dg = two({ chooseRegion: function (from, regions, pick) { asked = { from: from.id, of: regions.map(function (r) { return r.id; }).join(), pick: pick }; } });");
        eval("var L = dockOf(dg, 'left'); L._tabs = ['a']");
        assertFalse(eval("row('merge-into', L).hidden").asBoolean(), "offered when the holder can ask");
        eval("pick('merge-into', L)");
        assertEquals("left:right", eval("asked.from + ':' + asked.of").asString(), "the region, and the others");
        eval("log.length = 0; asked.pick(dg.region('right'))");
        assertEquals("move:a->right removeDock:left disposed:left remove:left:right", eval("log.join(' ')").asString());
    }

    @Test
    void theGridsEventsAndTheDocks_onOneSink_andDisposeTakesTheDocksThenTheGrid() {
        eval("var dg = two(); dg.part('left', 'bottom'); dockOf(dg, 'left').o.onEvent({ kind: 'TabActivated' })");
        assertEquals("Subdivided,TabActivated", eval("events.join()").asString());
        eval("log.length = 0; dg.dispose()");
        assertEquals("removeDock:left disposed:left removeDock:right disposed:right removeDock:c1 disposed:c1 grid:disposed", eval("log.join(' ')").asString());
    }
}
