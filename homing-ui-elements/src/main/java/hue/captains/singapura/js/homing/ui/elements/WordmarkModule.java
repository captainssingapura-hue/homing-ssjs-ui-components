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

/** The wordmark: the text's declarations on a {@code <span>}, worn in its own look. A name set as the mark of whom it belongs to. */
public record WordmarkModule() implements DomModule<WordmarkModule>, ImplFor<HouseText.Wordmark> {

    /** An element component: {@code new Wordmark(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Wordmark() implements ElementComponent<WordmarkModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A name set as the mark of whom it belongs to."; }
    }

    /** Its companion: {@code WordmarkFactory.create(branch, name)} makes the element it is realized on. */
    public record WordmarkFactory() implements Exportable._Constant<WordmarkModule> {}

    public static final WordmarkModule INSTANCE = new WordmarkModule();

    @Override
    public ImportsFor<WordmarkModule> imports() {
        return ImportsFor.<WordmarkModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<WordmarkModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Wordmark(), new WordmarkFactory()));
    }
}
