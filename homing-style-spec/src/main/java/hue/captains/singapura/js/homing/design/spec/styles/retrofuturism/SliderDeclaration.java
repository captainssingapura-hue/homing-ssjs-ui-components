package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** retro-futurism's declaration of slider: its own implementation, and a spec for each of its slots. */
public record SliderDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HouseControls.Slider, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HouseControls.Slider, RoleRoot> own() { return new Slider(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseControls.Slider> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseText.Label.INSTANCE, HouseSaying.Name.INSTANCE, new Target.Impl<>(new LabelName())),
                new PartSpec.Standalone<>(HouseControls.Slider.INSTANCE, HouseMarks.SliderRail.INSTANCE, HouseShaping.Editor.INSTANCE),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseMarks.SliderGroove.INSTANCE, HouseSaying.Span.INSTANCE, new Target.Impl<>(new SliderGrooveSpan())),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseMarks.Fill.INSTANCE, HouseSaying.Level.INSTANCE, new Target.Impl<>(new FillLevel())),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseMarks.Detent.INSTANCE, HouseSaying.Rest.INSTANCE, new Target.Impl<>(new DetentRest())),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseMarks.Tick.INSTANCE, HouseSaying.Notch.INSTANCE, new Target.Impl<>(new TickNotch())),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Figure.INSTANCE, new Target.Impl<>(new CaptionFigure())),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseControls.SliderKnob.INSTANCE, HouseDoing.Thumb.INSTANCE, new Target.Impl<>(new SliderKnobThumb())),
                new PartSpec.Standalone<>(HouseControls.Slider.INSTANCE, HouseIcons.GripIcon.INSTANCE, HouseShaping.Measure.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.Slider.INSTANCE, HouseIcons.SizeIcon.INSTANCE, HouseShaping.Measure.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.Slider.INSTANCE, HouseIcons.AspectIcon.INSTANCE, HouseShaping.Measure.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.Slider.INSTANCE, HouseIcons.ExtentIcon.INSTANCE, HouseShaping.Measure.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.Slider.INSTANCE, HouseIcons.LevelIcon.INSTANCE, HouseShaping.Measure.INSTANCE),
                new PartSpec.SameRole<>(HouseControls.Slider.INSTANCE, HouseText.Readout.INSTANCE, HouseSaying.Value.INSTANCE, new Target.Impl<>(new ReadoutValue()))));
    }
}
