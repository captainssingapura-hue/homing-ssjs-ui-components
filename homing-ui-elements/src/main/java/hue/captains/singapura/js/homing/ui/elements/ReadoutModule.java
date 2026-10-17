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

/** The readout: the text's declarations on a {@code <output>}, worn in its own look. A value read out exactly, as it stands now. */
public record ReadoutModule() implements DomModule<ReadoutModule>, ImplFor<HouseText.Readout> {

    /** An element component: {@code new Readout(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Readout() implements ElementComponent<ReadoutModule> {
        @Override public String tag() { return "output"; }
        @Override public String summary() { return "A value read out exactly, as it stands now."; }
    }

    /** Its companion: {@code ReadoutFactory.create(branch, name)} makes the element it is realized on. */
    public record ReadoutFactory() implements Exportable._Constant<ReadoutModule> {}

    public static final ReadoutModule INSTANCE = new ReadoutModule();

    @Override
    public ImportsFor<ReadoutModule> imports() {
        return ImportsFor.<ReadoutModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ReadoutModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Readout(), new ReadoutFactory()));
    }
}
