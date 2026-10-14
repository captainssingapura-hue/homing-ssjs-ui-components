package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.component.taxonomy.Design;
import hue.captains.singapura.js.homing.design.semantic.DesignObject;
import hue.captains.singapura.js.homing.design.semantic.DesignRead;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.semantic.FlattenDesign;
import hue.captains.singapura.js.homing.design.semantic.ReadDesigns;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The designs of the house's design branches, as a page has them (RFC 0067, appendix Virtual Leaves and Designs):
 * every design on the classpath, read once over the house's taxonomy and judged in every house theme, and for each
 * what its module serves - its sheet in the theme a page is worn in, flattened the first time it is asked for, and
 * its object. A design that is not complete in every house theme refuses them all, as a refused taxonomy does.
 */
public record HouseDesigns() implements StatelessFunctionalObject {

    public static final HouseDesigns INSTANCE = new HouseDesigns();

    /** Every design on the classpath of the house's branches. */
    public List<DesignRead> all() { return Read.ALL; }

    /** A design as read. */
    public DesignRead of(Design<?, ?> design) {
        for (DesignRead d : Read.ALL) if (d.design().equals(design)) return d;
        throw new IllegalArgumentException(design.token() + " is no design on the classpath of the house's branches");
    }

    /** A design's sheet in the theme a page is worn in, by its slug. */
    public String sheet(Design<?, ?> design, String themeSlug) {
        Dressed theme = HouseStyles.INSTANCE.dressed(themeSlug);
        String slug = HouseStyles.INSTANCE.slug(theme);
        return SHEETS.computeIfAbsent(design.token() + "/" + slug,
                k -> FlattenDesign.INSTANCE.flatten(of(design), theme, design.token() + " in " + slug).css());
    }

    /** A design's object, its first line naming its sheet by the name it is imported by. */
    public List<String> object(Design<?, ?> design, String sheet) { return DesignObject.INSTANCE.lines(of(design), sheet); }

    /** Each design's sheet in each theme, flattened the first time it is asked for. */
    private static final Map<String, String> SHEETS = new ConcurrentHashMap<>();

    /** The designs, read the first time any is asked for. */
    private static final class Read {
        private Read() {}
        static final List<DesignRead> ALL =
                ReadDesigns.INSTANCE.read(HouseStyling.STYLING.taxonomy(), HouseStyles.THEMES.values(), HouseDesigns.class.getClassLoader());
    }
}
