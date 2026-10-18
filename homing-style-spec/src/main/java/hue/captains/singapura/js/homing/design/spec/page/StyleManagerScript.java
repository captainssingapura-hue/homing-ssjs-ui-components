package hue.captains.singapura.js.homing.design.spec.page;

import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.ElementPaths;
import hue.captains.singapura.js.homing.design.spec.dual.States;
import hue.captains.singapura.js.homing.design.spec.dual.Styles;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The style manager's module (RFC 0071): the page's one keeper of every element's style, and of the style the page wears.
 * A component hands it an element and gets back a reference; that is all it ever does with it.
 *
 * <p>Each element handed over becomes a managed object, in charge of everything about the element's style for as long
 * as it lives: the class it is given, the states it is in - and, for a composite, its parts, being their sub-manager. The
 * component makes each part's element and hands it to its own managed object, along a slot it declares. So the managed
 * objects are a tree beside the tree of element paths, one for each live unit: the style manager keeps the units, each
 * managed object its parts - each in a map from an element path to the live instances at it, there being any number of
 * each: three dialogs open, a tab strip's every tab.
 *
 * <p>A component never holds a managed object, only a reference to it: its states to set and read, its parts to hand
 * over. Not the element, not its class, not the style.
 *
 * <p>Every class is derived from a typed node, the same in every style ({@code StyleClasses}): an element's from its
 * element path, a state's from its word. A switch is then the sheet swapped, and no element written.
 *
 * <p>It touches no document. All it writes goes through the surface it is given - three functions: add a class to an
 * element, remove one, show a style. In a browser the surface is an element's {@code classList} and the page's sheets;
 * anywhere else, whatever keeps them. It holds each element weakly. A managed object whose element is gone is taken out
 * by its clean-up worker, which sweeps the tree every ten seconds on the clock it is given - its parts with it; no
 * release call, and nothing drawn on a gone element meanwhile.
 *
 * <p>It accepts only its duals' own nodes, by identity: a top-level component the paths start at, a slot its owner
 * declares, a state type of the states' dual the page draws, a style of the styles' dual.
 */
public record StyleManagerScript() implements StatelessFunctionalObject {

    public static final StyleManagerScript INSTANCE = new StyleManagerScript();

    /** Its address: its class's canonical name, as every module's is. */
    public String address() { return "/module?class=" + getClass().getCanonicalName(); }

    /** The words of a document's API it never says: the check that it touches no document. */
    public List<String> unsaid() {
        return List.of("document", "window", "classList", "className", "setAttribute", "removeAttribute", "querySelector",
                "getElementById", "adoptedStyleSheets", "CSSStyleSheet", "insertRule", "cssText", "getComputedStyle", "HTMLElement");
    }

    /** What a component's reference gives it, and nothing more. */
    public List<String> reference() { return List.of("component", "part", "set", "state"); }

