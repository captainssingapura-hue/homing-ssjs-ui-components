package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** sketchy's implementation of heading5: CSS of its own - its content apart, the colours it draws with typed here. */
public record Heading5() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Heading5, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Heading5, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.HeadingInk());
    }
}
