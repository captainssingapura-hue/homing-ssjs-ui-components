// =============================================================================
// PreferenceField — what every setting's widget has in common. A widget: the
// setting's widget makes a sub-branch for it, constructs it, and wears its
// root as its own.
//
//   new PreferenceField(branch, params)
//
//   params   { name, label, summary?, default?, kicker? }   name is the steward's name;
//                                                           kicker what it is filed under,
//                                                           its name unless said
//   the instance
//            root          the element its holder wears as its own root
//            hold(el)      the holder's control, put under the title, above the note
//            value()       the value in force: address, then store, then default
//            set(value)    a pick: remember(name, value); everyone follows, this included
//            dispose()     stops following the store
//
// The note under the control says where the value came from: pinned by the
// address, chosen here, or the site's default. The reset button forgets the
// pick and is off when there is nothing to forget. Nothing here is a
// channel: the steward is the party, and the field is one of its members -
// as is the widget that holds it, which follows the store to draw its
// control.
// =============================================================================

const _fieldOwner = Object.freeze({ toString: () => "preferenceField" });

/** The field's reset pressed: its setting goes back to the site's default. */
class PreferenceFieldReset {
    constructor(field) { this._field = field; }
    canHandle(type) { return type === EVENT.CLICK; }
    handle() { PreferenceStewardInstance.forget(this._field._name); }
}

class PreferenceField {
    constructor(branch, params) {
        if (!branch) throw new Error("[PreferenceField] a branch of its own is required");
        if (!params || !params.name) throw new Error("[PreferenceField] params.name is required");
        var self = this;
        branch.activate(_fieldOwner);
        this._name = params.name;
        this._fallback = params.default == null ? null : String(params.default);

        var root = branch.createElement("field", "div");
        // its texts are the house's, each cast as what it is to the field: placed by the field, looking as they are
        var kicker = new Kicker(KickerFactory.create(branch, "kicker"), { text: params.kicker || params.name }, ROLE.CATEGORY);
        css.addClass(kicker.el, pv_kicker);
        root.appendChild(kicker.el);
        var title = new Heading4(Heading4Factory.create(branch, "title"), { text: params.label || params.name }, ROLE.TITLE);
        css.addClass(title.el, pv_title);
        root.appendChild(title.el);
        if (params.summary) {
            var summary = new Lede(LedeFactory.create(branch, "summary"), { text: params.summary }, ROLE.SUMMARY);
            css.addClass(summary.el, pv_summary);
            root.appendChild(summary.el);
        }
        var body = branch.createElement("body", "div");
        root.appendChild(body);
        this._note = new Caption(CaptionFactory.create(branch, "note"), {}, ROLE.NOTE);
        css.addClass(this._note.el, pv_note);
        root.appendChild(this._note.el);
        var actions = branch.createElement("actions", "div");
        css.addClass(actions, pv_actions);
        this._reset = new PlainButton(PlainButtonFactory.create(branch, "reset"), { label: "Use the site's default" }, ROLE.RESET);
        this._reset.setOnClick(new PreferenceFieldReset(this));
        actions.appendChild(this._reset.el);
        root.appendChild(actions);
        this.root = root;
        this._body = body;

        this._stop = PreferenceViewInstance.onChange(function () { self._draw(); });
        this._draw();
    }

    /** The holder's control, in the field's body: under the title, above the note. */
    hold(el) { this._body.appendChild(el); }

    value() { return PreferenceViewInstance.resolve(this._name, this._fallback); }

    set(v) { PreferenceStewardInstance.remember(this._name, v); }

    _draw() {
        var pinned = PreferenceViewInstance.override(this._name);
        var stored = PreferenceViewInstance.preferred(this._name);
        this._note.text = pinned ? "Pinned by this page's address: " + pinned + ". A pick here is kept, but this page shows the address's."
                               : stored ? "Chosen: " + stored + "."
                               : "The site's default" + (this._fallback === null ? "." : ": " + this._fallback + ".");
        this._reset.enabled = !!stored;
    }

    dispose() { this._stop(); }
}
