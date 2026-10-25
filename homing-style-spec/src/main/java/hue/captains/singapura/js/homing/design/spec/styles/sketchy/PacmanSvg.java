package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.spec.house.SvgWithColorScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;

import java.util.List;

/** sketchy's own pacman, drawn by hand: its body in the accent, outlined in the colour it is given; its eye in the colour it is given; a trail of pellets in the success ink, a big one in the danger ink. */
public record PacmanSvg() implements SvgWithColorScheme<BaseScheme> {
    @Override public List<Palettable<? super BaseScheme, ?>> colours() {
        return List.of(new BaseColours.Accent(), new BaseColours.SuccessInk(), new BaseColours.DangerInk());
    }
}
