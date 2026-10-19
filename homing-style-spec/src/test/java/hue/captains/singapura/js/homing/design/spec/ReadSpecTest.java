package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.design.spec.house.EditorialSpec;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.spec.house.UiElementsBaseSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseEvents;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRoleCatalogue;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The reader's rules bite (RFC 0067, D54): the house read with one thing changed is refused, naming what - a jar assigning
 * a component it does not say it styles, a component assigned twice in a layer, the base leaving a component out, a
 * layer leaving out a role of a component's set (D55) - the base, or a style assigning a component only in part.
 */
class ReadSpecTest {

    private static final ComponentAssignment<BaseScheme, Base, ?> PLAIN = UiElementsBaseSpec.INSTANCE.assignments().stream()
            .filter(a -> a.component().equals(HouseControls.PlainButton.INSTANCE)).findFirst().orElseThrow();

    @Test
    void theHouse_reads() {
        assertTrue(HouseSpec.INSTANCE.registry().assignments().get(Base.INSTANCE).size() > 0);
    }

    @Test
    void aJar_assigningAComponentItDoesNotSayItStyles_isRefused() {
        refused(HouseSpec.BASE, styles(new Style(List.of(UiIconsComponentModule.INSTANCE), List.of(editorial(HouseControls.PlainButton.INSTANCE)))),
                "which UiElementsComponentModule brings - no module it names");
    }

    @Test
    void aComponentAssignedTwiceInALayer_isRefused() {
        refused(HouseSpec.BASE, styles(new Style(EditorialSpec.INSTANCE.modules(), List.of(editorial(HouseControls.PlainButton.INSTANCE)))),
                "plain-button is assigned twice in editorial");
    }

    @Test
    void theBase_leavingAComponentOut_isRefused() {
        refused(bases(a -> a.component().equals(HouseControls.PlainButton.INSTANCE) ? null : a), HouseSpec.STYLES,
                "plain-button has no assignment in the base");
    }

    @Test
    void theBase_leavingOutARoleOfAComponentsSet_isRefused() {
        refused(bases(a -> a.equals(PLAIN) ? without(PLAIN, HouseDoing.Action.INSTANCE) : a), HouseSpec.STYLES,
                "base assigns plain-button and not plain-button_action: a layer assigns every casting of a component it assigns");
    }

    @Test
    void aStyle_assigningAComponentInPart_isRefused() {
        var styles = new ArrayList<SpecModule<BaseScheme, ?>>(HouseSpec.STYLES);
        styles.set(0, new Style(EditorialSpec.INSTANCE.modules(), EditorialSpec.INSTANCE.assignments().stream()
                .map(a -> a.component().equals(HouseControls.PlainButton.INSTANCE) ? editorial(HouseControls.PlainButton.INSTANCE) : a).toList()));
        refused(HouseSpec.BASE, styles, "editorial assigns plain-button and not plain-button_go, plain-button_cancel, plain-button_action");
    }

    /** Editorial's own assignment of a component, its roles left out. */
    private static ComponentAssignment<BaseScheme, Editorial, ?> editorial(Component<?> component) {
        ComponentAssignment<BaseScheme, Editorial, ?> a = EditorialSpec.INSTANCE.assignments().stream()
                .filter(x -> x.component().equals(component)).findFirst().orElseThrow();
        return alone(a);
    }

    private static <C extends Component<?>> ComponentAssignment<BaseScheme, Editorial, C> alone(ComponentAssignment<BaseScheme, Editorial, C> a) {
        return new ComponentAssignment<>(a.component(), a.alone(), List.of());
    }

    private static <C extends Component<?>> ComponentAssignment<BaseScheme, Base, C> without(ComponentAssignment<BaseScheme, Base, C> a, Role<?> role) {
        return new ComponentAssignment<>(a.component(), a.alone(), a.roles().stream().filter(r -> !r.role().equals(role)).toList());
    }

    /** The house's base modules, UiElements' assignments each changed - or left out, where the change gives none. */
    private static List<BaseSpecModule<BaseScheme, Base>> bases(UnaryOperator<ComponentAssignment<BaseScheme, Base, ?>> change) {
        var out = new ArrayList<BaseSpecModule<BaseScheme, Base>>(HouseSpec.BASE);
        var changed = new ArrayList<ComponentAssignment<BaseScheme, Base, ?>>();
        for (ComponentAssignment<BaseScheme, Base, ?> a : UiElementsBaseSpec.INSTANCE.assignments()) {
            ComponentAssignment<BaseScheme, Base, ?> c = change.apply(a);
            if (c != null) changed.add(c);
        }
        out.set(out.indexOf(UiElementsBaseSpec.INSTANCE), new Elements(List.copyOf(changed)));
        return out;
    }

    /** The house's styles, and one more module of Editorial's. */
    private static List<SpecModule<BaseScheme, ?>> styles(SpecModule<BaseScheme, ?> more) {
        var out = new ArrayList<SpecModule<BaseScheme, ?>>(HouseSpec.STYLES);
        out.add(more);
        return out;
    }

    private static void refused(List<? extends BaseSpecModule<?, ?>> bases, List<? extends SpecModule<?, ?>> styles, String says) {
        var e = assertThrows(IllegalArgumentException.class, () -> ReadSpec.INSTANCE.read(bases, styles, HouseRoleCatalogue.INSTANCE.roles(),
                HouseStates.ALL, HouseEvents.ALL, new ClasspathCssResolver("homing-css/")));
        assertTrue(e.getMessage().contains(says), "refused, and saying \"" + says + "\":\n" + e.getMessage());
    }

    /** UiElements' base module, its assignments given. */
    private record Elements(List<ComponentAssignment<BaseScheme, Base, ?>> assignments) implements BaseSpecModule<BaseScheme, Base> {
        @Override public Base style() { return Base.INSTANCE; }
        @Override public ComponentModule components() { return UiElementsBaseSpec.INSTANCE.components(); }
    }

    /** A module of Editorial's, its modules and assignments given. */
    private record Style(List<? extends ComponentModule> modules, List<ComponentAssignment<BaseScheme, Editorial, ?>> assignments)
            implements SpecModule<BaseScheme, Editorial> {
        @Override public Editorial style() { return Editorial.INSTANCE; }
    }
}
