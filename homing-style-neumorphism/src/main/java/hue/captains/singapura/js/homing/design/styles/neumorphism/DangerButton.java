package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The danger button in neumorphism: an action that destroys or cannot be undone: danger at 1, safety turned the other
 * way. Whole: every property its look has (D9).
 */
public record DangerButton()
        implements CastingStyle<BaseScheme, Neumorphism, HouseControls.DangerButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.DangerButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Ring(), new BaseColours.ShadeFirm(),
                new BaseColours.DangerEdge(), new BaseColours.DangerGround(), new BaseColours.DangerWriting());
    }
}
