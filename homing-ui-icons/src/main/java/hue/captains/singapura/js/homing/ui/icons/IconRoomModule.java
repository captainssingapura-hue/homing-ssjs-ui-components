package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * {@code IconRoom} (RFC 0067, D52): the room an owner keeps for its icons - one shown at a time, the one its owner
 * says, each made once on the owner's branch and mounted in place of the one before. Which is shown is the owner's
 * state; the room only mounts. Not a component, so it implements nothing: it is its importers' code, and the icons
 * it mounts are theirs - the slider's five on its knob, any icon leading a menu row.
 */
public record IconRoomModule() implements DomModule<IconRoomModule> {

    public static final IconRoomModule INSTANCE = new IconRoomModule();

    /** The class. */
    public record IconRoom() implements Exportable._Class<IconRoomModule> {}

    @Override
    public ImportsFor<IconRoomModule> imports() {
        return ImportsFor.<IconRoomModule>builder()
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<IconRoomModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new IconRoom()));
    }
}
