package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.design.semantic.ColorImports;
import hue.captains.singapura.js.homing.design.semantic.ColorImportsExtension;
import hue.captains.singapura.js.homing.design.semantic.StyleImports;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Accent;
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
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Raised;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ring;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFirm;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeMedium;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Sheen;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Text;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The colours each design this module gives the house's styles imports, for its own files - {@code <family>.control-panel.css}
 * (RFC 0067, appendix Two Classes per Leaf, D32): every colour of the tree each component's rules use there, style by
 * style, and no other.
 */
public record ControlPanelColorImports() implements ColorImportsExtension {

    @Override public String module() { return "control-panel"; }

    @Override public List<StyleImports<?>> imports() { return IMPORTS; }

    private static final List<StyleImports<?>> IMPORTS = List.of(
            new StyleImports<>(Base.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring())),
            new StyleImports<>(Editorial.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeFaint(), new ShadeLight(), new ShadeMedium())),
            new StyleImports<>(NeoBrutalism.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Inverted(), new Text(), new Border(), new Ring())),
            new StyleImports<>(NeoFuturism.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeSoft(), new GlowFaint(), new GlowDim(), new GlowSoft(), new GlowBright())),
            new StyleImports<>(Neumorphism.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeFirm())),
            new StyleImports<>(Glassmorphism.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new ShadeSoft(), new ShadeFirm(), new Sheen())),
            new StyleImports<>(RetroFuturism.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring(), new GlowDim(), new GlowMedium(), new GlowStrong(), new GlowFull())),
            new StyleImports<>(Sketchy.class,
                    // cluster
                    new ColorImports<>(HouseContainers.SliderGroup.INSTANCE, new Raised(), new Border(), new Accent(), new KeysCandidate(), new KeysLent()),
                    // button
                    new ColorImports<>(HouseControls.PlainButton.INSTANCE, new Raised(), new Text(), new Border(), new Ring())));
}
