package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's preferences: the view of them, a field for one setting, the widgets that edit a setting, the overview of
 * a group and its rows. Defined here, with the house's other components, whether or not its implementation is on the
 * classpath; implemented - and drawn by Base - in {@code homing-preferences} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HousePreferences {

    private HousePreferences() {}

    /** One setting: what it is filed under, its title and summary, a note on it, and a way back to the default. Its body is the widget's. */
    public record PreferenceField() implements Composite<HouseBranches.Field> {
        public static final PreferenceField INSTANCE = new PreferenceField();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Category.INSTANCE).one(),
                           DSL.part(HouseText.Heading4.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Lede.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Reset.INSTANCE).one());
        }
    }

    /** A setting chosen from a few options. */
    public record ChoiceWidget() implements Composite<HouseBranches.Field> {
        public static final ChoiceWidget INSTANCE = new ChoiceWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** A setting picked along a scale, read out. */
    public record ScaleWidget() implements Composite<HouseBranches.Field> {
        public static final ScaleWidget INSTANCE = new ScaleWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseControls.Range.INSTANCE).as(HouseDoing.Adjust.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }

    /** A setting that is on or off. */
    public record ToggleWidget() implements Composite<HouseBranches.Field> {
        public static final ToggleWidget INSTANCE = new ToggleWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseControls.Switch.INSTANCE).as(HouseDoing.Toggle.INSTANCE).one());
        }
    }

    /** The settings' tree as a flat list, depth as indentation: choosing a row is choosing what is shown beside it. */
    public record ListMasterWidget() implements Composite<HouseBranches.Field> {
        public static final ListMasterWidget INSTANCE = new ListMasterWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.ListRow.INSTANCE).as(HouseDoing.Choice.INSTANCE).atLeast(1));
        }
    }

    /** A group of settings: its head, its children listed with their values, and a way back to the defaults. */
    public record OverviewWidget() implements Composite<HouseBranches.Field> {
        public static final OverviewWidget INSTANCE = new OverviewWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Category.INSTANCE).one(),
                           DSL.part(HouseText.Heading4.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Lede.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(SettingRow.INSTANCE).as(HouseShaping.Entry.INSTANCE).any(),
                           DSL.part(HouseMarks.Separator.INSTANCE).as(HouseShaping.Break.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Reset.INSTANCE).one());
        }
    }

    /**
     * The site's preferences, a widget: the index of its settings and the one chosen, side by side. A split grid lays
     * them out, fixed, so the line between them is the grid's; the widgets are held in the slots.
     */
    public record PreferencesView() implements Composite<HouseBranches.View> {
        public static final PreferencesView INSTANCE = new PreferencesView();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseSplitGrid.SplitGrid.INSTANCE).as(HouseShaping.Layout.INSTANCE).one());
        }
    }

    /** One setting of a group, listed with its value. Its name is its own. */
    public record SettingRow() implements Composite<HouseBranches.Item> {
        public static final SettingRow INSTANCE = new SettingRow();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Item parent() { return HouseBranches.Item.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
