package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A style's declarations and implementations as a graph ({@link StyleDag}), read off the registry by walking, never off
 * the types: each top-level component's standalone, in the style or the layer below it falls to; from a declaration, a
 * spec pointer to its own implementation, one to each slot's target in its role - of the same layer - and one to each
 * placed part's standalone, as the style worn gives it; from an implementation, its delegation pointer: within its layer,
 * or to the one below.
 */
public record ReadDag() implements StatelessFunctionalObject {

    public static final ReadDag INSTANCE = new ReadDag();

    public StyleDag read(SpecRegistry spec, DesignStyle<?> style) {
        var walk = new Walk(spec, style, new LinkedHashMap<>(), new ArrayList<>());
        for (Component<?> top : spec.top()) {
            DesignStyle<?> layer = spec.standaloneLayer(style, top);
            walk.target(spec.standalones().get(layer).get(top), layer, new Casting(top), true);
        }
        return new StyleDag(style, new ArrayList<>(walk.nodes().values()), walk.pointers());
    }

    private record Walk(SpecRegistry spec, DesignStyle<?> worn, Map<String, StyleDag.Node> nodes, List<StyleDag.Pointer> pointers) {

        String target(Target<?, ?, ?, ?> target, DesignStyle<?> layer, Casting as, boolean root) {
            return switch (target) {
                case Target.Impl<?, ?, ?, ?> i -> impl(i.impl(), layer, as, root);
                case Target.Declared<?, ?, ?, ?> d -> declaration(d.declaration(), layer, as, root);
            };
        }

        String declaration(CastingDeclaration<?, ?, ?, ?> declaration, DesignStyle<?> layer, Casting as, boolean root) {
            String id = layer.token() + "/" + declaration.getClass().getSimpleName();
            StyleDag.Node known = nodes.get(id);
            if (known != null) {
                if (root && !known.root()) nodes.put(id, new StyleDag.DeclarationNode(id, layer, as, true, declaration));
                return id;
            }
            nodes.put(id, new StyleDag.DeclarationNode(id, layer, as, root, declaration));
            for (PartSpec<?, ?, ?> p : declaration.parts().parts()) {
                switch (p) {
                    case PartSpec.SameRole<?, ?, ?, ?, ?> s ->
                            pointers.add(new StyleDag.SpecPointer(id, target(s.to(), layer, s.casting(), false), StyleDag.Spec.SAME_ROLE, s.slot()));
                    case PartSpec.Standalone<?, ?, ?, ?, ?> s -> {
                        DesignStyle<?> at = spec.standaloneLayer(worn, s.part());
                        String to = target(spec.standalones().get(at).get(s.part()), at, new Casting(s.part()), false);
                        pointers.add(new StyleDag.SpecPointer(id, to, StyleDag.Spec.STANDALONE, s.slot()));
                    }
                }
            }
            pointers.add(new StyleDag.SpecPointer(id, impl(declaration.own(), layer, as, false), StyleDag.Spec.OWN, as));
            return id;
        }

        String impl(CastingStylePerRole<?, ?, ?, ?> impl, DesignStyle<?> layer, Casting as, boolean root) {
            String id = layer.token() + "/" + impl.getClass().getSimpleName();
            StyleDag.Node known = nodes.get(id);
            if (known instanceof StyleDag.ImplNode k) {
                if (root && !k.root()) nodes.put(id, new StyleDag.ImplNode(id, layer, as, k.gives(), true, impl));
                return id;
            }
            StyleDag.Gives gives = switch (impl.style()) {
                case StylingRes.CssContent<?, ?, ?, ?> c -> StyleDag.Gives.CSS;
                case StylingRes.Delegation<?, ?, ?, ?> d -> StyleDag.Gives.DELEGATION;
                case StylingRes.RootDelegation<?, ?, ?, ?> r -> StyleDag.Gives.ROOT_DELEGATION;
                case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> StyleDag.Gives.PARENT_DELEGATION;
            };
            nodes.put(id, new StyleDag.ImplNode(id, layer, as, gives, root, impl));
            switch (impl.style()) {
                case StylingRes.CssContent<?, ?, ?, ?> c -> { }
                case StylingRes.Delegation<?, ?, ?, ?> d ->
                        pointers.add(new StyleDag.DelegationPointer(id, impl(d.to(), layer, as, false), gives));
                case StylingRes.RootDelegation<?, ?, ?, ?> r ->
                        pointers.add(new StyleDag.DelegationPointer(id, impl(r.to(), layer, as.alone(), false), gives));
                case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> {
                    DesignStyle<?> below = spec.parent(layer).orElseThrow(() -> new IllegalStateException(id + " delegates below " + layer.token() + ", written over nothing"));
                    pointers.add(new StyleDag.DelegationPointer(id, impl(p.to(), below, as, false), gives));
                }
            }
            return id;
        }
    }
}
