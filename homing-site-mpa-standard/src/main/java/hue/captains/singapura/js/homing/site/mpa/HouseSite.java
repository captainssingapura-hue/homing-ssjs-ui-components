package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.preferences.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
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
 * The house's standard site, as this module adds it to the house's taxonomy: the chrome and the
 * theme chosen. The site's brand and the button to its preferences are the common tree's, so the
 * styles can answer them. In the tree if and only if
 * this module is on the classpath.
 */
public record HouseSite() implements TaxonomyExtension {

    public static final HouseSite INSTANCE = new HouseSite();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(MpaChrome.INSTANCE, ThemeWidget.INSTANCE);
    }

    /** The site's chrome: its brand, its trail, its preferences, and the page's room - which is the app's. */
    public record MpaChrome() implements Component<HouseBranches.Container> {
        public static final MpaChrome INSTANCE = new MpaChrome();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.Section.INSTANCE).as(HouseShaping.Head.INSTANCE).one(),
                           DSL.part(HouseControls.Brand.INSTANCE).as(HouseDoing.Home.INSTANCE).one(),
                           DSL.part(HouseContainers.Trail.INSTANCE).as(HouseDoing.Path.INSTANCE).one(),
                           DSL.part(HouseControls.PreferencesButton.INSTANCE).as(HouseDoing.Preferences.INSTANCE).one());
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
