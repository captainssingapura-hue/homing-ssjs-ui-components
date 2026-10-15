package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.elements.PillModule;
import hue.captains.singapura.js.homing.ui.elements.DropMarkModule;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.elements.CloseButtonModule;
import hue.captains.singapura.js.homing.ui.elements.IconButtonModule;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code TabStrip}, the row of chips over a pane. {@code new TabStrip(branch,
 * {onAdd?, onDrop, onDragOut?})} builds the strip — chips with a label and a
 * cross, the tail with the add button and the count, the mark for a tab
 * offered from outside — and the drag that is a browser's, along a rail:
 * the chip pressed goes where the hand goes along the row, the others
 * stepping aside live, and settles on the slot it is nearest, never before
 * the pinned ones. It holds no tab state; the pane arranges, selects and
 * counts, and turns a drop into a move.
 */
public record TabStripModule() implements DomModule<TabStripModule>, ImplFor<HouseContainers.TabStrip> {

    /** The class. */
    public record TabStrip() implements BranchComponent<TabStripModule> {
        @Override public String summary() { return "The strip of chips on its own: selection by a press, and the hand that drags a chip along the rail; no chip takes native focus, and the keys over a strip are its pane's."; }
    }

    public static final TabStripModule INSTANCE = new TabStripModule();

    @Override
    public ImportsFor<TabStripModule> imports() {
        return ImportsFor.<TabStripModule>builder()
                .add(new ModuleImports<>(List.of(new PillModule.Pill()), PillModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE(), new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconButtonModule.IconButton()), IconButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CloseButtonModule.CloseButton()), CloseButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new DropMarkModule.DropMark()), DropMarkModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_strip(),
                        new PaneStyles.mtp_rail(),
                        new PaneStyles.mtp_strip_loose(),
                        new PaneStyles.mtp_strip_tail(),
                        new PaneStyles.mtp_rail_add(),
                        new PaneStyles.mtp_bar_close(),
                        new PaneStyles.mtp_pill(),
                        new PaneStyles.mtp_drop_mark(),
                        new PaneStyles.mtp_chip_shifted(),
                        new PaneStyles.mtp_chip_dragging()
                ), PaneStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabModule.Tab()), TabModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabHandModule.TabHand()), TabHandModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabDragModule.TabDrag()), TabDragModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabWindowModule.TabWindow()), TabWindowModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabStripModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabStrip()));
    }
}
