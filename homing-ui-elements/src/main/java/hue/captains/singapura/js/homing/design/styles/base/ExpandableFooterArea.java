package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The expandable footer area in the base layer: its status footer draws its own; its area, opened over what it covers,
 * raised and ruled along its foot (23 Oct). Whole: every property its look has (D9).
 */
public record ExpandableFooterArea() implements CastingStyle<BaseScheme, Base, HouseContainers.ExpandableFooterArea, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ExpandableFooterArea> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border());
    }
}
