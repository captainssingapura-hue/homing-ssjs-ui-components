package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's look of caption_note: CSS of its own. */
public record CaptionNote() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Note> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Note> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded());
    }
}
