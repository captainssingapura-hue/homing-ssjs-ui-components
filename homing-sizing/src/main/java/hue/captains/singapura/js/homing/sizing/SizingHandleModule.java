package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * {@code SizingHandle}: a sizable boundary's behaviour (RFC 0068, appendix C §2), armed on the line
 * a layout drew and dressed - the hand, the keys handed in, where it stands said in aria, its states
 * written as the reflections its layout's look answers. It brings no look and makes no element: it
 * is its layout's code, never a component by itself.
 */
public record SizingHandleModule() implements DomModule<SizingHandleModule> {

    /** The class: {@code new SizingHandle(el, {axis, index, rail, measure, onMove, onDone, step?, leap?})}; {@code key}, {@code enabled}, {@code reflect}, {@code dispose}. */
    public record SizingHandle() implements Exportable._Class<SizingHandleModule> {}

    public static final SizingHandleModule INSTANCE = new SizingHandleModule();

    @Override
    public ImportsFor<SizingHandleModule> imports() {
        return ImportsFor.<SizingHandleModule>builder()
                .add(new ModuleImports<>(List.of(new RailModule.Rail()), RailModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new SizingStyles.sz_handle(),
                        new SizingStyles.sz_handle_h(),
                        new SizingStyles.sz_handle_v()
                ), SizingStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SizingHandleModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SizingHandle()));
    }
}
