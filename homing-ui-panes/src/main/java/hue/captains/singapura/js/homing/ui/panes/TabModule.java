package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.CloseButtonModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The tab: one tab on a bar of tabs - its name, a room of its own, the mark of where the keys are, the within icon, and
 * its cross, the house's close button, unless its tab cannot be closed - a composed component on its own
 * {@code <div role="tab">}, made on the branch its bar hands it. Ported (RFC 0071): the bar makes one for each tab it
 * holds and hears it held - the hand down on it, and up again - and its close asked, its cross's clicks passed on as its
 * own {@code ChipCloseControl}; which tab is in use, where each stands and what a close is are the bar's (owner, 22 Oct).
 */
public record TabModule() implements DomModule<TabModule>, ImplFor<HouseControls.Tab> {

    /** {@code new Tab(branch, new FixedTab(new FixedCloseButton() | null), place)}; {@code onChange(StateHeld | StateChipCloseControl, listener)}; {@code title}, {@code closable}, {@code closes}, {@code selected}, {@code keys}, {@code dragging}, {@code held}, {@code size}, {@code aspect}, {@code dispose}. */
    public record Tab() implements BranchComponent<TabModule> {
        @Override public String summary() { return "One tab on a bar of tabs: its name, the mark of where the keys are and its cross; in use, in the hand, held, its close asked."; }
    }

    public static final TabModule INSTANCE = new TabModule();

    @Override
    public ImportsFor<TabModule> imports() {
        return ImportsFor.<TabModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabBehaviorModule.TabBehavior()), TabBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CloseButtonModule.CloseButton()), CloseButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_chip(),
                        new PaneStyles.mtp_chip_label(),
                        new PaneStyles.mtp_chip_mark(),
                        new PaneStyles.mtp_chip_mark_on(),
                        new PaneStyles.mtp_chip_close()
                ), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Tab()));
    }
}
