package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The text cell: a table cell's words on a {@code <div>}, worn in its own look (RFC 0067, D42). Plain text set in its
 * cell - a fact in its row, cast as a data cell's value, or the name heading its column, cast as a header cell's title.
 * Its room is its holder's: a grid's, a document table's.
 */
public record TextCellModule() implements DomModule<TextCellModule>, ImplFor<HouseText.TextCell> {

    /** An element component: {@code new TextCell(el, { text, size }, role)} on the element its companion makes; {@code text}, {@code size}. */
    public record TextCell() implements ElementComponent<TextCellModule> {
        @Override public String tag() { return "div"; }
        @Override public String summary() { return "A table cell's words: plain text set in its cell - a fact in its row, or the name heading its column."; }
    }

    /** Its companion: {@code TextCellFactory.create(branch, name)} makes the element it is realized on. */
    public record TextCellFactory() implements Exportable._Constant<TextCellModule> {}

    public static final TextCellModule INSTANCE = new TextCellModule();

    @Override
    public ImportsFor<TextCellModule> imports() {
        return ImportsFor.<TextCellModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TextModule.Text()), TextModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TextCellModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new TextCell(), new TextCellFactory()));
    }
}
