package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModules;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's taxonomy as its jars define it: each house jar's component module, defined here beside the components it
 * lists, whether or not the jar implementing them is on the classpath - the implementation of each, and Base's look of it,
 * live in its own jar (RFC 0067, appendix The Style Jars, D40, D48). An instance's taxonomy is read from the modules it
 * registers, explicitly, nothing found on the classpath (D46, D51): the house's, and a downstream's beside them. The
 * branches need no listing - they are reached through the components' parents; parts come in with their owners.
 *
 * <p>Beside the modules, the house's role catalogue, its {@link HouseStates tree of states} and its
 * {@link HouseEvents tree of events}: the house's words, not cut by module, but whole - covering the common cases, so a
 * component is built from their words, and a module adds one of its own only where none says what it means.</p>
 */
public record HouseTaxonomy() implements StatelessFunctionalObject {

    public static final HouseTaxonomy INSTANCE = new HouseTaxonomy();

    /**
     * The house's jars' component modules, each defined here beside the components it lists: the flat list an instance
     * of the whole house registers at bootstrap, and all a style jar's check needs of the house's components.
     */
    public static final List<ComponentModule> MODULES = List.of(
            UiElementsComponentModule.INSTANCE, UiIconsComponentModule.INSTANCE, UiDialogComponentModule.INSTANCE,
            UiFloatingComponentModule.INSTANCE, UiMenuComponentModule.INSTANCE, UiMonitorsComponentModule.INSTANCE,
            UiPanesComponentModule.INSTANCE, UiPanZoomComponentModule.INSTANCE, UiSplitGridComponentModule.INSTANCE,
            UiPreferencesComponentModule.INSTANCE, MpaComponentModule.INSTANCE, UiControlPanelComponentModule.INSTANCE);

    /**
     * The taxonomy of the component modules given, in the house's words: their components, a flat list, explicitly -
     * each jar's - their parts with them, transitively; the house's catalogue of roles and its trees of states and
     * events, whole. Nothing found on the classpath.
     */
    public Taxonomy read(List<? extends ComponentModule> modules) {
        return ComponentModules.INSTANCE.read(modules, HouseRoleCatalogue.INSTANCE.roles(), HouseStates.ALL, HouseEvents.ALL);
    }
}
