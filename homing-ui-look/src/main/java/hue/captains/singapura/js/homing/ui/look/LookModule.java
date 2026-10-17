package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.StyledLeaves;

import java.util.List;

/**
 * How a component wears its casting's style-aware classes (RFC 0067, appendix Two Classes per Leaf), through core's
 * {@code StyledLeaf}: its component and role, locked in its constructor, naming the one class it wears, and a
 * class for each application state its leaf lists while the state holds (D12) - every name generated from Java
 * ({@link LookManifestModule}), every rule the style worn's ({@link HouseStyleSheet}) in the palette worn's colours
 * ({@link HousePaletteSheet}). A picture is an icon's, a component of its own (D52). Nothing is resolved on the page.
 */
public record LookModule() implements DomModule<LookModule> {

    /** {@code new Look(Leaf, role)}; {@code wear}, {@code show}, {@code keys}, {@code degree}, {@code text}. */
    public record Look() implements Exportable._Class<LookModule> {}

    public static final LookModule INSTANCE = new LookModule();

    @Override
    public ImportsFor<LookModule> imports() {
        return ImportsFor.<LookModule>builder()
                .add(new ModuleImports<>(List.of(new HouseStyleSheet.style_sheet()), HouseStyleSheet.INSTANCE))
                .add(new ModuleImports<>(List.of(new HousePaletteSheet.palette_sheet()), HousePaletteSheet.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookManifestModule.LEAVES()), LookManifestModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StyledLeaves.StyledLeaf()), StyledLeaves.INSTANCE))
                .build();
    }

    @Override public ExportsOf<LookModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new Look())); }
}
