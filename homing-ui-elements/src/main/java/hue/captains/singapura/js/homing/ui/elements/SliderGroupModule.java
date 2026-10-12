package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
import hue.captains.singapura.js.homing.component.keyboard.Modifier;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.ArrayList;
import java.util.List;

/**
 * The slider group: sliders that share the keys, one
 * member of the keyboard party for all of them and one of them current.
 * The sliders are built inside it and have no keys of their own; the group
 * holds the keys and hands them to the current slider, and Tab walks them.
 */
public record SliderGroupModule() implements DomModule<SliderGroupModule>, ImplFor<HouseContainers.SliderGroup> {

    /** A branch component, on a sub-branch the caller made, with its properties: a header with the title, then the sliders; add, sliders, current, key, dispose. */
    public record SliderGroup() implements BranchComponent<SliderGroupModule>, NeedKeyboard {
        @Override public String summary() { return "Sliders that share the keys: one holder for all of them, one of them current; Tab walks them, the rest is the current one's."; }

        /** Tab either way, Escape, and the current slider's keys. */
        public static final List<KeyBinding> KEYS = keys0();
        private static List<KeyBinding> keys0() {
            var out = new ArrayList<KeyBinding>();
            out.add(KeyBinding.of(Key.TAB, "the next slider is current, wrapping; the focus to its knob"));
            out.add(KeyBinding.of(Key.TAB, Modifier.SHIFT, "the previous slider is current, wrapping; the focus to its knob"));
            out.add(KeyBinding.of(Key.ESCAPE, "the keys given back; the key travels on"));
            for (KeyBinding b : SliderModule.Slider.KEYS) out.add(new KeyBinding(b.key(), b.modifiers(), "to the current slider: " + b.meaning()));
            return List.copyOf(out);
        }
        @Override public List<KeyBinding> keys() { return KEYS; }
    }

    public static final SliderGroupModule INSTANCE = new SliderGroupModule();

    @Override
    public ImportsFor<SliderGroupModule> imports() {
        return ImportsFor.<SliderGroupModule>builder()
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Heading4Module.Heading4(), new Heading4Module.Heading4Factory()), Heading4Module.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_slider_group(),
                        new ElementStyles.el_slider_group_header(),
                        new ElementStyles.el_slider_group_title(),
                        new ElementStyles.el_slider_group_body(),
                        new ElementStyles.el_slider_group_body_across()
                ), ElementStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SliderModule.Slider()), SliderModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SliderGroupModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SliderGroup()));
    }
}
