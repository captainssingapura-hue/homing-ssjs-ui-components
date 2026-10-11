package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.icons.IconsModule;

import java.util.List;

/**
 * The new CSS manager: a component's look worn one to one, by name - one semantic class for each
 * target, the casting's, and one treatment class for each state and target its component is
 * treated in - from a flattened sheet ({@link HouseLookSheet}) that has a rule for every one of
 * them; and a mark, a word of the house's lexicon, by the class its sheet draws it under ({@code
 * IconsModule}). No design word is worn, and nothing is resolved on the page.
 */
public record LookModule() implements DomModule<LookModule> {

    /** {@code new Look(Leaf, role)}; {@code wear}, {@code shed}, {@code show}, {@code degree}, {@code text}, {@code mark}. */
    public record Look() implements Exportable._Class<LookModule> {}

    public static final LookModule INSTANCE = new LookModule();

    @Override
    public ImportsFor<LookModule> imports() {
        return ImportsFor.<LookModule>builder()
                .add(new ModuleImports<>(List.of(new HouseLookSheet.look_sheet()), HouseLookSheet.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookManifestModule.LOOK_TARGETS(), new LookManifestModule.TREATED()), LookManifestModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconsModule.ICONS()), IconsModule.INSTANCE))
                .build();
    }

    @Override public ExportsOf<LookModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new Look())); }
}
