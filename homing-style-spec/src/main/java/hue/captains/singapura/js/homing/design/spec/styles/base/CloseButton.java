package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** base's implementation of close-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record CloseButton() implements CastingStylePerRole<BaseScheme, Base, HouseControls.CloseButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.CloseButton, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.OnDanger(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.Danger(), new BaseColours.DangerPressed());
    }
}
