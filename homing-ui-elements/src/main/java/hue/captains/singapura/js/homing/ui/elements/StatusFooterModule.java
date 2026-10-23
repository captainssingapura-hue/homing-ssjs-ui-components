package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code StatusFooter} (owner, 23 Oct): a bar of status along the foot of what it ends, read-only - its caller's status
 * widget mounted in its room, an unseen cover over it so nothing on it is pressed. A container only.
 */
public record StatusFooterModule() implements DomModule<StatusFooterModule>, ImplFor<HouseContainers.StatusFooter> {

    /** {@code new StatusFooter(branch, new FixedStatusFooter(), place?)}; {@code mount(root, party?)}, {@code unmount()}. */
    public record StatusFooter() implements BranchComponent<StatusFooterModule> {
        @Override public String summary() { return "new StatusFooter(branch, new FixedStatusFooter(), place?); mount(root, party?), unmount()."; }
    }

    public static final StatusFooterModule INSTANCE = new StatusFooterModule();

    @Override
    public ImportsFor<StatusFooterModule> imports() {
        return ImportsFor.<StatusFooterModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_status_footer(), new ElementStyles.el_status_room(), new ElementStyles.el_status_cover()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<StatusFooterModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new StatusFooter()));
    }
}
