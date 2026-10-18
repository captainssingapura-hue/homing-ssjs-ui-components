package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** neumorphism's implementation of list-row: CSS of its own - its content apart, the colours it draws with typed here. */
public record ListRow() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseItems.ListRow, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseItems.ListRow, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.AccentWashSoft(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
