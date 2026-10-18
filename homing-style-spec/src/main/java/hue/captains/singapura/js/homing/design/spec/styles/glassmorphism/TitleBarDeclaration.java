package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** glassmorphism's declaration of title-bar: its own implementation, and a spec for each of its slots. */
public record TitleBarDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseContainers.TitleBar, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseContainers.TitleBar, RoleRoot> own() { return new TitleBar(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseContainers.TitleBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.TitleBar.INSTANCE, HouseText.Label.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new LabelTitle())),
                new PartSpec.SameRole<>(HouseContainers.TitleBar.INSTANCE, HouseControls.CloseButton.INSTANCE, HouseDoing.Close.INSTANCE, new Target.Declared<>(new CloseButtonCloseDeclaration()))));
    }
}
