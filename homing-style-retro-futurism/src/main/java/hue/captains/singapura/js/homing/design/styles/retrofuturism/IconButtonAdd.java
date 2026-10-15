package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's icon button add: as its icon button on its own, by delegation. */
public record IconButtonAdd()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.IconButton> style() {
        return new StylingRes.Delegation<>(new IconButton());
    }
}
