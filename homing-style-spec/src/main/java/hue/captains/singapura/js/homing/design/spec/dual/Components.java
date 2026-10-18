package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** The component tree read for its dual (RFC 0071): its branches and its components, each said a branch, a leaf or a composite. */
public record Components() implements DualTree<Taxon> {

    public static final Components INSTANCE = new Components();

    @Override public String name() { return "components"; }
    @Override public String prefix() { return "Component"; }
    @Override public List<Taxon> roots(Taxonomy t) { return List.of(Root.INSTANCE); }
    @Override public List<Taxon> children(Taxonomy t, Taxon node) { return node instanceof ComponentBranch b ? t.children(b) : List.of(); }
    @Override public String token(Taxon node) { return node.token(); }
    @Override public String construct(Taxonomy t, Taxon node, String parent, List<Taxon> above) {
        String token = JsLiterals.INSTANCE.string(node.token());
        return node instanceof ComponentBranch ? "new ComponentBranch(" + token + ", " + parent + ")"
                : "new Component(" + token + ", " + parent + ", " + (node instanceof Composite<?>) + ")";
    }
    @Override public List<String> kit() { return List.of("ComponentBranch", "Component"); }
    @Override public Set<Taxon> used(Taxonomy t) { return new LinkedHashSet<>(t.components()); }
}
