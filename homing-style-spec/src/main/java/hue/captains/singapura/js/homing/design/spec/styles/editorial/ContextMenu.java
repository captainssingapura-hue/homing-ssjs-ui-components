package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import java.util.List;

/** editorial's look of context-menu: CSS of its own. */
public record ContextMenu() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.ContextMenu, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.ContextMenu, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.ShadeSolid(), new BaseColours.ShadeDense());
    }

    @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.ContextMenu> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.ContextMenu.INSTANCE, HouseItems.MenuItem.INSTANCE, HouseDoing.Choice.INSTANCE, new MenuItemChoice())));
    }
}
