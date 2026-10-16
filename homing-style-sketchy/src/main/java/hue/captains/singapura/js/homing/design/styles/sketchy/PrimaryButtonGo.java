package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's primary button go: as its primary button on its own, by delegation. */
public record PrimaryButtonGo()
        implements CastingStyle<BaseScheme, Sketchy, HouseControls.PrimaryButton, HouseDoing.Go> {
    @Override public StylingRes<BaseScheme, HouseControls.PrimaryButton> style() {
        return new StylingRes.Delegation<>(new PrimaryButton());
    }
}
