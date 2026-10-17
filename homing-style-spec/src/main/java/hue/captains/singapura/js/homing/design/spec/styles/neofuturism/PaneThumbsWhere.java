package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** neo-futurism's implementation of pane-thumbs_where: the base's, by delegation. */
public record PaneThumbsWhere() implements CastingStylePerRole<BaseScheme, NeoFuturism, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HousePanes.PaneThumbs, HouseDoing.Where> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbsWhere());
    }
}
