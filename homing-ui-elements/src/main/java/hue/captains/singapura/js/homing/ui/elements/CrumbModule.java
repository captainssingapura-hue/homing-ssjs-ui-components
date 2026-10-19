package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.HrefManager;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The crumb: a simple component on an {@code <a>}, a step of a trail back - its words, the way it goes, whether it is the page itself; worn in its own look. */
public record CrumbModule() implements DomModule<CrumbModule>, ImplFor<HouseControls.Crumb> {

    /** An element component: {@code new Crumb(el, { text, to, current }, role)} on the element its companion makes; {@code setOnClick(handler)}, {@code setOnCurrentChanged(handler)}; {@code text}, {@code to}, {@code current}. */
    public record Crumb() implements ElementComponent<CrumbModule> {
        @Override public String tag() { return "a"; }
        @Override public String summary() { return "One step of a trail back: its words, where it goes, and whether it is the page itself."; }
    }


    public static final CrumbModule INSTANCE = new CrumbModule();

    @Override
    public ImportsFor<CrumbModule> imports() {
        return ImportsFor.<CrumbModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LinkBehaviorModule.LinkBehavior()), LinkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HrefManager.HrefManagerInstance()), HrefManager.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<CrumbModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Crumb()));
    }
}
