package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.spec.house.IconPicture.Glyph;
import hue.captains.singapura.js.homing.design.spec.house.IconPicture.Svg;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's icon palettes (RFC 0067, D56): base's, a picture of every house icon - a character for seventeen, an SVG
 * for five: rotate and flip, the samples, the page drawing a placeholder for one not here yet; the pacman; the
 * preferences; and the plus, drawn so that no font moves it off its box's middle (22 Oct) - and each house style's own where it
 * differs: five a cross of their own, sketchy six. Editorial draws base's. The characters are those each style's sheet
 * drew on the icon's ::before before D56, moved here unchanged.
 */
public record HouseIconPalettes() implements StatelessFunctionalObject {

    public static final HouseIconPalettes INSTANCE = new HouseIconPalettes();

    /** Base's palette: every icon. */
    public IconPalette<BaseScheme> base() {
        var p = new LinkedHashMap<IconLeaf, IconPicture<? super BaseScheme>>();
        p.put(HouseIcons.AddIcon.INSTANCE, new Svg<>(new hue.captains.singapura.js.homing.design.spec.styles.base.AddSvg()));   // a character sat off its box's middle (22 Oct)
        p.put(HouseIcons.RemoveIcon.INSTANCE, new Glyph("−"));
        p.put(HouseIcons.CloseIcon.INSTANCE, new Glyph("✕"));
        p.put(HouseIcons.ResetIcon.INSTANCE, new Glyph("↺"));
        p.put(HouseIcons.RotateIcon.INSTANCE, new Svg<>(new hue.captains.singapura.js.homing.design.spec.styles.base.RotateSvg()));
        p.put(HouseIcons.FlipIcon.INSTANCE, new Svg<>(new hue.captains.singapura.js.homing.design.spec.styles.base.FlipSvg()));
        p.put(HouseIcons.PinIcon.INSTANCE, new Glyph("⌖"));
        p.put(HouseIcons.CheckIcon.INSTANCE, new Glyph("✓"));
        p.put(HouseIcons.GripIcon.INSTANCE, new Glyph("⠿"));
        p.put(HouseIcons.DiscloseIcon.INSTANCE, new Glyph("▸"));
        p.put(HouseIcons.SettingsIcon.INSTANCE, new Glyph("⚙"));
        p.put(HouseIcons.PreferencesIcon.INSTANCE, new Svg<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesSvg()));
        p.put(HouseIcons.SizeIcon.INSTANCE, new Glyph("⤢"));
        p.put(HouseIcons.AspectIcon.INSTANCE, new Glyph("⬌"));
        p.put(HouseIcons.ExtentIcon.INSTANCE, new Glyph("◐"));
        p.put(HouseIcons.LevelIcon.INSTANCE, new Glyph("≡"));
        p.put(HouseIcons.ColumnIcon.INSTANCE, new Glyph("▥"));
        p.put(HouseIcons.RowIcon.INSTANCE, new Glyph("▤"));
        p.put(HouseIcons.MergeIcon.INSTANCE, new Glyph("▣"));
        p.put(HouseIcons.DetachIcon.INSTANCE, new Glyph("⧉"));
        p.put(HouseIcons.WithinIcon.INSTANCE, new Glyph("◉"));
        p.put(HouseIcons.PacmanIcon.INSTANCE, new Svg<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PacmanSvg()));
        return new IconPalette<>(Base.INSTANCE, p);
    }

    /** Each style's palette that draws something of its own. */
    public List<IconPalette<?>> styles() {
        return List.of(
                new IconPalette<>(Glassmorphism.INSTANCE, Map.of(HouseIcons.CloseIcon.INSTANCE, new Glyph("×"))),
                new IconPalette<>(NeoBrutalism.INSTANCE, Map.of(HouseIcons.CloseIcon.INSTANCE, new Glyph("╳"))),
                new IconPalette<>(NeoFuturism.INSTANCE, Map.of(HouseIcons.CloseIcon.INSTANCE, new Glyph("⨯"))),
                new IconPalette<>(Neumorphism.INSTANCE, Map.of(HouseIcons.CloseIcon.INSTANCE, new Glyph("✗"))),
                new IconPalette<>(RetroFuturism.INSTANCE, Map.of(HouseIcons.CloseIcon.INSTANCE, new Glyph("✘"))),
                new IconPalette<>(Sketchy.INSTANCE, sketchy()));
    }

    private static Map<IconLeaf, IconPicture<? super BaseScheme>> sketchy() {
        var p = new LinkedHashMap<IconLeaf, IconPicture<? super BaseScheme>>();
        p.put(HouseIcons.CloseIcon.INSTANCE, new Glyph("✖"));
        p.put(HouseIcons.PinIcon.INSTANCE, new Glyph("⚲"));
        p.put(HouseIcons.CheckIcon.INSTANCE, new Glyph("✔"));
        p.put(HouseIcons.GripIcon.INSTANCE, new Glyph("⁘"));
        p.put(HouseIcons.DiscloseIcon.INSTANCE, new Glyph("➤"));
        p.put(HouseIcons.SettingsIcon.INSTANCE, new Glyph("⚒"));
        p.put(HouseIcons.PacmanIcon.INSTANCE, new Svg<>(new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PacmanSvg()));   // its own, drawn by hand
        return p;
    }

    /** The icons a taxonomy registers under the Icon branch, with the house's palettes - refused if a leaf there is no icon. */
    public IconPictures read(Taxonomy taxonomy) {
        var icons = new ArrayList<IconLeaf>();
        for (Taxon t : taxonomy.children(HouseBranches.Icon.INSTANCE)) {
            if (!(t instanceof IconLeaf i)) throw new IllegalArgumentException(t.token() + " is under the Icon branch, and is no icon: an icon is an IconLeaf");
            icons.add(i);
        }
        return new IconPictures(icons, base(), styles());
    }
}
