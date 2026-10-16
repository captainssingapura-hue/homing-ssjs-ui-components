package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The plain card in editorial: a frame its caller fills - its ground, its edge, its corner, its shadow and its measure;
 * still under the hand. Whole: every property its look has (D9).
 */
public record PlainCard() implements CastingStyle<BaseScheme, Editorial, HouseContainers.PlainCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.PlainCard> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.ShadeFaint());
    }
}
