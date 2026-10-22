package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.SelfContent;
import hue.captains.singapura.js.homing.ui.look.IconsModule;
import hue.captains.singapura.js.homing.ui.look.UiLookCrate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Every simple component of the crate held to its leaf (Implementing Components, rule 6): the
 * states its class has are exactly those its leaf names from the house's
 * trees, each with its explicit setter, and its companion beside it makes the element it takes. A
 * simple component is a leaf's module exporting its companion; the check reads the JavaScript as a
 * page runs it.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class SimpleComponentsDeclaredTest extends UiElementsJsTest {

    /** The crate's simple components: each leaf's module that exports its companion, in the crate's order. */
    private static final List<EsModule<?>> SIMPLE = UiElementsCrate.INSTANCE.entries().stream().map(CrateEntry::module)
            .filter(m -> m instanceof ImplFor<?> impl && companion(m, impl.leaf()) != null).toList();

    /** Where what they need is: the look's crate - the design's - then the elements' own, in the crates' order. */
    private static final List<CrateEntry> NEEDED_FROM = Stream.concat(UiLookCrate.INSTANCE.entries().stream(), UiElementsCrate.INSTANCE.entries().stream()).toList();

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", "var css = { cls: function (n) { return { name: n }; }, addClass: function () {}, removeClass: function () {},"
                + " size: function () {}, extent: function () {}, aspect: function () {} };");
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet'; if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };"); loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        // what the simple components need, loaded in the crate's order - icons from their own crate, as handles
        js.eval("js", "var ic_base = { name: 'ic_base' };");   // the icon's box
        js.eval("js", String.join("\n", hue.captains.singapura.js.homing.ui.look.IconsModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        var needed = new LinkedHashSet<EsModule<?>>();
        for (EsModule<?> m : SIMPLE) need(m, needed);
        for (CrateEntry e : NEEDED_FROM) {
            EsModule<?> m = e.module();
            if (!needed.contains(m) || m == IconsModule.INSTANCE) continue;   // the instance's icons, given above
            if (m instanceof SelfContent sc) js.eval("js", String.join("\n", sc.selfContent(null)));
            else if (m instanceof CssGroup<?> || m instanceof DerivedCss<?>) continue;
            else loadModule("/homing/js/" + m.getClass().getPackageName().replace('.', '/') + "/" + m.getClass().getSimpleName() + ".js");
        }
    }

    /** A module and what it imports from the look's crate or this one; a style group's classes as handles. */
    private static void need(EsModule<?> m, Set<EsModule<?>> out) {
        if (!out.add(m)) return;
        m.imports().getAllImports().forEach((key, imports) -> {
            if (!(key instanceof EsModule<?> dep)) return;
            if (dep instanceof CssGroup<?>) { out.add(dep); return; }
            if (NEEDED_FROM.stream().anyMatch(e -> e.module() == dep)) need(dep, out);
        });
    }

    @Test
    void thereAreSimpleComponents() {
        assertFalse(SIMPLE.isEmpty(), "the crate's simple components: its leaves' modules with a companion");
    }

    @Test
    void everySimpleComponent_hasExactlyWhatItsLeafNames_eachWithItsSetter_andItsCompanion() {
        // a style group's classes, as handles, for whatever a simple component imports of one
        for (EsModule<?> m : SIMPLE)
            m.imports().getAllImports().forEach((key, imports) -> {
                if (key instanceof CssGroup<?>) for (var x : imports.allImports())
                    js.eval("js", "var " + x.getClass().getSimpleName() + " = { name: '" + x.getClass().getSimpleName() + "' };");
            });
        var problems = new ArrayList<String>();
        for (EsModule<?> m : SIMPLE) {
            Component<?> leaf = ((ImplFor<?>) m).leaf();
            String cls = leaf.getClass().getSimpleName();
            // a pseudo state is the browser's: no axis, no setter of the component's (RFC 0067, appendix Two Classes per Leaf, D12)
            Set<String> states = new TreeSet<>(leaf.states().stream().filter(s -> !(s instanceof Pseudo)).map(s -> s.name().value()).toList());
            Set<String> hasStates = tokens("Object.values(" + cls + ".AXES || {}).filter(function (a) { return a.state; }).map(function (a) { return a.state.token; })");
            if (!states.equals(hasStates)) problems.add(cls + " has the states " + hasStates + ", its leaf names " + states);
            var setters = new TreeSet<String>();
            for (String s : states) setters.add("setOn" + pascal(s) + "Changed");
            Set<String> has = tokens("(function () { var out = []; for (var p = " + cls + ".prototype; p && p !== Object.prototype; p = Object.getPrototypeOf(p))"
                    + " Object.getOwnPropertyNames(p).forEach(function (n) { if (/^setOn/.test(n) && out.indexOf(n) < 0) out.push(n); }); return out; })()");
            if (!setters.equals(has)) problems.add(cls + " has the setters " + has + ", its leaf calls for " + setters);
            String companion = cls + "Factory";
            boolean ok = js.eval("js", "typeof " + companion + " !== 'undefined' && " + companion + " instanceof SimpleComponentFactory && "
                    + companion + ".tag === " + cls + ".TAG && Object.isFrozen(" + companion + ")").asBoolean();
            if (!ok) problems.add(cls + " has no companion " + companion + " making its <" + js.eval("js", cls + ".TAG") + ">");
        }
        assertEquals(List.of(), problems);
    }

    private Set<String> tokens(String expression) {
        var v = js.eval("js", expression);
        var out = new TreeSet<String>();
        for (long i = 0; i < v.getArraySize(); i++) out.add(v.getArrayElement(i).asString());
        return out;
    }

    /** {@code enabled} gives {@code Enabled}, {@code drop-mark} {@code DropMark}. */
    private static String pascal(String token) {
        var b = new StringBuilder();
        for (String w : token.split("-")) b.append(w.substring(0, 1).toUpperCase(Locale.ROOT)).append(w.substring(1));
        return b.toString();
    }

    /** The module's companion export, or null. */
    private static Exportable<?> companion(EsModule<?> m, Component<?> leaf) {
        for (Exportable<?> x : m.exports().exports())
            if (x instanceof Exportable._Constant<?> && x.getClass().getSimpleName().equals(leaf.getClass().getSimpleName() + "Factory")) return x;
        return null;
    }
}
