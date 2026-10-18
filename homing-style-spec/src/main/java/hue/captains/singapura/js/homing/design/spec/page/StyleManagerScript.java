package hue.captains.singapura.js.homing.design.spec.page;

import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.Handles;
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
 * objects are a tree beside the handle tree, one for each live unit: the style manager keeps the units, each managed
 * object its parts - each in a map from a handle to the live instances at it, there being any number of each: three
 * dialogs open, a tab strip's every tab.
 *
 * <p>A component never holds a managed object, only a reference to it: its states to set and read, its parts to hand
 * over. Not the element, not its class, not the style.
 *
 * <p>Every class is derived from a typed node, the same in every style ({@code StyleClasses}): an element's from its
 * handle, a state's from its word. A switch is then the sheet swapped, and no element written.
 *
 * <p>It touches no document. All it writes goes through the surface it is given - three functions: add a class to an
 * element, remove one, show a style. In a browser the surface is an element's {@code classList} and the page's sheets;
 * anywhere else, whatever keeps them. It holds each element weakly, and a managed object whose element is gone leaves its
 * map by itself - no release call.
 *
 * <p>It accepts only its duals' own nodes, by identity: a top-level component the handles start at, a slot its owner
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
                // show(style). Generated from Java; not to be edited.
                """
                + "import { Component, Handle, Slot, StateType, Style } from " + js.string(DualKit.INSTANCE.address()) + ";\n"
                + "import * as HANDLES from " + js.string(Handles.INSTANCE.address()) + ";\n"
                + "import * as STATES from " + js.string(States.INSTANCE.address()) + ";\n"
                + "import * as STYLES from " + js.string(Styles.INSTANCE.address()) + ";\n"
                + """

                const MINT = Symbol("the style manager's");

                /** A handle's class: its top-level component, then each slot's part and role - h-dialog--action-bar_actions. */
                function elementClass(handle) {
                    const segments = [];
                    for (let at = handle; at !== null; at = at.parent) segments.unshift(at.parent === null ? at.part.token : at.part.token + "_" + at.role.token);
                    return "h-" + segments.join("--");
                }

                /** A state's class: its word - s-disabled. The state at rest has none. */
                function stateClass(type, state) { return "s-" + type.words[state]; }

                /** Into a map from a handle to its live instances, one more. */
                function enter(map, handle, managed) {
                    let at = map.get(handle);
                    if (at === undefined) map.set(handle, at = new Set());
                    at.add(managed);
                }

                /** The live of a map, counted - and the gone taken out as they are met. */
                function live(map) {
                    let n = 0;
                    for (const [handle, at] of map) {
                        for (const m of at) if (m.element.deref() === undefined) at.delete(m); else n += 1 + live(m.parts);
                        if (at.size === 0) map.delete(handle);
                    }
                    return n;
                }

                /**
                 * An element handed over: in charge of its style for as long as it lives - the class it is given, the states it
                 * is in - and the sub-manager of its parts, each in a map from its handle to the live instances at it. Made only
                 * by the style manager, held by it and by its owner, never by a component: a component holds its reference.
                 */
                class ManagedElement {
                    constructor(mint, element, handle, page) {
                        if (mint !== MINT) throw new TypeError("ManagedElement: an element is managed by the style manager alone");
                        this.element = new WeakRef(element);
                        this.handle = handle;
                        this.page = page;
                        this.held = new Map();
                        this.parts = new Map();
                        this.reference = new Reference(MINT, this);
                        Object.freeze(this);
                    }

                    part(element, slot) {
                        if (!(slot instanceof Slot)) throw new TypeError("part: a part is handed over along a slot its owner declares");
                        if (slot.owner !== this.handle.part)
                            throw new TypeError("part: the slot is " + slot.owner.token + "'s, and this element " + this.handle.part.token + "'s");
                        return this.page.manage(element, this.page.below(this.handle, slot), this.parts);
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
                    get component() { return this.#managed.handle.part; }

                    /** Hands over a part's element, along a slot the component declares: the part's reference. */
                    part(element, slot) { return this.#managed.part(element, slot); }

                    /** Puts the element in a state of a type the page draws: its old state's class off, its new one's on. */
                    set(type, state) { this.#managed.set(type, state); }

                    /** The state the element is in of a type: the one set, or the one at rest. */
                    state(type) { return this.#managed.state(type); }
                }

                class StyleManager {
                    #surface; #style; #units = new Map(); #known = new WeakSet(); #gone; #classes = new Map(); #roots = new Map();
                    #below = new Map(); #states = new Set(); #styles = new Set(); #page;

                    /**
                     * @param surface what it writes through: add(element, class), remove(element, class), show(style)
                     * @param style   the style the page wears first, a node of the styles' dual
                     */
                    constructor(surface, style) {
                        for (const f of ["add", "remove", "show"]) if (surface === null || typeof surface !== "object" || typeof surface[f] !== "function")
                            throw new TypeError("StyleManager: its surface has add(element, class), remove(element, class) and show(style)");
                        for (const h of Object.values(HANDLES)) {
                            if (!(h instanceof Handle)) throw new TypeError("StyleManager: the handles' dual has a node no Handle");
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
                        this.#gone = new FinalizationRegistry(({ map, handle, managed }) => {
                            const at = map.get(handle);
                            if (at === undefined) return;
                            at.delete(managed);
                            if (at.size === 0) map.delete(handle);
                        });
                        this.#page = Object.freeze({
                            add: (element, c) => surface.add(element, c),
                            remove: (element, c) => surface.remove(element, c),
                            manage: (element, handle, map) => this.#manage(element, handle, map),
                            below: (handle, slot) => this.#child(handle, slot),
                            draws: type => this.#states.has(type),
                        });
                        surface.show(style);
                        Object.freeze(this);
                    }

                    /** The style the page wears. */
                    get style() { return this.#style; }

                    /** The styles it may wear, in their dual's order. */
                    get styles() { return [...this.#styles]; }

                    /** How many elements it manages whose element is still alive, its units' and every part's. */
                    get live() { return live(this.#units); }

                    /** Hands over a top-level component's element, at the root: its reference. */
                    manage(element, component) {
                        const handle = component instanceof Component ? this.#roots.get(component) : undefined;
                        if (handle === undefined) throw new TypeError("manage: " + (component instanceof Component ? component.token : String(component))
                                + " is no top-level component the page registers; a part is handed to its owner's");
                        return this.#manage(element, handle, this.#units);
                    }

                    /** Wears a style: its sheet shown, and nothing else - every element's classes are the same in every style. */
                    use(style) {
                        if (!this.#styles.has(style)) throw new TypeError("use: a style of the styles' dual");
                        const from = this.#style;
                        if (style !== from) { this.#surface.show(style); this.#style = style; }
                        return Object.freeze({ from, to: style });
                    }

                    #manage(element, handle, map) {
                        if (element === null || (typeof element !== "object" && typeof element !== "function")) throw new TypeError("manage: an element is an object");
                        if (this.#known.has(element)) throw new TypeError("manage: the element is managed already");
                        const managed = new ManagedElement(MINT, element, handle, this.#page);
                        enter(map, handle, managed);
                        this.#known.add(element);
                        this.#gone.register(element, { map, handle, managed });
                        this.#surface.add(element, this.#classes.get(handle));
                        return managed.reference;
                    }

                    #child(handle, slot) {
                        const child = this.#below.get(handle)?.get(slot.part)?.get(slot.role);
                        if (child === undefined) throw new TypeError("part: " + slot.part.token + " in " + slot.role.token + " is no part of " + handle.part.token + " the page registers");
                        return child;
                    }
                }

                export { StyleManager };
                """;
    }
}
