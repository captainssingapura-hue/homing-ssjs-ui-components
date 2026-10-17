package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A style's looks as a graph ({@link StyleDag}), read off the registry by walking, never off the looks' types: each
 * top-level component's implementation found in the style or the layer below it falls to, then each spec - of the same
 * layer, as the compiler holds - and each delegation: to the same layer, or the one below.
 */
public record ReadDag() implements StatelessFunctionalObject {

    public static final ReadDag INSTANCE = new ReadDag();

    public StyleDag read(SpecRegistry spec, DesignStyle<?> style) {
        var nodes = new LinkedHashMap<String, StyleDag.Node>();
        var edges = new ArrayList<StyleDag.Edge>();
        for (Component<?> top : spec.top()) {
            for (Optional<DesignStyle<?>> at = Optional.of(style); at.isPresent(); at = spec.parent(at.get())) {
                CastingStylePerRole<?, ?, ?, ?> own = spec.implementations().getOrDefault(at.get(), Map.of()).get(top);
                if (own == null) continue;
                visit(spec, own, at.get(), new Casting(top), true, nodes, edges);
                break;
            }
        }
        return new StyleDag(style, new ArrayList<>(nodes.values()), edges);
    }

    private static String id(DesignStyle<?> layer, CastingStylePerRole<?, ?, ?, ?> look) {
        return layer.token() + "/" + look.getClass().getSimpleName();
    }

    private static String visit(SpecRegistry spec, CastingStylePerRole<?, ?, ?, ?> look, DesignStyle<?> layer, Casting as, boolean root,
                                Map<String, StyleDag.Node> nodes, List<StyleDag.Edge> edges) {
        String id = id(layer, look);
        StyleDag.Node known = nodes.get(id);
        if (known != null) {
            if (root && !known.root()) nodes.put(id, new StyleDag.Node(id, layer, as, known.gives(), true, look));
            return id;
        }
        StyleDag.Gives gives = switch (look.style()) {
            case StylingRes.CssContent<?, ?, ?, ?> c -> StyleDag.Gives.CSS;
            case StylingRes.Delegation<?, ?, ?, ?> d -> StyleDag.Gives.DELEGATION;
            case StylingRes.RootDelegation<?, ?, ?, ?> r -> StyleDag.Gives.ROOT_DELEGATION;
            case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> StyleDag.Gives.PARENT_DELEGATION;
        };
        nodes.put(id, new StyleDag.Node(id, layer, as, gives, root, look));
        for (PartSpec<?, ?, ?> p : look.parts().parts()) {
            String to = visit(spec, p.look(), layer, p.casting(), false, nodes, edges);
            edges.add(new StyleDag.Edge(id, to, StyleDag.Points.PART, p.slot()));
        }
        switch (look.style()) {
            case StylingRes.CssContent<?, ?, ?, ?> c -> { }
            case StylingRes.Delegation<?, ?, ?, ?> d ->
                    edges.add(new StyleDag.Edge(id, visit(spec, d.to(), layer, as, false, nodes, edges), StyleDag.Points.DELEGATION, null));
            case StylingRes.RootDelegation<?, ?, ?, ?> r ->
                    edges.add(new StyleDag.Edge(id, visit(spec, r.to(), layer, as.alone(), false, nodes, edges), StyleDag.Points.ROOT_DELEGATION, null));
            case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> {
                DesignStyle<?> below = spec.parent(layer).orElseThrow(() -> new IllegalStateException(id + " delegates below " + layer.token() + ", which is written over nothing"));
                edges.add(new StyleDag.Edge(id, visit(spec, p.to(), below, as, false, nodes, edges), StyleDag.Points.PARENT_DELEGATION, null));
            }
        }
        return id;
    }
}
