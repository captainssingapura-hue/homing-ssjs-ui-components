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

/** The heading of level 2: the heading's declarations on an {@code <h2>}, worn in its own look. The heading of one of a page's great divisions. */
public record Heading2Module() implements DomModule<Heading2Module>, ImplFor<HouseText.Heading2> {

    /** An element component: {@code new Heading2(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Heading2() implements ElementComponent<Heading2Module> {
        @Override public String tag() { return "h2"; }
        @Override public String summary() { return "The heading of one of a page's great divisions."; }
    }

    /** Its companion: {@code Heading2Factory.create(branch, name)} makes the element it is realized on. */
    public record Heading2Factory() implements Exportable._Constant<Heading2Module> {}

    public static final Heading2Module INSTANCE = new Heading2Module();

    @Override
    public ImportsFor<Heading2Module> imports() {
        return ImportsFor.<Heading2Module>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading()), HeadingModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<Heading2Module> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading2(), new Heading2Factory()));
    }
}
