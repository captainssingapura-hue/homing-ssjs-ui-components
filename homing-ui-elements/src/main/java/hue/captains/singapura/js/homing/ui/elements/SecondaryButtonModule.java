package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The secondary button: the button's declarations, worn in its own look. */
public record SecondaryButtonModule() implements DomModule<SecondaryButtonModule>, ImplFor<HouseControls.SecondaryButton> {

    /** An element component: {@code new SecondaryButton(el, { label, extent, size, onClick }, role)} on an element minted with {@code SecondaryButton.TAG}; {@code enabled}, {@code size}, {@code extent}, {@code label}. */
    public record SecondaryButton() implements ElementComponent<SecondaryButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button beside the primary one: offered, not urged."; }
    }

    public static final SecondaryButtonModule INSTANCE = new SecondaryButtonModule();

    @Override
    public ImportsFor<SecondaryButtonModule> imports() {
        return ImportsFor.<SecondaryButtonModule>builder()
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SecondaryButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SecondaryButton()));
    }
}
