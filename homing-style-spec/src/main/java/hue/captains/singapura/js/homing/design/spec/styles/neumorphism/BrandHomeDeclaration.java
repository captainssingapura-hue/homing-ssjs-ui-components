package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neumorphism's declaration of brand_home: its own implementation, and a spec for each of its slots. */
public record BrandHomeDeclaration() implements CastingDeclaration<BaseScheme, Neumorphism, HouseControls.Brand, HouseDoing.Home> {
    @Override public CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.Brand, HouseDoing.Home> own() { return new BrandHome(); }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseControls.Brand> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.Brand.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseSaying.Symbol.INSTANCE, new Target.Impl<>(new BrandMarkSymbol())),
                new PartSpec.SameRole<>(HouseControls.Brand.INSTANCE, HouseText.Wordmark.INSTANCE, HouseSaying.Name.INSTANCE, new Target.Impl<>(new WordmarkName()))));
    }
}
