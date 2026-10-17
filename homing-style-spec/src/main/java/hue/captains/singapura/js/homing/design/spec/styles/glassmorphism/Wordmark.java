package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's look of wordmark: CSS of its own. */
public record Wordmark() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Wordmark, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
