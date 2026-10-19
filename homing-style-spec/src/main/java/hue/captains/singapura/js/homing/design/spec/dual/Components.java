package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.design.spec.FixedTypes;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.TreeDual;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** The component tree read for its dual (RFC 0071): its branches and its components, each said a branch, a leaf or a composite. */
public record Components() implements DualTree<Taxon> {

    public static final Components INSTANCE = new Components();

    @Override public String name() { return "components"; }
    @Override public String prefix() { return "Component"; }
    @Override public List<Taxon> roots(Registration r) { return List.of(Root.INSTANCE); }
    @Override public List<Taxon> children(Registration r, Taxon node) { return node instanceof ComponentBranch b ? r.taxonomy().children(b) : List.of(); }
    @Override public String token(Taxon node) { return node.token(); }
    @Override public String construct(Registration r, Taxon node, String parent, List<Taxon> above) {
        String token = JsLiterals.INSTANCE.string(node.token());
        return node instanceof ComponentBranch ? "new ComponentBranch(" + token + ", " + parent + ")"
                : "new Component(" + token + ", " + parent + ", " + (node instanceof Composite<?>) + ")";
    }
    @Override public List<String> kit() { return List.of("ComponentBranch", "Component", "Fixed"); }

    /** Beside each component, its fixed type (D30): {@code FixedPlainButton} beside {@code ComponentPlainButton}. */
    @Override public List<TreeDual.Companion> companions(Registration r, Taxon node) {
        return node instanceof Component<?> c ? List.of(FixedTypes.INSTANCE.companion(c, name(node))) : List.of();
    }
    @Override public Set<Taxon> used(Registration r) { return new LinkedHashSet<>(r.taxonomy().components()); }
}
