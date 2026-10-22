package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.IconButtonModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code TabStrip}, the bar over a multi-tab pane, ported a step at a time (RFC 0071): a chip for each tab it holds -
 * its own, made by it, pressed to be the one in use and slid along it as a browser's tab is, the chips it passes stepping
 * aside, eased - and its one plus, a regular button, the
 * house's icon button. Its chips' states are its own (owner, 22 Oct): its owner hears which tab is in use
 * ({@code ActiveTab}), the order of its tabs once a drag has changed it ({@code TabOrder}), and its plus's presses,
 * passed on as its {@code TabAddControl}, handling none. Its count and its landing come as they are ported.
 */
public record TabStripModule() implements DomModule<TabStripModule>, ImplFor<HouseContainers.TabStrip> {

    /** A composed component: {@code new TabStrip(branch, new FixedTabStrip(new FixedIconButton(new FixedAddIcon())), place?)}; {@code hold(tabs)}; {@code onChange(StateActiveTab | StateTabOrder | StateTabAddControl, listener)}; {@code active}, {@code order}, {@code adds}, {@code current}, {@code dispose}. */
    public record TabStrip() implements BranchComponent<TabStripModule> {
        @Override public String summary() { return "The bar over a multi-tab pane: its tabs' chips, pressed and dragged along it, and its one plus, whose presses it passes on."; }
    }

    public static final TabStripModule INSTANCE = new TabStripModule();

    @Override
    public ImportsFor<TabStripModule> imports() {
        return ImportsFor.<TabStripModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabStripBehaviorModule.TabStripBehavior()), TabStripBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabModule.Tab()), TabModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabDragModule.TabDrag()), TabDragModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconButtonModule.IconButton()), IconButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneStyles.mtp_strip(), new PaneStyles.mtp_rail_add(), new PaneStyles.mtp_strip_loose(),
                        new PaneStyles.mtp_chip_dragging(), new PaneStyles.mtp_chip_shifted()), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabStripModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabStrip())); }
}
