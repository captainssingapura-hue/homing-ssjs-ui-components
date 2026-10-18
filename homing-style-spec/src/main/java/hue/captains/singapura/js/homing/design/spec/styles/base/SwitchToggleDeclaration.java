package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

import java.util.List;

/** base's declaration of switch_toggle: its own implementation, and a spec for each of its slots. */
public record SwitchToggleDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public CastingImplementation<BaseScheme, Base, HouseControls.Switch, HouseDoing.Toggle> own() { return new SwitchToggle(); }

    @Override public PartsSpec<BaseScheme, Base, HouseControls.Switch> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.Switch.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseSaying.Span.INSTANCE, new Target.Impl<>(new ToggleGrooveSpan())),
                new PartSpec.SameRole<>(HouseControls.Switch.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new Target.Impl<>(new ToggleKnobThumb()))));
    }
}
