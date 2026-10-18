package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** glassmorphism's implementation of toggle-groove_span: as its component standalone, by delegation. */
public record ToggleGrooveSpan() implements RootDelegation<BaseScheme, Glassmorphism, HouseMarks.ToggleGroove, HouseSaying.Span> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseMarks.ToggleGroove, RoleRoot> to() { return new ToggleGroove(); }
}
