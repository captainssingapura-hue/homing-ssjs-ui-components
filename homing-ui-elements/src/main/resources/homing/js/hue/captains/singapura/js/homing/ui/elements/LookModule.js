// =============================================================================
// Look — the new CSS manager: a component's look worn one to one, by name.
// A look is one semantic class for each target - the casting's - and one
// treatment class for each state and target the component is treated in:
//
//     primary-button_go__color-background             the primary button as Go, its surface
//     primary-button__effect-opacity--disabled     the primary button's opacity, disabled
//
// The sheet (HouseLookSheet) is flattened: every class named here has its
// rule, the body copied from the node of the taxonomy the theme answers. So
// nothing is resolved here and no design word is worn: a class is named and
// put on. A treatment's rule holds its state's selector, so a state shows
// when its reflection is written - the component writes the reflection
// (show), and the class does the rest. Nothing changes on a theme switch:
// the names are the casting's, and the sheet is fetched again.
//
//   var look = new Look(Leaf, role)     the leaf's casting in a role of ROLE; a
//                                       functional role looks as the leaf on its own
//     .casting                          its token: primary-button_go
//     .classes                          every class it wears
//     .wear(el)  .shed(el)              put them on, take them off
//     .show(el, state)                  each axis's reflection written
//     .degree(el, name, n)              size, extent or aspect; at rest nothing written
//     .text(el, words)                  the element's words
//
// Leaf is the component's class: its token (LEAF) and its axes (AXES), each
// with the reflection its value writes. `css` - the manager underneath - is
// injected with the sheet's import, and puts the classes on.
// =============================================================================

var _LOOK_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });
var _lookHandles = new Map();   // class name → the manager's handle for it

function _lookHandle(name) {
    var h = _lookHandles.get(name);
    if (!h) { h = css.cls(name); _lookHandles.set(name, h); }
    return h;
}

class Look {
    constructor(Leaf, role) {
        if (!Leaf || !Leaf.LEAF) throw new Error("[Look] a leaf with its token is required");
        if (!role || typeof role.token !== "string") throw new Error("[Look] a role is one of ROLE, not " + role);
        this.leaf = Leaf;
        this.role = role;
        this.casting = role.token === "standalone" || role.functional ? Leaf.LEAF : Leaf.LEAF + "_" + role.token;
        var self = this;
        this.classes = Object.freeze(LOOK_TARGETS.map(function (t) { return self.casting + "__" + t; })
                .concat((TREATED[Leaf.LEAF] || []).map(function (s) { return Leaf.LEAF + "__" + s; })));
        void look_sheet;   // the sheet the classes are ruled in: imported, so it loads
    }

    wear(el) { this.classes.forEach(function (c) { css.addClass(el, _lookHandle(c)); }); }

    shed(el) { this.classes.forEach(function (c) { css.removeClass(el, _lookHandle(c)); }); }

    show(el, state) {
        var axes = this.leaf.AXES || {};
        for (var a in axes) if (typeof axes[a].reflect === "function") axes[a].reflect(el, state[a]);
    }

    degree(el, name, n) {
        if (!(name in _LOOK_REST)) throw new Error("[Look] no degree '" + name + "'; one of " + Object.keys(_LOOK_REST).join(", "));
        css[name](el, n === _LOOK_REST[name] ? null : n);
    }

    text(el, words) { el.textContent = words; }
}
