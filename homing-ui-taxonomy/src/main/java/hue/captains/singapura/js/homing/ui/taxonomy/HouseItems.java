package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Item;

/** The house's items: one of many alike, in a menu, a list or a set of choices. */
public final class HouseItems {

    private HouseItems() {}

    /** One choice of a menu, with its glyph, its label, its hint and its way into a submenu. */
    public record MenuItem() implements Component<Item> {
        public static final MenuItem INSTANCE = new MenuItem();
        @Override public Item parent() { return Item.INSTANCE; }
    }

    /** One row of a list. */
    public record ListRow() implements Component<Item> {
        public static final ListRow INSTANCE = new ListRow();
        @Override public Item parent() { return Item.INSTANCE; }
    }

    /** One choice of a setting, with a note on what it means. */
    public record ChoiceOption() implements Component<Item> {
        public static final ChoiceOption INSTANCE = new ChoiceOption();
        @Override public Item parent() { return Item.INSTANCE; }
    }
}
