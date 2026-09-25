package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * {@code TabSource}: where a new tab comes from. A page knows what it can
 * mount and a pane knows how to hold a tab; between them lies one rule nobody
 * owned — a tab-pane opened on the desk, and a widget made for <b>the dock it
 * is going into</b> rather than the one it was written for. That rule lives
 * here, so that every way of asking for a tab — this crate's control, the
 * strip's own plus, a menu, a restore from a checkpoint — asks for it the same
 * way and gets the same answer.
 *
 * <p>Headless. It opens tab-panes in the desk's register, which call the
 * kind's {@code make}; the DOM that appears is the widget's, and the widget is
 * the page's. The tab-pane holds what comes back to the pane's law, so a kind
 * that returns the wrong shape is caught where it was written.</p>
 *
 * <p>It decides nothing about WHERE. Which pane is always the caller's — a
 * control asks its user, the strip's button would name its own pane, a restore
 * names the one in the record. The source is asked for a tab, and says what a
 * tab is.</p>
 */
public record TabSourceModule() implements EsModule<TabSourceModule> {

    /** The class: {@code new TabSource(branch, { kinds, desk })}, then {@code addTo(pane, kindId)}. */
    public record TabSource() implements Exportable._Constant<TabSourceModule> {}

    public static final TabSourceModule INSTANCE = new TabSourceModule();

    @Override
    public ImportsFor<TabSourceModule> imports() { return ImportsFor.noImports(); }

    @Override
    public ExportsOf<TabSourceModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabSource()));
    }
}
