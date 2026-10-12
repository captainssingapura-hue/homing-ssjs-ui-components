// =============================================================================
// Panel — a head, a body, and whatever is mounted in it. The workspace's unit
// of work: a named region that fills what holds it — a cell of a grid, a
// pane's tab, a page — and brings nothing else. It measures nothing: what
// holds it decides how big it is, and it fills that.
//
//   new Panel(branch, { title, bare, fills, size, host }) — on a sub-branch the caller made:
//     title                 the name in the head; without one there is no head
//     bare: true            no frame and no corner: for a panel whose CONTAINER
//                           draws the lines — a cell of a grid that already
//                           draws its seams and marks the room being worked in.
//                           A sheet draws its own edge; a room's floor does not.
//     fills: true           what is mounted fills the body: no air, and no
//                           scroll of its own (a dock, a grid). Off, the body
//                           has the design's air and scrolls — for content.
//     size                  -1 to 1: every length the design gives the panel
//                           grown by its own ratio to the power of it
//     host                  appended there once made
//
//   Panel — the instance:
//     .root                 the element, appended to the host when one was given
//     .head                 the bar; null when the panel has no title
//     .controls             the slot at the end of the head: the caller appends
//                           what acts on the panel — buttons, a menu mark
//     .body                 where the caller mounts what the panel shows
//     .title(text?)         read, or set
//     .size(s)              the size, live, on the panel and its parts
//     .elevation(e)         "elevated", "sunken", or null for flat: how far off
//                           its own plane the design sits it. WHAT A REGISTER
//                           MEANS IS THE APP'S: the panel links it to nothing.
//     .highlight(on) .isHighlighted()   the current one, said in colour alone —
//                           the other axis, orthogonal to the depth, and the
//                           one a design without depth can still answer
//     .watch(component|el, onWithin)  follow what is mounted and SAY so:
//                           onWithin(true|false) as the keys come and go. It
//                           decides nothing. .isWithin() reads it. .unwatch() stops.
//     .dispose()            the panel and everything on its branch
//
// It takes no keys and claims nothing: a panel is furniture. What is mounted
// in it may be a member of the keyboard party, and the panel neither joins
// nor interferes — it only follows. The one thing it reads is `data-keys`,
// the attribute a component writes to say where the keys are: the design
// vocabulary's own contract, published for exactly this. A page that would
// rather wire it itself calls active(on) and never watches; a dock mounted on
// a flat surface, with no panel around it, neither knows nor needs one.
// =============================================================================

const _panelOwner = Object.freeze({ toString: () => "panel" });
/** Its head - the bar that names it - and its body - a section of its room: each realized inline, in its own look. */
const _PANEL_HEAD = Object.freeze({ LEAF: "panel-bar" });
const _PANEL_BODY = Object.freeze({ LEAF: "section" });
/** What a panel's own placement says, which its look leaves to it: its least size, so it fills what holds it and no more. */
const _PANEL_PLACED = Object.freeze(["size-extent"]);

class Panel {
    static LEAF = "panel";

    /** On a sub-branch the caller made, with its properties. */
    constructor(branch, props) {
        if (!branch) throw new Error("[Panel] a branch of its own is required");
        var p = props || {};
        branch.activate(_panelOwner);
        this.branch = branch;
        this._size = 0;
        this._elevation = null;
        this._highlight = false;
        this._within = false;
        this._observer = null;
        this._watched = null;

        var root = branch.createElement("panel", "section");
        css.addClass(root, el_panel);
        // its look: raised, framed as a card is - unless its container draws the lines - lifted or set in as the app says;
        // its least size its placement's, so it fills what holds it
        new Look(Panel, ROLE.STANDALONE).wear(root, { except: p.bare ? _PANEL_PLACED.concat(["shape-corner", "shape-border"]) : _PANEL_PLACED });
        this._parts = [root];

        this.head = null;
        this.controls = null;
        this._title = null;
        if (p.title != null) {
            var head = branch.createElement("head", "header");
            css.addClass(head, el_panel_head);
            new Look(_PANEL_HEAD, ROLE.HEAD).wear(head, { only: ["size-inset", "size-gap", "shape-border", "color-border"] });   // its room, and the line under it
            // its title is the house's kicker, cast as its title: placed by the panel, looking as a kicker does
            var title = new Kicker(KickerFactory.create(branch, "title"), { text: String(p.title) }, ROLE.TITLE);
            css.addClass(title.el, el_panel_title);
            head.appendChild(title.el);
            var slot = branch.createElement("controls", "div");
            css.addClass(slot, el_panel_slot);
            head.appendChild(slot);
            root.appendChild(head);
            root.setAttribute("aria-label", String(p.title));
            this.head = head;
            this.controls = slot;
            this._title = title;
            this._parts.push(head, slot);
        }

        var body = branch.createElement("body", "div");
        css.addClass(body, el_panel_body);
        if (!p.fills) {   // content: the style's room round it, and a scroll of its own; filled, the body is flush
            css.addClass(body, el_panel_body_air);
            new Look(_PANEL_BODY, ROLE.BODY).wear(body, { only: ["size-inset", "size-gap"] });
            this._parts.push(body);
        }
        root.appendChild(body);
        this.body = body;

        this.root = root;
        this.size(p.size == null ? 0 : p.size);
        if (p.host) p.host.appendChild(root);
    }

