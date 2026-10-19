package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The trail: the way from a site's home to where the user is, each step the house's crumb, cast as a
 * step, and the house's delimiter between two. A branch component, worn in the trail's look.
 */
public record TrailModule() implements DomModule<TrailModule>, ImplFor<HouseContainers.Trail> {

    /** A branch component: {@code new Trail(branch, new FixedTrail(), place?)}; {@code root}, {@code draw(crumbs)}. */
    public record Trail() implements BranchComponent<TrailModule> {
        @Override public String summary() { return "The way from a site's home to where the user is, each step a way back."; }
    }

    public static final TrailModule INSTANCE = new TrailModule();

    @Override
    public ImportsFor<TrailModule> imports() {
        return ImportsFor.<TrailModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CrumbModule.Crumb()), CrumbModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new DelimiterModule.Delimiter()), DelimiterModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_trail(),
                        new ElementStyles.el_trail_crumb()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TrailModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Trail()));
    }
}
