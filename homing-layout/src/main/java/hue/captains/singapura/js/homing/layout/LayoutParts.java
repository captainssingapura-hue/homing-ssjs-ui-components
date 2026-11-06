package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/** The parts of a split ({@link Layout}). */
public record LayoutParts() implements StatelessFunctionalObject {

    public static final LayoutParts INSTANCE = new LayoutParts();

    /** Two or more parts, and no cell in two of them. */
    <P extends Layout> List<P> checked(List<P> parts, String what) {
        var copy = List.copyOf(Objects.requireNonNull(parts, what + ".parts"));
        if (copy.size() < 2) throw new IllegalArgumentException(what + " of " + copy.size() + " - two parts or more");
        var seen = new HashSet<CellId>();
        for (P p : copy) for (CellId c : p.cells()) if (!seen.add(c)) throw new IllegalArgumentException(what + " - the cell " + c + " stands twice");
        return copy;
    }
}
