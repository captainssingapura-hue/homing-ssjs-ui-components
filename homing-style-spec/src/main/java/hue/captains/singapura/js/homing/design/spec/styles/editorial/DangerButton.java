package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** editorial's implementation of danger-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record DangerButton() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.DangerButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.DangerButton, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Ring(), new BaseColours.ShadeFaint(), new BaseColours.ShadeLight(), new BaseColours.ShadeMedium(), new BaseColours.DangerEdge(), new BaseColours.DangerGround(), new BaseColours.DangerWriting());
    }
}
