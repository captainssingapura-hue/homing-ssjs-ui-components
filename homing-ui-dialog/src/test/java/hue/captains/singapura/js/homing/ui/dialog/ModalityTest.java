package hue.captains.singapura.js.homing.ui.dialog;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * One element owning the screen, over a fake document: everything else under
 * the body goes inert — but what the caller keeps live, and what was inert
 * already; release undoes its own marks and only those, and gives the focus
 * back to whoever had it, if they are still on the page. Twice is harmless.
 */
class ModalityTest extends JsModuleTestBase {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/dialog/";

    private static final String SHIM = """
        var log = [];
        function el(tag) { return { tag: tag, inert: false, parentNode: null, focus: function () { document.activeElement = this; log.push("focus:" + this.tag); } }; }
        var body = { children: [] };
        function add(e) { body.children.push(e); e.parentNode = body; return e; }
        var document = { body: body, activeElement: null, contains: function (e) { return body.children.indexOf(e) >= 0; } };
        var page = add(el("page")), nav = add(el("nav")), scrim = add(el("scrim")), frame = add(el("frame")), asleep = add(el("asleep"));
        asleep.inert = true;   // inert before the hold began: not the hold's to undo
        document.activeElement = nav;
        function inert() { return body.children.filter(function (e) { return e.inert; }).map(function (e) { return e.tag; }).join(","); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", SHIM);
        loadModule(DIR + "ModalityModule.js");
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void everythingElseGoesInert_butTheFrameAndWhatTheCallerKeeps() {
        eval("var hold = new Modality(frame, { keep: [scrim] })");
        assertEquals("page,nav,asleep", eval("inert()").asString());
    }

    @Test
    void releaseUndoesItsOwnMarksOnly_andGivesTheFocusBack_once() {
        eval("var hold = new Modality(frame); frame.focus(); log = []; hold.release()");
        assertEquals("asleep", eval("inert()").asString(), "what was inert before stays so");
        assertEquals("focus:nav", eval("log.join(' ')").asString(), "the focus back to whoever had it when the hold began");
        eval("log = []; hold.release()");
        assertEquals("", eval("log.join(' ')").asString(), "a second release is nothing");
    }

    @Test
    void theFocusGoesWhereTheCallerSaid_andNowhereIfThatHasLeftThePage() {
        eval("var hold = new Modality(frame, { restoreTo: page }); hold.release()");
        assertEquals("focus:page", eval("log.join(' ')").asString());
        eval("log = []; var gone = el('gone'); var h2 = new Modality(frame, { restoreTo: gone }); h2.release()");
        assertEquals("", eval("log.join(' ')").asString(), "not on the page: nobody is focused");
    }

    @Test
    void aFrameIsRequired() {
        assertThrows(PolyglotException.class, () -> eval("new Modality(null)"));
    }
}
