package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.HrefManager;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The brand: the site's own - its mark and its name in the house's wordmark - and the way home. A
 * branch component, worn in the brand's look: its colour its mark's - a brand mark, its symbol - its
 * name in the colour of what holds it.
 */
public record BrandModule() implements DomModule<BrandModule>, ImplFor<HouseControls.Brand> {

    /** A branch component: {@code new Brand(branch, { label, home }, role?)}; {@code root}. */
    public record Brand() implements BranchComponent<BrandModule> {
        @Override public String summary() { return "The site's own: its mark and its name, the way home."; }
    }

    public static final BrandModule INSTANCE = new BrandModule();

    @Override
    public ImportsFor<BrandModule> imports() {
        return ImportsFor.<BrandModule>builder()
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new BrandMarkModule.BrandMark(), new BrandMarkModule.BrandMarkFactory()), BrandMarkModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new WordmarkModule.Wordmark()), WordmarkModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HrefManager.HrefManagerInstance()), HrefManager.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_brand(),
                        new ElementStyles.el_brand_mark()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<BrandModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Brand()));
    }
}
