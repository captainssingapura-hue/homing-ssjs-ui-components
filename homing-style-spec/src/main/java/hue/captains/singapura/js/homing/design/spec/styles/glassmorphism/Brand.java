package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** glassmorphism's look of brand: CSS of its own. */
public record Brand() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.Brand, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.Brand, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseControls.Brand> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Brand.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseSaying.Symbol.INSTANCE, new BrandMarkSymbol()),
                new PartSpec.Cast<>(HouseControls.Brand.INSTANCE, HouseText.Wordmark.INSTANCE, HouseSaying.Name.INSTANCE, new WordmarkName())));
    }
}
