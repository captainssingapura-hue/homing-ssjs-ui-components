package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.IconsModule;

import java.util.List;

/** The close button: the button's declarations, its face the close icon - each style's own cross, its look (D52) - made on the room its owner gives, and its words its name; worn in its own look. */
public record CloseButtonModule() implements DomModule<CloseButtonModule>, ImplFor<HouseControls.CloseButton> {

    /** An element component: {@code new CloseButton(el, { label, size }, role)} on the element its companion makes; {@code setOnClick(handler)}; {@code enabled}, {@code size}, {@code label} - its name. */
    public record CloseButton() implements ElementComponent<CloseButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "The button that closes what holds it: a cross, each style its own, named for whoever cannot see it."; }
    }

    /** Its companion: {@code CloseButtonFactory.create(branch, name)} makes the element it is realized on. */
    public record CloseButtonFactory() implements Exportable._Constant<CloseButtonModule> {}

    public static final CloseButtonModule INSTANCE = new CloseButtonModule();

    @Override
    public ImportsFor<CloseButtonModule> imports() {
        return ImportsFor.<CloseButtonModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconsModule.ICON()), IconsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<CloseButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new CloseButton(), new CloseButtonFactory()));
    }
}
