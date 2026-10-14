package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.design.semantic.ColorImports;
import hue.captains.singapura.js.homing.design.semantic.ColorImportsExtension;
import hue.captains.singapura.js.homing.design.semantic.StyleImports;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Accent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Border;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysCandidate;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysLent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.LineHovered;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/**
 * The colours each design this module gives the house's styles imports, for its own files - {@code <family>.splitgrid.css}
 * (RFC 0067, appendix Two Classes per Leaf, D32): every colour of the tree each component's rules use there, style by
 * style, and no other.
 */
public record SplitGridColorImports() implements ColorImportsExtension {

    @Override public String module() { return "splitgrid"; }

    @Override public List<StyleImports<?>> imports() { return IMPORTS; }

    private static final List<StyleImports<?>> IMPORTS = List.of(
            new StyleImports<>(Base.class,
                    // mark
                    new ColorImports<>(HouseMarks.BoundaryLine.INSTANCE, new Border(), new LineHovered(), new Accent()),
                    // selector
                    new ColorImports<>(HouseSplitGrid.SplitGridMirror.INSTANCE, new Accent(), new KeysCandidate(), new KeysLent())));
}
