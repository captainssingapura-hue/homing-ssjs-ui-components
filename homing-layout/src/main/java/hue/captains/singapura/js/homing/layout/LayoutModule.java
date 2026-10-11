package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * {@code Layout}: the layout algebra as a page has it - the JavaScript twin of {@link Layout} and
 * {@link LayoutAlgebra}, held to them by a parity test. Pure: no element, no design, no sizing.
 */
public record LayoutModule() implements EsModule<LayoutModule> {

    /** The class of statics: the two splits, the term read and written, the edits as substitutions. */
    public record Layout() implements Exportable._Class<LayoutModule> {}

    public static final LayoutModule INSTANCE = new LayoutModule();

    @Override public ImportsFor<LayoutModule> imports() { return ImportsFor.noImports(); }

    @Override
    public ExportsOf<LayoutModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Layout()));
    }
}
