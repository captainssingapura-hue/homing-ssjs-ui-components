// =============================================================================
// SimpleComponent — the standard of a SIMPLE component, on the style manager
// (RFC 0071): one element, which it makes itself, of its own tag, with the
// single-use maker its owner's branch issues - branch.elementMaker(name) - and
// places itself, with the single-use placement its owner hands it for the slot
// it is in, or, at the root, with its own binding. The reference it gets back it
// keeps to itself: its owner holds neither its element's style nor its
// reference. (A COMPOSED component takes a branch of the DOM-ops party and mints
// its own tree on it: another standard.) A simple component is three:
//
//   the component  its state - one value on each axis, kept here and nowhere
//                  else, changed only by its setters - its degrees, its words,
//                  and the handlers set on its events;
//   its style      its reference: its casting's class put on as it is placed,
//                  once, by the style manager; a state's class while it holds,
//                  set through the reference (D12, D13); its degrees the
//                  style-agnostic manager's, a number on its element;
//   its behaviour  the leaf's BEHAVIOR: what the element does, its listeners. It
//                  reads the state, changes it through the component, and has
//                  the component fire its events; it never touches the style.
//
// An AXIS is a sum type of mutually exclusive values: enabled is true or false.
// An axis that is one of the house's states names its state type - a node its
// header imports, state: StateEnabled - and setting it sets the state through
// the reference, a yes or a no as "true" or "false"; the browser's own states
// (hovered, focused, down) are never an axis. Its change is an event the
// component offers, its type the state type itself. Setting a value stores it,
// writes its reflection and sets its state, tells the behaviour (changed(axis,
// value)), and fires the change to its handler, if one is set, with the previous
// value: from a setter with no DOM event, from the behaviour with the one that
// made it. The same value again does nothing.
//
// Its EVENTS are the others it offers - [EventClick], nodes its header imports -
// each dressed from the raw DOM event into a ComponentEvent that carries it. A
// handler is set by an explicit setter per event (setOnClick,
// setOnEnabledChanged): it is asked canHandle(type) first, refused unless it
// answers true, refused if it is a closure - a function, or a bare object of
// functions - and refused for an event that has one already: zero or one per
// event, and once for good. It is called with handle(event).
//
// What a leaf declares, as statics:
//   TAG       the element it takes: "button"
//   AXES      { axis: { values, rest, reflect?(el, value), state? } }
//   EVENTS    the events it offers besides its states' changes: [EventClick]
//   DEGREES   the extents it varies by, nodes its header imports: [ExtentSize]
//   BEHAVIOR  its behaviour's class: new BEHAVIOR(el, component)
// an accessor for each axis, degree and word, a setter for each event, and a
// constructor of its own - the one every component has (D29), (make, fixed,
// place?) - that hands its own fixed type and its own binding on:
//
//   class PlainButton extends Button {
//       constructor(make, fixed, place) { super(make, fixed, FixedPlainButton, place ?? managePlainButton); }
//   }
//   var b = new PlainButton(branch.elementMaker("save"), new FixedPlainButton(), me.slot(SLOT_ACTION_BAR_PLAIN_BUTTON_ACTIONS));
//     make    the maker its owner's branch issues: it makes the <TAG>, once
//     fixed   what is immutable for its life: an object of its own fixed type,
//             made by its dual (D30) - none of a bag of named values
//     place   the placement its owner hands it for its slot - its owner's
//             reference's slot(SLOT) - none at the root, where it places
//             itself with its own binding; a component only ever a part has
//             none, and is refused without one
//   .el  .fixed
// Everything else that changes is a setter's: its words, its degrees, its axes.
// =============================================================================

/** Which of the style-agnostic manager's numbers each extent is: the colour's is its extent. */
var _COMPONENT_DEGREE_SETTER = Object.freeze({ colour: "extent", size: "size", aspect: "aspect" });

class SimpleComponent {
    #styles;

    /**
     * @param make  the maker its owner's branch issues
     * @param fixed what is fixed for its life, of the leaf's own fixed type
     * @param Fixed the leaf's own fixed type, which the leaf hands on
     * @param place the placement its owner handed it, or the leaf's own binding at the root
     */
    constructor(make, fixed, Fixed, place) {
        var Leaf = new.target, self = this;
        if (!Leaf.BEHAVIOR || typeof Leaf.TAG !== "string") throw new Error("[" + Leaf.name + "] is no leaf: a simple component is made as one of its leaves");
        if (typeof Fixed !== "function" || fixed === null || typeof fixed !== "object" || Object.getPrototypeOf(fixed) !== Fixed.prototype)
            throw new TypeError("[" + Leaf.name + "] what is fixed for its life is an object of its own fixed type"
                + (typeof Fixed === "function" ? ", " + Fixed.name : "") + " - never a bag of named values");
        if (!DomOpsParty.isElementMaker(make))
            throw new TypeError("[" + Leaf.name + "] takes the maker its owner's branch issues - branch.elementMaker(name) - and no other function, nor an element");
        if (typeof place !== "function")
            throw new TypeError("[" + Leaf.name + "] is placed by the placement its owner hands it - its reference's slot(SLOT) - and has no binding of its own: it is only ever a part");
        var el = make(Leaf.TAG);
        this.el = el;
        this.fixed = fixed;
        this.#styles = place(el);
        var axes = Leaf.AXES || {};
        this._state = {};
        for (var a in axes) this._state[a] = axes[a].rest;
        this._handlers = new Map();
        this._degrees = new Map();
        (Leaf.DEGREES || []).forEach(function (x) { self._degrees.set(x, x.rest); });
        for (var r in axes) if (typeof axes[r].reflect === "function") axes[r].reflect(el, this._state[r]);
        this._behavior = new Leaf.BEHAVIOR(el, this);
    }

