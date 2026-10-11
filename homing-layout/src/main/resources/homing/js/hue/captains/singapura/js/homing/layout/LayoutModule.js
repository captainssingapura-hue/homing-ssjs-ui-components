// =============================================================================
// Layout — the layout algebra (RFC 0068, appendix A): a layout as structure
// alone, and the edits on it. The same as the Java Layout and LayoutAlgebra,
// move for move, held to them by a parity test. No element, no sizing.
//
//   a layout:  { kind: "whole", id }
//            | { kind: "horizontal", parts: [...] }   two or more, in a row
//            | { kind: "vertical",   parts: [...] }   two or more, in a stack
//   a split never holds one of its own axis, and every id stands once. The
//   empty layout - what removing the last cell leaves - is null, never a value.
//   Values are frozen, and made only here.
//
//   Layout.whole(id)                    one cell
//   Layout.horizontal(...parts)         H(a, b, ...): a part null is dropped, a row in it
//   Layout.vertical(...parts)           flattened into it, one part left is itself; null if none
//   Layout.from(json)                   a value from data: rebuilt here, so normal, or refused
//   Layout.parse(text)  Layout.text(l)  the term: H(a, V(b, c)); parse reads any term, ε too
//   Layout.cells(l)  Layout.holds(l, id)  Layout.equals(a, b)  Layout.neighbours(l, id)
//   Layout.substitute(l, { id: layout | null })   every edit's form: one walk, a fold
//   Layout.subdivide(l, id, side, fresh)  side "left" | "right" | "top" | "bottom"
//   Layout.remove(l, id)  → a layout, or null when it was the last cell
//   Layout.swap(l, a, b)  Layout.move(l, id, to, side)  Layout.transpose(l)  Layout.mirror(l)
// =============================================================================

var _SIDES = Object.freeze({ left: "horizontal", right: "horizontal", top: "vertical", bottom: "vertical" });
var _ID = /^[A-Za-z0-9._-]+$/;

class Layout {
    static whole(id) {
        if (typeof id !== "string" || !_ID.test(id)) throw new Error("[Layout] a cell's id: letters, digits, dot, underscore, hyphen - not " + JSON.stringify(id));
        return Object.freeze({ kind: "whole", id: id });
    }

    static horizontal() { return Layout._join("horizontal", Array.prototype.slice.call(arguments)); }
    static vertical() { return Layout._join("vertical", Array.prototype.slice.call(arguments)); }

    /** The parts along the axis: nulls dropped, its own kind flattened in, one left is itself, none is null. */
    static _join(kind, parts) {
        var flat = [];
        for (var i = 0; i < parts.length; i++) {
            var p = parts[i];
            if (p === null || p === undefined) continue;
            if (p.kind === kind) flat.push.apply(flat, p.parts); else flat.push(p);
        }
        if (flat.length === 0) return null;
        if (flat.length === 1) return flat[0];
        var seen = new Set();
        for (var j = 0; j < flat.length; j++) Layout.cells(flat[j]).forEach(function (c) {
            if (seen.has(c)) throw new Error("[Layout] the cell " + c + " stands twice");
            seen.add(c);
        });
        return Object.freeze({ kind: kind, parts: Object.freeze(flat) });
    }

    static from(json) {
        if (!json || typeof json !== "object") throw new Error("[Layout] a layout is an object, not " + JSON.stringify(json));
        if (json.kind === "whole") return Layout.whole(json.id);
        if ((json.kind === "horizontal" || json.kind === "vertical") && Array.isArray(json.parts) && json.parts.length >= 2) {
            return Layout._join(json.kind, json.parts.map(Layout.from));
        }
        throw new Error("[Layout] not a layout: " + JSON.stringify(json));
    }

    static cells(l) {
        if (l.kind === "whole") return [l.id];
        var out = [];
        for (var i = 0; i < l.parts.length; i++) out.push.apply(out, Layout.cells(l.parts[i]));
        return out;
    }

    static holds(l, id) { return Layout.cells(l).indexOf(id) >= 0; }

    static text(l) {
        if (l === null) return "ε";
        if (l.kind === "whole") return l.id;
        return (l.kind === "horizontal" ? "H(" : "V(") + l.parts.map(Layout.text).join(", ") + ")";
    }

