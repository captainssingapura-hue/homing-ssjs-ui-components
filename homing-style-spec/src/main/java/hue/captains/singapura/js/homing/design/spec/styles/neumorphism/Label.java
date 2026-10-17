package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's look of label: CSS of its own. */
public record Label() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Label, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Label, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
