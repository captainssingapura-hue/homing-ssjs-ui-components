package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** neo-brutalism's implementation of choice-option: CSS of its own - its content apart, the colours it draws with typed here. */
public record ChoiceOption() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseItems.ChoiceOption, RoleRoot> style() {
        return new StylingRes.CssPlaceHolder<>();
    }

    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.AccentWashSoft());
    }
}
