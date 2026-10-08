package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForBranch;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import java.util.List;

/**
 * The button's base: what every button leaf is - an element dressed as a button, labelled, at an
 * extent and a size, on or off. Each leaf's own module extends it and binds its look alone: the
 * plain button, the primary, the secondary, the danger, the warning and the success. Never made on
 * its own, and imported only by the modules of the leaves under the button branch.
 */
public record ButtonModule() implements DomModule<ButtonModule>, ImplForBranch<HouseBranches.Button> {

    /** The base class: {@code class XButton extends Button}, its constructor handed the leaf's look. */
    public record Button() implements Exportable._Class<ButtonModule> {}

    public static final ButtonModule INSTANCE = new ButtonModule();

    @Override
    public ImportsFor<ButtonModule> imports() {
        return ImportsFor.<ButtonModule>builder()
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_button(),
                        new ElementStyles.el_button_on(),
                        new ElementStyles.el_button_off()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Button()));
    }
}
