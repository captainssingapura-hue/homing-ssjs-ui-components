package hue.captains.singapura.js.homing.layout;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A cell's name in a layout: letters, digits, dot, underscore and hyphen. A cell is a region its
 * owner fills; the name is how the owner finds it, and stands once in a layout.
 *
 * @param value the name
 */
public record CellId(String value) {

    private static final Pattern GRAMMAR = Pattern.compile("[A-Za-z0-9._-]+");

    public CellId {
        Objects.requireNonNull(value, "CellId.value");
        if (!GRAMMAR.matcher(value).matches()) throw new IllegalArgumentException("CellId '" + value + "' - letters, digits, dot, underscore, hyphen");
    }

    @Override public String toString() { return value; }
}
