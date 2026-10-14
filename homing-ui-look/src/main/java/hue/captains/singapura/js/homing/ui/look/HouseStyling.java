package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.List;

/**
 * What the house styles with (RFC 0067, appendix Two Classes per Leaf): its leaves - its own and every extension's on
 * the classpath - its colour tree, its seven styles and its eight palettes, Editorial and Harbour first and so the
 * defaults; read once. The two sheets a page wears are served from it, each by its own slug.
 */
public final class HouseStyling {

    private HouseStyling() {}

    public static final Styling STYLING = new Styling(HouseTaxonomy.INSTANCE.read(), HouseColours.TREE,
            List.<DesignStyle<?>>copyOf(HouseStyles.STYLES), List.<ColorPalette<?>>copyOf(HouseStyles.PALETTES));
}