    static equals(a, b) { return Layout.text(a) === Layout.text(b); }

    static parse(text) {
        var s = String(text), at = 0;
        function space() { while (at < s.length && /\s/.test(s[at])) at++; }
        function refused(wanted) { return new Error("[Layout] the term '" + s + "' - " + wanted + " expected at " + at); }
        function term() {
            space();
            if (s[at] === "ε") { at++; return null; }
            var from = at;
            while (at < s.length && /[A-Za-z0-9._-]/.test(s[at])) at++;
            if (from === at) throw refused("a cell, H(...), V(...) or ε");
            var name = s.slice(from, at);
            space();
            if (s[at] === "(" && (name === "H" || name === "V")) {
                at++;
                var parts = [];
                while (true) {
                    parts.push(term());
                    space();
                    if (s[at] === ",") { at++; continue; }
                    break;
                }
                if (s[at] !== ")") throw refused("',' or ')'");
                at++;
                return Layout._join(name === "H" ? "horizontal" : "vertical", parts);
            }
            return Layout.whole(name);
        }
        var l = term();
        space();
        if (at < s.length) throw refused("the end");
        return l;
    }

    /** The wholes either side of the cell in the split holding it. */
    static neighbours(l, id) {
        if (!Layout.holds(l, id)) throw new Error("[Layout] no cell " + id + " in " + Layout.text(l));
        if (l.kind === "whole") return [];
        for (var i = 0; i < l.parts.length; i++) {
            var p = l.parts[i];
            if (p.kind === "whole" && p.id === id) {
                var out = [], b = l.parts[i - 1], a = l.parts[i + 1];
                if (b && b.kind === "whole") out.push(b.id);
                if (a && a.kind === "whole") out.push(a.id);
                return out;
            }
            if (Layout.holds(p, id)) return Layout.neighbours(p, id);
        }
        return [];
    }

    static substitute(l, sigma) {
        if (l.kind === "whole") return Object.prototype.hasOwnProperty.call(sigma, l.id) ? sigma[l.id] : l;
        return Layout._join(l.kind, l.parts.map(function (p) { return Layout.substitute(p, sigma); }));
    }

    static subdivide(l, id, side, fresh) {
        var kind = _SIDES[side];
        if (!kind) throw new Error("[Layout] a side is left, right, top or bottom - not " + JSON.stringify(side));
        if (!Layout.holds(l, id)) throw new Error("[Layout] no cell " + id + " in " + Layout.text(l));
        if (Layout.holds(l, fresh)) throw new Error("[Layout] the cell " + fresh + " already stands in " + Layout.text(l));
        var old = Layout.whole(id), nw = Layout.whole(fresh);
        var sigma = {};
        sigma[id] = side === "left" || side === "top" ? Layout._join(kind, [nw, old]) : Layout._join(kind, [old, nw]);
        return Layout.substitute(l, sigma);
    }

    static remove(l, id) {
        if (!Layout.holds(l, id)) throw new Error("[Layout] no cell " + id + " in " + Layout.text(l));
        var sigma = {};
        sigma[id] = null;
        return Layout.substitute(l, sigma);
    }

    static swap(l, a, b) {
        if (!Layout.holds(l, a) || !Layout.holds(l, b)) throw new Error("[Layout] swap " + a + " and " + b + " - both must stand in " + Layout.text(l));
        if (a === b) return l;
        var sigma = {};
        sigma[a] = Layout.whole(b);
        sigma[b] = Layout.whole(a);
        return Layout.substitute(l, sigma);
    }

    static move(l, id, to, side) {
        if (id === to) throw new Error("[Layout] move " + id + " beside itself");
        if (!Layout.holds(l, to)) throw new Error("[Layout] no cell " + to + " in " + Layout.text(l));
        return Layout.subdivide(Layout.remove(l, id), to, side, id);
    }

    static transpose(l) {
        if (l.kind === "whole") return l;
        return Layout._join(l.kind === "horizontal" ? "vertical" : "horizontal", l.parts.map(Layout.transpose));
    }

    static mirror(l) {
        if (l.kind === "whole") return l;
        var parts = l.parts.map(Layout.mirror);
        return Layout._join(l.kind, l.kind === "horizontal" ? parts.reverse() : parts);
    }
}
