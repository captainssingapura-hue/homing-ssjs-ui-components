package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtensions;
import hue.captains.singapura.tao.ontology.enforcer.OntologyEnforcer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's base tree - its common components, which every deployment of the house has - is read
 * whole, with the role catalogue, and refused nothing; nothing extends it here, where no module that
 * extends it is on the classpath. jOntology holds every node stateless, here rather than in the
 * read; everything reached is declared; every branch reached has a component under it; and every
 * common component the house ships today has its leaf. Its components declare their parts as slots
 * over the catalogue; every node of both trees means something, and the axes a branch declares are
 * every leaf's under it. The whole house, every extension read in, is held by the components
 * conformance, where every module is.
 */
class HouseTaxonomyTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    /** The common components ui-components ships today, by their class names; its Button is realized by the button leaves, its Card by SummaryCard. */
    private static final Set<String> SHIPPED = Set.of(
            "SummaryCard", "Slider", "SliderGroup", "Panel", "EdgeStrip", "Dialog", "FloatingPane", "Icon", "ContextMenu",
            "Link", "Crumb", "Scrim", "Separator", "Delimiter", "Label", "Heading", "Caption", "Readout");

    @Test
    void theBase_aloneOnThisClasspath() {
        assertEquals(List.of(), TaxonomyExtensions.INSTANCE.of(HouseTaxonomy.class), "no module that extends the house is here");
        assertEquals(HouseTaxonomy.INSTANCE.common(), HouseTaxonomy.INSTANCE.components());
    }

    @Test
    void theBaseIsReadWhole_withItsCatalogue() {
        assertEquals(14, HOUSE.branches().size(), "the view, the field and the split have no common leaf: their leaves are the extensions' - a view is a widget, a split the grid's");
        assertEquals(58, HOUSE.components().size());
        assertEquals(91, HOUSE.roles().size(), "the catalogue beside the tree, whole: the extensions name their parts in its words");
        assertEquals(49, HOUSE.parts().size(), "the slots, each a part once its owner is appended");
        assertEquals(20, HOUSE.parts().stream().map(Part::owner).distinct().count(), "the components that have parts");
    }

    /**
     * Every node the house declares is a stateless functional object, as jOntology judges one: held
     * here, at build time, since a read no longer judges state and a running server has no enforcer.
     * The trees of states and of events too.
     */
    @Test
    void everyNode_isStateless() {
        var nodes = new java.util.ArrayList<Object>();
        nodes.addAll(HOUSE.branches());
        nodes.addAll(HOUSE.components());
        nodes.addAll(HOUSE.roleBranches());
        nodes.addAll(HOUSE.roles());
        nodes.addAll(HOUSE.states().branches());
        nodes.addAll(HOUSE.states().types());
        nodes.addAll(HOUSE.events().branches());
        nodes.addAll(HOUSE.events().types());
        var enforcer = new OntologyEnforcer();
        var violations = nodes.stream().map(Object::getClass).distinct()
                .flatMap(c -> enforcer.enforce(c).stream().map(v -> c.getName() + ": " + v.message()))
                .toList();
        assertEquals(List.of(), violations);
    }

    @Test
    void everythingReached_isDeclared() {
        assertEquals(Set.copyOf(HouseTaxonomy.INSTANCE.components()), Set.copyOf(HOUSE.components()),
                "no component is reached without being declared in the house");
    }

    @Test
    void theBranches_levelled_eachWithAComponentUnderIt() {
        for (ComponentBranch b : HOUSE.branches())
            assertFalse(descendants(b).isEmpty(), b.getClass().getSimpleName() + " has no component under it");
        assertEquals(List.of("control", "item", "container", "region", "text", "mark", "track"),
                HOUSE.children(Root.INSTANCE).stream().map(n -> n.token()).toList(), "level 1, in the order first reached");
        assertEquals(List.of(1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2),
                HOUSE.branches().stream().map(ComponentBranch::level).toList(), "seven at level 1, seven at level 2");
    }

    @Test
    void everyCommonComponentShippedToday_hasItsLeaf() {
        var names = HOUSE.components().stream().map(c -> c.getClass().getSimpleName()).collect(Collectors.toSet());
        assertTrue(names.containsAll(SHIPPED), "missing: " + SHIPPED.stream().filter(n -> !names.contains(n)).toList());
        assertTrue(HOUSE.children(HouseBranches.Button.INSTANCE).size() >= 6, "the button's looks are buttons of their own");
    }

    @Test
    void theCard_aPlainLeaf_andACaseBuiltOnIt() {
        assertEquals(List.of(HouseContainers.PlainCard.INSTANCE, HouseContainers.SummaryCard.INSTANCE),
                HOUSE.children(HouseBranches.Card.INSTANCE));
        assertTrue(HOUSE.partsOf(HouseContainers.PlainCard.INSTANCE).isEmpty(), "the basic card fixes no part");
        assertEquals(List.of("summary-card-title 1 heading", "summary-card-tag 0..1 badge",
                             "summary-card-summary 0..1 caption", "summary-card-open 0..1 link"),
                said(HouseContainers.SummaryCard.INSTANCE));
    }

    @Test
    void aPart_playedByAnIndependentComponent_fallsBackThroughIt() {
        assertEquals(List.of("dialog-veil 0..1 scrim", "dialog-window 1 floating-pane", "dialog-actions 0..1 action-bar"),
                said(HouseContainers.Dialog.INSTANCE));
        assertEquals(List.of("trail-step 0..* crumb", "trail-break 0..* delimiter"), said(HouseContainers.Trail.INSTANCE));
        var thumb = HOUSE.partsOf(HouseControls.Slider.INSTANCE).stream().filter(p -> p.role() == HouseDoing.Thumb.INSTANCE).findFirst().orElseThrow();
        assertEquals(List.of("slider-thumb", "knob", "handle", "control", "root"),
                HOUSE.fallback(thumb).stream().map(n -> n.token()).toList(), "through its base, never its owner");
    }

    @Test
    void everyNode_meansSomething_bothTrees() {
        var nodes = new java.util.ArrayList<String>();
        HOUSE.branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.components().forEach(c -> nodes.add(HOUSE.meaning(c).markdown()));
        HOUSE.roleBranches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.roles().forEach(r -> nodes.add(HOUSE.meaning(r).markdown()));
        HOUSE.states().branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.states().types().forEach(s -> nodes.add(HOUSE.meaning(s).markdown()));
        HOUSE.events().branches().forEach(b -> nodes.add(HOUSE.meaning(b).markdown()));
        HOUSE.events().types().forEach(e -> nodes.add(HOUSE.meaning(e).markdown()));
        assertEquals(14 + 58 + 21 + 91 + 8 + 22 + 7 + 14, nodes.size());
        assertTrue(nodes.stream().noneMatch(String::isBlank));
        assertTrue(HOUSE.meaning(HouseControls.DangerButton.INSTANCE).markdown().startsWith("Does a thing that destroys"));
        assertTrue(HOUSE.meaning(HouseSaying.Title.INSTANCE).markdown().startsWith("Names a container"));
    }

    /**
     * The trees of states and of events are whole in the base, as the catalogue is - read whether or
     * not a component has their types, filed by what they are about. The tree files a state's types,
     * never its values: each type has its values, the one at rest first.
     */
    @Test
    void theStatesAndEvents_wholeInTheBase_filedByWhatTheyAreAbout() {
        assertEquals(List.of("availability", "picking", "disclosure", "validity", "place", "content", "interaction", "physical"),
                HOUSE.states().branches().stream().map(b -> b.name().value()).toList());
        assertEquals(HouseStates.ALL, HOUSE.states().types(), "every type, whether or not a component has it yet");
        assertEquals(List.of("pointing", "dragging", "editing", "asking", "loading", "timing", "scrolling"),
                HOUSE.events().branches().stream().map(b -> b.name().value()).toList());
        assertEquals(HouseEvents.ALL, HOUSE.events().types());
        assertEquals(List.of(HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Down.INSTANCE, HouseStates.Keyboard.INSTANCE, HouseStates.Dragging.INSTANCE),
                HOUSE.states().children(HouseStates.Interaction.INSTANCE), "what the hand and the keys do, folded into the tree - the hand's drag among them");
        assertEquals(List.of("true", "false"), tokens(HouseStates.Enabled.INSTANCE), "enabled at rest");
        assertEquals(List.of("false", "true"), tokens(HouseStates.Pressed.INSTANCE), "pressed only when pressed");
        assertEquals(List.of("none", "some", "all"), tokens(HouseStates.Coverage.INSTANCE), "the mixed checkbox's, a type of its own");
        assertEquals(List.of("away", "candidate", "lent", "held"), tokens(HouseStates.Keyboard.INSTANCE));
        assertEquals(List.of("flat", "elevated", "sunken"), tokens(HouseStates.Elevation.INSTANCE));
        assertEquals(List.of("horizontal", "vertical"), tokens(HouseStates.Orientation.INSTANCE), "laid along, unless stood up");
    }

    private static List<String> tokens(hue.captains.singapura.js.homing.component.taxonomy.StateType<?> type) {
        return type.values().stream().map(hue.captains.singapura.js.homing.component.taxonomy.StateValue::token).toList();
    }

    @Test
    void theAxes_aBranchsAreEveryLeafsUnderIt_aPartTakesItsOwnComponents() {
        assertEquals(List.of(ExtentAxis.SIZE), HOUSE.extents(HouseControls.PlainButton.INSTANCE), "every button has a size");
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.COLOUR), HOUSE.extents(HouseControls.DangerButton.INSTANCE),
                "a coloured button a colour too");
        assertEquals(List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT), HOUSE.extents(HouseContainers.PlainCard.INSTANCE), "every card a size and an aspect");
        var close = HOUSE.partsOf(HouseContainers.FloatingPane.INSTANCE).get(2);
        assertEquals(List.of(ExtentAxis.SIZE), HOUSE.extents(close), "a floating pane's close is a button, and has a button's");
        var open = HOUSE.partsOf(HouseContainers.SummaryCard.INSTANCE).get(3);
        assertEquals(List.of(), HOUSE.extents(open), "a card's link has none of its card's: a part follows its own component");
    }

    /** A component's parts, each said as its token, how many, and what plays it. */
    private static List<String> said(Component<?> owner) {
        return HOUSE.partsOf(owner).stream().map(p -> p.token() + " " + p.cardinality().multiplicity() + " " + p.base().token()).toList();
    }

    private static List<Component<?>> descendants(ComponentBranch b) {
        return HOUSE.components().stream().filter(c -> HOUSE.fallback(c).contains(b)).toList();
    }
}
