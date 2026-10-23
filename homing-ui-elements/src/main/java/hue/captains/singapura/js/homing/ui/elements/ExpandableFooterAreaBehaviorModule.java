package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What an expandable footer area does with the pointer: onto it and off it, and a press on its status footer, told to it (23 Oct). */
public record ExpandableFooterAreaBehaviorModule() implements DomModule<ExpandableFooterAreaBehaviorModule> {

    /** {@code new ExpandableFooterAreaBehavior(el, area)}. */
    public record ExpandableFooterAreaBehavior() implements Exportable._Class<ExpandableFooterAreaBehaviorModule> {}

    public static final ExpandableFooterAreaBehaviorModule INSTANCE = new ExpandableFooterAreaBehaviorModule();

    @Override public ImportsFor<ExpandableFooterAreaBehaviorModule> imports() { return ImportsFor.<ExpandableFooterAreaBehaviorModule>builder().build(); }

    @Override public ExportsOf<ExpandableFooterAreaBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ExpandableFooterAreaBehavior())); }
}
