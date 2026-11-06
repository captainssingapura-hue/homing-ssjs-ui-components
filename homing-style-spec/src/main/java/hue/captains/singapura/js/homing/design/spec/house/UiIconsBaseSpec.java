package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.styles.base.*;
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
                new ComponentAssignment<>(HouseIcons.AddIcon.INSTANCE, new AddIcon()),
                new ComponentAssignment<>(HouseIcons.RemoveIcon.INSTANCE, new RemoveIcon()),
                new ComponentAssignment<>(HouseIcons.CloseIcon.INSTANCE, new CloseIcon()),
                new ComponentAssignment<>(HouseIcons.ResetIcon.INSTANCE, new ResetIcon()),
                new ComponentAssignment<>(HouseIcons.RotateIcon.INSTANCE, new RotateIcon()),
                new ComponentAssignment<>(HouseIcons.FlipIcon.INSTANCE, new FlipIcon()),
                new ComponentAssignment<>(HouseIcons.PinIcon.INSTANCE, new PinIcon()),
                new ComponentAssignment<>(HouseIcons.CheckIcon.INSTANCE, new CheckIcon()),
                new ComponentAssignment<>(HouseIcons.GripIcon.INSTANCE, new GripIcon()),
                new ComponentAssignment<>(HouseIcons.DiscloseIcon.INSTANCE, new DiscloseIcon()),
                new ComponentAssignment<>(HouseIcons.SettingsIcon.INSTANCE, new SettingsIcon()),
                new ComponentAssignment<>(HouseIcons.PreferencesIcon.INSTANCE, new PreferencesIcon()),
                new ComponentAssignment<>(HouseIcons.SizeIcon.INSTANCE, new SizeIcon()),
                new ComponentAssignment<>(HouseIcons.AspectIcon.INSTANCE, new AspectIcon()),
                new ComponentAssignment<>(HouseIcons.ExtentIcon.INSTANCE, new ExtentIcon()),
                new ComponentAssignment<>(HouseIcons.LevelIcon.INSTANCE, new LevelIcon()),
                new ComponentAssignment<>(HouseIcons.PacmanIcon.INSTANCE, new PacmanIcon()));
    }
}
