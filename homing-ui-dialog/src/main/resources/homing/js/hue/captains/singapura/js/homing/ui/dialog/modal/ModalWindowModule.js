// =============================================================================
// ModalWindow — a window that holds the page while it is open (owner, 23 Oct):
// what was the dialog, reduced to its one purpose. Its veil, the house's scrim,
// over the whole page, and its window, a floating pane, centred over it; the page
// behind inert while it is open - nothing there can be focused, pressed or read -
// and the focus given back as it closes. A true modal: it closes only when asked
// explicitly - its window's cross, passed on, counted (FloatCloseControl) - and
// its caller closes it. A press on the veil is nothing: whether a press outside
// closes something is a caller's choice, made by choosing a floater instead. What
// it shows is its caller's: a widget's root, mounted, which the widget alone
// manages - a dialog is such a widget, its content and, at its foot, the
// standard footer. Made closed; opened and closed by its caller, again and
// again. Centred, a golden fraction of the viewport, unless sized. The keys wait.
//
//   var w = new ModalWindow(branch.createBranch("rename"),
//               new FixedModalWindow(new FixedFloatingPane(new FixedTitleBar(new FixedLabel(), null, new FixedCloseButton()))), place?);
//   w.title = "Rename"   w.mount(root, party?)   w.size(w, h)   w.open()   w.close()   w.shown   w.closes
//   w.onChange(StateFloatCloseControl, listener)   its cross pressed: the count up by one
// Its own classes only place. `css` is injected with the styles import.
// =============================================================================

const _modalOwner = Object.freeze({ toString: () => "modalWindow" });
var PHI = 1.6180339887;

/** Its window's cross pressed, as it hears it: a close asked, passed on. */
class WindowCloseAsked {
    constructor(modal) { this.modal = modal; Object.freeze(this); }
    canHandle(on) { return on === StateFloatCloseControl; }
    handle() { this.modal._closeAsked(); }
}

class ModalWindow extends SimpleComponent {
    static TAG = "div";
    static AXES = Object.freeze({
        shown: Object.freeze({ values: Object.freeze([false, true]), rest: false, state: StateShown }),   // its caller's: open() and close()
        closes: Object.freeze({ rest: 0, state: StateFloatCloseControl })                               // its cross's, passed on: no class
    });
    static BEHAVIOR = MarkBehavior;   // the hand on it is its parts': the veil's, which is nothing; the window's

    constructor(branch, fixed, place) {
        if (!branch || typeof branch.elementMaker !== "function")
            throw new TypeError("[ModalWindow] a composed component takes a branch of its own - branch.createBranch(name) - and makes its parts on it");
        branch.activate(_modalOwner);
        super(branch.elementMaker("modal"), fixed, FixedModalWindow, place ?? manageModalWindow);
        this.branch = branch;
        css.addClass(this.el, mw_layer);
        this._veil = new Scrim(branch.elementMaker("veil"), new FixedScrim(), this._place(FixedScrim, RoleVeil));
        css.addClass(this._veil.el, mw_veil);
        this.el.appendChild(this._veil.el);
        this._window = new FloatingPane(branch.createBranch("window"), this.fixed.window, this._place(FixedFloatingPane, RoleWindow));
        this._window.root.setAttribute("role", "dialog");
        this._window.root.setAttribute("aria-modal", "true");
        this._window.root.setAttribute("tabindex", "-1");
        this._window.onChange(StateFloatCloseControl, new WindowCloseAsked(this));
        this._window.z = 1;
        this.el.appendChild(this._window.root);
        this._size = null;
        this._hold = null;
        this.root = this.el;
    }

    /** Its window's title. */
    get title() { return this._window.title; }
    set title(words) { this._window.title = words; }

    /** What it shows, mounted by its caller: a widget's root in its window, its branch of the page grafted. */
    mount(root, party) { this._window.mount(root, party); }
    /** What it shows let go: still the widget's. */
    unmount() { this._window.unmount(); }

    /** Its window's size, its caller's: in place of the golden fraction of the viewport, from its next opening - or now, while open. */
    size(w, h) { this._size = { w: Number(w), h: Number(h) }; if (this.shown) this._centre(); return this; }

    /** Opened, by its caller: over the page, centred, the page behind inert, the focus in its window. */
    open() {
        if (this.shown) return;
        var restoreTo = document.activeElement;
        document.body.appendChild(this.el);
        this._centre();
        this._hold = new Modality(this.el, { restoreTo: restoreTo });
        this._window.current = true;
        try { this._window.root.focus({ preventScroll: true }); } catch (e) {}
        this._set("shown", true);
    }

    /** Closed, by its caller: out of the page, the page behind let go, the focus given back. */
    close() {
        if (!this.shown) return;
        this._window.current = false;
        if (this.el.parentNode) this.el.parentNode.removeChild(this.el);
        var hold = this._hold;
        this._hold = null;
        hold.release();
        this._set("shown", false);
    }

    /** On view over the page, or not. */
    get shown() { return this._get("shown"); }

    /** How many times its close was asked, by its cross, from none. */
    get closes() { return this._get("closes"); }

    /** It and everything on its branch go together; what it shows is its caller's: let go, first. */
    dispose() { this.close(); this._window.dispose(); this.branch.dissolve(); }

    /** Its cross pressed, as it hears it: one more close asked - and nothing else. */
    _closeAsked() { this._change("closes", this.closes + 1); }

    /** Its window centred over the page it covers - its own layer, the page less any scrollbar - as big as its caller said, else a golden fraction of each axis, kept within it. */
    _centre() {
        var box = this.el.getBoundingClientRect(), vw = box.width || window.innerWidth || 1024, vh = box.height || window.innerHeight || 768, s = this._size;
        var w = s ? Math.min(s.w, vw - 48) : Math.min(980, Math.max(460, Math.round(vw / PHI)));
        var h = s ? Math.min(s.h, vh - 48) : Math.min(720, Math.max(320, Math.round(vh / PHI)));
        this._window.size(w, h);
        this._window.at(Math.max(0, Math.round((vw - w) / 2)), Math.max(0, Math.round((vh - h) / 2)));
    }
}
