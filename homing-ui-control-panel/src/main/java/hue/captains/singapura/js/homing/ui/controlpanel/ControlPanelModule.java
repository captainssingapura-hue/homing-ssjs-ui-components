package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.elements.SliderGroupModule;
import hue.captains.singapura.js.homing.ui.elements.SliderModule;

import java.util.List;

/**
 * The control panel: the controls for a set of options, each made as its means calls for - a slider
 * for a degree, in one group that holds the keys; a toggle for a switch; a button for an action or
 * a question. It drives nothing itself: it says what was set or asked, and shows what it is told.
 */
public record ControlPanelModule() implements DomModule<ControlPanelModule> {

    public static final ControlPanelModule INSTANCE = new ControlPanelModule();

    /** A branch component: {@code new ControlPanel(branch, { options, onSet, onInvoke })}; {@code set}, {@code key}, {@code dispose}. */
    public record ControlPanel() implements BranchComponent<ControlPanelModule> {
        @Override public String summary() { return "The controls for a set of options, each as its means calls for: a slider for a degree, a toggle for a switch, a button for an action or a question."; }
    }

    @Override
    public ImportsFor<ControlPanelModule> imports() {
        return ImportsFor.<ControlPanelModule>builder()
                .add(new ModuleImports<>(List.of(new ControlsModule.Controls()), ControlsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SliderGroupModule.SliderGroupBuilder()), SliderGroupModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SliderModule.SliderBuilder()), SliderModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new ControlPanelStyles.cp_panel(), new ControlPanelStyles.cp_row(), new ControlPanelStyles.cp_note()),
                        ControlPanelStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ControlPanelModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ControlPanel())); }
}
