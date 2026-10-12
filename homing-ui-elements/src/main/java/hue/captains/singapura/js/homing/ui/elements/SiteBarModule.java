package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * The site's bar: heads a site's pages - its brand at the start, the way to where the user is across
 * the middle, what its owner hands it at the end - in the ink written on it. A branch component: it
 * realizes its brand and its trail, and places what it is handed; its look is the house's.
 */
public record SiteBarModule() implements DomModule<SiteBarModule>, ImplFor<HouseContainers.SiteBar> {

    /** A branch component: {@code new SiteBar(branch, { brand, crumbs })}; {@code root}, {@code trail}, {@code end(c)}. */
    public record SiteBar() implements BranchComponent<SiteBarModule> {
        @Override public String summary() { return "Heads a site's pages: its brand, the way to where the user is, and what its owner hands it at its end."; }
    }

    public static final SiteBarModule INSTANCE = new SiteBarModule();

    @Override
    public ImportsFor<SiteBarModule> imports() {
        return ImportsFor.<SiteBarModule>builder()
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new BrandModule.Brand()), BrandModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TrailModule.Trail()), TrailModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_site_bar(),
                        new ElementStyles.el_site_bar_start(),
                        new ElementStyles.el_site_bar_way(),
                        new ElementStyles.el_site_bar_end()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SiteBarModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SiteBar()));
    }
}
