package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.CatalogueNames;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleBranch;
import hue.captains.singapura.js.homing.component.taxonomy.RoleCatalogue;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyFinding;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The house's role catalogue, read on its own and constructed - so its names are each its own, and
 * jOntology holds every node stateless - holds the shape the appendix files it in: Saying, Doing,
 * Shaping at level 1, eighteen branches at level 2, and sixty-seven roles, each in its branch.
 */
class HouseRoleCatalogueTest {

    private static final RoleCatalogue CATALOGUE = HouseRoleCatalogue.INSTANCE.read();

    @Test
    void threeTopBranches_eighteenBeneath_sixtySevenRoles() {
        assertEquals(List.of(HouseSaying.Saying.INSTANCE, HouseDoing.Doing.INSTANCE, HouseShaping.Shaping.INSTANCE),
                CATALOGUE.children(RoleRoot.INSTANCE));
        assertEquals(21, CATALOGUE.branches().size());
        assertEquals(18, CATALOGUE.branches().stream().filter(b -> b.level() == 2).count());
        assertEquals(67, CATALOGUE.roles().size());
        assertEquals(67, Set.copyOf(CATALOGUE.roles()).size(), "each listed once");
    }

    @Test
    void eachBranch_holdsTheRolesTheAppendixFilesInIt() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("naming", List.of("title", "subtitle", "name", "category", "symbol"));
        expected.put("describing", List.of("summary", "note", "tag"));
        expected.put("guiding", List.of("hint", "prompt"));
        expected.put("reporting", List.of("value", "level", "count", "status"));
        expected.put("signalling", List.of("keys", "landing", "disclose"));
        expected.put("scaling", List.of("span", "rest", "notch", "figure"));
        expected.put("choosing", List.of("choice", "style", "palette", "what", "how", "where", "picker"));
        expected.put("committing", List.of("go", "cancel", "action"));
        expected.put("changing", List.of("toggle", "adjust", "thumb", "reset"));
        expected.put("viewing", List.of("zoom-in", "zoom-out", "fit"));
        expected.put("managing", List.of("close", "add"));
        expected.put("going", List.of("open", "home", "preferences", "path", "step"));
        expected.put("bands", List.of("head", "actions", "tabs"));
        expected.put("layers", List.of("window", "veil", "float", "popup"));
        expected.put("contents", List.of("page", "host", "index", "detail", "specimen", "setting", "layout"));
        expected.put("members", List.of("member", "entry", "dock", "cell"));
        expected.put("dividers", List.of("seam", "break"));
        expected.put("handles", List.of("reveal", "chip"));
        var actual = new LinkedHashMap<String, List<String>>();
        for (RoleBranch b : CATALOGUE.branches())
            if (b.level() == 2) actual.put(b.name().value(), CATALOGUE.children(b).stream().map(RoleNode::name).map(n -> n.value()).toList());
        assertEquals(expected, actual);
    }

    @Test
    void everyRoleSitsTwoLevelsDown_underSaying_Doing_orShaping() {
        Map<String, Long> perTop = new LinkedHashMap<>();
        for (Role<?> r : CATALOGUE.roles()) {
            RoleBranch branch = CATALOGUE.parent(r);
            assertEquals(2, branch.level(), r + " is filed at level 2");
            perTop.merge(CATALOGUE.parent(branch).name().value(), 1L, Long::sum);
        }
        assertEquals(Map.of("saying", 21L, "doing", 24L, "shaping", 22L), perTop);
    }

    @Test
    void oneWordOneMeaning() {
        assertEquals(List.of(), new CatalogueNames().clashes(CATALOGUE.branches(), CATALOGUE.roles()));
    }

    @Test
    void readWithTheHouse_everyRoleNamedButOne() {
        var findings = HouseTaxonomy.INSTANCE.read().findings();
        assertEquals(1, findings.stream().filter(f -> f.sign() == TaxonomyFinding.Sign.ROLE_UNNAMED).count(),
                "only Host, a floater's - and the floater is not in the first cut");
    }
}
