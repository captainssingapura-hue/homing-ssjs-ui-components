package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** retro-futurism's implementation of success-badge: CSS of its own - its content apart, the colours it draws with typed here. */
public record SuccessBadge() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.SuccessBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.SuccessBadge, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.SuccessInk(), new BaseColours.SuccessWash());
    }
}
