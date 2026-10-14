package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.design.semantic.WritingExtension;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * The panes' words ({@link PaneWords}) written in Base, which every house style is written over: so every
 * style draws them, each as a symbol of the text's own font, in the colour the mark is given, until a style
 * writes one otherwise.
 */
public record PaneWritings() implements WritingExtension {

    @Override public Class<? extends DesignStyle> writes() { return Base.class; }

    @Override public List<Writing> writings() {
        return List.of(
                new Writing(PaneWords.Column.INSTANCE, "▥"), new Writing(PaneWords.Row.INSTANCE, "▤"),
                new Writing(PaneWords.Merge.INSTANCE, "▣"), new Writing(PaneWords.Detach.INSTANCE, "⧉"),
                new Writing(PaneWords.Within.INSTANCE, "◉"));
    }
}
