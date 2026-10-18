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

/** neumorphism's implementation of menu-item: CSS of its own - its content apart, the colours it draws with typed here. */
public record MenuItem() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseItems.MenuItem, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseItems.MenuItem, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentWashSoft(), new BaseColours.AccentWashStrong());
    }
}
