package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import hue.captains.singapura.js.homing.design.spec.dual.FixedValuesKit;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A tree's JS dual as its module (RFC 0071): the kit's classes it is made with imported, and the other trees' nodes its
 * nodes name, each from its own dual; then each node made by the
 * kit's constructor of its kind - its token, its parent the parent's own object, what else the kind takes - after its
 * parent, every one exported under its name. The same writer for every tree.
 */
public record DualScript() implements StatelessFunctionalObject {

    public static final DualScript INSTANCE = new DualScript();

    public String script(TreeDual dual) {
        var lines = new ArrayList<String>();
        lines.add("// The JS dual of the " + dual.tree().name() + " tree (RFC 0071), generated from Java: the " + dual.nodes().size() + " of its "
                + dual.inTree() + " nodes the registered components' declarations use, and those above them.");
        lines.add("import { " + String.join(", ", dual.tree().kit()) + " } from " + JsLiterals.INSTANCE.string(DualKit.INSTANCE.address()) + ";");
        if (!dual.tree().valuesKit().isEmpty())
            lines.add("import { " + String.join(", ", dual.tree().valuesKit()) + " } from " + JsLiterals.INSTANCE.string(FixedValuesKit.INSTANCE.address()) + ";");
        var refs = new LinkedHashMap<String, DualRef.Ref>();
        for (TreeDual.Node n : dual.nodes()) for (Object o : n.refers()) { DualRef.Ref r = DualRef.INSTANCE.of(o); refs.putIfAbsent(r.imported(), r); }
        for (DualTree<?> other : ReadDual.INSTANCE.trees()) {
            List<String> names = refs.values().stream().filter(r -> r.tree().equals(other)).map(r -> r.name() + " as " + r.imported()).toList();
            if (!names.isEmpty()) lines.add("import { " + String.join(", ", names) + " } from " + JsLiterals.INSTANCE.string(other.address()) + ";");
        }
        for (TreeDual.Node n : dual.nodes()) lines.add("const " + n.name() + " = " + n.construction() + ";");
        var exported = new ArrayList<String>();
        for (TreeDual.Node n : dual.nodes()) exported.add(n.name());
        for (TreeDual.Node n : dual.nodes()) for (TreeDual.Companion c : n.companions()) { lines.add(c.declaration()); exported.add(c.name()); }
        lines.add("export { " + String.join(", ", exported) + " };");
        return String.join("\n", lines) + "\n";
    }
}
