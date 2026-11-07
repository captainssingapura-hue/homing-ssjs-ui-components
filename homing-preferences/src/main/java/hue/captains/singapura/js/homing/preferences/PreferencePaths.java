package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/** Reads a preference tree's paths ({@link PreferenceTree}). */
public record PreferencePaths() implements StatelessFunctionalObject {

    public static final PreferencePaths INSTANCE = new PreferencePaths();

    /** The tree's root, levelled from zero. */
    PreferenceNode levelled(PreferenceNode root) {
        return Objects.requireNonNull(root, "root").atDepth(0);
    }

    /** Every node by its path, root first, each parent before its children. */
    Map<String, PreferenceNode> of(PreferenceNode levelled) {
        var byPath = new LinkedHashMap<String, PreferenceNode>();
        walk(levelled, levelled.segment().value(), byPath);
        return byPath;
    }

    private void walk(PreferenceNode node, String path, Map<String, PreferenceNode> byPath) {
        byPath.put(path, node);
        var seen = new java.util.HashSet<NodeName>();
        for (PreferenceNode c : node.children()) {
            if (!seen.add(c.segment())) {
                throw new IllegalArgumentException("Two children of '" + path + "' share the segment '" + c.segment() + "'");
            }
            walk(c, path + "/" + c.segment().value(), byPath);
        }
    }
}
