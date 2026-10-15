package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Sketchy's choice option style: as its choice option on its own, by delegation. */
public record ChoiceOptionStyle()
        implements CastingStyle<ColorScheme.Base, Sketchy, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.ChoiceOption> style() {
        return new StylingRes.Delegation<>(new ChoiceOption());
    }
}
