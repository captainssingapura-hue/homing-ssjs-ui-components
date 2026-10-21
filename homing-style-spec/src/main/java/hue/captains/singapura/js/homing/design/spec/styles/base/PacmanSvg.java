package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.spec.house.SvgWithColorScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;

import java.util.List;

/** base's pacman: its body in the accent, its eye in the text's colour, its pellets in the danger ink. */
public record PacmanSvg() implements SvgWithColorScheme<BaseScheme> {
    @Override public List<Palettable<? super BaseScheme, ?>> colours() {
        return List.of(new BaseColours.Accent(), new BaseColours.Text(), new BaseColours.DangerInk());
    }
}
