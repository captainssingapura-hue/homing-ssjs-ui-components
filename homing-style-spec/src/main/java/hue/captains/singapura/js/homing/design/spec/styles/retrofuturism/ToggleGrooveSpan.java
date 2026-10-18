package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** retro-futurism's implementation of toggle-groove_span: as its component standalone, by delegation. */
public record ToggleGrooveSpan() implements RootDelegation<BaseScheme, RetroFuturism, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HouseMarks.ToggleGroove, RoleRoot> to() { return new ToggleGroove(); }
}
