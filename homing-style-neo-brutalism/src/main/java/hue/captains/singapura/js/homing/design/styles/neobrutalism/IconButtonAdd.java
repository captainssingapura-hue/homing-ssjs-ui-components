package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoBrutalism's icon button add: as its icon button on its own, by delegation. */
public record IconButtonAdd()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<BaseScheme, HouseControls.IconButton> style() {
        return new StylingRes.Delegation<>(new IconButton());
    }
}
