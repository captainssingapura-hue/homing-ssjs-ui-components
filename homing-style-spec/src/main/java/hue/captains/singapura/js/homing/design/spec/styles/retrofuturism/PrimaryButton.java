package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import java.util.List;

/** retro-futurism's look of primary-button: CSS of its own. */
public record PrimaryButton() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.PrimaryButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.PrimaryButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.GlowDim(), new BaseColours.GlowMedium(), new BaseColours.GlowStrong(), new BaseColours.GlowFull(), new ButtonGround(), new ButtonLabel());
    }

    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the house colour at 1 (D41). */
    public record ButtonGround() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public BaseColours.Scales parent() { return new BaseColours.Scales(); }
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Clear(), -1), new Knot<>(new BaseColours.PrimaryNeutral(), 0),
                           new Knot<>(new BaseColours.Title(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the house colour from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public BaseColours.Scales parent() { return new BaseColours.Scales(); }
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Text(), -1), new Knot<>(new BaseColours.Text(), 0.4),
                           new Knot<>(new BaseColours.OnInverted(), 0.6), new Knot<>(new BaseColours.OnInverted(), 1));
        }
    }
}
