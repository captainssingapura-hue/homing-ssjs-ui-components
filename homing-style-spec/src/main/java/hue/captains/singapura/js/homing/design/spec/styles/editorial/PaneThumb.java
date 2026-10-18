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

/** editorial's implementation of pane-thumb: CSS of its own - its content apart, the colours it draws with typed here. */
public record PaneThumb() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.PaneThumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.PaneThumb, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentTintFaint());
    }
}
