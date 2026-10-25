package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-ui-icons's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - the house's fifteen icons (D52), each a class of its own in hue.captains.singapura.js.homing.design.styles.base.
 */
public record UiIconsStyleDeclaration() implements StyleDeclaration {

    public static final UiIconsStyleDeclaration INSTANCE = new UiIconsStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of(
                new AddIcon(),
                new RemoveIcon(),
                new CloseIcon(),
                new ResetIcon(),
                new RotateIcon(),
                new FlipIcon(),
                new PinIcon(),
                new CheckIcon(),
                new GripIcon(),
                new DiscloseIcon(),
                new SettingsIcon(),
                new PreferencesIcon(),
                new SizeIcon(),
                new AspectIcon(),
                new ExtentIcon(),
                new LevelIcon(),
                new PacmanIcon())));
    }
}
