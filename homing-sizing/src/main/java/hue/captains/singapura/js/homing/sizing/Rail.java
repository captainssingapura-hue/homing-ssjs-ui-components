package hue.captains.singapura.js.homing.sizing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A rail (RFC 0068, appendix C §3): an ordered run of boxes along one axis, sharing a length - each
 * box a share of the room the shares divide, in millionths, the shares adding up to a million and
 * none below one. A value: every operation returns a rail and leaves this one alone.
 *
 * <p>A rail knows nothing of what its boxes are, or of any rail beside it: what arranges rails - a
 * split grid's tree, a float on a desk - is its user's, and the pixels it is handed are its user's
 * measure, read where it is laid out. Its boxes are shares only, a least to each: what the split
 * grid needs. A box fit to its content, a fixed one, and a most are the appendix's, not yet here.</p>
 */
public record Rail(List<Integer> shares) {

    /** A whole rail, in millionths. */
    public static final int WHOLE = 1_000_000;

    public Rail {
        Objects.requireNonNull(shares, "shares");
        shares = List.copyOf(shares);
        if (shares.isEmpty()) throw new IllegalArgumentException("a rail has a box or more");
        long sum = 0;
        for (int s : shares) {
            if (s < 1) throw new IllegalArgumentException("a box's share is a millionth or more, not " + s);
            sum += s;
        }
        if (sum != WHOLE) throw new IllegalArgumentException("a rail's shares add up to " + WHOLE + ", not " + sum);
    }

    /** How many boxes. */
    public int size() { return shares.size(); }

    /** Box {@code i}'s share, in millionths. */
    public int share(int i) { return shares.get(i); }

    /** Where the boundary after box {@code i} stands: the shares up to it and through it, in millionths. */
    public int at(int i) {
        boundary(i);
        int sum = 0;
        for (int k = 0; k <= i; k++) sum += shares.get(k);
        return sum;
    }

    /**
     * Each box's length in pixels, laid out in {@code room} as flexbox would: each its share of it,
     * and a box under its least held there while the rest re-share what is left - held one by one,
     * until none is under. With no room for every least, every box is held and the rail overflows,
     * as flexbox's does. {@code least} is a length for each box; empty, none has a least.
     */
    public List<Double> lay(double room, List<Double> least) {
        int n = size();
        double[] sizes = new double[n];
        boolean[] frozen = new boolean[n];
        double left = Math.max(0, room);
        for (int pass = 0; pass <= n; pass++) {
            long free = 0;
            for (int a = 0; a < n; a++) if (!frozen[a]) free += shares.get(a);
            int under = -1;
            for (int b = 0; b < n; b++) {
                if (frozen[b]) continue;
                sizes[b] = free > 0 ? (double) shares.get(b) / free * left : 0;
                if (sizes[b] < leastOf(least, b) && under < 0) under = b;
            }
            if (under < 0) break;
            frozen[under] = true;
            sizes[under] = leastOf(least, under);
            left = Math.max(0, left - leastOf(least, under));
        }
        var out = new ArrayList<Double>(n);
        for (double s : sizes) out.add(s);
        return Collections.unmodifiableList(out);
    }

    /**
     * The boundary after box {@code i} moved {@code d} pixels: the two boxes either side re-shared
     * over {@code room} - the room the shares divide, not the rail's whole length - their sum kept
     * exact, and the move stopped where either would pass its least. A move never takes a box
     * further under its least than it already was, so a rail laid out too small still moves. No room
     * - a rail not yet laid out - moves nothing.
     */
    public Rail move(int i, double d, double room, List<Double> least) {
        boundary(i);
        if (!(room > 0) || Double.isNaN(d)) return this;
        int a0 = shares.get(i), b0 = shares.get(i + 1), t = a0 + b0;
        double raw = Math.max(-t, Math.min(t, d * WHOLE / room));
        long step = raw < 0 ? -Math.round(-raw) : Math.round(raw);       // halves away from nought, so -d undoes d
        int loA = Math.min(a0, (int) Math.max(1, Math.ceil(leastOf(least, i) * WHOLE / room)));
        int loB = Math.min(b0, (int) Math.max(1, Math.ceil(leastOf(least, i + 1) * WHOLE / room)));
        int a = (int) Math.max(loA, Math.min(t - loB, a0 + step));
        if (a == a0) return this;
        var out = new ArrayList<>(shares);
        out.set(i, a);
        out.set(i + 1, t - a);
        return new Rail(out);
    }

