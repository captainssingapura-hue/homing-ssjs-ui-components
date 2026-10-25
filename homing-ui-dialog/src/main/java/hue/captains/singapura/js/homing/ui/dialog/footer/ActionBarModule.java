package hue.captains.singapura.js.homing.ui.dialog.footer;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.MarkBehaviorModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;
import hue.captains.singapura.js.homing.ui.elements.PrimaryButtonModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code ActionBar}, the standard footer (owner, 23 Oct): a row of actions at the foot of what it ends, its main one at
 * its end, how many plain ones fixed for its life; named and labelled by its holder, each press passed on by the action.
 * A component of its own, for the dialog widgets and whatever else ends a matter.
 */
public record ActionBarModule() implements DomModule<ActionBarModule>, ImplFor<HouseContainers.ActionBar> {

    /** {@code new ActionBar(branch, new FixedActionBar(go?, [actions]), place?)}; {@code name}, {@code label}, {@code enable}, {@code presses}. */
    public record ActionBar() implements BranchComponent<ActionBarModule> {
        @Override public String summary() { return "new ActionBar(branch, new FixedActionBar(go?, [actions]), place?); name, label, enable, presses."; }
    }

    public static final ActionBarModule INSTANCE = new ActionBarModule();

    @Override
    public ImportsFor<ActionBarModule> imports() {
        return ImportsFor.<ActionBarModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PrimaryButtonModule.PrimaryButton()), PrimaryButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new FooterStyles.ft_bar()), FooterStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ActionBarModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ActionBar()));
    }
}
