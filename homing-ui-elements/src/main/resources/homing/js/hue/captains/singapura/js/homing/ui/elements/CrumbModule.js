// =============================================================================
// Crumb — one step of a trail back: its words, where it goes, and whether it is
// the page itself. A simple component (SimpleComponent) on an <a>, in the
// crumb's look:
//
//   var c = new Crumb(CrumbFactory.create(branch, "crumb-0"), { text: "Gallery", to: "/gallery" }, ROLE.STEP);
//   c.setOnClick(handler)          a click on it: handler.handle(ComponentEvent)
//   c.setOnCurrentChanged(handler) it became the page, or stopped being it: handler.handle(StateChange)
//   c.text = "…"                   its words
//   c.to                           where it goes, through the href manager; given once, it stays
//   c.current = true               the step that is this page - aria-current="page",
//                                  which its look's current treatment is shown by
//
// A step with no way - the page itself, or a heading of the trail - is an <a>
// with no href: words alone, never followed.
// =============================================================================

class Crumb extends SimpleComponent {
    static LEAF = "crumb";
    static TAG = "a";
    static AXES = Object.freeze({
        current: Object.freeze({
            values: Object.freeze([false, true]),
            rest: false,
            reflect: function (el, on) { if (on) el.setAttribute("aria-current", "page"); else el.removeAttribute("aria-current"); },
            state: STATE.CURRENT
        })
    });
    static EVENTS = Object.freeze([EVENT.CLICK]);
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

    get current() { return this._get("current"); }
    set current(on) { this._set("current", !!on); }

    /** A click on it: the way is followed unless the handler prevents the raw event. */
    setOnClick(handler) { this._setOn(EVENT.CLICK, handler); }

    /** It became the page, or stopped being it - by its setter, the one way a crumb's changes. */
    setOnCurrentChanged(handler) { this._setOn(STATE.CURRENT.changed, handler); }
}

/** Its companion: makes the <a> it is realized on, on its owner's branch, under its owner's name. */
const CrumbFactory = new SimpleComponentFactory(Crumb);
