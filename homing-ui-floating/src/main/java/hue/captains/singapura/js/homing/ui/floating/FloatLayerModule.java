package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.panes.FloatingPaneModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code FloatLayer}: the floor floating panes are stacked on, a desk of its own - ported (RFC 0071, owner 23 Oct). Its
 * floaters are its caller's to open, close and mount, as a multi-tab pane's tabs are; it never makes or disposes a
 * widget. The stack is its own: a press or the focus arriving in a floater puts it in front, the one in use, and its
 * caller hears the stack as one order, bottom to top ({@code FloatOrder}); a floater's close is passed on
 * ({@code LayerCloseControl}). The keys, a desk laid over another, and handing a floater to a dock wait.
 */
public record FloatLayerModule() implements DomModule<FloatLayerModule>, ImplFor<HouseContainers.FloatLayer> {

    /** The class: {@code new FloatLayer(branch, new FixedFloatLayer(), place?)}; {@code add}, {@code mount}, {@code remove}, {@code raise}, {@code order}, {@code closes}, {@code bounds}. */
    public record FloatLayer() implements BranchComponent<FloatLayerModule>, NeedKeyboard {
        @Override public String summary() { return "The layer floating panes live on: its caller's floaters, stacked, the one in front in use."; }
        /** The desk holds the keys for its panes: the active pane's widget's first, whatever they are, then Escape - waiting, with the keys. */
        @Override public List<KeyBinding> keys() { return List.of(KeyBinding.of(Key.ESCAPE, "the active pane closed, when it can be closed; after its widget")); }
    }

    public static final FloatLayerModule INSTANCE = new FloatLayerModule();

    @Override
    public ImportsFor<FloatLayerModule> imports() {
        return ImportsFor.<FloatLayerModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloatLayerBehaviorModule.FloatLayerBehavior()), FloatLayerBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloatingPaneModule.FloatingPane()), FloatingPaneModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloatingStyles.fp_desk()), FloatingStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<FloatLayerModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new FloatLayer()));
    }
}
