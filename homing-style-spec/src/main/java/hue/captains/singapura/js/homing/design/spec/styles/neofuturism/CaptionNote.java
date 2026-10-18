package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of caption_note: as its component standalone, by delegation. */
public record CaptionNote() implements RootDelegation<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Note> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseText.Caption, RoleRoot> to() { return new Caption(); }
}
