package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The clickable card in the base layer: a card that is a button - the plain card's frame, lifted under the hand, ringed
 * by the focus, marked where the keys are, sunk under the press. Whole: every property its look has (D9).
 */
public record ClickableCard() implements CastingStyle<ColorScheme.Base, Base, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.ClickableCard> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.Accent(), new HouseColours.Ring(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
