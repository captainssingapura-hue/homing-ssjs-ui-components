package hue.captains.singapura.js.homing.ui.controllability;

import java.util.List;

/** What is refused of a control catalogue, a control type or a classification: every problem, each named once. */
public final class RefusedControls extends IllegalStateException {

    private final List<String> problems;

    public RefusedControls(List<String> problems) {
        super("refused:\n  " + String.join("\n  ", problems));
        this.problems = List.copyOf(problems);
    }

    /** The problems, in the order found. */
    public List<String> problems() { return problems; }
}
