package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.SelfContent;
import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Every simple component of the crate held to its leaf (Implementing Components, rule 6): the
 * states its class has and the events it offers are exactly those its leaf names from the house's
 * trees, each with its explicit setter, and its companion beside it makes the element it takes. A
 * simple component is a leaf's module exporting its companion; the check reads the JavaScript as a
 * page runs it.
 */
class SimpleComponentsDeclaredTest extends JsModuleTestBase {

    /** The crate's simple components: each leaf's module that exports its companion, in the crate's order. */
    private static final List<EsModule<?>> SIMPLE = UiElementsCrate.INSTANCE.entries().stream().map(CrateEntry::module)
            .filter(m -> m instanceof ImplFor<?> impl && companion(m, impl.leaf()) != null).toList();

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", "var css = { cls: function (n) { return { name: n }; }, addClass: function () {}, removeClass: function () {},"
                + " size: function () {}, extent: function () {}, aspect: function () {} };");
        js.eval("js", "var look_sheet = 'look-sheet';");
        // what the simple components need, loaded in the crate's order - icons from their own crate, as handles
        js.eval("js", "var ic_base = { name: 'ic_base' };");
        for (var w : hue.captains.singapura.js.homing.ui.icons.IconStyles.worded())
            js.eval("js", "var " + w.getClass().getSimpleName() + " = { name: '" + w.getClass().getSimpleName() + "' };");
        js.eval("js", String.join("\n", hue.captains.singapura.js.homing.ui.icons.IconsModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        var needed = new LinkedHashSet<EsModule<?>>();
        for (EsModule<?> m : SIMPLE) need(m, needed);
        for (CrateEntry e : UiElementsCrate.INSTANCE.entries()) {
            EsModule<?> m = e.module();
            if (!needed.contains(m)) continue;
            if (m instanceof SelfContent sc) js.eval("js", String.join("\n", sc.selfContent(null)));
            else if (m instanceof CssGroup<?>) continue;
            else if (m == HouseLookSheet.INSTANCE) continue;
            else loadModule("/homing/js/" + m.getClass().getPackageName().replace('.', '/') + "/" + m.getClass().getSimpleName() + ".js");
        }
    }

    /** A module and what it imports from this crate; a style group's classes as handles. */
    private static void need(EsModule<?> m, Set<EsModule<?>> out) {
        if (!out.add(m)) return;
        m.imports().getAllImports().forEach((key, imports) -> {
            if (!(key instanceof EsModule<?> dep)) return;
            if (dep instanceof CssGroup<?>) { out.add(dep); return; }
            if (UiElementsCrate.INSTANCE.entries().stream().anyMatch(e -> e.module() == dep)) need(dep, out);
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
            Set<String> states = new TreeSet<>(leaf.states().stream().map(s -> s.name().value()).toList());
            Set<String> events = new TreeSet<>(leaf.events().stream().map(e -> e.name().value()).toList());
            Set<String> hasStates = tokens("Object.values(" + cls + ".AXES || {}).filter(function (a) { return a.state; }).map(function (a) { return a.state.token; })");
            Set<String> hasEvents = tokens("(" + cls + ".EVENTS || []).map(function (e) { return e.token; })");
            if (!states.equals(hasStates)) problems.add(cls + " has the states " + hasStates + ", its leaf names " + states);
            if (!events.equals(hasEvents)) problems.add(cls + " offers the events " + hasEvents + ", its leaf names " + events);
            var setters = new TreeSet<String>();
            for (String s : states) setters.add("setOn" + pascal(s) + "Changed");
            for (String e : events) setters.add("setOn" + pascal(e));
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
