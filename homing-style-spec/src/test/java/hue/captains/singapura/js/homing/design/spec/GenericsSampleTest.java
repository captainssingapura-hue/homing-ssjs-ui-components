package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericsSampleTest {
    record BasePlain() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> {
        @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> style() { return new StylingRes.CssContent<>(new BaseColours.Border()); }
    }
    record BasePlainAction() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> {
        @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> style() { return new StylingRes.CssContent<>(); }
    }
    record EditorialPlainAction() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> {
        @Override public StylingRes<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> style() { return new StylingRes.ParentDelegation<>(new BasePlainAction()); }
    }
    record EditorialDialog() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> {
        @Override public StylingRes<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> style() { return new StylingRes.CssContent<>(); }
        @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.Dialog> parts() {
            return new PartsSpec<>(List.of(
                    new PartSpec.Cast<>(HouseContainers.Dialog.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE, new EditorialPlainAction())));
        }
    }

    @Test void compiles() {
        assertEquals(1, new EditorialDialog().parts().parts().size());
        assertEquals(BasePlainAction.class, ((StylingRes.ParentDelegation<?, ?, ?, ?, ?>) new EditorialPlainAction().style()).to().getClass());
    }
}
