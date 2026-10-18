package hue.captains.singapura.js.homing.design.spec.page;

import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.Handles;
import hue.captains.singapura.js.homing.design.spec.dual.States;
import hue.captains.singapura.js.homing.design.spec.dual.Styles;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The style manager's module (RFC 0071): the page's one keeper of what each element is to the styles - its place, the
 * states it is in - and of the style the page wears. Every class it writes it derives from a typed node, the same in
 * every style ({@code StyleClasses}): an element's from its handle, a state's from its word. A switch is then the sheet
 * swapped, no element written.
 *
 * <p>It touches no document. All it writes goes through the surface it is given - three functions: add a class to an
 * element, remove one, show a style. In a browser the surface is an element's {@code classList} and the page's sheets;
 * anywhere else, whatever keeps them. An element is to it an object it keys a place by, nothing more. It holds no list of
 * elements: a place is a sidecar, kept in a weak map, gone with its element.
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

    /** Its module. */
    public String script() {
        var js = JsLiterals.INSTANCE;
        return """
                // The style manager (RFC 0071): the page's one keeper of what each element is to the styles - its place, the
                // states it is in - and of the style the page wears. Every class it writes it derives from a typed node, the
                // same in every style: an element's from its handle, a state's from its word; a switch is the sheet swapped and
                // no element written. It touches no document: all it writes goes through the surface it is given - add(element,
                // class), remove(element, class), show(style). Generated from Java; not to be edited.
                """
                + "import { Component, Handle, Slot, StateType, Style } from " + js.string(DualKit.INSTANCE.address()) + ";\n"
                + "import * as HANDLES from " + js.string(Handles.INSTANCE.address()) + ";\n"
                + "import * as STATES from " + js.string(States.INSTANCE.address()) + ";\n"
                + "import * as STYLES from " + js.string(Styles.INSTANCE.address()) + ";\n"
                + """

                const MINT = Symbol("a place");

                /** A handle's class: its top-level component, then each slot's part and role - h-dialog--action-bar_actions. */
                function elementClass(handle) {
                    const segments = [];
                    for (let at = handle; at !== null; at = at.parent) segments.unshift(at.parent === null ? at.part.token : at.part.token + "_" + at.role.token);
                    return "h-" + segments.join("--");
                }

                /** A state's class: its word - s-disabled. The state at rest has none. */
                function stateClass(type, state) { return "s-" + type.words[state]; }

                /**
                 * An element's place: its handle, and the states it is in. Given only by the style manager - by place() for a
                 * top-level component, by part() for each part along the slot its owner declares - and kept beside the element.
                 */
                class Place {
                    #element; #handle; #page; #held = new Map();
                    constructor(mint, element, handle, page) {
                        if (mint !== MINT) throw new TypeError("Place: a place is the style manager's to give");
                        this.#element = element; this.#handle = handle; this.#page = page;
                        Object.freeze(this);
                    }

                    /** The component in it. */
                    get component() { return this.#handle.part; }

                    /** A part's place, along a slot the component declares: the part's element given the part's handle's class. */
                    part(element, slot) {
                        if (!(slot instanceof Slot)) throw new TypeError("part: a part takes its place by a slot its owner declares");
                        if (slot.owner !== this.#handle.part)
                            throw new TypeError("part: the slot is " + slot.owner.token + "'s, and this place " + this.#handle.part.token + "'s");
                        return this.#page.place(element, this.#page.below(this.#handle, slot));
                    }

                    /** Puts the element in a state of a type the page draws: its old state's class removed, its new one's added. */
                    set(type, state) {
                        if (!(type instanceof StateType) || !this.#page.draws(type)) throw new TypeError("set: a state type of the states' dual");
                        if (type.browser !== null) throw new TypeError("set: " + type.token + " is the browser's to draw (" + type.browser + "), never the page's");
                        if (type.states.indexOf(state) < 0) throw new TypeError("set: " + type.token + " is " + type.states.join(" or ") + ", not " + state);
                        const was = this.state(type);
                        if (was === state) return;
                        if (was !== type.rest) this.#page.remove(this.#element, stateClass(type, was));
                        if (state === type.rest) this.#held.delete(type);
                        else { this.#held.set(type, state); this.#page.add(this.#element, stateClass(type, state)); }
                    }

                    /** The state it is in of a type: the one set, or the one at rest. */
                    state(type) { return this.#held.has(type) ? this.#held.get(type) : type.rest; }
                }

                class StyleManager {
                    #surface; #style; #places = new WeakMap(); #classes = new Map(); #roots = new Map(); #below = new Map();
                    #states = new Set(); #styles = new Set(); #page;

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
                        this.#page = Object.freeze({
                            add: (element, c) => surface.add(element, c),
                            remove: (element, c) => surface.remove(element, c),
                            place: (element, handle) => this.#place(element, handle),
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

                    /** A top-level component's place: its element given its handle's class. */
                    place(element, component) {
                        const handle = component instanceof Component ? this.#roots.get(component) : undefined;
                        if (handle === undefined) throw new TypeError("place: " + (component instanceof Component ? component.token : String(component))
                                + " is no top-level component the page registers; a part takes its place from its owner's");
                        return this.#place(element, handle);
                    }

                    /** Wears a style: its sheet shown, and nothing else - every element's classes are the same in every style. */
                    use(style) {
                        if (!this.#styles.has(style)) throw new TypeError("use: a style of the styles' dual");
                        const from = this.#style;
                        if (style !== from) { this.#surface.show(style); this.#style = style; }
                        return Object.freeze({ from, to: style });
                    }

                    #place(element, handle) {
                        if (element === null || (typeof element !== "object" && typeof element !== "function")) throw new TypeError("place: an element is an object");
                        if (this.#places.has(element)) throw new TypeError("place: the element has its place already");
                        const place = new Place(MINT, element, handle, this.#page);
                        this.#places.set(element, place);
                        this.#surface.add(element, this.#classes.get(handle));
                        return place;
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
