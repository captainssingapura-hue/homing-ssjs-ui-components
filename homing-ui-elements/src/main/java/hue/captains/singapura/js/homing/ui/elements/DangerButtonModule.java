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

/** The danger button: the button's declarations, worn in its own look. */
public record DangerButtonModule() implements DomModule<DangerButtonModule>, ImplFor<HouseControls.DangerButton> {

    /** An element component: {@code new DangerButton(el, { label, extent, size }, role)} on the element its companion makes; {@code setOnClick(handler)}; {@code enabled}, {@code size}, {@code extent}, {@code label}. */
    public record DangerButton() implements ElementComponent<DangerButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that destroys, or cannot be undone."; }
    }

    /** Its companion: {@code DangerButtonFactory.create(branch, name)} makes the element it is realized on. */
    public record DangerButtonFactory() implements Exportable._Constant<DangerButtonModule> {}

    public static final DangerButtonModule INSTANCE = new DangerButtonModule();

    @Override
    public ImportsFor<DangerButtonModule> imports() {
        return ImportsFor.<DangerButtonModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DangerButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new DangerButton(), new DangerButtonFactory()));
    }
}
