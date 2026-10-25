package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The floating pane: a pane over the page - its head, which names it and moves it, and its body, a room of its own where
 * its caller mounts what it shows - ported into the panes (RFC 0071, 23 Oct), beside the multi-tab pane and the chip its
 * head shares with it: top-level with its own binding, or a part its owner places. Its place, its measure and its place
 * on the stack are its own, set by its caller and by the hand; its close is passed on as its {@code FloatCloseControl}.
 */
public record FloatingPaneModule() implements DomModule<FloatingPaneModule>, ImplFor<HouseContainers.FloatingPane> {

    /** {@code new FloatingPane(branch, new FixedFloatingPane(head?), place?)}; {@code at}, {@code size}, {@code z}, {@code bounds}, {@code mount}, {@code unmount}; {@code onChange(StateFloatCloseControl | StateDragging | ..., listener)}; {@code title}, {@code current}, {@code dispose}. */
    public record FloatingPane() implements BranchComponent<FloatingPaneModule> {
        @Override public String summary() { return "A pane over the page: its head, which names it and moves it, and its body, what it shows mounted by its caller."; }
    }

    public static final FloatingPaneModule INSTANCE = new FloatingPaneModule();

    @Override
    public ImportsFor<FloatingPaneModule> imports() {
        return ImportsFor.<FloatingPaneModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloatingPaneBehaviorModule.FloatingPaneBehavior()), FloatingPaneBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TitleBarModule.TitleBar()), TitleBarModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneStyles.fp_frame(), new PaneStyles.fp_body(),
                        new PaneStyles.fp_size(),new PaneStyles.fp_size_n(), new PaneStyles.fp_size_s(), new PaneStyles.fp_size_e(), new PaneStyles.fp_size_w(), new PaneStyles.fp_size_ne(), new PaneStyles.fp_size_nw(), new PaneStyles.fp_size_se(), new PaneStyles.fp_size_sw()), PaneStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<FloatingPaneModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new FloatingPane())); }
}
