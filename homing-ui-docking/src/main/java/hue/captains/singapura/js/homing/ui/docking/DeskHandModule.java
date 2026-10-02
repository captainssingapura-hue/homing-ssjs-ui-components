package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.panes.TabTearModule;

import java.util.List;

/**
 * {@code DeskHand}, the hand on a chip across a desk: one gesture from the
 * press to the release, wherever the chip goes - along a dock's rail, torn off
 * into a float of its own, captured onto a dock's rail again - as a naked
 * chip goes, {@code TabTear} deciding. The desk makes one and hands it to
 * every host it holds; a press on a chip is then the hand's. The pointer is
 * captured on the desk's own box, which stays put while the chip changes
 * hands. RFC 0066 E3, appendix "tab-panes".
 */
public record DeskHandModule() implements DomModule<DeskHandModule> {

    public static final DeskHandModule INSTANCE = new DeskHandModule();

    /** The class: {@code new DeskHand(desk, { tear })}. */
    public record DeskHand() implements Exportable._Class<DeskHandModule> {}

    @Override
    public ImportsFor<DeskHandModule> imports() {
        return ImportsFor.<DeskHandModule>builder()
                .add(new ModuleImports<>(List.of(new TabTearModule.TabTear()), TabTearModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DeskHandModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new DeskHand()));
    }
}
