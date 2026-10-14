// =============================================================================
// Look — how a component wears its leaf's style-aware classes (RFC 0067,
// appendix Two Classes per Leaf), through core's StyledLeaf: its leaf's class -
// its own on its own, the part's when it plays one, which its owner names (D10)
// - and the class of each application state its leaf lists, while the state
// holds (D12). The browser draws the pseudo states itself. The names are the
// leaves' as Java generates them (LEAVES); the rules are the style worn's, in
// its sheet (HouseStyleSheet), its colours the palette worn's (HousePaletteSheet),
// each swapped on its own by the theme's owner. Nothing is resolved here.
//
//   var look = new Look(Leaf, role, Owner)   the leaf worn: Leaf's own on its own
//                                            (ROLE.STANDALONE), the part Owner
//                                            names - Owner.LEAF + "-" + role.token
//                                            - when it plays one
//     .token                            the leaf's token, or null while no owner names the part it plays
//     .leaf                             the leaf's description, or null
//     .wear(el)                         the leaf's class on the element
//     .show(el, state)                  each axis's reflection written, and each state the leaf lists held
//     .degree(el, name, n)              size, extent or aspect; at rest nothing written
//     .text(el, words)                  the element's words
//     .mark(el, word)                   the element's mark: a word of the lexicon - "close" - by its
//                                       class, which the lexicon's sheet draws on the element's ::before
//                                       as the style worn writes it
//
// While the house moves onto files (phase 5), two things wear nothing styled and
// say so once on the console, each until its family moves: a part played with no
// owner named - an owner hands its own Leaf as the third argument - and a slice of
// the old look, wear(el, { only | except }), each slice now a leaf of its own (D17).
// =============================================================================

var _LOOK_REST = Object.freeze({ size: 0, extent: 1, aspect: 0 });
var _LOOK_SAID = new Set();

function _lookSay(said) {
    if (_LOOK_SAID.has(said)) return;
    _LOOK_SAID.add(said);
    if (typeof console !== "undefined" && typeof console.warn === "function") console.warn("[Look] " + said);
}

class Look {
    constructor(Leaf, role, Owner) {
        if (!Leaf || !Leaf.LEAF) throw new Error("[Look] a leaf with its token is required");
        if (!role || typeof role.token !== "string") throw new Error("[Look] a role is one of ROLE, not " + role);
        this.component = Leaf;
        this.role = role;
        if (role.token === "standalone") this.token = Leaf.LEAF;
        else if (Owner && Owner.LEAF) this.token = Owner.LEAF + "-" + role.token;
        else if (role.functional) this.token = Leaf.LEAF;
        else {
            this.token = null;
            _lookSay(Leaf.LEAF + " plays " + role.token + " for an owner not named: it wears nothing styled until its owner hands itself (D10)");
        }
        this.leaf = this.token == null ? null : LEAVES[this.token] || null;
        if (this.token != null && !this.leaf) throw new Error("[Look] '" + this.token + "' is no leaf of the taxonomy");
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

    degree(el, name, n) {
        if (!(name in _LOOK_REST)) throw new Error("[Look] no degree '" + name + "'; one of " + Object.keys(_LOOK_REST).join(", "));
        css[name](el, n === _LOOK_REST[name] ? null : n);
    }

    text(el, words) { el.textContent = words; }

    mark(el, word) {
        if (!Object.prototype.hasOwnProperty.call(ICONS, word)) throw new Error("[Look] no word '" + word + "' in the lexicon; one of " + Object.keys(ICONS).join(", "));
        css.addClass(el, css.cls(ICONS[word]));
    }
}
