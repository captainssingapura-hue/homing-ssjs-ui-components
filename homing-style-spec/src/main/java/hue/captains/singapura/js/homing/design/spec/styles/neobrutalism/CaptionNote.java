package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's look of caption_note: as its component on its own, by delegation. */
public record CaptionNote() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseText.Caption, HouseSaying.Note> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseText.Caption, HouseSaying.Note> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
