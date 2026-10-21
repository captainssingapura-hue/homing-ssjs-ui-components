package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The icons a page may show are the taxonomy's (RFC 0067, D52): every leaf of the Icon branch, in the order read - the
 * house's sixteen, then the panes' five where their module is registered. The table itself is made from the installed
 * instance's taxonomy, and held where an instance is installed (the icons' jar's IconTest).
 */
class IconsTableTest {

    @Test
    void theIcons_everyLeafOfTheIconBranch_inTheOrderRead() {
        List<Component<?>> icons = IconsModule.Icons.INSTANCE.of(HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES));
        assertEquals(21, icons.size(), "the house's sixteen - the pacman among them (21 Oct) - and the panes' five");
        assertEquals(HouseIcons.AddIcon.INSTANCE, icons.get(0));
        assertTrue(icons.contains(HouseIcons.WithinIcon.INSTANCE));
        assertEquals(List.of(), IconsModule.INSTANCE.imports().getAllImports().keySet().stream().toList(), "the table imports nothing: the pictures are the icons' looks");
    }
}
