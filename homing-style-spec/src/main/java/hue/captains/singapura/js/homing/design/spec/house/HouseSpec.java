package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ClasspathCssResolver;
import hue.captains.singapura.js.homing.design.spec.ReadSpec;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseEvents;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRoleCatalogue;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house in the typed spec stack (RFC 0067, D53): each house component module's standalones in Base, and each house
 * style's, read and held by {@link ReadSpec} with the house's words.
 */
public record HouseSpec() implements StatelessFunctionalObject {

    public static final HouseSpec INSTANCE = new HouseSpec();

    /** Each house component module, its standalones in Base. */
    public static final List<BaseSpecModule<BaseScheme, Base>> BASE = List.of(
            UiElementsBaseSpec.INSTANCE,
            UiIconsBaseSpec.INSTANCE,
            UiDialogBaseSpec.INSTANCE,
            UiFloatingBaseSpec.INSTANCE,
            UiMenuBaseSpec.INSTANCE,
            UiMonitorsBaseSpec.INSTANCE,
            UiPanesBaseSpec.INSTANCE,
            UiPanZoomBaseSpec.INSTANCE,
            UiSplitGridBaseSpec.INSTANCE,
            UiPreferencesBaseSpec.INSTANCE,
            MpaBaseSpec.INSTANCE,
            UiControlPanelBaseSpec.INSTANCE);

    /** Each house style's standalones. */
    public static final List<SpecModule<BaseScheme, ?>> STYLES = List.of(
            EditorialSpec.INSTANCE,
            NeoBrutalismSpec.INSTANCE,
            NeoFuturismSpec.INSTANCE,
            NeumorphismSpec.INSTANCE,
            GlassmorphismSpec.INSTANCE,
            RetroFuturismSpec.INSTANCE,
            SketchySpec.INSTANCE);

    /** The house, read and held, its content found on the classpath. */
    public SpecRegistry registry() {
        return ReadSpec.INSTANCE.read(BASE, STYLES, HouseRoleCatalogue.INSTANCE.roles(), HouseStates.ALL, HouseEvents.ALL, ClasspathCssResolver.INSTANCE);
    }
}
