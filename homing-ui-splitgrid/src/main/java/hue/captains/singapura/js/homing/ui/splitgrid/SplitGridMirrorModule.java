package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.elements.PaneThumbModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import java.util.List;

/**
 * {@code SplitGridMirror}: a grid's arrangement drawn at a scale from the
 * geometry, with a cursor the keyboard moves from cell to cell by the
 * workspace's rule. It never touches the grid: the owner tells it the layout
 * and the box, and reads the cursor from {@code CursorMoved}. A widget
 * switcher in the making: a shortcut can raise it scaled, the arrows pick a
 * pane, and the owner makes that pane active. A control, and a branch
 * component: it wears its own casting, and draws each cell as a pane's
 * thumbnail, cast as a choice, its name in a kicker; its own classes place.
 */
public record SplitGridMirrorModule() implements DomModule<SplitGridMirrorModule>, ImplFor<HouseSplitGrid.SplitGridMirror> {

    /** The class: {@code new SplitGridMirror(branch, {host, scale?, labelOf?, onEvent?})}; {@code reflect(layout, box)}, {@code scale}, {@code cursor}, {@code rects}, {@code dispose}. */
    public record SplitGridMirror() implements BranchComponent<SplitGridMirrorModule>, NeedKeyboard {
        @Override public String summary() { return "The arrangement of a grid, drawn at a scale, with a cursor the keyboard moves from cell to cell."; }
        /** The arrows, through the party: the cursor to the cell beside it. */
        public static final List<KeyBinding> KEYS = List.of(
                KeyBinding.of(Key.ARROW_LEFT, "the cursor to the cell on the left"), KeyBinding.of(Key.ARROW_RIGHT, "the cursor to the cell on the right"),
                KeyBinding.of(Key.ARROW_UP, "the cursor to the cell above"), KeyBinding.of(Key.ARROW_DOWN, "the cursor to the cell below"));
        @Override public List<KeyBinding> keys() { return KEYS; }
    }

    public static final SplitGridMirrorModule INSTANCE = new SplitGridMirrorModule();

    @Override
    public ImportsFor<SplitGridMirrorModule> imports() {
        return ImportsFor.<SplitGridMirrorModule>builder()
                .add(new ModuleImports<>(List.of(new SplitGridEventsModule.SplitGridEvents()), SplitGridEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SplitGridTreeModule.SplitGridTree()), SplitGridTreeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SplitGridGeometryModule.SplitGridGeometry()), SplitGridGeometryModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneThumbModule.PaneThumb()), PaneThumbModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new SplitGridStyles.sgm_root(),
                        new SplitGridStyles.sgm_cell()
                ), SplitGridStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SplitGridMirrorModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SplitGridMirror()));
    }
}
