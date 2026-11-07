package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.icons.UiIconsCrate;
import hue.captains.singapura.js.homing.ui.elements.UiElementsCrate;
import hue.captains.singapura.js.homing.ui.look.UiLookCrate;

import java.util.List;

/**
 * The panes' crate: the multi-tab pane, its strip, where a new tab comes from and the control that
 * puts one somewhere, the events and their styles, on the runtime
 * ({@code ServerCrate}, the base's crate under its old name) and the design
 * targets. The pane, the strip and the hand on its chips are primitives, owning the DOM on their branch; the events and the drag's arithmetic are pure logic.
 */
public final class UiPanesCrate implements Crate, ComponentVehicle {

    public static final UiPanesCrate INSTANCE = new UiPanesCrate();

    private UiPanesCrate() {}

    @Override public String name() { return "homing-ui-panes"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, UiIconsCrate.INSTANCE, UiLookCrate.INSTANCE, UiElementsCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiPanesComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                new CrateEntry(MultiTabPaneModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(SingleTabPaneModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabStripModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabStripBehaviorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabStripSlideModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(MultiTabPaneFloatsModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(MultiTabPaneMenuModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(MultiTabPaneBehaviorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(FloatingPaneModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(FloatingPaneBehaviorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TitleBarModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TitleBarBehaviorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabBehaviorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabPaneModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabRegisterModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(PaneTabsModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabHandModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabWindowModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                new CrateEntry(TabOpenerModule.INSTANCE, StandardJsModuleType.CONSUMER),
                new CrateEntry(TabPickerModule.INSTANCE, StandardJsModuleType.CONSUMER),
                new CrateEntry(PaneEventsModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(TabDragModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(TabTearModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(TabFitModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(PaneKeysModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(PaneMenusModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(PaneMergeModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(TabSourceModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                new CrateEntry(PaneStyles.INSTANCE),
                new CrateEntry(FloatingPaneStyles.INSTANCE));
    }
}
