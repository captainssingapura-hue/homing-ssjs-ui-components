package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.editorial.EditorialStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.glassmorphism.GlassmorphismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.neobrutalism.NeoBrutalismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.neofuturism.NeoFuturismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.neumorphism.NeumorphismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.retrofuturism.RetroFuturismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.sketchy.SketchyStyleDeclaration;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.StyleRegistration;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.preferences.UiPreferencesStyleDeclaration;
import hue.captains.singapura.js.homing.site.mpa.MpaStyleDeclaration;
import hue.captains.singapura.js.homing.ui.controlpanel.UiControlPanelStyleDeclaration;
import hue.captains.singapura.js.homing.ui.dialog.UiDialogStyleDeclaration;
import hue.captains.singapura.js.homing.ui.elements.UiElementsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingStyleDeclaration;
import hue.captains.singapura.js.homing.ui.icons.UiIconsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.menu.UiMenuStyleDeclaration;
import hue.captains.singapura.js.homing.ui.monitors.UiMonitorsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.panes.UiPanesStyleDeclaration;
import hue.captains.singapura.js.homing.ui.panzoom.UiPanZoomStyleDeclaration;
import hue.captains.singapura.js.homing.ui.splitgrid.UiSplitGridStyleDeclaration;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's styles ready-made (RFC 0067, appendix The Style Jars): the house's own instance, for a site to reuse - both
 * stages of its registration. The first, each style with the jars implementing it: Base with every house jar that brings
 * castings into being, each house style with its own jar. The second, the instance: the seven styles served over Base,
 * Editorial first and so the default; the palettes, every one of the base scheme, so any style may be worn in any of
 * them; and each style's own palette, the one it is worn in unless another is asked for, chosen by hand (appendix
 * Semantic Colours and Flavours, D22) - a pairing neither a style nor a palette may know.
 *
 * <p>A site whose own jars bring castings of their own, or implement a style for the house's components, hands their
 * declarations in ({@link #styling(List)}): each joins the registration of every style it provides for. A site hands
 * the styling to its MPA, which installs it at bootstrap.</p>
 */
public record HouseStyles() {

    public static final HouseStyles INSTANCE = new HouseStyles();

    /** Base's implementations: every house jar that brings castings into being, each declaring Base's for them. */
    public static final List<StyleDeclaration> BASE_JARS = List.of(
            UiElementsStyleDeclaration.INSTANCE, UiIconsStyleDeclaration.INSTANCE, UiDialogStyleDeclaration.INSTANCE,
            UiFloatingStyleDeclaration.INSTANCE, UiMenuStyleDeclaration.INSTANCE, UiMonitorsStyleDeclaration.INSTANCE,
            UiPanesStyleDeclaration.INSTANCE, UiPanZoomStyleDeclaration.INSTANCE, UiSplitGridStyleDeclaration.INSTANCE,
            UiPreferencesStyleDeclaration.INSTANCE, MpaStyleDeclaration.INSTANCE, UiControlPanelStyleDeclaration.INSTANCE);

    /** The styles, the house's own first: it is the default. */
    public static final List<DesignStyle<ColorScheme.Base>> STYLES = List.of(
            Editorial.INSTANCE, NeoBrutalism.INSTANCE, NeoFuturism.INSTANCE, Neumorphism.INSTANCE,
            Glassmorphism.INSTANCE, RetroFuturism.INSTANCE, Sketchy.INSTANCE);

    /** Each style's own jar. */
    public static final Map<DesignStyle<ColorScheme.Base>, StyleDeclaration> STYLE_JARS = Map.of(
            Editorial.INSTANCE, EditorialStyleDeclaration.INSTANCE,
            NeoBrutalism.INSTANCE, NeoBrutalismStyleDeclaration.INSTANCE,
            NeoFuturism.INSTANCE, NeoFuturismStyleDeclaration.INSTANCE,
            Neumorphism.INSTANCE, NeumorphismStyleDeclaration.INSTANCE,
            Glassmorphism.INSTANCE, GlassmorphismStyleDeclaration.INSTANCE,
            RetroFuturism.INSTANCE, RetroFuturismStyleDeclaration.INSTANCE,
            Sketchy.INSTANCE, SketchyStyleDeclaration.INSTANCE);

    /** Each style's own palette, the one it is worn in unless another is asked for - the old designs' own colours. */
    public static final Map<DesignStyle<ColorScheme.Base>, ColorPalette<ColorScheme.Base>> OWN = Map.of(
            Editorial.INSTANCE, Harbour.INSTANCE,
            NeoBrutalism.INSTANCE, NeoBrutalismColours.INSTANCE,
            NeoFuturism.INSTANCE, NeoFuturismColours.INSTANCE,
            Neumorphism.INSTANCE, Clay.INSTANCE,
            Glassmorphism.INSTANCE, Frost.INSTANCE,
            RetroFuturism.INSTANCE, Synthwave.INSTANCE,
            Sketchy.INSTANCE, Marker.INSTANCE);

    /** The palettes: the styles' own in the styles' order, then those that are no style's own. */
    public static final List<ColorPalette<ColorScheme.Base>> PALETTES = List.of(
            Harbour.INSTANCE, NeoBrutalismColours.INSTANCE, NeoFuturismColours.INSTANCE, Clay.INSTANCE,
            Frost.INSTANCE, Synthwave.INSTANCE, Marker.INSTANCE, Forest.INSTANCE);

    /** The house's own instance, nothing a site adds: registered the first time it is asked for, and the same after. */
    public Styling styling() { return House.STYLING; }

    /** The house's instance with a site's own jars: each joins the registration of every style it provides for. */
    public Styling styling(List<StyleDeclaration> more) {
        var registrations = new ArrayList<StyleRegistration>();
        registrations.add(registration(Base.INSTANCE, BASE_JARS, more));
        for (DesignStyle<ColorScheme.Base> s : STYLES) registrations.add(registration(s, List.of(STYLE_JARS.get(s)), more));
        StyleManager manager = StyleManager.of(HouseTaxonomy.INSTANCE.read(), HouseColours.TREE, registrations);
        return new Styling(manager, List.copyOf(STYLES), List.copyOf(PALETTES), new LinkedHashMap<DesignStyle<?>, ColorPalette<?>>(OWN));
    }

    private static StyleRegistration registration(DesignStyle<?> style, List<StyleDeclaration> jars, List<StyleDeclaration> more) {
        var all = new ArrayList<>(jars);
        for (StyleDeclaration d : more)
            if (d.provided().stream().anyMatch(p -> p.style().getClass() == style.getClass())) all.add(d);
        return new StyleRegistration(style, all);
    }

    /** The house's own instance, registered once. */
    private static final class House {
        private House() {}
        static final Styling STYLING = INSTANCE.styling(List.of());
    }
}
