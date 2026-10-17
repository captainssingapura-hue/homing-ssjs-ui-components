package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's implementation of wordmark: CSS of its own. */
public record Wordmark() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Wordmark, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
