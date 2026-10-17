package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's plain button choice: as its plain button on its own, by delegation. */
public record PlainButtonChoice()
        implements CastingStyle<BaseScheme, Sketchy, HouseControls.PlainButton, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, HouseControls.PlainButton> style() {
        return new StylingRes.Delegation<>(new PlainButton());
    }
}
