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

/** The lede: the text's declarations on a {@code <p>}, worn in its own look. The first words of a body, saying what follows in brief. */
public record LedeModule() implements DomModule<LedeModule>, ImplFor<HouseText.Lede> {

    /** An element component: {@code new Lede(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Lede() implements ElementComponent<LedeModule> {
        @Override public String tag() { return "p"; }
        @Override public String summary() { return "The first words of a body, saying what follows in brief."; }
    }

    /** Its companion: {@code LedeFactory.create(branch, name)} makes the element it is realized on. */
    public record LedeFactory() implements Exportable._Constant<LedeModule> {}

    public static final LedeModule INSTANCE = new LedeModule();

    @Override
    public ImportsFor<LedeModule> imports() {
        return ImportsFor.<LedeModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<LedeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Lede(), new LedeFactory()));
    }
}