    /** The value on an axis. */
    _get(axis) {
        if (!(axis in this._state)) throw new Error("[" + this.constructor.name + "] has no axis '" + axis + "'");
        return this._state[axis];
    }

    /** A value on an axis, from a setter: changed with no DOM event. */
    _set(axis, value) { this._change(axis, value, null); }

    /**
     * A value on an axis: stored, its reflection written, its state set through the reference, told to the
     * behaviour, and - for a state, with a handler set for its change - fired with the previous value and the
     * DOM event that made it, if one did. The same value again does nothing.
     */
    _change(axis, value, raw) {
        var Leaf = this.constructor, v = SimpleComponent._value(Leaf, axis, value), was = this._state[axis];
        if (was === v) return;
        this._state[axis] = v;
        var a = Leaf.AXES[axis];
        if (typeof a.reflect === "function") a.reflect(this.el, v);
        if (a.state) this.#styles.set(a.state, a.state.flag ? String(v) : v);
        if (this._behavior && typeof this._behavior.changed === "function") this._behavior.changed(axis, v);
        if (a.state && this._handlers.has(a.state)) this._handlers.get(a.state).handle(new StateChange(a.state, this, raw, was));
    }

    /** One of its events, dressed from the DOM event that made it, to its handler if one is set. */
    _fire(type, raw) {
        var h = this._handlers.get(type);
        if (h) h.handle(new ComponentEvent(type, this, raw));
    }

    /**
     * A handler for one of its events, once and for good: asked first whether it handles the type,
     * then held to being an object of a class, then refused if the event has one already.
     */
    _setOn(type, handler) {
        var Leaf = this.constructor, what = type && type.token ? type.token : String(type);
        if (!handler || typeof handler.canHandle !== "function" || handler.canHandle(type) !== true)
            throw new Error("[" + Leaf.name + "] a handler says it handles " + what + ": canHandle(" + what + ") answers true");
        if (typeof handler === "function" || Object.getPrototypeOf(handler) === Object.prototype || Object.getPrototypeOf(handler) === null)
            throw new Error("[" + Leaf.name + "] a handler is an object of a class declared for it, never a closure");
        if (typeof handler.handle !== "function") throw new Error("[" + Leaf.name + "] a handler handles: handle(event)");
        if (!SimpleComponent._offers(Leaf, type)) throw new Error("[" + Leaf.name + "] offers no " + what);
        if (this._handlers.has(type)) throw new Error("[" + Leaf.name + "] has its handler for " + what + " already: set once, it stays");
        this._handlers.set(type, handler);
    }

    /** A degree, one of the extents it varies by. */
    _degree(extent) {
        if (!this._degrees.has(extent)) throw new Error("[" + this.constructor.name + "] varies by no " + (extent && extent.token));
        return this._degrees.get(extent);
    }

    /** A degree set: clamped to −1 … 1, at its rest unless it is a number - and at its rest, nothing written. */
    _setDegree(extent, n) {
        if (!this._degrees.has(extent)) throw new Error("[" + this.constructor.name + "] varies by no " + (extent && extent.token));
        var x = Math.max(-1, Math.min(1, Number(n))), v = Number.isFinite(x) ? x : extent.rest;
        this._degrees.set(extent, v);
        CssClassManagerInstance[_COMPONENT_DEGREE_SETTER[extent.token]](this.el, v === extent.rest ? null : v);
    }

    /** A placement along a slot the component declares, for one part it makes: its reference's, used once. */
    _slot(slot) { return this.#styles.slot(slot); }

    /** A room of its own placed - an element of its own inside it that is no part (RFC 0067): its reference writes the room's class, once. */
    _room(room, element) { this.#styles.room(room, element); }

    static _value(Leaf, axis, value) {
        var a = (Leaf.AXES || {})[axis];
        if (!a) throw new Error("[" + Leaf.name + "] has no axis '" + axis + "'");
        if (a.values.indexOf(value) < 0) throw new Error("[" + Leaf.name + "] " + axis + " is one of " + a.values.join(", ") + ", not " + value);
        return value;
    }

    /** Whether the leaf offers the event: one of its EVENTS, or the change of one of its states - its state type. */
    static _offers(Leaf, type) {
        if ((Leaf.EVENTS || []).indexOf(type) >= 0) return true;
        var axes = Leaf.AXES || {};
        for (var a in axes) if (axes[a].state && axes[a].state === type) return true;
        return false;
    }
}

/** One of a component's events, dressed from the DOM event that made it: its type, its component, and the raw event, or null. */
class ComponentEvent {
    constructor(type, source, raw) {
        this.type = type;
        this.source = source;
        this.raw = raw == null ? null : raw;
        if (new.target === ComponentEvent) Object.freeze(this);
    }
}

/** A state's change: a ComponentEvent of its state type, carrying the state's previous value. What it became is the component's attribute. */
class StateChange extends ComponentEvent {
    constructor(type, source, raw, previous) {
        super(type, source, raw);
        this.previous = previous;
        Object.freeze(this);
    }
}
