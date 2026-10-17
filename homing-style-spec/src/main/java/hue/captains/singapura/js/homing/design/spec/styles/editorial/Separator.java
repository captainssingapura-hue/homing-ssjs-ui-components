package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** editorial's look of separator: CSS of its own. */
public record Separator() implements CastingStylePerRole<BaseScheme, Editorial, HouseMarks.Separator, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseMarks.Separator, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Hairline());
    }
}
