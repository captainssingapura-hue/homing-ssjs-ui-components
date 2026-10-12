package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The ground: the surface a page stands on - its ground, the ink and the face of what is written on
 * it, its scrollbar - worn once, by the page, on the document's own element. Everything on the page
 * stands on it; a text takes its ink and its face from it.
 */
public record GroundModule() implements DomModule<GroundModule>, ImplFor<HouseContainers.Ground> {

    /** An element component on the document's element: {@code new Ground(document.documentElement)}. */
    public record Ground() implements ElementComponent<GroundModule> {
        @Override public String tag() { return "html"; }
        @Override public String summary() { return "The surface a page stands on: its ground, the ink and the face of what is written on it, its scrollbar."; }
    }

    public static final GroundModule INSTANCE = new GroundModule();

    @Override
    public ImportsFor<GroundModule> imports() {
        return ImportsFor.<GroundModule>builder()
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<GroundModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Ground()));
    }
}
