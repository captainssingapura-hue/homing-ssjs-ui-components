package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.elements.CastingsModule;
import hue.captains.singapura.js.homing.ui.elements.LookModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.WidgetSlotModule;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.preferences.PreferencesViewModule;
import hue.captains.singapura.js.homing.ui.dialog.DialogModule;

import java.util.List;

/**
 * The preferences button on the bar. Pressed, it opens the dialog with the
 * preferences view inside: the site's tree on the left, the chosen node's
 * widget on the right. The site's registry is named in the page's chrome
 * data and imported on the first open — its module, and every widget's,
 * arrive only when they are needed.
 *
 * <p>The dialog is modal, Escape closes it, and Done is a plain action so
 * that Enter stays the tree's. Nothing is written here: every widget
 * writes through the steward, and the page follows the store as it always
 * has, so a theme picked in the dialog is worn behind it as it is picked.</p>
 */
public record PreferencesButtonModule() implements DomModule<PreferencesButtonModule>, ImplFor<HouseControls.PreferencesButton> {

    /** The class. */
    public record PreferencesButton() implements BranchComponent<PreferencesButtonModule> {
        @Override public String summary() { return "The button on the bar, and the dialog it opens."; }
    }

    public static final PreferencesButtonModule INSTANCE = new PreferencesButtonModule();

    @Override
    public ImportsFor<PreferencesButtonModule> imports() {
        return ImportsFor.<PreferencesButtonModule>builder()
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new DialogModule.Dialog()), DialogModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferencesViewModule.PreferencesView()), PreferencesViewModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new WidgetSlotModule.WidgetSlot()), WidgetSlotModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new MpaStyles.mpa_prefs(),
                        new MpaStyles.mpa_prefs_btn()
                ), MpaStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PreferencesButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PreferencesButton()));
    }
}
