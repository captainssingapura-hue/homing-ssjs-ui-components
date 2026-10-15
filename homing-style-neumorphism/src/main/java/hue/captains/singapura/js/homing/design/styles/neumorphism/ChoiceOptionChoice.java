package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** Neumorphism's choice option choice: as its choice option on its own, by delegation. */
public record ChoiceOptionChoice()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseItems.ChoiceOption, HouseDoing.Choice> {
    @Override public StylingRes<ColorScheme.Base, HouseItems.ChoiceOption> style() {
        return new StylingRes.Delegation<>(new ChoiceOption());
    }
}
