package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The tab: one tab on a bar of tabs - its name, a room of its own, and the mark of where the keys are, the within icon -
 * a composed component on its own {@code <div role="tab">}, made on the branch its bar hands it. Ported (RFC 0071): the
 * bar makes one for each tab it holds and hears it held - the hand down on it, and up again; which tab is in use and
 * where each stands are the bar's, which sets the tab's states (owner, 22 Oct). Its cross comes with its own step.
 */
public record TabModule() implements DomModule<TabModule>, ImplFor<HouseControls.Tab> {

    /** {@code new Tab(branch, new FixedTab(null), place)}; {@code onChange(StateHeld, listener)}; {@code title}, {@code selected}, {@code keys}, {@code dragging}, {@code held}, {@code size}, {@code aspect}, {@code dispose}. */
    public record Tab() implements BranchComponent<TabModule> {
        @Override public String summary() { return "One tab on a bar of tabs: its name and the mark of where the keys are; in use, in the hand, held."; }
    }

    public static final TabModule INSTANCE = new TabModule();

    @Override
    public ImportsFor<TabModule> imports() {
        return ImportsFor.<TabModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabBehaviorModule.TabBehavior()), TabBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_chip(),
                        new PaneStyles.mtp_chip_label(),
                        new PaneStyles.mtp_chip_mark(),
                        new PaneStyles.mtp_chip_mark_on()
                ), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Tab()));
    }
}
