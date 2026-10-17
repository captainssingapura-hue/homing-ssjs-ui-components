package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** retro-futurism's declaration of preference-field: its own implementation, and a spec for each of its slots. */
public record PreferenceFieldDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HousePreferences.PreferenceField, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, RetroFuturism, HousePreferences.PreferenceField, RoleRoot> own() { return new PreferenceField(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HousePreferences.PreferenceField> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Category.INSTANCE, new Target.Impl<>(new KickerCategory())),
                new PartSpec.SameRole<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new Heading4Title())),
                new PartSpec.SameRole<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Lede.INSTANCE, HouseSaying.Summary.INSTANCE, new Target.Impl<>(new LedeSummary())),
                new PartSpec.SameRole<>(HousePreferences.PreferenceField.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new Target.Impl<>(new CaptionNote())),
                new PartSpec.SameRole<>(HousePreferences.PreferenceField.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Reset.INSTANCE, new Target.Impl<>(new PlainButtonReset()))));
    }
}
