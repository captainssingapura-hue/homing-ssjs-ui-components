package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** neumorphism's implementation of secondary-button: CSS of its own. */
public record SecondaryButton() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.SecondaryButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.SecondaryButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new ButtonLabel(), new BaseColours.AccentEdge(), new BaseColours.Ring(), new BaseColours.ShadeFirm(), new ButtonGround());
    }

    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the accent at 1 (D41). */
    public record ButtonGround() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public BaseColours.Scales parent() { return new BaseColours.Scales(); }
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Clear(), -1), new Knot<>(new BaseColours.SecondaryNeutral(), 0),
                           new Knot<>(new BaseColours.Accent(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the accent from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public BaseColours.Scales parent() { return new BaseColours.Scales(); }
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Text(), -1), new Knot<>(new BaseColours.Text(), 0.4),
                           new Knot<>(new BaseColours.OnAccent(), 0.6), new Knot<>(new BaseColours.OnAccent(), 1));
        }
    }
}
