// =============================================================================
// Crumb — one step of a trail back: its words, where it goes, and whether it is
// the page itself. A simple component (SimpleComponent) on an <a>, in the
// crumb's look:
//
//   var c = new Crumb(branch.elementMaker("crumb"), new FixedCrumb(), me.place(FixedCrumb));
//   c.setOnClick(handler)          a click on it: handler.handle(ComponentEvent)
//   c.setOnCurrentChanged(handler) it became the page, or stopped being it: handler.handle(StateChange)
//   c.text = "…"                   its words
//   c.to                           where it goes, through the href manager; given once, it stays
//   c.current = true               the step that is this page - aria-current="page",
//                                  which its look's current treatment is shown by
//
// A step with no way - the page itself, or a heading of the trail - is an <a>
// with no href: words alone, never followed.
// On the style manager (RFC 0071): its module stamped with its header (D19), its states set through its reference.
// =============================================================================

class Crumb extends SimpleComponent {
    static TAG = "a";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "page"); else el.removeAttribute("aria-current"); },
            state: StateCurrent
        })
    });
    static EVENTS = Object.freeze([EventClick]);
    static BEHAVIOR = LinkBehavior;

    constructor(make, fixed, place) { super(make, fixed, FixedCrumb, place ?? manageCrumb); }

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

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** A click on it: the way is followed unless the handler prevents the raw event. */
    setOnClick(handler) { this._setOn(EventClick, handler); }

    /** A click, as its behaviour hears it. */
    _clicked(ev) { this._fire(EventClick, ev); }

    /** It became the page, or stopped being it - by its setter, the one way a crumb's changes. */
    setOnCurrentChanged(handler) { this._setOn(StateCurrent, handler); }
}

