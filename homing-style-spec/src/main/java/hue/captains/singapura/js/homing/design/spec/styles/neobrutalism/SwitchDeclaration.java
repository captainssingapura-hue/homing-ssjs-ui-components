package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

import java.util.List;

/** neo-brutalism's declaration of switch: its own implementation, and a spec for each of its slots. */
public record SwitchDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseControls.Switch, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.Switch, RoleRoot> own() { return new Switch(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseControls.Switch> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.Switch.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseSaying.Span.INSTANCE, new Target.Impl<>(new ToggleGrooveSpan())),
                new PartSpec.SameRole<>(HouseControls.Switch.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new Target.Impl<>(new ToggleKnobThumb()))));
    }
}
