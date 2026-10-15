package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The danger badge in glassmorphism: danger said by a badge, its ink on its wash. Whole: every property its look has
 * (D9).
 */
public record DangerBadge() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseText.DangerBadge, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.DangerBadge> style() {
        return new StylingRes.CssContent<>(new HouseColours.DangerInk(), new HouseColours.DangerWash());
    }
}
