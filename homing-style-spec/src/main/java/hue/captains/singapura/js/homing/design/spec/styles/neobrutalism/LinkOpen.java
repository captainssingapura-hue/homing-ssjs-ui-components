package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's look of link_open: CSS of its own. */
public record LinkOpen() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.Link, HouseDoing.Open> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.Link, HouseDoing.Open> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded(), new BaseColours.WayHovered(), new BaseColours.Ring());
    }
}
