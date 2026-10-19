package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForLeavesOf;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.core.js.DomOpsPartyModule;

import java.util.List;

/**
 * The {@code Icon} element component (RFC 0067, D52), on the style manager (RFC 0071): {@code new Icon(make, new
 * FixedCloseIcon(), place?)} makes its own span with its owner's maker and is made as the leaf whose fixed object it is
 * handed - every leaf of the Icon branch, alike but for their look, so its one class realizes every one ({@link
 * ImplForLeavesOf}): its module stamped with every leaf's header, merged, and the leaves it realizes. The picture is the
 * icon's look, the style worn's; an icon shows one picture, and another is another icon, which its owner makes in its place.
 */
public record IconModule() implements DomModule<IconModule>, ImplForLeavesOf<HouseBranches.Icon> {

    /** The class. */
    public record Icon() implements ElementComponent<IconModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "An icon of the house's, one of its Icon branch's leaves: its picture its look, the style worn's."; }
    }

    public static final IconModule INSTANCE = new IconModule();

    @Override
    public ImportsFor<IconModule> imports() {
        return ImportsFor.<IconModule>builder()
                .add(new ModuleImports<>(List.of(new IconStyles.ic_base()), IconStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new DomOpsPartyModule.DomOpsParty()), DomOpsPartyModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<IconModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Icon()));
    }
}
