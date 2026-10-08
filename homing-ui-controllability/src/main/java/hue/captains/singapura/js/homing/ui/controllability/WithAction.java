package hue.captains.singapura.js.homing.ui.controllability;

/**
 * An option controlled by acting: something the component does, once, when asked - open, close,
 * zoom in, split - leaving no setting behind it. Its control is a button; what is controlled
 * answers {@code method()}.
 *
 * @param <P> the branch it is filed under
 */
public non-sealed interface WithAction<P extends ControlBranch> extends ControlOption<P> {}
