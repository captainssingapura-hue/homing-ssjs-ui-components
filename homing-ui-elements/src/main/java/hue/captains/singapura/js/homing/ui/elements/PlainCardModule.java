package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The plain card: a frame the caller fills, raised and of its own measure - a simple component on
 * its own {@code <div>}, still under the hand. What is in it is its caller's; a card that does
 * something when pressed is the {@link ClickableCardModule clickable card}.
 */
public record PlainCardModule() implements DomModule<PlainCardModule>, ImplFor<HouseContainers.PlainCard> {

    /** {@code new PlainCard(branch.elementMaker(name), { size, aspect })}; {@code el}, {@code size}, {@code aspect}. */
    public record PlainCard() implements ElementComponent<PlainCardModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "The basic card: a frame the caller fills, at the design's measure and aspect, what is inside scrolling beyond it."; }
    }


    public static final PlainCardModule INSTANCE = new PlainCardModule();

    @Override
    public ImportsFor<PlainCardModule> imports() {
        return ImportsFor.<PlainCardModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_card()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PlainCardModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PlainCard()));
    }
}
