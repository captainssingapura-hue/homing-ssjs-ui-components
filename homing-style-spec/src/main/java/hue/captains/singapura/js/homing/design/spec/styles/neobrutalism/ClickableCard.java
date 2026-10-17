package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-brutalism's look of clickable-card: CSS of its own. */
public record ClickableCard() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.ClickableCard, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Inverted(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
