package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** base's implementation of multi-tab-pane: CSS of its own. */
public record MultiTabPane() implements CastingStylePerRole<BaseScheme, Base, HousePanes.MultiTabPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HousePanes.MultiTabPane, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Accent(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
