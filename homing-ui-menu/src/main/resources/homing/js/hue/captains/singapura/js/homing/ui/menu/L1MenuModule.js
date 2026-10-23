// =============================================================================
// L1Menu — the rows a row of a context menu leads to: level 1 (owner, 23 Oct),
// only ever a part, shown beside its row while it is open. Its own rows' rows, a
// level-2 menu each: the rest is the levels' base.
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

class L1Menu extends Menu {
    constructor(branch, fixed, place) {
        if (place == null) throw new TypeError("[L1Menu] only ever a part: its menu places it, beside the row it is the rows of");
        super(branch, fixed, FixedL1Menu, place, [L2Menu, FixedL2Menu]);
    }
}
