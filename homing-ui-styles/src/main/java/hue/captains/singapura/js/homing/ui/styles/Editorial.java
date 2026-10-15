package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.ColorImports;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Accent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentEdge;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentTint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentTintFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentTintHint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashStrong;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Border;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Danger;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerEdge;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerGround;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerPressed;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerWash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerWriting;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Faint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowGentle;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowLow;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowTrace;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GrooveEdge;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ground;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Hairline;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HeadingInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Inverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysCandidate;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysLent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KickerInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Muted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnAccent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnBar;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnDanger;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnInverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.PrimaryGround;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.PrimaryInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Quiet;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Raised;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Recessed;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ring;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ScrollThumb;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.SecondaryGround;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeDeep;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeDense;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFirm;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeMedium;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeModerate;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeSolid;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeStrong;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.SuccessGround;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.SuccessInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.SuccessWash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Text;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.TextFaded;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Title;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WarningGround;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WarningInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WarningWash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Wash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WashFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WashSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WayHovered;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;
import java.util.Set;

/** Editorial, the house's own: a serif voice, a fine rule, a soft lift under the hand. */
public record Editorial() implements Derives<ColorScheme.Base, Base> {

    public static final Editorial INSTANCE = new Editorial();

    /** The components it draws its own way, on their own and in every role they are cast in, each casting a whole file; every other casting is Base's. */
    private static final Set<Component<?>> DRAWN = HouseFamilies.DRAWN;

    @Override public String group() { return "Neutral"; }

    @Override public String inspiration() { return "The house design — serif headings, hairline rules, a whisper of shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its files (RFC 0067, appendix Two Classes per Leaf); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public StylingRes style(Casting casting) {
        return DRAWN.contains(casting.cast()) ? StylingRes.CssContent.INSTANCE : StylingRes.Absence.INSTANCE;
    }

    /**
     * The colours each of its designs imports, for its own files (RFC 0067, appendix Two Classes per Leaf, D32): every
     * colour of the tree each component's rules use - on its own and in every role, in every state - and no other.
     */
    @Override public List<ColorImports<ColorScheme.Base>> colorImports() { return IMPORTS; }

