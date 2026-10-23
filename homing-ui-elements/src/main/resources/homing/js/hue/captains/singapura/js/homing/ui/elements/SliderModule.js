// =============================================================================
// Slider — a value along a scale, named and read out: a label, the house's
// range - the browser's own slider, as each style draws it - and a readout, in
// a row (owner, 23 Oct). A composed component on the style manager (RFC 0071):
// it takes a branch of its own and makes on it its row and its three parts,
// each placed along its slot by its own reference. Where it stands is its
// range's - a degree, −1 … 1, read on its caller's own scale through a
// DegreeScale - and so is being in the hand: both passed on, its own. Its keys
// are its range's, the browser's. Its readout's words are its caller's, set as
// it hears the degree change. For typical use: no detents, no scale, no icon.
//
//   var s = new Slider(branch.createBranch("volume"), new FixedSlider(), me.place(FixedSlider));
//   s.label = "Volume"    its name, said to the browser for its range too
//   s.degree = -0.2       where it stands, −1 … 1; 0 at rest - held to its step
//   s.step = 0.1          how far it moves at once, as a degree; any, unless set
//   s.readout = "40"      its readout's words: its caller's
//   s.enabled  s.dragging  s.root  s.dispose()
//   s.onChange(StateDegree, listener)     moved: by the hand, a key or a setter
//   s.onChange(StateDragging, listener)   taken by the hand, and let go
// =============================================================================

const _sliderOwner = Object.freeze({ toString: () => "slider" });

/** Its range moved, or taken and let go: its own, passed on. */
class SliderHeard {
    constructor(slider) { this._slider = slider; Object.freeze(this); }
    canHandle(type) { return type === StateDegree || type === StateDragging; }
    handle(change) { this._slider._heard(change); }
}

class Slider extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        enabled: Object.freeze({ values: Object.freeze([true, false]), rest: true, state: StateEnabled }),
        degree: Object.freeze({ rest: 0, state: StateDegree }),   // its range's, passed on: no class
        dragging: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateDragging })   // its range's, passed on
    });
    static BEHAVIOR = MarkBehavior;   // it hears nothing itself: its range does

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[Slider] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_sliderOwner);
        super(branch.elementMaker("slider"), fixed, FixedSlider, place ?? manageSlider);
        this.branch = branch;
        css.addClass(this.el, el_slider);
        this._label = new Label(branch.elementMaker("label"), new FixedLabel(), this._place(FixedLabel));
        css.addClass(this._label.el, el_slider_label);
        this._range = new Range(branch.elementMaker("range"), new FixedRange(), this._place(FixedRange));
        css.addClass(this._range.el, el_slider_range);
        this._readout = new Readout(branch.elementMaker("readout"), new FixedReadout(), this._place(FixedReadout));
        css.addClass(this._readout.el, el_slider_readout);
        var heard = new SliderHeard(this);
        this._range.onChange(StateDegree, heard);
        this._range.onChange(StateDragging, heard);
        this.el.appendChild(this._label.el);
        this.el.appendChild(this._range.el);
        this.el.appendChild(this._readout.el);
        this.root = this.el;
    }

    /** Its name: its label's words, said to the browser for its range too. */
    get label() { return this._label.text; }
    set label(words) {
        this._label.text = words == null ? "" : String(words);
        this._range.el.setAttribute("aria-label", this._label.text);
    }

    /** Where it stands, −1 … 1: its range's. */
    get degree() { return this._get("degree"); }
    set degree(d) { this._range.degree = d; }

    /** How far it moves at once, as a degree: its range's. */
    get step() { return this._range.step; }
    set step(s) { this._range.step = s; }

    /** Its readout's words: its caller's, as it reads the degree on its own scale. */
    get readout() { return this._readout.text; }
    set readout(words) { this._readout.text = words == null ? "" : String(words); }

    /** Whether the hand holds it: its range's. */
    get dragging() { return this._get("dragging"); }

    get enabled() { return this._get("enabled"); }
    set enabled(on) { this._set("enabled", !!on); this._range.enabled = !!on; }

    /** It and its parts go together. */
    dispose() { this.branch.dissolve(); }

    /** Its range moved, or taken and let go, as its listener hears it: its own, told. */
    _heard(change) {
        if (change.type === StateDegree) this._change("degree", change.after);
        else this._change("dragging", change.after);
    }
}
