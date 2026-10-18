package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styling.CastingCss;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Done right, every style's declarations and implementations are a DAG of two levels: no cycle; a declaration's own and
 * same-role spec pointers within its layer, a standalone spec pointer to the placed part's standalone in the style worn,
 * a delegation pointer within its layer or one layer down; a declaration with one pointer to its own implementation and
 * one for each slot of its composite; an implementation giving CSS of its own or exactly one delegation; a root for each
 * top-level component; everything reached from one; every element's implementation with CSS an end of the graph. The
 * graphs are written to target/dags/dags.json, to be drawn - each end with the resource its content is to be, no content
 * read: the virtual layer holds none.
 */
class StyleDagTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();

    @Test
    void everyStyle_isADagOfTwoLevels() throws IOException {
        var problems = new ArrayList<String>();
        var json = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles()) {
            StyleDag dag = ReadDag.INSTANCE.read(SPEC, s);
            var byId = new HashMap<String, StyleDag.Node>();
            for (StyleDag.Node n : dag.nodes()) byId.put(n.id(), n);
            if (dag.order().isEmpty()) problems.add(s.token() + ": a cycle");
            var delegations = new HashMap<String, Integer>();
            var owns = new HashMap<String, Integer>();
            var slots = new HashMap<String, Integer>();
            for (StyleDag.Pointer p : dag.pointers()) {
                StyleDag.Node from = byId.get(p.from()), to = byId.get(p.to());
                switch (p) {
                    case StyleDag.SpecPointer sp -> {
                        if (!(from instanceof StyleDag.DeclarationNode)) problems.add(s.token() + ": " + p + " leaves no declaration");
                        if (sp.spec() != StyleDag.Spec.STANDALONE && !from.layer().equals(to.layer())) problems.add(s.token() + ": " + p + " leaves its layer");
                        if (sp.spec() == StyleDag.Spec.STANDALONE && !SPEC.standaloneLayer(s, to.casting().cast()).equals(to.layer()))
                            problems.add(s.token() + ": " + p + " reaches no standalone of the style worn");
                        if (sp.spec() == StyleDag.Spec.OWN) {
                            owns.merge(sp.from(), 1, Integer::sum);
                            if (!(to instanceof StyleDag.ImplNode)) problems.add(s.token() + ": " + p + " reaches no implementation");
                        } else slots.merge(sp.from(), 1, Integer::sum);
                    }
                    case StyleDag.DelegationPointer dp -> {
                        if (!(from instanceof StyleDag.ImplNode fi) || !(to instanceof StyleDag.ImplNode)) {
                            problems.add(s.token() + ": " + p + " is no implementation's");
                            continue;
                        }
                        boolean kept = dp.gives() == StyleDag.Gives.PARENT_DELEGATION
                                ? SPEC.parent(from.layer()).map(to.layer()::equals).orElse(false) : from.layer().equals(to.layer());
                        if (!kept) problems.add(s.token() + ": " + p + " leaves its layer");
                        if (dp.gives() != fi.gives()) problems.add(s.token() + ": " + p + " is not what " + fi.id() + " gives, " + fi.gives());
                        delegations.merge(dp.from(), 1, Integer::sum);
                    }
                }
            }
            for (StyleDag.DeclarationNode d : dag.declarations()) {
                int expected = Slots.INSTANCE.of(SPEC.taxonomy(), d.casting().cast()).size();
                if (owns.getOrDefault(d.id(), 0) != 1) problems.add(s.token() + ": " + d.id() + " has " + owns.getOrDefault(d.id(), 0) + " pointers to its own implementation");
                if (slots.getOrDefault(d.id(), 0) != expected) problems.add(s.token() + ": " + d.id() + " has " + expected + " slots and " + slots.getOrDefault(d.id(), 0) + " spec pointers");
            }
            for (StyleDag.ImplNode n : dag.implementations()) {
                int d = delegations.getOrDefault(n.id(), 0);
                if (n.needsCss() ? d != 0 : d != 1) problems.add(s.token() + ": " + n.id() + " gives " + n.gives() + " with " + d + " delegations");
            }
            long roots = dag.nodes().stream().filter(StyleDag.Node::root).count();
            if (roots != SPEC.top().size()) problems.add(s.token() + ": " + roots + " roots for " + SPEC.top().size() + " top-level components");
            if (reachable(dag).size() != dag.nodes().size()) problems.add(s.token() + ": " + (dag.nodes().size() - reachable(dag).size()) + " nodes no root reaches");
            for (ElementPath e : SPEC.elements()) {
                CssImplementation<?, ?, ?, ?> worn = SPEC.wears(s, e);
                boolean found = dag.implementations().stream().anyMatch(n -> n.needsCss() && n.impl().getClass() == worn.getClass());
                if (!found) problems.add(s.token() + " " + e.token() + ": wears " + worn.getClass().getName() + ", no CSS end of the graph");
            }
            summary(dag);
            json.add(json(dag));
        }
        Path out = Path.of("target/dags/dags.json");
        Files.createDirectories(out.getParent());
        Files.writeString(out, "{\"styles\": [\n" + String.join(",\n", json) + "\n]}\n");
        assertTrue(problems.isEmpty(), problems.size() + " problems:\n" + String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        assertEquals(SPEC.styles().size(), json.size());
    }

    private static Set<String> reachable(StyleDag dag) {
        var out = new HashMap<String, List<String>>();
        for (StyleDag.Pointer p : dag.pointers()) out.computeIfAbsent(p.from(), k -> new ArrayList<>()).add(p.to());
        var seen = new HashSet<String>();
        var todo = new ArrayDeque<String>();
        for (StyleDag.Node n : dag.nodes()) if (n.root()) { seen.add(n.id()); todo.add(n.id()); }
        while (!todo.isEmpty()) for (String to : out.getOrDefault(todo.poll(), List.of())) if (seen.add(to)) todo.add(to);
        return seen;
    }

    private static void summary(StyleDag dag) {
        var layers = new LinkedHashMap<String, Integer>();
        for (StyleDag.Node n : dag.nodes()) layers.merge(n.layer().token(), 1, Integer::sum);
        var specs = new LinkedHashMap<StyleDag.Spec, Integer>();
        for (StyleDag.SpecPointer p : dag.specs()) specs.merge(p.spec(), 1, Integer::sum);
        var dels = new LinkedHashMap<StyleDag.Gives, Integer>();
        for (StyleDag.DelegationPointer d : dag.delegations()) dels.merge(d.gives(), 1, Integer::sum);
        var own = dag.needingCss(dag.style());
        System.out.println("[dag] " + dag.style().token() + ": declarations " + dag.declarations().size() + ", implementations " + dag.implementations().size()
                + " " + layers + "; spec pointers " + dag.specs().size() + " " + specs + " (nesting " + dag.nesting() + "); delegation pointers "
                + dag.delegations().size() + " " + dels + " (chain " + dag.delegationLength() + "); CSS ends of its own " + own.size());
    }

    private static String json(StyleDag dag) {
        var nodes = new ArrayList<String>();
        for (StyleDag.Node n : dag.nodes()) {
            String common = "{\"id\": " + q(n.id()) + ", \"layer\": " + q(n.layer().token()) + ", \"casting\": " + q(n.casting().token()) + ", \"root\": " + n.root();
            nodes.add(switch (n) {
                case StyleDag.DeclarationNode d -> common + ", \"node\": \"declaration\"}";
                case StyleDag.ImplNode i -> common + ", \"node\": \"impl\", \"gives\": " + q(i.gives().name())
                        + (i.needsCss() ? ", \"file\": " + q(CastingCss.INSTANCE.resource(i.impl().getClass())) : "") + "}";
            });
        }
        var edges = new ArrayList<String>();
        for (StyleDag.Pointer p : dag.pointers())
            edges.add("{\"from\": " + q(p.from()) + ", \"to\": " + q(p.to()) + switch (p) {
                case StyleDag.SpecPointer sp -> ", \"pointer\": \"spec\", \"spec\": " + q(sp.spec().name()) + ", \"slot\": " + q(sp.slot().token());
                case StyleDag.DelegationPointer dp -> ", \"pointer\": \"delegation\", \"gives\": " + q(dp.gives().name());
            } + "}");
        return "{\"style\": " + q(dag.style().token()) + ", \"parent\": " + SPEC.parent(dag.style()).map(p -> q(p.token())).orElse("null")
                + ", \"depth\": " + dag.depth() + ", \"nesting\": " + dag.nesting() + ", \"delegationLength\": " + dag.delegationLength()
                + ",\n \"nodes\": [\n  " + String.join(",\n  ", nodes) + "],\n \"edges\": [\n  " + String.join(",\n  ", edges) + "]}";
    }

    private static String q(String s) { return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\""; }
}
