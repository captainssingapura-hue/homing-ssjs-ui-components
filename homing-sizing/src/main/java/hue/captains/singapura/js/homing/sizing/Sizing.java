package hue.captains.singapura.js.homing.sizing;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Where rails come from (RFC 0068, appendix C §4): declared weights - an arrangement's 2 : 1, a
 * stored ratio - made millionths by the one rounding rule; a rail of equal boxes; a rail read from
 * its text. Every rail is made by its record or here, and the rule is the one in {@link Rail}.
 */
public record Sizing() {

    public static final Sizing INSTANCE = new Sizing();

    /** The weights - any positive numbers - as shares of a million, by the one rule. */
    public Rail of(double... weights) {
        Objects.requireNonNull(weights, "weights");
        if (weights.length == 0) throw new IllegalArgumentException("a rail has a box or more");
        return new Rail(Arrays.stream(Rail.apportion(weights)).boxed().toList());
    }

    /** The weights - any positive numbers - as shares of a million, by the one rule. */
    public Rail of(List<Double> weights) {
        Objects.requireNonNull(weights, "weights");
        return of(weights.stream().mapToDouble(Double::doubleValue).toArray());
    }

    /** {@code n} boxes alike, the earlier ones the odd millionths. */
    public Rail even(int n) {
        if (n < 1) throw new IllegalArgumentException("a rail has a box or more, not " + n);
        int[] ones = new int[n];
        Arrays.fill(ones, 1);
        return new Rail(Arrays.stream(Rail.apportion(Rail.WHOLE, ones)).boxed().toList());
    }

    /** A rail read from its text - {@code 500000 500000} - as {@link Rail#text()} writes it. */
    public Rail parse(String text) {
        Objects.requireNonNull(text, "text");
        String t = text.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("a rail has a box or more");
        return new Rail(Arrays.stream(t.split("\\s+")).map(Integer::valueOf).toList());
    }
}
