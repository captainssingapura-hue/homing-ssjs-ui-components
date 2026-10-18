package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** The component tree read for its dual (RFC 0071): its branches and its components, each said a branch, a leaf or a composite. */
public record Components() implements DualTree<Taxon> {

    public static final Components INSTANCE = new Components();

    @Override public String name() { return "components"; }
    @Override public String prefix() { return "Component"; }
    @Override public List<Taxon> roots(Taxonomy t) { return List.of(Root.INSTANCE); }
    @Override public List<Taxon> children(Taxonomy t, Taxon node) { return node instanceof ComponentBranch b ? t.children(b) : List.of(); }
    @Override public String token(Taxon node) { return node.token(); }
    @Override public Map<String, String> carries(Taxonomy t, Taxon node, List<Taxon> above) {
        return Map.of("kind", JsLiterals.INSTANCE.string(node instanceof ComponentBranch ? "branch" : node instanceof Composite<?> ? "composite" : "leaf"));
    }
    @Override public Set<Taxon> used(Taxonomy t) { return new LinkedHashSet<>(t.components()); }
}
