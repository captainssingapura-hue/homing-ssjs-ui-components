package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.preferences.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The house's standard site, as this module adds it to the house's taxonomy: the chrome, the
 * site's brand, the button to its preferences, and the theme chosen. In the tree if and only if
 * this module is on the classpath.
 */
public record HouseSite() implements TaxonomyExtension {

    public static final HouseSite INSTANCE = new HouseSite();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(Brand.INSTANCE, PreferencesButton.INSTANCE, MpaChrome.INSTANCE, ThemeWidget.INSTANCE);
    }

    /** The site's mark and name, a way home. */
    public record Brand() implements Component<HouseBranches.Control> {
        public static final Brand INSTANCE = new Brand();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Control parent() { return HouseBranches.Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseText.Wordmark.INSTANCE).as(HouseSaying.Name.INSTANCE).one());
        }
    }

    /** The button that opens the site's preferences in a dialog, the preferences' view its page. */
    public record PreferencesButton() implements Component<HouseBranches.Control> {
        public static final PreferencesButton INSTANCE = new PreferencesButton();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Control parent() { return HouseBranches.Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseContainers.Dialog.INSTANCE).as(HouseShaping.Window.INSTANCE).optional(),
                           DSL.part(HousePreferences.PreferencesView.INSTANCE).as(HouseShaping.Page.INSTANCE).optional());
        }
    }

    /** The site's chrome: its brand, its trail, its preferences, and the page's room - which is the app's. */
    public record MpaChrome() implements Component<HouseBranches.Container> {
        public static final MpaChrome INSTANCE = new MpaChrome();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.Section.INSTANCE).as(HouseShaping.Head.INSTANCE).one(),
                           DSL.part(Brand.INSTANCE).as(HouseDoing.Home.INSTANCE).one(),
                           DSL.part(HouseContainers.Trail.INSTANCE).as(HouseDoing.Path.INSTANCE).one(),
                           DSL.part(PreferencesButton.INSTANCE).as(HouseDoing.Preferences.INSTANCE).one());
        }
    }

    /** The theme chosen: a style, and a palette for it. */
    public record ThemeWidget() implements Component<HouseBranches.Field> {
        public static final ThemeWidget INSTANCE = new ThemeWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HousePreferences.PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Status.INSTANCE).optional(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Style.INSTANCE).any(),
                           DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Subtitle.INSTANCE).optional(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Palette.INSTANCE).any());
        }
    }
}
