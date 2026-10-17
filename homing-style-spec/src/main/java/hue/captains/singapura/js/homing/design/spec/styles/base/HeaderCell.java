package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of header-cell: CSS of its own. */
public record HeaderCell() implements CastingStylePerRole<BaseScheme, Base, HouseContainers.HeaderCell, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseContainers.HeaderCell, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.HeaderCell> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.HeaderCell.INSTANCE, HouseText.TextCell.INSTANCE, HouseSaying.Title.INSTANCE, new TextCellTitle())));
    }
}
