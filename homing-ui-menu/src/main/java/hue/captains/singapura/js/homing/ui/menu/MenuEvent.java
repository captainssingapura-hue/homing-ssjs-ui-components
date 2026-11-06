package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.base.lang.StatelessSingleton;
import hue.captains.singapura.js.homing.component.EventFields;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.Set;

/**
 * The closed vocabulary of context menus — the sealed sum the JS
 * {@code MenuEvents} factories mirror, record for record, component for
 * component. A menu of a kind opened at a point, an item of it picked, the
 * menu closed for a reason; the object the menu was bound to is not here —
 * it is the holder's, told through the kind's handler. {@code MenuEventsTest}
 * holds the two sides together.
 */
public sealed interface MenuEvent {

    /** Why a menu closed: a pick, the Escape key, a press outside, a scroll, the window losing focus, another menu replacing it, or its owner. */
    record Reasons() implements StatelessFunctionalObject, StatelessSingleton {
        public static final Reasons INSTANCE = new Reasons();

        /** Every reason a menu closes for. */
        public Set<String> all() { return Set.of("pick", "escape", "outside", "scroll", "blur", "taken", "replaced", "owner"); }
    }

    /** The kind: the record's simple name, the JS object's {@code kind}. */
    default String kind() { return getClass().getSimpleName(); }

    /** A menu of this kind opened, its top-left at this point of the viewport. */
    record Opened(String menuKind, double x, double y) implements MenuEvent {
        public Opened {
            EventFields.INSTANCE.id(menuKind, "Opened.menuKind");
            if (!Double.isFinite(x) || !Double.isFinite(y)) throw new IllegalArgumentException("Opened.x/y: must be finite");
        }
    }

    /** An item of the open menu was picked. */
    record Picked(String menuKind, String itemId) implements MenuEvent {
        public Picked {
            EventFields.INSTANCE.id(menuKind, "Picked.menuKind");
            EventFields.INSTANCE.id(itemId, "Picked.itemId");
        }
    }

    /** The open menu closed, for one of the {@link Reasons}. */
    record Closed(String menuKind, String reason) implements MenuEvent {
        public Closed {
            EventFields.INSTANCE.id(menuKind, "Closed.menuKind");
            Set<String> reasons = Reasons.INSTANCE.all();
            if (!reasons.contains(reason)) throw new IllegalArgumentException("Closed.reason: not one of " + reasons + ": " + reason);
        }
    }
}
