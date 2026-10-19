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

/** The label: the text's declarations on a {@code <span>}, worn in its own look. Words that name a control or a field, so the user knows what using it will touch. */
public record LabelModule() implements DomModule<LabelModule>, ImplFor<HouseText.Label> {

    /** An element component: {@code new Label(make, new FixedLabel(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Label() implements ElementComponent<LabelModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "Words that name a control or a field, so the user knows what using it will touch."; }
    }


    public static final LabelModule INSTANCE = new LabelModule();

    @Override
    public ImportsFor<LabelModule> imports() {
        return ImportsFor.<LabelModule>builder()
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<LabelModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Label()));
    }
}
