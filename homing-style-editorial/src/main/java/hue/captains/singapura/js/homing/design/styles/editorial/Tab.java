package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The tab in editorial: one tab of a strip - its ground, its edge, its lift and its shadow at rest, under the hand, in
 * use, lifted while the keys are on its strip, and in the hand. Whole: every property its look has (D9).
 */
public record Tab() implements CastingStyle<BaseScheme, Editorial, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Tab> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(),
                new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft(),
                new BaseColours.ShadeSoft(), new BaseColours.ShadeStrong(), new BaseColours.ShadeDeep());
    }
}
