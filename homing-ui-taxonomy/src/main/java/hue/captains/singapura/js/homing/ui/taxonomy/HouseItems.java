package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseKinds.Item;

import java.util.List;

/** The house's items: one of many alike, in a menu, a list or a set of choices. */
public final class HouseItems {

    private HouseItems() {}

    /** One choice of a menu, with its glyph, its label, its hint and its way into a submenu. */
    public record MenuItem() implements Component<Item> {
        public static final MenuItem INSTANCE = new MenuItem();
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Glyph.INSTANCE, Label.INSTANCE, Hint.INSTANCE, Disclose.INSTANCE); }

        public record Glyph()    implements Role<HouseMarks.Icon>    { public static final Glyph INSTANCE = new Glyph();       @Override public HouseMarks.Icon base()    { return HouseMarks.Icon.INSTANCE; } }
        public record Label()    implements Role<HouseText.Label>    { public static final Label INSTANCE = new Label();       @Override public HouseText.Label base()    { return HouseText.Label.INSTANCE; } }
        public record Hint()     implements Role<HouseText.Caption>  { public static final Hint INSTANCE = new Hint();         @Override public HouseText.Caption base()  { return HouseText.Caption.INSTANCE; } }
        public record Disclose() implements Role<HouseMarks.Icon>    { public static final Disclose INSTANCE = new Disclose(); @Override public HouseMarks.Icon base()    { return HouseMarks.Icon.INSTANCE; } }
    }

    /** One row of a list. */
    public record ListRow() implements Component<Item> {
        public static final ListRow INSTANCE = new ListRow();
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Label.INSTANCE); }

        public record Label() implements Role<HouseText.Label> { public static final Label INSTANCE = new Label(); @Override public HouseText.Label base() { return HouseText.Label.INSTANCE; } }
    }

    /** One choice of a setting, with a note on what it means. */
    public record ChoiceOption() implements Component<Item> {
        public static final ChoiceOption INSTANCE = new ChoiceOption();
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Note.INSTANCE); }

        public record Note() implements Role<HouseText.Caption> { public static final Note INSTANCE = new Note(); @Override public HouseText.Caption base() { return HouseText.Caption.INSTANCE; } }
    }
}
