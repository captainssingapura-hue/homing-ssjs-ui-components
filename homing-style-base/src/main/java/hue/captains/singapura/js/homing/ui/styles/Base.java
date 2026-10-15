package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

import java.util.List;

/**
 * The house's base layer (RFC 0067, appendix Two Classes per Leaf): neutral, a look of no style of its own, and the
 * look every house style's absence lands on. It answers content for every casting (D26), each by a class of its own in
 * {@code design.styles.base}, in the jar that brings the casting into being (appendix The Style Jars): a component's
 * with the jar implementing it, a part drawn inline with its owner, a casting a module's part adds in that module. What
 * every house style says alike is said here, so a style's class says only what makes it itself - and says the rest
 * again, whole, where it draws a casting at all (D9). This jar holds its record alone: its token, its scheme, its words.
 */
public record Base() implements DesignStyle<ColorScheme.Base> {

    public static final Base INSTANCE = new Base();

    /** None: its look is in its classes (appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    /**
     * Every house word, as a style draws it unless it draws it otherwise: a symbol of the text's own font, in
     * the colour the mark is given.
     */
    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Add.INSTANCE, "+"), new Writing(HouseWords.Remove.INSTANCE, "−"), new Writing(HouseWords.Close.INSTANCE, "✕"),
            new Writing(HouseWords.Reset.INSTANCE, "↺"), new Writing(HouseWords.Rotate.INSTANCE, "↻"), new Writing(HouseWords.Flip.INSTANCE, "⇋"),
            new Writing(HouseWords.Pin.INSTANCE, "⌖"), new Writing(HouseWords.Check.INSTANCE, "✓"), new Writing(HouseWords.Grip.INSTANCE, "⠿"),
            new Writing(HouseWords.Disclose.INSTANCE, "▸"), new Writing(HouseWords.Settings.INSTANCE, "⚙"), new Writing(HouseWords.Size.INSTANCE, "⤢"),
            new Writing(HouseWords.Aspect.INSTANCE, "⬌"), new Writing(HouseWords.Extent.INSTANCE, "◐"), new Writing(HouseWords.Level.INSTANCE, "≡"));
}
