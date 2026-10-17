package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * {@code Rail}: sizing's model as a page has it - the JavaScript twin of {@link Rail} and
 * {@link Sizing}, held to them by a parity test. Pure: no element, no layout, no design.
 */
public record RailModule() implements EsModule<RailModule> {

    /** The class of statics: rails made, read and written; laid out, moved, divided, removed, absorbed. */
    public record Rail() implements Exportable._Class<RailModule> {}

    public static final RailModule INSTANCE = new RailModule();

    @Override public ImportsFor<RailModule> imports() { return ImportsFor.noImports(); }

    @Override
    public ExportsOf<RailModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Rail()));
    }
}
