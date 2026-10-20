package hue.captains.singapura.js.homing.design.spec.page;

import hue.captains.singapura.js.homing.design.spec.JsLiterals;
import hue.captains.singapura.js.homing.design.spec.dual.Components;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.ElementPaths;
import hue.captains.singapura.js.homing.design.spec.dual.States;
import hue.captains.singapura.js.homing.design.spec.dual.Styles;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The style manager's module (RFC 0071): the page's one keeper of every element's style, and of the style the page wears.
 * A component places its element with it and gets back a reference; that is all it ever does with it. It never imports
 * it: a top-level component's header binds it to the component ({@code unit}) - a placement at the root - and a part is
 * handed a placement for its slot by its owner (D28). Either is a function, element to reference; the component calls
 * it in its constructor with the element it made, so its owner never holds its element nor its reference.
 *
 * <p>Each element placed becomes a managed object, in charge of everything about the element's style for as long as it
 * lives: the class it is given, the states it is in - and, for a composite, its parts, being their sub-manager. A
 * composite asks its own reference for a placement along each slot it declares - {@code slot(slot)}, one placement a
 * part, used once - and hands it to the part. So the managed objects are a tree beside the tree of element paths, one
 * for each live unit: the style manager keeps the units, each managed object its parts - each in a map from an element
 * path to the live instances at it, there being any number of each: three dialogs open, a tab strip's every tab.
 *
 * <p>A component never holds a managed object, only a reference to it: its states to set and read, a placement for each
 * part it makes, and its rooms' classes written as it places them - an element of its own inside it that is no part,
 * sharing its class (RFC 0067): {@code room(ROOM_TAIL, element)}, once a room, the class its casting's and the room's
 * name, {@code c-tab-strip__tail}. Not the element, not its class, not the style.
 *
 * <p>Every class is derived from a typed node, the same in every style ({@code StyleClasses}): an element's from its
 * casting - the path it is handed along says which, its owner and slot being the layout's (RFC 0067, D54) - a state's
 * from its word. A switch is then the sheet swapped, and no class written.
 *
 * <p>A component that shows a picture - an icon (RFC 0067, D56) - has it drawn by the style manager, never by itself:
 * the style manager is handed a picture provider, which says for a component and a style what its element shows,
 * and draws it as the element is placed. A switch redraws each element whose picture the new style draws otherwise, and
 * no other; still no class written.
 *
 * <p>It touches no document. All it writes goes through the surface it is given - three functions: add a class to an
 * element, remove one, show a style; and a fourth when it is handed a picture provider, draw a picture on an element. In
 * a browser the surface is an element's {@code classList}, its content and the page's sheets; anywhere else, whatever
 * keeps them. It holds each element weakly. A managed object whose element is gone is taken out
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
                "getElementById", "adoptedStyleSheets", "CSSStyleSheet", "insertRule", "cssText", "getComputedStyle", "HTMLElement",
                "textContent", "innerHTML", "replaceChildren", "appendChild", "createElementNS");
    }

    /** What a component's reference gives it, and nothing more. */
    public List<String> reference() { return List.of("component", "slot", "set", "state", "room"); }

    /** Its module. */
    public String script() {
        var js = JsLiterals.INSTANCE;
        return """
                // The style manager (RFC 0071): the page's one keeper of every element's style, and of the style the page
                // wears. A component places its element with it - through the binding its header is stamped with, or the
                // placement its owner hands it - and gets back a reference: its states to set, a placement for each of its
                // parts; it does nothing else. Each element placed is a managed object, in charge of the element's style for
                // as long as it lives, and the sub-manager of its parts. Every class is derived from a
                // typed node, the same in every style: a switch is the sheet swapped, no class written. What a component
                // shows - an icon's picture - it draws from the picture provider it is handed, as the element is placed, and
                // again on a switch where the new style draws it otherwise. It touches no document: all it writes goes
                // through the surface it is given - add(element, class), remove(element, class), show(style), and
                // draw(element, picture) with a picture provider - and its clean-up worker runs on the clock it is given -
                // every(ms, task). Generated from Java; not to be edited.
                """
                + "import { Component, ElementPath, Slot, Room, StateType, Style } from " + js.string(DualKit.INSTANCE.address()) + ";\n"
                + "import * as COMPONENTS from " + js.string(Components.INSTANCE.address()) + ";\n"
                + "import * as PATHS from " + js.string(ElementPaths.INSTANCE.address()) + ";\n"
                + "import * as STATES from " + js.string(States.INSTANCE.address()) + ";\n"
                + "import * as STYLES from " + js.string(Styles.INSTANCE.address()) + ";\n"
                + """

                const MINT = Symbol("the style manager's");

                /**
                 * An element's class: its casting's - the part, then its role where the role has a look of its own -
                 * c-plain-button, c-label_title. Its owner and its slot are the layout's: a top-level component, or a part
                 * a role only places, looks as it does on its own.
                 */
                function elementClass(path) {
                    return "c-" + (path.parent === null || path.role.functional ? path.part.token : path.part.token + "_" + path.role.token);
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
                 * An element placed: in charge of its style for as long as it lives - the class it is given, the states it is
                 * in - and the sub-manager of its parts, each in a map from its path to the live instances at it. Made only by
                 * the style manager, held by it and by its owner, never by a component: a component holds its reference.
                 */
                class ManagedElement {
                    constructor(mint, element, path, page) {
                        if (mint !== MINT) throw new TypeError("ManagedElement: an element is managed by the style manager alone");
                        this.element = new WeakRef(element);
                        this.path = path;
                        this.page = page;
                        this.held = new Map();
                        this.parts = new Map();
                        this.rooms = new Set();
                        this.reference = new Reference(MINT, this);
                        Object.freeze(this);
                    }

                    /** A placement along a slot this element's component declares: element to reference, used once. */
                    slot(slot) {
                        if (!(slot instanceof Slot)) throw new TypeError("slot: a part is placed along a slot its owner declares");
                        if (slot.owner !== this.path.part)
                            throw new TypeError("slot: the slot is " + slot.owner.token + "'s, and this element " + this.path.part.token + "'s");
                        const path = this.page.below(this.path, slot), parts = this.parts, page = this.page;
                        let used = false;
                        return Object.freeze(function place(element) {
                            if (used) throw new TypeError("a placement places one element, once: its owner asks its reference for another");
                            used = true;
                            return page.manage(element, path, parts);
                        });
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

                    /** A room of its component's own placed: its element given the room's class - once a room. */
                    room(room, element) {
                        if (!(room instanceof Room) || !this.page.rooms(room)) throw new TypeError("room: a room of the components' dual");
                        if (room.owner !== this.path.part) throw new TypeError("room: " + room.name + " is " + room.owner.token + "'s, and this element " + this.path.part.token + "'s");
                        if (element === null || (typeof element !== "object" && typeof element !== "function")) throw new TypeError("room: an element is an object");
                        if (this.rooms.has(room)) throw new TypeError("room: " + room.name + " is placed already: a room is one element of its owner's");
                        this.rooms.add(room);
                        this.page.add(element, this.page.classOf(this.path) + "__" + room.name);
                    }
                }

                /**
                 * What a component holds of its element's managed object, and all it can do with it: set and read the
                 * element's states, and give a placement for each part it makes - the part placing its own element with it,
                 * and keeping the reference it gets. It gives no way to the element, its class or the style.
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

                    /** A placement along a slot the component declares, for one part: element to the part's reference, used once. */
                    slot(slot) { return this.#managed.slot(slot); }

                    /** Puts the element in a state of a type the page draws: its old state's class off, its new one's on. */
                    set(type, state) { this.#managed.set(type, state); }

                    /** The state the element is in of a type: the one set, or the one at rest. */
                    state(type) { return this.#managed.state(type); }

                    /** A room of the component's own placed: an element of its own, inside it, given the room's class - once a room. */
                    room(room, element) { this.#managed.room(room, element); }
                }

                class StyleManager {
                    #surface; #style; #units = new Map(); #known = new WeakSet(); #swept = 0; #classes = new Map(); #roots = new Map();
                    #below = new Map(); #states = new Set(); #styles = new Set(); #rooms = new Set(); #page; #pictures = null; #drawn = new WeakMap();

                    /**
                     * @param surface what it writes through: add(element, class), remove(element, class), show(style) - and
                     *                draw(element, picture), with a picture provider
                     * @param clock   what runs its clean-up worker: every(ms, task) - in a browser a timer, anywhere else a tick
                     * @param style   the style the page wears first, a node of the styles' dual
                     * @param pictures what components show, if any shows a picture: picture(component, style), the picture
                     *                its element shows in the style, or null for a component that shows none - one object for one picture
                     */
                    constructor(surface, clock, style, pictures) {
                        for (const f of ["add", "remove", "show"]) if (surface === null || typeof surface !== "object" || typeof surface[f] !== "function")
                            throw new TypeError("StyleManager: its surface has add(element, class), remove(element, class) and show(style)");
                        if (pictures !== undefined && pictures !== null) {
                            if (typeof pictures !== "object" || typeof pictures.picture !== "function")
                                throw new TypeError("StyleManager: its picture provider has picture(component, style)");
                            if (typeof surface.draw !== "function")
                                throw new TypeError("StyleManager: handed a picture provider, its surface has draw(element, picture)");
                            this.#pictures = pictures;
                        }
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
                        for (const n of Object.values(COMPONENTS)) if (n instanceof Room) this.#rooms.add(n);
                        if (!this.#styles.has(style)) throw new TypeError("StyleManager: it starts in a style of the styles' dual");
                        this.#surface = surface;
                        this.#style = style;
                        this.#page = Object.freeze({
                            add: (element, c) => surface.add(element, c),
                            remove: (element, c) => surface.remove(element, c),
                            manage: (element, path, map) => this.#manage(element, path, map),
                            below: (path, slot) => this.#child(path, slot),
                            draws: type => this.#states.has(type),
                            rooms: room => this.#rooms.has(room),
                            classOf: path => this.#classes.get(path),
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

                    /**
                     * A top-level component's binding, which its header is stamped with: a placement at the root - a function
                     * that places the component's element there and gives back its reference, for each instance the component
                     * makes - the one way an element is placed but as a part, by the placement its owner hands it.
                     */
                    unit(component) {
                        const path = component instanceof Component ? this.#roots.get(component) : undefined;
                        if (path === undefined) throw new TypeError("unit: " + (component instanceof Component ? component.token : String(component))
                                + " is no top-level component the page registers; a part is placed by its owner's placement");
                        return Object.freeze(element => this.#manage(element, path, this.#units));
                    }

                    /**
                     * Wears a style: its sheet shown, every element's classes the same in every style; and each element whose
                     * picture the style draws otherwise redrawn - from which style, to which, how many redrawn.
                     */
                    use(style) {
                        if (!this.#styles.has(style)) throw new TypeError("use: a style of the styles' dual");
                        const from = this.#style;
                        let redrawn = 0;
                        if (style !== from) {
                            this.#surface.show(style);
                            this.#style = style;
                            if (this.#pictures !== null) redrawn = this.#redraw(this.#units);
                        }
                        return Object.freeze({ from, to: style, redrawn });
                    }

                    #manage(element, path, map) {
                        if (element === null || (typeof element !== "object" && typeof element !== "function")) throw new TypeError("manage: an element is an object");
                        if (this.#known.has(element)) throw new TypeError("manage: the element is managed already");
                        const managed = new ManagedElement(MINT, element, path, this.#page);
                        enter(map, path, managed);
                        this.#known.add(element);
                        this.#surface.add(element, this.#classes.get(path));
                        const picture = this.#pictures === null ? null : this.#pictures.picture(path.part, this.#style);
                        if (picture !== null) { this.#drawn.set(managed, picture); this.#surface.draw(element, picture); }
                        return managed.reference;
                    }

                    /** In a map and below, each live element showing a picture the style worn draws otherwise, redrawn: how many. */
                    #redraw(map) {
                        let n = 0;
                        for (const at of map.values()) for (const m of at) {
                            const element = m.element.deref();
                            if (element === undefined) continue;
                            const was = this.#drawn.get(m);
                            if (was !== undefined) {
                                const now = this.#pictures.picture(m.path.part, this.#style);
                                if (now === null) throw new TypeError("draw: " + m.path.part.token + " shows a picture in one style and none in " + this.#style.token);
                                if (now !== was) { this.#drawn.set(m, now); this.#surface.draw(element, now); n++; }
                            }
                            n += this.#redraw(m.parts);
                        }
                        return n;
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
