// =============================================================================
// Controls — the house's controllability, asked: WHAT of a component can be
// controlled from outside it, and HOW. Pure: no DOM, no state; what it knows
// is the generated catalogue - every option, how it is controlled, the method
// that applies it; every control type; every leaf's type.
//
//   Controls.option(name)          { category, means, rest, method, label }, or null
//                                  means: "extent" | "switch" | "action" | "question"
//   Controls.typeOf(leaf)          a leaf's control type, by its token; null for one the house lacks
//   Controls.optionsOf(type)       the options a type takes, in order
//   Controls.forLeaf(leaf, axes)   what a leaf is controlled by: a degree for each axis it varies
//                                  along - in the catalogue's order - then its type's options
//   Controls.apply(target, name, value)
//                                  the option applied by the method it names: target[method](v) for a
//                                  degree, (on) for a switch, () for an action or a question. A
//                                  question's answer is returned; otherwise true when applied, false
//                                  when the target has no such method
// =============================================================================

class Controls {

    static option(name) {
        return Object.prototype.hasOwnProperty.call(CONTROL_OPTIONS, name) ? CONTROL_OPTIONS[name] : null;
    }

    static typeOf(leaf) {
        return Object.prototype.hasOwnProperty.call(CONTROL_TYPE_OF, leaf) ? CONTROL_TYPE_OF[leaf] : null;
    }

    static optionsOf(type) {
        return Object.prototype.hasOwnProperty.call(CONTROL_TYPES, type) ? CONTROL_TYPES[type].slice() : [];
    }

    static forLeaf(leaf, axes) {
        var varies = axes || [];
        var degrees = Object.keys(CONTROL_OPTIONS).filter(function (n) { return CONTROL_OPTIONS[n].means === "extent" && varies.indexOf(n) >= 0; });
        return degrees.concat(Controls.optionsOf(Controls.typeOf(leaf)));
    }

    static apply(target, name, value) {
        var o = Controls.option(name);
        if (!o) throw new Error("[Controls] no option '" + name + "' in the catalogue");
        var fn = target ? target[o.method] : null;
        if (typeof fn !== "function") return o.means === "question" ? null : false;
        if (o.means === "extent") { fn.call(target, Number(value)); return true; }
        if (o.means === "switch") { fn.call(target, !!value); return true; }
        var answer = fn.call(target);
        return o.means === "question" ? (answer == null ? null : String(answer)) : true;
    }
}
