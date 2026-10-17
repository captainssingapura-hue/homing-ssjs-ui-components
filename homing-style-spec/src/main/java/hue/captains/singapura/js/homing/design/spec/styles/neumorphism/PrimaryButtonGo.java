package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** neumorphism's look of primary-button_go: CSS of its own. */
public record PrimaryButtonGo() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.PrimaryButton, HouseDoing.Go> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.PrimaryButton, HouseDoing.Go> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.ShadeFirm(), new ButtonGround(), new ButtonLabel());
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
