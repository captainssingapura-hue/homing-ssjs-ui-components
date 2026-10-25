package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** The plain container in the base layer: nothing of itself - its spacing alone, which a style may give; base gives none. */
public record PlainContainer() implements CastingStyle<BaseScheme, Base, HouseContainers.PlainContainer, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.PlainContainer> style() {
        return new StylingRes.CssContent<>();
    }
}
