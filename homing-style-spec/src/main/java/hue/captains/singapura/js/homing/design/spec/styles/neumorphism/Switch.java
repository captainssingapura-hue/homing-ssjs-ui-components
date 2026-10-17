package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import java.util.List;

/** neumorphism's look of switch: CSS of its own. */
public record Switch() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.Switch, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.Switch, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseControls.Switch> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseSaying.Span.INSTANCE, new ToggleGrooveSpan()),
                new PartSpec.Cast<>(HouseControls.Switch.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new ToggleKnobThumb())));
    }
}
