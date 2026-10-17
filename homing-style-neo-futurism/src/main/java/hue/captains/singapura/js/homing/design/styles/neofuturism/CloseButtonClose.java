package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** NeoFuturism's close button close: as its close button on its own, by delegation. */
public record CloseButtonClose()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.CloseButton, HouseDoing.Close> {
    @Override public StylingRes<BaseScheme, HouseControls.CloseButton> style() {
        return new StylingRes.Delegation<>(new CloseButton());
    }
}
