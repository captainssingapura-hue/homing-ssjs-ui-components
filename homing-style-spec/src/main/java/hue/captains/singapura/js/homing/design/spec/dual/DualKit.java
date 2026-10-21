package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The kit every tree's JS dual and every component's header is made with (RFC 0071): one class for each kind of node -
 * a branch or a leaf of each tree, an extent, a path, a style - one for a slot and one for its count, and the one every
 * component's fixed type extends (D30). Each instance is made by its
 * constructor, which checks what it is given - a token a token, a parent a branch of its own tree, a slot's owner a
 * composite - and frozen. Nothing in a dual or a header is an object made by hand.
 */
public record DualKit() implements StatelessFunctionalObject {

    public static final DualKit INSTANCE = new DualKit();

    /** Its address: its class's canonical name, as every module's is. */
    public String address() { return "/module?class=" + getClass().getCanonicalName(); }

    /** The classes it gives, in its order. */
    public List<String> classes() {
        return List.of("ComponentBranch", "Component", "RoleBranch", "Role", "StateBranch", "StateType", "EventBranch", "EventType",
                "Extent", "Count", "Room", "ElementPath", "Style", "FixedField", "Fixed",
                "Point", "Span", "Ranges", "Valued", "Between", "Rises", "Falls", "NamedChange", "StateClasses");
    }

