package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;
import hue.captains.singapura.js.homing.ui.elements.SliderGroupModule;

import java.util.List;

/**
 * The control panel: the controls for a set of options, each made as its means calls for - a slider
 * for a degree, in one group that holds the keys; a toggle for a switch; a button for an action or
 * a question. It drives nothing itself: it says what was set or asked, and shows what it is told.
 *
 * <p>A widget's body, not a component: the taxonomy has no leaf for it, so it is anchored nowhere
 * and catalogued nowhere - the components it is made of are. Where a component ends and what is
 * built of components begins is a question of its own, left open.</p>
 */
public record ControlPanelModule() implements DomModule<ControlPanelModule> {

    public static final ControlPanelModule INSTANCE = new ControlPanelModule();

    /** The class: {@code new ControlPanel(branch, { options, onSet, onInvoke })}; {@code set}, {@code key}, {@code dispose}. */
    public record ControlPanel() implements Exportable._Class<ControlPanelModule> {}

    @Override
    public ImportsFor<ControlPanelModule> imports() {
        return ImportsFor.<ControlPanelModule>builder()
                .add(new ModuleImports<>(List.of(new ControlsModule.Controls()), ControlsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SliderGroupModule.SliderGroup()), SliderGroupModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ControlPanelStyles.cp_panel(), new ControlPanelStyles.cp_row(), new ControlPanelStyles.cp_note()),
                        ControlPanelStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ControlPanelModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ControlPanel())); }
}
