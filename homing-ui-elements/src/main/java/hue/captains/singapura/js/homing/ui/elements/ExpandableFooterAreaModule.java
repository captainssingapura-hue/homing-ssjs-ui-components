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
 * {@code ExpandableFooterArea} (owner, 23 Oct): a status footer at the foot and an area opening over what it covers, its
 * caller's control widget in it, open while the pointer, the widget being active, or its owner keeps it so; closing by
 * itself after. A container only; the foot's own.
 */
public record ExpandableFooterAreaModule() implements DomModule<ExpandableFooterAreaModule>, ImplFor<HouseContainers.ExpandableFooterArea> {

    /** {@code new ExpandableFooterArea(branch, new FixedExpandableFooterArea(), place?)}; {@code status}, {@code mount(widget)}, {@code hold(on)}, {@code expanded}. */
    public record ExpandableFooterArea() implements BranchComponent<ExpandableFooterAreaModule> {
        @Override public String summary() { return "new ExpandableFooterArea(branch, new FixedExpandableFooterArea(), place?); status, mount(widget), hold(on), expanded."; }
    }

    public static final ExpandableFooterAreaModule INSTANCE = new ExpandableFooterAreaModule();

    @Override
    public ImportsFor<ExpandableFooterAreaModule> imports() {
        return ImportsFor.<ExpandableFooterAreaModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ExpandableFooterAreaBehaviorModule.ExpandableFooterAreaBehavior()), ExpandableFooterAreaBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatusFooterModule.StatusFooter()), StatusFooterModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_footer_area(), new ElementStyles.el_footer_area_panel(), new ElementStyles.el_footer_area_closed()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ExpandableFooterAreaModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ExpandableFooterArea()));
    }
}
