package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** retro-futurism's look of slider: CSS of its own. */
public record Slider() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.Slider, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.Slider, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseControls.Slider> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseText.Label.INSTANCE, HouseSaying.Name.INSTANCE, new LabelName()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseMarks.SliderGroove.INSTANCE, HouseSaying.Span.INSTANCE, new SliderGrooveSpan()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseMarks.Fill.INSTANCE, HouseSaying.Level.INSTANCE, new FillLevel()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseMarks.Detent.INSTANCE, HouseSaying.Rest.INSTANCE, new DetentRest()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseMarks.Tick.INSTANCE, HouseSaying.Notch.INSTANCE, new TickNotch()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Figure.INSTANCE, new CaptionFigure()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseControls.SliderKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new SliderKnobThumb()),
                new PartSpec.Cast<>(HouseControls.Slider.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new ReadoutValue())));
    }
}
