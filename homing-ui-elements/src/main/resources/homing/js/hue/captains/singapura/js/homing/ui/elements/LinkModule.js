// =============================================================================
// Link — a way to somewhere else: its words, where it goes, and a click. A
// simple component (SimpleComponent) on an <a>, in the link's look:
//
//   var l = new Link(branch.elementMaker("link"), new FixedLink(), me.place(FixedLink));
//   l.setOnClick(handler)   a click on it: handler.handle(ComponentEvent) - the way
//                           is followed unless the handler prevents the raw event
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
    static EVENTS = Object.freeze([EventClick]);
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

    /** A click on it: the way is followed unless the handler prevents the raw event. */
    setOnClick(handler) { this._setOn(EventClick, handler); }

    /** A click, as its behaviour hears it. */
    _clicked(ev) { this._fire(EventClick, ev); }
}

