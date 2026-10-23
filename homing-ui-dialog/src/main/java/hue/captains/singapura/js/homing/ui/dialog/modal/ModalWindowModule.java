package hue.captains.singapura.js.homing.ui.dialog.modal;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.MarkBehaviorModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.elements.ScrimModule;
import hue.captains.singapura.js.homing.ui.panes.FloatingPaneModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code ModalWindow} (owner, 23 Oct): a window that holds the page while it is open - what was the dialog, reduced. Its
 * veil over the page, its window centred over it, the page behind inert, the focus given back; a true modal, closed only
 * when asked explicitly, by its cross, passed on - its caller closes it. What it shows is its caller's.
 */
public record ModalWindowModule() implements DomModule<ModalWindowModule>, ImplFor<HouseContainers.ModalWindow> {

    /** {@code new ModalWindow(branch, new FixedModalWindow(window), place?)}; {@code mount}, {@code open}, {@code close}, {@code size}, {@code title}, {@code shown}, {@code closes}. */
    public record ModalWindow() implements BranchComponent<ModalWindowModule> {
        @Override public String summary() { return "new ModalWindow(branch, new FixedModalWindow(window), place?); mount, open, close, size, title, shown, closes."; }
    }

    public static final ModalWindowModule INSTANCE = new ModalWindowModule();

    @Override
    public ImportsFor<ModalWindowModule> imports() {
        return ImportsFor.<ModalWindowModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ScrimModule.Scrim()), ScrimModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FloatingPaneModule.FloatingPane()), FloatingPaneModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ModalityModule.Modality()), ModalityModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ModalStyles.mw_layer(), new ModalStyles.mw_veil()), ModalStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ModalWindowModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ModalWindow()));
    }
}
