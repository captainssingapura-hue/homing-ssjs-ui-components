package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The edge strip and its builder: a strip of controls that lies OVER the foot
 * of its host, shown when the hand comes to that edge. The host keeps all its
 * room but a thin lip at its foot, the strip's own; nothing the host holds is
 * covered until the strip is asked for. It is shown while the hand is on the
 * lip or on it, while the keys are in it, and while the caller holds it - for
 * something on it a person must know.
 *
 * <p>It takes no keys: what is in it is the caller's, as a panel's slot is.</p>
 */
public record EdgeStripModule() implements DomModule<EdgeStripModule> {

    /** A branch component, made through its builder: a lip at its host's foot and a strip over it, which the caller fills. */
    public record EdgeStrip() implements BranchComponent<EdgeStripModule> {
        @Override public String summary() { return "A strip of controls over the foot of its host, shown when the hand comes to that edge; a thin lip is all it takes of the host's room."; }
    }

    /** The builder: {@code new EdgeStripBuilder()}; label and host set progressively; {@code build(branch)} on a sub-branch of the caller's. */
    public record EdgeStripBuilder() implements Exportable._Constant<EdgeStripModule> {}

    public static final EdgeStripModule INSTANCE = new EdgeStripModule();

    @Override
    public ImportsFor<EdgeStripModule> imports() {
        return ImportsFor.<EdgeStripModule>builder()
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_strip(),
                        new ElementStyles.el_strip_hidden(),
                        new ElementStyles.el_strip_lip()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<EdgeStripModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new EdgeStrip(), new EdgeStripBuilder()));
    }
}
