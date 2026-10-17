package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neumorphism's implementation of danger-button: CSS of its own. */
public record DangerButton() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.DangerButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.DangerButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Ring(), new BaseColours.ShadeFirm(), new BaseColours.DangerEdge(), new BaseColours.DangerGround(), new BaseColours.DangerWriting());
    }
}
