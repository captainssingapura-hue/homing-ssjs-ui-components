package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** base's implementation of clickable-card: CSS of its own - its content apart, the colours it draws with typed here. */
public record ClickableCard() implements CssImplementation<BaseScheme, Base, HouseContainers.ClickableCard> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
