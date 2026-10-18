package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

import java.util.List;

/** sketchy's declaration of add-tab: its own implementation, and a spec for each of its slots. */
public record AddTabDeclaration() implements CastingDeclaration<BaseScheme, Sketchy, HousePanes.AddTab, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Sketchy, HousePanes.AddTab, RoleRoot> own() { return new AddTab(); }

    @Override public PartsSpec<BaseScheme, Sketchy, HousePanes.AddTab> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePanes.AddTab.INSTANCE, HousePanes.PaneThumbs.INSTANCE, HouseDoing.Where.INSTANCE, new Target.Declared<>(new PaneThumbsWhereDeclaration())),
                new PartSpec.SameRole<>(HousePanes.AddTab.INSTANCE, HouseControls.Select.INSTANCE, HouseDoing.What.INSTANCE, new Target.Impl<>(new SelectWhat())),
                new PartSpec.SameRole<>(HousePanes.AddTab.INSTANCE, HouseControls.Select.INSTANCE, HouseDoing.How.INSTANCE, new Target.Impl<>(new SelectHow())),
                new PartSpec.SameRole<>(HousePanes.AddTab.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Go.INSTANCE, new Target.Impl<>(new PlainButtonGo()))));
    }
}
