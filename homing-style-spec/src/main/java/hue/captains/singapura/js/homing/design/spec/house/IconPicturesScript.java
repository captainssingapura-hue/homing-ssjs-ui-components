package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.DualRef;
import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.dual.Components;
import hue.captains.singapura.js.homing.design.spec.dual.Styles;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The icons' pictures as the page has them (RFC 0067, D56): the picture provider the page's style manager is handed -
 * for an icon and a style, the picture its element shows: a character, or an SVG picture - its resource's name and its
 * markup, put in the element as it is ({@link IconSvg}), or its name alone where its resource is not there yet, the page
 * drawing a placeholder; null for a component that shows none. Each picture is one object, shared by
 * every style that draws it alike, so a switch redraws an icon only where the new style draws it otherwise. Made from
 * the icon palettes, for the styles the page may wear; generated, never written by hand.
 */
public record IconPicturesScript() implements StatelessFunctionalObject {

    public static final IconPicturesScript INSTANCE = new IconPicturesScript();

    /** What the module exports: the picture provider. */
    public static final String EXPORTED = "iconPictures";

    /** Its address: its class's canonical name, as every module's is. */
    public String address() { return "/module?class=" + getClass().getCanonicalName(); }

    /** The module, for the icons and palettes given and the styles the page may wear. */
    public String script(IconPictures pictures, List<DesignStyle<?>> styles) {
        var js = JsLiterals.INSTANCE;
        var refs = DualRef.INSTANCE;
        var constants = new LinkedHashMap<String, String>();   // a picture's constant, by its icon and what it is: one object for each
        var named = new LinkedHashMap<String, String>();       // its line
        var rows = new ArrayList<String>();
        for (DesignStyle<?> s : styles) {
            var cells = new ArrayList<String>();
            for (IconLeaf i : pictures.icons()) {
                IconPicture picture = pictures.picture(s, i);
                String own = pictures.own(s, i) ? "_" + constant(s.token()) : "";
                String key, line, name;
                switch (picture) {
                    case IconPicture.Glyph g -> {
                        key = i.token() + " glyph " + g.character();
                        name = "GLYPH_" + constant(i.token()) + own;
                        line = "Object.freeze({ glyph: " + js.string(g.character()) + " })";
                    }
                    case IconPicture.Svg v -> {
                        DesignStyle<?> drawer = pictures.drawer(s, i);
                        key = i.token() + " svg " + drawer.token() + " " + v.resource();
                        name = "SVG_" + constant(i.token()) + own;
                        // its markup, where its resource is there; else none, and the page draws its placeholder
                        line = IconSvgs.INSTANCE.find(drawer, v)
                                .map(svg -> "Object.freeze({ svg: " + js.string(v.resource()) + ", markup: " + js.string(svg.markup()) + " })")
                                .orElse("Object.freeze({ svg: " + js.string(v.resource()) + " })");
                    }
                }
                if (!constants.containsKey(key)) {
                    if (named.containsKey(name)) throw new IllegalStateException("two pictures named " + name);
                    constants.put(key, name);
                    named.put(name, line);
                }
                cells.add("[" + refs.of(i).imported() + ", " + constants.get(key) + "]");
            }
            rows.add("    [" + refs.of(s).imported() + ", new Map([" + String.join(", ", cells) + "])]");
        }
        var lines = new ArrayList<String>();
        lines.add("// The icons' pictures (RFC 0067, D56): the picture provider the page's style manager is handed - for an icon and a");
        lines.add("// style, the picture its element shows: a character, or an SVG picture - its name and its markup, put in the element as");
        lines.add("// it is; its name alone, drawn as a placeholder, where its resource is not there yet. Each picture one object, shared by");
        lines.add("// every style that draws it alike. Generated from Java; not to be edited.");
        lines.add("import { " + String.join(", ", pictures.icons().stream().map(i -> refs.of(i)).map(r -> r.name() + " as " + r.imported()).toList())
                + " } from " + js.string(Components.INSTANCE.address()) + ";");
        lines.add("import { " + String.join(", ", styles.stream().map(s -> refs.of(s)).map(r -> r.name() + " as " + r.imported()).toList())
                + " } from " + js.string(Styles.INSTANCE.address()) + ";");
        lines.add("");
        for (Map.Entry<String, String> e : named.entrySet()) lines.add("const " + e.getKey() + " = " + e.getValue() + ";");
        lines.add("");
        lines.add("/** Each style the page may wear, with the picture of each icon in it. */");
        lines.add("const PICTURES = new Map([");
        lines.add(String.join(",\n", rows));
        lines.add("]);");
        lines.add("");
        lines.add("const " + EXPORTED + " = Object.freeze({");
        lines.add("    /** The picture a component shows in a style: an icon's, as the style's icon palette draws it; null for a component that shows none. */");
        lines.add("    picture(component, style) {");
        lines.add("        const pictures = PICTURES.get(style);");
        lines.add("        if (pictures === undefined) throw new TypeError(\"picture: a style the page may wear\");");
        lines.add("        return pictures.get(component) ?? null;");
        lines.add("    },");
        lines.add("});");
        lines.add("");
        lines.add("export { " + EXPORTED + " };");
        return String.join("\n", lines) + "\n";
    }

    private static String constant(String token) { return token.toUpperCase(Locale.ROOT).replace('-', '_'); }
}
