package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

/**
 * The grid's mirror in the base layer: its shape drawn small, a control holding the keys - its cells the panes'
 * thumbnails; its keys' marks as every holder of the keys draws them, every style's alike. Whole: every property its
 * look has (D9).
 */
public record SplitGridMirror()
        implements CastingStyle<BaseScheme, Base, HouseSplitGrid.SplitGridMirror, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseSplitGrid.SplitGridMirror> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Accent(), new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
