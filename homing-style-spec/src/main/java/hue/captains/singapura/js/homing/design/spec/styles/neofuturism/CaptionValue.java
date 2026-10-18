package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of caption_value: as its component standalone, by delegation. */
public record CaptionValue() implements RootDelegation<BaseScheme, NeoFuturism, HouseText.Caption, HouseSaying.Value> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseText.Caption, RoleRoot> to() { return new Caption(); }
}
