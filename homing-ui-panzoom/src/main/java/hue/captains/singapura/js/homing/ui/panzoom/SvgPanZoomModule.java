package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;

import java.util.List;

/**
 * An SVG in a viewport that zooms and pans, wherever it is placed: the wheel about the pointer
 * (with Ctrl or Cmd in the flow of a page), a drag once zoomed in, a double press; and its own
 * keys while it has the focus. Zoomed, the viewport scrolls natively - its bars, a trackpad, a touch.
 */
public record SvgPanZoomModule() implements DomModule<SvgPanZoomModule>, ImplFor<HousePanZoom.SvgPanZoom> {

    public static final SvgPanZoomModule INSTANCE = new SvgPanZoomModule();

    /** A composed component: its viewport and canvas, its bar at its foot where made with one; mount, label, zoomIn, zoomOut, fit, measure, view, dispose. */
    public record SvgPanZoom() implements BranchComponent<SvgPanZoomModule>, NeedKeyboard {
        @Override public String summary() { return "An SVG in a viewport that zooms about the pointer and pans once zoomed in: the hand, the wheel and its own keys."; }

        /** Its keys, natively, while the viewport has the focus. */
        public static final List<KeyBinding> KEYS = List.of(
                new KeyBinding(Key.PLUS, "zoom in"), new KeyBinding(Key.MINUS, "zoom out"), new KeyBinding(Key.DIGIT_0, "back to fit"),
                new KeyBinding(Key.ARROW_LEFT, "pan left"), new KeyBinding(Key.ARROW_RIGHT, "pan right"),
                new KeyBinding(Key.ARROW_UP, "pan up"), new KeyBinding(Key.ARROW_DOWN, "pan down"));
        @Override public List<KeyBinding> keys() { return KEYS; }
    }

    @Override
    public ImportsFor<SvgPanZoomModule> imports() {
        return ImportsFor.<SvgPanZoomModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SvgPanZoomBehaviorModule.SvgPanZoomBehavior()), SvgPanZoomBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PanZoomModule.PanZoom()), PanZoomModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PanZoomBarModule.PanZoomBar()), PanZoomBarModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PanZoomStyles.pz_view(), new PanZoomStyles.pz_frame(), new PanZoomStyles.pz_pinned(), new PanZoomStyles.pz_canvas(),
                        new PanZoomStyles.pz_zoomed(), new PanZoomStyles.pz_svg(), new PanZoomStyles.pz_scaled(),
                        new PanZoomStyles.pz_pannable()), PanZoomStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SvgPanZoomModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SvgPanZoom())); }
}
