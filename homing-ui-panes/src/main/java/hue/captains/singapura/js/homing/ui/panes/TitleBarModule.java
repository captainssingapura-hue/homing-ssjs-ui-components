package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.CloseButtonModule;
import hue.captains.singapura.js.homing.ui.elements.LabelModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The title bar: the head of a pane that floats - what names it, its title or the chip of the one tab it holds, and its
 * close, each where its fixed object holds one - a composed component, made by the floating pane it heads (RFC 0071,
 * 23 Oct). It handles nothing: the hand down on it is its {@code Held}, which its pane moves by; its close's clicks are
 * passed on as the pane's {@code FloatCloseControl}.
 */
public record TitleBarModule() implements DomModule<TitleBarModule>, ImplFor<HouseContainers.TitleBar> {

    /** {@code new TitleBar(branch, new FixedTitleBar(title?, chip?, close?), place)}; {@code onChange(StateHeld | StateFloatCloseControl, listener)}; {@code title}, {@code dragging}, {@code held}, {@code closes}. */
    public record TitleBar() implements BranchComponent<TitleBarModule> {
        @Override public String summary() { return "The head of a pane that floats: its title or its one tab's chip, and its close; where the hand takes the pane."; }
    }

    public static final TitleBarModule INSTANCE = new TitleBarModule();

    @Override
    public ImportsFor<TitleBarModule> imports() {
        return ImportsFor.<TitleBarModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TitleBarBehaviorModule.TitleBarBehavior()), TitleBarBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LabelModule.Label()), LabelModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabModule.Tab()), TabModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CloseButtonModule.CloseButton()), CloseButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneStyles.fp_head(), new PaneStyles.fp_title()), PaneStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<TitleBarModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TitleBar())); }
}
