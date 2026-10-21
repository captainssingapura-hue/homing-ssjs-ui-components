package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.spec.house.SvgWithColorScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;

import java.util.List;

/** base's preferences: three sliders, each set to your liking - their lines in the text's colour, their knobs in the accent, ringed in the text's colour. */
public record PreferencesSvg() implements SvgWithColorScheme<BaseScheme> {
    @Override public List<Palettable<? super BaseScheme, ?>> colours() {
        return List.of(new BaseColours.Text(), new BaseColours.Accent());
    }
}
