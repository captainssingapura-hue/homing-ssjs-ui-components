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

/** The heading: the text's declarations on a {@code <div>}, worn in its own look. Words that say what a container holds, read first. */
public record HeadingModule() implements DomModule<HeadingModule>, ImplFor<HouseText.Heading> {

    /** An element component: {@code new Heading(el, { text, level, size }, role)} on the element its companion makes; {@code text}, {@code level}, {@code size}. */
    public record Heading() implements ElementComponent<HeadingModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "Words that say what a container holds, read first."; }
    }

    /** Its companion: {@code HeadingFactory.create(branch, name)} makes the element it is realized on. */
    public record HeadingFactory() implements Exportable._Constant<HeadingModule> {}

    public static final HeadingModule INSTANCE = new HeadingModule();

    @Override
    public ImportsFor<HeadingModule> imports() {
        return ImportsFor.<HeadingModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<HeadingModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading(), new HeadingFactory()));
    }
}
