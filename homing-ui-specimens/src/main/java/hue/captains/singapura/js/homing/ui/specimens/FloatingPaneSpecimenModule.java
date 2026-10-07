package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.floating.FloatingPaneModule;

import java.util.List;

/** {@code FloatingPaneSpecimen}: the house's floating pane in action - moved, sized, clamped to its box, ringed, closed by its holder. */
public record FloatingPaneSpecimenModule() implements DomModule<FloatingPaneSpecimenModule> {

    public static final FloatingPaneSpecimenModule INSTANCE = new FloatingPaneSpecimenModule();

    public record FloatingPaneSpecimen() implements BranchComponent<FloatingPaneSpecimenModule> {
        @Override public String summary() { return "The house's floating pane in action: a window over its box, moved by its head and sized by its grip, clamped to the box, ringed as the active one, and closed by its holder when its cross asks."; }
    }

    @Override
    public ImportsFor<FloatingPaneSpecimenModule> imports() {
        return ImportsFor.<FloatingPaneSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new FloatingPaneModule.FloatingPane()), FloatingPaneModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_row(),
                        new SpecimenStyles.sp_text()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<FloatingPaneSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new FloatingPaneSpecimen())); }
}
