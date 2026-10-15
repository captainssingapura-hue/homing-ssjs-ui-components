package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoFuturism's icon button add: as its icon button on its own, by delegation. */
public record IconButtonAdd()
        implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.IconButton> style() {
        return new StylingRes.Delegation<>(new IconButton());
    }
}
