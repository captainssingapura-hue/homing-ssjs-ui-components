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

/** The warning badge: the text's declarations on a {@code <span>}, worn in its own look. A short word asking for care before going on. */
public record WarningBadgeModule() implements DomModule<WarningBadgeModule>, ImplFor<HouseText.WarningBadge> {

    /** An element component: {@code new WarningBadge(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record WarningBadge() implements ElementComponent<WarningBadgeModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A short word asking for care before going on."; }
    }

    /** Its companion: {@code WarningBadgeFactory.create(branch, name)} makes the element it is realized on. */
    public record WarningBadgeFactory() implements Exportable._Constant<WarningBadgeModule> {}

    public static final WarningBadgeModule INSTANCE = new WarningBadgeModule();

    @Override
    public ImportsFor<WarningBadgeModule> imports() {
        return ImportsFor.<WarningBadgeModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<WarningBadgeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new WarningBadge(), new WarningBadgeFactory()));
    }
}
