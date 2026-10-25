package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ui.elements.KickerModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;

import java.util.List;

/**
 * {@code TabOpener}, a transient widget (owner, 22 Oct), its caller's - a
 * workspace's or an app's, never its pane's, which stays dumb: what a new tab
 * holds until you say what it should hold.
 * A strip's plus cannot know which of a page's kinds was meant, and guessing
 * one is worse than asking: so the plus opens a tab with this in it, and
 * picking from it turns that tab into the thing picked. The chip you made is
 * the chip you keep, in the place you made it.
 *
 * <p>It REPLACES rather than contains. A widget's root is appended to its
 * tab's panel once and never detached, so an opener that mounted the chosen
 * thing inside itself would be a tab that is always an opener wearing somebody
 * else's name. Giving the tab up and asking the {@link
 * TabSourceModule.TabSource} for a fresh one costs three calls and leaves
 * nothing of the opener behind — and it is the source's one minting rule, so a
 * tab opened here and a tab added from code are the same kind of thing.</p>
 *
 * <p>Its choices are native buttons inside a logical member: the widget holds
 * the keys for the dock, the focused button holds them for the widget — said
 * as {@code lent} — and an Escape the button did not want comes back to the
 * widget rather than out of the room. That seam is wired here once, so a page
 * need not wire it.</p>
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 11,
        reason = "It makes elements of its own and by a companion, places them, wears classes of its own and touches its components' elements: a component until 22 Oct, it waits for its port as a widget - the designed containers, and the widget slot - to draw only components.")
public record TabOpenerModule() implements Widget<Widget._None, TabOpenerModule> {

    /** The class. */
    public record TabOpener() implements Widget._Class<Widget._None, TabOpenerModule>, NeedKeyboard {
        /**
         * Two Escapes and nothing else. The choices are native buttons, so the
         * browser's own keys walk them and press them and this declares none
         * of that; what IS its own is the seam — one Escape brings the keys
         * back from a button to the widget, the next gives them to the dock —
         * and a seam that is not declared is a key nobody can find.
         */
        @Override public List<KeyBinding> keys() {
            return List.of(
                    KeyBinding.of(Key.ESCAPE, "from a choice: the keys come back to the opener, and the room is not left"),
                    KeyBinding.of(Key.ESCAPE, "from the opener: the keys go up to the dock"));
        }
    }

    public static final TabOpenerModule INSTANCE = new TabOpenerModule();

    @Override public String title() { return "Tab opener"; }

    @Override
    public ImportsFor<TabOpenerModule> imports() {
        return ImportsFor.<TabOpenerModule>builder()
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_opener(),
                        new PaneStyles.mtp_opener_note(),
                        new PaneStyles.mtp_opener_grid()
                ), PaneStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabOpenerModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TabOpener()));
    }
}