    /** The name in the head, read or set; a panel built without one has no head to name. */
    title(text) {
        if (!this._title) return null;
        if (text !== undefined) { this._title.text = String(text); this.root.setAttribute("aria-label", String(text)); }
        return this._title.text;
    }

    /** The size, on the panel and on every part it minted: the size is an element's, not inherited. */
    size(s) {
        var n = Math.max(-1, Math.min(1, Number(s)));
        this._size = Number.isFinite(n) ? n : 0;
        var v = this._size === 0 ? null : this._size;
        this._parts.forEach(function (el) { css.size(el, v); });
        if (this._title) this._title.size = this._size == null ? 0 : this._size;   // its title, by its own size
        return this;
    }

    /**
     * How far off its own plane the panel sits: "elevated", "sunken", or null
     * for flat, where the design rests it. The design says what those look
     * like, in whatever plane it uses for depth — and one with no idiom for
     * depth answers neither, leaving the panel where it is, which is its
     * right. The panel says nothing about what a register MEANS: an app links
     * its own states to one, in its own code, and may link them otherwise
     * tomorrow.
     */
    elevation(e) {
        this._elevation = e === "elevated" || e === "sunken" ? e : null;
        css.elevation(this.root, this._elevation);
        return this;
    }
    elevationOf() { return this._elevation; }

    /** The current one, said in colour alone: the axis beside the depth, and the one every design has something to say on. */
    highlight(on) {
        var want = !!on;
        if (want !== this._highlight) {
            this._highlight = want;
            if (want) this.root.setAttribute("aria-current", "true");   // said by its reflection: the look draws it
            else this.root.removeAttribute("aria-current");
        }
        return this;
    }
    isHighlighted() { return this._highlight; }

    /** Whether the keys are anywhere in what is mounted, as the last watch read it. */
    isWithin() { return this._within; }

    /**
     * Follow what is mounted in the panel: while the keys are ANYWHERE IN IT —
     * held by the thing itself, lent to a control of its own, or held by
     * something it holds in turn, a dock handing them to a tab's widget — the
     * panel is the active region. It reads one attribute, `data-keys`, which
     * is what a component writes to say where the keys are, over the mounted
     * thing and its subtree; nothing else of it is touched, and it is told
     * nothing. A component, or its element.
     *
     * THE PANEL DECIDES NOTHING BY IT: it says so, through onWithin(on), and
     * what that means — a lift, a press, a colour, nothing at all — is the
     * app's to say in its own code. A workspace that wants the region you are
     * in to come forward writes that line itself.
     */
    watch(what, onWithin) {
        var el = Panel.elementOf(what);
        if (!el) throw new Error("[Panel] watch wants what is mounted in the panel, or its element");
        this.unwatch();
        var self = this;
        this._watched = el;
        this._told = typeof onWithin === "function" ? onWithin : null;
        var inside = [];   // what has said, in the subtree, that the keys are with it; kept from the records, never looked up
        this._read = function (records) {
            if (records) records.forEach(function (r) {
                var t = r.target, v = t === el || !t.getAttribute ? null : t.getAttribute("data-keys");
                var at = inside.indexOf(t);
                if (v === "held" || v === "lent") { if (at < 0) inside.push(t); }
                else if (at >= 0) inside.splice(at, 1);
            });
            for (var i = inside.length - 1; i >= 0; i--) if (!el.contains(inside[i])) inside.splice(i, 1);   // what was taken away holds nothing
            var own = el.getAttribute("data-keys");
            var now = own === "held" || own === "lent" || inside.length > 0;
            if (now === self._within) return;
            self._within = now;
            if (self._told) self._told(now);
        };
        if (typeof MutationObserver === "function") {
            this._observer = new MutationObserver(this._read);
            this._observer.observe(el, { attributes: true, attributeFilter: ["data-keys"], subtree: true });
        }
        this._read();
        return this;
    }

    /** Stop following; the panel keeps what it says now, and a caller may still set it. */
    unwatch() {
        if (this._observer) { this._observer.disconnect(); this._observer = null; }
        this._watched = null;
        this._read = null;
        this._told = null;
        return this;
    }

    /** What a thing shows its keys on: an element as it is, else a component's root. */
    static elementOf(what) {
        if (!what) return null;
        if (what.nodeType === 1) return what;
        return what.root || what.el || null;
    }

    /** The panel and everything on its branch go together; what was mounted in the body is the mounter's to dispose. */
    dispose() {
        this.unwatch();
        if (this.root.parentNode) this.root.parentNode.removeChild(this.root);
        try { this.branch.dissolve(); } catch (e) {}
    }
}
