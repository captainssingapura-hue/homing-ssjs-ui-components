package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A control type: a named subset of the control catalogue - the options a kind of component is
 * controlled by, beyond the axes it varies along. Components are classified by type, never treated
 * one by one: every button is switched on and off, every overlay opened and closed. A type never
 * names an option set by degree: which axes a component varies along is the taxonomy's to say, and
 * a control panel takes the component's own.
 *
 * @param name    its name: lowercase letters, digits and hyphens, a letter first
 * @param options the options it takes, in the order a panel shows them
 */
public record ControlType(String name, List<ControlOption<?>> options) implements ValueObject {

    /** A type's name: lowercase letters, digits and hyphens, a letter first. */
    public static final Pattern NAME = Pattern.compile("[a-z][a-z0-9-]*");

    public ControlType {
        Objects.requireNonNull(name, "ControlType.name");
        options = List.copyOf(options);
        var problems = new ArrayList<String>();
        if (!NAME.matcher(name).matches()) problems.add("the control type '" + name + "': lowercase letters, digits and hyphens, a letter first");
        var seen = new HashSet<ControlOption<?>>();
        for (ControlOption<?> o : options) {
            if (!seen.add(o)) problems.add("the control type '" + name + "' takes " + ControlCatalogue.qualified(o) + " twice");
            if (o instanceof WithExtent<?>)
                problems.add("the control type '" + name + "' names " + ControlCatalogue.qualified(o) + ", set by degree: the axes are the component's own, from the taxonomy");
        }
        if (!problems.isEmpty()) throw new RefusedControls(problems);
    }
}
