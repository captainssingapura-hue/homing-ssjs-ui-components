package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.CheckScheme;
import hue.captains.singapura.js.homing.design.semantic.SchemeRegistry;
import hue.captains.singapura.js.homing.design.styling.PaletteRegistry;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This jar held to its own declaration alone (RFC 0067, D45): every palette it declares of the base scheme, each whole
 * over the base's colours, sitting on nothing, no two of one name - whatever instance registers it.
 */
class BasePaletteDeclarationTest {

    @Test
    void everyPaletteDeclared_isWholeOverTheBase_andOfOneName() {
        List<? extends ColorPalette<?>> palettes = BasePaletteDeclaration.INSTANCE.palettes();
        assertEquals(8, palettes.size());
        assertEquals("harbour", palettes.get(0).token(), "the house's own palette first: the default");
        var names = new HashSet<String>();
        for (ColorPalette<?> p : palettes) {
            assertTrue(names.add(p.token()), p.token() + " declared twice");
            assertEquals(List.of(), BaseColours.TREE.check(p), p.token() + ": whole over the base, sitting on nothing");
            assertEquals(List.of(), CheckScheme.INSTANCE.says(p), p.token() + ": saying the scheme its type names (D49)");
        }
    }

    @Test
    void theJar_aSubRegistryOfItsOwn_overTheRegisteredBase() {
        PaletteRegistry registry = PaletteRegistry.of(List.of(BasePaletteDeclaration.INSTANCE), SchemeRegistry.of(List.of(BaseColours.INSTANCE)));
        assertEquals(List.of(BaseScheme.class), List.copyOf(registry.trees().keySet()), "BaseScheme, declared by BaseColours (D49)");
        assertEquals(BaseColours.TREE.nodes(), registry.tree(Harbour.INSTANCE).nodes());
    }
}
