package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * A degree read on a caller's own scale, and back - {@code new DegreeScale(least, most)}: pure arithmetic, holding nothing
 * (RFC 0069, ranges case by case). What a range's degree means - a volume, a gain, a font size - is its caller's, and
 * this is how it reads it.
 */
public record DegreeScaleModule() implements DomModule<DegreeScaleModule> {

    /** {@code new DegreeScale(least, most)}: {@code at(degree)}, {@code degreeOf(value)}, {@code stepOf(step)}. */
    public record DegreeScale() implements Exportable._Class<DegreeScaleModule> {}

    public static final DegreeScaleModule INSTANCE = new DegreeScaleModule();

    @Override public ImportsFor<DegreeScaleModule> imports() { return ImportsFor.noImports(); }

    @Override public ExportsOf<DegreeScaleModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new DegreeScale())); }
}
