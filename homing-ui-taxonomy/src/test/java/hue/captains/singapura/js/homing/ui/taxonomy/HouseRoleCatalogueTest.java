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
    void threeTopBranches_nineteenBeneath_ninetyFiveRoles() {
        assertEquals(List.of(HouseSaying.Saying.INSTANCE, HouseDoing.Doing.INSTANCE, HouseShaping.Shaping.INSTANCE),
                CATALOGUE.children(RoleRoot.INSTANCE));
        assertEquals(22, CATALOGUE.branches().size());
        assertEquals(19, CATALOGUE.branches().stream().filter(b -> b.level() == 2).count());
        assertEquals(95, CATALOGUE.roles().size());
        assertEquals(95, Set.copyOf(CATALOGUE.roles()).size(), "each listed once");
    }

    @Test
    void eachBranch_holdsTheRolesTheAppendixFilesInIt() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("naming", List.of("title", "subtitle", "name", "category", "symbol"));
        expected.put("describing", List.of("summary", "note", "tag"));
        expected.put("guiding", List.of("hint", "prompt", "placeholder"));
        expected.put("reporting", List.of("value", "level", "count", "status", "error"));
        expected.put("signalling", List.of("landing", "check"));
        expected.put("scaling", List.of("span", "rest", "notch", "figure"));
        expected.put("choosing", List.of("choice", "style", "palette", "what", "how", "where", "picker", "query"));
        expected.put("committing", List.of("go", "cancel", "action", "skip"));
        expected.put("changing", List.of("toggle", "adjust", "thumb", "splitter", "reset", "increase", "decrease", "clear"));
        expected.put("viewing", List.of("zoom-in", "zoom-out", "fit"));
        expected.put("managing", List.of("close", "add", "remove"));
        expected.put("going", List.of("open", "home", "preferences", "path", "step", "previous", "next", "first", "last", "more"));
        expected.put("bands", List.of("head", "actions", "tabs", "foot"));
        expected.put("layers", List.of("window", "veil", "float", "popup"));
        expected.put("contents", List.of("page", "host", "index", "detail", "specimen", "setting", "layout", "body", "media", "fallback", "editor"));
        expected.put("members", List.of("member", "entry", "dock", "cell", "group", "column"));
        expected.put("dividers", List.of("seam", "break"));
        expected.put("handles", List.of("reveal", "chip", "opener", "pull", "scroll"));
        expected.put("picturing", List.of("face", "leading", "measure", "keys", "disclose"));   // where an icon sits on its owner (D52)
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
        assertEquals(Map.of("saying", 22L, "doing", 36L, "shaping", 37L), perTop);
    }

    @Test
    void oneWordOneMeaning() {
        assertEquals(List.of(), new CatalogueNames().clashes(CATALOGUE.branches(), CATALOGUE.roles()));
    }

    /**
     * The catalogue is the house's whole vocabulary, ahead of its components: read with every component of the house -
     * the modules' defined here too (D40) - the words no component says yet are these, each named by none.
     */
    @Test
    void theWholeHouse_leavesTheseWordsUnnamed_theCatalogueAheadOfIt() {
        var unnamed = HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES).findings().stream().filter(f -> f.sign() == TaxonomyFinding.Sign.ROLE_UNNAMED)
                .map(f -> f.says().replace(" is named by no component", "")).toList();
        assertEquals(List.of("Subtitle", "Category", "Placeholder", "Status", "Error", "Check", "Style", "Palette", "Query", "Skip", "Toggle", "Adjust",
                "Reset", "Increase", "Decrease", "Clear", "Remove", "Preferences", "Previous", "Next", "First", "Last", "More", "Foot", "Page", "Host",
                "Index", "Detail", "Setting", "Layout", "Body", "Media", "Fallback", "Member", "Entry", "Dock", "Group", "Opener", "Pull", "Scroll"), unnamed,
                "a strip's foot, a site's page, a view's index and detail unnamed since the rooms left the parts; the words only widgets said - a subtitle, a status, a setting, an entry, a member and the rest - since the widgets are no components (20 Oct)");
    }
}
