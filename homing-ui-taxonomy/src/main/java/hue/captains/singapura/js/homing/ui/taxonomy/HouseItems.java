package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Room;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Item;

import java.util.List;

/**
 * The house's items: one of many alike, in a menu, a list or a set of choices. A click on an item is no event of its own:
 * what holds it hears it, and its selection changes (RFC 0069, amended 21 Oct).
 */
public final class HouseItems {

    private HouseItems() {}

    /** One choice of a menu, with its glyph, its hint and its way into a submenu. Its name is its own text. */
    public record MenuItem() implements Composite<Item>, WithRoles<HouseRoleSets.Choosing> {
        public static final MenuItem INSTANCE = new MenuItem();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /**
         * The pointer over it; usable, or off; where its menu's cursor is - the one Enter would pick; checked, for one that
         * can be; its rows open, for one that opens rows; and the picks of it, passed on, handled by none: what a pick does,
         * its menu decides (owner, 23 Oct).
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Enabled.INSTANCE, HouseStates.Pointed.INSTANCE,
                HouseStates.Checked.INSTANCE, HouseStates.Expanded.INSTANCE, HouseStates.PickControl.INSTANCE); }
        /**
         * Its own icon, leading it, chosen once; the check, for a row that can be checked, in the same place, shown in place of
         * its own while it is checked - both fixed for its life (owner, 23 Oct: option a); its hint; and its way into its rows.
         */
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.anyOf(HouseBranches.Icon.INSTANCE).as(HouseShaping.Leading.INSTANCE).optional(),
                           DSL.part(HouseIcons.CheckIcon.INSTANCE).as(HouseShaping.Chosen.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Hint.INSTANCE).optional(),
                           DSL.part(HouseIcons.DiscloseIcon.INSTANCE).as(HouseShaping.Disclose.INSTANCE).optional());
        }
        /** Its leading place, kept whether an icon is shown there or not, so the names in a menu line up; and its name. */
        @Override public List<Room> rooms() { return List.of(new Room("lead"), new Room("label")); }
    }

    /** One row of a list. What it shows is its owner's. */
    public record ListRow() implements Leaf<Item>, WithRoles<HouseRoleSets.Choosing> {
        public static final ListRow INSTANCE = new ListRow();
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /** The pointer over it; picked from the list; the one that is where the user is; where the page's keys are, as its owner tells it. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Selected.INSTANCE,
                HouseStates.Current.INSTANCE, HouseStates.Keyboard.INSTANCE); }
    }

    /** One choice of a setting, with a note on what it means. */
    public record ChoiceOption() implements Composite<Item>, WithRoles<HouseRoleSets.Choosing> {
        public static final ChoiceOption INSTANCE = new ChoiceOption();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Item parent() { return Item.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /** The pointer over it; the focus on it; the one picked. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Selected.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).optional());
        }
    }

}
