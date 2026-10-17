package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The floating pane in glassmorphism: a pane over the page - raised, framed, its corner the style's, casting its
 * shadow; the current one ringed; lit and lifted under the hand through its head, lifted further while in it. Whole:
 * every property its look has (D9).
 */
public record FloatingPane()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatingPane> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.ShadeFirm(), new BaseColours.ShadeHeavy(), new BaseColours.ShadeSolid(),
                new BaseColours.ShadeThick(), new BaseColours.GlowMedium());
    }
}
