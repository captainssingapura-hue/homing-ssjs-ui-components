package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** The list's row: one row of a list, what it shows its owner's - a simple component on its own {@code <div>}: picked, current, and where the page's keys are. */
public record ListRowModule() implements DomModule<ListRowModule>, ImplFor<HouseItems.ListRow> {

    public record ListRow() implements ElementComponent<ListRowModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "One row of a list: picked from it, the one that is current, and where the page's keys are."; }
    }


    public static final ListRowModule INSTANCE = new ListRowModule();

    @Override
    public ImportsFor<ListRowModule> imports() {
        return ImportsFor.<ListRowModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ItemBehaviorModule.ItemBehavior()), ItemBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ListRowModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ListRow()));
    }
}
