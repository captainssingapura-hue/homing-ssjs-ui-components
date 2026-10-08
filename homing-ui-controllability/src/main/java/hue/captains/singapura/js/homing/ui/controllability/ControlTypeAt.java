package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A control type declared at a node of the taxonomy: a branch, for every leaf under it that
 * declares nothing nearer; a leaf, for itself.
 *
 * @param at   the node: a branch or a leaf
 * @param type its control type
 */
public record ControlTypeAt(Taxon at, ControlType type) implements ValueObject {

    public ControlTypeAt {
        Objects.requireNonNull(at, "ControlTypeAt.at");
        Objects.requireNonNull(type, "ControlTypeAt.type");
    }
}
