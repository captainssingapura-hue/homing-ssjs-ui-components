package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The floating pane in editorial: a pane over the page - raised, framed, its corner the style's, casting its shadow;
 * the current one ringed; lit and lifted under the hand through its head, lifted further while in it. Whole: every
 * property its look has (D9).
 */
public record FloatingPane()
        implements CastingStyle<BaseScheme, Editorial, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatingPane> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.ShadeSolid(), new BaseColours.ShadeDense(), new BaseColours.GlowTrace(),
                new BaseColours.GlowLow(), new BaseColours.GlowGentle(), new BaseColours.GlowSoft());
    }
}
