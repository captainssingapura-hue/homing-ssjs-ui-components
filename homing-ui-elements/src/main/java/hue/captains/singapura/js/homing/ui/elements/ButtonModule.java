package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForBranch;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import java.util.List;

/**
 * What the button leaves share, as declarations: a simple component on a {@code <button>}, on the axis {@code enabled},
 * with a size and a label, and the behaviour that is theirs. Never made on its own: each leaf is, with its own fixed
 * type and binding. Stamped with every leaf's header (RFC 0071, D19), it names the duals' nodes - {@code StateEnabled},
 * {@code EventClick}, {@code ExtentSize} - and no flat table.
 */
public record ButtonModule() implements DomModule<ButtonModule>, ImplForBranch<HouseBranches.Button> {

    /** The base: {@code class XButton extends Button}; {@code enabled}, {@code size}, {@code label}. */
    public record Button() implements Exportable._Class<ButtonModule> {}

    public static final ButtonModule INSTANCE = new ButtonModule();

    @Override
    public ImportsFor<ButtonModule> imports() {
        return ImportsFor.<ButtonModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ButtonBehaviorModule.ButtonBehavior()), ButtonBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Button()));
    }
}
