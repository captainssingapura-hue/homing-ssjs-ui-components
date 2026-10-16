package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The caption in neo-brutalism: a quieter line beside what it tells of. Whole: every property its look has (D9). */
public record Caption() implements CastingStyle<BaseScheme, NeoBrutalism, HouseText.Caption, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Caption> style() {
        return new StylingRes.CssContent<>(new HouseColours.TextFaded());
    }
}
