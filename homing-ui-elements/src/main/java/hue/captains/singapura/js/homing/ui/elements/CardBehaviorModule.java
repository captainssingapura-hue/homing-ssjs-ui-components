package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a card that is a button does: a press is its click; it is a button to the browser too, its role and a place in the Tab key's order. */
public record CardBehaviorModule() implements DomModule<CardBehaviorModule> {

    /** {@code new CardBehavior(el, card)}. */
    public record CardBehavior() implements Exportable._Class<CardBehaviorModule> {}

    public static final CardBehaviorModule INSTANCE = new CardBehaviorModule();

    @Override
    public ImportsFor<CardBehaviorModule> imports() {
        return ImportsFor.noImports();
    }

    @Override public ExportsOf<CardBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new CardBehavior())); }
}
