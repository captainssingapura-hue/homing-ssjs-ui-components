package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/** The extents a component varies along, read for their dual (RFC 0071): a flat tree, every axis a root, each with its value at rest. */
public record Extents() implements DualTree<ExtentAxis> {

    public static final Extents INSTANCE = new Extents();

    @Override public String name() { return "extents"; }
    @Override public String prefix() { return "Extent"; }
    @Override public List<ExtentAxis> roots(Taxonomy t) { return List.of(ExtentAxis.values()); }
    @Override public List<ExtentAxis> children(Taxonomy t, ExtentAxis node) { return List.of(); }
    @Override public String token(ExtentAxis node) { return node.name().toLowerCase(Locale.ROOT); }
    @Override public String construct(Taxonomy t, ExtentAxis node, String parent, List<ExtentAxis> above) {
        return "new Extent(" + JsLiterals.INSTANCE.string(token(node)) + ", " + node.rest() + ")";
    }
    @Override public List<String> kit() { return List.of("Extent"); }
    @Override public Set<ExtentAxis> used(Taxonomy t) {
        var out = new LinkedHashSet<ExtentAxis>();
        for (Component<?> c : t.components()) out.addAll(t.extents(c));
        return out;
    }
}
