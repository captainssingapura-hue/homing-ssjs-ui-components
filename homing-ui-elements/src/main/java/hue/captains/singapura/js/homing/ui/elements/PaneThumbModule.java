package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The pane's thumbnail: a pane picked by a small picture of where it is, with its name - a simple component on its own {@code <div>}, its name a kicker. */
public record PaneThumbModule() implements DomModule<PaneThumbModule>, ImplFor<HouseControls.PaneThumb> {

    /** {@code new PaneThumb(branch, { name, current }, role?)}; {@code root}, {@code name}, {@code current}, {@code setOnCurrentChanged}. */
    public record PaneThumb() implements BranchComponent<PaneThumbModule> {
        @Override public String summary() { return "A pane, picked by a small picture of where it is, with its name."; }
    }

    public static final PaneThumbModule INSTANCE = new PaneThumbModule();

    @Override
    public ImportsFor<PaneThumbModule> imports() {
        return ImportsFor.<PaneThumbModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_pane_thumb_name()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PaneThumbModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PaneThumb()));
    }
}
