package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's implementation of plain-button_zoom-out: as its component standalone, by delegation. */
public record PlainButtonZoomOut() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.PlainButton, HouseDoing.ZoomOut> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.PlainButton, HouseDoing.ZoomOut> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
