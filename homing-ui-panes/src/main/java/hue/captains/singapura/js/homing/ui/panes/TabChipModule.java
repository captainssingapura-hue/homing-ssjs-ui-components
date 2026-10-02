package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The chip that names a tab on a strip — the icon's slot, the label, the
 * within mark and the close cross — minted on the branch it is handed and
 * wired to the handlers it is given. Static helpers, as {@link PaneTabsModule}
 * is. The {@link TabStripModule.TabStrip} mints its chips through it, and so
 * does a {@link TabPaneModule.TabPane}, which mints its one chip once and
 * carries it from strip to strip: one chip, whoever holds it.
 *
 * <p>Split out of the strip when the tab-pane came (RFC 0066 E3, appendix
 * "tab-panes"): a chip that travels cannot be the strip's to make.</p>
 */
public record TabChipModule() implements DomModule<TabChipModule> {

    public static final TabChipModule INSTANCE = new TabChipModule();

    /** The helpers. */
    public record TabChip() implements Exportable._Class<TabChipModule> {}

    @Override
    public ImportsFor<TabChipModule> imports() {
        return ImportsFor.<TabChipModule>builder()
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_chip(),
                        new PaneStyles.mtp_chip_seated(),
                        new PaneStyles.mtp_chip_icon(),
                        new PaneStyles.mtp_chip_icon_on(),
                        new PaneStyles.mtp_chip_label(),
                        new PaneStyles.mtp_chip_mark(),
                        new PaneStyles.mtp_chip_close()
                ), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabChipModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabChip()));
    }
}
