package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;
import hue.captains.singapura.js.homing.core.Wearable;

import java.util.List;
import java.util.Set;

import static hue.captains.singapura.js.homing.design.Box.Control;
import static hue.captains.singapura.js.homing.design.DesignClass.of;
import static hue.captains.singapura.js.homing.design.Emphasis.Muted;
import static hue.captains.singapura.js.homing.design.Interaction.Dragging;
import static hue.captains.singapura.js.homing.design.Structure.Hairline;
import static hue.captains.singapura.js.homing.design.Target.Affordance;
import static hue.captains.singapura.js.homing.design.Target.Color;
import static hue.captains.singapura.js.homing.design.Target.Shape;
import static hue.captains.singapura.js.homing.design.Target.Type;
import static hue.captains.singapura.js.homing.design.Text.Caption;

/**
 * The sheet of a pan-zoom view, in the design's words and nothing of its own but layout: the
 * viewport - framed by a hairline, a control's rule, and its ring when it has the focus - that
 * scrolls natively over the canvas, held at its fitted height while zoomed in place; the canvas as
 * large as the drawing is drawn, and the drawing scaled from its corner, by the view's variables;
 * the hand that grabs once there is somewhere to pan; the bar and its readout.
 */
public record PanZoomStyles() implements CssGroup<PanZoomStyles> {

    public static final PanZoomStyles INSTANCE = new PanZoomStyles();

    /**
     * The viewport: a column the canvas stands in - stretched across while the drawing lays out as it is,
     * centred both ways once sized - scrolling what overflows it, natively; a control to the keys, so it
     * wears a control's rule and ring.
     */
    public record pz_view() implements CssClass<PanZoomStyles> {
        @Override public List<? extends Wearable> wears() {
            return List.of(of(Control.class, Shape.Rule.class), of(Control.class, Color.Edge.class), of(Hairline.class, Color.Edge.class));
        }
        @Override public String body() { return "display: flex;\nflex-direction: column;\noverflow: auto;\nmin-width: 0;\n"; }
    }

    /** Zoomed in place: the viewport held at the height it fitted at, so the drawing scrolls rather than the page growing. */
    public record pz_pinned() implements CssClass<PanZoomStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--pz-vh")); }
        @Override public String body() { return "box-sizing: border-box;\nheight: var(--pz-vh);\n"; }
    }

    /** The canvas the drawing is on: what the viewport scrolls over. */
    public record pz_canvas() implements CssClass<PanZoomStyles> {
        @Override public String body() { return "position: relative;\n"; }
    }

    /** Sized: the canvas as large as the drawing is drawn - centred on an axis where it is the smaller, from the start where it overflows. */
    public record pz_zoomed() implements CssClass<PanZoomStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--pz-w"), new CssVar("--pz-h")); }
        @Override public String body() { return "flex: none;\nwidth: var(--pz-w);\nheight: var(--pz-h);\nmargin: auto;\n"; }
    }

    /** The drawing: laid out no wider than the viewport, its height its own, centred - the size it fits at. */
    public record pz_svg() implements CssClass<PanZoomStyles> {
        @Override public String body() { return "display: block;\nmax-width: 100%;\nheight: auto;\nmargin: 0 auto;\n"; }
    }

    /** Zoomed: the drawing kept at the width it fitted at, in the canvas's corner, and scaled from there. */
    public record pz_scaled() implements CssClass<PanZoomStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--pz-sw"), new CssVar("--pz-scale")); }
        @Override public String body() {
            return "position: absolute;\nleft: 0;\ntop: 0;\nwidth: var(--pz-sw);\ntransform-origin: 0 0;\ntransform: scale(var(--pz-scale));\n";
        }
    }

    /** Zoomed in, with somewhere to pan: the hand grabs. */
    public record pz_pannable() implements CssClass<PanZoomStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Dragging.class, Affordance.Cursor.class)); }
        @Override public String body() { return ""; }
    }

    /** The bar: out, the readout, in, fit, side by side. */
    public record pz_bar() implements CssClass<PanZoomStyles> {
        @Override public String body() { return "display: flex;\nalign-items: center;\ngap: 4px;\n"; }
    }

    /** The zoom, said: a share of fit, in figures that keep their width. */
    public record pz_readout() implements CssClass<PanZoomStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Caption.class, Type.Scale.class), of(Muted.class, Color.Ink.class)); }
        @Override public String body() { return "min-width: 3.5em;\ntext-align: center;\nfont-variant-numeric: tabular-nums;\n"; }
    }

    @Override
    public List<CssClass<PanZoomStyles>> cssClasses() {
        return List.of(new pz_view(), new pz_pinned(), new pz_canvas(), new pz_zoomed(), new pz_svg(), new pz_scaled(), new pz_pannable(), new pz_bar(), new pz_readout());
    }
}
