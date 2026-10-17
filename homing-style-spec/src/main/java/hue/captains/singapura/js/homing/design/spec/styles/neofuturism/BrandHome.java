package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neo-futurism's look of brand_home: CSS of its own. */
public record BrandHome() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Brand, HouseDoing.Home> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Brand, HouseDoing.Home> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseControls.Brand> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Brand.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseSaying.Symbol.INSTANCE, new BrandMarkSymbol()),
                new PartSpec.Cast<>(HouseControls.Brand.INSTANCE, HouseText.Wordmark.INSTANCE, HouseSaying.Name.INSTANCE, new WordmarkName())));
    }
}
