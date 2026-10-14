package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentLeaf;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.Set;

/**
 * The house's leaves by family, as they move onto files one family at a time (RFC 0067, appendix Two Classes per Leaf,
 * phase 5): what a style says it draws its own way, family by family. Knows no style, so a style may read it while the
 * styles are being made.
 */
public final class HouseFamilies {

    private HouseFamilies() {}

    /** The buttons: the family every house style draws its own way first, each in a whole file of its own. */
    public static final Set<ComponentLeaf> BUTTONS = Set.of(
            HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseControls.SecondaryButton.INSTANCE,
            HouseControls.DangerButton.INSTANCE, HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
            HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE);
}
