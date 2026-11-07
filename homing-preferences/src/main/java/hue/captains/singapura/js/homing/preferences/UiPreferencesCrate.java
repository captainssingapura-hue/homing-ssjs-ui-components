package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.js.CoreJsCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.elements.UiElementsCrate;
import hue.captains.singapura.js.homing.ui.look.UiLookCrate;
import hue.captains.singapura.js.homing.ui.splitgrid.UiSplitGridCrate;

import java.util.List;

/**
 * The preferences component's crate: the view, the field, the four widgets
 * and the styles. A site's {@link PreferencesRegistry} instance is the
 * site's to declare, in the site's crate, since its content is the site's.
 */
public final class UiPreferencesCrate implements Crate, ComponentVehicle {

    public static final UiPreferencesCrate INSTANCE = new UiPreferencesCrate();

    private UiPreferencesCrate() {}

    @Override public String name() { return "homing-preferences"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, CoreJsCrate.INSTANCE, UiLookCrate.INSTANCE, UiElementsCrate.INSTANCE, UiSplitGridCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiPreferencesComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                new CrateEntry(PreferencesViewModule.INSTANCE),
                new CrateEntry(PreferenceFieldModule.INSTANCE),
                new CrateEntry(ChoiceWidgetModule.INSTANCE),
                new CrateEntry(ToggleWidgetModule.INSTANCE),
                new CrateEntry(ScaleWidgetModule.INSTANCE),
                new CrateEntry(SettingRowModule.INSTANCE),
                new CrateEntry(OverviewWidgetModule.INSTANCE),
                new CrateEntry(ListMasterWidgetModule.INSTANCE),
                new CrateEntry(PreferencesStyles.INSTANCE));
    }
}
