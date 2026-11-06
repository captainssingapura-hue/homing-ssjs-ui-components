package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssGroupDependencies;
import hue.captains.singapura.js.homing.core.CssImportsFor;
import hue.captains.singapura.js.homing.preferences.PreferencesStyles;
import hue.captains.singapura.js.homing.ui.elements.ElementStyles;
import hue.captains.singapura.js.homing.ui.icons.IconStyles;
import hue.captains.singapura.js.homing.ui.menu.MenuStyles;
import hue.captains.singapura.js.homing.ui.panes.PaneStyles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The provided sheets share a layer, so where two of their classes sit on one element and set one property, the sheet
 * applied later wins. Those orders are declared, never left to how a page's imports happen to run: each case here broke
 * a visible behaviour when it came out the other way round.
 */
class ProvidedSheetOrderTest {

    private static void follows(CssGroup<?> sheet, CssGroup<?> first) {
        assertTrue(CssGroupDependencies.INSTANCE.of(sheet).contains(first), sheet.getClass().getSimpleName() + " must load after " + first.getClass().getSimpleName());
    }

    @Test
    void aHiddenMenuIcon_staysHidden() { follows(ElementStyles.INSTANCE, IconStyles.INSTANCE); }

    @Test
    void aHiddenTabMark_staysHidden() { follows(PaneStyles.INSTANCE, IconStyles.INSTANCE); }

    @Test
    void aHiddenMenuRow_staysHidden() { follows(MenuStyles.INSTANCE, ElementStyles.INSTANCE); }

    @Test
    void thePreferencesMargins_holdOverTheTexts() { follows(PreferencesStyles.INSTANCE, ElementStyles.INSTANCE); }
}
