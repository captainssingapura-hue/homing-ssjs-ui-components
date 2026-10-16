package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.SchemeDeclaration;
import hue.captains.singapura.js.homing.design.semantic.SchemeRegistry;
import hue.captains.singapura.js.homing.design.styles.PaletteDeclaration;
import hue.captains.singapura.js.homing.design.styling.PaletteRegistry;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.StyleRegistryBuilder;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.design.styling.StylingInstance;
import hue.captains.singapura.js.homing.ui.modules.HouseModules;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's styles ready-made (RFC 0067, appendix The Style Jars): the house's own instance, for a site to reuse - both
 * stages of its registration. The first, its styles, built in stages (D50): the house's base modules - each component
 * module with its components' looks in Base - then each house style over Base with its module, all as the house's
 * collector gathers them ({@link HouseModules}). The second, the instance: the seven styles served over Base,
 * Editorial first and so the default; the palettes, every one of the base scheme, so any style may be worn in any of
 * them; and each style's own palette, the one it is worn in unless another is asked for, chosen by hand (appendix
 * Semantic Colours and Flavours, D22) - a pairing neither a style nor a palette may know. Its schemes are registered
 * once, each by its declaration - the base's, BaseColours (D49) - and the palettes are the instance's registry of them,
 * apart from the styles, which care nothing for colours. A site hands the styling to its MPA, which installs it at
 * bootstrap.
 *
 * <p>Its jar carries the house's family files, for reading: written when it is built (WriteStyleFamilies), served by a
 * deployed site from the classpath (StyleFamilies).</p>
 */
public record HouseStyles() implements StylingInstance {

    public static final HouseStyles INSTANCE = new HouseStyles();

    /** The styles served, the house's own first: it is the default. */
    public static final List<DesignStyle<BaseScheme>> STYLES = List.of(
            Editorial.INSTANCE, NeoBrutalism.INSTANCE, NeoFuturism.INSTANCE, Neumorphism.INSTANCE,
            Glassmorphism.INSTANCE, RetroFuturism.INSTANCE, Sketchy.INSTANCE);

    /** Each style's own palette, the one it is worn in unless another is asked for - the old designs' own colours. */
    public static final Map<DesignStyle<BaseScheme>, ColorPalette<BaseScheme>> OWN = Map.of(
            Editorial.INSTANCE, Harbour.INSTANCE,
            NeoBrutalism.INSTANCE, NeoBrutalismColours.INSTANCE,
            NeoFuturism.INSTANCE, NeoFuturismColours.INSTANCE,
            Neumorphism.INSTANCE, Clay.INSTANCE,
            Glassmorphism.INSTANCE, Frost.INSTANCE,
            RetroFuturism.INSTANCE, Synthwave.INSTANCE,
            Sketchy.INSTANCE, Marker.INSTANCE);

    /** The house's schemes, each by its declaration, registered once: the base scheme alone (D49). */
    public static final List<SchemeDeclaration<?>> SCHEME_JARS = List.of(BaseColours.INSTANCE);

    /** The house's jars of palettes, each by its declaration: the base scheme's eight, Harbour first and so the default. */
    public static final List<PaletteDeclaration> PALETTE_JARS = List.of(BasePaletteDeclaration.INSTANCE);

    /** The house's own instance: registered the first time it is asked for, and the same after. */
    @Override public Styling styling() { return House.STYLING; }

    /** The house's styles: its base modules with its words, then each house style over Base with its module. */
    private StyleManager styles() {
        var modules = HouseModules.INSTANCE;
        return new StyleRegistryBuilder<>(modules.base(), modules.roles(), modules.states(), modules.events())
                .style(Editorial.INSTANCE, modules.style(Editorial.INSTANCE))
                .style(NeoBrutalism.INSTANCE, modules.style(NeoBrutalism.INSTANCE))
                .style(NeoFuturism.INSTANCE, modules.style(NeoFuturism.INSTANCE))
                .style(Neumorphism.INSTANCE, modules.style(Neumorphism.INSTANCE))
                .style(Glassmorphism.INSTANCE, modules.style(Glassmorphism.INSTANCE))
                .style(RetroFuturism.INSTANCE, modules.style(RetroFuturism.INSTANCE))
                .style(Sketchy.INSTANCE, modules.style(Sketchy.INSTANCE))
                .build();
    }

    /** The instance: the styles served over the house's styles, the house's palettes over its schemes, each style's own. */
    private Styling register() {
        SchemeRegistry schemes = new SchemeRegistry(SCHEME_JARS);
        return new Styling(styles(), List.copyOf(STYLES), new PaletteRegistry(PALETTE_JARS, schemes), new LinkedHashMap<DesignStyle<?>, ColorPalette<?>>(OWN));
    }

    /** The house's own instance, registered once. */
    private static final class House {
        private House() {}
        static final Styling STYLING = INSTANCE.register();
    }
}
