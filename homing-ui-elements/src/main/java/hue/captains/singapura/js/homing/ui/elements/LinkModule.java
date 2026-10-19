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

/** The link: a simple component on an {@code <a>}, its words and the way it goes, and a click; worn in its own look. A way to somewhere else. */
public record LinkModule() implements DomModule<LinkModule>, ImplFor<HouseControls.Link> {

    /** An element component: {@code new Link(el, { text, to, size }, role)} on the element its companion makes; {@code setOnClick(handler)}; {@code text}, {@code to}, {@code size}. */
    public record Link() implements ElementComponent<LinkModule> {
        @Override public String tag() { return "a"; }
        @Override public String summary() { return "A way to somewhere else: its words, and where it goes."; }
    }


    public static final LinkModule INSTANCE = new LinkModule();

    @Override
    public ImportsFor<LinkModule> imports() {
        return ImportsFor.<LinkModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LinkBehaviorModule.LinkBehavior()), LinkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HrefManager.HrefManagerInstance()), HrefManager.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<LinkModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Link()));
    }
}
