// =============================================================================
// ComponentUi — the standard UI of a component: everything that decides how its
// element looks, and nothing else. A component is three - its state (the
// component itself), its UI (this) and its behaviour (its leaf's) - and this is
// the only one of them that touches the element's classes, the reflections of
// its state and its degrees. It is made for one casting, the leaf and the role,
// and keeps it: what css.for(component, role) returns once the manager has it.
//
//   var ui = new ComponentUi(el, Leaf, role)
//     .show(state)       the state point worn: the leaf's look, then each axis's
//                        look for its value - what the last point wore and this
//                        one does not comes off, the rest is put on - and each
//                        axis's reflection written
//     .degree(name, n)   size, extent or aspect: the number the design scales by;
//                        at rest it writes nothing
//     .text(words)       the element's words
//
// Leaf is the component's class, whose statics say what it is (SimpleComponent:
// LOOK, AXES). A look is a list of class handles, put on and taken off through
// the manager; every handle of a point is put on again after the swap, so a
// token two handles share stays while either is worn.
// =============================================================================

var _DEGREE_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });

class ComponentUi {
    constructor(el, Leaf, role) {
        if (!el) throw new Error("[ComponentUi] an element is required");
        if (!Leaf || !Leaf.LOOK) throw new Error("[ComponentUi] a leaf with a look is required");
        this.el = el;
        this.leaf = Leaf;
        this.role = role;
        this._worn = [];
    }

    /** The handles a state point wears: the leaf's look, then each axis's look for its value. */
    look(state) {
        var axes = this.leaf.AXES || {}, out = this.leaf.LOOK.slice();
        for (var a in axes) out = out.concat(axes[a].looks[String(state[a])] || []);
        return out;
    }

    show(state) {
        var next = this.look(state), el = this.el, axes = this.leaf.AXES || {};
        this._worn.forEach(function (h) { if (next.indexOf(h) < 0) CssClassManagerInstance.removeClass(el, h); });
        next.forEach(function (h) { CssClassManagerInstance.addClass(el, h); });
        this._worn = next;
        for (var a in axes) if (typeof axes[a].reflect === "function") axes[a].reflect(el, state[a]);
    }

    degree(name, n) {
        if (!(name in _DEGREE_REST)) throw new Error("[ComponentUi] no degree '" + name + "'; one of " + Object.keys(_DEGREE_REST).join(", "));
        CssClassManagerInstance[name](this.el, n === _DEGREE_REST[name] ? null : n);
    }

    text(words) { this.el.textContent = words; }
}
