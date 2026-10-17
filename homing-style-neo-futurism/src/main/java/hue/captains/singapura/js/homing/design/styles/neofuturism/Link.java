package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The link in neo-futurism: a way somewhere, in the words around it. Whole: every property its look has (D9). */
public record Link() implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.Link, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Link> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.TextFaded(), new BaseColours.WayHovered(), new BaseColours.Ring());
    }
}
