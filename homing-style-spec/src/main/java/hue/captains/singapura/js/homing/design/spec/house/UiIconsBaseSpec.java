package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;
import java.util.List;

/** UiIconsComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiIconsBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiIconsBaseSpec INSTANCE = new UiIconsBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiIconsComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseIcons.AddIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.AddIcon()),
                new Implemented<>(HouseIcons.RemoveIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.RemoveIcon()),
                new Implemented<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.CloseIcon()),
                new Implemented<>(HouseIcons.ResetIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ResetIcon()),
                new Implemented<>(HouseIcons.RotateIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.RotateIcon()),
                new Implemented<>(HouseIcons.FlipIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.FlipIcon()),
                new Implemented<>(HouseIcons.PinIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PinIcon()),
                new Implemented<>(HouseIcons.CheckIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.CheckIcon()),
                new Implemented<>(HouseIcons.GripIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.GripIcon()),
                new Implemented<>(HouseIcons.DiscloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.DiscloseIcon()),
                new Implemented<>(HouseIcons.SettingsIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SettingsIcon()),
                new Implemented<>(HouseIcons.SizeIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SizeIcon()),
                new Implemented<>(HouseIcons.AspectIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.AspectIcon()),
                new Implemented<>(HouseIcons.ExtentIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ExtentIcon()),
                new Implemented<>(HouseIcons.LevelIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.LevelIcon()));
    }
}
