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

/** The heading of level 4: the heading's declarations on an {@code <h4>}, worn in its own look. The heading of a smaller section, or of what a container holds: a card's, a group's, a setting's title. */
public record Heading4Module() implements DomModule<Heading4Module>, ImplFor<HouseText.Heading4> {

    /** An element component: {@code new Heading4(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Heading4() implements ElementComponent<Heading4Module> {
        @Override public String tag() { return "h4"; }
        @Override public String summary() { return "The heading of a smaller section, or of what a container holds: a card's, a group's, a setting's title."; }
    }

    /** Its companion: {@code Heading4Factory.create(branch, name)} makes the element it is realized on. */
    public record Heading4Factory() implements Exportable._Constant<Heading4Module> {}

    public static final Heading4Module INSTANCE = new Heading4Module();

    @Override
    public ImportsFor<Heading4Module> imports() {
        return ImportsFor.<Heading4Module>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading()), HeadingModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<Heading4Module> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading4(), new Heading4Factory()));
    }
}
