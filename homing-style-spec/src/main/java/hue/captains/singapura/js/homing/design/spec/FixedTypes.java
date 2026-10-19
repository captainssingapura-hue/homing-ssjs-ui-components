package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * A component's fixed type, for the page (RFC 0071, D30): what is immutable for the component's life, one object of
 * the component's own type - the second argument of every constructor, {@code (make, fixed, place?)}. Made in the
 * components' dual beside the component's node - {@code FixedPlainButton} beside {@code ComponentPlainButton} - and
 * imported by every header that names the component, its own and its owners', so an owner has the type of every part it
 * makes. It extends the kit's {@code Fixed}, which checks what it is given and freezes what it makes; the component takes
 * an instance of its own type alone.
 *
 * <p>Every component has one. A component with nothing fixed has a type with no fields, refusing any argument -
 * every house component, as the port begins: the fields a component's Java declaration gives its type come with the
 * first component that has one.</p>
 */
public record FixedTypes() implements StatelessFunctionalObject {

    public static final FixedTypes INSTANCE = new FixedTypes();

    /** A component's fixed type's name: {@code Fixed}, then the component's - {@code FixedPlainButton}. */
    public String name(Component<?> component) { return "Fixed" + JsLiterals.INSTANCE.name(component.token()); }

    /**
     * A component's fixed type, as its dual declares it: the kit's {@code Fixed} for the component's node, named in the
     * dual - {@code class FixedPlainButton extends Fixed}, handing on what it is given.
     *
     * @param node the component's node's name in the dual: {@code PlainButton}
     */
    public TreeDual.Companion companion(Component<?> component, String node) {
        String n = name(component);
        return new TreeDual.Companion(n, "class " + n + " extends Fixed { constructor(...given) { super(" + node + ", given); } }");
    }
}
