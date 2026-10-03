package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import java.util.List;

/**
 * {@code TabTear}: the arithmetic of tearing a tab off its strip, a browser's
 * model, with no element in sight. On the rail while the hand is within a
 * band around the strip; torn at the breach, where its window is made; the
 * velocity at the breach taken as the flight's own, and the window left
 * waiting while the hand keeps it; a material change in the velocity, held
 * for a moment, settles the window at the hand, which it follows from then
 * on. Headless, so a strip or a lab draws what it says and it is tested on
 * numbers.
 */
public record TabTearModule() implements EsModule<TabTearModule> {

    /** The class: {@code new TabTear(opts)}, a gesture at a time — press, move, tick, release. */
    public record TabTear() implements Exportable._Class<TabTearModule> {}

    public static final TabTearModule INSTANCE = new TabTearModule();

    @Override public ImportsFor<TabTearModule> imports() { return ImportsFor.noImports(); }

    @Override
    public ExportsOf<TabTearModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabTear()));
    }
}
