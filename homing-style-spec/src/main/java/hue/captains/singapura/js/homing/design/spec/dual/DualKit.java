package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The kit every tree's JS dual and every component's header is made with (RFC 0071): one class for each kind of node -
 * a branch or a leaf of each tree, an extent, a handle, a style - and one for a slot and one for its count. Each instance is made by its
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
                "Extent", "Count", "Slot", "Handle", "Style");
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
                class StateType {
                    constructor(t, parent, states, words, flagged, browser, level) {
                        this.token = token("StateType", t);
                        if (!(parent instanceof StateBranch)) throw new TypeError("StateType " + t + ": its parent is a StateBranch");
                        this.parent = parent;
                        if (!Array.isArray(states) || states.length < 2 || new Set(states).size !== states.length)
                            throw new TypeError("StateType " + t + ": two states at least, each once - the one at rest first");
                        this.states = Object.freeze(states.slice());
                        this.rest = states[0];
                        for (const s of Object.keys(words)) if (s === this.rest || states.indexOf(s) < 0)
                            throw new TypeError("StateType " + t + ": a word for " + s + ", which is no state of it but the one at rest");
                        this.words = Object.freeze(Object.assign({}, words));
                        this.flag = flag("StateType " + t, "a yes or a no", flagged);
                        if (browser !== null && typeof browser !== "string") throw new TypeError("StateType " + t + ": the browser's pseudo-class, or none");
                        this.browser = browser;
                        this.level = level;
                        Object.freeze(this);
                    }
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
                class Slot {
                    constructor(owner, part, role, count) {
                        if (!(owner instanceof Component) || !owner.composite) throw new TypeError("Slot: its owner is a composite Component");
                        if (!(part instanceof Component)) throw new TypeError("Slot: its part is a Component");
                        if (part === owner) throw new TypeError("Slot: " + owner.token + " is no part of itself");
                        if (!(role instanceof Role)) throw new TypeError("Slot: its role is a Role");
                        if (!(count instanceof Count)) throw new TypeError("Slot: how many it is, a Count");
                        this.owner = owner; this.part = part; this.role = role; this.count = count;
                        Object.freeze(this);
                    }
                }
                class Handle {
                    constructor(parent, part, role) {
                        if (parent !== null && !(parent instanceof Handle)) throw new TypeError("Handle: its parent is a Handle, or none for a top-level component");
                        if (!(part instanceof Component)) throw new TypeError("Handle: its part is a Component");
                        if (parent === null ? role !== null : !(role instanceof Role))
                            throw new TypeError("Handle " + part.token + ": a top-level component is in no role; a part is in its owner's");
                        if (parent !== null && !parent.part.composite) throw new TypeError("Handle " + part.token + ": its owner, " + parent.part.token + ", is a leaf");
                        this.parent = parent; this.part = part; this.role = role;
                        Object.freeze(this);
                    }
                }
                class Style {
                    constructor(t) { this.token = token("Style", t); Object.freeze(this); }
                }
                export { ComponentBranch, Component, RoleBranch, Role, StateBranch, StateType, EventBranch, EventType, Extent, Count, Slot, Handle, Style };
                """;
    }
}
