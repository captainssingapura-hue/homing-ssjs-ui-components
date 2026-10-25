package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

import java.util.List;

/**
 * A jar's assignments in one layer (RFC 0067, D53, D54): for each component it assigns, the implementation each of the
 * component's castings wears. It assigns only components the component modules it names bring, so a jar styles nothing
 * it does not say it styles: an extension names its own module and reaches none of the house's.
 *
 * @param <Sc> the layer's scheme
 * @param <S>  the layer
 */
public interface SpecModule<Sc extends ColorScheme, S extends DesignStyle<Sc>> {
    /** The layer its assignments are of. */
    S style();
    /** The component modules whose components it assigns: no other component's. */
    List<? extends ComponentModule> modules();
    /** Each component it assigns, with that assignment. */
    List<ComponentAssignment<Sc, S, ?>> assignments();
}
