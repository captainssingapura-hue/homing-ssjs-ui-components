package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.menu.ContextMenuModule;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code ContextMenuSpecimen}: the house's context menu in action - shown where asked, walked by the
 * keys handed on, picked, hidden - and shown still. The page's one steward is not the specimen's to
 * take, so it holds the menu as a steward would.
 */
public record ContextMenuSpecimenModule() implements DomModule<ContextMenuSpecimenModule> {

    public static final ContextMenuSpecimenModule INSTANCE = new ContextMenuSpecimenModule();

    public record ContextMenuSpecimen() implements BranchComponent<ContextMenuSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's context menu in action: a document's choices shown where the user asks, walked by the keys handed on - a row's rows beside it - picked or hidden; and the same menu shown still, every level open."; }
        @Override public List<KeyBinding> keys() {
            var keys = new ArrayList<KeyBinding>();
            keys.addAll(KeyBinding.each("the cursor along the rows, handed on to the menu", Key.ARROW_UP, Key.ARROW_DOWN, Key.HOME, Key.END));
            keys.addAll(KeyBinding.each("into a row's rows, or the row picked", Key.ARROW_RIGHT, Key.ENTER, Key.SPACE));
            keys.add(KeyBinding.of(Key.ARROW_LEFT, "a level back"));
            keys.add(KeyBinding.of(Key.ESCAPE, "the menu hidden"));
            return List.copyOf(keys);
        }
    }

    @Override
    public ImportsFor<ContextMenuSpecimenModule> imports() {
        return ImportsFor.<ContextMenuSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new ContextMenuModule.ContextMenu()), ContextMenuModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_text(),
                        new SpecimenStyles.sp_row(), new SpecimenStyles.sp_layer(), new SpecimenStyles.sp_frames()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ContextMenuSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ContextMenuSpecimen())); }
}
