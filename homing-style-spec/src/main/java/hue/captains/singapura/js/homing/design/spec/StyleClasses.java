package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * The classes the style manager puts on an element (RFC 0071), each a pure function of a typed object and the same in
 * every style: an element's, of its handle - {@code h-dialog--action-bar_actions--plain-button_action} - and a state's,
 * of its design word - {@code s-disabled}, the words being unique across the house's state types. Nothing ships them and
 * nothing looks them up: the style manager makes them from the handle and the state type it holds, and the sheets select
 * by them; what a style draws is the sheet's alone.
 */
public record StyleClasses() implements StatelessFunctionalObject {

    public static final StyleClasses INSTANCE = new StyleClasses();

    /** An element's class, of its handle: {@code h-}, then the handle's steps, each joined to the next by {@code --}. */
    public String element(ElementPath handle) { return "h-" + handle.handle().replace("/", "--"); }

    /** A state's class, of its design word: {@code s-disabled}. None for a state at rest, which wears no class. */
    public String state(String word) { return "s-" + word; }
}
