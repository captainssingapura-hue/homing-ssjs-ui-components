package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.ui.elements.CastingsModule;
import hue.captains.singapura.js.homing.ui.elements.LookManifestModule;
import hue.captains.singapura.js.homing.ui.elements.StatesAndEventsModule;

import org.graalvm.polyglot.Context;

import java.util.function.Consumer;

/**
 * The house's text leaves, and what they stand on, for a test whose owner holds texts: {@link #load}
 * before its shim - the castings, the trees of states and events, the look's manifest and its sheet,
 * the look and the simple component, the text's base and its nine leaves - and {@link #withCss} on
 * its shim, which gives the shim's css what the look asks of it besides - naming a class, setting a
 * degree - right after the shim makes it, the shim's own kept.
 */
final class TextsInJs {

    private TextsInJs() {}

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String CSS = "if (!css.cls) css.cls = function (n) { return n; };"
            + " if (!css.removeClass) css.removeClass = function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); };"
            + " ['size', 'extent', 'aspect'].forEach(function (d) { if (!css[d]) css[d] = function (e, v) { e['_' + d] = v; }; });"
            + " if (typeof el_text === 'undefined') globalThis.el_text = 'el_text';\n";

    static void load(Context js, Consumer<String> loadModule) {
        if (js.eval("js", "typeof ROLE === 'undefined'").asBoolean())
            js.eval("js", String.join(System.lineSeparator(), CastingsModule.INSTANCE.selfContent(null)));
        if (js.eval("js", "typeof STATE === 'undefined'").asBoolean())
            js.eval("js", String.join(System.lineSeparator(), StatesAndEventsModule.INSTANCE.selfContent(null)));
        if (js.eval("js", "typeof LOOK_TARGETS === 'undefined'").asBoolean())
            js.eval("js", String.join(System.lineSeparator(), LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "if (typeof look_sheet === 'undefined') globalThis.look_sheet = 'look-sheet';");
        if (js.eval("js", "typeof ICONS === 'undefined'").asBoolean()) {   // the lexicon's table, as a var: a test may give its own after
            js.eval("js", "if (typeof lexicon_sheet === 'undefined') globalThis.lexicon_sheet = 'lexicon-sheet';");
            js.eval("js", String.join(System.lineSeparator(), hue.captains.singapura.js.homing.ui.icons.IconsModule.INSTANCE.selfContent(null)).replace("const ICONS", "var ICONS"));
        }
        for (String m : new String[] {"LookModule.js", "SimpleComponentModule.js", "TextBehaviorModule.js", "TextModule.js",
                                      "HeadingModule.js", "KickerModule.js", "CaptionModule.js", "LedeModule.js", "LabelModule.js",
                                      "ReadoutModule.js", "BadgeModule.js", "PillModule.js", "WordmarkModule.js"})
            if (js.eval("js", "typeof " + m.replace("Module.js", "") + " === 'undefined'").asBoolean())
                loadModule.accept(DIR + m);
    }

    /** The shim, with what the look asks of its css right after the shim makes it. */
    static String withCss(String shim) {
        int at = shim.indexOf("var css = ");
        if (at < 0) throw new IllegalArgumentException("the shim makes no css");
        int end = shim.indexOf("};\n", at);
        if (end < 0) throw new IllegalArgumentException("the shim's css does not end");
        return shim.substring(0, end + 3) + CSS + shim.substring(end + 3);
    }
}
