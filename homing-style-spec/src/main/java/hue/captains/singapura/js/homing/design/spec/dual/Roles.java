package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** The role catalogue read for its dual (RFC 0071): its branches and its roles - a role functional when a branch it is filed under says so. */
public record Roles() implements DualTree<RoleNode> {

    public static final Roles INSTANCE = new Roles();

    @Override public String name() { return "roles"; }
    @Override public String prefix() { return "Role"; }
    @Override public List<RoleNode> roots(Registration r) { return List.of(RoleRoot.INSTANCE); }
    @Override public List<RoleNode> children(Registration r, RoleNode node) { return node instanceof RoleBranch b ? r.taxonomy().children(b) : List.of(); }
    @Override public String token(RoleNode node) { return node.name().value(); }
    @Override public String construct(Registration r, RoleNode node, String parent, List<RoleNode> above) {
        boolean functional = node instanceof RoleBranch b && b.functional();
        for (RoleNode a : above) if (a instanceof RoleBranch b && b.functional()) functional = true;
        return "new " + (node instanceof RoleBranch ? "RoleBranch" : "Role") + "(" + JsLiterals.INSTANCE.string(token(node)) + ", " + parent + ", " + functional + ")";
    }
    @Override public List<String> kit() { return List.of("RoleBranch", "Role"); }
    @Override public Set<RoleNode> used(Registration r) {
        var out = new LinkedHashSet<RoleNode>();
        for (Part<?, ?> p : r.taxonomy().parts()) out.add(p.role());
        return out;
    }
}
