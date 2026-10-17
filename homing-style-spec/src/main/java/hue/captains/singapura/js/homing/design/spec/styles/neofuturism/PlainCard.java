package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neo-futurism's look of plain-card: CSS of its own. */
public record PlainCard() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseContainers.PlainCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseContainers.PlainCard, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.ShadeSoft(), new BaseColours.GlowFaint());
    }
}
