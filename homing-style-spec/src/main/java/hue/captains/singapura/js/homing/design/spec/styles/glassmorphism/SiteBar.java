package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** glassmorphism's look of site-bar: CSS of its own. */
public record SiteBar() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.SiteBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseContainers.SiteBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Inverted(), new BaseColours.OnBar(), new BaseColours.Accent());
    }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseContainers.SiteBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.SiteBar.INSTANCE, HouseControls.Brand.INSTANCE, HouseDoing.Home.INSTANCE, new BrandHome()),
                new PartSpec.Cast<>(HouseContainers.SiteBar.INSTANCE, HouseContainers.Trail.INSTANCE, HouseDoing.Path.INSTANCE, new TrailPath())));
    }
}
