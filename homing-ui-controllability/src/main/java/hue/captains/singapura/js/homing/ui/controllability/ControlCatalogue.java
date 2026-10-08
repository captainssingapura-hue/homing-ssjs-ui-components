package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

/**
 * A control catalogue, as read: its branches under the root and its options. It cannot be
 * constructed when it does not hold together - the check is the constructor's, so no such
 * catalogue exists, however it is made. Refused, every problem named:
 *
 * <ul>
 *   <li>two nodes of one name - the root, a branch, an option, at any level: one word, one meaning;</li>
 *   <li>two options applied by one method - what is controlled could not tell them apart;</li>
 *   <li>an option controlled by more than one means;</li>
 *   <li>a node filed under a branch the catalogue does not hold.</li>
 * </ul>
 *
 * @param branches the branches under the root, in the order first reached
 * @param options  the options
 */
public record ControlCatalogue(List<ControlBranch> branches, List<ControlOption<?>> options) implements ValueObject {

    public ControlCatalogue {
        branches = List.copyOf(branches);
        options = List.copyOf(options);
        var problems = new ArrayList<String>();
        var nodes = new LinkedHashSet<ControlNode>();
        nodes.add(ControlRoot.INSTANCE);
        nodes.addAll(branches);
        nodes.addAll(options);
        var byName = new LinkedHashMap<String, List<ControlNode>>();
        for (ControlNode n : nodes) byName.computeIfAbsent(n.name().value(), x -> new ArrayList<>()).add(n);
        byName.forEach((name, same) -> {
            if (same.size() > 1) problems.add("'" + name + "' names " + same.size() + " nodes: " + said(same));
        });
        var byMethod = new LinkedHashMap<String, List<ControlNode>>();
        for (ControlOption<?> o : options) byMethod.computeIfAbsent(o.method(), x -> new ArrayList<>()).add(o);
        byMethod.forEach((method, same) -> {
            if (same.size() > 1) problems.add("the method '" + method + "' applies " + same.size() + " options: " + said(same));
        });
        for (ControlOption<?> o : options) {
            int means = (o instanceof WithExtent<?> ? 1 : 0) + (o instanceof WithSwitch<?> ? 1 : 0)
                      + (o instanceof WithAction<?> ? 1 : 0) + (o instanceof WithQuestion<?> ? 1 : 0);
            if (means > 1) problems.add(qualified(o) + " is controlled by " + means + " means: one, of extent, switch, action, question");
        }
        for (ControlNode n : nodes) {
            ControlBranch parent = ControlLevels.parentOf(n);
            if (parent != null && !nodes.contains(parent))
                problems.add(qualified(n) + " is filed under " + qualified(parent) + ", which the catalogue does not hold");
        }
        if (!problems.isEmpty()) throw new RefusedControls(problems);
    }

    /** The branch a node is filed under; none for the root. */
    public ControlBranch parent(ControlNode node) { return ControlLevels.parentOf(node); }

    /** The branches and options directly under a branch - the root's included - branches first. */
    public List<ControlNode> children(ControlBranch branch) {
        var out = new ArrayList<ControlNode>();
        for (ControlBranch b : branches) if (branch.equals(ControlLevels.parentOf(b))) out.add(b);
        for (ControlOption<?> o : options) if (branch.equals(o.parent())) out.add(o);
        return List.copyOf(out);
    }

    /** The option of a name, if the catalogue has one. */
    public Optional<ControlOption<?>> option(String name) {
        return options.stream().filter(o -> o.name().value().equals(name)).findFirst();
    }

    private static String said(List<ControlNode> nodes) {
        return String.join(" and ", nodes.stream().map(ControlCatalogue::qualified).toList());
    }

    /** A node as a problem says it: its type without the package, nesting kept. */
    static String qualified(Object node) {
        String n = node.getClass().getName();
        return n.substring(n.lastIndexOf('.') + 1);
    }
}
