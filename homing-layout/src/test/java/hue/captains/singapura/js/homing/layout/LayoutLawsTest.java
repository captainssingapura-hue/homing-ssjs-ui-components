package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.js.homing.layout.Layout.Horizontal_NSplit;
import hue.captains.singapura.js.homing.layout.Layout.Vertical_NSplit;
import hue.captains.singapura.js.homing.layout.Layout.Whole;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The layout algebra held to its theorems (RFC 0068, appendix A), over random terms and random
 * walks of edits: one normal form; the splits associative, the empty layout their unit; the
 * edits' laws; every layout reached by subdividing, as many as the large Schröder numbers say.
 */
class LayoutLawsTest {

    private static final LayoutAlgebra A = LayoutAlgebra.INSTANCE;
    private static final int RUNS = 3000;

    private static CellId id(String s) { return new CellId(s); }
    private static Layout parse(String s) { return A.parse(s).orElseThrow(); }

    /** A layout reached from one cell by random subdivides and removes. */
    private static Layout walk(Random r, int steps, String prefix) {
        Layout l = A.whole(prefix + "0");
        int n = 1;
        for (int s = 0; s < steps; s++) {
            var cells = l.cells();
            if (cells.size() > 1 && r.nextInt(10) < 3) l = l.remove(cells.get(r.nextInt(cells.size()))).orElseThrow();
            else l = l.subdivide(cells.get(r.nextInt(cells.size())), Side.values()[r.nextInt(4)], id(prefix + n++));
        }
        return l;
    }

    /** Any term over the ids, in their order: splits in splits of their own axis, splits of one, ε among the parts. */
    private static String term(Random r, List<String> ids) {
        if (ids.isEmpty()) return "ε";
        if (ids.size() == 1) return r.nextInt(5) == 0 ? (r.nextBoolean() ? "H" : "V") + "(ε, " + ids.get(0) + ")" : ids.get(0);
        var parts = new ArrayList<String>();
        var rest = new ArrayList<>(ids);
        while (!rest.isEmpty()) {
            int take = 1 + r.nextInt(rest.size());
            parts.add(term(r, new ArrayList<>(rest.subList(0, take))));
            rest.subList(0, take).clear();
        }
        if (parts.size() == 1) parts.add("ε");
        return (r.nextBoolean() ? "H" : "V") + "(" + String.join(", ", parts) + ")";
    }

    /** The structure with the names forgotten: the shape, its cells in order. */
    private static String shape(Layout l) {
        return switch (l) {
            case Whole w -> "*";
            case Horizontal_NSplit h -> "H(" + String.join(",", h.parts().stream().map(LayoutLawsTest::shape).toList()) + ")";
            case Vertical_NSplit v -> "V(" + String.join(",", v.parts().stream().map(LayoutLawsTest::shape).toList()) + ")";
        };
    }

    @Test
    void theTypes_refuseWhatIsNoLayout() {
        Layout.Horizontal_Part a = new Whole(id("a"));
        assertThrows(IllegalArgumentException.class, () -> new Horizontal_NSplit(List.of(a)), "a split of one");
        assertThrows(IllegalArgumentException.class, () -> new Horizontal_NSplit(List.of(a, new Whole(id("a")))), "a cell twice");
        assertThrows(IllegalArgumentException.class, () -> parse("H(a, V(b, a))"), "a cell twice, deeper");
        assertThrows(IllegalArgumentException.class, () -> id("a b"), "a name with a space");
        assertThrows(IllegalArgumentException.class, () -> A.parse("H(a, b"), "an unclosed split");
        // a row inside a row is not refused, it cannot be written: Horizontal_NSplit holds Horizontal_Parts, a whole or a stack
        assertEquals(Optional.empty(), A.parse("ε"));
    }

    @Test
    void everyTerm_hasOneNormalForm() {
        assertEquals(parse("H(a, b, c)"), parse("H(a, H(b, c))"));
        assertEquals(parse("H(a, b, c)"), parse("H(H(a, b), c)"));
        assertEquals(parse("V(a, H(b, c))"), parse("V(a, H(ε, b, H(c)))"));
        assertEquals("H(V(a, b), c, d)", parse("H(V(a, b), H(c, d))").text());
        var r = new Random(7);
        for (int i = 0; i < RUNS; i++) {
            var ids = new ArrayList<String>();
            for (int k = 0, n = 1 + r.nextInt(8); k < n; k++) ids.add("x" + k);
            String t = term(r, ids);
            Layout l = parse(t);
            assertEquals(l, parse(l.text()), "read back: " + t);
            assertEquals(ids, l.cells().stream().map(CellId::value).toList(), "the cells in order: " + t);
            assertEquals(l.hashCode(), parse(l.text()).hashCode());
        }
    }

