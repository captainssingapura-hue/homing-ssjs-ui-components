package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The standard of a simple component: one element, minted by its caller with the tag the
 * component declares. It is three - its state, kept here and changed only by its setters; its look
 * ({@link LookModule}); its behaviour, its leaf's - and a state is a value on an axis, a sum
 * type of mutually exclusive values, set by one setter that stores it, writes its reflection and
 * tells the behaviour. Written by hand until it is polished, generated from the declarations after.
 */
public record SimpleComponentModule() implements DomModule<SimpleComponentModule> {

    /** The base of a simple component's leaves: {@code new Leaf(el, props, role)}; {@code el}, {@code role}. */
    public record SimpleComponent() implements Exportable._Class<SimpleComponentModule> {}

    /** A leaf's companion: {@code new SimpleComponentFactory(Leaf)}; {@code create(branch, name)} makes its element. */
    public record SimpleComponentFactory() implements Exportable._Class<SimpleComponentModule> {}

    /** One of a component's events, dressed from the DOM's: {@code type}, {@code source}, {@code raw}. */
    public record ComponentEvent() implements Exportable._Class<SimpleComponentModule> {}

    /** A state's change: a {@code ComponentEvent} with the state's {@code previous} value. */
    public record StateChange() implements Exportable._Class<SimpleComponentModule> {}

    public static final SimpleComponentModule INSTANCE = new SimpleComponentModule();

    @Override
    public ImportsFor<SimpleComponentModule> imports() {
        return ImportsFor.<SimpleComponentModule>builder()
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE(), new CastingsModule.CASTINGS()), CastingsModule.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SimpleComponentModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SimpleComponent(), new SimpleComponentFactory(), new ComponentEvent(), new StateChange())); }
}
