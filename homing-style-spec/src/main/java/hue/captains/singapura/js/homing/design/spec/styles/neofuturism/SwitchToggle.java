package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import java.util.List;

/** neo-futurism's look of switch_toggle: as its component on its own, by delegation. */
public record SwitchToggle() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Switch, HouseDoing.Toggle> style() {
        return new StylingRes.RootDelegation<>(new Switch());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseControls.Switch> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseSaying.Span.INSTANCE, new ToggleGrooveSpan()),
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new ToggleKnobThumb())));
    }
}
