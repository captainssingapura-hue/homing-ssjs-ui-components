package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The success button: the button's declarations, worn in its own look. */
public record SuccessButtonModule() implements DomModule<SuccessButtonModule>, ImplFor<HouseControls.SuccessButton> {

    /** An element component: {@code new SuccessButton(el, { label, extent, size }, role)} on the element its companion makes; {@code setOnClick(handler)}; {@code enabled}, {@code size}, {@code extent}, {@code label}. */
    public record SuccessButton() implements ElementComponent<SuccessButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that completes what went well: accepts, finishes, confirms a good outcome."; }
    }

    /** Its companion: {@code SuccessButtonFactory.create(branch, name)} makes the element it is realized on. */
    public record SuccessButtonFactory() implements Exportable._Constant<SuccessButtonModule> {}

    public static final SuccessButtonModule INSTANCE = new SuccessButtonModule();

    @Override
    public ImportsFor<SuccessButtonModule> imports() {
        return ImportsFor.<SuccessButtonModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SuccessButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SuccessButton(), new SuccessButtonFactory()));
    }
}
