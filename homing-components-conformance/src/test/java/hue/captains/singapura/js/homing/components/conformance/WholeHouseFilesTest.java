package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.CheckStyle;
import hue.captains.singapura.js.homing.design.styling.StyleFiles;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The whole house on files (RFC 0067, appendix Two Classes per Leaf, phase 5): with every module that adds leaves on
 * this classpath, every house style's files - Base's for every leaf, each module's given from its own jar (D26) - held
 * by core's check to the leaves and to the house's colour tree, every problem at once.
 */
class WholeHouseFilesTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    @Test
    void everyHouseStylesFiles_passTheCheck_overTheWholeHouse() {
        var styles = new ArrayList<DesignStyle<?>>(HouseStyles.STYLES);
        styles.add(0, Base.INSTANCE);
        assertEquals(List.of(), CheckStyle.INSTANCE.require(HOUSE, HouseColours.TREE, styles), "every state drawn in its leaf's precedence");
    }

    @Test
    void baseHasAFileForEveryLeaf_fromTheModuleThatAddsIt() {
        assertEquals(HOUSE.castings().size(), StyleFiles.INSTANCE.present(Base.INSTANCE).size(),
                "one file for each of the whole house's castings, the house's own and every extension's, across the jars");
        assertEquals(164, HOUSE.castings().size(), "105 components on their own, 59 in a role");
    }
}
