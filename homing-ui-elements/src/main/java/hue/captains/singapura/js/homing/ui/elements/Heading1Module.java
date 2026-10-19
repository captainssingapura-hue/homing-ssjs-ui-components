package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** The heading of level 1: the heading's declarations on an {@code <h1>}, worn in its own look. A page's own name: the first heading of its outline. */
public record Heading1Module() implements DomModule<Heading1Module>, ImplFor<HouseText.Heading1> {

    /** An element component: {@code new Heading1(make, new FixedHeading1(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Heading1() implements ElementComponent<Heading1Module> {
        @Override public String tag() { return "h1"; }
        @Override public String summary() { return "A page's own name: the first heading of its outline."; }
    }


    public static final Heading1Module INSTANCE = new Heading1Module();

    @Override
    public ImportsFor<Heading1Module> imports() {
        return ImportsFor.<Heading1Module>builder()
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading()), HeadingModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<Heading1Module> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading1()));
    }
}
