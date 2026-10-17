package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import java.util.List;

/** sketchy's look of context-menu: CSS of its own. */
public record ContextMenu() implements CastingStylePerRole<BaseScheme, Sketchy, HouseContainers.ContextMenu, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseContainers.ContextMenu, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border());
    }

    @Override public PartsSpec<BaseScheme, Sketchy, HouseContainers.ContextMenu> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.ContextMenu.INSTANCE, HouseItems.MenuItem.INSTANCE, HouseDoing.Choice.INSTANCE, new MenuItemChoice())));
    }
}
