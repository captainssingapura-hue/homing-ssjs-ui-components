package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForBranch;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import java.util.List;

/**
 * What the text leaves share, as declarations: a simple component whose face is words - its text -
 * at a size, with nothing to do. Never made on its own: each leaf's own module names its element
 * and binds its look to it.
 */
public record TextModule() implements DomModule<TextModule>, ImplForBranch<HouseBranches.Text> {

    /** The base: {@code class Heading extends Text}; {@code text}, {@code size}. */
    public record Text() implements Exportable._Class<TextModule> {}

    public static final TextModule INSTANCE = new TextModule();

    @Override
    public ImportsFor<TextModule> imports() {
        return ImportsFor.<TextModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextBehaviorModule.TextBehavior()), TextBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_text()), ElementStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<TextModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new Text())); }
}
