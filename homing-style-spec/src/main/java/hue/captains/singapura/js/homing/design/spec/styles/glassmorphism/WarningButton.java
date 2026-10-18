package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** glassmorphism's implementation of warning-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record WarningButton() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.WarningButton, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.WarningInk(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.ShadeSoft(), new BaseColours.ShadeFirm(), new BaseColours.Sheen(), new BaseColours.WarningGround());
    }
}
