package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Reads a control catalogue from its options: the branches are reached through them, each up its
 * lineage to the root, in the order first reached - top-down, so a category comes before the ones
 * beneath it. What the catalogue refuses, reading refuses.
 */
public record ReadControls() implements StatelessFunctionalObject {

    public static final ReadControls INSTANCE = new ReadControls();

    /** The catalogue of these options, its branches reached through them; refused, saying why, when it does not hold together. */
    public ControlCatalogue read(List<? extends ControlOption<?>> options) {
        var branches = new LinkedHashSet<ControlBranch>();
        for (ControlOption<?> o : options) {
            var lineage = new ArrayDeque<ControlBranch>();
            for (ControlBranch b = o.parent(); b != null && !(b instanceof L0_ControlBranch); b = ControlLevels.parentOf(b)) lineage.push(b);
            branches.addAll(lineage);
        }
        return new ControlCatalogue(new ArrayList<>(branches), List.copyOf(options));
    }
}
