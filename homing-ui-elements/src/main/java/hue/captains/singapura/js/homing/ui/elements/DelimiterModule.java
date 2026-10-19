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

/** The delimiter: a simple component on a {@code <span>}, the mark between the steps of a run - a trail's crumbs; worn in its own look. */
public record DelimiterModule() implements DomModule<DelimiterModule>, ImplFor<HouseMarks.Delimiter> {

    /** An element component: {@code new Delimiter(el, {}, role)} on the element its companion makes. Its sign is its look's; it offers nothing. */
    public record Delimiter() implements ElementComponent<DelimiterModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A mark between the steps of a run, on one line with them: the sign between a trail's crumbs."; }
    }


    public static final DelimiterModule INSTANCE = new DelimiterModule();

    @Override
    public ImportsFor<DelimiterModule> imports() {
        return ImportsFor.<DelimiterModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<DelimiterModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Delimiter()));
    }
}
