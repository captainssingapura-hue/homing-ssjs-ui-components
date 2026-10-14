// =============================================================================
// SliderScale — a slider's scale: a tick at each value named, beside its
// track, with its caption. The slider's own code, drawn on the slider's
// branch into its rail: each tick's line a tick of the taxonomy's, worn as a
// notch; its caption a caption, worn as a figure. It places, and paints
// nothing of its own.
//
//   SliderScale.draw(branch, rail, ticks, { fraction, format, min, max, vertical })
//     → { parts: [el…], texts: [Caption…] }   what the slider sizes with the rest
//   ticks: [{ at, label }…] or [at…]; one off the range is dropped
// =============================================================================


class SliderScale {
    static draw(branch, rail, ticks, o) {
        var out = { parts: [], texts: [] };
        for (var t = 0; t < (ticks || []).length; t++) {
            var at = Number(ticks[t] && ticks[t].at != null ? ticks[t].at : ticks[t]);
            if (!(at >= o.min && at <= o.max)) continue;
            var tick = branch.createElement("tick-" + t, "div");
            css.addClass(tick, el_slider_tick);
            if (o.vertical) css.addClass(tick, el_slider_tick_vertical);
            tick.style.setProperty("--sl-tick", (o.fraction(at) * 100).toFixed(3) + "%");
            var line = new Tick(branch.elementMaker("tick-" + t + "-line"), {}, ROLE.NOTCH).el;   // the house's tick, cast as a notch of the scale
            css.addClass(line, el_slider_tick_line);
            if (o.vertical) css.addClass(line, el_slider_tick_line_vertical);
            tick.appendChild(line);
            var caption = new Caption(branch.elementMaker("tick-" + t + "-label"),
                    { text: ticks[t] && ticks[t].label != null ? String(ticks[t].label) : o.format(at) }, ROLE.FIGURE);
            css.addClass(caption.el, el_slider_tick_label);
            tick.appendChild(caption.el);
            rail.appendChild(tick);
            out.parts.push(tick, line);
            out.texts.push(caption);
        }
        if (out.parts.length) css.addClass(rail, el_slider_rail_ticked);
        return out;
    }
}
