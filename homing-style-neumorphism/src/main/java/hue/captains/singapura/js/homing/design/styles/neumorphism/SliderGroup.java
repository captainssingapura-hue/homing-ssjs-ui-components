package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The slider group in neumorphism: sliders gathered under one heading - raised, no edge drawn, its corner the style's,
 * its room round its heading and its rack; a press anywhere in it takes the keys, so it points; its keys' rings, a
 * candidate dashed, lent thin, held whole. Whole: every property its look has (D9).
 */
public record SliderGroup()
        implements CastingStyle<BaseScheme, Neumorphism, HouseContainers.SliderGroup, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.SliderGroup> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.Accent(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
