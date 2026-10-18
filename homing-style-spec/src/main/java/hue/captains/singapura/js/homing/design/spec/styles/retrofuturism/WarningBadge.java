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

/** retro-futurism's implementation of warning-badge: CSS of its own - its content apart, the colours it draws with typed here. */
public record WarningBadge() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.WarningBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.WarningBadge, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.WarningInk(), new BaseColours.WarningWash());
    }
}
