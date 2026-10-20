package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The panel: a named region that fills what holds it - the workspace's unit of work - its head, the panel's bar,
 * naming it and holding what acts on it; its body, a room of its own (RFC 0067, D57), what is mounted in it. A branch
 * component on the style manager (RFC 0071): it realizes its head, and places in its body and at its head's end what
 * it is handed. It measures nothing: what holds it decides how big it is.
 *
 * <p>It takes no keys: a panel is furniture. Its old watch read the attribute a component wrote to say where the keys
 * were, which nothing writes now (RFC 0071, D16); what it is to tell its app of the keys inside it waits for a
 * component's own events.</p>
 */
public record PanelModule() implements DomModule<PanelModule>, ImplFor<HouseContainers.Panel> {

    /** A branch component: {@code new Panel(branch, new FixedPanel(), place?)}; {@code root}, {@code title}, {@code end(c)}, {@code mount(c)}, {@code elevation}, {@code current}. */
    public record Panel() implements BranchComponent<PanelModule> {
        @Override public String summary() { return "A named region that fills what holds it: its head naming it and holding what acts on it, its body what is mounted in it."; }
    }


    public static final PanelModule INSTANCE = new PanelModule();

    @Override
    public ImportsFor<PanelModule> imports() {
        return ImportsFor.<PanelModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PanelBarModule.PanelBar()), PanelBarModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_panel(),
                        new ElementStyles.el_panel_body(),
                        new ElementStyles.el_panel_body_air()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PanelModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Panel()));
    }
}
