package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;
import hue.captains.singapura.js.homing.ui.taxonomy.UiSplitGridComponentModule;

import java.util.List;

/** UiSplitGridComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiSplitGridBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiSplitGridBaseSpec INSTANCE = new UiSplitGridBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiSplitGridComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseSplitGrid.SplitGrid.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SplitGridDeclaration())),
                new StandaloneEntry<>(HouseSplitGrid.SplitGridMirror.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SplitGridMirrorDeclaration())),
                new StandaloneEntry<>(HouseSplitGrid.GridCell.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.GridCell())));
    }
}
