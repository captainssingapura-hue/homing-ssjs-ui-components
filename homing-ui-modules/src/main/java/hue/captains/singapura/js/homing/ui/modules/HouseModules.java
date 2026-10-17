package hue.captains.singapura.js.homing.ui.modules;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.editorial.EditorialStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.glassmorphism.GlassmorphismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.neobrutalism.NeoBrutalismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.neofuturism.NeoFuturismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.neumorphism.NeumorphismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.retrofuturism.RetroFuturismStyleDeclaration;
import hue.captains.singapura.js.homing.design.styles.sketchy.SketchyStyleDeclaration;
import hue.captains.singapura.js.homing.design.styling.BaseModule;
import hue.captains.singapura.js.homing.design.styling.CollectModules;
import hue.captains.singapura.js.homing.design.styling.StyleModule;
import hue.captains.singapura.js.homing.preferences.UiPreferencesStyleDeclaration;
import hue.captains.singapura.js.homing.site.mpa.MpaStyleDeclaration;
import hue.captains.singapura.js.homing.ui.controlpanel.UiControlPanelStyleDeclaration;
import hue.captains.singapura.js.homing.ui.dialog.UiDialogStyleDeclaration;
import hue.captains.singapura.js.homing.ui.elements.UiElementsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingStyleDeclaration;
import hue.captains.singapura.js.homing.ui.icons.UiIconsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.menu.UiMenuStyleDeclaration;
import hue.captains.singapura.js.homing.ui.monitors.UiMonitorsStyleDeclaration;
import hue.captains.singapura.js.homing.ui.panes.UiPanesStyleDeclaration;
import hue.captains.singapura.js.homing.ui.panzoom.UiPanZoomStyleDeclaration;
import hue.captains.singapura.js.homing.ui.splitgrid.UiSplitGridStyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseEvents;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRoleCatalogue;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;
import java.util.Map;

/**
 * The house's modules, collected (RFC 0067, D50): what an instance of the house hands its style registry's builder,
 * gathered from the jars as they stand - the files stay where they are, each jar's style declaration as it is. The base
 * modules: each house component module with its components' looks in Base, from every house jar's declaration, each look
 * in the bundle of the component whose design it is of; then each house style's module, from its own jar. Beside them
 * the house's words - its role catalogue and its trees of states and events, whole. Nothing is found on the classpath
 * (D51): a downstream's base modules are registered by the instance beside these.
 */
public record HouseModules() implements StatelessFunctionalObject {

    public static final HouseModules INSTANCE = new HouseModules();

    /** Base's implementations: every house jar that brings castings into being, each declaring Base's for them. */
    public static final List<StyleDeclaration> BASE_JARS = List.of(
            UiElementsStyleDeclaration.INSTANCE, UiIconsStyleDeclaration.INSTANCE, UiDialogStyleDeclaration.INSTANCE,
            UiFloatingStyleDeclaration.INSTANCE, UiMenuStyleDeclaration.INSTANCE, UiMonitorsStyleDeclaration.INSTANCE,
            UiPanesStyleDeclaration.INSTANCE, UiPanZoomStyleDeclaration.INSTANCE, UiSplitGridStyleDeclaration.INSTANCE,
            UiPreferencesStyleDeclaration.INSTANCE, MpaStyleDeclaration.INSTANCE, UiControlPanelStyleDeclaration.INSTANCE);

    /** Each house style's own jar. */
    public static final Map<DesignStyle<BaseScheme>, StyleDeclaration> STYLE_JARS = Map.of(
            Editorial.INSTANCE, EditorialStyleDeclaration.INSTANCE,
            NeoBrutalism.INSTANCE, NeoBrutalismStyleDeclaration.INSTANCE,
            NeoFuturism.INSTANCE, NeoFuturismStyleDeclaration.INSTANCE,
            Neumorphism.INSTANCE, NeumorphismStyleDeclaration.INSTANCE,
            Glassmorphism.INSTANCE, GlassmorphismStyleDeclaration.INSTANCE,
            RetroFuturism.INSTANCE, RetroFuturismStyleDeclaration.INSTANCE,
            Sketchy.INSTANCE, SketchyStyleDeclaration.INSTANCE);

    /** The component modules the house registers: its jars', as the house defines them. */
    public List<ComponentModule> definitions() { return HouseTaxonomy.MODULES; }

    /** The base modules: each component module with its components' looks in Base, from every house jar's declaration. */
    public List<BaseModule<BaseScheme, Base>> base() { return CollectModules.INSTANCE.base(Base.INSTANCE, definitions(), BASE_JARS); }

    /** A house style's module: its looks from its own jar, each in the bundle of the component whose design it is of. */
    public <S extends DesignStyle<BaseScheme>> StyleModule<BaseScheme, S> style(S style) {
        StyleDeclaration jar = STYLE_JARS.get(style);
        if (jar == null) throw new IllegalArgumentException(style.token() + " is no house style: it has no jar of the house's");
        return CollectModules.INSTANCE.style(style, definitions(), List.of(jar));
    }

    /** The house's catalogue of roles, whole. */
    public List<Role<?>> roles() { return HouseRoleCatalogue.INSTANCE.roles(); }

    /** The house's tree of states, whole. */
    public List<StateType<?>> states() { return HouseStates.ALL; }

    /** The house's tree of events, whole. */
    public List<EventType<?>> events() { return HouseEvents.ALL; }
}
