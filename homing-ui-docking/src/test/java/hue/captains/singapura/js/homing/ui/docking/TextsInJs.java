package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;

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
        if (js.eval("js", "typeof LEAVES === 'undefined'").asBoolean())
            js.eval("js", String.join(System.lineSeparator(), LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "if (typeof style_sheet === 'undefined') { globalThis.style_sheet = 'style-sheet'; globalThis.palette_sheet = 'palette-sheet'; } if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };"); if (js.eval("js", "typeof StyledLeaf === 'undefined'").asBoolean()) loadModule.accept("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        if (js.eval("js", "typeof ICON === 'undefined'").asBoolean())   // the instance's icons, as a var: a test may load its own first
            js.eval("js", String.join(System.lineSeparator(), hue.captains.singapura.js.homing.ui.look.IconsModule.INSTANCE.selfContent(null)).replace("const ICON ", "var ICON "));
        if (js.eval("js", "typeof Look === 'undefined'").asBoolean())   // the look, the design's: beside the elements, not one of them
            loadModule.accept("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");
        if (js.eval("js", "typeof Icon === 'undefined'").asBoolean()) {   // the icon an owner mounts - a close button's face among them (D52) - and its box
            js.eval("js", "if (typeof ic_base === 'undefined') globalThis.ic_base = 'ic_base';");
            loadModule.accept("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        }
        if (js.eval("js", "typeof IconRoom === 'undefined'").asBoolean())   // the room an owner mounts its icons in, one at a time
            loadModule.accept("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconRoomModule.js");
        for (String m : new String[] {"SimpleComponentModule.js", "TextBehaviorModule.js", "TextModule.js",
                                      "HeadingModule.js", "Heading1Module.js", "Heading2Module.js", "Heading3Module.js", "Heading4Module.js", "Heading5Module.js", "Heading6Module.js", "KickerModule.js", "CaptionModule.js", "LedeModule.js", "LabelModule.js",
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
