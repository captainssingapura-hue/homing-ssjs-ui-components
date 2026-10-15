package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.design.semantic.ColorImports;
import hue.captains.singapura.js.homing.design.semantic.ColorImportsExtension;
import hue.captains.singapura.js.homing.design.semantic.StyleImports;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Border;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Faint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KickerInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnInverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ring;
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
 * The colours each design this module gives the house's styles imports, for its own files - {@code <family>.site-mpa-standard.css}
 * (RFC 0067, appendix Two Classes per Leaf, D32): every colour of the tree each component's rules use there, style by
 * style, and no other.
 */
public record SiteColorImports() implements ColorImportsExtension {

    @Override public String module() { return "site-mpa-standard"; }

    @Override public List<StyleImports<?>> imports() { return IMPORTS; }

    private static final List<StyleImports<?>> IMPORTS = List.of(
            new StyleImports<>(Base.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(Editorial.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(NeoBrutalism.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(NeoFuturism.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(Neumorphism.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(Glassmorphism.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(RetroFuturism.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())),
            new StyleImports<>(Sketchy.class,
                    // button
                    new ColorImports<>(HouseControls.PreferencesButton.INSTANCE, new Faint(), new Ring()),
                    // item
                    new ColorImports<>(HouseItems.ChoiceOption.INSTANCE, new OnInverted(), new Border(), new Title(), new Ring(), new AccentWashSoft()),
                    // text
                    new ColorImports<>(HouseText.Kicker.INSTANCE, new KickerInk())));
}
