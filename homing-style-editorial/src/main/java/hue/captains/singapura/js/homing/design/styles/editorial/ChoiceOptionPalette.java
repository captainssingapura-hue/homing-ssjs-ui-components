package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Editorial's choice option palette: as its choice option on its own, by delegation. */
public record ChoiceOptionPalette()
        implements CastingStyle<ColorScheme.Base, Editorial, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.ChoiceOption> style() {
        return new StylingRes.Delegation<>(new ChoiceOption());
    }
}
