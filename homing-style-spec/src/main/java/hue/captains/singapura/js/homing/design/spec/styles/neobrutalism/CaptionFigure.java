package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.RootDelegation;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-brutalism's implementation of caption_figure: as its component standalone, by delegation. */
public record CaptionFigure() implements RootDelegation<BaseScheme, NeoBrutalism, HouseText.Caption, HouseSaying.Figure> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseText.Caption, RoleRoot> to() { return new Caption(); }
}
