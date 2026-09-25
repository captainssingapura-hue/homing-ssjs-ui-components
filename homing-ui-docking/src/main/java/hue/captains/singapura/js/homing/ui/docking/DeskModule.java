package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.component.keyboard.FocusPartyModule;
import hue.captains.singapura.js.homing.component.keyboard.focusParty;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.floating.FloatLayerModule;
import hue.captains.singapura.js.homing.ui.panes.PaneEventsModule;
import hue.captains.singapura.js.homing.ui.panes.TabRegisterModule;

import java.util.List;

/**
 * {@code Desk}, the whole (RFC 0066 E3, appendix "tab-panes", §3): the
 * register of a desk's tab-panes, the hosts that hold them — docks, the
 * page's, and floats, the desk's own — the float layer the floats lie on, and
 * every move of a tab-pane between them. A tab-pane is opened in the desk's
 * register and owned there for its whole life; its widget rests in the desk's
 * focus branch while no host holds it. A lone multi-tab pane is a desk with
 * one host; the float layer is made only when a float is first wanted.
 */
public record DeskModule() implements DomModule<DeskModule> {

    public static final DeskModule INSTANCE = new DeskModule();

    /** The class. */
    public record Desk() implements Exportable._Class<DeskModule> {}

    @Override
    public ImportsFor<DeskModule> imports() {
        return ImportsFor.<DeskModule>builder()
                .add(new ModuleImports<>(List.of(new TabRegisterModule.TabRegister()), TabRegisterModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloatLayerModule.FloatLayer()), FloatLayerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloaterModule.Floater()), FloaterModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneEventsModule.PaneEvents()), PaneEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new focusParty()), FocusPartyModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DeskModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Desk()));
    }
}