    private static final List<ColorImports<ColorScheme.Base>> IMPORTS = List.of(
            // button
            new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium()),
            new ColorImports<>(HouseControls.PrimaryButton.INSTANCE, new Title(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium(), new PrimaryGround(), new PrimaryInk()),
            new ColorImports<>(HouseControls.SecondaryButton.INSTANCE, new OnAccent(), new AccentEdge(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium(), new SecondaryGround()),
            new ColorImports<>(HouseControls.DangerButton.INSTANCE, new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium(), new DangerEdge(), new DangerGround(), new DangerWriting()),
            new ColorImports<>(HouseControls.WarningButton.INSTANCE, new WarningInk(), new Border(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium(), new WarningGround()),
            new ColorImports<>(HouseControls.SuccessButton.INSTANCE, new SuccessInk(), new Border(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium(), new SuccessGround()),
            new ColorImports<>(HouseControls.ToggleButton.INSTANCE, new Raised(), new Text(), new Border(), new AccentEdge(), new Ring(), new AccentTint(), new ShadeFaint(), new ShadeLight(), new ShadeMedium()),
            new ColorImports<>(HouseControls.IconButton.INSTANCE, new Wash(), new Border(), new Ring()),
            new ColorImports<>(HouseControls.CloseButton.INSTANCE, new OnDanger(), new Border(), new Ring(), new Danger(), new DangerPressed()),
            new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
            // handle
            new ColorImports<>(HouseControls.SliderKnob.INSTANCE, new Ring()),
            new ColorImports<>(HouseControls.ToggleKnob.INSTANCE, new Raised()),
            new ColorImports<>(HouseControls.Grip.INSTANCE, new Ring()),
            // way
            new ColorImports<>(HouseControls.Link.INSTANCE, new TextFaded(), new WayHovered(), new Ring()),
            new ColorImports<>(HouseControls.Crumb.INSTANCE, new Ring()),
            // input
            new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
            new ColorImports<>(HouseControls.TextBox.INSTANCE, new Recessed(), new Text(), new TextFaded(), new Border(), new Ring()),
            new ColorImports<>(HouseControls.Checkbox.INSTANCE, new Accent(), new Ring()),
            new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
            new ColorImports<>(HouseControls.Slider.INSTANCE, new Accent(), new KeysCandidate(), new KeysLent()),
            new ColorImports<>(HouseControls.Select.INSTANCE, new Raised(), new Text(), new Border(), new Ring()),
            // selector
            new ColorImports<>(HouseControls.PaneThumb.INSTANCE, new Raised(), new Border(), new Accent(), new AccentTintFaint()),
            new ColorImports<>(HouseControls.Tab.INSTANCE, new Text(), new OnInverted(), new Border(), new Title(), new AccentWashFaint(), new AccentWashSoft(), new ShadeSoft(), new ShadeStrong(), new ShadeDeep()),
            // way
            new ColorImports<>(HouseControls.Brand.INSTANCE, new Ring()),
            // item
            new ColorImports<>(HouseItems.MenuItem.INSTANCE, new Border(), new Accent(), new AccentWashSoft(), new AccentWashStrong()),
            new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
            new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
            // card
            new ColorImports<>(HouseContainers.PlainCard.INSTANCE, new Raised(), new Border(), new ShadeFaint()),
            new ColorImports<>(HouseContainers.ClickableCard.INSTANCE, new Raised(), new Border(), new Accent(), new Ring(), new KeysCandidate(), new KeysLent(), new ShadeFaint(), new ShadeLight(), new ShadeMedium()),
            // text
            new ColorImports<>(HouseText.Heading1.INSTANCE, new HeadingInk()),
            new ColorImports<>(HouseText.Heading2.INSTANCE, new HeadingInk()),
            new ColorImports<>(HouseText.Heading3.INSTANCE, new HeadingInk()),
            new ColorImports<>(HouseText.Heading4.INSTANCE, new HeadingInk()),
            new ColorImports<>(HouseText.Heading5.INSTANCE, new HeadingInk()),
            new ColorImports<>(HouseText.Heading6.INSTANCE, new HeadingInk()),
            new ColorImports<>(HouseText.Code.INSTANCE, new WashFaint()),
            new ColorImports<>(HouseText.SuccessBadge.INSTANCE, new SuccessInk(), new SuccessWash()),
            new ColorImports<>(HouseText.WarningBadge.INSTANCE, new WarningInk(), new WarningWash()),
            new ColorImports<>(HouseText.DangerBadge.INSTANCE, new DangerInk(), new DangerWash()),
            new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
            new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
            new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded()),
            new ColorImports<>(HouseText.Badge.INSTANCE, new AccentWash()),
            new ColorImports<>(HouseText.Pill.INSTANCE, new WashSoft()),
            // mark
            new ColorImports<>(HouseMarks.Detent.INSTANCE, new Muted()),
            new ColorImports<>(HouseMarks.Tick.INSTANCE, new Muted()),
            new ColorImports<>(HouseMarks.Separator.INSTANCE, new Hairline()),
            new ColorImports<>(HouseMarks.Lamp.INSTANCE, new Text(), new OnDanger(), new Quiet(), new Accent(), new AccentWashLight(), new Danger()),
            // track
            new ColorImports<>(HouseMarks.SliderGroove.INSTANCE, new WashSoft(), new GrooveEdge()),
            new ColorImports<>(HouseMarks.ToggleGroove.INSTANCE, new WashSoft(), new GrooveEdge(), new Accent()),
            new ColorImports<>(HouseMarks.Fill.INSTANCE, new Accent()),
            // region
            new ColorImports<>(HouseRegions.Quote.INSTANCE, new TextFaded(), new Border()),
            new ColorImports<>(HouseRegions.CodeBlock.INSTANCE, new Text(), new Recessed(), new Border()),
            // surface
            new ColorImports<>(HouseContainers.Panel.INSTANCE, new Raised(), new Text(), new Border(), new Accent(), new ShadeLight(), new ShadeSoft(), new ShadeModerate(), new ShadeFirm()),
            new ColorImports<>(HouseContainers.Ground.INSTANCE, new Ground(), new ScrollThumb(), new Text()),
            // bar
            new ColorImports<>(HouseContainers.EdgeStrip.INSTANCE, new Raised(), new Text(), new Border(), new ShadeSolid(), new ShadeDense()),
            new ColorImports<>(HouseContainers.ActionBar.INSTANCE, new Raised(), new Border()),
            new ColorImports<>(HouseContainers.TabStrip.INSTANCE, new Raised(), new Accent(), new AccentTintHint()),
            new ColorImports<>(HouseContainers.SiteBar.INSTANCE, new Inverted(), new OnBar(), new Accent()),
            new ColorImports<>(HouseContainers.TitleBar.INSTANCE, new Inverted(), new OnInverted(), new OnBar(), new Border(), new Title()),
            new ColorImports<>(HouseContainers.PanelBar.INSTANCE, new Accent()),
            // pane
            new ColorImports<>(HouseContainers.FloatingPane.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeSolid(), new ShadeDense(), new GlowTrace(), new GlowLow(), new GlowGentle(), new GlowSoft()),
            // layer
            new ColorImports<>(HouseContainers.FloatLayer.INSTANCE, new Recessed()),
            // menu
            new ColorImports<>(HouseContainers.ContextMenu.INSTANCE, new Raised(), new Text(), new Border(), new ShadeSolid(), new ShadeDense()),
            // cluster
            new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()));
}
