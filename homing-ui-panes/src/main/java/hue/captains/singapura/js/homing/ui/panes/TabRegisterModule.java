package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * {@code TabRegister}, a desk's register of its tab-panes: it opens each
 * {@link TabPaneModule.TabPane} on a branch of its own named fresh, keeps them
 * by id — any string, unique per register, never a name on the party — and
 * forgets them when they close. RFC 0066 E3, appendix "tab-panes".
 *
 * <p>Served in the DOM lane although it touches no element itself: it
 * imports the tab-pane, and a pure module importing a DOM module would load
 * a second, theme-less copy of it.</p>
 */
public record TabRegisterModule() implements DomModule<TabRegisterModule> {

    public static final TabRegisterModule INSTANCE = new TabRegisterModule();

    /** The class. */
    public record TabRegister() implements Exportable._Class<TabRegisterModule> {}

    @Override
    public ImportsFor<TabRegisterModule> imports() {
        return ImportsFor.<TabRegisterModule>builder()
                .add(new ModuleImports<>(List.of(new TabPaneModule.TabPane()), TabPaneModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabRegisterModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabRegister()));
    }
}
