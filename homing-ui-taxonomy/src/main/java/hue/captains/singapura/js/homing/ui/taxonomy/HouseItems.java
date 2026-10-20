package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Item;

import java.util.List;

/** The house's items: one of many alike, in a menu, a list or a set of choices. */
public final class HouseItems {

    private HouseItems() {}

    /** Pressed and let go: each item's one event, besides its states' changes. */
    private static final List<EventType<?>> CLICKED = List.of(HouseEvents.Click.INSTANCE);

    /** One choice of a menu, with its glyph, its hint and its way into a submenu. Its name is its own text. */
    public record MenuItem() implements Composite<Item>, WithRoles<HouseRoleSets.Choosing> {
        public static final MenuItem INSTANCE = new MenuItem();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /** The pointer over it; usable, or off; where its menu's cursor is - the one Enter would pick. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Enabled.INSTANCE, HouseStates.Pointed.INSTANCE); }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.anyOf(HouseBranches.Icon.INSTANCE).as(HouseShaping.Leading.INSTANCE).optional(Cardinality.DYNAMIC),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Hint.INSTANCE).optional(),
                           DSL.part(HouseIcons.DiscloseIcon.INSTANCE).as(HouseShaping.Disclose.INSTANCE).optional());
        }
    }

    /** One row of a list. What it shows is its owner's. */
    public record ListRow() implements Leaf<Item>, WithRoles<HouseRoleSets.Choosing> {
        public static final ListRow INSTANCE = new ListRow();
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /** The pointer over it; picked from the list; the one that is where the user is; where the page's keys are, as its owner tells it. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Selected.INSTANCE,
                HouseStates.Current.INSTANCE, HouseStates.Keyboard.INSTANCE); }
        @Override public List<EventType<?>> events() { return CLICKED; }
    }

    /** One choice of a setting, with a note on what it means. */
    public record ChoiceOption() implements Composite<Item>, WithRoles<HouseRoleSets.Choosing> {
        public static final ChoiceOption INSTANCE = new ChoiceOption();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /** The pointer over it; the focus on it; the one picked. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Selected.INSTANCE); }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).optional());
        }
    }

}
