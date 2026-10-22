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
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The clickable card: a card that is a button - the plain card's frame which, pressed by the hand or
 * by Enter or Space, does its one thing. A simple component on its own {@code <div role="button">},
 * made on the branch its owner hands it: handed the page's steward, a member of the keyboard party
 * under that branch's name, told where the keys are on it and setting its own Keyboard state (RFC 0071, D16).
 */
public record ClickableCardModule() implements DomModule<ClickableCardModule>, ImplFor<HouseContainers.ClickableCard> {

    /** {@code new ClickableCard(branch, new FixedClickableCard(), place?)}; {@code joinKeys(steward, id?)}, once; {@code root}, {@code size}, {@code aspect}, {@code keys}, {@code clicks}, {@code onChange(StateClicks, listener)}, {@code key}, {@code dispose}. */
    public record ClickableCard() implements BranchComponent<ClickableCardModule>, NeedKeyboard {
        @Override public String summary() { return "A card that is a button: pressed, it does its one thing; it lifts under the hand and takes the keys."; }
        /** The card is a button to the keyboard too: through the party. */
        @Override public List<KeyBinding> keys() { return KeyBinding.each("the card's click", Key.ENTER, Key.SPACE); }
    }

    public static final ClickableCardModule INSTANCE = new ClickableCardModule();

    @Override
    public ImportsFor<ClickableCardModule> imports() {
        return ImportsFor.<ClickableCardModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CardBehaviorModule.CardBehavior()), CardBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_card()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ClickableCardModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ClickableCard()));
    }
}
