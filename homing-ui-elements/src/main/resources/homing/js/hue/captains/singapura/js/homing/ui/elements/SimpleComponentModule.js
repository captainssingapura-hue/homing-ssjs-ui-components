// =============================================================================
// SimpleComponent — the standard of a SIMPLE component: one element, which its
// caller mints with the tag the component declares and hands in. (A COMPOSED
// component takes a branch of the DOM-ops party and mints its own tree on it:
// another standard.) A simple component is three:
//
//   the component  its state - one value on each axis, kept here and nowhere
//                  else, changed only by its setters - its degrees, its role;
//   its UI         ComponentUi: the element's look, the state's reflections and
//                  the degrees - the only thing that touches its classes;
//   its behaviour  the leaf's BEHAVIOR: what the element does, its listeners. It
//                  reads the state and changes it through the component's
//                  setters; it never touches the look.
//
// An AXIS is a sum type of mutually exclusive values: enabled is true or false.
// Setting one stores the value, shows the new point - its classes swapped at
// once, its reflection written - and tells the behaviour (changed(axis, value)),
// in that order; the same value again does nothing. A UI state without a
// behavioural one means nothing, so there is no other way to change either.
//
// What a leaf declares, as statics - what a generator writes later:
//   TAG       the element it takes: "button"
//   LEAF      its token in the taxonomy: "primary-button"; CASTINGS[LEAF] its castings
//   LOOK      the class handles it wears in every state
//   AXES      { axis: { values, rest, looks: { value: [handles] }, reflect?(el, value) } }
//   DEGREES   the degrees it varies by: "size", "extent", "aspect"
//   WORDS     the words it shows: "label"
//   BEHAVIOR  its behaviour's class: new BEHAVIOR(el, component, props)
// and an accessor for each axis, degree and word it has.
//
//   new PrimaryButton(el, props, role?)
//     el      minted with PrimaryButton.TAG on the caller's branch
//     props   an axis, a degree or a word by its name; the rest are the behaviour's
//     role    one of ROLE, Standalone unless given; refused unless the leaf is cast in it
//   .el  .role
// =============================================================================

var _COMPONENT_DEGREE_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });

class SimpleComponent {
    constructor(el, props, role) {
        var Leaf = new.target, self = this;
        if (!Leaf.LEAF || !Leaf.LOOK || !Leaf.BEHAVIOR) throw new Error("[" + Leaf.name + "] is no leaf: a simple component is made as one of its leaves");
        if (!el || String(el.tagName).toLowerCase() !== Leaf.TAG) throw new Error("[" + Leaf.name + "] wants the element minted with its tag, <" + Leaf.TAG + ">");
        var p = props || {}, axes = Leaf.AXES || {}, degrees = Leaf.DEGREES || [], words = Leaf.WORDS || [];
        this.el = el;
        this.role = SimpleComponent._cast(Leaf, role == null ? ROLE.STANDALONE : role);
        this._state = {};
        for (var a in axes) this._state[a] = a in p ? SimpleComponent._value(Leaf, a, p[a]) : axes[a].rest;
        this._degrees = {};
        this._ui = new ComponentUi(el, Leaf, this.role);
        this._ui.show(this._state);
        degrees.forEach(function (d) { self._degree(d, p[d] == null ? _COMPONENT_DEGREE_REST[d] : p[d]); });
        words.forEach(function (w) { self[w] = p[w]; });
        var rest = {};
        for (var k in p) if (!(k in axes) && degrees.indexOf(k) < 0 && words.indexOf(k) < 0) rest[k] = p[k];
        this._behavior = new Leaf.BEHAVIOR(el, this, rest);
    }

    /** The value on an axis. */
    _get(axis) {
        if (!(axis in this._state)) throw new Error("[" + this.constructor.name + "] has no axis '" + axis + "'");
        return this._state[axis];
    }

    /** A value on an axis: stored, shown, then told to the behaviour; the same value again does nothing. */
    _set(axis, value) {
        var v = SimpleComponent._value(this.constructor, axis, value);
        if (this._state[axis] === v) return;
        this._state[axis] = v;
        this._ui.show(this._state);
        if (this._behavior && typeof this._behavior.changed === "function") this._behavior.changed(axis, v);
    }

    /** A degree, clamped to −1 … 1; at rest unless it is a number. */
    _degree(name, n) {
        if ((this.constructor.DEGREES || []).indexOf(name) < 0) throw new Error("[" + this.constructor.name + "] has no degree '" + name + "'");
        var x = Math.max(-1, Math.min(1, Number(n)));
        this._degrees[name] = Number.isFinite(x) ? x : _COMPONENT_DEGREE_REST[name];
        this._ui.degree(name, this._degrees[name]);
    }

    static _value(Leaf, axis, value) {
        var a = (Leaf.AXES || {})[axis];
        if (!a) throw new Error("[" + Leaf.name + "] has no axis '" + axis + "'");
        if (a.values.indexOf(value) < 0) throw new Error("[" + Leaf.name + "] " + axis + " is one of " + a.values.join(", ") + ", not " + value);
        return value;
    }

    static _cast(Leaf, role) {
        if (!role || typeof role.token !== "string") throw new Error("[" + Leaf.name + "] a role is one of ROLE, not " + role);
        var castings = CASTINGS[Leaf.LEAF];
        if (!castings) throw new Error("[" + Leaf.name + "] '" + Leaf.LEAF + "' is no leaf of the taxonomy");
        if (castings.indexOf(role.token) < 0) throw new Error("[" + Leaf.name + "] is never cast as " + role.token + ": only as " + castings.join(", "));
        return role;
    }
}
