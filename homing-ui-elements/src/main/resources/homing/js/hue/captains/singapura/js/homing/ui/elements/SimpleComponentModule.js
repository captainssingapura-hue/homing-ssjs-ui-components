// =============================================================================
// SimpleComponent — the standard of a SIMPLE component (Implementing Components,
// the guide): one element, which it owns no more than a value owns anything. It
// makes the element itself, of its own tag, with the single-use maker its owner
// hands it - branch.elementMaker(name) - so its owner never holds a bare element;
// its owner places it. (A COMPOSED component takes a branch of the DOM-ops party
// and mints its own tree on it: another standard.) A simple component is three:
//
//   the component  its state - one value on each axis, kept here and nowhere
//                  else, changed only by its setters - its degrees, its role,
//                  and the handlers set on its events;
//   its look       Look: its leaf's style-aware class, a state's class while it
//                  holds, the reflections and the degrees - the only thing
//                  that touches its classes;
//   its behaviour  the leaf's BEHAVIOR: what the element does, its listeners. It
//                  reads the state, changes it through the component, and has
//                  the component fire its events; it never touches the look.
//
// An AXIS is a sum type of mutually exclusive values: enabled is true or false.
// An axis that is one of the house's states names it (state: STATE.ENABLED), and
// its change is an event the component offers by having it. Setting one stores
// the value, writes its reflection and holds its state's class (RFC 0067, D12)
// - tells the behaviour (changed(axis, value)), and fires
// the change to its handler, if one is set, with the previous value: from a
// setter with no DOM event, from the behaviour with the one that made it. The
// same value again does nothing.
//
// Its EVENTS are the others it offers - EVENT.CLICK - each dressed from the raw
// DOM event into a ComponentEvent that carries it. A handler is set by an
// explicit setter per event (setOnClick, setOnEnabledChanged): it is asked
// canHandle(type) first, refused unless it answers true, refused if it is a
// closure - a function, or a bare object of functions - and refused for an event
// that has one already: zero or one per event, and once for good. It is called
// with handle(event).
//
// What a leaf declares, as statics:
//   TAG       the element it takes: "button"
//   LEAF      its token in the taxonomy: "primary-button"; CASTINGS[LEAF] its castings
//   AXES      { axis: { values, rest, reflect?(el, value), state? } }
//   EVENTS    the events it offers besides its states' changes: [EVENT.CLICK]
//   DEGREES   the degrees it varies by: "size", "extent", "aspect"
//   WORDS     the words it shows: "label"
//   PARTS     the parts it is handed, made by its owner: "icon" - its own to hold
//   BEHAVIOR  its behaviour's class: new BEHAVIOR(el, component)
// an accessor for each axis, degree and word, a setter for each event, and its
// companion beside it: const PrimaryButtonFactory = new SimpleComponentFactory(PrimaryButton).
//
//   var b = new PrimaryButton(branch.elementMaker("save"), props, role?)
//     make    the maker its owner's branch issues: it makes the <TAG>, once. An
//             element its companion made is still taken, the old way, until every
//             owner hands a maker; a widget may not (widget-draws-only-components)
//     props   an axis, a degree, a word or a part by its name, and nothing else
//     role    one of ROLE, Standalone unless given, passed in by its owner; refused unless
//             the leaf is cast in it. The leaf and the role, locked here, are its casting,
//             whose one class it wears (RFC 0067, appendix Two Classes per Leaf)
//   .el  .role
// =============================================================================

var _COMPONENT_DEGREE_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });

