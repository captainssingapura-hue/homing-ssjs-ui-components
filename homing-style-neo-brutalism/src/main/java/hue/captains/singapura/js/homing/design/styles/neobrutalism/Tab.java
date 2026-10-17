package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The tab in neo-brutalism: one tab of a strip - its ground, its edge, its lift and its shadow at rest, under the hand,
 * in use, lifted while the keys are on its strip, and in the hand. Whole: every property its look has (D9).
 */
public record Tab() implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Tab> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Inverted(), new BaseColours.Text(), new BaseColours.OnInverted(),
                new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(),
                new BaseColours.AccentWashSoft());
    }
}
