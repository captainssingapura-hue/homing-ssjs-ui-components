package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * The styles a page may wear, read for their dual (RFC 0071): a flat tree, every selectable style a root. What the style
 * manager is told to wear, and what it tells its surface to show - a node, never a name.
 */
public record Styles() implements DualTree<DesignStyle<?>> {

    public static final Styles INSTANCE = new Styles();

    @Override public String name() { return "styles"; }
    @Override public String prefix() { return "Style"; }
    @Override public List<DesignStyle<?>> roots(Registration r) { return r.styles(); }
    @Override public List<DesignStyle<?>> children(Registration r, DesignStyle<?> node) { return List.of(); }
    @Override public String token(DesignStyle<?> node) { return node.token(); }
    @Override public String construct(Registration r, DesignStyle<?> node, String parent, List<DesignStyle<?>> above) {
        return "new Style(" + JsLiterals.INSTANCE.string(token(node)) + ")";
    }
    @Override public List<String> kit() { return List.of("Style"); }
    @Override public Set<DesignStyle<?>> used(Registration r) { return new LinkedHashSet<>(r.styles()); }
}
