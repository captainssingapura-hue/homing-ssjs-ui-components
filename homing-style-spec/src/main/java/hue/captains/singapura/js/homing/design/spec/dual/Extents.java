package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
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
    @Override public List<ExtentAxis> roots(Registration r) { return List.of(ExtentAxis.values()); }
    @Override public List<ExtentAxis> children(Registration r, ExtentAxis node) { return List.of(); }
    @Override public String token(ExtentAxis node) { return node.name().toLowerCase(Locale.ROOT); }
    @Override public String construct(Registration r, ExtentAxis node, String parent, List<ExtentAxis> above) {
        return "new Extent(" + JsLiterals.INSTANCE.string(token(node)) + ", " + node.rest() + ")";
    }
    @Override public List<String> kit() { return List.of("Extent"); }
    @Override public Set<ExtentAxis> used(Registration r) {
        var out = new LinkedHashSet<ExtentAxis>();
        for (Component<?> c : r.taxonomy().components()) out.addAll(r.taxonomy().extents(c));
        return out;
    }
}
