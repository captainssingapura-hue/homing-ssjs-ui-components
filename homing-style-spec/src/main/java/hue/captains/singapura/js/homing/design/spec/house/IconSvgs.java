package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.color.ColorNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorTree;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Where an icon's SVG picture's file is, and what it must be (RFC 0067, D56): the resource at its SVG's class's name, under
 * {@code homing-svg/} - {@code homing-svg/…/styles/base/PacmanSvg.svg} - in a jar of content, as a look's CSS is. The file
 * is held to the colours its SVG imports, both ways. A picture whose file is not there yet is drawn by the page as its
 * placeholder; the content jar's test says which.
 */
public record IconSvgs() implements StatelessFunctionalObject {

    public static final IconSvgs INSTANCE = new IconSvgs();

    /** The directory every SVG picture's file is under, from the classpath's root. */
    public static final String PREFIX = "homing-svg/";

    /** The resource an SVG's file is: the prefix, then its class's name as a path, {@code .svg}. */
    public String resource(SvgWithColorScheme<?> svg) { return PREFIX + svg.getClass().getName().replace('.', '/') + ".svg"; }

    /** An SVG's file, read from its class's loader and held whole; none when it is not there yet. */
    public Optional<IconSvg> find(SvgWithColorScheme<?> svg) {
        String resource = resource(svg);
        ClassLoader loader = svg.getClass().getClassLoader() != null ? svg.getClass().getClassLoader() : ClassLoader.getSystemClassLoader();
        URL where = loader.getResource(resource);
        if (where == null) return Optional.empty();
        try (InputStream in = where.openStream()) {
            return Optional.of(new IconSvg(resource, StandardCharsets.UTF_8.newDecoder().decode(java.nio.ByteBuffer.wrap(in.readAllBytes())).toString()));
        } catch (IOException e) {
            throw new IllegalStateException(resource + ": there at " + where + ", and not to be read as UTF-8: " + e);
        }
    }

    /** One SVG a palette draws: the style whose palette it is, and the SVG its picture imports. */
    public record Drawn(DesignStyle<?> drawer, SvgWithColorScheme<?> svg) {}

    /** Every SVG drawn in the styles given, each once, by the palette that draws it - a style's own, else base's. */
    public List<Drawn> drawn(IconPictures pictures, List<DesignStyle<?>> styles) {
        var out = new LinkedHashSet<Drawn>();
        for (DesignStyle<?> s : styles)
            for (IconLeaf i : pictures.icons())
                if (pictures.picture(s, i) instanceof IconPicture.Svg<?> v) out.add(new Drawn(pictures.drawer(s, i), v.svg()));
        return List.copyOf(out);
    }

    /** The resource names of every SVG drawn in the styles given. */
    public List<String> expected(IconPictures pictures, List<DesignStyle<?>> styles) {
        return drawn(pictures, styles).stream().map(d -> resource(d.svg())).toList();
    }

    /** The SVGs drawn in the styles given whose files are not there yet: each drawn as its placeholder. */
    public List<String> awaiting(IconPictures pictures, List<DesignStyle<?>> styles) {
        return drawn(pictures, styles).stream().filter(d -> find(d.svg()).isEmpty()).map(d -> resource(d.svg())).toList();
    }

    /** The colours an SVG imports, by their names: {@code accent}. */
    public List<String> imported(SvgWithColorScheme<?> svg) { return svg.colours().stream().map(ColorNode::token).toList(); }

    /**
     * A file held to the colours its SVG imports, both ways (D32): each colour it names imported, each imported named. Every
     * problem, each naming the file; none when they agree.
     */
    public List<String> against(IconSvg file, SvgWithColorScheme<?> svg) {
        var out = new ArrayList<String>();
        List<String> imported = imported(svg);
        if (new LinkedHashSet<>(imported).size() != imported.size()) out.add(file.resource() + ": " + svg.getClass().getSimpleName() + " imports a colour twice - " + imported);
        for (String c : file.colours()) if (!imported.contains(c)) out.add(file.resource() + ": names --fp-" + c + ", which " + svg.getClass().getSimpleName() + " does not import");
        for (String c : imported) if (!file.colours().contains(c)) out.add(file.resource() + ": " + svg.getClass().getSimpleName() + " imports " + c + ", which it never names");
        return List.copyOf(out);
    }

    /**
     * Every SVG drawn whose file is there, held whole: the file to the colours its SVG imports, both ways; and each colour
     * imported a palettable colour of the tree, seen by the scheme of the style that draws it (D7) - which the compiler
     * holds, but for a raw type. An SVG whose file is not there yet imports nothing. Every problem; none when all are whole.
     */
    public List<String> problems(IconPictures pictures, List<DesignStyle<?>> styles, ColorTree tree) {
        Map<String, ColorNode<?, ?, ?>> nodes = tree.nodes().stream().collect(Collectors.toMap(ColorNode::token, Function.identity(), (a, b) -> a));
        var out = new ArrayList<String>();
        for (Drawn d : drawn(pictures, styles)) {
            Optional<IconSvg> found;
            try { found = find(d.svg()); }
            catch (IllegalArgumentException | IllegalStateException e) { out.add(e.getMessage()); continue; }
            if (found.isEmpty()) {
                if (!d.svg().colours().isEmpty()) out.add(resource(d.svg()) + ": not there yet, and " + d.svg().getClass().getSimpleName() + " imports colours - " + imported(d.svg()));
                continue;
            }
            out.addAll(against(found.get(), d.svg()));
            Optional<ColorScheme> scheme = tree.scheme(d.drawer());
            for (Palettable<?, ?> colour : d.svg().colours()) {
                ColorNode<?, ?, ?> node = nodes.get(colour.token());
                if (!(node instanceof Palettable<?, ?>)) out.add(found.get().resource() + ": imports " + colour.token() + ", no palettable colour of the tree");
                else if (scheme.isEmpty() || !tree.sees(scheme.get(), node))
                    out.add(found.get().resource() + ": imports " + colour.token() + ", " + tree.schemeName(tree.scheme(node)) + "'s, which " + d.drawer().token() + "'s scheme does not see (D7)");
            }
        }
        return List.copyOf(out);
    }
}
