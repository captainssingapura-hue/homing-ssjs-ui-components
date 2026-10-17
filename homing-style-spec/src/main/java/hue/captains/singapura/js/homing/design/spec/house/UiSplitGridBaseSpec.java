package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;
import hue.captains.singapura.js.homing.ui.taxonomy.UiSplitGridComponentModule;
import java.util.List;

/** UiSplitGridComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiSplitGridBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiSplitGridBaseSpec INSTANCE = new UiSplitGridBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiSplitGridComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseSplitGrid.SplitGrid.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SplitGrid()),
                new Implemented<>(HouseSplitGrid.SplitGridMirror.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SplitGridMirror()),
                new Implemented<>(HouseSplitGrid.GridCell.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.GridCell()));
    }
}
