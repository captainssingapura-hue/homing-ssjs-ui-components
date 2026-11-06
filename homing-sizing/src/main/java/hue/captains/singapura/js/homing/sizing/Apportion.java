package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.List;

/** Shares a whole among weights, every share a whole number ({@link Rail}). */
public record Apportion() implements StatelessFunctionalObject {

    public static final Apportion INSTANCE = new Apportion();

    /**
     * {@code total} shared by whole weights, by the one rule: each part's floor, what is left over a
     * unit at a time to the largest remainders, the earlier on a tie; then a part left at nothing
     * given one, taken from the largest. The workspace's log rounds by it.
     */
    int[] apportion(int total, int[] weights) {
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
    int[] apportion(double[] weights) {
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
            double q = Rail.WHOLE * weights[i] / sum;
            double f = Math.floor(q);
            units[i] = (int) f;
            rem[i] = q - f;
            got += units[i];
        }
        var order = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) order.add(i);
        order.sort((a, b) -> rem[a] != rem[b] ? Double.compare(rem[b], rem[a]) : Integer.compare(a, b));
        return settle(units, order, Rail.WHOLE - got);
    }

    private int[] settle(int[] units, List<Integer> order, long left) {
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
