package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Item;

import java.util.List;

/** The house's items: one of many alike, in a menu, a list or a set of choices. */
public final class HouseItems {

    private HouseItems() {}

    /** One choice of a menu, with its glyph, its hint and its way into a submenu. Its name is its own text. */
    public record MenuItem() implements Component<Item> {
        public static final MenuItem INSTANCE = new MenuItem();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Hint.INSTANCE).optional(),
                           DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Disclose.INSTANCE).optional());
        }
    }

    /** One row of a list. */
    public record ListRow() implements Component<Item> {
        public static final ListRow INSTANCE = new ListRow();
        @Override public Item parent() { return Item.INSTANCE; }
    }

    /** One choice of a setting, with a note on what it means. */
    public record ChoiceOption() implements Component<Item> {
        public static final ChoiceOption INSTANCE = new ChoiceOption();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).optional());
        }
    }

    /** One setting of a group, listed with its value. Its name is its own. */
    public record SettingRow() implements Component<Item> {
        public static final SettingRow INSTANCE = new SettingRow();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
