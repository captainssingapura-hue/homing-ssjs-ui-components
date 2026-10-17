package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The wordmark in glassmorphism: a name set as the mark of whom it belongs to. Whole: every property its look has (D9).
 */
public record Wordmark() implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Wordmark> style() {
        return new StylingRes.CssContent<>();
    }
}
