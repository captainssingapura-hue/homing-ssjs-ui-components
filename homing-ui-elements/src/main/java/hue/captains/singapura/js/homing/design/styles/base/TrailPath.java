package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Base's trail path: as its trail on its own, by delegation. */
public record TrailPath() implements CastingStyle<BaseScheme, Base, HouseContainers.Trail, HouseDoing.Path> {
    @Override public StylingRes<BaseScheme, HouseContainers.Trail> style() {
        return new StylingRes.Delegation<>(new Trail());
    }
}
