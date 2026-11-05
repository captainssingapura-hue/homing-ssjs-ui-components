package hue.captains.singapura.js.homing.ui.jarinstance;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.SchemeRegistry;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.design.styling.CollectModules;
import hue.captains.singapura.js.homing.design.styling.PaletteRegistry;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.StyleRegistryBuilder;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BasePaletteDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Harbour;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRoleCatalogue;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;
import java.util.Map;

/**
 * A house jar's own instance, for its tests (RFC 0067, D51): what an instance of that jar alone would serve. Its
 * component modules - the jar's, and those of the house jars it is built on, whose components are its parts - each
 * component with its look in Base, collected from those jars' declarations - a look they give a component another jar
 * casts, its owner not among them, set aside: no casting of this instance; read in the house's words; Base served, in
 * Harbour. A test that serves what is made from the instance's taxonomy - the castings, the leaves, the states - hands
 * it to what it serves, as a site hands over its own; each call makes one, so a jar's test base keeps the one it makes.
 */
public record JarInstance() implements StatelessFunctionalObject {

    public static final JarInstance INSTANCE = new JarInstance();

    /** The styling of these modules, their looks in Base from these jars' declarations: made anew by each call. */
    public Styling styling(List<? extends ComponentModule> modules, List<? extends StyleDeclaration> jars) {
        StyleManager styles = new StyleRegistryBuilder<>(CollectModules.INSTANCE.baseWithin(Base.INSTANCE, modules, jars),
                HouseRoleCatalogue.INSTANCE.roles(), HouseStates.INSTANCE.all()).build();
        SchemeRegistry schemes = new SchemeRegistry(List.of(BaseColours.INSTANCE));
        return new Styling(styles, List.<DesignStyle<?>>of(Base.INSTANCE), new PaletteRegistry(List.of(BasePaletteDeclaration.INSTANCE), schemes),
                Map.<DesignStyle<?>, ColorPalette<?>>of(Base.INSTANCE, Harbour.INSTANCE));
    }
}
