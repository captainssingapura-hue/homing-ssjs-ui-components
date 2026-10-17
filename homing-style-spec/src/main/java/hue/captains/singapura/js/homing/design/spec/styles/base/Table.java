package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** base's look of table: CSS of its own. */
public record Table() implements CastingStylePerRole<BaseScheme, Base, HouseRegions.Table, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseRegions.Table, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HouseRegions.Table> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseRegions.Table.INSTANCE, HouseContainers.HeaderCell.INSTANCE, HouseShaping.Column.INSTANCE, new HeaderCell()),
                new PartSpec.Placed<>(HouseRegions.Table.INSTANCE, HouseContainers.DataCell.INSTANCE, HouseShaping.Cell.INSTANCE, new DataCell())));
    }
}
