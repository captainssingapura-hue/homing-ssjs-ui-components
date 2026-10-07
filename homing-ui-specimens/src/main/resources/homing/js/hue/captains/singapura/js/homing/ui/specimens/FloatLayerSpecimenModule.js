// =============================================================================
// FloatLayerSpecimen — the house's float layer in action: the floor floating
// panes are stacked on, keeping their order. Each opened pane comes in front;
// a press on one behind raises it; Escape - the keys handed on to the layer -
// closes the one in front, as its cross does, and the next comes forward.
// Every opening, raise, move, resize and close is said.
//
//   new FloatLayerSpecimen(branch, { leaf, say })   leaf: "float-layer"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _floatLayerSpecimen = Object.freeze({ toString: () => "floatLayerSpecimen" });

/** What a pane on the layer shows: a note, by the base's contract for a widget. */
class _FloatNote {
    constructor(branch, params) {
        branch.activate(_floatLayerSpecimen);
        this.root = branch.createElement("note", "p");
        css.addClass(this.root, sp_text);
        this.root.textContent = "Note " + params.n + ": press a note behind to raise it; Escape closes the one in front.";
    }
    dispose() {}
}

class FloatLayerSpecimen {
    constructor(branch, params) {
        var p = params || {}, self = this;
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_floatLayerSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var host = branch.createElement("host", "div");
        css.addClass(host, sp_host);
        root.appendChild(host);
        var titles = {};
        this._layer = new FloatLayer(branch.createBranch("layer"), {
            host: host,
            onEvent: function (ev) {
                if (ev.kind === "Opened") { titles[ev.id] = ev.title; say(ev.title + " opened"); }
                else if (ev.kind === "Raised") say((titles[ev.id] || ev.id) + " raised: in front");
                else if (ev.kind === "Moved") say((titles[ev.id] || ev.id) + " moved to " + ev.x + ", " + ev.y);
                else if (ev.kind === "Resized") say((titles[ev.id] || ev.id) + " resized to " + ev.w + " by " + ev.h);
                else if (ev.kind === "Closed") say((titles[ev.id] || ev.id) + " closed");
            }
        });

        var opened = 0;
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        var open = new ButtonBuilder().label("Open a note").onClick(function () {
            opened++;
            self._layer.open({ title: "Note " + opened, w: 200, h: 100, widget: _FloatNote, params: { n: opened } });
        });
        row.appendChild(open.build(branch.createElement("open", open.tag)).el);
        var close = new ButtonBuilder().label("Close the one in front").plain().onClick(function () {
            var front = self._layer.active();
            if (front == null) say("nothing is open");
            else self._layer.close(front);
        });
        row.appendChild(close.build(branch.createElement("close", close.tag)).el);
        root.appendChild(row);

        this.root = root;
        say("open notes: they cascade and stack - press one behind to raise it, Escape closes the one in front");
        this._layer.open({ title: "Note " + (++opened), w: 200, h: 100, widget: _FloatNote, params: { n: opened } });
        this._layer.open({ title: "Note " + (++opened), w: 200, h: 100, widget: _FloatNote, params: { n: opened } });
    }

    /** A layer varies along no axis. */
    extent(axis, v) {}

    /** The keys its holder hands on: to the layer - the front pane's widget, then Escape. */
    key(ev) { return this._layer.key(ev); }

    dispose() {
        try { this._layer.dispose(); } catch (e) {}
        try { this.branch.dissolve(); } catch (e) {}
    }
}
