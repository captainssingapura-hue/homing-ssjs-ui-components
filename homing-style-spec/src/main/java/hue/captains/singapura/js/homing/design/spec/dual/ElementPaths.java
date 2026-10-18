package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.spec.DualRef;
import hue.captains.singapura.js.homing.design.spec.ElementPath;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.Slots;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * The tree of element paths read for its dual (RFC 0071): every place an element can be - a top-level component, then each slot
 * down from it, a part in the role its owner declares. A pure function of what is registered, the same in every style;
 * every path is used, the tree being made of the registered components alone. Each node made by the kit, its part and
 * its role the components' and the roles' duals' own nodes.
 */
public record ElementPaths() implements DualTree<ElementPath> {

    public static final ElementPaths INSTANCE = new ElementPaths();

    @Override public String name() { return "paths"; }
    @Override public String prefix() { return "Path"; }
    @Override public List<ElementPath> roots(Registration r) { return r.top().stream().map(c -> new ElementPath(c, List.of())).toList(); }
    @Override public List<ElementPath> children(Registration r, ElementPath path) {
        return Slots.INSTANCE.of(r.taxonomy(), component(path)).stream().map(s -> path.then(s.slot())).toList();
    }
    @Override public String token(ElementPath path) { return path.path(); }

    /** A path's JS name: its top-level component's, then each slot's part and role, joined - {@code Dialog_ActionBarActions}. */
    @Override public String name(ElementPath path) {
        var js = JsLiterals.INSTANCE;
        var out = new StringBuilder(js.name(path.top().token()));
        for (Casting slot : path.parts()) out.append('_').append(js.name(slot.cast().token())).append(js.name(slot.role().name().value()));
        return out.toString();
    }

    @Override public String construct(Registration r, ElementPath path, String parent, List<ElementPath> above) {
        var refs = DualRef.INSTANCE;
        if (path.parts().isEmpty()) return "new ElementPath(null, " + refs.of(path.top()).imported() + ", null)";
        Casting slot = path.parts().getLast();
        return "new ElementPath(" + parent + ", " + refs.of(slot.cast()).imported() + ", " + refs.of(slot.role()).imported() + ")";
    }

    @Override public List<Object> refers(Registration r, ElementPath path) {
        if (path.parts().isEmpty()) return List.of(path.top());
        Casting slot = path.parts().getLast();
        return List.of(slot.cast(), slot.role());
    }

    @Override public Set<ElementPath> used(Registration r) {
        var out = new LinkedHashSet<ElementPath>();
        var todo = new ArrayList<>(roots(r));
        while (!todo.isEmpty()) {
            ElementPath at = todo.removeLast();
            out.add(at);
            todo.addAll(children(r, at));
        }
        return out;
    }

    @Override public List<String> kit() { return List.of("ElementPath"); }

    private static Component<?> component(ElementPath path) { return path.parts().isEmpty() ? path.top() : path.parts().getLast().cast(); }
}
