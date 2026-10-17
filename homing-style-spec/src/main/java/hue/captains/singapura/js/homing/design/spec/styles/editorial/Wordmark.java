package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of wordmark: CSS of its own. */
public record Wordmark() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Wordmark, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
