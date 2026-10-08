package hue.captains.singapura.js.homing.ui.controllability;

import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Ask;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Aspect;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Clear;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Close;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Colour;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Current;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Enabled;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.EvenOut;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Fit;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Held;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Missing;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Move;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Next;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Open;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.OpenModal;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Raised;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.ResetLayout;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Resize;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Ring;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Size;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.Split;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.ZoomIn;
import hue.captains.singapura.js.homing.ui.controllability.HouseControlOptions.ZoomOut;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's controllability: its control options, the control types they make up, and every leaf
 * of the house's taxonomy classified by type - declared on a branch where every leaf under it is
 * controlled alike, on a leaf where it alone is. A leaf with nothing declared on its lineage is
 * {@link #PLAIN}: it varies along its axes, and nothing more. The types are refined as the house's
 * components are, so they are few, and named for what their components have in common.
 */
public record HouseControllability() implements StatelessFunctionalObject {

    public static final HouseControllability INSTANCE = new HouseControllability();

    /** Its axes, and nothing more. */
    public static final ControlType PLAIN = new ControlType("plain", List.of());
    /** Switched on and off: off, inert, and saying so. */
    public static final ControlType SWITCHABLE = new ControlType("switchable", List.of(Enabled.INSTANCE));
    /** Made the current one, and raised off its plane. */
    public static final ControlType EMPHASISED = new ControlType("emphasised", List.of(Current.INSTANCE, Raised.INSTANCE));
    /** Held shown, where it would hide again by itself. */
    public static final ControlType HOLDABLE = new ControlType("holdable", List.of(Held.INSTANCE));
    /** Opened over the page, modal or not, and closed. */
    public static final ControlType MODAL_OVERLAY = new ControlType("modal-overlay", List.of(OpenModal.INSTANCE, Open.INSTANCE, Close.INSTANCE));
    /** Opened over the page, and closed. */
    public static final ControlType OVERLAY = new ControlType("overlay", List.of(Open.INSTANCE, Close.INSTANCE));
    /** Zoomed in and out, and fitted. */
    public static final ControlType ZOOMABLE = new ControlType("zoomable", List.of(ZoomIn.INSTANCE, ZoomOut.INSTANCE, Fit.INSTANCE));
    /** Its room split, evened out, and put back as it was. */
    public static final ControlType ARRANGEABLE = new ControlType("arrangeable", List.of(Split.INSTANCE, EvenOut.INSTANCE, ResetLayout.INSTANCE));
    /** Moved, sized and ringed by call. */
    public static final ControlType PLACEABLE = new ControlType("placeable", List.of(Move.INSTANCE, Resize.INSTANCE, Ring.INSTANCE));
    /** What it shows changed: the next, none, one it lacks. */
    public static final ControlType CONTENT = new ControlType("content", List.of(Next.INSTANCE, Clear.INSTANCE, Missing.INSTANCE));
    /** Asked what it shows now. */
    public static final ControlType MONITOR = new ControlType("monitor", List.of(Ask.INSTANCE));

    /** Every option the house files, category by category. */
    public List<ControlOption<?>> options() {
        return List.of(
                Colour.INSTANCE, Size.INSTANCE, Aspect.INSTANCE,
                Enabled.INSTANCE, Current.INSTANCE, Raised.INSTANCE, Held.INSTANCE,
                OpenModal.INSTANCE, Open.INSTANCE, Close.INSTANCE,
                ZoomIn.INSTANCE, ZoomOut.INSTANCE, Fit.INSTANCE,
                Split.INSTANCE, EvenOut.INSTANCE, ResetLayout.INSTANCE,
                Move.INSTANCE, Resize.INSTANCE, Ring.INSTANCE,
                Next.INSTANCE, Clear.INSTANCE, Missing.INSTANCE,
                Ask.INSTANCE);
    }

    /** The catalogue, read - or refused with every problem in it. */
    public ControlCatalogue catalogue() { return ReadControls.INSTANCE.read(options()); }

    /** The house's leaves classified: on a branch for every leaf under it, on a leaf for itself; plain otherwise. */
    public ControlClassification classification() {
        return new ControlClassification(List.of(
                new ControlTypeAt(HouseBranches.Button.INSTANCE, SWITCHABLE),
                new ControlTypeAt(HouseControls.Slider.INSTANCE, SWITCHABLE),
                new ControlTypeAt(HouseContainers.Panel.INSTANCE, EMPHASISED),
                new ControlTypeAt(HouseContainers.EdgeStrip.INSTANCE, HOLDABLE),
                new ControlTypeAt(HouseContainers.Dialog.INSTANCE, MODAL_OVERLAY),
                new ControlTypeAt(HouseContainers.ContextMenu.INSTANCE, OVERLAY),
                new ControlTypeAt(HouseContainers.FloatLayer.INSTANCE, OVERLAY),
                new ControlTypeAt(HouseContainers.TabOpener.INSTANCE, OVERLAY),
                new ControlTypeAt(HouseContainers.SvgPanZoom.INSTANCE, ZOOMABLE),
                new ControlTypeAt(HouseBranches.Split.INSTANCE, ARRANGEABLE),
                new ControlTypeAt(HouseContainers.PaneThumbs.INSTANCE, ARRANGEABLE),
                new ControlTypeAt(HouseContainers.FloatingPane.INSTANCE, PLACEABLE),
                new ControlTypeAt(HouseMarks.Icon.INSTANCE, CONTENT),
                new ControlTypeAt(HouseContainers.FocusMonitor.INSTANCE, MONITOR),
                new ControlTypeAt(HouseContainers.StewardMonitor.INSTANCE, MONITOR),
                new ControlTypeAt(HouseContainers.ContextMenuSteward.INSTANCE, MONITOR)),
                PLAIN);
    }

    /** {@code controlType = findControlType(component)}, in the house's taxonomy as read. */
    public ControlType findControlType(Taxonomy taxonomy, Taxon component) { return classification().findControlType(taxonomy, component); }

    /** What a leaf is controlled by: its axes' degrees, then its type's options. */
    public List<ControlOption<?>> optionsFor(Taxonomy taxonomy, Taxon component) { return classification().optionsFor(taxonomy, catalogue(), component); }
}
