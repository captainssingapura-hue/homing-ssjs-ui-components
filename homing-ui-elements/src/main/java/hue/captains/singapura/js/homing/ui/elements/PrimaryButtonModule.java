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

/** The primary button: the button's declarations, worn in its own look. */
public record PrimaryButtonModule() implements DomModule<PrimaryButtonModule>, ImplFor<HouseControls.PrimaryButton> {

    /** An element component: {@code new PrimaryButton(make, new FixedPrimaryButton(), place?)}, made and placed by itself; {@code onChange(StateClicks, listener)}; {@code clicks}, {@code enabled}, {@code size}, {@code extent}, {@code label}. */
    public record PrimaryButton() implements ElementComponent<PrimaryButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "The button a view leads with: the action most users came for."; }
    }


    public static final PrimaryButtonModule INSTANCE = new PrimaryButtonModule();

    @Override
    public ImportsFor<PrimaryButtonModule> imports() {
        return ImportsFor.<PrimaryButtonModule>builder()
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PrimaryButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PrimaryButton()));
    }
}
