// =============================================================================
// Look — how a component wears its casting's style-aware classes (RFC 0067,
// appendix Two Classes per Leaf), through core's StyledLeaf. Its component and
// its role are locked in its constructor, the role passed in by its owner and
// checked there; the pair is its casting - a leaf for its class, never one of
// the taxonomy's tree, the owner never in it - and names the one class it wears:
// styled-<component> on its own, styled-<component>_<role> in a role. And the
// class of each application state its component lists, while the state holds
// (D12); the browser draws the pseudo states itself. The names are Java's
// (LEAVES); the rules the style worn's, in its sheet (HouseStyleSheet), its
// colours the palette worn's (HousePaletteSheet), each swapped on its own by the
// theme's owner. Nothing is resolved here.
//
//   var look = new Look(Leaf, role)     the casting worn: Leaf on its own (ROLE.STANDALONE,
//                                       or a role that only places it), else Leaf in its role;
//                                       refused for a pair the taxonomy never casts
//     .token                            the casting's token: primary-button_go
//     .leaf                             the casting's description
//     .wear(el)                         the leaf's class on the element
//     .show(el, state)                  each axis's reflection written, and each state the leaf lists held
//     .keys()                           its keys' marks for Keys.claimOn: its leaf and its Keyboard state
//     .degree(el, name, n)              size, extent or aspect; at rest nothing written
//     .text(el, words)                  the element's words
//
// A picture is an icon's: a component of its own, its picture its look (D52).
//
// While the house moves onto files (phase 5), a slice of the old look - wear(el,
// { only | except }) - wears nothing styled and says so once on the console,
// until its family moves and the slice is a leaf of its own (D17).
// =============================================================================

var _LOOK_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });
var _LOOK_SAID = new Set();

function _lookSay(said) {
    if (_LOOK_SAID.has(said)) return;
    _LOOK_SAID.add(said);
    if (typeof console !== "undefined" && typeof console.warn === "function") console.warn("[Look] " + said);
}

class Look {
    constructor(Leaf, role) {
        if (!Leaf || !Leaf.LEAF) throw new Error("[Look] a leaf with its token is required");
        if (!role || typeof role.token !== "string") throw new Error("[Look] a role is one of ROLE, not " + role);
        this.component = Leaf;
        this.role = role;
        this.token = role.token === "standalone" || role.functional ? Leaf.LEAF : Leaf.LEAF + "_" + role.token;
        this.leaf = LEAVES[this.token] || null;
        if (!this.leaf) throw new Error("[Look] '" + this.token + "' is no casting of the taxonomy: " + Leaf.LEAF + " is never cast as " + role.token);
        void style_sheet; void palette_sheet;   // the sheets its classes are ruled in: imported, so they load
    }

    wear(el, slice) {
        if (slice) {
            _lookSay((this.token || this.component.LEAF) + ": a slice of the old look wears nothing styled - it becomes a leaf of its own (D17)");
            return;
        }
        if (this.leaf) StyledLeaf.wear(el, this.leaf);
    }

    show(el, state) {
        var axes = this.component.AXES || {};
        for (var a in axes) {
            if (typeof axes[a].reflect === "function") axes[a].reflect(el, state[a]);
            var held = axes[a].state;
            if (this.leaf && held && Object.prototype.hasOwnProperty.call(this.leaf.states, held.token))
                StyledLeaf.hold(el, this.leaf, held.token, state[a]);
        }
    }

    /**
     * Its keys' marks, for Keys.claimOn: its leaf and the house's Keyboard state of it, which the steward holds on the
     * root it enrols while the root holds a mark (D12); refused when its casting lists no
     * Keyboard state, its marks then never to show.
     */
    keys() {
        if (!this.leaf) return null;
        if (!Object.prototype.hasOwnProperty.call(this.leaf.states, "keyboard"))
            throw new Error("[Look] " + this.token + " lists no keyboard state: the keys' marks on its root could never show");
        return Object.freeze({ leaf: this.leaf, state: "keyboard" });
    }

    degree(el, name, n) {
        if (!(name in _LOOK_REST)) throw new Error("[Look] no degree '" + name + "'; one of " + Object.keys(_LOOK_REST).join(", "));
        css[name](el, n === _LOOK_REST[name] ? null : n);
    }

    text(el, words) { el.textContent = words; }
}
