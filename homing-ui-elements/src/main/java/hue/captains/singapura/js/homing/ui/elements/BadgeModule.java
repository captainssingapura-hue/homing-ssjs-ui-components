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

/** The badge: the text's declarations on a {@code <span>}, worn in its own look. A short word that sorts or flags what it is beside. */
public record BadgeModule() implements DomModule<BadgeModule>, ImplFor<HouseText.Badge> {

    /** An element component: {@code new Badge(make, new FixedBadge(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Badge() implements ElementComponent<BadgeModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A short word that sorts or flags what it is beside."; }
    }


    public static final BadgeModule INSTANCE = new BadgeModule();

    @Override
    public ImportsFor<BadgeModule> imports() {
        return ImportsFor.<BadgeModule>builder()
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<BadgeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Badge()));
    }
}