    @Test
    void theSplits_areAssociative_theEmptyLayoutTheirUnit() {
        var r = new Random(11);
        for (int i = 0; i < RUNS; i++) {
            Layout x = walk(r, r.nextInt(4), "a"), y = walk(r, r.nextInt(4), "b"), z = walk(r, r.nextInt(4), "c");
            for (Axis axis : Axis.values()) {
                assertEquals(A.split(axis, A.split(axis, x, y), z), A.split(axis, x, A.split(axis, y, z)));
                assertEquals(A.split(axis, x, y, z), A.split(axis, x, A.split(axis, y, z)));
                assertEquals(Optional.of(x), A.join(axis, List.of(Optional.empty(), Optional.of(x))));
                assertEquals(Optional.of(x), A.join(axis, List.of(Optional.of(x), Optional.empty())));
            }
            assertFalse(A.split(Axis.HORIZONTAL, x, y).equals(A.split(Axis.HORIZONTAL, y, x)), "neither split commutes");
        }
        // no interchange: a line across the whole is not a line down the whole
        assertFalse(parse("V(H(a, b), H(c, d))").equals(parse("H(V(a, c), V(b, d))")));
    }

    @Test
    void theEdits_keepTheirLaws() {
        var r = new Random(13);
        for (int i = 0; i < RUNS; i++) {
            Layout l = walk(r, 1 + r.nextInt(12), "c");
            var cells = l.cells();
            CellId c = cells.get(r.nextInt(cells.size()));
            Side side = Side.values()[r.nextInt(4)];
            CellId n = id("new");
            Layout s = l.subdivide(c, side, n);
            // order: the new cell next to the old, on its side
            int at = s.cells().indexOf(c), nw = s.cells().indexOf(n);
            assertEquals(side.before() ? at - 1 : at + 1, nw, l.text() + " " + c + " " + side);
            // inverse
            assertEquals(Optional.of(l), s.remove(n), l.text() + " " + c + " " + side);
            // symmetry: transposing and mirroring commute with the edits
            assertEquals(s.transpose(), l.transpose().subdivide(c, side.transposed(), n));
            assertEquals(s.mirror(), l.mirror().subdivide(c, side.mirrored(), n));
            assertEquals(l, l.transpose().transpose());
            assertEquals(l, l.mirror().mirror());
            if (cells.size() >= 2) {
                Layout rm = l.remove(c).orElseThrow();
                assertEquals(cells.stream().filter(x -> !x.equals(c)).toList(), rm.cells(), "remove keeps the others in order");
                assertEquals(Optional.of(rm.transpose()), l.transpose().remove(c));
                assertEquals(Optional.of(rm.mirror()), l.mirror().remove(c));
                CellId d = cells.stream().filter(x -> !x.equals(c)).toList().get(r.nextInt(cells.size() - 1));
                // independence: removes of two cells commute, subdivides of two cells commute
                assertEquals(rm.remove(d), l.remove(d).orElseThrow().remove(c));
                assertEquals(l.subdivide(c, side, n).subdivide(d, Side.BOTTOM, id("m")), l.subdivide(d, Side.BOTTOM, id("m")).subdivide(c, side, n));
                // a swap twice is nothing; a move keeps the cells
                assertEquals(l, l.swap(c, d).swap(c, d));
                assertEquals(Set.copyOf(cells), Set.copyOf(l.move(c, d, side).cells()));
                assertTrue(l.move(c, d, side).neighbours(c).contains(d), "moved beside it: " + l.move(c, d, side).text());
            } else {
                assertEquals(Optional.empty(), l.remove(c), "the last cell leaves nothing");
            }
        }
    }

    @Test
    void everyLayout_isReachedBySubdividing_asManyAsTheLargeSchroederNumbers() {
        List<Integer> schroeder = List.of(1, 2, 6, 22, 90, 394, 1806);
        Map<String, Layout> layer = new HashMap<>(Map.of(shape(A.whole("c")), A.whole("c")));
        for (int k = 1; k <= schroeder.size(); k++) {
            assertEquals(schroeder.get(k - 1), layer.size(), "shapes of " + k + " cells");
            var next = new HashMap<String, Layout>();
            for (Layout l : layer.values()) for (CellId c : l.cells()) for (Side s : Side.values()) {
                Layout u = l.subdivide(c, s, id("n" + k));
                next.putIfAbsent(shape(u), u);
            }
            layer = next;
        }
    }

    @Test
    void neighbours_areTheWholesBesideACell_inTheSplitHoldingIt() {
        Layout l = parse("H(a, V(b, c), d, e)");
        assertEquals(List.of(), l.neighbours(id("a")), "beside a is a stack, not a whole");
        assertEquals(List.of(id("c")), l.neighbours(id("b")));
        assertEquals(List.of(id("e")), l.neighbours(id("d")));
        assertEquals(List.of(id("d")), l.neighbours(id("e")));
        assertEquals("H(V(a, b), c, d)", parse("H(V(a, b, x), c, d)").remove(id("x")).orElseThrow().text());
        assertEquals("H(a, c, d)", parse("H(V(a, b), c, d)").remove(id("b")).orElseThrow().text(), "the stack gives way, a joins the row");
        assertEquals(new LinkedHashSet<>(List.of(id("a"))), new LinkedHashSet<>(parse("V(a)").cells()));
    }
}
