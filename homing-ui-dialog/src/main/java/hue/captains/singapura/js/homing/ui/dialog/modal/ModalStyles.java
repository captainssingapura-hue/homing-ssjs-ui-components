package hue.captains.singapura.js.homing.ui.dialog.modal;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The modal window's own placement (23 Oct): over the whole page, its veil beneath its window. Their looks are the
 * scrim's and the floating pane's.
 */
public record ModalStyles() implements CssGroup<ModalStyles> {

    public static final ModalStyles INSTANCE = new ModalStyles();

    /** Over the whole page, in the page only while it is open. */
    public record mw_layer() implements CssClass<ModalStyles> {
        @Override public String body() { return """
            position: fixed;
            inset: 0;
            z-index: 10010;
            """;
        }
    }

    /** The veil, over the whole page beneath the window. */
    public record mw_veil() implements CssClass<ModalStyles> {
        @Override public String body() { return """
            position: absolute;
            inset: 0;
            """;
        }
    }

    @Override
    public List<CssClass<ModalStyles>> cssClasses() {
        return List.of(new mw_layer(), new mw_veil());
    }
}
