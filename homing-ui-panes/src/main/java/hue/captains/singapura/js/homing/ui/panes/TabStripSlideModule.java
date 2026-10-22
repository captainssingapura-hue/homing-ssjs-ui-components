package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/** A chip slid along its bar, the bar's own: under the hand, the chips it passes aside, settling onto its place, then at rest - split from the bar, its line limit reached (23 Oct). */
public record TabStripSlideModule() implements DomModule<TabStripSlideModule> {

    /** {@code new TabStripSlide(strip, row, from, x)}; {@code to}, {@code end}, {@code settle}, {@code rest}; {@code TabStripSlide._ease(el)}, {@code TabStripSlide._eased(el)}. */
    public record TabStripSlide() implements Exportable._Class<TabStripSlideModule> {}

    public static final TabStripSlideModule INSTANCE = new TabStripSlideModule();

    @Override
    public ImportsFor<TabStripSlideModule> imports() {
        return ImportsFor.<TabStripSlideModule>builder()
                .add(new ModuleImports<>(List.of(new TabDragModule.TabDrag()), TabDragModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneStyles.mtp_strip_loose(), new PaneStyles.mtp_chip_dragging(), new PaneStyles.mtp_chip_shifted()), PaneStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<TabStripSlideModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabStripSlide())); }
}
