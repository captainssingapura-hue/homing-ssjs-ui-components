package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.FixedValue;
import hue.captains.singapura.js.homing.component.taxonomy.PartOnly;
import hue.captains.singapura.js.homing.component.taxonomy.Room;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;

import java.util.List;

/**
 * The house's pan and zoom: a picture that pans and zooms, and the bar that drives it. Defined here, with the house's
 * other components, whether or not its implementation is on the classpath; implemented - and drawn by Base - in {@code
 * homing-ui-panzoom} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HousePanZoom {

    private HousePanZoom() {}

    /**
     * A picture that pans and zooms, and the bar that zooms it at its foot, where it is made with one (owner, 23 Oct): the
     * view keeps its bar right itself, and tells nothing outside. The drawing is the caller's.
     */
    public record SvgPanZoom() implements Composite<HouseBranches.View> {
        public static final SvgPanZoom INSTANCE = new SvgPanZoom();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() { return List.of(DSL.part(PanZoomBar.INSTANCE).as(HouseShaping.Foot.INSTANCE).optional()); }
        /** Its viewport, the box it scrolls in, and the canvas the drawing moves on: its own, no parts. */
        @Override public List<Room> rooms() { return List.of(new Room("viewport"), new Room("canvas")); }
        /** Its zoom's bounds, as shares of the fit in percent, and whether a plain wheel zooms it: immutable for its life. */
        @Override public List<FixedValue> fixedValues() {
            return List.of(new FixedValue.Group("zoom", List.of(new FixedValue.Whole("least", 25, false), new FixedValue.Whole("most", 800, false))),
                    new FixedValue.OneOf("wheel", List.of("modified", "plain"), "modified", false));
        }
    }

    /**
     * A picture's zoom, read out, with buttons to change it: a part of the view's only (owner, 23 Oct) - it shows what its
     * view says, and passes its presses on, counted by the action.
     */
    public record PanZoomBar() implements Composite<HouseBranches.Bar>, PartOnly {
        public static final PanZoomBar INSTANCE = new PanZoomBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        /** Its presses, by the action, passed on to its view. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.ActionControl.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.ZoomOut.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.ZoomIn.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Fit.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
