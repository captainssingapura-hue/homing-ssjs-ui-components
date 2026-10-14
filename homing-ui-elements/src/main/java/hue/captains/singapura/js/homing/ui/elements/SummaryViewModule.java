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
 * The summary's view: sums one thing up, laid out whole - its title and a tag beside it, a line of summary,
 * a way to the thing. A view with no frame of its own, shown on whatever holds it: a card, a pane, a
 * dialog. A branch component: the caller makes a sub-branch for it; it mints its tree there and
 * offers {@code root}. It holds no state, fetches nothing and navigates on its own nowhere - its
 * link is set through the href manager.
 */
public record SummaryViewModule() implements DomModule<SummaryViewModule>, ImplFor<HouseContainers.SummaryView> {

    /** {@code new SummaryView(branch, { title, tag, text, link, size })}; {@code root}; size, title, dispose. */
    public record SummaryView() implements BranchComponent<SummaryViewModule> {
        @Override public String summary() { return "Sums one thing up, laid out whole: a title and a tag beside it, a line of summary, a way to the thing - shown on any container."; }
    }

    public static final SummaryViewModule INSTANCE = new SummaryViewModule();

    @Override
    public ImportsFor<SummaryViewModule> imports() {
        return ImportsFor.<SummaryViewModule>builder()
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Heading4Module.Heading4()), Heading4Module.INSTANCE))
                .add(new ModuleImports<>(List.of(new BadgeModule.Badge()), BadgeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LinkModule.Link()), LinkModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_summary(),
                        new ElementStyles.el_summary_head(),
                        new ElementStyles.el_summary_title(),
                        new ElementStyles.el_summary_tag(),
                        new ElementStyles.el_summary_text(),
                        new ElementStyles.el_summary_foot()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SummaryViewModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SummaryView()));
    }
}
