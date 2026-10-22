package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ui.look.IconsModule;
import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.CloseButtonModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The tab: one tab of a strip - its icon's slot, its name, the mark of where the keys are and its cross - a simple
 * component on its own {@code <div role="tab">}, made on the branch its owner hands it. A {@link TabPaneModule.TabPane}
 * makes its one tab once and carries it from strip to strip: one tab, whoever holds it. Its holders set its states -
 * in use, where the keys are, in the hand - and hear its Press and its Dismiss.
 *
 * <p>Split out of the strip when the tab-pane came (RFC 0066 E3, appendix "tab-panes"): a tab that travels cannot be
 * the strip's to make. Static helpers on the chip's element until it was made the component its leaf asks for, by the
 * owner, 14 Oct 2026.</p>
 */
public record TabModule() implements DomModule<TabModule>, ImplFor<HouseControls.Tab> {

    /** {@code new Tab(branch, { title, icon, pinned, closable }, role)}; {@code el}, its states and degrees, {@code crosses}, {@code menu}; its setters. */
    public record Tab() implements BranchComponent<TabModule> {
        @Override public String summary() { return "One tab of a strip: its icon, its name, the mark of where the keys are and its cross; in use, marked, in the hand."; }
    }

    public static final TabModule INSTANCE = new TabModule();

    @Override
    public ImportsFor<TabModule> imports() {
        return ImportsFor.<TabModule>builder()
                .add(new ModuleImports<>(List.of(new IconsModule.ICON()), IconsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabBehaviorModule.TabBehavior()), TabBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CloseButtonModule.CloseButton()), CloseButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_chip(),
                        new PaneStyles.mtp_chip_icon(),
                        new PaneStyles.mtp_chip_icon_on(),
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
