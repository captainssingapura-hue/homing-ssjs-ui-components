// =============================================================================
// SummaryCardSpecimen — the house's summary card in action: a title, a tag, a
// line of summary, and an action - pressed with the pointer, or with Enter or
// Space where the card has the focus, the keys handed on by whoever holds
// them. Every opening is said. Its axes are every card's: its size, and its
// aspect - square at 0, the widest the design allows at 1, the tallest at −1.
//
//   new SummaryCardSpecimen(branch, { leaf, say })   leaf: "summary-card"
//     .root  .extent(axis, v)  .key(ev)  .dispose()
// =============================================================================

const _summaryCardSpecimen = Object.freeze({ toString: () => "summaryCardSpecimen" });

class SummaryCardSpecimen {
    constructor(branch, params) {
        var p = params || {};
        var say = typeof p.say === "function" ? p.say : function () {};
        branch.activate(_summaryCardSpecimen);
        this.branch = branch;
        var root = branch.createElement("specimen", "div");
        css.addClass(root, sp_stage);
        var row = branch.createElement("row", "div");
        css.addClass(row, sp_row);
        root.appendChild(row);

        var opened = 0;
        this._card = new CardBuilder()
            .title("The quarter, in brief")
            .badge("new")
            .text("Revenue up a tenth on the last quarter, costs held, and three markets opening in the spring.")
            .onClick(function () { opened++; say("opened - " + opened + (opened === 1 ? " time" : " times") + ", by the pointer or by Enter or Space"); })
            .build(branch.createBranch("card"));
        row.appendChild(this._card.root);

        this.root = root;
        say("press the card, or give it the focus and press Enter or Space");
    }

    /** Every card's axes: its size, and its aspect. */
    extent(axis, v) {
        if (axis === "size") this._card.size(v);
        else if (axis === "aspect") this._card.aspect(v);
    }

    /** Enter or Space on the card is its action. */
    key(ev) { return this._card.key(ev); }

    dispose() { this._card.dispose(); try { this.branch.dissolve(); } catch (e) {} }
}
