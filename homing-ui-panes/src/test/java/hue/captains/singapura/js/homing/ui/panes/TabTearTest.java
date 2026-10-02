package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tearing a tab off its strip, headless, on a strip 0..30 high with the
 * default margin of 24, so the band ends at 54 below. The hand is sampled
 * every 10ms. On the rail inside the band; torn at the breach, the window
 * waiting there while the flight keeps the breach's velocity; settled at the
 * hand once a material change has held, or at once for a slow tear, or where
 * the hand lets go.
 */
class TabTearTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/panes/";

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(P + "TabTearModule.js");
        eval("""
            var band = { top: 0, bottom: 30 };
            function at(s) { return s.phase + (s.anchor ? '@' + Math.round(s.anchor.x) + ',' + Math.round(s.anchor.y) : ''); }
            // pressed in the strip, then straight down at 1 px/ms: out of the band at t=40, y=55
            function flung(opts) { var g = new TabTear(opts); g.press(100, 15, 0, band); for (var t = 10; t <= 100; t += 10) g.move(100, 15 + t, t); return g; }
            """);
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void withinTheBand_theChipStaysOnItsRail_howeverFarTheHandGoesSideways() {
        eval("var g = new TabTear(); g.press(100, 15, 0, band);");
        assertEquals("rail", eval("at(g.move(900, 54, 10))").asString(), "54 is the band's edge: still the rail");
        assertEquals("rail", eval("at(g.move(-400, -24, 20))").asString(), "and above it, the margin's worth");
        assertEquals("done", eval("at(g.release(-400, -24, 30))").asString(), "let go on the rail: not torn, no anchor");
        assertTrue(eval("!g.torn() && g.breach() === null && g.settle() === null").asBoolean());
    }

    @Test
    void outOfTheBand_itIsTorn_andTheWindowWaitsAtTheBreach_whileTheFlightKeepsItsVelocity() {
        eval("var g = new TabTear(); g.press(100, 15, 0, band); var s; for (var t = 10; t <= 40; t += 10) s = g.move(100, 15 + t, t);");
        assertEquals("flight@100,55", eval("at(s)").asString(), "breached at 55: the window made there");
        assertTrue(eval("s.changed && Math.abs(g.breach().speed - 1) < 1e-9 && g.breach().vy === 1").asBoolean(), "the breach's velocity: 1 px/ms down");
        eval("for (t = 50; t <= 100; t += 10) s = g.move(100, 15 + t, t);");
        assertEquals("flight@100,55", eval("at(s)").asString(), "the hand at 115 and still flying: the window has not chased it");
        assertEquals("0.00", eval("s.ratio.toFixed(2)").asString());
    }

    @Test
    void aHandThatStops_settlesTheWindowAtIt_onceTheChangeHasHeld() {
        eval("var g = flung(), s = {}, seen = []; for (var t = 110; t <= 190; t += 10) { s = g.tick(t); seen.push(t + ':' + at(s) + ' ' + s.ratio.toFixed(2)); }");
        assertEquals("140:flight@100,55 0.00", eval("seen[3]").asString(), "quiet for 40ms: not yet at rest, the frames say nothing");
        assertEquals("150:flight@100,55 0.83", eval("seen[4]").asString(), "quiet for the idle's 50: at rest, and the speed falls away");
        assertEquals("180:flight@100,55 1.00", eval("seen[7]").asString(), "above the change from 150, held 30ms");
        assertEquals("190:follow@100,115 1.00", eval("seen[8]").asString(), "held 40ms: settled at the hand");
        assertEquals("change", eval("g.settle().why").asString());
        assertEquals("follow@140,160", eval("at(g.move(140, 160, 200))").asString(), "and the window follows the hand from then on");
    }

    /** A browser sends a hand's moves about once a frame: a frame between two of them is not a hand at rest. */
    @Test
    void framesBetweenTheMoves_ofASteadyHand_neverSettleIt() {
        eval("var g = new TabTear(), s; g.press(100, 15, 0, band); for (var t = 16; t <= 480; t += 16) { g.move(100, 15 + t, t); s = g.tick(t + 5); }");
        assertEquals("flight 0.00", eval("s.phase + ' ' + s.ratio.toFixed(2)").asString());
    }

    @Test
    void aSlowTear_hasNoFlight_itSettlesWhereItBreaches() {
        eval("var g = new TabTear(), s; g.press(100, 15, 0, band); for (var t = 100; t <= 800; t += 100) s = g.move(100, 15 + t * 0.05, t);");
        assertEquals("follow@100,55", eval("at(s)").asString(), "0.05 px/ms is under the floor");
        assertEquals("slow", eval("g.settle().why").asString());
    }

    @Test
    void letGoInFlight_settlesWhereTheHandLetGo() {
        eval("var g = new TabTear(); g.press(100, 15, 0, band); for (var t = 10; t <= 80; t += 10) g.move(100, 15 + t, t); var s = g.release(100, 100, 90);");
        assertEquals("done@100,100", eval("at(s)").asString());
        assertEquals("release", eval("g.settle().why").asString());
        assertTrue(eval("g.torn() && !g.inBand(100)").asBoolean(), "torn, and let go out of the band");
    }

    @Test
    void aChangeShorterThanTheHold_isNotOne_andAPauseShorterThanTheIdleIsNoChangeAtAll() {
        eval("function paused(o) { var g = flung(o), y = 115, s; for (var t = 110; t <= 140; t += 10) s = g.tick(t); for (t = 150; t <= 240; t += 10) { y += 10; s = g.move(100, y, t); } return g; }");
        assertEquals("flight 0.00", eval("var g = paused(); g.phase() + ' ' + g.step().ratio.toFixed(2)").asString(), "a 40ms pause: under the idle, so nothing");
        assertEquals("flight@100,55", eval("at(paused({ idle: 0 }).step())").asString(), "no idle: above the change for 20ms only, and the flight goes on");
    }

    @Test
    void theMeasure_saysWhatCountsAsAChange() {
        eval("""
            function faster(m) { var g = new TabTear({ measure: m }), y = 15, s; g.press(100, y, 0, band);
                for (var t = 10; t <= 60; t += 10) { y += 10; g.move(100, y, t); } for (t = 70; t <= 200; t += 10) { y += 20; s = g.move(100, y, t); } return s.phase; }
            function turned(m) { var g = new TabTear({ measure: m }), x = 100, y = 15, s; g.press(x, y, 0, band);
                for (var t = 10; t <= 60; t += 10) { y += 10; g.move(x, y, t); } for (t = 70; t <= 200; t += 10) { x += 10; s = g.move(x, y, t); } return s.phase; }
            """);
        assertEquals("follow flight follow", eval("['speed', 'slowdown', 'velocity'].map(faster).join(' ')").asString(),
                "twice as fast: a change to the speed and the vector, not to a slowdown");
        assertEquals("flight flight follow", eval("['speed', 'slowdown', 'velocity'].map(turned).join(' ')").asString(),
                "a turn at one speed: only the vector sees it");
        var ex = assertThrows(PolyglotException.class, () -> eval("new TabTear({ measure: 'jerk' })"));
        assertTrue(ex.getMessage().contains("measure is one of"), ex.getMessage());
    }

    @Test
    void theOptionsAreLive_andAGestureNeedsAPressAndABand() {
        eval("var g = new TabTear().set({ margin: 0 }); g.press(100, 15, 0, band);");
        assertEquals("flight@100,31", eval("at(g.move(100, 31, 10))").asString(), "no margin: out the moment it leaves the strip");
        assertEquals("0 0.5", eval("g.options().margin + ' ' + g.options().change").asString());
        assertThrows(PolyglotException.class, () -> eval("new TabTear().move(1, 1, 1)"));
        assertThrows(PolyglotException.class, () -> eval("new TabTear().press(1, 1, 0)"));
    }
}
