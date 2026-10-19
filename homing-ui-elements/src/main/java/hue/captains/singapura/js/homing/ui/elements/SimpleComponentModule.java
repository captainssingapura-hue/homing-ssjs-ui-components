package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.core.js.DomOpsPartyModule;
import hue.captains.singapura.js.homing.server.CssClassManager;

import java.util.List;

/**
 * The standard of a simple component, on the style manager (RFC 0071): one element, made by the component with the
 * maker its owner's branch issues, and placed by it with the placement its owner hands it - or its own binding, at the
 * root - its reference kept to itself. It is three - its state, kept here and changed only by its setters; its style, the
 * reference's, its degrees the style-agnostic manager's numbers; its behaviour, its leaf's - and a state is a value on an
 * axis, a sum type of mutually exclusive values, set by one setter that stores it, writes its reflection, sets its
 * state through the reference and tells the behaviour. Its leaves' modules are each stamped with their header; this one
 * names no node of its own.
 */
public record SimpleComponentModule() implements DomModule<SimpleComponentModule> {

    /** The base of a simple component's leaves: {@code super(make, fixed, FixedLeaf, place ?? manageLeaf)}; {@code el}, {@code fixed}. */
    public record SimpleComponent() implements Exportable._Class<SimpleComponentModule> {}

    /**
     * The companion the old leaves were made on. Retired with the port (RFC 0071): a component makes its own element with
     * its owner's maker. Kept only so the leaves not yet ported compile; no longer exported.
     */
    public record SimpleComponentFactory() implements Exportable._Class<SimpleComponentModule> {}

    /** One of a component's events, dressed from the DOM's: {@code type}, {@code source}, {@code raw}. */
    public record ComponentEvent() implements Exportable._Class<SimpleComponentModule> {}

    /** A state's change: a {@code ComponentEvent} of its state type, with the state's {@code previous} value. */
    public record StateChange() implements Exportable._Class<SimpleComponentModule> {}

    public static final SimpleComponentModule INSTANCE = new SimpleComponentModule();

    @Override
    public ImportsFor<SimpleComponentModule> imports() {
        return ImportsFor.<SimpleComponentModule>builder()
                .add(new ModuleImports<>(List.of(new DomOpsPartyModule.DomOpsParty()), DomOpsPartyModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CssClassManager.CssClassManagerInstance()), CssClassManager.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SimpleComponentModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SimpleComponent(), new ComponentEvent(), new StateChange())); }
}
