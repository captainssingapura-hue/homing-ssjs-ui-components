package hue.captains.singapura.js.homing.layout;

/**
 * Where a new cell goes beside an old one: to its left or right - in a row, a horizontal split -
 * or above or below it - in a stack, a vertical split.
 */
public enum Side {
    LEFT, RIGHT, TOP, BOTTOM;

    /** The axis of the split the two stand in. */
    public Axis axis() { return this == LEFT || this == RIGHT ? Axis.HORIZONTAL : Axis.VERTICAL; }

    /** Whether the new cell comes first: left of the old, or above it. */
    public boolean before() { return this == LEFT || this == TOP; }

    /** Across the diagonal: left to top, right to bottom, and back. */
    public Side transposed() {
        return switch (this) { case LEFT -> TOP; case TOP -> LEFT; case RIGHT -> BOTTOM; case BOTTOM -> RIGHT; };
    }

    /** In a mirror: left and right exchanged, top and bottom kept. */
    public Side mirrored() {
        return switch (this) { case LEFT -> RIGHT; case RIGHT -> LEFT; default -> this; };
    }
}
