package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The graph's own check, held: a cycle leaves no order; a chain has one, and its depth. */
class StyleDagOrderTest {

    private static StyleDag.Node node(String id) {
        return new StyleDag.ImplNode(id, Base.INSTANCE, new Casting(HouseControls.PlainButton.INSTANCE), StyleDag.Gives.CSS, id.equals("a"),
                new GenericsSampleTest.BasePlain());
    }

    private static StyleDag.Pointer edge(String from, String to) { return new StyleDag.SpecPointer(from, to, StyleDag.Spec.SAME_ROLE, new Casting(HouseControls.PlainButton.INSTANCE)); }

    @Test
    void aCycle_leavesNoOrder() {
        var dag = new StyleDag(Base.INSTANCE, List.of(node("a"), node("b"), node("c")), List.of(edge("a", "b"), edge("b", "c"), edge("c", "b")));
        assertTrue(dag.order().isEmpty());
    }

    @Test
    void aChain_hasAnOrder_andItsDepth() {
        var dag = new StyleDag(Base.INSTANCE, List.of(node("c"), node("b"), node("a")), List.of(edge("a", "b"), edge("b", "c")));
        assertEquals(List.of("a", "b", "c"), dag.order().orElseThrow().stream().map(StyleDag.Node::id).toList());
        assertEquals(2, dag.depth());
    }
}
