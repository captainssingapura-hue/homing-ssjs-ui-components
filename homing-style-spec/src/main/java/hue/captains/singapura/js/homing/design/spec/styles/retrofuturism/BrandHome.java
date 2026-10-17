package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** retro-futurism's look of brand_home: as its component on its own, by delegation. */
public record BrandHome() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.Brand, HouseDoing.Home> style() {
        return new StylingRes.RootDelegation<>(new Brand());
    }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseControls.Brand> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Brand.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseSaying.Symbol.INSTANCE, new BrandMarkSymbol()),
                new PartSpec.Cast<>(HouseControls.Brand.INSTANCE, HouseText.Wordmark.INSTANCE, HouseSaying.Name.INSTANCE, new WordmarkName())));
    }
}
