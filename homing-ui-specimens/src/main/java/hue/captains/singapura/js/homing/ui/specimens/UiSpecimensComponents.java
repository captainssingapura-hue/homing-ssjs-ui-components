package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: the specimens, each a house component in action. */
public record UiSpecimensComponents() implements C0_Components<UiSpecimensComponents> {

    public static final UiSpecimensComponents INSTANCE = new UiSpecimensComponents();

    @Override public String name() { return "Specimens"; }
    @Override public String summary() { return "The house's components in action: each built live, its behaviour exercised and said, and the axes its leaf varies along set by number."; }

    @Override public List<ComponentEntry<UiSpecimensComponents>> leaves() {
        return List.of(
                ComponentEntry.of(this, new ButtonSpecimenModule.ButtonSpecimen()),
                ComponentEntry.of(this, new IconSpecimenModule.IconSpecimen()),
                ComponentEntry.of(this, new SummaryCardSpecimenModule.SummaryCardSpecimen()),
                ComponentEntry.of(this, new SliderSpecimenModule.SliderSpecimen()),
                ComponentEntry.of(this, new SliderGroupSpecimenModule.SliderGroupSpecimen()),
                ComponentEntry.of(this, new PanelSpecimenModule.PanelSpecimen()),
                ComponentEntry.of(this, new EdgeStripSpecimenModule.EdgeStripSpecimen()));
    }
}
