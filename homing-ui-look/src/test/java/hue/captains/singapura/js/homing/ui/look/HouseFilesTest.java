package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.CheckStyle;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house on files (RFC 0067, appendix Two Classes per Leaf, phase 5): every style's files held by core's check to
 * the leaves on this classpath and to the house's colour tree, Base's included; and the two sheets a page wears, each
 * served by its own slug.
 */
class HouseFilesTest {

    @Test
    void everyHouseStylesFiles_passTheCheck_Bases_included() {
        var styles = new ArrayList<DesignStyle<?>>(HouseStyles.STYLES);
        styles.add(0, Base.INSTANCE);
        List<String> reports = CheckStyle.INSTANCE.require(HouseStyling.STYLING.taxonomy(), HouseColours.TREE, styles);
        assertEquals(List.of(), reports, "every state drawn in its leaf's precedence");
    }

    @Test
    void theStyleSheet_isOneClassPerLeaf_theStylesOwnForTheButtons_BasesElsewhere() {
        String editorial = HouseStyleSheet.INSTANCE.css("editorial");
        assertTrue(editorial.contains("/* plain-button: editorial's own */"), "a button, the style's own");
        assertTrue(editorial.contains("/* knob: base's, editorial answering absence */"), "a leaf it draws not, Base's");
        assertEquals(HouseStyling.STYLING.taxonomy().leaves().size(),
                editorial.lines().filter(l -> l.matches("/\\* [a-z0-9-]+: .*('s own|answering absence) \\*/")).count(), "every leaf once");
        assertTrue(editorial.contains("@property --size"), "the degrees registered");
    }

    @Test
    void thePaletteSheet_isEveryHouseColour_asItsVariable() {
        String harbour = HousePaletteSheet.INSTANCE.css("harbour");
        assertTrue(harbour.contains("    --fp-accent: #f4b942;\n"), harbour);
        assertTrue(harbour.contains("    --fp-ring: "), "a tint, worked out from the seeds");
        assertEquals("editorial", HouseStyleSheet.INSTANCE.fallback().orElseThrow());
        assertEquals("harbour", HousePaletteSheet.INSTANCE.fallback().orElseThrow());
    }
}
