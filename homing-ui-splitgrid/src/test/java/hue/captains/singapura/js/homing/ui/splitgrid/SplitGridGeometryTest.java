package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The geometry, headless: a tree resolved into rectangles as flex would —
 * dividers taken out of the room first, the minimum held and the rest
 * re-shared, by the rail's lay — the workspace's neighbour rule on the
 * rectangles, and the thing under a point. The lay and the move are sizing's,
 * and tested there.
 */
class SplitGridGeometryTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/splitgrid/";

    // the gallery shell's layout: a rail beside a column of two, at 1 : 3 and 3 : 1
    private static final String SHIM = """
        var LAYOUT = SplitGridTree.validate({ kind: "split", orientation: "horizontal", children: [
            { node: { kind: "cell", id: "nav" }, ratio: 1 },
            { node: { kind: "split", orientation: "vertical", children: [
                { node: { kind: "cell", id: "demo" }, ratio: 3 },
                { node: { kind: "cell", id: "explain" }, ratio: 1 } ] }, ratio: 3 } ] });
        var R = SplitGridGeometry.rects(LAYOUT, { w: 1007, h: 607 }, { dividerPx: 7, minPx: 40 });
        function rect(id) { var c = R.cells[id]; return [c.x, c.y, c.w, c.h].map(function (v) { return Math.round(v); }).join(","); }
        function around(v) { return Math.round(v * 100) / 100; }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(P + "SplitGridTreeModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/sizing/RailModule.js");
        loadModule(P + "SplitGridGeometryModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void rects_shareTheRoomLeftAfterTheDividers_asFlexDoes() {
        // 1007 wide, one 7px divider: 1000 to share at 1 : 3 → 250 and 750; the column 607 tall, one divider: 600 at 3 : 1 → 450 and 150
        assertEquals("0,0,250,607", eval("rect('nav')").asString());
        assertEquals("257,0,750,450", eval("rect('demo')").asString());
        assertEquals("257,457,750,150", eval("rect('explain')").asString());
        assertEquals("|0:250,0,7,607 1|0:257,450,750,7", eval("R.dividers.map(function (d) { return d.path + '|' + d.index + ':' + [d.x, d.y, d.w, d.h].map(Math.round).join(','); }).join(' ')").asString());
    }

    @Test
    void neighbour_isTheWorkspacesRule_sharedEdge_nearestGap_largestOverlap() {
        assertEquals("demo", eval("SplitGridGeometry.neighbour(R, 'nav', 'right')").asString(), "two candidates on the right: the one with the larger overlap");
        assertEquals("nav", eval("SplitGridGeometry.neighbour(R, 'demo', 'left')").asString());
        assertEquals("nav", eval("SplitGridGeometry.neighbour(R, 'explain', 'left')").asString());
        assertEquals("explain", eval("SplitGridGeometry.neighbour(R, 'demo', 'down')").asString());
        assertEquals("demo", eval("SplitGridGeometry.neighbour(R, 'explain', 'up')").asString());
        assertEquals("true", eval("String(SplitGridGeometry.neighbour(R, 'nav', 'left') === null)").asString(), "an outer edge");
        assertEquals("true", eval("String(SplitGridGeometry.neighbour(R, 'nav', 'up') === null && SplitGridGeometry.neighbour(R, 'demo', 'right') === null)").asString());
        assertEquals("true", eval("String(SplitGridGeometry.neighbour(R, 'nope', 'up') === null)").asString(), "an unknown cell");
        // a column of three beside one: from the tall one, right lands on the middle when it overlaps most
        eval("var L3 = SplitGridTree.validate({ kind: 'split', orientation: 'horizontal', children: [ { node: { kind: 'cell', id: 'a' } }, { node: { kind: 'split', orientation: 'vertical', children: [ { node: { kind: 'cell', id: 'b' }, ratio: 1 }, { node: { kind: 'cell', id: 'c' }, ratio: 2 }, { node: { kind: 'cell', id: 'd' }, ratio: 1 } ] } } ] }); var R3 = SplitGridGeometry.rects(L3, { w: 1000, h: 1000 });");
        assertEquals("c", eval("SplitGridGeometry.neighbour(R3, 'a', 'right')").asString());
        assertEquals("a", eval("SplitGridGeometry.neighbour(R3, 'd', 'left')").asString());
    }

    @Test
    void hit_findsTheDividerFirst_thenTheCell() {
        assertEquals("divider:|0", eval("var h = SplitGridGeometry.hit(R, 252, 10); h.kind + ':' + h.path + '|' + h.index").asString());
        assertEquals("cell:explain", eval("var h = SplitGridGeometry.hit(R, 600, 500); h.kind + ':' + h.id").asString());
        assertEquals("true", eval("String(SplitGridGeometry.hit(R, 2000, 10) === null)").asString());
    }
}
