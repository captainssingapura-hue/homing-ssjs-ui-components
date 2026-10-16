package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The badge in editorial: a tag on the accent's wash. Whole: every property its look has (D9). */
public record Badge() implements CastingStyle<BaseScheme, Editorial, HouseText.Badge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Badge> style() {
        return new StylingRes.CssContent<>(new HouseColours.AccentWash());
    }
}
