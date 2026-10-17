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

/** The danger badge: the text's declarations on a {@code <span>}, worn in its own look. A short word saying something is wrong, or cannot be undone. */
public record DangerBadgeModule() implements DomModule<DangerBadgeModule>, ImplFor<HouseText.DangerBadge> {

    /** An element component: {@code new DangerBadge(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record DangerBadge() implements ElementComponent<DangerBadgeModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A short word saying something is wrong, or cannot be undone."; }
    }

    /** Its companion: {@code DangerBadgeFactory.create(branch, name)} makes the element it is realized on. */
    public record DangerBadgeFactory() implements Exportable._Constant<DangerBadgeModule> {}

    public static final DangerBadgeModule INSTANCE = new DangerBadgeModule();

    @Override
    public ImportsFor<DangerBadgeModule> imports() {
        return ImportsFor.<DangerBadgeModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DangerBadgeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new DangerBadge(), new DangerBadgeFactory()));
    }
}
