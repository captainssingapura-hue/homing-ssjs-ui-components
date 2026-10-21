package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.color.ColorNode;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
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
 * Where an icon's SVG picture is, and what it must be (RFC 0067, D56): a resource of the style whose palette draws it,
 * under {@code homing-svg/} at that style's class's name - {@code homing-svg/…/ui/styles/Base/rotate.svg} - in a jar of
 * content, as a sheet is. A picture whose resource is not there yet is drawn by the page as its placeholder; the content
 * jar's test says which.
 */
public record IconSvgs() implements StatelessFunctionalObject {

    public static final IconSvgs INSTANCE = new IconSvgs();

    /** The directory every SVG picture is under, from the classpath's root. */
    public static final String PREFIX = "homing-svg/";

    /** The resource a style's SVG picture is: the prefix, the style's class's name as a path, the picture's name, {@code .svg}. */
    public String resource(DesignStyle<?> drawer, IconPicture.Svg svg) {
        return PREFIX + drawer.getClass().getName().replace('.', '/') + "/" + svg.resource() + ".svg";
    }

    /** A style's SVG picture, read from its style's class's loader and held whole; none when it is not there yet. */
    public Optional<IconSvg> find(DesignStyle<?> drawer, IconPicture.Svg svg) {
        String resource = resource(drawer, svg);
        ClassLoader loader = drawer.getClass().getClassLoader() != null ? drawer.getClass().getClassLoader() : ClassLoader.getSystemClassLoader();
        URL where = loader.getResource(resource);
        if (where == null) return Optional.empty();
        try (InputStream in = where.openStream()) {
            return Optional.of(new IconSvg(resource, StandardCharsets.UTF_8.newDecoder().decode(java.nio.ByteBuffer.wrap(in.readAllBytes())).toString()));
        } catch (IOException e) {
            throw new IllegalStateException(resource + ": there at " + where + ", and not to be read as UTF-8: " + e);
        }
    }

    /** One SVG picture a palette draws: the style whose palette it is, and the picture. */
    public record Drawn(DesignStyle<?> drawer, IconPicture.Svg svg) {}

    /** Every SVG picture drawn in the styles given, each once, by the palette that draws it - a style's own, else base's. */
    public List<Drawn> drawn(IconPictures pictures, List<DesignStyle<?>> styles) {
        var out = new LinkedHashSet<Drawn>();
        for (DesignStyle<?> s : styles)
            for (IconLeaf i : pictures.icons())
                if (pictures.picture(s, i) instanceof IconPicture.Svg v) out.add(new Drawn(pictures.drawer(s, i), v));
        return List.copyOf(out);
    }

    /** The resource names of every SVG picture drawn in the styles given. */
    public List<String> expected(IconPictures pictures, List<DesignStyle<?>> styles) {
        return drawn(pictures, styles).stream().map(d -> resource(d.drawer(), d.svg())).toList();
    }

    /** The SVG pictures drawn in the styles given whose resources are not there yet: each drawn as its placeholder. */
    public List<String> awaiting(IconPictures pictures, List<DesignStyle<?>> styles) {
        return drawn(pictures, styles).stream().filter(d -> find(d.drawer(), d.svg()).isEmpty()).map(d -> resource(d.drawer(), d.svg())).toList();
    }

    /**
     * Every SVG picture there, held to the colour tree: each colour it names a palettable colour of the tree, seen by the
     * scheme of the style whose palette draws it (D7). Every problem, each naming its resource; none when all are whole.
     */
    public List<String> problems(IconPictures pictures, List<DesignStyle<?>> styles, ColorTree tree) {
        Map<String, ColorNode<?, ?, ?>> nodes = tree.nodes().stream().collect(Collectors.toMap(ColorNode::token, Function.identity(), (a, b) -> a));
        var out = new ArrayList<String>();
        for (Drawn d : drawn(pictures, styles)) {
            Optional<IconSvg> found;
            try { found = find(d.drawer(), d.svg()); }
            catch (IllegalArgumentException | IllegalStateException e) { out.add(e.getMessage()); continue; }
            if (found.isEmpty()) continue;
            Optional<ColorScheme> scheme = tree.scheme(d.drawer());
            for (String token : found.get().colours()) {
                ColorNode<?, ?, ?> node = nodes.get(token);
                if (!(node instanceof Palettable<?, ?>)) out.add(found.get().resource() + ": --fp-" + token + " names no palettable colour of the tree");
                else if (scheme.isEmpty() || !tree.sees(scheme.get(), node))
                    out.add(found.get().resource() + ": --fp-" + token + " is " + tree.schemeName(tree.scheme(node)) + "'s, which " + d.drawer().token() + "'s scheme does not see (D7)");
            }
        }
        return List.copyOf(out);
    }
}
