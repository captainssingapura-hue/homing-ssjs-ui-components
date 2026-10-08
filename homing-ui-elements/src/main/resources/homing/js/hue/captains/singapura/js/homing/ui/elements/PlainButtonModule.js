// =============================================================================
// PlainButton — the plain button: an ELEMENT component, the button's base in the
// plain look. The caller mints the element with its tag on its own branch:
//
//   var b = new PlainButton(branch.createElement("go", PlainButton.TAG), { label: "Go", onClick: fn });
//     label, size, onClick     as every button has them; no extent: plain is not a semantic word, and does not scale
//
// Everything else is the base's: .el, .extent(t), .size(s), .label(text?), .setOn(on).
// =============================================================================

class PlainButton extends Button {
    constructor(el, props) {
        super(el, props, { cls: el_button_plain, scales: false });
    }
}
