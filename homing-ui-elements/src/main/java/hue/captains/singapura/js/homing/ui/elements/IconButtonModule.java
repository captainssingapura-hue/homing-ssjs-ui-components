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

/** The icon button: the button's declarations, its face an Icon - its part - which icon it is the icon's business; worn in its own look. */
public record IconButtonModule() implements DomModule<IconButtonModule>, ImplFor<HouseControls.IconButton> {

    /** An element component: {@code new IconButton(el, { icon, label, size, onClick }, role)} on an element minted with {@code IconButton.TAG}, the icon made by its caller; {@code icon}, {@code enabled}, {@code size}, {@code label} - its name. */
    public record IconButton() implements ElementComponent<IconButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that is an icon alone: whichever icon it is handed, named for whoever cannot see it."; }
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