    /** Its module. */
    public String script() {
        var js = JsLiterals.INSTANCE;
        return """
                // The style manager (RFC 0071): the page's one keeper of every element's style, and of the style the page
                // wears. A component hands it an element and gets back a reference - its states to set, its parts to hand
                // over - and does nothing else with it. Each element handed over is a managed object, in charge of the
                // element's style for as long as it lives, and the sub-manager of its parts. Every class is derived from a
                // typed node, the same in every style: a switch is the sheet swapped, no element written. It touches no
                // document: all it writes goes through the surface it is given - add(element, class), remove(element, class),
                // show(style) - and its clean-up worker runs on the clock it is given - every(ms, task). Generated from Java;
                // not to be edited.
                """
                + "import { Component, ElementPath, Slot, StateType, Style } from " + js.string(DualKit.INSTANCE.address()) + ";\n"
                + "import * as PATHS from " + js.string(ElementPaths.INSTANCE.address()) + ";\n"
                + "import * as STATES from " + js.string(States.INSTANCE.address()) + ";\n"
                + "import * as STYLES from " + js.string(Styles.INSTANCE.address()) + ";\n"
                + """

                const MINT = Symbol("the style manager's");

                /** A path's class: its top-level component, then each slot's part and role - p-dialog--action-bar_actions. */
                function elementClass(path) {
                    const segments = [];
                    for (let at = path; at !== null; at = at.parent) segments.unshift(at.parent === null ? at.part.token : at.part.token + "_" + at.role.token);
                    return "p-" + segments.join("--");
                }

                /** A state's class: its word - s-disabled. The state at rest has none. */
                function stateClass(type, state) { return "s-" + type.words[state]; }

                /** Into a map from a path to its live instances, one more. */
                function enter(map, path, managed) {
                    let at = map.get(path);
                    if (at === undefined) map.set(path, at = new Set());
                    at.add(managed);
                }

                /** How often the clean-up worker sweeps: every ten seconds. */
                const SWEEP_EVERY = 10000;

                /** The managed of a map, counted: every one it holds, or only those whose element - and every owner's - is alive. */
                function count(map, alive) {
                    let n = 0;
                    for (const at of map.values()) for (const m of at) if (!alive || m.element.deref() !== undefined) n += 1 + count(m.parts, alive);
                    return n;
                }

                /** Out of a map, every managed whose element is gone, its parts with it: how many went. */
                function sweep(map) {
                    let gone = 0;
                    for (const [path, at] of map) {
                        for (const m of at) {
                            if (m.element.deref() === undefined) { at.delete(m); gone += 1 + count(m.parts, false); }
                            else gone += sweep(m.parts);
                        }
                        if (at.size === 0) map.delete(path);
                    }
                    return gone;
                }

                /**
                 * An element handed over: in charge of its style for as long as it lives - the class it is given, the states it
                 * is in - and the sub-manager of its parts, each in a map from its path to the live instances at it. Made only
                 * by the style manager, held by it and by its owner, never by a component: a component holds its reference.
                 */
                class ManagedElement {
                    constructor(mint, element, path, page) {
                        if (mint !== MINT) throw new TypeError("ManagedElement: an element is managed by the style manager alone");
                        this.element = new WeakRef(element);
                        this.path = path;
                        this.page = page;
                        this.held = new Map();
                        this.parts = new Map();
                        this.reference = new Reference(MINT, this);
                        Object.freeze(this);
                    }

                    part(element, slot) {
                        if (!(slot instanceof Slot)) throw new TypeError("part: a part is handed over along a slot its owner declares");
                        if (slot.owner !== this.path.part)
                            throw new TypeError("part: the slot is " + slot.owner.token + "'s, and this element " + this.path.part.token + "'s");
                        return this.page.manage(element, this.page.below(this.path, slot), this.parts);
                    }

                    set(type, state) {
                        if (!(type instanceof StateType) || !this.page.draws(type)) throw new TypeError("set: a state type of the states' dual");
                        if (type.browser !== null) throw new TypeError("set: " + type.token + " is the browser's to draw (" + type.browser + "), never the page's");
                        if (type.states.indexOf(state) < 0) throw new TypeError("set: " + type.token + " is " + type.states.join(" or ") + ", not " + state);
                        const was = this.state(type);
                        if (was === state) return;
                        if (state === type.rest) this.held.delete(type); else this.held.set(type, state);
                        const element = this.element.deref();
                        if (element === undefined) return;
                        if (was !== type.rest) this.page.remove(element, stateClass(type, was));
                        if (state !== type.rest) this.page.add(element, stateClass(type, state));
                    }

                    state(type) { return this.held.has(type) ? this.held.get(type) : type.rest; }
                }

                /**
                 * What a component holds of its element's managed object, and all it can do with it: set and read the
                 * element's states, and hand over its parts' elements - each handing back the part's reference. It gives no
                 * way to the element, its class or the style.
                 */
                class Reference {
                    #managed;
                    constructor(mint, managed) {
                        if (mint !== MINT) throw new TypeError("Reference: a reference is the style manager's to give");
                        this.#managed = managed;
                        Object.freeze(this);
                    }

                    /** The component the element is of. */
                    get component() { return this.#managed.path.part; }

                    /** Hands over a part's element, along a slot the component declares: the part's reference. */
                    part(element, slot) { return this.#managed.part(element, slot); }

                    /** Puts the element in a state of a type the page draws: its old state's class off, its new one's on. */
                    set(type, state) { this.#managed.set(type, state); }

                    /** The state the element is in of a type: the one set, or the one at rest. */
                    state(type) { return this.#managed.state(type); }
                }

                class StyleManager {
                    #surface; #style; #units = new Map(); #known = new WeakSet(); #swept = 0; #classes = new Map(); #roots = new Map();
                    #below = new Map(); #states = new Set(); #styles = new Set(); #page;

                    /**
                     * @param surface what it writes through: add(element, class), remove(element, class), show(style)
                     * @param clock   what runs its clean-up worker: every(ms, task) - in a browser a timer, anywhere else a tick
                     * @param style   the style the page wears first, a node of the styles' dual
                     */
                    constructor(surface, clock, style) {
                        for (const f of ["add", "remove", "show"]) if (surface === null || typeof surface !== "object" || typeof surface[f] !== "function")
                            throw new TypeError("StyleManager: its surface has add(element, class), remove(element, class) and show(style)");
                        if (clock === null || typeof clock !== "object" || typeof clock.every !== "function")
                            throw new TypeError("StyleManager: its clock has every(ms, task), to run its clean-up worker");
                        for (const h of Object.values(PATHS)) {
                            if (!(h instanceof ElementPath)) throw new TypeError("StyleManager: the paths' dual has a node no ElementPath");
                            this.#classes.set(h, elementClass(h));
                            if (h.parent === null) { this.#roots.set(h.part, h); continue; }
                            let parts = this.#below.get(h.parent);
                            if (parts === undefined) this.#below.set(h.parent, parts = new Map());
                            let roles = parts.get(h.part);
                            if (roles === undefined) parts.set(h.part, roles = new Map());
                            roles.set(h.role, h);
                        }
                        for (const s of Object.values(STATES)) if (s instanceof StateType) this.#states.add(s);
                        for (const s of Object.values(STYLES)) if (s instanceof Style) this.#styles.add(s);
                        if (!this.#styles.has(style)) throw new TypeError("StyleManager: it starts in a style of the styles' dual");
                        this.#surface = surface;
                        this.#style = style;
                        this.#page = Object.freeze({
                            add: (element, c) => surface.add(element, c),
                            remove: (element, c) => surface.remove(element, c),
                            manage: (element, path, map) => this.#manage(element, path, map),
                            below: (path, slot) => this.#child(path, slot),
                            draws: type => this.#states.has(type),
                        });
                        surface.show(style);
                        clock.every(SWEEP_EVERY, () => { this.#swept += sweep(this.#units); });
                        Object.freeze(this);
                    }

                    /** The style the page wears. */
                    get style() { return this.#style; }

                    /** The styles it may wear, in their dual's order. */
                    get styles() { return [...this.#styles]; }

                    /** How many managed objects it holds whose element is alive, and every owner's: its units' and every part's. */
                    get live() { return count(this.#units, true); }

                    /** How many managed objects it holds, their elements alive or gone: the gone until the next sweep. */
                    get held() { return count(this.#units, false); }

                    /** How many managed objects its clean-up worker has taken out, their elements gone. */
                    get swept() { return this.#swept; }

                    /** Hands over a top-level component's element, at the root: its reference. */
                    manage(element, component) {
                        const path = component instanceof Component ? this.#roots.get(component) : undefined;
                        if (path === undefined) throw new TypeError("manage: " + (component instanceof Component ? component.token : String(component))
                                + " is no top-level component the page registers; a part is handed to its owner's");
                        return this.#manage(element, path, this.#units);
                    }

                    /** Wears a style: its sheet shown, and nothing else - every element's classes are the same in every style. */
                    use(style) {
                        if (!this.#styles.has(style)) throw new TypeError("use: a style of the styles' dual");
                        const from = this.#style;
                        if (style !== from) { this.#surface.show(style); this.#style = style; }
                        return Object.freeze({ from, to: style });
                    }

                    #manage(element, path, map) {
                        if (element === null || (typeof element !== "object" && typeof element !== "function")) throw new TypeError("manage: an element is an object");
                        if (this.#known.has(element)) throw new TypeError("manage: the element is managed already");
                        const managed = new ManagedElement(MINT, element, path, this.#page);
                        enter(map, path, managed);
                        this.#known.add(element);
                        this.#surface.add(element, this.#classes.get(path));
                        return managed.reference;
                    }

                    #child(path, slot) {
                        const child = this.#below.get(path)?.get(slot.part)?.get(slot.role);
                        if (child === undefined) throw new TypeError("part: " + slot.part.token + " in " + slot.role.token + " is no part of " + path.part.token + " the page registers");
                        return child;
                    }
                }

                export { StyleManager };
                """;
    }
}
