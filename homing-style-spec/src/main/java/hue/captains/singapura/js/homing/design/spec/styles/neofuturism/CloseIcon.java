package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;

/** neo-futurism's implementation of close-icon: CSS of its own. */
public record CloseIcon() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseIcons.CloseIcon, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseIcons.CloseIcon, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
