// =============================================================================
// StatusFooter — a bar of status along the foot of what it ends (owner, 23 Oct):
// read-only. What it shows is its caller's status widget's - icons, badges,
// words: the state of things, at a glance - mounted in its room; an unseen cover
// lies over it, so the hand lands on the cover, never on what is beneath: nothing
// on it is pressed, hovered or selected, and it is still read aloud. A container
// only: no state of its own.
//
//   var s = new StatusFooter(branch.createBranch("status"), new FixedStatusFooter(), place?);
//   s.mount(root, party?)   a status widget's root in its room, its branch of the page grafted   s.unmount()
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _statusOwner = Object.freeze({ toString: () => "statusFooter" });

class StatusFooter extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({});
    static BEHAVIOR = MarkBehavior;   // read, never pressed: its cover takes the hand

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[StatusFooter] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_statusOwner);
        super(branch.elementMaker("footer"), fixed, FixedStatusFooter, place ?? manageStatusFooter);
        this.branch = branch;
        css.addClass(this.el, el_status_footer);
        this._status = branch.createElement("status", "div");
        this._room(ROOM_STATUS, this._status);
        css.addClass(this._status, el_status_room);
        this._cover = branch.createElement("cover", "div");
        css.addClass(this._cover, el_status_cover);
        this.el.append(this._status, this._cover);
        this._root = null;
        this._grafted = false;
        this.root = this.el;
    }

    /** What it shows, mounted by its caller: a status widget's root in its room - the widget's own - its branch of the page grafted. One at a time. */
    mount(root, party) {
        if (this._root !== null) throw new Error("[StatusFooter] shows something already: unmount it first");
        if (!root || root.nodeType !== 1) throw new TypeError("[StatusFooter] what it shows is a widget's root, an element");
        if (party != null) { this.branch.graft("status", party); this._grafted = true; }
        this._root = root;
        this._status.appendChild(root);
    }

    /** What it shows let go: still the widget's. */
    unmount() {
        if (this._root === null) return;
        this._status.removeChild(this._root);
        if (this._grafted) this.branch.detach("status");
        this._root = null;
        this._grafted = false;
    }

    /** It and everything on its branch go together; what it shows is its caller's: let go, first. */
    dispose() { this.unmount(); this.branch.dissolve(); }
}
