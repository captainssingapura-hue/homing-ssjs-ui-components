package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The floating pane in glassmorphism: a pane over the page - raised, framed, its corner the style's, casting its
 * shadow; the current one ringed; lit and lifted under the hand through its head, lifted further while in it. Whole:
 * every property its look has (D9).
 */
public record FloatingPane()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.FloatingPane> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(), new HouseColours.Ring(),
                new HouseColours.ShadeFirm(), new HouseColours.ShadeHeavy(), new HouseColours.ShadeSolid(),
                new HouseColours.ShadeThick(), new HouseColours.GlowMedium());
    }
}
