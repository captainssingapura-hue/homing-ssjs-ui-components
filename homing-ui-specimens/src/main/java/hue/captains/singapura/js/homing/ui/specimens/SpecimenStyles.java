package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.Wearable;

import java.util.List;

import static hue.captains.singapura.js.homing.design.DesignClass.of;
import static hue.captains.singapura.js.homing.design.Layer.Raised;
import static hue.captains.singapura.js.homing.design.Target.Color;
import static hue.captains.singapura.js.homing.design.Target.Shape;
import static hue.captains.singapura.js.homing.design.Target.Type;
import static hue.captains.singapura.js.homing.design.Text.Body;

/**
 * What a specimen is laid out in, and nothing of the components' own: the stage it stands on, a
 * row of what goes together, a box for a component that fills its host or lies over its foot, and
 * words. The components wear their own classes; these only place them.
 */
public record SpecimenStyles() implements CssGroup<SpecimenStyles> {

    public static final SpecimenStyles INSTANCE = new SpecimenStyles();

    /** A specimen's stage: what it shows, top to bottom, each part as wide as the stage. */
    public record sp_stage() implements CssClass<SpecimenStyles> {
        @Override public String body() { return "display: flex;\nflex-direction: column;\nalign-items: stretch;\ngap: 12px;\nmin-width: 0;\n"; }
    }

    /** What goes together, side by side, wrapping. */
    public record sp_row() implements CssClass<SpecimenStyles> {
        @Override public String body() { return "display: flex;\nflex-wrap: wrap;\nalign-items: center;\ngap: 8px;\n"; }
    }

    /** A framed box of its own height, positioned: what a component that fills its host, or lies over its foot, is put in. */
    public record sp_host() implements CssClass<SpecimenStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Raised.class, Color.Edge.class), of(Raised.class, Shape.Rule.class), of(Raised.class, Shape.Corner.class)); }
        @Override public String body() { return "position: relative;\ndisplay: flex;\nflex-direction: column;\nheight: 180px;\noverflow: hidden;\n"; }
    }

    /** Words in a specimen: what a box holds, what a mark is called. */
    public record sp_text() implements CssClass<SpecimenStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Body.class, Type.Face.class), of(Body.class, Color.Ink.class)); }
        @Override public String body() { return "margin: 0;\npadding: 8px 12px;\n"; }
    }

    /** A layer over the page, for what a specimen shows at a point: fixed, the hand passing through it except on what it shows. */
    public record sp_layer() implements CssClass<SpecimenStyles> {
        @Override public String body() { return "position: fixed;\ninset: 0;\nz-index: 10021;\npointer-events: none;\n"; }
    }

    /** Frames side by side, from the first, wrapping when the room runs out: a menu shown still. */
    public record sp_frames() implements CssClass<SpecimenStyles> {
        @Override public String body() { return "display: flex;\nflex-wrap: wrap;\nalign-items: flex-start;\ngap: 24px;\n"; }
    }

    @Override
    public List<CssClass<SpecimenStyles>> cssClasses() {
        return List.of(new sp_stage(), new sp_row(), new sp_host(), new sp_text(), new sp_layer(), new sp_frames());
    }
}
