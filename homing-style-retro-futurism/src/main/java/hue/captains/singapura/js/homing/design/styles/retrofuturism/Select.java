package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The select in retro-futurism: a value picked from a list that drops down, raised off the page. Whole: every property
 * its look has (D9).
 */
public record Select() implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.Select, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Select> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring());
    }
}
