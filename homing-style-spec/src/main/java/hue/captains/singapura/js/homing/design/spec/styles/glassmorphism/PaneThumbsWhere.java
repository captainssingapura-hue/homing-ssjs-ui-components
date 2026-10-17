package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/** glassmorphism's implementation of pane-thumbs_where: the base's, by delegation. */
public record PaneThumbsWhere() implements CastingStylePerRole<BaseScheme, Glassmorphism, HousePanes.PaneThumbs, HouseDoing.Where> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HousePanes.PaneThumbs, HouseDoing.Where> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbsWhere());
    }
}
