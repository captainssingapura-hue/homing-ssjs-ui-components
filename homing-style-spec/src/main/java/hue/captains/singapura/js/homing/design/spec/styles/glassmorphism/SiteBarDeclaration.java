package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

import java.util.List;

/** glassmorphism's declaration of site-bar: its own implementation, and a spec for each of its slots. */
public record SiteBarDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseContainers.SiteBar, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.SiteBar, RoleRoot> own() { return new SiteBar(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseContainers.SiteBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.SiteBar.INSTANCE, HouseControls.Brand.INSTANCE, HouseDoing.Home.INSTANCE, new Target.Declared<>(new BrandHomeDeclaration())),
                new PartSpec.SameRole<>(HouseContainers.SiteBar.INSTANCE, HouseContainers.Trail.INSTANCE, HouseDoing.Path.INSTANCE, new Target.Declared<>(new TrailPathDeclaration()))));
    }
}
