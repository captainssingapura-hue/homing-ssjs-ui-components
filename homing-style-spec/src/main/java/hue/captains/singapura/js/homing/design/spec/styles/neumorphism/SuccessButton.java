package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** neumorphism's implementation of success-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record SuccessButton() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.SuccessButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.SuccessButton, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.SuccessInk(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.ShadeFirm(), new BaseColours.SuccessGround());
    }
}