    /** Its module. */
    public String script() {
        return """
                // The kit every tree's JS dual and every header is made with (RFC 0071): one class for each kind of node, a
                // slot and its count - each instance checked as it is made, and frozen. Generated from Java; not to be edited.
                const TOKEN = /^[a-z][a-z0-9-]*$/;
                function token(kind, t) {
                    if (typeof t !== "string" || !TOKEN.test(t)) throw new TypeError(kind + ": a token is lower case words joined by a dash, not " + t);
                    return t;
                }
                function parentOf(kind, p, Branch) {
                    if (p !== null && !(p instanceof Branch)) throw new TypeError(kind + ": its parent is a " + Branch.name + ", or none for a root");
                    return p;
                }
                function flag(kind, what, b) {
                    if (typeof b !== "boolean") throw new TypeError(kind + ": " + what + " is true or false, not " + b);
                    return b;
                }

                class ComponentBranch {
                    constructor(t, parent) { this.token = token("ComponentBranch", t); this.parent = parentOf("ComponentBranch", parent, ComponentBranch); Object.freeze(this); }
                }
                class Component {
                    constructor(t, parent, composite) {
                        this.token = token("Component", t);
                        if (!(parent instanceof ComponentBranch)) throw new TypeError("Component " + t + ": its parent is a ComponentBranch");
                        this.parent = parent;
                        this.composite = flag("Component " + t, "composite", composite);
                        Object.freeze(this);
                    }
                }
                class RoleBranch {
                    constructor(t, parent, functional) {
                        this.token = token("RoleBranch", t); this.parent = parentOf("RoleBranch", parent, RoleBranch);
                        this.functional = flag("RoleBranch " + t, "functional", functional); Object.freeze(this);
                    }
                }
                class Role {
                    constructor(t, parent, functional) {
                        this.token = token("Role", t);
                        if (!(parent instanceof RoleBranch)) throw new TypeError("Role " + t + ": its parent is a RoleBranch");
                        this.parent = parent;
                        this.functional = flag("Role " + t, "functional", functional);
                        Object.freeze(this);
                    }
                }
                class StateBranch {
                    constructor(t, parent) { this.token = token("StateBranch", t); this.parent = parentOf("StateBranch", parent, StateBranch); Object.freeze(this); }
                }
                // What a type of state may be (RFC 0069, amended 21 Oct), each checked as it is made and frozen: a finite set of
                // ranges - a named value, or the numbers between two bounds - or a value object, a text; its named changes; and,
                // for a type whose look is a class, what its classes are said by.
                const NAMED = /^[a-z][a-z0-9]*(-[a-z0-9]+)*$/;
                class Point {
                    constructor(value) {
                        if (typeof value !== "string" || !NAMED.test(value)) throw new TypeError("Point: a named value, lower case words joined by a dash, not " + value);
                        this.value = value;
                        Object.freeze(this);
                    }
                    holds(v) { return v === this.value; }
                }
                class Span {
                    constructor(least, most, whole) {
                        if (!Number.isFinite(least) || !Number.isFinite(most) || !(least < most)) throw new TypeError("Span: finite bounds, its least below its most, not " + least + " to " + most);
                        this.whole = flag("Span", "whole numbers only", whole);
                        if (whole && !(Number.isInteger(least) && Number.isInteger(most))) throw new TypeError("Span: whole numbers have whole bounds, not " + least + " to " + most);
                        this.least = least;
                        this.most = most;
                        Object.freeze(this);
                    }
                    holds(v) { return typeof v === "number" && v >= this.least && v <= this.most && (!this.whole || Number.isInteger(v)); }
                }
                class Ranges {
                    constructor(ranges, rest) {
                        if (!Array.isArray(ranges) || !ranges.every(r => r instanceof Point || r instanceof Span)) throw new TypeError("Ranges: its ranges, each a Point or a Span");
                        const named = ranges.filter(r => r instanceof Point).map(r => r.value), spans = ranges.filter(r => r instanceof Span);
                        if (new Set(named).size !== named.length) throw new TypeError("Ranges: a named value twice");
                        for (let i = 0; i < spans.length; i++) for (let j = 0; j < i; j++)
                            if (spans[i].least <= spans[j].most && spans[j].least <= spans[i].most) throw new TypeError("Ranges: two spans that meet");
                        if (named.length < 2 && spans.length === 0) throw new TypeError("Ranges: two values at least");
                        this.ranges = Object.freeze(ranges.slice());
                        if (!this.allows(rest)) throw new TypeError("Ranges: its rest is one of its values, not " + rest);
                        this.rest = rest;
                        this.named = Object.freeze(named);
                        this.continuous = spans.length > 0;
                        Object.freeze(this);
                    }
                    allows(v) { return this.ranges.some(r => r.holds(v)); }
                }
                class Valued {
                    constructor(kind) {
                        if (kind !== "text") throw new TypeError("Valued: a text, not " + kind);
                        this.kind = kind;
                        this.rest = "";
                        Object.freeze(this);
                    }
                    allows(v) { return typeof v === "string"; }
                }
                class Between {
                    constructor(from, to) {
                        if (typeof from !== "string" || typeof to !== "string" || from === to) throw new TypeError("Between: two different named values, not " + from + " and " + to);
                        this.from = from;
                        this.to = to;
                        Object.freeze(this);
                    }
                    fits(before, after) { return before === this.from && after === this.to; }
                }
                class Rises {
                    constructor() { Object.freeze(this); }
                    fits(before, after) { return typeof before === "number" && typeof after === "number" && after > before; }
                }
                class Falls {
                    constructor() { Object.freeze(this); }
                    fits(before, after) { return typeof before === "number" && typeof after === "number" && after < before; }
                }
                class NamedChange {
                    constructor(name, transition) {
                        if (typeof name !== "string" || !NAMED.test(name)) throw new TypeError("NamedChange: lower case words joined by a dash, not " + name);
                        if (!(transition instanceof Between || transition instanceof Rises || transition instanceof Falls)) throw new TypeError("NamedChange " + name + ": a Between, a Rises or a Falls");
                        this.name = name;
                        this.transition = transition;
                        Object.freeze(this);
                    }
                }
                class StateClasses {
                    constructor(words, flagged, browser, level) {
                        this.words = Object.freeze(Object.assign({}, words));
                        this.flag = flag("StateClasses", "a yes or a no", flagged);
                        if (browser !== null && typeof browser !== "string") throw new TypeError("StateClasses: the browser's pseudo-class, or none");
                        this.browser = browser;
                        this.level = level;
                        Object.freeze(this);
                    }
                }
                class StateType {
                    constructor(t, parent, domain, changes, classes) {
                        this.token = token("StateType", t);
                        if (!(parent instanceof StateBranch)) throw new TypeError("StateType " + t + ": its parent is a StateBranch");
                        this.parent = parent;
                        if (!(domain instanceof Ranges || domain instanceof Valued)) throw new TypeError("StateType " + t + ": its domain, Ranges or Valued");
                        this.domain = domain;
                        if (!Array.isArray(changes) || !changes.every(c => c instanceof NamedChange) || new Set(changes.map(c => c.name)).size !== changes.length)
                            throw new TypeError("StateType " + t + ": its named changes, each a NamedChange, each name once");
                        for (const c of changes) {
                            const x = c.transition;
                            if (x instanceof Between ? !(domain.allows(x.from) && domain.allows(x.to)) : !(domain instanceof Ranges && domain.continuous))
                                throw new TypeError("StateType " + t + ": its change " + c.name + " does not fit its domain");
                        }
                        this.changes = Object.freeze(changes.slice());
                        if (classes !== null && !(classes instanceof StateClasses)) throw new TypeError("StateType " + t + ": its classes, a StateClasses, or none");
                        this.classed = classes !== null;
                        if (this.classed && !(domain instanceof Ranges && !domain.continuous)) throw new TypeError("StateType " + t + ": classed, and its values not all named");
                        // what the page needs of it: its named values, the one at rest first, where its look is a class
                        this.states = this.classed ? domain.named : Object.freeze([]);
                        this.rest = domain.rest;
                        if (this.classed && this.states[0] !== this.rest) throw new TypeError("StateType " + t + ": a classed type rests at its first value");
                        const words = this.classed ? classes.words : {};
                        for (const s of Object.keys(words)) if (s === this.rest || this.states.indexOf(s) < 0)
                            throw new TypeError("StateType " + t + ": a word for " + s + ", which is no state of it but the one at rest");
                        this.words = this.classed ? classes.words : Object.freeze({});
                        this.flag = this.classed && classes.flag;
                        this.browser = this.classed ? classes.browser : null;
                        this.level = this.classed ? classes.level : null;
                        Object.freeze(this);
                    }
                    /** Whether a value is one it allows. */
                    allows(value) { return this.domain.allows(value); }
                    /** Its named change, by its name; none for a name it has not. */
                    change(name) { return this.changes.find(c => c.name === name) ?? null; }
                }
                class EventBranch {
                    constructor(t, parent) { this.token = token("EventBranch", t); this.parent = parentOf("EventBranch", parent, EventBranch); Object.freeze(this); }
                }
                class EventType {
                    constructor(t, parent) {
                        this.token = token("EventType", t);
                        if (!(parent instanceof EventBranch)) throw new TypeError("EventType " + t + ": its parent is an EventBranch");
                        this.parent = parent;
                        Object.freeze(this);
                    }
                }
                class Extent {
                    constructor(t, rest) {
                        this.token = token("Extent", t);
                        if (!Number.isInteger(rest)) throw new TypeError("Extent " + t + ": its value at rest is a whole number");
                        this.rest = rest;
                        Object.freeze(this);
                    }
                }
                class Count {
                    constructor(min, max) {
                        if (!Number.isInteger(min) || min < 0) throw new TypeError("Count: its least is a whole number, none below 0");
                        if (max !== null && (!Number.isInteger(max) || max < Math.max(1, min))) throw new TypeError("Count: its most is at least its least and 1, or none for no bound");
                        this.min = min; this.max = max;
                        Object.freeze(this);
                    }
                }
                class Room {
                    constructor(owner, name) {
                        if (!(owner instanceof Component)) throw new TypeError("Room: its owner is a Component");
                        if (typeof name !== "string" || !/^[a-z][a-z0-9]*(-[a-z0-9]+)*$/.test(name)) throw new TypeError("Room: its name is lower-case words joined by hyphens");
                        this.owner = owner; this.name = name;
                        Object.freeze(this);
                    }
                }
                class ElementPath {
                    constructor(parent, part, role) {
                        if (parent !== null && !(parent instanceof ElementPath)) throw new TypeError("ElementPath: its parent is an ElementPath, or none for a top-level component");
                        if (!(part instanceof Component)) throw new TypeError("ElementPath: its part is a Component");
                        if (parent === null ? role !== null : !(role instanceof Role))
                            throw new TypeError("ElementPath " + part.token + ": a top-level component is in no role; a part is in its owner's");
                        if (parent !== null && !parent.part.composite) throw new TypeError("ElementPath " + part.token + ": its owner, " + parent.part.token + ", is a leaf");
                        this.parent = parent; this.part = part; this.role = role;
                        Object.freeze(this);
                    }
                }
                class Style {
                    constructor(t) { this.token = token("Style", t); Object.freeze(this); }
                }
                // A field of a component's fixed type (RFC 0071 port, question 5): a static part's choice - its role's name,
                // its count, and the fixed types of the components that may play it, looked up as an object is made.
                class FixedField {
                    constructor(name, count, players) {
                        if (typeof name !== "string" || !/^[a-z][A-Za-z0-9]*$/.test(name)) throw new TypeError("FixedField: its name is its role's, as a field's");
                        if (!(count instanceof Count)) throw new TypeError("FixedField: " + name + "'s count is a Count");
                        if (typeof players !== "function") throw new TypeError("FixedField: " + name + "'s players, a function of the fixed types that may play it");
                        this.name = name; this.count = count; this.players = players;
                        Object.freeze(this);
                    }
                }
                // What every component's fixed type extends (D30): its dual's FixedPlainButton names the component and hands on
                // what it is given. Its fields are its type's FIELDS, derived from the component's static parts (RFC 0071
                // port, question 5): each a part's choice, in its owner's order - one fixed object of a player's, or none
                // where the part is optional; as many as its count, as a list. A component with nothing fixed has a type with
                // no fields, refusing any argument.
                class Fixed {
                    constructor(component, given) {
                        if (new.target === Fixed) throw new TypeError("Fixed: made only as a component's own fixed type");
                        if (!(component instanceof Component)) throw new TypeError(new.target.name + ": the fixed type of a Component");
                        if (new.target.COMPONENT !== component) throw new TypeError(new.target.name + ": says it is the fixed type of " + (new.target.COMPONENT && new.target.COMPONENT.token) + ", and is made as " + component.token + "'s");
                        const fields = new.target.FIELDS ?? [];
                        if (!Array.isArray(fields) || !fields.every(f => f instanceof FixedField)) throw new TypeError(new.target.name + ": its FIELDS, each a FixedField");
                        if (fields.length === 0 && given.length !== 0) throw new TypeError(new.target.name + ": " + component.token + " has nothing fixed - its fixed object has no fields");
                        if (given.length !== fields.length)
                            throw new TypeError(new.target.name + ": its fields are " + fields.map(f => f.name).join(", ") + " - " + fields.length + ", given " + given.length);
                        fields.forEach((f, i) => {
                            const players = f.players(), value = given[i];
                            const played = v => players.some(P => v instanceof P);
                            const said = new.target.name + "'s " + f.name + ": " + players.map(P => P.name).join(" or ");
                            const least = f.count.min, most = f.count.max;
                            if (most === 1) {
                                if (value === null && least === 0) { this[f.name] = null; return; }
                                if (!played(value)) throw new TypeError(said + (least === 0 ? ", or null for none" : ""));
                                this[f.name] = value;
                            } else {
                                if (!Array.isArray(value) || value.length < least || (most !== null && value.length > most) || !value.every(played))
                                    throw new TypeError(said + " - a list of " + least + ".." + (most === null ? "*" : most));
                                this[f.name] = Object.freeze([...value]);
                            }
                        });
                        Object.freeze(this);
                    }
                }
                export { ComponentBranch, Component, RoleBranch, Role, StateBranch, StateType, EventBranch, EventType, Extent, Count, Room, ElementPath, Style, FixedField, Fixed,
                    Point, Span, Ranges, Valued, Between, Rises, Falls, NamedChange, StateClasses };
                """;
    }
}
