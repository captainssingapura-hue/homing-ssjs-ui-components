package hue.captains.singapura.js.homing.ui.controllability;

/**
 * An option controlled by asking: what the component shows now, answered in words - who holds the
 * keys, what is open. It changes nothing. Its control is a button, and the answer is said; what is
 * controlled answers {@code method()} with the words.
 *
 * @param <P> the branch it is filed under
 */
public non-sealed interface WithQuestion<P extends ControlBranch> extends ControlOption<P> {}
