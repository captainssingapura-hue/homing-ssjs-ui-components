package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** editorial's look of caption_note: as its component on its own, by delegation. */
public record CaptionNote() implements CastingStylePerRole<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Note> {
    @Override public StylingRes<BaseScheme, Editorial, HouseText.Caption, HouseSaying.Note> style() {
        return new StylingRes.RootDelegation<>(new Caption());
    }
}
