package hue.captains.singapura.js.homing.ui.controllability;

import java.util.List;

/**
 * How a module's own leaves are controlled, as it adds them to the house's controllability: the
 * leaves it adds to the house's taxonomy, each classified by type beside them. In the
 * classification if and only if the module is on the classpath, as its leaves are in the tree.
 *
 * <p>Found on the classpath by {@link java.util.ServiceLoader}, as the module's leaves are: it
 * names its extension in {@code META-INF/services/hue.captains.singapura.js.homing.ui.controllability.ControllabilityExtension},
 * a public record with no components. A leaf it leaves out is {@link HouseControllability#PLAIN},
 * unless a branch above it is classified.</p>
 */
public interface ControllabilityExtension {

    /** Its leaves classified: each with its type. */
    List<ControlTypeAt> classification();
}
