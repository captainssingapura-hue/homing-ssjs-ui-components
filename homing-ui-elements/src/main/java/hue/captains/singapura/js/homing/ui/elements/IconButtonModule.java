package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The icon button: the function of a button, the look of an icon - the button's declarations, its face an icon chosen once in its fixed object, made on its own branch and placed along that icon's slot, and its words its name; worn in its own look. */
public record IconButtonModule() implements DomModule<IconButtonModule>, ImplFor<HouseControls.IconButton> {

    /** An element component: {@code new IconButton(branch, new FixedIconButton(new FixedAddIcon()), place?)} - a composed component, its face its part, made on its branch; {@code onChange(StateClicks, listener)}; {@code clicks}, {@code enabled}, {@code size}, {@code label} - its name; {@code face}. */
    public record IconButton() implements ElementComponent<IconButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "The function of a button, the look of an icon: one thing done, named by the icon it wears and by its words for whoever cannot see it."; }
    }

    public static final IconButtonModule INSTANCE = new IconButtonModule();

    @Override
    public ImportsFor<IconButtonModule> imports() {
        return ImportsFor.<IconButtonModule>builder()
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<IconButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new IconButton()));
    }
}
