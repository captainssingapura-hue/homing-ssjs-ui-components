package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styling.CastingCss;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Done right, every style's looks are a DAG: no cycle; its layers kept - a spec and a same-style delegation within a
 * layer, a parent delegation one layer down, nothing pointing up; one root a top-level component; every look reached from
 * one; each look CSS of its own, or exactly one delegation; every element's look with CSS a look of the graph. A look
 * needs CSS exactly where it is an end; each end's file is there. The graphs are written to target/dags/dags.json, to be
 * drawn - each end with its CSS's fingerprint, so that what repeats another look's CSS shows.
 */
class StyleDagTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();

    @Test
    void everyStyle_isADag_itsLayersKept() throws IOException {
        var problems = new ArrayList<String>();
        var json = new ArrayList<String>();
        for (DesignStyle<?> s : SPEC.styles()) {
            StyleDag dag = ReadDag.INSTANCE.read(SPEC, s);
            var byId = new HashMap<String, StyleDag.Node>();
            for (StyleDag.Node n : dag.nodes()) byId.put(n.id(), n);
            if (dag.order().isEmpty()) problems.add(s.token() + ": a cycle: " + cycle(dag));
            var delegations = new HashMap<String, Integer>();
            for (StyleDag.Edge e : dag.edges()) {
                StyleDag.Node from = byId.get(e.from()), to = byId.get(e.to());
                boolean kept = switch (e.points()) {
                    case PART, DELEGATION -> from.layer().equals(to.layer());
                    case PARENT_DELEGATION -> SPEC.parent(from.layer()).map(to.layer()::equals).orElse(false);
                };
                if (!kept) problems.add(s.token() + ": " + e.from() + " -" + e.points() + "-> " + e.to() + " leaves its layer");
                if (e.points() != StyleDag.Points.PART) delegations.merge(e.from(), 1, Integer::sum);
            }
            for (StyleDag.Node n : dag.nodes()) {
                int d = delegations.getOrDefault(n.id(), 0);
                if (n.gives() == StyleDag.Gives.CSS ? d != 0 : d != 1) problems.add(s.token() + ": " + n.id() + " gives " + n.gives() + " with " + d + " delegations");
            }
            long roots = dag.nodes().stream().filter(StyleDag.Node::root).count();
            if (roots != SPEC.top().size()) problems.add(s.token() + ": " + roots + " roots for " + SPEC.top().size() + " top-level components");
            Set<String> reached = reachable(dag);
            if (reached.size() != dag.nodes().size()) problems.add(s.token() + ": " + (dag.nodes().size() - reached.size()) + " looks no root reaches");
            for (StyleDag.Node n : dag.nodes())
                if (n.needsCss() && CastingCss.INSTANCE.read(n.look().getClass()).isEmpty())
                    problems.add(s.token() + ": " + n.id() + " needs CSS, and has no " + CastingCss.INSTANCE.resource(n.look().getClass()));
            for (ElementPath e : SPEC.elements()) {
                String worn = wornId(s, e);
                StyleDag.Node n = byId.get(worn);
                if (n == null || n.gives() != StyleDag.Gives.CSS) problems.add(s.token() + " " + e.token() + ": wears " + worn + ", no CSS look of the graph");
            }
            summary(dag);
            json.add(json(dag));
        }
        Path out = Path.of("target/dags/dags.json");
        Files.createDirectories(out.getParent());
        Files.writeString(out, "{\"styles\": [\n" + String.join(",\n", json) + "\n]}\n");
        assertTrue(problems.isEmpty(), String.join("\n", problems));
        assertEquals(SPEC.styles().size(), json.size());
    }

    /** The id of the look with CSS an element wears, by walking the registry - its layer found as the graph finds it. */
    private static String wornId(DesignStyle<?> s, ElementPath e) {
        CastingStylePerRole<?, ?, ?, ?> look = SPEC.wears(s, e);
        for (DesignStyle<?> at = s; at != null; at = SPEC.parent(at).orElse(null))
            if (look.getClass().getPackageName().endsWith("." + at.token().replace("-", ""))) return at.token() + "/" + look.getClass().getSimpleName();
        return "?/" + look.getClass().getSimpleName();
    }

    private static Set<String> reachable(StyleDag dag) {
        var out = new HashMap<String, List<String>>();
        for (StyleDag.Edge e : dag.edges()) out.computeIfAbsent(e.from(), k -> new ArrayList<>()).add(e.to());
        var seen = new HashSet<String>();
        var todo = new ArrayDeque<String>();
        for (StyleDag.Node n : dag.nodes()) if (n.root()) { seen.add(n.id()); todo.add(n.id()); }
        while (!todo.isEmpty()) for (String to : out.getOrDefault(todo.poll(), List.of())) if (seen.add(to)) todo.add(to);
        return seen;
    }

    private static String cycle(StyleDag dag) {
        var out = new HashMap<String, List<String>>();
        for (StyleDag.Edge e : dag.edges()) out.computeIfAbsent(e.from(), k -> new ArrayList<>()).add(e.to());
        var state = new HashMap<String, Integer>();
        var path = new ArrayList<String>();
        for (StyleDag.Node n : dag.nodes()) {
            String found = dfs(n.id(), out, state, path);
            if (found != null) return found;
        }
        return "?";
    }

    private static String dfs(String id, Map<String, List<String>> out, Map<String, Integer> state, List<String> path) {
        Integer st = state.get(id);
        if (st != null && st == 2) return null;
        if (st != null && st == 1) return String.join(" -> ", path.subList(path.indexOf(id), path.size())) + " -> " + id;
        state.put(id, 1);
        path.add(id);
        for (String to : out.getOrDefault(id, List.of())) {
            String found = dfs(to, out, state, path);
            if (found != null) return found;
        }
        path.removeLast();
        state.put(id, 2);
        return null;
    }

    private static void summary(StyleDag dag) {
        var layers = new LinkedHashMap<String, Integer>();
        for (StyleDag.Node n : dag.nodes()) layers.merge(n.layer().token(), 1, Integer::sum);
        var kinds = new LinkedHashMap<StyleDag.Points, Integer>();
        for (StyleDag.Edge e : dag.edges()) kinds.merge(e.points(), 1, Integer::sum);
        long css = dag.nodes().stream().filter(StyleDag.Node::needsCss).count();
        var own = dag.needingCss(dag.style());
        long distinct = own.stream().map(StyleDagTest::fingerprint).distinct().count();
        var shared = dag.pointedAt().entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(3)
                .map(e -> e.getKey() + "<-" + e.getValue()).toList();
        System.out.println("[dag] " + dag.style().token() + ": looks " + dag.nodes().size() + " " + layers + ", edges " + dag.edges().size() + " " + kinds
                + ", CSS ends " + css + " (its own " + own.size() + ", " + distinct + " distinct texts), depth " + dag.depth() + ", most pointed at " + shared);
    }

    private static String json(StyleDag dag) {
        var nodes = new ArrayList<String>();
        for (StyleDag.Node n : dag.nodes())
            nodes.add("{\"id\": " + q(n.id()) + ", \"layer\": " + q(n.layer().token()) + ", \"casting\": " + q(n.casting().token())
                    + ", \"gives\": " + q(n.gives().name()) + ", \"root\": " + n.root()
                    + (n.needsCss() ? ", \"css\": " + q(fingerprint(n)) + ", \"file\": " + q(CastingCss.INSTANCE.resource(n.look().getClass())) : "") + "}");
        var edges = new ArrayList<String>();
        for (StyleDag.Edge e : dag.edges())
            edges.add("{\"from\": " + q(e.from()) + ", \"to\": " + q(e.to()) + ", \"points\": " + q(e.points().name())
                    + ", \"slot\": " + (e.slot() == null ? "null" : q(e.slot().token())) + "}");
        return "{\"style\": " + q(dag.style().token()) + ", \"parent\": " + SPEC.parent(dag.style()).map(p -> q(p.token())).orElse("null")
                + ", \"depth\": " + dag.depth() + ",\n \"nodes\": [\n  " + String.join(",\n  ", nodes) + "],\n \"edges\": [\n  " + String.join(",\n  ", edges) + "]}";
    }

    /** A look's CSS text, fingerprinted: equal texts, equal prints. */
    private static String fingerprint(StyleDag.Node n) {
        String text = CastingCss.INSTANCE.read(n.look().getClass()).orElse("").replace("\r\n", "\n").strip();
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8))).substring(0, 12);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String q(String s) { return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\""; }
}
