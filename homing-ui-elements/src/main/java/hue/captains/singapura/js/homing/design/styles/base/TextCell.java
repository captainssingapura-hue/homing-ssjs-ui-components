package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The text cell in the base layer: a table cell's words, plain, in the text's ink and the page's type (D42). Whole: every property its look has (D9). */
public record TextCell() implements CastingStyle<BaseScheme, Base, HouseText.TextCell, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.TextCell> style() {
        return new StylingRes.CssContent<>(new HouseColours.Text());
    }
}
