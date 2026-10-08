package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * {@code Controls}: the house's controllability, asked - a leaf's control type, the options it is
 * controlled by, and an option applied to what is controlled by the method the option names. Pure:
 * no DOM, no state; what it knows is the generated catalogue.
 */
public record ControlsModule() implements EsModule<ControlsModule> {

    public static final ControlsModule INSTANCE = new ControlsModule();

    public record Controls() implements Exportable._Class<ControlsModule> {}

    @Override
    public ImportsFor<ControlsModule> imports() {
        return ImportsFor.<ControlsModule>builder()
                .add(new ModuleImports<>(List.of(new ControlCatalogueModule.CONTROL_OPTIONS(), new ControlCatalogueModule.CONTROL_TYPES(),
                        new ControlCatalogueModule.CONTROL_TYPE_OF(), new ControlCatalogueModule.CONTROL_OPTIONS_OF()), ControlCatalogueModule.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ControlsModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new Controls())); }
}
