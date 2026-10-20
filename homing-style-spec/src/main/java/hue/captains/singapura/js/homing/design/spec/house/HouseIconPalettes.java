package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.IconContent.Glyph;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's icon palettes (RFC 0067, D56): base's, drawing every glyph the house's icons show, and each house style's
 * own where it differs - five a cross of their own, sketchy six. Editorial draws base's. The glyphs are those each
 * style's sheet drew on the icon's ::before before D56, moved here unchanged; rotate and flip are the SVG samples, which
 * no palette draws yet.
 */
public record HouseIconPalettes() implements StatelessFunctionalObject {

    public static final HouseIconPalettes INSTANCE = new HouseIconPalettes();

    /** Base's palette: every glyph. */
    public IconPalette base() {
        var g = new LinkedHashMap<Glyph, String>();
        g.put(new Glyph("add"), "+");
        g.put(new Glyph("remove"), "−");
        g.put(new Glyph("close"), "✕");
        g.put(new Glyph("reset"), "↺");
        g.put(new Glyph("pin"), "⌖");
        g.put(new Glyph("check"), "✓");
        g.put(new Glyph("grip"), "⠿");
        g.put(new Glyph("disclose"), "▸");
        g.put(new Glyph("settings"), "⚙");
        g.put(new Glyph("size"), "⤢");
        g.put(new Glyph("aspect"), "⬌");
        g.put(new Glyph("extent"), "◐");
        g.put(new Glyph("level"), "≡");
        g.put(new Glyph("column"), "▥");
        g.put(new Glyph("row"), "▤");
        g.put(new Glyph("merge"), "▣");
        g.put(new Glyph("detach"), "⧉");
        g.put(new Glyph("within"), "◉");
        return new IconPalette(Base.INSTANCE, g);
    }

    /** Each style's palette that draws something of its own. */
    public List<IconPalette> styles() {
        return List.of(
                new IconPalette(Glassmorphism.INSTANCE, Map.of(new Glyph("close"), "×")),
                new IconPalette(NeoBrutalism.INSTANCE, Map.of(new Glyph("close"), "╳")),
                new IconPalette(NeoFuturism.INSTANCE, Map.of(new Glyph("close"), "⨯")),
                new IconPalette(Neumorphism.INSTANCE, Map.of(new Glyph("close"), "✗")),
                new IconPalette(RetroFuturism.INSTANCE, Map.of(new Glyph("close"), "✘")),
                new IconPalette(Sketchy.INSTANCE, sketchy()));
    }

    private static Map<Glyph, String> sketchy() {
        var g = new LinkedHashMap<Glyph, String>();
        g.put(new Glyph("close"), "✖");
        g.put(new Glyph("pin"), "⚲");
        g.put(new Glyph("check"), "✔");
        g.put(new Glyph("grip"), "⁘");
        g.put(new Glyph("disclose"), "➤");
        g.put(new Glyph("settings"), "⚒");
        return g;
    }

    /** The icons a taxonomy registers under the Icon branch, with the house's palettes - refused if a leaf there says nothing of what it shows. */
    public IconPictures read(Taxonomy taxonomy) {
        var icons = new ArrayList<IconLeaf>();
        for (Taxon t : taxonomy.children(HouseBranches.Icon.INSTANCE)) {
            if (!(t instanceof IconLeaf i)) throw new IllegalArgumentException(t.token() + " is under the Icon branch, and says nothing of what it shows: an icon is an IconLeaf");
            icons.add(i);
        }
        return new IconPictures(icons, base(), styles());
    }
}
