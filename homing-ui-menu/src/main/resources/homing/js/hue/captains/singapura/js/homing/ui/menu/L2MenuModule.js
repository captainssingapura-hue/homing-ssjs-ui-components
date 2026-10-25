// =============================================================================
// L2Menu — the rows a row of a level-1 menu leads to: level 2 (owner, 23 Oct),
// the last, only ever a part, shown beside its row while it is open. Its rows open
// none: the rest is the levels' base.
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

class L2Menu extends Menu {
    constructor(branch, fixed, place) {
        if (place == null) throw new TypeError("[L2Menu] only ever a part: its menu places it, beside the row it is the rows of");
        super(branch, fixed, FixedL2Menu, place, null);
    }
}