class SimpleComponent {
    constructor(make, props, role) {
        var Leaf = new.target, self = this;
        if (!Leaf.LEAF || !Leaf.BEHAVIOR) throw new Error("[" + Leaf.name + "] is no leaf: a simple component is made as one of its leaves");
        var el = make;   // an element its companion made: the old way, until every owner hands a maker
        if (typeof make === "function") {
            if (!DomOpsParty.isElementMaker(make))
                throw new Error("[" + Leaf.name + "] takes the maker its owner's branch issues - branch.elementMaker(name) - and no other function");
            el = make(Leaf.TAG);
        }
        if (!el || String(el.tagName).toLowerCase() !== Leaf.TAG) throw new Error("[" + Leaf.name + "] wants the maker its owner's branch issues - branch.elementMaker(name) - and makes its own <" + Leaf.TAG + ">");
        var p = props || {}, axes = Leaf.AXES || {}, degrees = Leaf.DEGREES || [], words = Leaf.WORDS || [], parts = Leaf.PARTS || [];
        for (var k in p) if (!(k in axes) && degrees.indexOf(k) < 0 && words.indexOf(k) < 0 && parts.indexOf(k) < 0)
            throw new Error("[" + Leaf.name + "] has no axis, degree, word or part '" + k + "'"
                + (typeof p[k] === "function" ? ": a handler is set by its setter, never handed in" : ""));
        this.el = el;
        this.role = SimpleComponent._cast(Leaf, role == null ? ROLE.STANDALONE : role);
        this._state = {};
        for (var a in axes) this._state[a] = a in p ? SimpleComponent._value(Leaf, a, p[a]) : axes[a].rest;
        this._handlers = new Map();
        this._degrees = {};
        this._look = new Look(Leaf, this.role);
        this._look.wear(el);
        this._look.show(el, this._state);
        degrees.forEach(function (d) { self._degree(d, p[d] == null ? _COMPONENT_DEGREE_REST[d] : p[d]); });
        words.forEach(function (w) { self[w] = p[w]; });
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
     * A value on an axis: stored, its reflection written, told to the behaviour, and - for a state,
     * with a handler set for its change - fired with the previous value and the DOM event that made
     * it, if one did. The same value again does nothing.
     */
    _change(axis, value, raw) {
        var v = SimpleComponent._value(this.constructor, axis, value), was = this._state[axis];
        if (was === v) return;
        this._state[axis] = v;
        this._look.show(this.el, this._state);
        if (this._behavior && typeof this._behavior.changed === "function") this._behavior.changed(axis, v);
        var state = this.constructor.AXES[axis].state;
        if (state && this._handlers.has(state.changed)) this._handlers.get(state.changed).handle(new StateChange(state.changed, this, raw, was));
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
        var Leaf = this.constructor;
        if (!handler || typeof handler.canHandle !== "function" || handler.canHandle(type) !== true)
            throw new Error("[" + Leaf.name + "] a handler says it handles " + type + ": canHandle(" + type + ") answers true");
        if (typeof handler === "function" || Object.getPrototypeOf(handler) === Object.prototype || Object.getPrototypeOf(handler) === null)
            throw new Error("[" + Leaf.name + "] a handler is an object of a class declared for it, never a closure");
        if (typeof handler.handle !== "function") throw new Error("[" + Leaf.name + "] a handler handles: handle(event)");
        if (!SimpleComponent._offers(Leaf, type)) throw new Error("[" + Leaf.name + "] offers no " + type);
        if (this._handlers.has(type)) throw new Error("[" + Leaf.name + "] has its handler for " + type + " already: set once, it stays");
        this._handlers.set(type, handler);
    }

    /** A degree, clamped to −1 … 1; at rest unless it is a number. */
    _degree(name, n) {
        if ((this.constructor.DEGREES || []).indexOf(name) < 0) throw new Error("[" + this.constructor.name + "] has no degree '" + name + "'");
        var x = Math.max(-1, Math.min(1, Number(n)));
        this._degrees[name] = Number.isFinite(x) ? x : _COMPONENT_DEGREE_REST[name];
        this._look.degree(this.el, name, this._degrees[name]);
    }

    static _value(Leaf, axis, value) {
        var a = (Leaf.AXES || {})[axis];
        if (!a) throw new Error("[" + Leaf.name + "] has no axis '" + axis + "'");
        if (a.values.indexOf(value) < 0) throw new Error("[" + Leaf.name + "] " + axis + " is one of " + a.values.join(", ") + ", not " + value);
        return value;
    }

    /** Whether the leaf offers the event: one of its EVENTS, or the change of one of its states. */
    static _offers(Leaf, type) {
        if ((Leaf.EVENTS || []).indexOf(type) >= 0) return true;
        var axes = Leaf.AXES || {};
        for (var a in axes) if (axes[a].state && axes[a].state.changed === type) return true;
        return false;
    }

    static _cast(Leaf, role) {
        if (!role || typeof role.token !== "string") throw new Error("[" + Leaf.name + "] a role is one of ROLE, not " + role);
        var castings = CASTINGS[Leaf.LEAF];
        if (!castings) throw new Error("[" + Leaf.name + "] '" + Leaf.LEAF + "' is no leaf of the taxonomy");
        if (castings.indexOf(role.token) < 0) throw new Error("[" + Leaf.name + "] is never cast as " + role.token + ": only as " + castings.join(", "));
        return role;
    }
}

/**
 * A simple component's companion: the factory that makes the element it is realized on, of the
 * type it takes and nothing else, on the branch and under the name its owner gives. One per leaf,
 * exported beside it, named for it: PlainButtonFactory.
 */
class SimpleComponentFactory {
    constructor(Leaf) {
        if (!Leaf || typeof Leaf.TAG !== "string" || !Leaf.LEAF) throw new Error("[SimpleComponentFactory] a companion is a leaf's, and makes its TAG");
        this._tag = Leaf.TAG;
        Object.freeze(this);
    }

    /** The type of element it makes. */
    get tag() { return this._tag; }

    /** An element for the component, minted on the owner's branch under the owner's name: the old way - an owner hands a maker now (branch.elementMaker). */
    create(branch, name) { return branch.createElement(name, this._tag); }
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

/** A state's change: a ComponentEvent carrying the state's previous value. What it became is the component's attribute. */
class StateChange extends ComponentEvent {
    constructor(type, source, raw, previous) {
        super(type, source, raw);
        this.previous = previous;
        Object.freeze(this);
    }
}
