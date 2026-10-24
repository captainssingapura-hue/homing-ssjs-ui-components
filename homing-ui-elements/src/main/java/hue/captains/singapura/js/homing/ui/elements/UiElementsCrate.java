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
                CrateEntry.of(SimpleComponentModule.INSTANCE),
                CrateEntry.of(ButtonBehaviorModule.INSTANCE),
                CrateEntry.of(ButtonModule.INSTANCE),
                CrateEntry.of(PlainButtonModule.INSTANCE),
                CrateEntry.of(PrimaryButtonModule.INSTANCE),
                CrateEntry.of(SecondaryButtonModule.INSTANCE),
                CrateEntry.of(DangerButtonModule.INSTANCE),
                CrateEntry.of(WarningButtonModule.INSTANCE),
                CrateEntry.of(SuccessButtonModule.INSTANCE),
                CrateEntry.of(ToggleButtonBehaviorModule.INSTANCE),
                CrateEntry.of(ToggleButtonModule.INSTANCE),
                CrateEntry.of(CloseButtonModule.INSTANCE),
                CrateEntry.of(IconButtonModule.INSTANCE),
                CrateEntry.of(TextBehaviorModule.INSTANCE),
                CrateEntry.of(TextModule.INSTANCE),
                CrateEntry.of(HeadingModule.INSTANCE),
                CrateEntry.of(Heading1Module.INSTANCE),
                CrateEntry.of(Heading2Module.INSTANCE),
                CrateEntry.of(Heading3Module.INSTANCE),
                CrateEntry.of(Heading4Module.INSTANCE),
                CrateEntry.of(Heading5Module.INSTANCE),
                CrateEntry.of(Heading6Module.INSTANCE),
                CrateEntry.of(KickerModule.INSTANCE),
                CrateEntry.of(CaptionModule.INSTANCE),
                CrateEntry.of(LedeModule.INSTANCE),
                CrateEntry.of(LabelModule.INSTANCE),
                CrateEntry.of(TextCellModule.INSTANCE),
                CrateEntry.of(ReadoutModule.INSTANCE),
                CrateEntry.of(BadgeModule.INSTANCE),
                CrateEntry.of(PillModule.INSTANCE),
                CrateEntry.of(CodeModule.INSTANCE),
                CrateEntry.of(SuccessBadgeModule.INSTANCE),
                CrateEntry.of(WarningBadgeModule.INSTANCE),
                CrateEntry.of(DangerBadgeModule.INSTANCE),
                CrateEntry.of(WordmarkModule.INSTANCE),
                // the ways somewhere - a link, a crumb - and the veil behind a dialog
                CrateEntry.of(LinkBehaviorModule.INSTANCE),
                CrateEntry.of(LinkModule.INSTANCE),
                CrateEntry.of(CrumbModule.INSTANCE),
                // the browser's own inputs, riding on their elements
                CrateEntry.of(InputBehaviorsModule.INSTANCE),
                CrateEntry.of(TextBoxModule.INSTANCE),
                CrateEntry.of(CheckboxModule.INSTANCE),
                CrateEntry.of(RangeModule.INSTANCE),
                CrateEntry.of(DegreeScaleModule.INSTANCE),   // a range's degree read on its caller's scale
                CrateEntry.of(SelectModule.INSTANCE),
                CrateEntry.of(ScrimBehaviorModule.INSTANCE),
                CrateEntry.of(ScrimModule.INSTANCE),
                // the marks between things: a line between rows, a sign between steps; the brand's own sign
                CrateEntry.of(MarkBehaviorModule.INSTANCE),
                CrateEntry.of(SeparatorModule.INSTANCE),
                CrateEntry.of(DelimiterModule.INSTANCE),
                CrateEntry.of(BrandMarkModule.INSTANCE),
                CrateEntry.of(LampModule.INSTANCE),
                CrateEntry.of(DropMarkModule.INSTANCE),
                CrateEntry.of(BoundaryLineModule.INSTANCE),
                // the items: a menu's, a list's row, a choice's option - one behaviour for the three
                CrateEntry.of(ItemBehaviorModule.INSTANCE),
                CrateEntry.of(MenuItemModule.INSTANCE),
                CrateEntry.of(PlainMenuItemModule.INSTANCE),
                CrateEntry.of(CheckMenuItemModule.INSTANCE),
                CrateEntry.of(ListRowModule.INSTANCE),
                CrateEntry.of(ChoiceOptionModule.INSTANCE),
                // the cards - a container, plain or a button - and the summary, a view shown on any container
                CrateEntry.of(PlainCardModule.INSTANCE),
                CrateEntry.of(CardBehaviorModule.INSTANCE),
                CrateEntry.of(ClickableCardModule.INSTANCE),
                CrateEntry.of(SummaryViewModule.INSTANCE),
                // the slider's parts and the toggle's, each a component; the grip
                CrateEntry.of(ToggleGrooveModule.INSTANCE),
                CrateEntry.of(ToggleKnobModule.INSTANCE),
                CrateEntry.of(GripModule.INSTANCE),
                CrateEntry.of(SwitchModule.INSTANCE),
                CrateEntry.of(PaneThumbModule.INSTANCE),
                CrateEntry.of(SliderModule.INSTANCE),
                CrateEntry.of(StatusFooterModule.INSTANCE),
                CrateEntry.of(ExpandableFooterAreaModule.INSTANCE),
                CrateEntry.of(ExpandableFooterAreaBehaviorModule.INSTANCE),
                CrateEntry.of(SiteBarModule.INSTANCE),
                CrateEntry.of(BrandModule.INSTANCE),
                CrateEntry.of(TrailModule.INSTANCE),
                CrateEntry.of(ElementStyles.INSTANCE));
    }
}
