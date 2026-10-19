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

/** The warning button: the button's declarations, worn in its own look. */
public record WarningButtonModule() implements DomModule<WarningButtonModule>, ImplFor<HouseControls.WarningButton> {

    /** An element component: {@code new WarningButton(make, new FixedWarningButton(), place?)}, made and placed by itself; {@code setOnClick(handler)}; {@code enabled}, {@code size}, {@code extent}, {@code label}. */
    public record WarningButton() implements ElementComponent<WarningButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that deserves a second thought, though it can be undone."; }
    }


    public static final WarningButtonModule INSTANCE = new WarningButtonModule();

    @Override
    public ImportsFor<WarningButtonModule> imports() {
        return ImportsFor.<WarningButtonModule>builder()
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<WarningButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new WarningButton()));
    }
}
