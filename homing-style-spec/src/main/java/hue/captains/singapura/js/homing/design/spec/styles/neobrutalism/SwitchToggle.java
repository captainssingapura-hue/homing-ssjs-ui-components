package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import java.util.List;

/** neo-brutalism's look of switch_toggle: as its component on its own, by delegation. */
public record SwitchToggle() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.Switch, HouseDoing.Toggle> style() {
        return new StylingRes.RootDelegation<>(new Switch());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseControls.Switch> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseSaying.Span.INSTANCE, new ToggleGrooveSpan()),
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new ToggleKnobThumb())));
    }
}
