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

/** The heading of level 6: the heading's declarations on an {@code <h6>}, worn in its own look. The least heading: a division within that. */
public record Heading6Module() implements DomModule<Heading6Module>, ImplFor<HouseText.Heading6> {

    /** An element component: {@code new Heading6(make, new FixedHeading6(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Heading6() implements ElementComponent<Heading6Module> {
        @Override public String tag() { return "h6"; }
        @Override public String summary() { return "The least heading: a division within that."; }
    }


    public static final Heading6Module INSTANCE = new Heading6Module();

    @Override
    public ImportsFor<Heading6Module> imports() {
        return ImportsFor.<Heading6Module>builder()
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading()), HeadingModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<Heading6Module> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading6()));
    }
}
