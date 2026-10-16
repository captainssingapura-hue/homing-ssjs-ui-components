package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The clickable card in retro-futurism: a card that is a button - the plain card's frame, lifted under the hand, ringed
 * by the focus, marked where the keys are, sunk under the press. Whole: every property its look has (D9).
 */
public record ClickableCard()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseContainers.ClickableCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ClickableCard> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.Accent(), new HouseColours.Ring(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent(), new HouseColours.GlowDim(),
                new HouseColours.GlowMedium(), new HouseColours.GlowStrong(), new HouseColours.GlowFull());
    }
}
