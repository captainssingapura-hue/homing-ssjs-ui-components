package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.js.homing.layout.Layout.Horizontal_NSplit;
import hue.captains.singapura.js.homing.layout.Layout.Horizontal_Part;
import hue.captains.singapura.js.homing.layout.Layout.Vertical_NSplit;
import hue.captains.singapura.js.homing.layout.Layout.Vertical_Part;
import hue.captains.singapura.js.homing.layout.Layout.Whole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The layout algebra's operations (RFC 0068, appendix A): the horizontal and the vertical split -
 * associative, the empty layout their unit, nothing else holding - and a substitution of cells,
 * every edit's form. A split made here flattens a split of its own axis into itself and lets a
 * single part give way, so what it makes is always a layout; nothing else makes an n-split but
 * its records, which refuse what is not one.
 */
public record LayoutAlgebra() {

    public static final LayoutAlgebra INSTANCE = new LayoutAlgebra();

    /** One cell, named so. */
    public Layout whole(String id) { return new Whole(new CellId(id)); }

    /** The parts split along an axis: {@code H(a, b, ...)} or {@code V(a, b, ...)}. One part is itself. */
    public Layout split(Axis axis, Layout... parts) {
        if (parts.length == 0) throw new IllegalArgumentException("a split of nothing is the empty layout, which is no layout");
        return join(axis, Arrays.stream(parts).map(Optional::of).toList()).orElseThrow();
    }

    /**
     * The parts - each a layout, or the empty layout - split along an axis: the empty ones dropped,
     * a split of the same axis given up into this one in place, a single part left as itself, none
     * left the empty layout.
     */
    public Optional<Layout> join(Axis axis, List<Optional<Layout>> parts) {
        Objects.requireNonNull(axis, "axis");
        if (axis == Axis.HORIZONTAL) {
            var row = new ArrayList<Horizontal_Part>();
            for (Optional<Layout> p : parts) p.ifPresent(l -> {
                switch (l) {
                    case Horizontal_NSplit h -> row.addAll(h.parts());
                    case Whole w -> row.add(w);
                    case Vertical_NSplit v -> row.add(v);
                }
            });
            return row.isEmpty() ? Optional.empty() : Optional.of(row.size() == 1 ? row.get(0) : new Horizontal_NSplit(row));
        }
        var stack = new ArrayList<Vertical_Part>();
        for (Optional<Layout> p : parts) p.ifPresent(l -> {
            switch (l) {
                case Vertical_NSplit v -> stack.addAll(v.parts());
                case Whole w -> stack.add(w);
                case Horizontal_NSplit h -> stack.add(h);
            }
        });
        return stack.isEmpty() ? Optional.empty() : Optional.of(stack.size() == 1 ? stack.get(0) : new Vertical_NSplit(stack));
    }

    /**
     * Every cell the map names put in place by what it maps to - a layout, or the empty layout -
     * the rest left as they are, and every split rebuilt by {@link #join}: one walk, a fold. A
     * cell that would then stand twice is refused by the split that would hold it twice.
     */
    public Optional<Layout> substitute(Layout layout, Map<CellId, Optional<Layout>> sigma) {
        return switch (layout) {
            case Whole w -> sigma.containsKey(w.id()) ? sigma.get(w.id()) : Optional.of(w);
            case Horizontal_NSplit h -> join(Axis.HORIZONTAL, h.parts().stream().map(p -> substitute(p, sigma)).toList());
            case Vertical_NSplit v -> join(Axis.VERTICAL, v.parts().stream().map(p -> substitute(p, sigma)).toList());
        };
    }

    /**
     * A term read: a cell's name, {@code H(t, ...)} or {@code V(t, ...)}, or {@code ε}. Any term -
     * a split in one of its own axis, a split of one, the empty layout among the parts - read to
     * its one layout; the empty layout when nothing is left.
     */
    public Optional<Layout> parse(String text) {
        var reader = new Reader(Objects.requireNonNull(text, "text"));
        var term = reader.term();
        reader.space();
        if (reader.at < text.length()) throw reader.refused("the end");
        return term;
    }

    /** A recursive descent over the term's text: its place in it is its only state, and it lives for one read. */
    private final class Reader {
        private final String s;
        private int at;
        Reader(String s) { this.s = s; }

        Optional<Layout> term() {
            space();
            if (s.startsWith("ε", at)) { at += 1; return Optional.empty(); }
            int from = at;
            while (at < s.length() && (Character.isLetterOrDigit(s.charAt(at)) || "._-".indexOf(s.charAt(at)) >= 0)) at++;
            if (from == at) throw refused("a cell, H(...), V(...) or ε");
            String name = s.substring(from, at);
            space();
            if (at < s.length() && s.charAt(at) == '(' && (name.equals("H") || name.equals("V"))) {
                at++;
                var parts = new ArrayList<Optional<Layout>>();
                while (true) {
                    parts.add(term());
                    space();
                    if (at < s.length() && s.charAt(at) == ',') { at++; continue; }
                    break;
                }
                if (at >= s.length() || s.charAt(at) != ')') throw refused("',' or ')'");
                at++;
                return join(name.equals("H") ? Axis.HORIZONTAL : Axis.VERTICAL, parts);
            }
            return Optional.of(new Whole(new CellId(name)));
        }

        void space() { while (at < s.length() && Character.isWhitespace(s.charAt(at))) at++; }

        IllegalArgumentException refused(String wanted) {
            return new IllegalArgumentException("the term '" + s + "' - " + wanted + " expected at " + at);
        }
    }
}
