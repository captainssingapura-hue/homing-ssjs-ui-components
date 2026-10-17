package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import java.util.List;

/** editorial's look of switch_toggle: CSS of its own. */
public record SwitchToggle() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.Switch, HouseDoing.Toggle> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.Switch, HouseDoing.Toggle> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }

    @Override public PartsSpec<BaseScheme, Editorial, HouseControls.Switch> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseSaying.Span.INSTANCE, new ToggleGrooveSpan()),
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new ToggleKnobThumb())));
    }
}
