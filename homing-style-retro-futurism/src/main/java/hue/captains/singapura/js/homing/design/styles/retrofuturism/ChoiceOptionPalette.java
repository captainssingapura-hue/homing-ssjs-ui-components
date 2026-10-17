package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** RetroFuturism's choice option palette: as its choice option on its own, by delegation. */
public record ChoiceOptionPalette()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseItems.ChoiceOption, HouseDoing.Palette> {
    @Override public StylingRes<BaseScheme, HouseItems.ChoiceOption> style() {
        return new StylingRes.Delegation<>(new ChoiceOption());
    }
}
