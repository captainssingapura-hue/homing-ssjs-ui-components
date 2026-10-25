package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.spec.house.SvgWithColorScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;

import java.util.List;

/** base's rotate: a turn clockwise - its arc in the colour it is given, its head in the accent. */
public record RotateSvg() implements SvgWithColorScheme<BaseScheme> {
    @Override public List<Palettable<? super BaseScheme, ?>> colours() {
        return List.of(new BaseColours.Accent());
    }
}
