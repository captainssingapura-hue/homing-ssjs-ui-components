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

/** The heading of level 5: the heading's declarations on an {@code <h5>}, worn in its own look. The heading of a division within a smaller section. */
public record Heading5Module() implements DomModule<Heading5Module>, ImplFor<HouseText.Heading5> {

    /** An element component: {@code new Heading5(make, new FixedHeading5(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Heading5() implements ElementComponent<Heading5Module> {
        @Override public String tag() { return "h5"; }
        @Override public String summary() { return "The heading of a division within a smaller section."; }
    }


    public static final Heading5Module INSTANCE = new Heading5Module();

    @Override
    public ImportsFor<Heading5Module> imports() {
        return ImportsFor.<Heading5Module>builder()
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading()), HeadingModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<Heading5Module> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading5()));
    }
}
