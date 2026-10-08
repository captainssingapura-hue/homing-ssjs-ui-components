package hue.captains.singapura.js.homing.ui.controllability;

/**
 * A control option, a leaf of the control catalogue: one thing of a component that can be
 * controlled from outside it - its size, whether it is enabled, opening it, asking it what it
 * shows. WHAT is controlled is its place in the catalogue; HOW is the sum type it is one of, which
 * says what a control for it is and what it carries:
 *
 * <ul>
 *   <li>{@link WithExtent} - set by degree, a number from −1 to 1: a slider;</li>
 *   <li>{@link WithSwitch} - switched on or off: a toggle;</li>
 *   <li>{@link WithAction} - done, once, when asked: a button;</li>
 *   <li>{@link WithQuestion} - asked, and answered in words: a button whose answer is said.</li>
 * </ul>
 *
 * <p>And it names the {@link #method() method} on what is controlled that applies it - taking the
 * number, the truth, or nothing, as its means says - so whatever is controlled answers every option
 * it takes by that method, and a control panel needs nothing else to drive it.</p>
 *
 * <pre>{@code
 * public record ZoomIn() implements WithAction<Viewing> {       // Viewing: a category, at level 1
 *     public static final ZoomIn INSTANCE = new ZoomIn();
 *     @Override public Viewing parent() { return Viewing.INSTANCE; }
 * }
 * }</pre>
 *
 * @param <P> the branch it is filed under, at any level
 */
public sealed interface ControlOption<P extends ControlBranch> extends ControlNode permits WithExtent, WithSwitch, WithAction, WithQuestion {

    /** The branch it is filed under. */
    P parent();

    /** The method on what is controlled that applies it: its name in camel case - {@code zoom-in} is {@code zoomIn} - unless said. */
    default String method() {
        String[] words = name().value().split("-");
        var out = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) out.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
        return out.toString();
    }

    /** Its words on a control: its name, spaced, the first letter up - {@code zoom-in} is {@code Zoom in} - unless said. */
    default String label() {
        String spaced = name().value().replace('-', ' ');
        return Character.toUpperCase(spaced.charAt(0)) + spaced.substring(1);
    }

    /** How it is controlled, as a page has it: {@code extent}, {@code switch}, {@code action} or {@code question}. */
    default String means() {
        return switch (this) {
            case WithExtent<?> e   -> "extent";
            case WithSwitch<?> s   -> "switch";
            case WithAction<?> a   -> "action";
            case WithQuestion<?> q -> "question";
        };
    }
}
