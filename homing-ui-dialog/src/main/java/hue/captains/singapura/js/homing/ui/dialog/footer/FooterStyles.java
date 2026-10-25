package hue.captains.singapura.js.homing.ui.dialog.footer;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The standard footer's own placement (23 Oct): its actions on a row, at its end. Its look - a row of actions, raised
 * and capped off - is its casting's.
 */
public record FooterStyles() implements CssGroup<FooterStyles> {

    public static final FooterStyles INSTANCE = new FooterStyles();

    /** Its actions on a row, at its end; never shrunk by what it ends. */
    public record ft_bar() implements CssClass<FooterStyles> {
        @Override public String body() { return """
            flex: 0 0 auto;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            """;
        }
    }

    @Override
    public List<CssClass<FooterStyles>> cssClasses() {
        return List.of(new ft_bar());
    }
}
