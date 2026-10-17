package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** neo-brutalism's look of floating-pane: CSS of its own. */
public record FloatingPane() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.FloatingPane, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Inverted(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Ring());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.FloatingPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseContainers.FloatingPane.INSTANCE, HouseContainers.TitleBar.INSTANCE, HouseShaping.Head.INSTANCE, new TitleBar())));
    }
}
