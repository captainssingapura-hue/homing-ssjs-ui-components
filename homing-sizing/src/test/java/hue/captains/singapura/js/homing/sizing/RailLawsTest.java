package hue.captains.singapura.js.homing.sizing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Theorem 6 (RFC 0068, appendix C §7), for every rail: exact, local, inverse, held, absorbing
 * exact - over random rails and random operations; and the one rounding, the flexbox lay and the
 * move by the cases the split grid's geometry was held to before sizing was its own.
 */
class RailLawsTest {

    private static final Sizing S = Sizing.INSTANCE;
    private static final int W = Rail.WHOLE;

    private static List<Double> least(double... px) {
        var out = new ArrayList<Double>();
        for (double p : px) out.add(p);
        return out;
    }

    @Test
    void theOneRounding_floorsThenTheLargestRemainders_theEarlierOnATie() {
        assertEquals("333334 333333 333333", S.of(1, 1, 1).text());
        assertEquals("333334 333333 333333", S.even(3).text());
        assertEquals("666667 333333", S.of(2, 1).text());
        assertEquals("250000 750000", S.of(0.25, 0.75).text());
        assertEquals("142858 142857 142857 142857 142857 142857 142857", S.even(7).text());
        assertEquals("1 999999", S.of(1e-9, 1).text(), "a part rounded to nothing is given one, from the largest");
        assertEquals(S.of(0.5, 0.5), S.parse(S.of(0.5, 0.5).text()));
        assertThrows(IllegalArgumentException.class, () -> S.of(1, 0));
        assertThrows(IllegalArgumentException.class, () -> S.of(1, Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> new Rail(List.of(500_000, 500_001)));
        assertThrows(IllegalArgumentException.class, () -> new Rail(List.of(0, W)));
        assertThrows(IllegalArgumentException.class, () -> new Rail(List.of()));
    }

    @Test
    void lay_sharesTheRoom_asFlexboxDoes_holdingALeastAndReSharingTheRest() {
        assertEquals(List.of(250.0, 750.0), S.of(0.25, 0.75).lay(1000, least(40, 40)));
        assertEquals(List.of(40.0, 60.0), S.of(0.1, 0.9).lay(100, least(40, 40)), "1 : 9 of 100 would be 10: held at 40, the other takes the rest");
        assertEquals(List.of(40.0, 40.0, 120.0), S.of(0.1, 0.1, 0.8).lay(200, least(40, 40, 40)));
        assertEquals(List.of(40.0, 40.0, 40.0), S.of(0.1, 0.1, 0.8).lay(100, least(40, 40, 40)), "no room for every least: all held, overflowing");
        assertEquals(List.of(40.0, 40.0), S.of(0.5, 0.5).lay(20, least(40, 40)));
        assertEquals(List.of(500.0, 500.0), S.even(2).lay(1000, List.of()), "no least, none held");
    }

    @Test
    void move_reSharesTheTwoEitherSide_overTheRoom_stoppedAtALeast() {
        Rail r = S.of(0.25, 0.75);
        assertEquals("500000 500000", r.move(0, 250, 1000, least(40, 40)).text());
        assertEquals("40000 960000", r.move(0, -900, 1000, least(40, 40)).text(), "held at its least, 40 of 1000");
        assertEquals("960000 40000", r.move(0, 900, 1000, least(40, 40)).text());
        assertSame(r, r.move(0, 100, 0, least(40, 40)), "a rail not yet laid out moves nothing");
        assertSame(r, r.move(0, 0.0004, 1000, least(40, 40)), "under half a millionth's worth moves nothing");
        assertEquals("40000 960000", r.move(0, Double.NEGATIVE_INFINITY, 1000, least(40, 40)).text(), "as far as it goes");
        // laid out too small: a box already under its least is not pushed up by a move toward it, nor taken further down
        Rail tight = S.of(0.02, 0.98);
        assertEquals("20000 980000", tight.move(0, -50, 1000, least(40, 40)).text());
        assertEquals("30000 970000", tight.move(0, 10, 1000, least(40, 40)).text());
        assertEquals(250_000, r.at(0));
    }

    @Test
    void insertRemoveAndAbsorb_byTheirCases() {
        Rail r = S.of(1, 1, 1);
        assertEquals("166667 166667 333333 333333", r.insert(0, false).text(), "the earlier of the two halves the odd millionth");
        assertEquals("166667 166667 333333 333333", r.insert(0, true).text());
        assertEquals("333334 166667 166666 333333", r.insert(1, true).text());
        assertEquals("666667 333333", r.remove(1).text(), "to the one before");
        assertEquals("333334 666666", r.remove(1, 2).text(), "to the one after, named");
        assertEquals("666667 333333", r.remove(0).text(), "the first has none before: the one after");
        assertThrows(IllegalArgumentException.class, () -> r.remove(0, 2));
        assertThrows(IllegalArgumentException.class, () -> S.even(1).remove(0));
        assertEquals("333334 166667 166666 333333", r.absorb(1, S.even(2)).text());
        assertEquals("250000 250000 500000", S.even(2).absorb(0, S.even(2)).text());
        assertThrows(IllegalArgumentException.class, () -> new Rail(List.of(1, W - 1)).insert(0, true));
    }

    /** Theorem 6, over random rails: every law after every operation. */
    @Test
    void theorem6_overRandomRails() {
        var rnd = new Random(6);
        for (int run = 0; run < 3000; run++) {
            int n = 1 + rnd.nextInt(6);
            double[] w = new double[n];
            for (int k = 0; k < n; k++) w[k] = 0.05 + rnd.nextDouble() * 4;
            Rail r = S.of(w);
            exact(r);
            double room = 50 + rnd.nextDouble() * 2000;
            var lst = new ArrayList<Double>();
            for (int k = 0; k < n; k++) lst.add(rnd.nextDouble() * 120);
            if (n >= 2) {
                int i = rnd.nextInt(n - 1);
                double d = (rnd.nextDouble() - 0.5) * room;
                Rail m = r.move(i, d, room, lst);
                exact(m);
                for (int k = 0; k < n; k++) if (k != i && k != i + 1) assertEquals(r.share(k), m.share(k), "a move is local");
                assertEquals(r.share(i) + r.share(i + 1), m.share(i) + m.share(i + 1), "the pair's sum is kept");
                held(r.share(i), m.share(i), lst.get(i), room);
                held(r.share(i + 1), m.share(i + 1), lst.get(i + 1), room);
                long step = Math.round(Math.abs(d) * W / room) * (d < 0 ? -1 : 1);
                Rail back = m.move(i, -d, room, lst);
                if (m.share(i) == r.share(i) + step && back.share(i) == m.share(i) - step) assertEquals(r, back, "a move, then its opposite");

                int j = rnd.nextInt(n);
                if (r.share(j) >= 2) {
                    boolean before = rnd.nextBoolean();
                    Rail ins = r.insert(j, before);
                    exact(ins);
                    int fresh = before ? j : j + 1, old = before ? j + 1 : j;
                    assertEquals(r.share(j), ins.share(fresh) + ins.share(old), "an insertion divides the box it is beside, and only it");
                    assertEquals(r, ins.remove(fresh, old), "removing what was inserted, toward where it came from, is the rail it was");
                }

                int g = rnd.nextInt(n);
                int toward = g == 0 ? 1 : g == n - 1 ? n - 2 : (rnd.nextBoolean() ? g - 1 : g + 1);
                Rail rem = r.remove(g, toward);
                exact(rem);
                int heir = toward < g ? toward : toward - 1;
                for (int k = 0; k < rem.size(); k++) {
                    int was = k < g ? k : k + 1;
                    assertEquals(r.share(was) + (k == heir ? r.share(g) : 0), rem.share(k), "a removal changes its heir alone");
                }
            }

            int a = rnd.nextInt(n);
            int m2 = 1 + rnd.nextInt(4);
            double[] w2 = new double[m2];
            for (int k = 0; k < m2; k++) w2[k] = 0.05 + rnd.nextDouble() * 4;
            Rail inner = S.of(w2);
            if (r.share(a) >= m2) {
                Rail ab = r.absorb(a, inner);
                exact(ab);
                assertEquals(n + m2 - 1, ab.size());
                long sum = 0;
                for (int k = 0; k < m2; k++) {
                    int part = ab.share(a + k);
                    sum += part;
                    double ideal = (double) r.share(a) * inner.share(k) / W;
                    assertTrue(Math.abs(part - ideal) < 1 + 1e-9 || part == 1, "a part is its share of the whole, to a millionth: " + part + " for " + ideal);
                }
                assertEquals(r.share(a), sum, "the box's share, every millionth of it, and no more");
            }

            double total = r.lay(room, List.of()).stream().mapToDouble(Double::doubleValue).sum();
            assertEquals(room, total, 1e-6, "laid out with no least, the boxes fill the room");
        }
    }

    private static void exact(Rail r) {
        long sum = 0;
        for (int k = 0; k < r.size(); k++) {
            assertTrue(r.share(k) >= 1);
            sum += r.share(k);
        }
        assertEquals(W, sum);
    }

    private static void held(int before, int after, double least, double room) {
        int lo = (int) Math.max(1, Math.ceil(least * W / room));
        assertTrue(after >= Math.min(before, lo), "held: " + after + " under " + lo + ", from " + before);
    }
}
