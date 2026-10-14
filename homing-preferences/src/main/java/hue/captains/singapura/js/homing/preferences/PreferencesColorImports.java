package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.design.semantic.ColorImports;
import hue.captains.singapura.js.homing.design.semantic.ColorImportsExtension;
import hue.captains.singapura.js.homing.design.semantic.StyleImports;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Accent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Border;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowBright;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowDim;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowFull;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowMedium;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowStrong;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Inverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysCandidate;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysLent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KickerInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnInverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Raised;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ring;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFirm;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeMedium;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Sheen;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Text;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.TextFaded;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Title;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The colours each design this module gives the house's styles imports, for its own files - {@code <family>.preferences.css}
 * (RFC 0067, appendix Two Classes per Leaf, D32): every colour of the tree each component's rules use there, style by
 * style, and no other.
 */
public record PreferencesColorImports() implements ColorImportsExtension {

    @Override public String module() { return "preferences"; }

    @Override public List<StyleImports<?>> imports() { return IMPORTS; }

    private static final List<StyleImports<?>> IMPORTS = List.of(
            new StyleImports<>(Base.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(Editorial.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(NeoBrutalism.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Inverted(), new Text(), new Border(), new Ring()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(NeoFuturism.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeSoft(), new GlowFaint(), new GlowDim(), new GlowSoft(), new GlowBright()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(Neumorphism.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeFirm()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(Glassmorphism.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeSoft(), new ShadeFirm(), new Sheen()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(RetroFuturism.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new GlowDim(), new GlowMedium(), new GlowStrong(), new GlowFull()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())),
            new StyleImports<>(Sketchy.class,
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring()),
                    // input
                    new ColorImports<>(HouseControls.Range.INSTANCE, new Accent(), new Ring()),
                    new ColorImports<>(HouseControls.Switch.INSTANCE, new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ListRow.INSTANCE, new OnInverted(), new Border(), new Title(), new Accent(), new AccentWashLight(), new AccentWashSoft(), new KeysCandidate(), new KeysLent()),
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk()),
                    new ColorImports<>(HouseText.Caption.INSTANCE, new TextFaded()),
                    new ColorImports<>(HouseText.Lede.INSTANCE, new TextFaded())));
}