    /** A box beside box {@code i}, before or after it, taking half of its share: the earlier of the two the odd millionth. */
    public Rail insert(int i, boolean before) {
        int s = shares.get(i);
        if (s < 2) throw new IllegalArgumentException("a box of one millionth cannot be divided");
        int first = s - s / 2, second = s / 2;
        var out = new ArrayList<>(shares);
        out.set(i, before ? second : first);
        out.add(before ? i : i + 1, before ? first : second);
        return new Rail(out);
    }

    /** Box {@code i} gone, its share to the box before it, else the one after. */
    public Rail remove(int i) {
        return remove(i, i > 0 ? i - 1 : i + 1);
    }

    /** Box {@code i} gone, its share to its neighbour {@code toward} - the box before it or the one after. */
    public Rail remove(int i, int toward) {
        Objects.checkIndex(i, size());
        if (size() < 2) throw new IllegalArgumentException("the last box of a rail cannot go");
        if (toward != i - 1 && toward != i + 1 || toward < 0 || toward >= size())
            throw new IllegalArgumentException("box " + i + "'s share goes to a neighbour, not to " + toward);
        var out = new ArrayList<>(shares);
        out.set(toward, out.get(toward) + out.get(i));
        out.remove(i);
        return new Rail(out);
    }

    /** Box {@code i} replaced by another rail's boxes, each its share of box {@code i}'s by the one rounding: two rails made one. */
    public Rail absorb(int i, Rail inner) {
        Objects.requireNonNull(inner, "inner");
        int s = shares.get(i);
        if (s < inner.size()) throw new IllegalArgumentException("a box of " + s + " millionths cannot hold " + inner.size() + " boxes");
        int[] weights = inner.shares.stream().mapToInt(Integer::intValue).toArray();
        int[] parts = apportion(s, weights);
        var out = new ArrayList<Integer>(size() + parts.length - 1);
        out.addAll(shares.subList(0, i));
        for (int p : parts) out.add(p);
        out.addAll(shares.subList(i + 1, size()));
        return new Rail(out);
    }

    /** The shares, written: {@code 500000 500000}. */
    public String text() {
        return shares.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private void boundary(int i) {
        if (i < 0 || i >= size() - 1) throw new IndexOutOfBoundsException("a rail of " + size() + " has no boundary after box " + i);
    }

    private double leastOf(List<Double> least, int i) {
        if (least == null || least.isEmpty()) return 0;
        if (least.size() != size()) throw new IllegalArgumentException("a least for each of " + size() + " boxes, not " + least.size());
        return least.get(i);
    }

    // ── the one rounding rule ────────────────────────────────────────────

    /**
     * {@code total} shared by whole weights, by the one rule: each part's floor, what is left over a
     * unit at a time to the largest remainders, the earlier on a tie; then a part left at nothing
     * given one, taken from the largest. The workspace's log rounds by it.
     */
    static int[] apportion(int total, int[] weights) {
        int n = weights.length;
        long sum = 0;
        for (int w : weights) sum += w;
        int[] units = new int[n];
        long[] rem = new long[n];
        long got = 0;
        for (int i = 0; i < n; i++) {
            long q = (long) total * weights[i];
            units[i] = (int) (q / sum);
            rem[i] = q % sum;
            got += units[i];
        }
        var order = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) order.add(i);
        order.sort((a, b) -> rem[a] != rem[b] ? Long.compare(rem[b], rem[a]) : Integer.compare(a, b));
        return settle(units, order, total - got);
    }

    /** The same rule over weights that are any positive numbers: a declaration's 2 : 1, a stored ratio. */
    static int[] apportion(double[] weights) {
        int n = weights.length;
        double sum = 0;
        for (double w : weights) {
            if (!(w > 0) || Double.isInfinite(w)) throw new IllegalArgumentException("a weight is a positive number, not " + w);
            sum += w;
        }
        int[] units = new int[n];
        double[] rem = new double[n];
        long got = 0;
        for (int i = 0; i < n; i++) {
            double q = WHOLE * weights[i] / sum;
            double f = Math.floor(q);
            units[i] = (int) f;
            rem[i] = q - f;
            got += units[i];
        }
        var order = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) order.add(i);
        order.sort((a, b) -> rem[a] != rem[b] ? Double.compare(rem[b], rem[a]) : Integer.compare(a, b));
        return settle(units, order, WHOLE - got);
    }

    private static int[] settle(int[] units, List<Integer> order, long left) {
        int n = units.length;
        for (long k = 0; k < left; k++) units[order.get((int) (k % n))]++;
        for (int i = 0; i < n; i++) {
            if (units[i] > 0) continue;
            int big = 0;
            for (int j = 1; j < n; j++) if (units[j] > units[big]) big = j;
            units[big]--;
            units[i] = 1;
        }
        return units;
    }
}
