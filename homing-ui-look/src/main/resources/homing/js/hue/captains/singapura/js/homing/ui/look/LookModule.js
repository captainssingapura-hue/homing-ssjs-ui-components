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
// the names are the casting's, and the sheet - derived, the theme's - is
// fetched again by the theme's owner (RFC 0064, D25).
//
//   var look = new Look(Leaf, role)     the leaf's casting in a role of ROLE; a
//                                       functional role looks as the leaf on its own
//     .casting                          its token: primary-button_go
//     .classes                          every class it wears
//     .wear(el, part?)  .shed(el, part?)  put them on, take them off; with part { only: [target…] }
//                                       or { except: [target…] }, just those targets - a component drawn
//                                       on more than one element puts each target where it shows: a
//                                       knob's box the ring and the size, its face the ground and the edge
//     .show(el, state)                  each axis's reflection written
//     .degree(el, name, n)              size, extent or aspect; at rest nothing written
//     .text(el, words)                  the element's words
//     .mark(el, word)                   the element's mark: a word of the lexicon - "close" - by its
//                                       class, which the lexicon's sheet draws on the element's ::before
//                                       as the style worn writes it
//
// Leaf is the component's class: its token (LEAF) and its axes (AXES), each
// with the reflection its value writes. The look puts its classes on itself:
// they are a derived sheet's - the look's, or the lexicon's for a mark - and
// the CSS manager handles only what does not vary. `css` - the manager - is injected for the element's degrees.
// =============================================================================

var _LOOK_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });

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

    wear(el, part) { this._of(part).forEach(function (c) { el.classList.add(c); }); }

    shed(el, part) { this._of(part).forEach(function (c) { el.classList.remove(c); }); }

    /** Its classes, or those of the targets a part says: only these, or all but these - each a target of LOOK_TARGETS. */
    _of(part) {
        if (!part) return this.classes;
        var only = part.only ? new Set(part.only) : null, except = part.except ? new Set(part.except) : null;
        [].concat(part.only || [], part.except || []).forEach(function (t) {
            if (LOOK_TARGETS.indexOf(t) < 0) throw new Error("[Look] no target '" + t + "'; one of " + LOOK_TARGETS.join(", "));
        });
        return this.classes.filter(function (c) {
            var t = c.slice(c.indexOf("__") + 2), s = t.indexOf("--");
            if (s >= 0) t = t.slice(0, s);
            return (!only || only.has(t)) && (!except || !except.has(t));
        });
    }

    show(el, state) {
        var axes = this.leaf.AXES || {};
        for (var a in axes) if (typeof axes[a].reflect === "function") axes[a].reflect(el, state[a]);
    }

    degree(el, name, n) {
        if (!(name in _LOOK_REST)) throw new Error("[Look] no degree '" + name + "'; one of " + Object.keys(_LOOK_REST).join(", "));
        css[name](el, n === _LOOK_REST[name] ? null : n);
    }

    text(el, words) { el.textContent = words; }

    mark(el, word) {
        if (!Object.prototype.hasOwnProperty.call(ICONS, word)) throw new Error("[Look] no word '" + word + "' in the lexicon; one of " + Object.keys(ICONS).join(", "));
        el.classList.add(ICONS[word]);
    }
}
