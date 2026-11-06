package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.styles.base.*;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule;

import java.util.List;

/**
 * UiPanesComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiPanesBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPanesBaseSpec INSTANCE = new UiPanesBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPanesComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HousePanes.MultiTabPane.INSTANCE, new MultiTabPane()),
                new ComponentAssignment<>(HousePanes.SingleTabPane.INSTANCE, new SingleTabPane()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new TabStrip()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new Tab()),
                new ComponentAssignment<>(HousePanes.TabPane.INSTANCE, new TabPane()),
                new ComponentAssignment<>(HousePanes.AddTab.INSTANCE, new AddTab()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new FloatingPane()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new TitleBar()),
                new ComponentAssignment<>(HouseIcons.ColumnIcon.INSTANCE, new ColumnIcon()),
                new ComponentAssignment<>(HouseIcons.RowIcon.INSTANCE, new RowIcon()),
                new ComponentAssignment<>(HouseIcons.MergeIcon.INSTANCE, new MergeIcon()),
                new ComponentAssignment<>(HouseIcons.DetachIcon.INSTANCE, new DetachIcon()),
                new ComponentAssignment<>(HouseIcons.WithinIcon.INSTANCE, new WithinIcon()),
                new ComponentAssignment<>(HousePanes.PaneThumbs.INSTANCE, new PaneThumbs()));
    }
}
