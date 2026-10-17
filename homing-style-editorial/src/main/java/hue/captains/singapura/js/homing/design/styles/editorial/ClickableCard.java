package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The clickable card in editorial: a card that is a button - the plain card's frame, lifted under the hand, ringed by
 * the focus, marked where the keys are, sunk under the press. Whole: every property its look has (D9).
 */
public record ClickableCard()
        implements CastingStyle<BaseScheme, Editorial, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ClickableCard> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.Ring(),
                new BaseColours.KeysCandidate(), new BaseColours.KeysLent(), new BaseColours.ShadeFaint(),
                new BaseColours.ShadeLight(), new BaseColours.ShadeMedium());
    }
}
