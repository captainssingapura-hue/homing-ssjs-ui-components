package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Base's icon button add: as its icon button on its own, by delegation. */
public record IconButtonAdd()
        implements CastingStyle<BaseScheme, Base, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<BaseScheme, HouseControls.IconButton> style() {
        return new StylingRes.Delegation<>(new IconButton());
    }
}
