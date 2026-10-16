package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The plain button in neo-brutalism: a button that does one ordinary thing. Whole: every property its look has (D9).
 */
public record PlainButton()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.PlainButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.PlainButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Inverted(), new BaseColours.Text(),
                new BaseColours.Border(), new BaseColours.Ring());
    }
}
