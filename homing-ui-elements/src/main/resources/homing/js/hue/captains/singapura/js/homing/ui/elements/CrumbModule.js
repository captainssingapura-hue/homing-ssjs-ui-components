// =============================================================================
// Crumb — one step of a trail back: its words, where it goes, and whether it is
// the page itself. A simple component (SimpleComponent) on an <a>, in the
// crumb's look:
//
//   var c = new Crumb(branch.elementMaker("crumb"), new FixedCrumb(), me.place(FixedCrumb));
//   c.onChange(StateClicks, listener)  a click on it: its clicks counted up
//   c.onChange(StateCurrent, listener) it became the page, or stopped being it: listener.handle(StateChange)
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
        }),
        clicks: Object.freeze({ rest: 0, state: StateClicks })   // counted up by its behaviour, never set: no class
    });
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

    /** How many times it was clicked, from none: a click is its going up. */
    get clicks() { return this._get("clicks"); }

    /** A click, as its behaviour hears it: its clicks counted up. */
    _clicked() { this._change("clicks", this.clicks + 1); }
}

