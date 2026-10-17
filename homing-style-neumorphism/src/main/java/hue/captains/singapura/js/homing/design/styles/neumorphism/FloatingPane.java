package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The floating pane in neumorphism: a pane over the page - raised, no edge drawn, its corner the style's, casting its
 * shadow; the current one ringed; lit and lifted under the hand through its head, lifted further while in it (no edge,
 * so its ring shows in its shadow alone). Whole: every property its look has (D9).
 */
public record FloatingPane()
        implements CastingStyle<BaseScheme, Neumorphism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatingPane> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.ShadeHeavy(), new BaseColours.GlowMild());
    }
}
