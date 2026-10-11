package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.sizing.RailModule;
import java.util.List;

/**
 * {@code SplitGridGeometry}: the arrangement resolved into rectangles for a
 * box, as flex would - each split laid out by sizing's rail - and the rules
 * that read them - the cell beside a cell by the workspace's rule, the thing
 * under a point - with no element in sight. Headless, so a mirror draws what the grid has and the
 * rules are tested without a browser.
 */
public record SplitGridGeometryModule() implements EsModule<SplitGridGeometryModule> {

    /** The class of statics: {@code rects}, {@code neighbour}, {@code hit}. */
    public record SplitGridGeometry() implements Exportable._Constant<SplitGridGeometryModule> {}

    public static final SplitGridGeometryModule INSTANCE = new SplitGridGeometryModule();

    @Override
    public ImportsFor<SplitGridGeometryModule> imports() {
        return ImportsFor.<SplitGridGeometryModule>builder()
                .add(new ModuleImports<>(List.of(new RailModule.Rail()), RailModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SplitGridGeometryModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SplitGridGeometry()));
    }
}
