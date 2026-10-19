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

/** The plain button: the button's declarations, worn in its own look. */
public record PlainButtonModule() implements DomModule<PlainButtonModule>, ImplFor<HouseControls.PlainButton> {

    /** An element component: {@code new PlainButton(make, new FixedPlainButton(), place?)}, made and placed by itself; {@code setOnClick(handler)}; {@code enabled}, {@code size}, {@code label}. */
    public record PlainButton() implements ElementComponent<PlainButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that does one ordinary thing, with nothing to warn of and nothing to lead with."; }
    }


    public static final PlainButtonModule INSTANCE = new PlainButtonModule();

    @Override
    public ImportsFor<PlainButtonModule> imports() {
        return ImportsFor.<PlainButtonModule>builder()
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PlainButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PlainButton()));
    }
}
