package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.tao.ontology.ValueObject;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An SVG picture of an icon, read from its resource and held whole (RFC 0067, D56): put in the icon's element as it is,
 * never shown as an image or a mask, so it follows what holds it and the colour palette (owner, 21 Oct): by default it
 * takes the colour its icon is given - a shape that says nothing fills in it and draws no stroke, as a character does,
 * and {@code currentColor} says it outright, a stroke in the colour it is given; else a palette's colour by variable,
 * written in it, {@code style="fill: var(--fp-accent)"} - no class on it, and no colour no one chose. Whole means: an
 * {@code svg} of SVG's, a box of its own ({@code viewBox}) and one character's size ({@code 1em} each way), saying no
 * colour of its own; inside, only shapes and groups; no class, no id, nothing that runs or links. Which palette colours it names, and that the style drawing it sees each,
 * is for the colour tree ({@link IconSvgs#problems}).
 *
 * @param resource where it was read: {@code homing-svg/…/Base/rotate.svg}
 * @param text     its text as the resource has it
 */
public record IconSvg(String resource, String text) implements ValueObject {

    private static final String SVG = "http://www.w3.org/2000/svg";
    private static final Set<String> SHAPES = Set.of("path", "circle", "ellipse", "line", "polyline", "polygon", "rect");
    private static final Set<String> SHAPE_ATTRIBUTES = Set.of("d", "points", "x", "y", "width", "height", "cx", "cy", "r", "rx", "ry",
            "x1", "y1", "x2", "y2", "transform", "style", "fill", "stroke", "stroke-width", "stroke-linecap", "stroke-linejoin",
            "stroke-miterlimit", "fill-rule", "clip-rule", "opacity");
    private static final Set<String> GROUP_ATTRIBUTES = Set.of("transform", "opacity");
    private static final Pattern DECLARATION = Pattern.compile("\\s*([a-z-]+)\\s*:\\s*([^;]+?)\\s*(;|$)");
    private static final Pattern PALETTE = Pattern.compile("var\\(--fp-([a-z0-9]+(?:-[a-z0-9]+)*)\\)");

    public IconSvg {
        Objects.requireNonNull(resource, "IconSvg.resource");
        Objects.requireNonNull(text, "IconSvg.text");
        List<String> problems = problems(text);
        if (!problems.isEmpty()) throw new IllegalArgumentException(resource + ": " + String.join("; ", problems));
    }

    /**
     * What the page puts in the icon's element: its text, its comments gone and the space between its tags, its svg
     * filling in the colour the icon is given - the default each shape that says nothing takes.
     */
    public String markup() {
        String bare = text.replaceAll("(?s)<!--.*?-->", "").replaceAll(">\\s+<", "><").replaceAll("\\s+", " ").trim();
        return bare.replaceFirst("<svg\\b", "<svg fill=\"currentColor\"");
    }

    /** The palette's colours it names, each once, in the order it names them: {@code text}, {@code accent}. */
    public List<String> colours() {
        var out = new LinkedHashSet<String>();
        for (Matcher m = PALETTE.matcher(text); m.find(); ) out.add(m.group(1));
        return List.copyOf(out);
    }

    /** Everything that keeps a text from being an icon's SVG picture; none when it is one. */
    private static List<String> problems(String text) {
        var out = new ArrayList<String>();
        Element root;
        try {
            var factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            root = factory.newDocumentBuilder().parse(new InputSource(new StringReader(text))).getDocumentElement();
        } catch (Exception e) {
            return List.of("no SVG to be read - " + e.getMessage());
        }
        if (!SVG.equals(root.getNamespaceURI()) || !"svg".equals(root.getLocalName())) return List.of("its root is no svg of SVG's, " + root.getTagName());
        if (root.getAttribute("viewBox").isBlank()) out.add("its svg has no box of its own: viewBox");
        if (!"1em".equals(root.getAttribute("width")) || !"1em".equals(root.getAttribute("height")))
            out.add("its svg is one character's size, 1em each way - width " + root.getAttribute("width") + ", height " + root.getAttribute("height"));
        attributes(root, Set.of("viewBox", "width", "height"), out);
        int shapes = children(root, out);
        if (shapes == 0) out.add("it draws nothing: no shape in it");
        return out;
    }

    /** Its children, held: groups, each held the same way, and shapes; how many shapes, all told. */
    private static int children(Element parent, List<String> out) {
        int shapes = 0;
        for (Node n = parent.getFirstChild(); n != null; n = n.getNextSibling()) {
            if (n.getNodeType() == Node.TEXT_NODE && n.getTextContent().isBlank() || n.getNodeType() == Node.COMMENT_NODE) continue;
            if (!(n instanceof Element e) || !SVG.equals(e.getNamespaceURI())) { out.add("only shapes and groups inside, not " + n.getNodeName()); continue; }
            String tag = e.getLocalName();
            if (tag.equals("g")) {
                attributes(e, GROUP_ATTRIBUTES, out);
                shapes += children(e, out);
            } else if (SHAPES.contains(tag)) {
                attributes(e, SHAPE_ATTRIBUTES, out);
                painted(e, out);
                if (e.hasChildNodes() && !e.getTextContent().isBlank()) out.add(tag + ": a shape holds nothing");
                shapes++;
            } else out.add("only shapes and groups inside, not " + tag);
        }
        return shapes;
    }

    /** Only the attributes its element may have: no class, no id, nothing that runs or links. */
    private static void attributes(Element e, Set<String> allowed, List<String> out) {
        NamedNodeMap all = e.getAttributes();
        for (int i = 0; i < all.getLength(); i++) {
            String name = all.item(i).getNodeName();
            if (name.equals("xmlns")) continue;
            if (!allowed.contains(name)) out.add(e.getLocalName() + ": " + name + " is no attribute of an icon's picture"
                    + (name.equals("class") ? " - its colours are the palette's variables, written in it, never a class" : ""));
        }
    }

    /**
     * A shape's fill and its stroke: each none, the colour it is given ({@code currentColor}) or a palette's colour in its
     * style - or unsaid, as its svg gives it: a fill in the colour it is given, no stroke. Nothing else in its style.
     */
    private static void painted(Element e, List<String> out) {
        String tag = e.getLocalName();
        var said = new java.util.HashMap<String, String>();
        for (String a : List.of("fill", "stroke")) if (e.hasAttribute(a)) {
            said.put(a, e.getAttribute(a).trim());
            if (!said.get(a).equals("none") && !said.get(a).equals("currentColor"))
                out.add(tag + ": its " + a + " attribute is " + said.get(a) + " - an attribute says none or currentColor; a palette's colour is said in its style, var(--fp-…)");
        }
        String style = e.getAttribute("style");
        int at = 0;
        for (Matcher m = DECLARATION.matcher(style); at < style.length() && m.find(at) && m.start() == at; at = m.end()) {
            String property = m.group(1), value = m.group(2);
            if (!property.equals("fill") && !property.equals("stroke")) { out.add(tag + ": its style says " + property + " - only its fill and its stroke"); continue; }
            if (said.put(property, value) != null) out.add(tag + ": its " + property + " said twice");
        }
        if (!style.substring(at).isBlank()) out.add(tag + ": its style is not to be read from '" + style.substring(at).trim() + "'");
        for (String a : List.of("fill", "stroke")) {
            String v = said.get(a);
            if (v != null && !v.equals("none") && !v.equals("currentColor") && !PALETTE.matcher(v).matches())
                out.add(tag + ": its " + a + " is " + v + " - none, the colour it is given (currentColor), or a palette's colour, var(--fp-…)");
        }
    }
}
