package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * What each icon shows (RFC 0067, D56): every leaf of the Icon branch an icon saying its content, no two the same; a
 * glyph, or an SVG - rotate and flip the samples; a content's name lower-case words.
 */
class IconContentTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES);

    @Test
    void everyIcon_saysWhatItShows_noTwoAlike() {
        List<Taxon> icons = HOUSE.children(HouseBranches.Icon.INSTANCE);
        assertEquals(20, icons.size());
        var names = new HashSet<String>();
        for (Taxon t : icons) {
            assertTrue(t instanceof IconLeaf, t.token() + " is under the Icon branch, and says nothing of what it shows");
            assertTrue(names.add(((IconLeaf) t).content().name()), t.token() + " shows what another icon shows");
        }
        assertEquals(List.of("flip", "rotate"), icons.stream().map(t -> ((IconLeaf) t).content()).filter(c -> c instanceof IconContent.Svg)
                .map(IconContent::name).sorted().toList());
    }

    @Test
    void aContentsName_isLowerCaseWords() {
        assertEquals("split-beside", new IconContent.Glyph("split-beside").name());
        assertThrows(IllegalArgumentException.class, () -> new IconContent.Glyph("Check"));
        assertThrows(IllegalArgumentException.class, () -> new IconContent.Svg("check icon"));
        assertThrows(IllegalArgumentException.class, () -> new IconContent.Svg(""));
    }
}
