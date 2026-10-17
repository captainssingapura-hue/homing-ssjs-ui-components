package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** base's declaration of table: its own implementation, and a spec for each of its slots. */
public record TableDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseRegions.Table, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseRegions.Table, RoleRoot> own() { return new Table(); }

    @Override public PartsSpec<BaseScheme, Base, HouseRegions.Table> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseRegions.Table.INSTANCE, HouseContainers.HeaderCell.INSTANCE, HouseShaping.Column.INSTANCE),
                new PartSpec.Standalone<>(HouseRegions.Table.INSTANCE, HouseContainers.DataCell.INSTANCE, HouseShaping.Cell.INSTANCE)));
    }
}
