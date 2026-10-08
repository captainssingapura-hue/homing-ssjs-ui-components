package hue.captains.singapura.js.homing.ui.controllability;

/**
 * An option controlled by switching: a state the component is in or not - enabled, current,
 * raised, held shown - kept until it is switched again. Its control is a toggle; what is
 * controlled answers {@code method(on)}.
 *
 * @param <P> the branch it is filed under
 */
public non-sealed interface WithSwitch<P extends ControlBranch> extends ControlOption<P> {

    /** Whether it is on until it is switched. */
    boolean rest();
}
