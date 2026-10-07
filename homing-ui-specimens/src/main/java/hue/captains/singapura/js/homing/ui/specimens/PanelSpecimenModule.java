package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.elements.PanelModule;

import java.util.List;

/** {@code PanelSpecimen}: the house's panel in action - filling its box, marked current, lifted and sunk from its head. */
public record PanelSpecimenModule() implements DomModule<PanelSpecimenModule> {

    public static final PanelSpecimenModule INSTANCE = new PanelSpecimenModule();

    public record PanelSpecimen() implements BranchComponent<PanelSpecimenModule> {
        @Override public String summary() { return "The house's panel in action: one concern, named, filling the box it is put in, with buttons in its head that mark it current and lift it off its plane or sink it."; }
    }

    @Override
    public ImportsFor<PanelSpecimenModule> imports() {
        return ImportsFor.<PanelSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new PanelModule.PanelBuilder()), PanelModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_text()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<PanelSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new PanelSpecimen())); }
}
