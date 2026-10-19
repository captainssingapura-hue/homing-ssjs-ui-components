package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The text box: a simple component on a {@code <input>}, riding on the browser's own; worn in its own look. Words the user types, on one line: the browser's own editing. */
public record TextBoxModule() implements DomModule<TextBoxModule>, ImplFor<HouseControls.TextBox> {

    /** An element component: {@code new TextBox(make, new FixedTextBox(), place?)}; {@code setOnInput(handler)}, {@code setOnCommit(handler)}; {@code value}, {@code placeholder}, {@code enabled}, {@code size}. */
    public record TextBox() implements ElementComponent<TextBoxModule> {
        @Override public String tag() { return "input"; }
        @Override public String summary() { return "Words the user types, on one line: the browser's own editing."; }
    }


    public static final TextBoxModule INSTANCE = new TextBoxModule();

    @Override
    public ImportsFor<TextBoxModule> imports() {
        return ImportsFor.<TextBoxModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new InputBehaviorsModule.EditBehavior()), InputBehaviorsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TextBoxModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TextBox()));
    }
}
