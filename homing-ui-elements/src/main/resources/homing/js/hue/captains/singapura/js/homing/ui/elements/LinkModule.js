// =============================================================================
// Link — a way to somewhere else: its words, where it goes, and a click. A
// simple component (SimpleComponent) on an <a>, in the link's look:
//
//   var l = new Link(LinkFactory.create(branch, "open"), { text: "Open the report", to: "/reports/q3" }, ROLE.OPEN);
//   l.setOnClick(handler)   a click on it: handler.handle(ComponentEvent) - the way
//                           is followed unless the handler prevents the raw event
//   l.text = "…"            its words
//   l.to                    where it goes, set through the href manager. Given
//                           once it stays: a way is changed, never taken away
//   l.size = 0.5            −1 the smallest … 1 the biggest; 0 regular
//
// No way given, it goes nowhere: an <a> with no href, words alone.
// =============================================================================

class Link extends SimpleComponent {
    static LEAF = "link";
    static TAG = "a";
    static EVENTS = Object.freeze([EVENT.CLICK]);
    static DEGREES = Object.freeze(["size"]);
    static WORDS = Object.freeze(["text", "to"]);
    static BEHAVIOR = LinkBehavior;

    get text() { return this.el.textContent; }
    set text(words) { this._look.text(this.el, words == null ? "" : String(words)); }

    get to() { return this._to == null ? null : this._to; }
    set to(way) {
        if (way == null) {
            if (this._to != null) throw new Error("[" + this.constructor.name + "] a way, once given, is changed, never taken away");
            return;
        }
        this._to = String(way);
        HrefManagerInstance.set(this.el, this._to);
    }

    get size() { return this._degrees.size; }
    set size(s) { this._degree("size", s); }

    /** A click on it: the way is followed unless the handler prevents the raw event. */
    setOnClick(handler) { this._setOn(EVENT.CLICK, handler); }
}

/** Its companion: makes the <a> it is realized on, on its owner's branch, under its owner's name. */
const LinkFactory = new SimpleComponentFactory(Link);
