// =============================================================================
// Link — a way to somewhere else: its words, where it goes, and its clicks. A
// simple component (SimpleComponent) on an <a>, in the link's look:
//
//   var l = new Link(branch.elementMaker("link"), new FixedLink(), me.place(FixedLink));
//   l.onChange(StateClicks, listener)   a click on it: its clicks counted up, and the
//                           way followed, as the browser follows one
//   l.clicks                how many times it was clicked, from none
//   l.text = "…"            its words
//   l.to                    where it goes, set through the href manager. Given
//                           once it stays: a way is changed, never taken away
//   l.size = 0.5            −1 the smallest … 1 the biggest; 0 regular
//
// No way given, it goes nowhere: an <a> with no href, words alone.
// On the style manager (RFC 0071): its module stamped with its header (D19).
// =============================================================================

class Link extends SimpleComponent {
    static TAG = "a";
    static AXES = Object.freeze({
        clicks: Object.freeze({ rest: 0, state: StateClicks })   // counted up by its behaviour, never set: no class
    });
    static DEGREES = Object.freeze([ExtentSize]);
    static BEHAVIOR = LinkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedLink, place ?? manageLink); }

    get text() { return this.el.textContent; }
    set text(words) { this.el.textContent = words == null ? "" : String(words); }

    get to() { return this._to == null ? null : this._to; }
    set to(way) {
        if (way == null) {
            if (this._to != null) throw new Error("[" + this.constructor.name + "] a way, once given, is changed, never taken away");
            return;
        }
        this._to = String(way);
        HrefManagerInstance.set(this.el, this._to);
    }

    get size() { return this._degree(ExtentSize); }
    set size(s) { this._setDegree(ExtentSize, s); }

    /** How many times it was clicked, from none: a click is its going up. */
    get clicks() { return this._get("clicks"); }

    /** A click, as its behaviour hears it: its clicks counted up. */
    _clicked() { this._change("clicks", this.clicks + 1); }
}

