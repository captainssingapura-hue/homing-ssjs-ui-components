package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** glassmorphism's implementation of choice-option: CSS of its own. */
public record ChoiceOption() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseItems.ChoiceOption, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseItems.ChoiceOption, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.AccentWashSoft());
    }
}
