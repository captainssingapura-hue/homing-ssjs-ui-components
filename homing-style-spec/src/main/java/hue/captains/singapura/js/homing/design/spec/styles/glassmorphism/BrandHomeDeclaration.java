package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** glassmorphism's declaration of brand_home: its own implementation, and a spec for each of its slots. */
public record BrandHomeDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseControls.Brand, HouseDoing.Home> {
    @Override public CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.Brand, HouseDoing.Home> own() { return new BrandHome(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseControls.Brand> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.Brand.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseSaying.Symbol.INSTANCE, new Target.Impl<>(new BrandMarkSymbol())),
                new PartSpec.SameRole<>(HouseControls.Brand.INSTANCE, HouseText.Wordmark.INSTANCE, HouseSaying.Name.INSTANCE, new Target.Impl<>(new WordmarkName()))));
    }
}
