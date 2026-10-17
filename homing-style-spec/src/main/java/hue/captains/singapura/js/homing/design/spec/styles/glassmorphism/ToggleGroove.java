package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** glassmorphism's look of toggle-groove: CSS of its own. */
public record ToggleGroove() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseMarks.ToggleGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseMarks.ToggleGroove, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Accent());
    }
}
