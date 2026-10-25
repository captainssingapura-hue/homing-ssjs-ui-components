package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;

import java.util.List;

/**
 * UiIconsComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiIconsBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiIconsBaseSpec INSTANCE = new UiIconsBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiIconsComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseIcons.AddIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.AddIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.RemoveIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.RemoveIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.CloseIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.ResetIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ResetIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.RotateIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.RotateIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.FlipIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.FlipIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.PinIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PinIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.CheckIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.CheckIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.GripIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.GripIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.DiscloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.DiscloseIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.SettingsIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SettingsIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.PreferencesIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.SizeIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SizeIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.AspectIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.AspectIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.ExtentIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ExtentIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.LevelIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.LevelIcon(), List.of()),
                new ComponentAssignment<>(HouseIcons.PacmanIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PacmanIcon(), List.of()));
    }
}
