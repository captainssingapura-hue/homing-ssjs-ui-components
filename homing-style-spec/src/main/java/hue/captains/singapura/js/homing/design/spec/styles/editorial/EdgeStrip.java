package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** editorial's implementation of edge-strip: CSS of its own. */
public record EdgeStrip() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.EdgeStrip, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.ShadeSolid(), new BaseColours.ShadeDense());
    }
}
