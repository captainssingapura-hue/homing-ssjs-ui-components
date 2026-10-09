package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.HrefManager;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The summary card: sums one thing up - its title, how it is sorted, what it is about - and leads
 * to it. A branch component: the caller makes a sub-branch for it; the card mints its tree on it
 * and offers {@code root}. It holds no state, fetches nothing and navigates on its own nowhere - its
 * link is set through the href manager, and that is the only thing beyond {@code css} it asks of
 * the base.
 */
public record SummaryCardModule() implements DomModule<SummaryCardModule>, ImplFor<HouseContainers.SummaryCard> {

    /** A branch component: {@code new SummaryCard(branch, { title, badge, text, link, size, aspect, onClick, keyboard })}; {@code root}, {@code body}; size, aspect, title, key, dispose. */
    public record SummaryCard() implements BranchComponent<SummaryCardModule>, NeedKeyboard {
        @Override public String summary() { return "A card that sums one thing up and leads to it: a hard frame at the design's measure and aspect, with a head, a body and a foot."; }
        /** With an action, the card is a button to the keyboard too: through the party. */
        @Override public List<KeyBinding> keys() { return KeyBinding.each("the action, on a card that has one", Key.ENTER, Key.SPACE); }
    }

    public static final SummaryCardModule INSTANCE = new SummaryCardModule();

    @Override
    public ImportsFor<SummaryCardModule> imports() {
        return ImportsFor.<SummaryCardModule>builder()
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading(), new HeadingModule.HeadingFactory()), HeadingModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new BadgeModule.Badge(), new BadgeModule.BadgeFactory()), BadgeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption(), new CaptionModule.CaptionFactory()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HrefManager.HrefManagerInstance()), HrefManager.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_card(),
                        new ElementStyles.el_card_action(),
                        new ElementStyles.el_card_head(),
                        new ElementStyles.el_card_title(),
                        new ElementStyles.el_badge(),
                        new ElementStyles.el_card_body(),
                        new ElementStyles.el_card_text(),
                        new ElementStyles.el_card_foot(),
                        new ElementStyles.el_card_link()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SummaryCardModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SummaryCard()));
    }
}
