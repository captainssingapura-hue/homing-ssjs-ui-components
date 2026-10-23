package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A fixed type's fields, derived from its component's static parts (RFC 0071 port, question 5): one for each static part
 * that leaves a choice, in its owner's order, named for its role - an optional part's fixed object or none, a count's
 * list, one of several players - and a part whose own type has fields, nested; none for a dynamic part, and none at all
 * where nothing is left to choose.
 */
class FixedTypesTest {

    private static final Taxonomy HOUSE = HouseSpec.INSTANCE.registry().taxonomy();
    private static final FixedTypes FIXED = FixedTypes.INSTANCE;

    @Test
    void eachStaticChoice_aField_inTheOwnersOrder() {
        assertEquals(List.of("note 0..1 caption"), said(HouseItems.ChoiceOption.INSTANCE), "whether it has a note, the caption's own fixed object or none");
        assertEquals(List.of("go 0..1 primary-button", "action 1..* plain-button"), said(HouseContainers.ActionBar.INSTANCE), "a Go or none, and how many actions");
        assertEquals(List.of("leading 0..1 add-icon|remove-icon|close-icon|reset-icon|rotate-icon|flip-icon|pin-icon|check-icon|grip-icon|disclose-icon|settings-icon"
                        + "|preferences-icon|size-icon|aspect-icon|extent-icon|level-icon|pacman-icon|column-icon|row-icon|merge-icon|detach-icon|within-icon", "hint 0..1 caption", "disclose 0..1 disclose-icon"),
                said(HouseItems.PlainMenuItem.INSTANCE), "its own icon chosen once, its hint, its way on (23 Oct)");
        assertEquals(List.of("leading 0..1 add-icon|remove-icon|close-icon|reset-icon|rotate-icon|flip-icon|pin-icon|check-icon|grip-icon|disclose-icon|settings-icon"
                        + "|preferences-icon|size-icon|aspect-icon|extent-icon|level-icon|pacman-icon|column-icon|row-icon|merge-icon|detach-icon|within-icon", "hint 0..1 caption"),
                said(HouseItems.CheckMenuItem.INSTANCE), "its own icon chosen once, its hint: its check always, no field (23 Oct)");
    }

    @Test
    void whichPlayer_oneFixedObject_andAPartsOwnFields_nested() {
        var face = FIXED.fields(HOUSE, HouseControls.IconButton.INSTANCE);
        assertEquals(1, face.size());
        assertTrue(face.get(0).single() && face.get(0).least() == 1 && face.get(0).players().size() == 22, "the icon button's face: one of the twenty icons");
        assertEquals(List.of("window 1..1 floating-pane"), said(HouseContainers.ModalWindow.INSTANCE),
                "its veil dynamic; its window one, nested - the floating pane's own head a choice");
    }

    @Test
    void nothingLeftToChoose_noFields() {
        for (Component<?> c : List.of(HouseControls.PlainButton.INSTANCE, HouseControls.Slider.INSTANCE, HouseControls.CloseButton.INSTANCE, HouseContainers.Trail.INSTANCE, HouseContainers.SummaryView.INSTANCE))
            assertEquals(List.of(), FIXED.fields(HOUSE, c), c.token() + ": its parts one with one player, or dynamic");
        assertEquals(17, HOUSE.components().stream().filter(c -> !FIXED.fields(HOUSE, c).isEmpty()).count(), "seventeen of the house's components have a field - the slider's none, on the native range (23 Oct); the picker's and the opener's choices no more, widgets (22 Oct)");
    }

    @Test
    void theDualsType_saysItsFields_forTheKitToCheck() {
        assertEquals("class FixedPlainButton extends Fixed { static COMPONENT = PlainButton; constructor(...given) { super(PlainButton, given); } }",   // its component said, an owner placing its part by it (21 Oct)
                FIXED.companion(HOUSE, HouseControls.PlainButton.INSTANCE, "PlainButton").declaration());
        assertEquals("class FixedChoiceOption extends Fixed { static COMPONENT = ChoiceOption; static FIELDS = [new FixedField(\"note\", new Count(0, 1), () => [FixedCaption])]; "
                        + "constructor(...given) { super(ChoiceOption, given); } }",
                FIXED.companion(HOUSE, HouseItems.ChoiceOption.INSTANCE, "ChoiceOption").declaration());
    }

    @Test
    void itsValues_saidAfterItsFields_asTheKitChecksThem() {
        String d = FIXED.companion(HOUSE, HouseContainers.FloatingPane.INSTANCE, "FloatingPane").declaration();
        assertTrue(d.contains("static VALUES = new FixedGroup(\"values\", [new FixedGroup(\"size\", [new FixedGroup(\"least\", [new FixedWhole(\"w\", 160, false), "
                + "new FixedWhole(\"h\", 96, false)]), new FixedGroup(\"most\", [new FixedWhole(\"w\", null, true), new FixedWhole(\"h\", null, true)])])]); "), d);
        assertTrue(!FIXED.companion(HOUSE, HouseControls.PlainButton.INSTANCE, "PlainButton").declaration().contains("VALUES"), "no values, none said");
    }

    private static List<String> said(Component<?> c) {
        return FIXED.fields(HOUSE, c).stream().map(f -> f.name() + " " + f.least() + ".." + (f.most() == null ? "*" : f.most()) + " "
                + String.join("|", f.players().stream().map(Component::token).toList())).toList();
    }
}
