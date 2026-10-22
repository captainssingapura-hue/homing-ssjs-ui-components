package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-ui-panes's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - 18, each a class of its own in hue.captains.singapura.js.homing.design.styles.base - the floating pane and
 * its head among them, moved here with them (23 Oct).
 */
public record UiPanesStyleDeclaration() implements StyleDeclaration {

    public static final UiPanesStyleDeclaration INSTANCE = new UiPanesStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of(
                new Tab(),
                new TabStrip(),
                new PaneThumbs(),
                new AddTab(),
                new FloatingPane(),
                new TitleBar(),
                new MultiTabPane(),
                new SingleTabPane(),
                new TabPane(),
                new PaneThumbsWhere(),
                new SelectWhat(),
                new SelectHow(),
                new PlainButtonGo(),
                new ColumnIcon(),
                new RowIcon(),
                new MergeIcon(),
                new DetachIcon(),
                new WithinIcon())));
    }
}
