package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of link_open: as its component standalone, by delegation. */
public record LinkOpen() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.Link, HouseDoing.Open> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.Link, HouseDoing.Open> style() {
        return new StylingRes.RootDelegation<>(new Link());
    }
}
