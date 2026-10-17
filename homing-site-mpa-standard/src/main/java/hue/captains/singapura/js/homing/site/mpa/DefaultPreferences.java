package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.preferences.ListMasterWidgetModule;
import hue.captains.singapura.js.homing.preferences.OverviewWidgetModule;
import hue.captains.singapura.js.homing.preferences.PreferenceNode;
import hue.captains.singapura.js.homing.preferences.PreferenceTree;
import hue.captains.singapura.js.homing.preferences.PreferencesRegistry;
import hue.captains.singapura.js.homing.preferences.WidgetProvider;

import java.util.List;
import java.util.Map;

/**
 * The preferences a site gets unless it brings its own: the theme - its style and its palette, each its
 * own preference - under an overview, with the flat list as the master. Enough for the bar's button
 * to open something on every site; a site with more to remember extends
 * {@link PreferencesRegistry} itself and gives it to {@link StandardMpa}.
 */
public final class DefaultPreferences extends PreferencesRegistry {

    static final PreferenceTree TREE = PreferenceTree.of(
            PreferenceNode.of("preferences", "Preferences", "What this site remembers for you, in this browser.",
                    WidgetProvider.of(OverviewWidgetModule.INSTANCE, Map.of(
                            "label", "Preferences",
                            "summary", "What this site remembers for you, in this browser: the design it wears - its style, and its colours.",
                            "settings", List.of(Map.of("name", "style", "label", "Style"), Map.of("name", "palette", "label", "Colours")))),
                    PreferenceNode.of("theme", "Theme", "The design this site wears, and the colours it wears it in.",
                            WidgetProvider.of(ThemeWidgetModule.INSTANCE, Map.of(
                                    "label", "Style",
                                    "summary", "The styles this site offers, then the colours the chosen one is offered in - each kept on its own. A pick switches its own sheet on the page; another tab follows.")))));

    // Declared after the tree it is built from: a static initialiser runs in order.
    public static final DefaultPreferences INSTANCE = new DefaultPreferences();

    private DefaultPreferences() {
        super(TREE, WidgetProvider.of(ListMasterWidgetModule.INSTANCE));
    }
}
