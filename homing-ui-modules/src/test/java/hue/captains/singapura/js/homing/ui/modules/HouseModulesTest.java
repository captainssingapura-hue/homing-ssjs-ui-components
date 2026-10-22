package hue.captains.singapura.js.homing.ui.modules;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.BaseModule;
import hue.captains.singapura.js.homing.design.styling.StyleBundle;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.StyleModule;
import hue.captains.singapura.js.homing.design.styling.StyleRegistryBuilder;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's modules, collected from its jars as they stand (RFC 0067, D50): a base module for each house component
 * module, every component's look in Base bundled with it; a module for each house style; and the builder takes them -
 * the house's taxonomy, read from the modules with its words, the one it reads itself.
 */
class HouseModulesTest {

    private static final HouseModules MODULES = HouseModules.INSTANCE;

    @Test
    void aBaseModule_forEachHouseComponentModule() {
        List<BaseModule<BaseScheme, Base>> base = MODULES.base();
        assertEquals(HouseTaxonomy.MODULES, MODULES.definitions(), "the house's jars' modules: nothing found on the classpath (D51)");
        assertEquals(HouseTaxonomy.MODULES, base.stream().map(BaseModule::components).toList(), "one for each, in order");
        for (BaseModule<BaseScheme, Base> m : base) {
            assertEquals(Base.INSTANCE, m.style());
            var bundled = new HashSet<Component<?>>();
            for (StyleBundle<BaseScheme, Base> b : m.bundles()) bundled.add(b.component());
            assertTrue(m.components().components().containsAll(bundled), m.components().getClass().getSimpleName() + " bundles only its own");
        }
    }

    @Test
    void eachHouseStyle_aModule_anotherStyleNone() {
        for (DesignStyle<BaseScheme> s : HouseModules.STYLE_JARS.keySet()) {
            StyleModule<BaseScheme, DesignStyle<BaseScheme>> m = MODULES.style(s);
            assertEquals(s, m.style());
            assertTrue(!m.bundles().isEmpty(), s.token() + " refines some component");
        }
        assertEquals(7, HouseModules.STYLE_JARS.size());
        String none = assertThrows(IllegalArgumentException.class, () -> MODULES.style(Base.INSTANCE)).getMessage();
        assertEquals("base is no house style: it has no jar of the house's", none);
    }

    @Test
    void theBuilderTakesThem_theHousesTaxonomyAsItReadsItself() {
        StyleManager styles = new StyleRegistryBuilder<>(MODULES.base(), MODULES.roles(), MODULES.states())
                .style(Editorial.INSTANCE, MODULES.style(Editorial.INSTANCE))
                .style(Sketchy.INSTANCE, MODULES.style(Sketchy.INSTANCE))
                .build();
        var house = HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES);
        assertEquals(new HashSet<>(house.components()), new HashSet<>(styles.taxonomy().components()));
        assertEquals(new HashSet<>(house.castings()), new HashSet<>(styles.taxonomy().castings()));
        assertEquals(house.roles(), styles.taxonomy().roles(), "the catalogue whole, in its order");
        assertEquals(house.states(), styles.taxonomy().states());
        assertEquals(house.castings().size(), styles.implementations(Base.INSTANCE).size(), "Base gives every casting");
    }
}
