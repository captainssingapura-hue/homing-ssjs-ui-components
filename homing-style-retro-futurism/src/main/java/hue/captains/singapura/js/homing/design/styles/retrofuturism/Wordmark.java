package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The wordmark in retro-futurism: a name set as the mark of whom it belongs to. Whole: every property its look has
 * (D9).
 */
public record Wordmark() implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseText.Wordmark, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Wordmark> style() {
        return new StylingRes.CssContent<>();
    }
}
