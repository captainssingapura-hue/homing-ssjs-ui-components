package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The warning button: the button's base, in the warning look. */
public record WarningButtonModule() implements DomModule<WarningButtonModule>, ImplFor<HouseControls.WarningButton> {

    /** An element component: {@code new WarningButton(el, { label, extent, size, onClick })} on an element minted with {@code WarningButton.TAG}; extent, size, label, setOn. */
    public record WarningButton() implements ElementComponent<WarningButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that deserves a second thought, though it can be undone."; }
    }

    public static final WarningButtonModule INSTANCE = new WarningButtonModule();

    @Override
    public ImportsFor<WarningButtonModule> imports() {
        return ImportsFor.<WarningButtonModule>builder()
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_button_warning()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<WarningButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new WarningButton()));
    }
}
