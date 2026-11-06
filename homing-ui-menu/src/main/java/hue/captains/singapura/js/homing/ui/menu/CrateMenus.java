package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.component.ComponentTrees;
import hue.captains.singapura.js.homing.component.UiComponent;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.ui.menu.tree.ContextMenuKind;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/** What a tree of crates asks of the context menu, and whether it keeps to it ({@link ContextMenuRegistry}). */
public record CrateMenus() implements StatelessFunctionalObject {

    public static final CrateMenus INSTANCE = new CrateMenus();

    /** The kinds the catalogued components of a crate closure need, each once: in catalogue order between components, by name within one — a set has no order, and a stamped module must. */
    public ContextMenuRegistry requiredBy(List<Crate> topLevel) {
        var kinds = new LinkedHashMap<Class<?>, ContextMenuKind<?>>();
        for (UiComponent<?> c : ComponentTrees.INSTANCE.components(topLevel))
            if (c instanceof NeedContextMenu n)
                for (ContextMenuKind<?> k : n.required().stream().sorted(Comparator.comparing(ContextMenuKind::kind)).toList()) kinds.putIfAbsent(k.getClass(), k);
        return new ContextMenuRegistry(List.copyOf(kinds.values()));
    }

    /**
     * The problems with the closure's needs: a need on something that is
     * not a declared component, or on a component no catalogue lists — a
     * need nobody can find registers nothing — and a need that names no
     * kind. Empty when every need will be met by {@link #requiredBy}.
     */
    public List<String> validate(List<Crate> topLevel) {
        var problems = new ArrayList<String>();
        var catalogued = new HashSet<Class<?>>();
        for (UiComponent<?> c : ComponentTrees.INSTANCE.components(topLevel)) catalogued.add(c.getClass());
        for (Crate crate : ComponentTrees.INSTANCE.closure(topLevel)) {
            for (CrateEntry e : crate.entries()) {
                for (var x : e.module().exports().exports()) {
                    if (!(x instanceof NeedContextMenu n)) continue;
                    String who = crate.name() + ": " + e.module().getClass().getSimpleName() + "." + x.getClass().getSimpleName();
                    if (!(x instanceof UiComponent<?>) || !(e.module() instanceof DomModule<?>)) { problems.add(who + " needs context menus but is not a declared component"); continue; }
                    if (!catalogued.contains(x.getClass())) problems.add(who + " needs context menus but no catalogue lists it");
                    if (n.required() == null || n.required().isEmpty()) problems.add(who + " needs context menus but names no kind");
                }
            }
        }
        return List.copyOf(problems);
    }
}
