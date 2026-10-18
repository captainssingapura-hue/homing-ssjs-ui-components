package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;

/**
 * A tree's JS dual as its module (RFC 0071): each node a frozen object - its token, its parent the parent's own object,
 * what it carries - declared after its parent, every one exported under its name. The same writer for every tree.
 */
public record DualScript() implements StatelessFunctionalObject {

    public static final DualScript INSTANCE = new DualScript();

    public String script(TreeDual dual) {
        var js = JsLiterals.INSTANCE;
        var lines = new ArrayList<String>();
        lines.add("// The JS dual of the " + dual.tree().name() + " tree (RFC 0071), generated from Java: the " + dual.nodes().size() + " of its "
                + dual.inTree() + " nodes the registered components' declarations use, and those above them.");
        for (TreeDual.Node n : dual.nodes()) {
            var fields = new ArrayList<String>();
            fields.add("token: " + js.string(n.token()));
            fields.add("parent: " + n.parent().orElse("null"));
            n.carries().forEach((k, v) -> fields.add(k + ": " + v));
            lines.add("const " + n.name() + " = Object.freeze({ " + String.join(", ", fields) + " });");
        }
        lines.add("export { " + String.join(", ", dual.nodes().stream().map(TreeDual.Node::name).toList()) + " };");
        return String.join("\n", lines) + "\n";
    }
}
