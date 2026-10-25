package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** The pill: the text's declarations on a {@code <span>}, worn in its own look. A count, kept short, of what cannot all be seen. */
public record PillModule() implements DomModule<PillModule>, ImplFor<HouseText.Pill> {

    /** An element component: {@code new Pill(make, new FixedPill(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Pill() implements ElementComponent<PillModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "A count, kept short, of what cannot all be seen."; }
    }


    public static final PillModule INSTANCE = new PillModule();

    @Override
    public ImportsFor<PillModule> imports() {
        return ImportsFor.<PillModule>builder()
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PillModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Pill()));
    }
}
