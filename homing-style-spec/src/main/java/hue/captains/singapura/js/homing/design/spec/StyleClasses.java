package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * The classes the style manager puts on an element (RFC 0071, RFC 0067 D54), each a pure function of a typed object and
 * the same in every style: an element's, of its casting - {@code c-plain-button}, {@code c-label_title} - its owner and its
 * slot being the layout's, never the style's; and a state's, of its design word - {@code s-disabled}, the words being
 * unique across the house's state types. Nothing ships them and nothing looks them up: the style manager makes them from
 * the casting and the state type it holds, and the sheets select by them; what a style draws is the sheet's alone.
 */
public record StyleClasses() implements StatelessFunctionalObject {

    public static final StyleClasses INSTANCE = new StyleClasses();

    /** A casting's class: {@code c-}, then the casting's token - the component's, then the role's where it has a look of its own. */
    public String casting(Casting casting) { return "c-" + casting.token(); }

    /** An element's class: its casting's. */
    public String element(ElementPath path) { return casting(path.casting()); }

    /** A state's class, of its design word: {@code s-disabled}. None for a state at rest, which wears no class. */
    public String state(String word) { return "s-" + word; }
}
