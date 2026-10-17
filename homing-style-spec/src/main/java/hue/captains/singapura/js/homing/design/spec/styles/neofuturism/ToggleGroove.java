package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** neo-futurism's implementation of toggle-groove: CSS of its own. */
public record ToggleGroove() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseMarks.ToggleGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseMarks.ToggleGroove, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Accent());
    }
}
