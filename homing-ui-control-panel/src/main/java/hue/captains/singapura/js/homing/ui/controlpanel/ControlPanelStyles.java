package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.Wearable;

import java.util.List;

import static hue.captains.singapura.js.homing.design.DesignClass.of;
import static hue.captains.singapura.js.homing.design.Emphasis.Muted;
import static hue.captains.singapura.js.homing.design.Target.Color;
import static hue.captains.singapura.js.homing.design.Target.Type;
import static hue.captains.singapura.js.homing.design.Text.Caption;

/** The control panel's classes: its column of controls, a row of toggles or buttons, and what it says when there is nothing to control. */
public record ControlPanelStyles() implements CssGroup<ControlPanelStyles> {

    public static final ControlPanelStyles INSTANCE = new ControlPanelStyles();

    /** The panel: the sliders, then the toggles, then the buttons, top to bottom. */
    public record cp_panel() implements CssClass<ControlPanelStyles> {
        @Override public String body() { return "display: flex;\nflex-direction: column;\nalign-items: stretch;\ngap: 10px;\nmin-width: 0;\n"; }
    }

    /** A row of toggles, or of buttons, wrapping. */
    public record cp_row() implements CssClass<ControlPanelStyles> {
        @Override public String body() { return "display: flex;\nflex-wrap: wrap;\nalign-items: center;\ngap: 8px;\n"; }
    }

    /** What the panel says of itself: nothing to control. Small and quiet. */
    public record cp_note() implements CssClass<ControlPanelStyles> {
        @Override public List<? extends Wearable> wears() { return List.of(of(Caption.class, Type.Scale.class), of(Muted.class, Color.Ink.class)); }
        @Override public String body() { return "margin: 0;"; }
    }

    @Override
    public List<CssClass<ControlPanelStyles>> cssClasses() { return List.of(new cp_panel(), new cp_row(), new cp_note()); }
}
