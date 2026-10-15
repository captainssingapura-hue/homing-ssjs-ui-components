package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The theme widget picks two preferences, each its own (RFC 0067, appendix Two Classes per Leaf): the style, from the
 * site's offer, and the palette, among those the style worn is offered in. A palette picked is the palette alone; a
 * style picked keeps the palette worn when it is offered for the style, else takes the style's own. Nothing chosen,
 * each is the site's default theme's. Its fields, its options and the store are stood in for: the widget's choices are
 * what is tested.
 */
class ThemeWidgetTest extends JsModuleTestBase {

    private static final String SHIM = """
        var store = {}, listeners = [];
        var PreferenceViewInstance = { resolve: function (n, f) { return store[n] != null ? store[n] : (f || null); },
            preferred: function (n) { return store[n] || null; }, override: function () { return null; },
            onChange: function (fn) { listeners.push(fn); return function () {}; } };
        function remember(n, v) { store[n] = v; listeners.slice().forEach(function (fn) { fn(); }); }
        function el(tag) {
            return { tag: tag, children: [], parentNode: null, attrs: {}, classes: [], text: "",
                appendChild: function (c) { if (c.parentNode) c.remove(); this.children.push(c); c.parentNode = this; return c; },
                remove: function () { var p = this.parentNode; if (p) { p.children.splice(p.children.indexOf(this), 1); this.parentNode = null; } },
                setAttribute: function (k, v) { this.attrs[k] = String(v); } };
        }
        function fakeBranch(name) {
            var made = [], subs = {};
            return { name: name, activate: function () {},
                createElement: function (n, tag) { var e = el(tag); made.push(e); return e; },
                createBranch: function (n) { var b = fakeBranch(n); subs[n] = b; return b; },
                dissolveBranch: function (n) { if (subs[n]) { subs[n]._dissolve(); delete subs[n]; } },
                _dissolve: function () { made.forEach(function (e) { e.remove(); }); Object.keys(subs).forEach(function (k) { subs[k]._dissolve(); }); } };
        }
        class PreferenceField {
            constructor(branch, p) { this._name = p.name; this.params = p; this.root = el("div"); this.body = el("div"); this.root.appendChild(this.body); }
            hold(e) { this.body.appendChild(e); }
            value() { return PreferenceViewInstance.resolve(this._name, null); }
            set(v) { remember(this._name, v); }
            dispose() {}
        }
        var options = [];
        class ChoiceOption {
            constructor(branch, p, role) { this.el = branch.createElement("option", "button"); this.p = p; this.role = role; this.el.option = this; }
            setOnClick(h) { this.click = h; }
        }
        class Kicker { constructor(e, p) { this.el = e; this.text = p.text; } }
        var KickerFactory = { create: function (b, n) { return b.createElement(n, "span"); } };
        var css = { addClass: function (e, c) { e.classes.push(c); } };
        var ROLE = { STATUS: "status", STYLE: "style", PALETTE: "palette" }, EVENT = { CLICK: "click" };
        var pv_kicker = "pv_kicker", pv_options = "pv_options", pv_option = "pv_option";
        var REGISTRY = { default: "editorial",
            themes: [
                { slug: "editorial", label: "Editorial", inspiration: "print", colours: [
                    { slug: "editorial", palette: "harbour", own: true, fits: true },
                    { slug: "editorial_clay", palette: "clay", own: false, fits: true },
                    { slug: "editorial_marker", palette: "marker", own: false, fits: false } ] },
                { slug: "neumorphism", label: "Neumorphism", inspiration: "moulded", colours: [
                    { slug: "neumorphism", palette: "clay", own: true, fits: true },
                    { slug: "neumorphism_harbour", palette: "harbour", own: false, fits: true } ] },
                { slug: "sketchy", label: "Sketchy", inspiration: "drawn", colours: [
                    { slug: "sketchy", palette: "marker", own: true, fits: true },
                    { slug: "sketchy_harbour", palette: "harbour", own: false, fits: false },
                    { slug: "sketchy_clay", palette: "clay", own: false, fits: false } ] } ],
            palettes: [ { slug: "harbour", label: "Harbour", inspiration: "sea" }, { slug: "clay", label: "Clay", inspiration: "kiln" },
                        { slug: "marker", label: "Marker", inspiration: "pen" } ] };
        var fetch = function () { return Promise.resolve({ ok: true, json: function () { return Promise.resolve(REGISTRY); } }); };
        var w = new ThemeWidget(fakeBranch("theme"), { label: "Style", summary: "s" });
        /** The options a field holds now, each "label*" when marked chosen. */
        function listed(field) {
            var list = field.body.children.filter(function (c) { return c.attrs.role === "listbox"; })[0];
            return list ? list.children.map(function (o) { return o.option.p.label + (o.option.p.selected ? "*" : ""); }).join(" ") : "";
        }
        function press(field, label) {
            var list = field.body.children.filter(function (c) { return c.attrs.role === "listbox"; })[0];
            list.children.filter(function (o) { return o.option.p.label === label; })[0].option.click.handle();
        }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", "var self = globalThis;");
        loadModule("/homing/js/hue/captains/singapura/js/homing/site/mpa/ThemeWidgetModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    /** Two fields, the style's then the palette's after it, each its own preference. */
    @Test
    void twoFields_eachItsOwnPreference() {
        assertEquals("style palette", eval("w._style._name + ' ' + w._palette._name").asString());
        assertEquals("true", eval("String(w._palette.root.parentNode === w.root)").asString(), "the palette's field after the style's, in its root");
    }

    /** Nothing chosen: the site's default theme's style, and the palettes it is offered in - its own and those that fit. */
    @Test
    void nothingChosen_theDefaultsStyle_andThePalettesItIsOfferedIn() {
        assertEquals("Editorial* Neumorphism Sketchy", eval("listed(w._style)").asString());
        assertEquals("Harbour* Clay", eval("listed(w._palette)").asString(), "Marker does not fit Editorial: not offered");
    }

    /** A palette picked is the palette alone. */
    @Test
    void aPalettePicked_isThePaletteAlone() {
        eval("press(w._palette, 'Clay')");
        assertEquals("clay null", eval("store.palette + ' ' + (store.style || null)").asString());
        assertEquals("Harbour Clay*", eval("listed(w._palette)").asString(), "redrawn from the store");
    }

    /** A style picked keeps the palette worn when it is offered for the style. */
    @Test
    void aStylePicked_keepsThePaletteWorn_whenItIsOffered() {
        eval("press(w._style, 'Neumorphism')");
        assertEquals("neumorphism", eval("store.style").asString());
        assertEquals("undefined", eval("String(store.palette)").asString(), "the default's palette, Harbour, is offered for it: nothing written");
        assertEquals("Clay Harbour*", eval("listed(w._palette)").asString());
    }

    /** A style picked takes its own palette when the one worn is not offered for it. */
    @Test
    void aStylePicked_takesItsOwnPalette_whenTheWornOneIsNotOffered() {
        eval("press(w._palette, 'Clay'); press(w._style, 'Sketchy')");
        assertEquals("sketchy marker", eval("store.style + ' ' + store.palette").asString());
        assertEquals("Marker*", eval("listed(w._palette)").asString(), "Sketchy is offered in its own alone");
        assertEquals("Editorial Neumorphism Sketchy*", eval("listed(w._style)").asString());
    }
}
