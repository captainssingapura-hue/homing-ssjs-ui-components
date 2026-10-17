package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's implementation of caption_note: as its component standalone, by delegation. */
public record CaptionNote() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Note> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Caption, HouseSaying.Note> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
