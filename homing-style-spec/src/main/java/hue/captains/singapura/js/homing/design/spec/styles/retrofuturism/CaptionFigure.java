package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's look of caption_figure: as its component on its own, by delegation. */
public record CaptionFigure() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Figure> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Figure> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
