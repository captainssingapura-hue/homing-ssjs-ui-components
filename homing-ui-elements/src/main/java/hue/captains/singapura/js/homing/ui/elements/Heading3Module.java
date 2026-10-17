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

/** The heading of level 3: the heading's declarations on an {@code <h3>}, worn in its own look. The heading of a section within a part. */
public record Heading3Module() implements DomModule<Heading3Module>, ImplFor<HouseText.Heading3> {

    /** An element component: {@code new Heading3(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Heading3() implements ElementComponent<Heading3Module> {
        @Override public String tag() { return "h3"; }
        @Override public String summary() { return "The heading of a section within a part."; }
    }

    /** Its companion: {@code Heading3Factory.create(branch, name)} makes the element it is realized on. */
    public record Heading3Factory() implements Exportable._Constant<Heading3Module> {}

    public static final Heading3Module INSTANCE = new Heading3Module();

    @Override
    public ImportsFor<Heading3Module> imports() {
        return ImportsFor.<Heading3Module>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading()), HeadingModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<Heading3Module> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading3(), new Heading3Factory()));
    }
}
