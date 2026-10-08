package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;

/**
 * An option controlled by degree: one of the core's axes - colour, size, aspect - set as a number
 * from −1 to 1, live. Its control is a slider, resting at the axis's rest; what is controlled
 * answers {@code method(v)}. Which axes a component varies along is the taxonomy's to say
 * ({@code Taxon.extents()}), so a control type never names one: a panel takes the component's own.
 *
 * @param <P> the branch it is filed under
 */
public non-sealed interface WithExtent<P extends ControlBranch> extends ControlOption<P> {

    /** The core's axis it sets. */
    ExtentAxis axis();

    /** Where it rests until it is set: its axis's rest. */
    default double rest() { return axis().rest(); }
}
