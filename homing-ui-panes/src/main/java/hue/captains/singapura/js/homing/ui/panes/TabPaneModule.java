package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * {@code TabPane}, one open tab, whole: the tab — the chip that names it on a
 * strip — and the pane, the body that holds its widget, minted once on a
 * branch of its own under its desk's {@link TabRegisterModule.TabRegister}
 * and travelling together from host to host. Its title and icon are its own,
 * and so is closing it; what it is asked where it stands — a press, a menu —
 * goes to the host it is in. RFC 0066 E3, appendix "tab-panes".
 */
public record TabPaneModule() implements DomModule<TabPaneModule> {

    /** The class. */
    public record TabPane() implements BranchComponent<TabPaneModule> {
        @Override public String summary() { return "One open tab, whole: its chip and the pane holding its widget, minted once on a branch of its own under the desk and carried from host to host."; }
    }

    public static final TabPaneModule INSTANCE = new TabPaneModule();

    @Override
    public ImportsFor<TabPaneModule> imports() {
        return ImportsFor.<TabPaneModule>builder()
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_tab_content(),
                        new PaneStyles.mtp_tab_content_hidden()
                ), PaneStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabChipModule.TabChip()), TabChipModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneKeysModule.PaneKeys()), PaneKeysModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabPaneModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabPane()));
    }
}
