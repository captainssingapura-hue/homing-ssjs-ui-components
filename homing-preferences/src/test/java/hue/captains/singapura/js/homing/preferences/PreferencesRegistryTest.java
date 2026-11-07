package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.conformance.rules.CrateDependencyRule;
import hue.captains.singapura.js.homing.conformance.rules.OrphanCheck;
import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.ModuleNameResolver;
import hue.captains.singapura.js.homing.core.PartialModulePath;
import hue.captains.singapura.js.homing.tree.TreeLevel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The tree levels and addresses its nodes; the registry stamps modules, exports and params; the crate holds. */
class PreferencesRegistryTest {

    static final ModuleNameResolver RESOLVER = (EsModule<?> m) ->
            new PartialModulePath("/module?class=" + m.getClass().getCanonicalName());

    static PreferenceTree tree() {
        return new PreferenceTree(new PreferenceNode("preferences", "Preferences", "All of them",
                new WidgetProvider(OverviewWidgetModule.INSTANCE, Map.of("label", "Preferences", "settings", List.of())),
                new PreferenceNode("theme", "Theme", "", new WidgetProvider(ChoiceWidgetModule.INSTANCE,
                        Map.of("name", "theme", "options", List.of(Map.of("value", "a", "label", "A"))))),
                new PreferenceNode("editor", "Editor", "How text is shown", new WidgetProvider(OverviewWidgetModule.INSTANCE),
                        new PreferenceNode("font-size", "Font size", "", new WidgetProvider(ScaleWidgetModule.INSTANCE,
                                Map.of("name", "editor/font-size", "min", 12, "max", 24, "default", 16))),
                        new PreferenceNode("wrap", "Wrap lines", "", new WidgetProvider(ToggleWidgetModule.INSTANCE,
                                Map.of("name", "editor/wrap", "default", true))))));
    }

    @Test
    void levelsByDepthAndAddressesByPath() {
        var t = tree();
        assertEquals(List.of("preferences", "preferences/theme", "preferences/editor",
                             "preferences/editor/font-size", "preferences/editor/wrap"), t.paths());
        assertEquals(TreeLevel.L0.INSTANCE, t.root().level());
        assertEquals(TreeLevel.L2.INSTANCE, t.at("preferences/editor/wrap").level());
        assertEquals(List.of("preferences/editor/font-size", "preferences/editor/wrap"), t.below("preferences/editor"));
        assertEquals("Wrap lines", t.at("preferences/editor/wrap").label());
    }

    @Test
    void refusesTwoSiblingsWithOneSegment() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new PreferenceTree(
                new PreferenceNode("p", "P", "", new WidgetProvider(OverviewWidgetModule.INSTANCE),
                        new PreferenceNode("x", "X1", "", new WidgetProvider(ToggleWidgetModule.INSTANCE)),
                        new PreferenceNode("x", "X2", "", new WidgetProvider(ToggleWidgetModule.INSTANCE)))));
        assertTrue(ex.getMessage().contains("share the segment 'x'"), ex.getMessage());
    }

    @Test
    void stampsEveryNodeWithItsWidgetModuleExportAndParams() {
        var reg = new PreferencesRegistry(tree(), new WidgetProvider(ChoiceWidgetModule.INSTANCE, Map.of("folder", true))) {};
        String json = reg.json(RESOLVER);
        // the rigid tree, canonical
        assertTrue(json.contains("\"tree\":{\"level\":\"L0\",\"segment\":\"preferences\""), json);
        assertTrue(json.contains("{\"key\":\"displayLabel\",\"valueTag\":\"name\",\"text\":\"Font size\"}"), json);
        // the master: the provider's params plus the tree and the labels
        assertTrue(json.contains("\"master\":{\"export\":\"ChoiceWidget\",\"module\":\"/module?class=hue.captains.singapura.js.homing.preferences.ChoiceWidgetModule\",\"params\":{\"folder\":true,\"labels\":{"), json);
        assertTrue(json.contains("\"labels\":{\"preferences\":\"Preferences\",\"preferences/editor\":\"Editor\""), json);
        // a node: label, summary, widget with typed params
        assertTrue(json.contains("\"preferences/editor/font-size\":{\"label\":\"Font size\",\"summary\":\"\",\"widget\":{\"export\":\"ScaleWidget\",\"module\":\"/module?class=hue.captains.singapura.js.homing.preferences.ScaleWidgetModule\",\"params\":{"), json);
        assertTrue(json.contains("\"min\":12") && json.contains("\"default\":true"), json);
        // the module itself defines the constant
        var lines = reg.selfContent(RESOLVER);
        assertTrue(lines.get(lines.size() - 1).startsWith("const PREFERENCES = Object.freeze({"), lines.toString());
    }

    @Test
    void theCrateHoldsAndImportsOnlyWhatItRequires() {
        assertEquals(List.of(), OrphanCheck.INSTANCE.check(UiPreferencesCrate.INSTANCE));
        assertEquals(List.of(), CrateDependencyRule.INSTANCE.check(UiPreferencesCrate.INSTANCE));
    }

    @Test
    void jsonEscapesWhatWouldCloseAScript() {
        assertEquals("\"a\\u003c/script>\\\"b\\\"\"", Json.INSTANCE.write("a</script>\"b\""));
        var written = new java.util.LinkedHashMap<String, Object>();
        written.put("n", 1); written.put("t", true); written.put("l", java.util.Arrays.asList("x", null));
        assertEquals("{\"l\":[\"x\",null],\"n\":1,\"t\":true}", Json.INSTANCE.write(written));
    }

    @Test
    void aMapsKeysAreSorted_atEveryDepth_howeverItWasBuilt() {
        assertEquals("{\"a\":[{\"x\":1,\"y\":2}],\"b\":{\"c\":3,\"d\":4}}",
                Json.INSTANCE.write(Map.of("b", Map.of("d", 4, "c", 3), "a", List.of(Map.of("y", 2, "x", 1)))));
    }

    @Test
    void theRootStaysTheFirstNode_forTheViewOpensOnTheFirst() {
        var reg = new PreferencesRegistry(tree(), new WidgetProvider(ChoiceWidgetModule.INSTANCE)) {};
        assertTrue(reg.json(RESOLVER).contains("\"nodes\":{\"preferences\":{"), "every other path extends the root's, so it sorts first");
    }
}
