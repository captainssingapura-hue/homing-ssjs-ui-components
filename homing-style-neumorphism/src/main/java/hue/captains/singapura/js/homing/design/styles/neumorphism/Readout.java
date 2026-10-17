package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The readout in neumorphism: a figure read off, its digits even. Whole: every property its look has (D9). */
public record Readout() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Readout, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Readout> style() {
        return new StylingRes.CssContent<>();
    }
}
