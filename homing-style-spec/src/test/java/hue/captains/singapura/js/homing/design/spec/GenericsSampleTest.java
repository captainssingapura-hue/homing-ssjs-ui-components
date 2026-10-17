package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** The types, as a declaration and its implementations are written: what compiles holds the rules the compiler is given. */
class GenericsSampleTest {

    record BasePlain() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> {
        @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> style() { return new StylingRes.CssContent<>(new BaseColours.Border()); }
    }

    record BasePlainAction() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> {
        @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> style() { return new StylingRes.RootDelegation<>(new BasePlain()); }
    }

    record EditorialPlainAction() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> {
        @Override public StylingRes<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> style() { return new StylingRes.ParentDelegation<>(new BasePlainAction()); }
    }

    record EditorialDialog() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> {
        @Override public StylingRes<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> style() { return new StylingRes.CssContent<>(); }
    }

    record EditorialDialogDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> {
        @Override public CastingStylePerRole<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> own() { return new EditorialDialog(); }
        @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.Dialog> parts() {
            return new PartsSpec<>(List.of(
                    new PartSpec.Standalone<>(HouseContainers.Dialog.INSTANCE, HouseContainers.ActionBar.INSTANCE, HouseShaping.Actions.INSTANCE)));
        }
    }

    record EditorialActionBar() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.ActionBar, RoleRoot> {
        @Override public StylingRes<BaseScheme, Editorial, HouseContainers.ActionBar, RoleRoot> style() { return new StylingRes.CssContent<>(); }
    }

    record EditorialActionBarDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseContainers.ActionBar, RoleRoot> {
        @Override public CastingStylePerRole<BaseScheme, Editorial, HouseContainers.ActionBar, RoleRoot> own() { return new EditorialActionBar(); }
        @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.ActionBar> parts() {
            return new PartsSpec<>(List.of(
                    new PartSpec.SameRole<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE,
                            new Target.Impl<>(new EditorialPlainAction()))));
        }
    }

    record EditorialActionBarActions() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.ActionBar, HouseShaping.Actions> {
        @Override public StylingRes<BaseScheme, Editorial, HouseContainers.ActionBar, HouseShaping.Actions> style() { return new StylingRes.RootDelegation<>(new EditorialActionBar()); }
    }

    record EditorialActionBarActionsDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseContainers.ActionBar, HouseShaping.Actions> {
        @Override public CastingStylePerRole<BaseScheme, Editorial, HouseContainers.ActionBar, HouseShaping.Actions> own() { return new EditorialActionBarActions(); }
        @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.ActionBar> parts() { return new EditorialActionBarDeclaration().parts(); }
    }

    @Test
    void aDeclarationAndItsImplementations_compile_andRead() {
        var dialog = new EditorialDialogDeclaration();
        assertInstanceOf(PartSpec.Standalone.class, dialog.parts().parts().getFirst());
        var bar = new EditorialActionBarDeclaration();
        var spec = (PartSpec.SameRole<?, ?, ?, ?, ?>) bar.parts().parts().getFirst();
        assertEquals(EditorialPlainAction.class, ((Target.Impl<?, ?, ?, ?>) spec.to()).impl().getClass());
        assertEquals(BasePlainAction.class, ((StylingRes.ParentDelegation<?, ?, ?, ?, ?>) new EditorialPlainAction().style()).to().getClass());
    }

    @Test
    void aSlotsRole_decidesItsKind() {
        assertThrows(IllegalArgumentException.class, () -> new PartSpec.Standalone<BaseScheme, Editorial, HouseContainers.ActionBar, HouseControls.PlainButton, HouseDoing.Action>(
                HouseContainers.ActionBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE));
        assertThrows(IllegalArgumentException.class, () -> new PartSpec.SameRole<>(HouseContainers.Dialog.INSTANCE, HouseContainers.ActionBar.INSTANCE,
                HouseShaping.Actions.INSTANCE, new Target.Declared<>(new EditorialActionBarActionsDeclaration())));
    }
}
