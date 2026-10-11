package hue.captains.singapura.js.homing.layout;

/**
 * Which way a split lays its parts, as the house has always named it: HORIZONTAL in a row, left to
 * right - the lines between them run up and down; VERTICAL in a stack, top to bottom - the lines
 * between them run across.
 */
public enum Axis {
    HORIZONTAL, VERTICAL;

    /** The other one. */
    public Axis across() { return this == HORIZONTAL ? VERTICAL : HORIZONTAL; }
}
