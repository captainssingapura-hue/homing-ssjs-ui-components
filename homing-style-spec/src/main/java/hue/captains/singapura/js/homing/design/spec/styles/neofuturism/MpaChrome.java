package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

/** neo-futurism's implementation of mpa-chrome: the base's, by delegation. */
public record MpaChrome() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseSite.MpaChrome, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseSite.MpaChrome, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChrome());
    }
}
