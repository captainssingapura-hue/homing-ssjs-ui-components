package hue.captains.singapura.js.homing.layout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A layout, as structure alone (RFC 0068, appendix A): a whole - one cell - or an n-split of two
 * or more parts along an axis, a horizontal one in a row, a vertical one in a stack. A split never
 * holds one of its own axis: the types say so, so the axes alternate down every path and a layout
 * has one form - two layouts are equal exactly when they are equal records. Each cell stands once.
 *
 * <p>Nothing here is sized: how much room a part has is a separate concern. The edits are
 * substitutions of cells ({@link LayoutAlgebra#substitute}), so none can leave the type; the
 * empty layout, which a removal of the last cell leaves, is outside it - {@code Optional.empty()}.</p>
 */
public sealed interface Layout permits Layout.Horizontal_Part, Layout.Vertical_Part {

    /** What may stand in a row: a whole, or a stack. */
    sealed interface Horizontal_Part extends Layout permits Whole, Vertical_NSplit {}

    /** What may stand in a stack: a whole, or a row. */
    sealed interface Vertical_Part extends Layout permits Whole, Horizontal_NSplit {}

    /** One cell. */
    record Whole(CellId id) implements Horizontal_Part, Vertical_Part {
        public Whole { Objects.requireNonNull(id, "Whole.id"); }
    }

    /** Two or more parts in a row, left to right; none of them a row. */
    record Horizontal_NSplit(List<Horizontal_Part> parts) implements Vertical_Part {
        public Horizontal_NSplit { parts = checked(parts, "Horizontal_NSplit"); }
    }

    /** Two or more parts in a stack, top to bottom; none of them a stack. */
    record Vertical_NSplit(List<Vertical_Part> parts) implements Horizontal_Part {
        public Vertical_NSplit { parts = checked(parts, "Vertical_NSplit"); }
    }

    /** Two or more parts, and no cell in two of them. */
    private static <P extends Layout> List<P> checked(List<P> parts, String what) {
        var copy = List.copyOf(Objects.requireNonNull(parts, what + ".parts"));
        if (copy.size() < 2) throw new IllegalArgumentException(what + " of " + copy.size() + " - two parts or more");
        var seen = new HashSet<CellId>();
        for (P p : copy) for (CellId c : p.cells()) if (!seen.add(c)) throw new IllegalArgumentException(what + " - the cell " + c + " stands twice");
        return copy;
    }

    // ── what it is ─────────────────────────────────────────────────────────

    /** Its cells, in order: left to right, top to bottom, depth first. */
    default List<CellId> cells() {
        var out = new ArrayList<CellId>();
        collect(this, out);
        return List.copyOf(out);
    }

    private static void collect(Layout l, List<CellId> out) {
        switch (l) {
            case Whole w -> out.add(w.id());
            case Horizontal_NSplit h -> h.parts().forEach(p -> collect(p, out));
            case Vertical_NSplit v -> v.parts().forEach(p -> collect(p, out));
        }
    }

    /** Whether the cell stands in it. */
    default boolean holds(CellId cell) { return cells().contains(cell); }

    /** Written as a term: {@code H(a, V(b, c))}; read back by {@link LayoutAlgebra#parse}. */
    default String text() {
        return switch (this) {
            case Whole w -> w.id().value();
            case Horizontal_NSplit h -> "H(" + String.join(", ", h.parts().stream().map(Layout::text).toList()) + ")";
            case Vertical_NSplit v -> "V(" + String.join(", ", v.parts().stream().map(Layout::text).toList()) + ")";
        };
    }

    /** The wholes either side of a cell in the split holding it - the cells it shares a whole line with. */
    default List<CellId> neighbours(CellId cell) {
        if (!holds(cell)) throw new IllegalArgumentException("no cell " + cell + " in " + text());
        List<? extends Layout> parts = switch (this) {
            case Whole w -> List.of();
            case Horizontal_NSplit h -> h.parts();
            case Vertical_NSplit v -> v.parts();
        };
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i) instanceof Whole w && w.id().equals(cell)) {
                var out = new ArrayList<CellId>();
                if (i > 0 && parts.get(i - 1) instanceof Whole b) out.add(b.id());
                if (i < parts.size() - 1 && parts.get(i + 1) instanceof Whole a) out.add(a.id());
                return List.copyOf(out);
            }
            if (parts.get(i).holds(cell)) return parts.get(i).neighbours(cell);
        }
        return List.of();
    }

    // ── the edits: each a substitution of cells ──────────────────────────

    /** A new cell beside an old one, on its side: {@code c ↦ H(c, n)} to the right, and so on. */
    default Layout subdivide(CellId cell, Side side, CellId fresh) {
        if (!holds(cell)) throw new IllegalArgumentException("no cell " + cell + " in " + text());
        if (holds(fresh)) throw new IllegalArgumentException("the cell " + fresh + " already stands in " + text());
        var A = LayoutAlgebra.INSTANCE;
        Layout old = new Whole(cell), nw = new Whole(fresh);
        var pair = side.before() ? A.split(side.axis(), nw, old) : A.split(side.axis(), old, nw);
        return A.substitute(this, Map.of(cell, Optional.of(pair))).orElseThrow();
    }

    /** The layout without the cell: {@code c ↦ ε}. Nothing is left when it was the last. */
    default Optional<Layout> remove(CellId cell) {
        if (!holds(cell)) throw new IllegalArgumentException("no cell " + cell + " in " + text());
        return LayoutAlgebra.INSTANCE.substitute(this, Map.of(cell, Optional.empty()));
    }

    /** Two cells exchanged: {@code a ↦ b, b ↦ a}. */
    default Layout swap(CellId a, CellId b) {
        if (!holds(a) || !holds(b)) throw new IllegalArgumentException("swap " + a + " and " + b + " - both must stand in " + text());
        if (a.equals(b)) return this;
        return LayoutAlgebra.INSTANCE.substitute(this, Map.of(a, Optional.of(new Whole(b)), b, Optional.of(new Whole(a)))).orElseThrow();
    }

    /** A cell taken out and put beside another, on its side. */
    default Layout move(CellId cell, CellId to, Side side) {
        if (cell.equals(to)) throw new IllegalArgumentException("move " + cell + " beside itself");
        if (!holds(to)) throw new IllegalArgumentException("no cell " + to + " in " + text());
        return remove(cell).orElseThrow().subdivide(to, side, cell);
    }

    // ── its symmetries ─────────────────────────────────────────────────────

    /** Across the diagonal: every row a stack, every stack a row. */
    default Layout transpose() {
        var A = LayoutAlgebra.INSTANCE;
        return switch (this) {
            case Whole w -> w;
            case Horizontal_NSplit h -> A.split(Axis.VERTICAL, h.parts().stream().map(Layout::transpose).toArray(Layout[]::new));
            case Vertical_NSplit v -> A.split(Axis.HORIZONTAL, v.parts().stream().map(Layout::transpose).toArray(Layout[]::new));
        };
    }

    /** In a mirror: every row read right to left. */
    default Layout mirror() {
        var A = LayoutAlgebra.INSTANCE;
        return switch (this) {
            case Whole w -> w;
            case Horizontal_NSplit h -> A.split(Axis.HORIZONTAL, h.parts().reversed().stream().map(Layout::mirror).toArray(Layout[]::new));
            case Vertical_NSplit v -> A.split(Axis.VERTICAL, v.parts().stream().map(Layout::mirror).toArray(Layout[]::new));
        };
    }
}
