package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Components classified by control type: the types declared at nodes of a taxonomy, and the one
 * every leaf takes when nothing on its lineage declares one. A leaf's type is the one declared
 * nearest it - itself, then its branch, up to the root - as its axes are inherited, so one
 * declaration on a branch classifies every leaf beneath it. Refused, every problem named: a node
 * declared twice; two types of one name.
 *
 * @param declared  the types declared, each at its node
 * @param otherwise the type of a leaf with none declared on its lineage
 */
public record ControlClassification(List<ControlTypeAt> declared, ControlType otherwise) implements ValueObject {

    public ControlClassification {
        declared = List.copyOf(declared);
        Objects.requireNonNull(otherwise, "ControlClassification.otherwise");
        var problems = new ArrayList<String>();
        var nodes = new HashSet<Taxon>();
        var byName = new HashMap<String, ControlType>();
        byName.put(otherwise.name(), otherwise);
        for (ControlTypeAt d : declared) {
            if (!nodes.add(d.at())) problems.add(ControlCatalogue.qualified(d.at()) + " is declared twice");
            ControlType was = byName.putIfAbsent(d.type().name(), d.type());
            if (was != null && !was.equals(d.type())) problems.add("two control types named '" + d.type().name() + "'");
        }
        if (!problems.isEmpty()) throw new RefusedControls(problems);
    }

    /** {@code controlType = findControlType(component)}: the type declared nearest the node, up its lineage in the taxonomy given; otherwise the default. */
    public ControlType findControlType(Taxonomy taxonomy, Taxon node) {
        for (Taxon n = node; n != null; n = taxonomy.parent(n))
            for (ControlTypeAt d : declared) if (d.at().equals(n)) return d.type();
        return otherwise;
    }

    /** Every type it names, the default first, each once. */
    public List<ControlType> types() {
        var out = new ArrayList<ControlType>();
        out.add(otherwise);
        for (ControlTypeAt d : declared) if (!out.contains(d.type())) out.add(d.type());
        return List.copyOf(out);
    }
}
