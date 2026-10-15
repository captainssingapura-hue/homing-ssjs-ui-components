package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Editorial's plain button zoom in: as its plain button on its own, by delegation. */
public record PlainButtonZoomIn()
        implements CastingStyle<ColorScheme.Base, Editorial, HouseControls.PlainButton, HouseDoing.ZoomIn> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PlainButton> style() {
        return new StylingRes.Delegation<>(new PlainButton());
    }
}
