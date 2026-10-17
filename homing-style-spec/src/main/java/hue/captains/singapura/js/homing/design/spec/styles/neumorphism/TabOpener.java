package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** neumorphism's implementation of tab-opener: the base's, by delegation. */
public record TabOpener() implements CastingStylePerRole<BaseScheme, Neumorphism, HousePanes.TabOpener, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HousePanes.TabOpener, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener());
    }
}
