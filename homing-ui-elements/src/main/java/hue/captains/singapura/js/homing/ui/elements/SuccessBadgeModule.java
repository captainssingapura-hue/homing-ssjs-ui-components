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

/** The success badge: the text's declarations on a {@code <span>}, worn in its own look. A short word saying it went well. */
public record SuccessBadgeModule() implements DomModule<SuccessBadgeModule>, ImplFor<HouseText.SuccessBadge> {

    /** An element component: {@code new SuccessBadge(make, new FixedSuccessBadge(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record SuccessBadge() implements ElementComponent<SuccessBadgeModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A short word saying it went well."; }
    }


    public static final SuccessBadgeModule INSTANCE = new SuccessBadgeModule();

    @Override
    public ImportsFor<SuccessBadgeModule> imports() {
        return ImportsFor.<SuccessBadgeModule>builder()
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SuccessBadgeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SuccessBadge()));
    }
}
