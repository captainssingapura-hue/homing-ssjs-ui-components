package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;

import java.util.List;

/**
 * A slider's scale: a tick at each value named, beside its track, with its caption - the slider's
 * own code, drawn on its branch into its rail. Each tick's line is worn as a notch, its caption as
 * a figure; it places and paints nothing of its own. It implements no leaf: what it imports is the
 * slider's.
 */
public record SliderScaleModule() implements DomModule<SliderScaleModule> {

    /** {@code SliderScale.draw(branch, rail, ticks, { fraction, format, min, max, vertical })}. */
    public record SliderScale() implements Exportable._Class<SliderScaleModule> {}

    public static final SliderScaleModule INSTANCE = new SliderScaleModule();

    @Override public ImportsFor<SliderScaleModule> imports() {
        return ImportsFor.<SliderScaleModule>builder()
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TickModule.Tick()), TickModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_slider_rail_ticked(),
                        new ElementStyles.el_slider_tick(),
                        new ElementStyles.el_slider_tick_vertical(),
                        new ElementStyles.el_slider_tick_line(),
                        new ElementStyles.el_slider_tick_line_vertical(),
                        new ElementStyles.el_slider_tick_label()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SliderScaleModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SliderScale())); }
}
