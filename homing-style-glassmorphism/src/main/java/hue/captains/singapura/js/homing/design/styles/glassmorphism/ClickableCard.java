package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The clickable card in glassmorphism: a card that is a button - the plain card's frame, lifted under the hand, ringed
 * by the focus, marked where the keys are, sunk under the press. Whole: every property its look has (D9).
 */
public record ClickableCard()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.ClickableCard> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.Accent(), new HouseColours.Ring(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent(), new HouseColours.ShadeSoft(),
                new HouseColours.ShadeFirm());
    }
}
