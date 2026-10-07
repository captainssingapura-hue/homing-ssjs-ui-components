package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.Objects;

/**
 * A leaf of the house's taxonomy seen in action: the leaf, and the class that builds it live - a
 * branch component of this module, made with a sub-branch and {@code { leaf, say }}. One class
 * may show several leaves: the six buttons are one button, each in its own colour.
 *
 * @param leaf       the house's leaf it shows
 * @param constructs the one class that builds it, and the module that exports it
 */
public record Specimen(Component<?> leaf, ModuleImports<?> constructs) {

    public Specimen {
        Objects.requireNonNull(leaf, "Specimen.leaf");
        Objects.requireNonNull(constructs, "Specimen.constructs");
        if (constructs.allImports().size() != 1)
            throw new IllegalArgumentException(leaf.getClass().getSimpleName() + ": a specimen is built by one class, not "
                    + constructs.allImports().size());
    }

    /** The class's name, as the page has it. */
    public String className() { return constructs.allImports().get(0).getClass().getSimpleName(); }
}
