package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** glassmorphism's look of select_what: as its component on its own, by delegation. */
public record SelectWhat() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.Select, HouseDoing.What> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.Select, HouseDoing.What> style() {
        return new StylingRes.RootDelegation<>(new Select());
    }
}
