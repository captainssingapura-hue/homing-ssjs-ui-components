package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/** The brand's mark: a simple component on a {@code <span>}, a square of the brand's colour until a site gives a logo; worn in its own look. */
public record BrandMarkModule() implements DomModule<BrandMarkModule>, ImplFor<HouseMarks.BrandMark> {

    /** An element component: {@code new BrandMark(el, {}, role)} on the element its companion makes. Its colour is its look's; it offers nothing. */
    public record BrandMark() implements ElementComponent<BrandMarkModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "The brand's own sign beside its name: a square of its colour, until a site gives a logo."; }
    }


    public static final BrandMarkModule INSTANCE = new BrandMarkModule();

    @Override
    public ImportsFor<BrandMarkModule> imports() {
        return ImportsFor.<BrandMarkModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<BrandMarkModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new BrandMark()));
    }
}
