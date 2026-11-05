package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.js.CoreJsCrate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.icons.UiIconsCrate;
import hue.captains.singapura.js.homing.ui.look.UiLookCrate;

import java.util.List;

/**
 * The elements' crate: the components and their styles, on the runtime
 * ({@code ServerCrate} — the component base's crate, under its old name
 * until the old structure retires), each worn in the house's look
 * ({@code UiLookCrate}, the design's: the look, its castings, its sheet).
 */
public final class UiElementsCrate implements Crate, ComponentVehicle {

    public static final UiElementsCrate INSTANCE = new UiElementsCrate();

    private UiElementsCrate() {}

    @Override public String name() { return "homing-ui-elements"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, CoreJsCrate.INSTANCE, UiLookCrate.INSTANCE, UiIconsCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiElementsComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                new CrateEntry(SimpleComponentModule.INSTANCE),
                new CrateEntry(ButtonBehaviorModule.INSTANCE),
                new CrateEntry(ButtonModule.INSTANCE),
                new CrateEntry(PlainButtonModule.INSTANCE),
                new CrateEntry(PrimaryButtonModule.INSTANCE),
                new CrateEntry(SecondaryButtonModule.INSTANCE),
                new CrateEntry(DangerButtonModule.INSTANCE),
                new CrateEntry(WarningButtonModule.INSTANCE),
                new CrateEntry(SuccessButtonModule.INSTANCE),
                new CrateEntry(ToggleButtonBehaviorModule.INSTANCE),
                new CrateEntry(ToggleButtonModule.INSTANCE),
                new CrateEntry(CloseButtonModule.INSTANCE),
                new CrateEntry(IconButtonModule.INSTANCE),
                new CrateEntry(TextBehaviorModule.INSTANCE),
                new CrateEntry(TextModule.INSTANCE),
                new CrateEntry(HeadingModule.INSTANCE),
                new CrateEntry(Heading1Module.INSTANCE),
                new CrateEntry(Heading2Module.INSTANCE),
                new CrateEntry(Heading3Module.INSTANCE),
                new CrateEntry(Heading4Module.INSTANCE),
                new CrateEntry(Heading5Module.INSTANCE),
                new CrateEntry(Heading6Module.INSTANCE),
                new CrateEntry(KickerModule.INSTANCE),
                new CrateEntry(CaptionModule.INSTANCE),
                new CrateEntry(LedeModule.INSTANCE),
                new CrateEntry(LabelModule.INSTANCE),
                new CrateEntry(TextCellModule.INSTANCE),
                new CrateEntry(ReadoutModule.INSTANCE),
                new CrateEntry(BadgeModule.INSTANCE),
                new CrateEntry(PillModule.INSTANCE),
                new CrateEntry(CodeModule.INSTANCE),
                new CrateEntry(SuccessBadgeModule.INSTANCE),
                new CrateEntry(WarningBadgeModule.INSTANCE),
                new CrateEntry(DangerBadgeModule.INSTANCE),
                new CrateEntry(WordmarkModule.INSTANCE),
                // the ways somewhere - a link, a crumb - and the veil behind a dialog
                new CrateEntry(LinkBehaviorModule.INSTANCE),
                new CrateEntry(LinkModule.INSTANCE),
                new CrateEntry(CrumbModule.INSTANCE),
                // the browser's own inputs, riding on their elements
                new CrateEntry(InputBehaviorsModule.INSTANCE),
                new CrateEntry(TextBoxModule.INSTANCE),
                new CrateEntry(CheckboxModule.INSTANCE),
                new CrateEntry(RangeModule.INSTANCE),
                new CrateEntry(DegreeScaleModule.INSTANCE),   // a range's degree read on its caller's scale
                new CrateEntry(SelectModule.INSTANCE),
                new CrateEntry(ScrimBehaviorModule.INSTANCE),
                new CrateEntry(ScrimModule.INSTANCE),
                // the marks between things: a line between rows, a sign between steps; the brand's own sign
                new CrateEntry(MarkBehaviorModule.INSTANCE),
                new CrateEntry(SeparatorModule.INSTANCE),
                new CrateEntry(DelimiterModule.INSTANCE),
                new CrateEntry(BrandMarkModule.INSTANCE),
                new CrateEntry(LampModule.INSTANCE),
                new CrateEntry(DropMarkModule.INSTANCE),
                new CrateEntry(BoundaryLineModule.INSTANCE),
                // the items: a menu's, a list's row, a choice's option - one behaviour for the three
                new CrateEntry(ItemBehaviorModule.INSTANCE),
                new CrateEntry(MenuItemModule.INSTANCE),
                new CrateEntry(PlainMenuItemModule.INSTANCE),
                new CrateEntry(CheckMenuItemModule.INSTANCE),
                new CrateEntry(ListRowModule.INSTANCE),
                new CrateEntry(ChoiceOptionModule.INSTANCE),
                // the cards - a container, plain or a button - and the summary, a view shown on any container
                new CrateEntry(PlainCardModule.INSTANCE),
                new CrateEntry(CardBehaviorModule.INSTANCE),
                new CrateEntry(ClickableCardModule.INSTANCE),
                new CrateEntry(SummaryViewModule.INSTANCE),
                // the slider's parts and the toggle's, each a component; the grip
                new CrateEntry(ToggleGrooveModule.INSTANCE),
                new CrateEntry(ToggleKnobModule.INSTANCE),
                new CrateEntry(GripModule.INSTANCE),
                new CrateEntry(SwitchModule.INSTANCE),
                new CrateEntry(PaneThumbModule.INSTANCE),
                new CrateEntry(SliderModule.INSTANCE),
                new CrateEntry(StatusFooterModule.INSTANCE),
                new CrateEntry(ExpandableFooterAreaModule.INSTANCE),
                new CrateEntry(ExpandableFooterAreaBehaviorModule.INSTANCE),
                new CrateEntry(SiteBarModule.INSTANCE),
                new CrateEntry(BrandModule.INSTANCE),
                new CrateEntry(TrailModule.INSTANCE),
                new CrateEntry(ElementStyles.INSTANCE));
    }
}
