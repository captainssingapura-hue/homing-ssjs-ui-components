package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.js.CoreJsCrate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.icons.UiIconsCrate;

import java.util.List;

/**
 * The elements' crate: the components and their styles, on the runtime
 * ({@code ServerCrate} — the component base's crate, under its old name
 * until the old structure retires) and the design substrate's targets.
 */
public final class UiElementsCrate implements Crate, ComponentVehicle {

    public static final UiElementsCrate INSTANCE = new UiElementsCrate();

    private UiElementsCrate() {}

    @Override public String name() { return "homing-ui-elements"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, CoreJsCrate.INSTANCE, DesignCrate.INSTANCE, UiIconsCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiElementsComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(CastingsModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(StatesAndEventsModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(HouseLookSheet.INSTANCE),
                CrateEntry.of(LookManifestModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(LookModule.INSTANCE),
                CrateEntry.of(SimpleComponentModule.INSTANCE),
                CrateEntry.of(ButtonBehaviorModule.INSTANCE),
                CrateEntry.of(ButtonModule.INSTANCE),
                CrateEntry.of(PlainButtonModule.INSTANCE),
                CrateEntry.of(PrimaryButtonModule.INSTANCE),
                CrateEntry.of(SecondaryButtonModule.INSTANCE),
                CrateEntry.of(DangerButtonModule.INSTANCE),
                CrateEntry.of(WarningButtonModule.INSTANCE),
                CrateEntry.of(SuccessButtonModule.INSTANCE),
                CrateEntry.of(ToggleButtonBehaviorModule.INSTANCE),
                CrateEntry.of(ToggleButtonModule.INSTANCE),
                CrateEntry.of(CloseButtonModule.INSTANCE),
                CrateEntry.of(IconButtonModule.INSTANCE),
                CrateEntry.of(TextBehaviorModule.INSTANCE),
                CrateEntry.of(TextModule.INSTANCE),
                CrateEntry.of(HeadingModule.INSTANCE),
                CrateEntry.of(KickerModule.INSTANCE),
                CrateEntry.of(CaptionModule.INSTANCE),
                CrateEntry.of(LedeModule.INSTANCE),
                CrateEntry.of(LabelModule.INSTANCE),
                CrateEntry.of(ReadoutModule.INSTANCE),
                CrateEntry.of(BadgeModule.INSTANCE),
                CrateEntry.of(PillModule.INSTANCE),
                CrateEntry.of(WordmarkModule.INSTANCE),
                // the ways somewhere - a link, a crumb - and the veil behind a dialog
                CrateEntry.of(LinkBehaviorModule.INSTANCE),
                CrateEntry.of(LinkModule.INSTANCE),
                CrateEntry.of(CrumbModule.INSTANCE),
                CrateEntry.of(ScrimBehaviorModule.INSTANCE),
                CrateEntry.of(ScrimModule.INSTANCE),
                // the marks between things: a line between rows, a sign between steps
                CrateEntry.of(MarkBehaviorModule.INSTANCE),
                CrateEntry.of(SeparatorModule.INSTANCE),
                CrateEntry.of(DelimiterModule.INSTANCE),
                CrateEntry.of(SummaryCardModule.INSTANCE),
                CrateEntry.of(SliderScaleModule.INSTANCE),
                CrateEntry.of(SliderModule.INSTANCE),
                CrateEntry.of(SliderGroupModule.INSTANCE),
                CrateEntry.of(PanelModule.INSTANCE),
                CrateEntry.of(EdgeStripModule.INSTANCE),
                CrateEntry.of(GroundModule.INSTANCE),
                CrateEntry.of(SiteBarModule.INSTANCE),
                CrateEntry.of(BrandModule.INSTANCE),
                CrateEntry.of(TrailModule.INSTANCE),
                CrateEntry.of(ElementStyles.INSTANCE));
    }
}
