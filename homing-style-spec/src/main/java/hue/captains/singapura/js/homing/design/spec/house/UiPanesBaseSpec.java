package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule;
import java.util.List;

/** UiPanesComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiPanesBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPanesBaseSpec INSTANCE = new UiPanesBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPanesComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.MultiTabPane()),
                new Implemented<>(HousePanes.SingleTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SingleTabPane()),
                new Implemented<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabStrip()),
                new Implemented<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.Tab()),
                new Implemented<>(HousePanes.TabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPane()),
                new Implemented<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener()),
                new Implemented<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                new Implemented<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.AddTab()),
                new Implemented<>(HouseIcons.ColumnIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ColumnIcon()),
                new Implemented<>(HouseIcons.RowIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.RowIcon()),
                new Implemented<>(HouseIcons.MergeIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.MergeIcon()),
                new Implemented<>(HouseIcons.DetachIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.DetachIcon()),
                new Implemented<>(HouseIcons.WithinIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.WithinIcon()));
    }
}
