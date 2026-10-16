package hue.captains.singapura.js.homing.ui.jarinstance;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.SchemeRegistry;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.design.styling.CollectModules;
import hue.captains.singapura.js.homing.design.styling.InstalledStyling;
import hue.captains.singapura.js.homing.design.styling.PaletteRegistry;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.StyleRegistryBuilder;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BasePaletteDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Harbour;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseEvents;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRoleCatalogue;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A house jar's own instance, for its tests (RFC 0067, D51): what an instance of that jar alone would serve. Its
 * component modules - the jar's, and those of the house jars it is built on, whose components are its parts - each
 * component with its look in Base, collected from those jars' declarations - a look they give a component another jar
 * casts, its owner not among them, set aside: no casting of this instance; read in the house's words; Base served, in
 * Harbour. A test that serves what is made from the instance's taxonomy - the castings, the leaves, the states - installs
 * it, as a site installs its own at bootstrap; each is made once for the modules and jars given, so every test of a jar
 * installs the same one.
 */
public record JarInstance() implements StatelessFunctionalObject {

    public static final JarInstance INSTANCE = new JarInstance();

    /** The styling of these modules, their looks in Base from these jars' declarations: made the first time, the same after. */
    public Styling styling(List<? extends ComponentModule> modules, List<? extends StyleDeclaration> jars) {
        return Made.STYLINGS.computeIfAbsent(List.of(List.copyOf(modules), List.copyOf(jars)), k -> make(modules, jars));
    }

    /** Installs the styling of these modules: once in a test's run, the same asked again. */
    public void install(List<? extends ComponentModule> modules, List<? extends StyleDeclaration> jars) {
        InstalledStyling.install(styling(modules, jars));
    }

    private Styling make(List<? extends ComponentModule> modules, List<? extends StyleDeclaration> jars) {
        StyleManager styles = new StyleRegistryBuilder<>(CollectModules.INSTANCE.baseWithin(Base.INSTANCE, modules, jars),
                HouseRoleCatalogue.INSTANCE.roles(), HouseStates.ALL, HouseEvents.ALL).build();
        SchemeRegistry schemes = new SchemeRegistry(List.of(BaseColours.INSTANCE));
        return new Styling(styles, List.<DesignStyle<?>>of(Base.INSTANCE), new PaletteRegistry(List.of(BasePaletteDeclaration.INSTANCE), schemes),
                Map.<DesignStyle<?>, ColorPalette<?>>of(Base.INSTANCE, Harbour.INSTANCE));
    }

    /** Each styling made, by its modules and jars. */
    private static final class Made {
        private Made() {}
        static final Map<List<List<?>>, Styling> STYLINGS = new ConcurrentHashMap<>();
    }
}
