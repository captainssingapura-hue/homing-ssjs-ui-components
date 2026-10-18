package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** glassmorphism's implementation of panel: CSS of its own - its content apart, the colours it draws with typed here. */
public record Panel() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseContainers.Panel, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.ShadeStrong(), new BaseColours.ShadeDeep(), new BaseColours.SheenSoft());
    }
}
