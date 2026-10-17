package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** glassmorphism's implementation of tab-picker: the base's, by delegation. */
public record TabPicker() implements CastingStylePerRole<BaseScheme, Glassmorphism, HousePanes.TabPicker, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HousePanes.TabPicker, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker());
    }
}
