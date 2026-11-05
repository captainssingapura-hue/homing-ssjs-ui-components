package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ExternalModule;
import hue.captains.singapura.js.homing.core.ModuleNameResolver;
import hue.captains.singapura.js.homing.core.SelfContent;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.server.CssContentGetAction;
import hue.captains.singapura.js.homing.server.CssQuery;
import hue.captains.singapura.js.homing.server.EsModuleGetAction;
import hue.captains.singapura.js.homing.server.ThemeRegistry;
import hue.captains.singapura.js.homing.site.Router;
import hue.captains.singapura.js.homing.site.Site;
import hue.captains.singapura.js.homing.site.mpa.Brand;
import hue.captains.singapura.js.homing.site.mpa.StandardMpa;
import hue.captains.singapura.js.homing.site.mpa.StyleFamiliesGetAction;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.HousePaletteSheet;
import hue.captains.singapura.js.homing.ui.look.HouseStyleSheet;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A site hands the styling it was built with to the server: what is made from it is served through the site's own
 * routes, with nothing else to set up; a site built without one has no such route, and refuses such a module by name.
 */
class StandardMpaServesItsStylingTest {

    private static final Styling STYLING = HouseStyles.INSTANCE.styling();

    record Library() implements ExternalModule<Library>, SelfContent {
        @Override public ExportsOf<Library> exports() { return new ExportsOf<>(this, List.of()); }
        @Override public List<String> selfContent(ModuleNameResolver names) { return List.of(); }
    }

    record Look() implements Crate {
        @Override public String name() { return "look-served"; }
        @Override public List<CrateEntry> entries() {
            return List.of(new CrateEntry(CastingsModule.INSTANCE), new CrateEntry(HouseStyleSheet.INSTANCE), new CrateEntry(HousePaletteSheet.INSTANCE));
        }
    }

    private static final Site SITE = new Site() {
        @Override public String name() { return "styled-test"; }
        @Override public Router router() { return path -> Optional.empty(); }
    };

    private static String module(StandardMpa mpa, EsModule<?> module) {
        return ((EsModuleGetAction) mpa.registry(SITE).getActions().get("/module")).render(module);
    }

    private static String sheet(StandardMpa mpa, Class<?> sheet, String style, String palette) throws Exception {
        return ((CssContentGetAction) mpa.registry(SITE).getActions().get("/css-content"))
                .execute(new CssQuery(sheet.getCanonicalName(), null, style, palette), null).get().body();
    }

    private static StandardMpa styled() { return new StandardMpa(new Brand("Styled"), STYLING, new Look()); }

    @Test
    void aModuleMadeFromTheStyling_isServed() {
        assertTrue(module(styled(), CastingsModule.INSTANCE).contains("const CASTINGS = "));
    }

    @Test
    void theSheets_areServedUnderWhatTheStylingSays_namingNothing() throws Exception {
        assertEquals(STYLING.styleSheet(STYLING.defaultStyle().token()), sheet(styled(), HouseStyleSheet.class, null, null));
        assertEquals(STYLING.paletteSheet(STYLING.defaultPalette().token()), sheet(styled(), HousePaletteSheet.class, null, null));
    }

    @Test
    void aSheetsModule_handsTheOwnerTheStylingsDefault() {
        assertTrue(module(styled(), HouseStyleSheet.INSTANCE).contains("\"" + STYLING.defaultStyle().token() + "\", \"style\""));
    }

    @Test
    void aSiteWithAStyling_servesItsFamilyFiles_onlyIt() {
        assertTrue(styled().registry(SITE).getActions().containsKey(StyleFamiliesGetAction.ROUTE));
        var themed = new StandardMpa(new Brand("Themed"), ThemeRegistry.EMPTY, new Look());
        assertFalse(themed.registry(SITE).getActions().containsKey(StyleFamiliesGetAction.ROUTE));
    }

    @Test
    void aSiteWithNoStyling_refusesWhatIsMadeFromOne() {
        var themed = new StandardMpa(new Brand("Themed"), ThemeRegistry.EMPTY, new Look());
        var refused = assertThrows(IllegalStateException.class, () -> module(themed, CastingsModule.INSTANCE));
        assertTrue(refused.getMessage().contains(CastingsModule.class.getName()), refused.getMessage());
    }

    @Test
    void aSiteRebuiltForALibrary_keepsItsStyling() {
        var moved = styled().withLibraryAt(Library.class, "https://mirror.internal/mermaid.mjs");
        assertTrue(module(moved, CastingsModule.INSTANCE).contains("const CASTINGS = "));
        assertTrue(moved.registry(SITE).getActions().containsKey(StyleFamiliesGetAction.ROUTE));
    }
}
