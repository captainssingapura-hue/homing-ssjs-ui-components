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
 * viewport - framed by a hairline, a control's rule, and its ring when it has the focus - clipping
 * the canvas the content moves on by three variables; the hand that grabs once there is somewhere
 * to pan; the bar and its readout.
 */
public record PanZoomStyles() implements CssGroup<PanZoomStyles> {

    public static final PanZoomStyles INSTANCE = new PanZoomStyles();

    /** The viewport: what is outside it clipped; a control to the keys, so it wears a control's rule and ring. */
    public record pz_view() implements CssClass<PanZoomStyles> {
        @Override public List<? extends Wearable> wears() {
            return List.of(of(Control.class, Shape.Rule.class), of(Control.class, Color.Edge.class), of(Hairline.class, Color.Edge.class));
        }
        @Override public String body() { return "position: relative;\noverflow: hidden;\nmin-width: 0;\n"; }
    }

    /** The canvas the content moves on: placed and scaled from its corner by the view's variables. */
    public record pz_canvas() implements CssClass<PanZoomStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--pz-x"), new CssVar("--pz-y"), new CssVar("--pz-scale")); }
        @Override public String body() {
            return "transform-origin: 0 0;\ntransform: translate(var(--pz-x, 0px), var(--pz-y, 0px)) scale(var(--pz-scale, 1));\n";
        }
    }

    /** The drawing: laid out no wider than the viewport, its height its own - the size it fits at. */
    public record pz_svg() implements CssClass<PanZoomStyles> {
        @Override public String body() { return "display: block;\nmax-width: 100%;\nheight: auto;\n"; }
    }

    /** Zoomed in, with somewhere to pan: the hand grabs, and a touch pans the drawing rather than the page. */
    public record pz_pannable() implements CssClass<PanZoomStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Dragging.class, Affordance.Cursor.class)); }
        @Override public String body() { return "touch-action: none;\n"; }
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
        return List.of(new pz_view(), new pz_canvas(), new pz_svg(), new pz_pannable(), new pz_bar(), new pz_readout());
    }
}
