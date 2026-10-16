package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The clickable card in neo-brutalism: a card that is a button - the plain card's frame, lifted under the hand, ringed
 * by the focus, marked where the keys are, sunk under the press. Whole: every property its look has (D9).
 */
public record ClickableCard()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ClickableCard> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Inverted(), new BaseColours.Border(),
                new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.KeysCandidate(),
                new BaseColours.KeysLent());
    }
}
