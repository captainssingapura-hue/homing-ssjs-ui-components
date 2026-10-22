package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.IconButtonModule;
import hue.captains.singapura.js.homing.ui.elements.MarkBehaviorModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code TabStrip}, the bar over a multi-tab pane, ported a step at a time (RFC 0071): its one plus for now - a
 * regular button, the house's icon button - whose presses it passes on as its own {@code TabAddControl}, handling
 * none (owner, 22 Oct). Its chips, its count and the drag along its row come as they are ported.
 */
public record TabStripModule() implements DomModule<TabStripModule>, ImplFor<HouseContainers.TabStrip> {

    /** A composed component: {@code new TabStrip(branch, new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), place?)}; {@code onChange(StateTabAddControl, listener)}; {@code adds}, {@code current}, {@code dispose}. */
    public record TabStrip() implements BranchComponent<TabStripModule> {
        @Override public String summary() { return "The bar over a multi-tab pane: its one plus, whose presses it passes on as the adds asked of it."; }
    }

    public static final TabStripModule INSTANCE = new TabStripModule();

    @Override
    public ImportsFor<TabStripModule> imports() {
        return ImportsFor.<TabStripModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconButtonModule.IconButton()), IconButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneStyles.mtp_strip(), new PaneStyles.mtp_rail_add()), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabStripModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabStrip())); }
}
