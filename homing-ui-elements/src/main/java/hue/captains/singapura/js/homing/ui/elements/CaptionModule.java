package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** The caption: the text's declarations on a {@code <span>}, worn in its own look. Quiet words beside something, adding to it without competing with it. */
public record CaptionModule() implements DomModule<CaptionModule>, ImplFor<HouseText.Caption> {

    /** An element component: {@code new Caption(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record Caption() implements ElementComponent<CaptionModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "Quiet words beside something, adding to it without competing with it."; }
    }

    /** Its companion: {@code CaptionFactory.create(branch, name)} makes the element it is realized on. */
    public record CaptionFactory() implements Exportable._Constant<CaptionModule> {}

    public static final CaptionModule INSTANCE = new CaptionModule();

    @Override
    public ImportsFor<CaptionModule> imports() {
        return ImportsFor.<CaptionModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<CaptionModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Caption(), new CaptionFactory()));
    }
}
