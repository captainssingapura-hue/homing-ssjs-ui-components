package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The title bar in glassmorphism: heads a pane that floats - the inverted layer, the page's ground written on it in the
 * style's face, ruled along its foot; where the hand takes it, and in the house's colour while the hand has it. Whole:
 * every property its look has (D9).
 */
public record TitleBar() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseContainers.TitleBar, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.TitleBar> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Inverted(), new HouseColours.OnInverted(), new HouseColours.OnBar(),
                new HouseColours.Border(), new HouseColours.Title());
    }
}
