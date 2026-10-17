package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** editorial's look of clickable-card: CSS of its own. */
public record ClickableCard() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.ClickableCard, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent(), new BaseColours.ShadeFaint(), new BaseColours.ShadeLight(), new BaseColours.ShadeMedium());
    }
}
