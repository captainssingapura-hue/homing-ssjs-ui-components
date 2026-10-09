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

/** The kicker: the text's declarations on a {@code <span>}, worn in its own look. A few words above a heading that say where it belongs. */
public record KickerModule() implements DomModule<KickerModule>, ImplFor<HouseText.Kicker> {

    /** An element component: {@code new Kicker(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Kicker() implements ElementComponent<KickerModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A few words above a heading that say where it belongs."; }
    }

    /** Its companion: {@code KickerFactory.create(branch, name)} makes the element it is realized on. */
    public record KickerFactory() implements Exportable._Constant<KickerModule> {}

    public static final KickerModule INSTANCE = new KickerModule();

    @Override
    public ImportsFor<KickerModule> imports() {
        return ImportsFor.<KickerModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<KickerModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Kicker(), new KickerFactory()));
    }
}
