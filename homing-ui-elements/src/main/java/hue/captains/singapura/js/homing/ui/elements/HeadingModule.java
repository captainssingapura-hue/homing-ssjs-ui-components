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
 * What the heading leaves share, as declarations: a text - words, at a size, nothing to do - read first. Never made on
 * its own: each level is a leaf of its own ({@code Heading1Module} … {@code Heading6Module}), on its own element.
 */
public record HeadingModule() implements DomModule<HeadingModule>, ImplForBranch<HouseBranches.Heading> {

    /** The base: {@code class HeadingN extends Heading}; {@code text}, {@code size}. */
    public record Heading() implements Exportable._Class<HeadingModule> {}

    public static final HeadingModule INSTANCE = new HeadingModule();

    @Override
    public ImportsFor<HeadingModule> imports() {
        return ImportsFor.<HeadingModule>builder()
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<HeadingModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Heading()));
    }
}
