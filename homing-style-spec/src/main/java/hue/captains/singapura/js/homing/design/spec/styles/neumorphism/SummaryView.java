package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** neumorphism's implementation of summary-view: the base's, by delegation. */
public record SummaryView() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.SummaryView, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.SummaryView, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SummaryView());
    }
}
