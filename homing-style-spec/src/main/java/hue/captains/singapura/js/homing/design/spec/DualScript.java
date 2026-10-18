package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;

/**
 * A tree's JS dual as its module (RFC 0071): the kit's classes it is made with imported, then each node made by the
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
        for (TreeDual.Node n : dual.nodes()) lines.add("const " + n.name() + " = " + n.construction() + ";");
        lines.add("export { " + String.join(", ", dual.nodes().stream().map(TreeDual.Node::name).toList()) + " };");
        return String.join("\n", lines) + "\n";
    }
}
