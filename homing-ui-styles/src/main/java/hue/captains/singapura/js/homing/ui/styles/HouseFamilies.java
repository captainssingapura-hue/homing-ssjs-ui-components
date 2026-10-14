package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.Set;

/**
 * The house's components by family, as they move onto files one family at a time (RFC 0067, appendix Two Classes per Leaf,
 * phase 5): what a style says it draws its own way, family by family. Knows no style, so a style may read it while the
 * styles are being made.
 */
public final class HouseFamilies {

    private HouseFamilies() {}

    /** The buttons: the family every house style draws its own way first - each button on its own and in every role it is cast in, a whole file each. */
    public static final Set<Component<?>> BUTTONS = Set.of(
            HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseControls.SecondaryButton.INSTANCE,
            HouseControls.DangerButton.INSTANCE, HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
            HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE);

    /** The ways: a link, a crumb, the brand - each style's own focus ring on them, and its own hand. The brand's mark is Base's. */
    public static final Set<Component<?>> WAYS = Set.of(
            HouseControls.Link.INSTANCE, HouseControls.Crumb.INSTANCE, HouseControls.Brand.INSTANCE);

    /** The text: every style's own faces, weights and spacing on every kind of words - a heading at each level among them. */
    public static final Set<Component<?>> TEXTS = Set.of(
            HouseText.Label.INSTANCE, HouseText.Heading1.INSTANCE, HouseText.Heading2.INSTANCE, HouseText.Heading3.INSTANCE,
            HouseText.Heading4.INSTANCE, HouseText.Heading5.INSTANCE, HouseText.Heading6.INSTANCE, HouseText.Code.INSTANCE,
            HouseText.SuccessBadge.INSTANCE, HouseText.WarningBadge.INSTANCE, HouseText.DangerBadge.INSTANCE,
            HouseText.Kicker.INSTANCE, HouseText.Caption.INSTANCE, HouseText.Lede.INSTANCE, HouseText.Readout.INSTANCE,
            HouseText.Badge.INSTANCE, HouseText.Pill.INSTANCE, HouseText.Wordmark.INSTANCE);

    /** The inputs: the browser's own and the house's - their frames, faces and rings; the switch's and slider's parts their own families'. */
    public static final Set<Component<?>> INPUTS = Set.of(
            HouseControls.TextBox.INSTANCE, HouseControls.Checkbox.INSTANCE, HouseControls.Range.INSTANCE, HouseControls.Select.INSTANCE,
            HouseControls.Switch.INSTANCE, HouseControls.Slider.INSTANCE);

    /** What every house style draws its own way so far: the buttons, the ways, the text, then the inputs. */
    public static final Set<Component<?>> DRAWN = union(BUTTONS, WAYS, TEXTS, INPUTS);

    @SafeVarargs
    private static Set<Component<?>> union(Set<Component<?>>... families) {
        var out = new java.util.LinkedHashSet<Component<?>>();
        for (var f : families) out.addAll(f);
        return Set.copyOf(out);
    }
}
