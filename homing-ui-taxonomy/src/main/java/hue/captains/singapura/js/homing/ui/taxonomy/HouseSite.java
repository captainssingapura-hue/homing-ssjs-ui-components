package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import hue.captains.singapura.js.homing.component.taxonomy.Room;

import java.util.List;

/**
 * The house's standard site: the chrome and the theme chosen. The site's brand and the button to its preferences are
 * the common tree's, so the styles can answer them. Defined here, with the house's other components, whether or not its
 * implementation is on the classpath; implemented - and drawn by Base - in {@code homing-site-mpa-standard} (RFC 0067,
 * appendix The Style Jars, D40).
 */
public final class HouseSite {

    private HouseSite() {}

    /**
     * The site's chrome: a widget, with no design of its own - the site's bar at its head, its preferences
     * handed to the bar's end, and the slot the page's app is loaded in, which is what it shows; laid out by
     * a layout of its own. It stands on the
     * page's ground, which the page wears.
     */
    public record MpaChrome() implements Composite<HouseBranches.View> {
        public static final MpaChrome INSTANCE = new MpaChrome();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.SiteBar.INSTANCE).as(HouseShaping.Head.INSTANCE).one(),
                           DSL.part(HouseControls.PreferencesButton.INSTANCE).as(HouseDoing.Preferences.INSTANCE).optional());
        }
        /** Its page: the room what the site shows is put in - its own. */
        @Override public List<Room> rooms() { return List.of(new Room("page")); }
    }

    /** The theme chosen: a style, and a palette for it. */
    public record ThemeWidget() implements Composite<HouseBranches.Field> {
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
