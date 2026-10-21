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
//                  else, changed only by its setters and its behaviour - its
//                  degrees, its words, and the listeners that hear it change;
//   its style      its reference: its casting's class put on as it is placed,
//                  once, by the style manager; a classed state's class while it
//                  holds, set through the reference (D12, D13); its degrees the
//                  style-agnostic manager's, a number on its element;
//   its behaviour  the leaf's BEHAVIOR: what the element does, its listeners. It
//                  reads the state and changes it through the component; it
//                  never touches the style.
//
// An AXIS is one of its states: a value of a type of state - a node its header
// imports, state: StateEnabled - one of the values the type allows (RFC 0069,
// amended 21 Oct): enabled is true or false, a count of clicks a whole number
// from 0, a draft a text. Setting a value stores it, writes its reflection, sets
// the state's class through the reference where the type is classed - a yes or a
// no as "true" or "false" - and nowhere else, tells the behaviour (changed(axis,
// value)), and tells the change to whoever hears it. The same value again does
// nothing: a change is between two different values. The browser's own states
// (hovered, focused, down) are never an axis.
//
// A change is HEARD one way: onChange(on, listener) - on one of its state types,
// every change of it; on one of that type's named changes - StateClicks.change(
// "click") - that change alone. The listener is asked canHandle(on) first,
// refused unless it answers true, refused if it is a closure - a function, or a
// bare object of functions - and refused where one is set already: zero or one
// for each, and once for good. It is handed a StateChange - its type, its
// component, the value before and the value after, the named changes it is - the
// type's listener first, then each named change's. No change carries its cause:
// the browser's events are no component's; what one means to a component is a
// change of a state of its own.
//
// Its EVENTS are what it still offers that is no change of a state - [EventClick]
// - each dressed from the raw DOM event into a ComponentEvent, heard by its own
// setter (setOnClick), with the same refusals: each goes as it moves to a state.
//
// What a leaf declares, as statics:
//   TAG       the element it takes: "button"
//   AXES      { axis: { values?, rest, reflect?(el, value), state? } } - its
//             values where no type of state says them
//   EVENTS    what it offers that is no change of a state: [EventClick]
//   DEGREES   the extents it varies by, nodes its header imports: [ExtentSize]
//   BEHAVIOR  its behaviour's class: new BEHAVIOR(el, component)
// an accessor for each axis, degree and word, a setter for each event, and a
// constructor of its own - the one every component has (D29), (make, fixed,
// place?) - that hands its own fixed type and its own binding on:
//
//   class PlainButton extends Button {
//       constructor(make, fixed, place) { super(make, fixed, FixedPlainButton, place ?? managePlainButton); }
//   }
//   var b = new PlainButton(branch.elementMaker("save"), new FixedPlainButton(), me.place(FixedPlainButton));
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
        this._listeners = new Map();
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

    /** A value on an axis, from a setter. */
    _set(axis, value) { this._change(axis, value); }

    /**
     * A value on an axis: stored, its reflection written, its state's class set through the reference where its type
     * is classed, told to the behaviour, and - for a state - its change told to whoever hears it. The same value again
     * does nothing.
     */
    _change(axis, value) {
        var Leaf = this.constructor, v = SimpleComponent._value(Leaf, axis, value), was = this._state[axis];
        if (was === v) return;
        this._state[axis] = v;
        var a = Leaf.AXES[axis];
        if (typeof a.reflect === "function") a.reflect(this.el, v);
        if (a.state && a.state.classed) this.#styles.set(a.state, SimpleComponent._said(a.state, v));   // a class only for a classed state (RFC 0069, amended)
        if (this._behavior && typeof this._behavior.changed === "function") this._behavior.changed(axis, v);
        if (a.state) this._tell(a.state, was, v);
    }

    /** A change of one of its states, told: the type's listener first, then each of the named changes it is. */
    _tell(type, before, after) {
        var b = SimpleComponent._said(type, before), x = SimpleComponent._said(type, after);
        var named = type.changes.filter(function (c) { return c.transition.fits(b, x); });
        var change = new StateChange(type, this, before, after, named), listeners = this._listeners;
        if (listeners.has(type)) listeners.get(type).handle(change);
        named.forEach(function (c) { if (listeners.has(c)) listeners.get(c).handle(change); });
    }

    /**
     * A listener for the changes of one of its states - on its type, every change; on one of the type's named
     * changes, that one alone - once and for good: asked first whether it hears it, then held to being an object of
     * a class, then refused where one is set already.
     */
    onChange(on, listener) {
        var Leaf = this.constructor, type = SimpleComponent._typeOf(Leaf, on);
        var what = type === null ? String(on && (on.token || on.name)) : on === type ? type.token : type.token + "'s " + on.name;
        if (type === null) throw new Error("[" + Leaf.name + "] has no state " + what + ": it is heard changing its own states alone");
        if (!listener || typeof listener.canHandle !== "function" || listener.canHandle(on) !== true)
            throw new Error("[" + Leaf.name + "] a listener says it hears " + what + ": canHandle(" + what + ") answers true");
        if (typeof listener === "function" || Object.getPrototypeOf(listener) === Object.prototype || Object.getPrototypeOf(listener) === null)
            throw new Error("[" + Leaf.name + "] a listener is an object of a class declared for it, never a closure");
        if (typeof listener.handle !== "function") throw new Error("[" + Leaf.name + "] a listener hears: handle(change)");
        if (this._listeners.has(on)) throw new Error("[" + Leaf.name + "] has its listener for " + what + " already: set once, it stays");
        this._listeners.set(on, listener);
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

    /** A placement for one part it makes, by the part's fixed type - and its role, only where that player fills two places: its reference's, used once. */
    _place(fixed, role) { return this.#styles.place(fixed, role); }

    /** A room of its own placed - an element of its own inside it that is no part (RFC 0067): its reference writes the room's class, once. */
    _room(room, element) { this.#styles.room(room, element); }

    /** A value on an axis: one of its values, where it lists them; else one its type of state allows. */
    static _value(Leaf, axis, value) {
        var a = (Leaf.AXES || {})[axis];
        if (!a) throw new Error("[" + Leaf.name + "] has no axis '" + axis + "'");
        if (a.values ? a.values.indexOf(value) < 0 : !(a.state && a.state.allows(SimpleComponent._said(a.state, value))))
            throw new Error("[" + Leaf.name + "] " + axis + " is " + (a.values ? "one of " + a.values.join(", ") : "a value its state " + (a.state ? a.state.token : "") + " allows") + ", not " + value);
        return value;
    }

    /** A value as its type of state says it: a yes or a no as "true" or "false", the rest as they are. */
    static _said(type, value) { return type.flag ? String(value) : value; }

    /** The state type a listener may be set on: one of its states', for the type or a named change of it; else none. */
    static _typeOf(Leaf, on) {
        var axes = Leaf.AXES || {};
        for (var a in axes) {
            var s = axes[a].state;
            if (s && (s === on || s.changes.indexOf(on) >= 0)) return s;
        }
        return null;
    }

    /** Whether the leaf offers the event: one of its EVENTS - a change of a state is heard by onChange. */
    static _offers(Leaf, type) { return (Leaf.EVENTS || []).indexOf(type) >= 0; }
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

/**
 * A change of one state of one component (RFC 0069, amended 21 Oct): its type, its component, the value before and the
 * value after - two different values, as the component has them - and the named changes of its type it is. No cause.
 */
class StateChange {
    constructor(type, source, before, after, named) {
        this.type = type;
        this.source = source;
        this.before = before;
        this.after = after;
        this.named = Object.freeze(named.slice());
        Object.freeze(this);
    }
}
