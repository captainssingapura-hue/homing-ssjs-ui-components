package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The house's preferences, as this module adds them to the house's taxonomy: the view of them,
 * a field for one setting, the widgets that edit a setting, the overview of a group and its rows.
 * In the tree if and only if this module is on the classpath.
 */
public record HousePreferences() implements TaxonomyExtension {

    public static final HousePreferences INSTANCE = new HousePreferences();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(PreferenceField.INSTANCE, ChoiceWidget.INSTANCE, ScaleWidget.INSTANCE, ToggleWidget.INSTANCE,
                       ListMasterWidget.INSTANCE, OverviewWidget.INSTANCE, PreferencesView.INSTANCE,
                       SettingRow.INSTANCE);
    }

    /** One setting: what it is filed under, its title and summary, a note on it, and a way back to the default. Its body is the widget's. */
    public record PreferenceField() implements Component<HouseBranches.Field> {
        public static final PreferenceField INSTANCE = new PreferenceField();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Category.INSTANCE).one(),
                           DSL.part(HouseText.Heading.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Lede.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Reset.INSTANCE).one());
        }
    }

    /** A setting chosen from a few options. */
    public record ChoiceWidget() implements Component<HouseBranches.Field> {
        public static final ChoiceWidget INSTANCE = new ChoiceWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** A setting picked along a scale, read out. */
    public record ScaleWidget() implements Component<HouseBranches.Field> {
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
    public record ToggleWidget() implements Component<HouseBranches.Field> {
        public static final ToggleWidget INSTANCE = new ToggleWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseControls.Switch.INSTANCE).as(HouseDoing.Toggle.INSTANCE).one());
        }
    }

    /** The settings' tree as a flat list, depth as indentation: choosing a row is choosing what is shown beside it. */
    public record ListMasterWidget() implements Component<HouseBranches.Field> {
        public static final ListMasterWidget INSTANCE = new ListMasterWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.ListRow.INSTANCE).as(HouseDoing.Choice.INSTANCE).atLeast(1));
        }
    }

    /** A group of settings: its head, its children listed with their values, and a way back to the defaults. */
    public record OverviewWidget() implements Component<HouseBranches.Field> {
        public static final OverviewWidget INSTANCE = new OverviewWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Category.INSTANCE).one(),
                           DSL.part(HouseText.Heading.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Lede.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(SettingRow.INSTANCE).as(HouseShaping.Entry.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Reset.INSTANCE).one());
        }
    }

    /** The site's preferences: the index of its settings, and the one chosen. The widgets are held in the slots. */
    public record PreferencesView() implements Component<HouseBranches.View> {
        public static final PreferencesView INSTANCE = new PreferencesView();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.WidgetSlot.INSTANCE).as(HouseShaping.Index.INSTANCE).one(),
                           DSL.part(HouseRegions.WidgetSlot.INSTANCE).as(HouseShaping.Detail.INSTANCE).one());
        }
    }

    /** One setting of a group, listed with its value. Its name is its own. */
    public record SettingRow() implements Component<HouseBranches.Item> {
        public static final SettingRow INSTANCE = new SettingRow();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Item parent() { return HouseBranches.Item.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
