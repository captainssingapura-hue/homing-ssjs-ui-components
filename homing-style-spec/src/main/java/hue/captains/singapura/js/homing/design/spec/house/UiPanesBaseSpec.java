package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
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
                new ComponentAssignment<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.MultiTabPane(), List.of()),
                new ComponentAssignment<>(HousePanes.SingleTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SingleTabPane(), List.of()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabStrip(), List.of()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.Tab(), List.of()),
                new ComponentAssignment<>(HousePanes.TabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPane(), List.of()),
                new ComponentAssignment<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener(), List.of()),
                new ComponentAssignment<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.TabPicker()))),
                new ComponentAssignment<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.AddTab(), List.of()),
                new ComponentAssignment<>(HouseIcons.ColumnIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ColumnIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.RowIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.RowIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.MergeIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.MergeIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.DetachIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.DetachIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.WithinIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.WithinIcon(), List.of()),
                new ComponentAssignment<>(HouseRegions.StripTail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.StripTail(), List.of()),
                new ComponentAssignment<>(HousePanes.PaneThumbs.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbs()))));
    }
}
