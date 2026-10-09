package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.Carrier;
import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.ResolveSemanticClass;
import hue.captains.singapura.js.homing.design.semantic.Resolution;
import hue.captains.singapura.js.homing.design.semantic.SemanticTokens;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * The house's looks in every house style ({@link HouseStyles}), each flattened once: the sheet
 * {@link HouseLookSheet} serves for a theme, and what {@link LookManifestModule} tells the page of
 * them all.
 */
final class HouseLooks {

    private HouseLooks() {}

    static final Taxonomy TAXONOMY = HouseTaxonomy.INSTANCE.read();

    /** Each style's sheet, flattened, by the style's token. */
    static final Map<String, FlattenSheet.Flattened> SHEETS = sheets();

    private static Map<String, FlattenSheet.Flattened> sheets() {
        var out = new LinkedHashMap<String, FlattenSheet.Flattened>();
        HouseStyles.THEMES.forEach((token, theme) -> out.put(token, FlattenSheet.INSTANCE.flatten(TAXONOMY, theme, theme.toString())));
        return java.util.Collections.unmodifiableMap(out);
    }

    /** The sheet for the theme a page is worn in, by its slug. */
    static FlattenSheet.Flattened sheet(String themeSlug) {
        return SHEETS.get(HouseStyles.INSTANCE.dressed(themeSlug).style().token());
    }

    /** Every CSS target, in the tree's order: a look is worn on each. */
    static List<String> targets() {
        return Target.leaves().stream().filter(t -> t.carrier() == Carrier.CSS).map(Target::token).toList();
    }

    /**
     * Every component some style treats, and its treatments - {@code effect-opacity--disabled} -
     * each a class some style's sheet has a rule for. Worn once, so the same in every theme: a
     * treatment the theme worn has no rule for is a class that does nothing.
     */
    static Map<String, List<String>> treated() {
        var tokens = SemanticTokens.INSTANCE;
        var out = new LinkedHashMap<String, List<String>>();
        for (Component<?> leaf : TAXONOMY.components()) {
            var mine = new LinkedHashSet<String>();
            for (State s : State.values()) {
                if (s == State.REST) continue;
                for (Target t : Target.leaves()) {
                    if (t.carrier() != Carrier.CSS || !t.states().contains(s)) continue;
                    for (Dressed theme : HouseStyles.THEMES.values()) {
                        Resolution r = ResolveSemanticClass.INSTANCE.resolve(TAXONOMY, theme, new Casting(leaf), s, t);
                        if (r.outcome() instanceof Resolution.Answered a && !a.body().isBlank()) {
                            mine.add(tokens.target(t) + "--" + tokens.state(s));
                            break;
                        }
                    }
                }
            }
            if (!mine.isEmpty()) out.put(leaf.token(), new ArrayList<>(mine));
        }
        return out;
    }
}
