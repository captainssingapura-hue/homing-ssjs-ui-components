package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMonitors;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of focus-monitor: its own implementation, and a spec for each of its slots. */
public record FocusMonitorDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseMonitors.FocusMonitor, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseMonitors.FocusMonitor, RoleRoot> own() { return new FocusMonitor(); }

    @Override public PartsSpec<BaseScheme, Base, HouseMonitors.FocusMonitor> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseMonitors.FocusMonitor.INSTANCE, HouseItems.ListRow.INSTANCE, HouseShaping.Entry.INSTANCE)));
    }
}
