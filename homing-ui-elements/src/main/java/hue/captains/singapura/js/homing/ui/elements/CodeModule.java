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

/** The code: the text's declarations on a {@code <code>}, worn in its own look. Code in a line of words, in a fixed face. */
public record CodeModule() implements DomModule<CodeModule>, ImplFor<HouseText.Code> {

    /** An element component: {@code new Code(make, new FixedCode(), place?)}, made and placed by itself; {@code text}, {@code size}. */
    public record Code() implements ElementComponent<CodeModule> {
        @Override public String tag() { return "code"; }
        @Override public String summary() { return "Code in a line of words, in a fixed face."; }
    }


    public static final CodeModule INSTANCE = new CodeModule();

    @Override
    public ImportsFor<CodeModule> imports() {
        return ImportsFor.<CodeModule>builder()
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<CodeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Code()));
    }
}
