package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of steward-monitor: its own implementation, and a spec for each of its slots. */
public record StewardMonitorDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseMonitors.StewardMonitor, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseMonitors.StewardMonitor, RoleRoot> own() { return new StewardMonitor(); }

    @Override public PartsSpec<BaseScheme, Base, HouseMonitors.StewardMonitor> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseMonitors.StewardMonitor.INSTANCE, HouseMarks.Lamp.INSTANCE, HouseShaping.Body.INSTANCE)));
    }
}
