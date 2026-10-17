package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule;

import java.util.List;

/** UiPanesComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiPanesBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPanesBaseSpec INSTANCE = new UiPanesBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPanesComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HousePanes.MultiTabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MultiTabPaneDeclaration())),
                new StandaloneEntry<>(HousePanes.TabPicker.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabPickerDeclaration())),
                new StandaloneEntry<>(HousePanes.SingleTabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SingleTabPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.TabStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabStripDeclaration())),
                new StandaloneEntry<>(HouseControls.Tab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabDeclaration())),
                new StandaloneEntry<>(HousePanes.TabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabPaneDeclaration())),
                new StandaloneEntry<>(HousePanes.TabOpener.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpenerDeclaration())),
                new StandaloneEntry<>(HousePanes.AddTab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.AddTabDeclaration())),
                new StandaloneEntry<>(HousePanes.PaneThumbs.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbsDeclaration())),
                new StandaloneEntry<>(HouseRegions.StripTail.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.StripTail())));
    }
}
