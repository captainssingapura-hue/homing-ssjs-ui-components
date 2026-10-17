package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.ui.splitgrid.SplitGridModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.component.WidgetSlotModule;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The preferences view: a master slot on the left holding the tree widget,
 * a detail slot on the right holding one widget per node, laid out by a split
 * grid, fixed - the line between them is the grid's, and the user may move it - every widget
 * loaded the first time it is needed and kept until the view is disposed.
 *
 * <p>{@code new PreferencesView(branch, registry)}: root, select, ready, key, dispose. A widget,
 * shown by its holder in a slot. Its params are a site's {@link PreferencesRegistry}; the view reads them
 * and holds what it constructs. It knows the master by the surface a tree
 * widget offers it — {@code onSelect(fn)}, {@code select(path)} — and knows
 * a detail widget by nothing at all: it constructs it with the registry's
 * params and shows its root. What a widget writes, it writes through the
 * steward; the view neither carries nor forwards a value.</p>
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 4,
        reason = "It makes the box its grid fills, and gives the grid's cells their room with classes of its own: it waits for a region's room to be the layout's.")
public record PreferencesViewModule() implements Widget<Widget._None, PreferencesViewModule>, ImplFor<HousePreferences.PreferencesView> {

    /** The class. */
    public record PreferencesView() implements Widget._Class<Widget._None, PreferencesViewModule>, NeedKeyboard {
        @Override public String summary() { return "A master slot on the left, a detail slot on the right, over a preferences registry."; }
        /** The view holds the keys for its two slots: the master's while the focus is in the master pane, the shown detail widget's in the detail pane. */
        @Override public List<KeyBinding> keys() {
            return ListMasterWidgetModule.ListMasterWidget.KEYS.stream().map(b -> new KeyBinding(b.key(), b.modifiers(), "to the master: " + b.meaning())).toList();
        }
    }

    public static final PreferencesViewModule INSTANCE = new PreferencesViewModule();

    @Override public String title() { return "Preferences"; }

    @Override
    public ImportsFor<PreferencesViewModule> imports() {
        return ImportsFor.<PreferencesViewModule>builder()
                .add(new ModuleImports<>(List.of(new WidgetSlotModule.WidgetSlot()), WidgetSlotModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SplitGridModule.SplitGrid()), SplitGridModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_root(),
                        new PreferencesStyles.pv_master(),
                        new PreferencesStyles.pv_detail()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PreferencesViewModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PreferencesView()));
    }
}
