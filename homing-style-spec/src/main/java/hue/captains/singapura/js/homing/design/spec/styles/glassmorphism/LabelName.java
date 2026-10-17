package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's look of label_name: CSS of its own. */
public record LabelName() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Label, HouseSaying.Name> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Label, HouseSaying.Name> style() {
        return new StylingRes.CssContent<>();
    }
}
