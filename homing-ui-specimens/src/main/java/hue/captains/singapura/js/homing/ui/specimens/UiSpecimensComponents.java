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
                ComponentEntry.of(this, new EdgeStripSpecimenModule.EdgeStripSpecimen()),
                ComponentEntry.of(this, new DialogSpecimenModule.DialogSpecimen()),
                ComponentEntry.of(this, new ContextMenuSpecimenModule.ContextMenuSpecimen()),
                ComponentEntry.of(this, new ContextMenuStewardSpecimenModule.ContextMenuStewardSpecimen()),
                ComponentEntry.of(this, new FloatingPaneSpecimenModule.FloatingPaneSpecimen()),
                ComponentEntry.of(this, new FloatLayerSpecimenModule.FloatLayerSpecimen()),
                ComponentEntry.of(this, new SplitPaneSpecimenModule.SplitPaneSpecimen()),
                ComponentEntry.of(this, new SplitGridSpecimenModule.SplitGridSpecimen()),
                ComponentEntry.of(this, new SplitGridMirrorSpecimenModule.SplitGridMirrorSpecimen()),
                ComponentEntry.of(this, new SpecimenDocksModule.SpecimenDocks()),
                ComponentEntry.of(this, new TabOpenerSpecimenModule.TabOpenerSpecimen()),
                ComponentEntry.of(this, new AddTabSpecimenModule.AddTabSpecimen()),
                ComponentEntry.of(this, new PaneThumbsSpecimenModule.PaneThumbsSpecimen()),
                ComponentEntry.of(this, new ListMasterWidgetSpecimenModule.ListMasterWidgetSpecimen()),
                ComponentEntry.of(this, new SvgPanZoomSpecimenModule.SvgPanZoomSpecimen()),
                ComponentEntry.of(this, new PanZoomBarSpecimenModule.PanZoomBarSpecimen()),
                ComponentEntry.of(this, new FocusMonitorSpecimenModule.FocusMonitorSpecimen()),
                ComponentEntry.of(this, new StewardMonitorSpecimenModule.StewardMonitorSpecimen()));
    }
}
