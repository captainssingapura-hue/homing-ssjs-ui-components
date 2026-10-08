// =============================================================================
// PrimaryButton — the primary button: an ELEMENT component, the button's base in the
// primary look. The caller mints the element with its tag on its own branch:
//
//   var b = new PrimaryButton(branch.createElement("go", PrimaryButton.TAG), { label: "Go", onClick: fn });
//     label, size, onClick     as every button has them; an extent of its word, −1 … 1 (1 unless said): the word as the design binds it at 1,
//   the design's neutral at 0, the meaning turned the other way at −1
//
// Everything else is the base's: .el, .extent(t), .size(s), .label(text?), .setOn(on).
// =============================================================================

class PrimaryButton extends Button {
    constructor(el, props) {
        super(el, props, { cls: el_button_primary, scales: true });
    }
}
