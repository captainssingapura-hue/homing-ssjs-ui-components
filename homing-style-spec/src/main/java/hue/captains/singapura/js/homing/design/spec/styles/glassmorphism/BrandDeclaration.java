package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** glassmorphism's declaration of brand: its own implementation, and a spec for each of its slots. */
public record BrandDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseControls.Brand, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseControls.Brand, RoleRoot> own() { return new Brand(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseControls.Brand> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.Brand.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseSaying.Symbol.INSTANCE, new Target.Impl<>(new BrandMarkSymbol())),
                new PartSpec.SameRole<>(HouseControls.Brand.INSTANCE, HouseText.Wordmark.INSTANCE, HouseSaying.Name.INSTANCE, new Target.Impl<>(new WordmarkName()))));
    }
}
