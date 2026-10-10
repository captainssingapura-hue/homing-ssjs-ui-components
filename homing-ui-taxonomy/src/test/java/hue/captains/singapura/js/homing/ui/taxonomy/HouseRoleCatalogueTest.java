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
    void threeTopBranches_eighteenBeneath_ninetyOneRoles() {
        assertEquals(List.of(HouseSaying.Saying.INSTANCE, HouseDoing.Doing.INSTANCE, HouseShaping.Shaping.INSTANCE),
                CATALOGUE.children(RoleRoot.INSTANCE));
        assertEquals(21, CATALOGUE.branches().size());
        assertEquals(18, CATALOGUE.branches().stream().filter(b -> b.level() == 2).count());
        assertEquals(91, CATALOGUE.roles().size());
        assertEquals(91, Set.copyOf(CATALOGUE.roles()).size(), "each listed once");
    }

    @Test
    void eachBranch_holdsTheRolesTheAppendixFilesInIt() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("naming", List.of("title", "subtitle", "name", "category", "symbol"));
        expected.put("describing", List.of("summary", "note", "tag"));
        expected.put("guiding", List.of("hint", "prompt", "placeholder"));
        expected.put("reporting", List.of("value", "level", "count", "status", "error"));
        expected.put("signalling", List.of("keys", "landing", "disclose", "check"));
        expected.put("scaling", List.of("span", "rest", "notch", "figure"));
        expected.put("choosing", List.of("choice", "style", "palette", "what", "how", "where", "picker", "query"));
        expected.put("committing", List.of("go", "cancel", "action", "skip"));
        expected.put("changing", List.of("toggle", "adjust", "thumb", "reset", "increase", "decrease", "clear"));
        expected.put("viewing", List.of("zoom-in", "zoom-out", "fit"));
        expected.put("managing", List.of("close", "add", "remove"));
        expected.put("going", List.of("open", "home", "preferences", "path", "step", "previous", "next", "first", "last", "more"));
        expected.put("bands", List.of("head", "actions", "tabs", "foot"));
        expected.put("layers", List.of("window", "veil", "float", "popup"));
        expected.put("contents", List.of("page", "host", "index", "detail", "specimen", "setting", "layout", "body", "media", "fallback", "editor"));
        expected.put("members", List.of("member", "entry", "dock", "cell", "group", "column"));
        expected.put("dividers", List.of("seam", "break"));
        expected.put("handles", List.of("reveal", "chip", "opener", "pull", "scroll"));
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
        assertEquals(Map.of("saying", 24L, "doing", 35L, "shaping", 32L), perTop);
    }

    @Test
    void oneWordOneMeaning() {
        assertEquals(List.of(), new CatalogueNames().clashes(CATALOGUE.branches(), CATALOGUE.roles()));
    }

    /**
     * Read with the base tree alone, a role only an extension's parts are named in goes unnamed: the
     * catalogue is the house's whole vocabulary, and the whole house names every word of it but
     * one - held by the components conformance, where every extension is.
     */
    @Test
    void readWithTheBase_theWordsOnlyTheExtensionsSay_goUnnamed() {
        var findings = HouseTaxonomy.INSTANCE.read().findings();
        assertEquals(58, findings.stream().filter(f -> f.sign() == TaxonomyFinding.Sign.ROLE_UNNAMED).count(),
                "thirty-three the extensions name, twenty-four no component says yet, and Host - a floater's, not in the first cut");
    }
}
