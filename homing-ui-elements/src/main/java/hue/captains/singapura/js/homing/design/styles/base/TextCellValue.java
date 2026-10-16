package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Base's text cell as a data cell's value: as the text cell on its own, by delegation (D42). */
public record TextCellValue() implements CastingStyle<ColorScheme.Base, Base, HouseText.TextCell, HouseSaying.Value> {
    @Override public StylingRes<ColorScheme.Base, HouseText.TextCell> style() {
        return new StylingRes.Delegation<>(new TextCell());
    }
}
