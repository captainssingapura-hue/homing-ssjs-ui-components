package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The panel's bar: heads a panel - its title, the house's kicker cast as its title, and at its end what acts on the
 * panel, the caller's. A branch component: it realizes its title, and places at its end, a room of its own (RFC 0067),
 * what it is handed; its look is the house's. A part once realized inline by the panel (owner, 20 Oct: every part a
 * component of its own).
 */
public record PanelBarModule() implements DomModule<PanelBarModule>, ImplFor<HouseContainers.PanelBar> {

    /** A branch component: {@code new PanelBar(branch, new FixedPanelBar(), place?)}; {@code root}, {@code title}, {@code end(c)}. */
    public record PanelBar() implements BranchComponent<PanelBarModule> {
        @Override public String summary() { return "Heads a panel: its title, and at its end what acts on the panel."; }
    }

    public static final PanelBarModule INSTANCE = new PanelBarModule();

    @Override
    public ImportsFor<PanelBarModule> imports() {
        return ImportsFor.<PanelBarModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_panel_head(),
                        new ElementStyles.el_panel_title(),
                        new ElementStyles.el_panel_slot()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PanelBarModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PanelBar()));
    }
}
