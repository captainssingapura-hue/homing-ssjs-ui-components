package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.styles.PaletteDeclaration;

import java.util.List;

/**
 * This jar's palettes, declared (RFC 0067, D45): the house's eight, all of the base scheme - the styles' own in the
 * styles' order, Harbour first and so the default, then those that are no style's own. An instance registers this
 * declaration; nothing finds the palettes on the classpath.
 */
public record BasePaletteDeclaration() implements PaletteDeclaration {

    public static final BasePaletteDeclaration INSTANCE = new BasePaletteDeclaration();

    @Override public List<? extends ColorPalette<?>> palettes() {
        return List.of(Harbour.INSTANCE, NeoBrutalismColours.INSTANCE, NeoFuturismColours.INSTANCE, Clay.INSTANCE,
                       Frost.INSTANCE, Synthwave.INSTANCE, Marker.INSTANCE, Forest.INSTANCE);
    }
}
