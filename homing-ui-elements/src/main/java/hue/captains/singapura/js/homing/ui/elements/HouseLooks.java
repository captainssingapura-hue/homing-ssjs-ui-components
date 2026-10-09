package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.DeriveTreatments;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.SemanticTokens;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's looks in every house style and palette ({@link HouseStyles}), each flattened once: the sheet
 * {@link HouseLookSheet} serves for a theme, and what {@link LookManifestModule} tells the page of
 * them all.
 */
final class HouseLooks {

    private HouseLooks() {}

    static final Taxonomy TAXONOMY = HouseTaxonomy.INSTANCE.read();

    /** Each theme's sheet, flattened the first time it is asked for, by the theme's slug. */
    private static final Map<String, FlattenSheet.Flattened> SHEETS = new java.util.concurrent.ConcurrentHashMap<>();

    /** The sheet for the theme a page is worn in, by its slug: a house style in a house palette. */
    static FlattenSheet.Flattened sheet(String themeSlug) {
        Dressed theme = HouseStyles.INSTANCE.dressed(themeSlug);
        return SHEETS.computeIfAbsent(HouseStyles.INSTANCE.slug(theme), s -> FlattenSheet.INSTANCE.flatten(TAXONOMY, theme, theme.toString()));
    }

    /** Every CSS target the semantic classes answer, in the tree's order: a look is worn on each. */
    static List<String> targets() {
        return SemanticTokens.INSTANCE.cssLeaves().stream().map(Target::token).toList();
    }

    /**
     * Every component some theme treats, and its treatments - {@code effect-opacity--disabled} -
     * each a class some theme's sheet has a rule for, read from the answers ({@link DeriveTreatments})
     * once: the styles and palettes are the house's constants. Worn once, so the same in every theme:
     * a treatment the theme worn has no rule for is a class that does nothing.
     */
    static Map<String, List<String>> treated() { return TREATED; }

    private static final Map<String, List<String>> TREATED = derive();

    private static Map<String, List<String>> derive() {
        var tokens = SemanticTokens.INSTANCE;
        var derive = DeriveTreatments.INSTANCE;
        var out = new LinkedHashMap<String, List<String>>();
        derive.byComponent(TAXONOMY, derive.union(TAXONOMY, HouseStyles.THEMES.values())).forEach((leaf, mine) ->
                out.put(leaf.token(), mine.stream().map(t -> tokens.target(t.target()) + "--" + tokens.state(t.state())).toList()));
        return java.util.Collections.unmodifiableMap(out);
    }
}
