package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's look of caption_figure: CSS of its own. */
public record CaptionFigure() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Figure> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Figure> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
