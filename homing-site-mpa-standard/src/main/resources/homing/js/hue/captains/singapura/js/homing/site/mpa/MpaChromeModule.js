// =============================================================================
// MpaChrome — the site's chrome: a widget, with no design of its own. The
// site's bar at its head - the brand, the trail, the preferences - over the
// slot the page's app is mounted in, laid out by the chrome's own layout. It
// stands on the page's ground, which the page wears; everything it shows is a
// designed component, in the house's look.
//
//   new MpaChrome(branch, params)
//     params: what the server stamped into the page, frozen -
//       { brand: { label, home }, crumbs: [ { text, to } ], preferences: { module, view }, fill }
//       The crumbs are the trail the router wrote; the last is this page and
//       carries no link, and none means the address said nothing about position:
//       the bar shows the brand alone. `fill` is the page's: it fills the viewport.
//   .root         the chrome, for the page to attach
//   .main         the slot the app is mounted in
//   .keyboard     the page's KeyboardSteward, one per document - the manager's
//                 instance: the keyboard is the page's, not an app's; the page
//                 hands it to the app, and the bar's dialog takes it too
//   .trail        { extend(crumbs) } - crumbs past the ones the router wrote,
//                 [ { text, to? } ], the last the page: a place inside the page
//                 the server never sees, the app's to say. Returns every crumb,
//                 so the page can title the document by the last
//   .preferences  the bar's button
//
// The chrome makes no element and touches none: it makes branches, constructs
// the components and the layout, and hands each what it holds.
// =============================================================================

const _chromeOwner = Object.freeze({ toString: () => "mpaChrome" });

class MpaChrome {
    constructor(branch, params) {
        if (!branch) throw new Error("[MpaChrome] a branch of its own is required");
        var p = params || {}, self = this;
        branch.activate(_chromeOwner);
        this.keyboard = KeyboardStewardInstance;
        var layout = new ChromeLayout(branch.createBranch("layout"), { fill: !!p.fill });
        this._stamped = p.crumbs || [];
        var bar = new SiteBar(branch.createBranch("bar"), { brand: p.brand || {}, crumbs: this._stamped });
        this._trail = bar.trail;
        this.preferences = new PreferencesButton(branch.createBranch("prefs"), p, this.keyboard);
        bar.end(this.preferences);
        layout.head(bar);
        this.root = layout.root;
        this.main = layout.slot();
        this.trail = Object.freeze({ extend: function (more) { return self.extendTrail(more); } });
    }

    /** The router's crumbs, then these, drawn afresh: the last the page. Every crumb, for the page to title the document by. */
    extendTrail(more) {
        if (!Array.isArray(more)) throw new TypeError("[MpaChrome] extendTrail takes an array of crumbs, got " + JSON.stringify(more));
        var all = this._stamped.concat(more);
        this._trail.draw(all);
        return all;
    }
}
