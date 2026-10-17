package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForLeavesOf;
import hue.captains.singapura.js.homing.ui.look.IconsModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;

import java.util.List;

/**
 * The {@code Icon} element component (RFC 0067, D52): {@code new Icon(el, ICON.CLOSE_ICON, role?)} dresses an element
 * the caller minted - a span, {@code Icon.TAG} - as the icon handed in, one of {@code ICON}: every leaf of the Icon
 * branch, alike but for their look, so its one class realizes every one ({@link ImplForLeavesOf}). The picture is the icon's look, the style worn's; an icon shows one
 * picture, and another is another icon, which its owner mounts in its place.
 */
public record IconModule() implements DomModule<IconModule>, ImplForLeavesOf<HouseBranches.Icon> {

    /** The class. */
    public record Icon() implements ElementComponent<IconModule> {
        @Override public String tag() { return "span"; }
        @Override public String summary() { return "An icon of the house's, one of ICON: its picture its look, the style worn's."; }
    }

    public static final IconModule INSTANCE = new IconModule();

    @Override
    public ImportsFor<IconModule> imports() {
        return ImportsFor.<IconModule>builder()
                .add(new ModuleImports<>(List.of(new IconStyles.ic_base()), IconStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconsModule.ICON()), IconsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<IconModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Icon()));
    }
}
