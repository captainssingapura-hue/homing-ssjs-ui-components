package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The link in the base layer: a way somewhere, in the words around it. Whole: every property its look has (D9). */
public record Link() implements CastingStyle<BaseScheme, Base, HouseControls.Link, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Link> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.TextFaded(), new HouseColours.WayHovered(), new HouseColours.Ring());
    }
}
