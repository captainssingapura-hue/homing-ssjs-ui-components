package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.EventNode;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.component.taxonomy.StateNode;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.ValueObject;

/**
 * Where a node is in the page's JS (RFC 0071): a pure function of the node alone - its Java type says which tree's dual
 * it is in, its token its name there. A header's imports are made from it and from nothing else: they need know nothing
 * of a dual but that it exists.
 */
public record DualRef() implements StatelessFunctionalObject {

    public static final DualRef INSTANCE = new DualRef();

    /**
     * A node's place: the dual it is in, its name there, and the name a module imports it as - the tree's prefix first,
     * so two trees' nodes never share a name in one module.
     */
    public record Ref(DualTree<?> tree, String name, String imported) implements ValueObject {}

    public Ref of(Object node) {
        return switch (node) {
            case Taxon t -> ref(DualTree.Components.INSTANCE, t.token());
            case RoleNode r -> ref(DualTree.Roles.INSTANCE, r.name().value());
            case StateNode s -> ref(DualTree.States.INSTANCE, s.name().value());
            case EventNode e -> ref(DualTree.Events.INSTANCE, e.name().value());
            case ExtentAxis d -> ref(DualTree.Degrees.INSTANCE, DualTree.Degrees.INSTANCE.token(d));
            default -> throw new IllegalArgumentException(node + " is a node of no tree the page names");
        };
    }

    private static Ref ref(DualTree<?> tree, String token) {
        String name = JsLiterals.INSTANCE.name(token);
        return new Ref(tree, name, tree.prefix() + name);
    }
}
