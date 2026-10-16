package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The slider group in editorial: sliders gathered under one heading - raised, framed, its corner the style's, its room
 * round its heading and its rack; a press anywhere in it takes the keys, so it points; its keys' rings, a candidate
 * dashed, lent thin, held whole. Whole: every property its look has (D9).
 */
public record SliderGroup()
        implements CastingStyle<BaseScheme, Editorial, HouseContainers.SliderGroup, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.SliderGroup> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(),
                new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
