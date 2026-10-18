package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of header-cell: its own implementation, and a spec for each of its slots. */
public record HeaderCellDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseContainers.HeaderCell, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseContainers.HeaderCell, RoleRoot> own() { return new HeaderCell(); }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.HeaderCell> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.HeaderCell.INSTANCE, HouseText.TextCell.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new TextCellTitle()))));
    }
}
