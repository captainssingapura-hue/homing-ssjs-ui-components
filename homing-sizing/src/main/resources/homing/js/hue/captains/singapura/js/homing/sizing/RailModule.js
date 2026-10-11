// =============================================================================
// Rail — sizing's model (RFC 0068, appendix C §3): an ordered run of boxes
// along one axis, sharing a length, each a share of the room the shares
// divide in millionths - a million in all, none below one. The same as the
// Java Rail and Sizing, to the millionth, held to them by a parity test. It
// knows nothing of what its boxes are, nor of the layout algebra: a layout
// that is resizable applies it, and hands it the pixels it measures.
//
//   a rail:  { shares: [n, ...] }   frozen, made only here
//
//   Rail.WHOLE                       a million
//   Rail.exact(shares)               a rail of these millionths, or refused
//   Rail.of(weights)                 any positive numbers - a 2 : 1, a stored ratio - by the one rounding
//   Rail.even(n)  Rail.parse(text)  Rail.text(r)  Rail.equals(a, b)
//   Rail.at(r, i)                    where the boundary after box i stands, in millionths
//   Rail.lay(r, room, least?)        each box's length in px, as flexbox: under its least, held there
//   Rail.move(r, i, d, room, least?) the boundary after box i moved d px over the room the shares
//                                    divide, the pair's sum exact, stopped at either's least
//   Rail.insert(r, i, before)        a box beside box i taking half of it
//   Rail.remove(r, i, toward?)       box i gone, its share to the neighbour named, else the one before
//   Rail.absorb(r, i, inner)         box i replaced by another rail's boxes: two rails made one
//
// THE ONE ROUNDING: each part's floor, what is left a unit at a time to the
// largest remainders, the earlier on a tie, then a part at nothing given one
// from the largest - the rule the workspace's log keeps.
// =============================================================================

var _WHOLE = 1000000;

class Rail {
    static WHOLE = _WHOLE;

    static exact(shares) {
        if (!Array.isArray(shares) || shares.length === 0) throw new Error("[Rail] a rail has a box or more");
        var sum = 0;
        for (var i = 0; i < shares.length; i++) {
            var s = shares[i];
            if (typeof s !== "number" || !Number.isInteger(s) || s < 1) throw new Error("[Rail] a box's share is a millionth or more, not " + s);
            sum += s;
        }
        if (sum !== _WHOLE) throw new Error("[Rail] a rail's shares add up to " + _WHOLE + ", not " + sum);
        return Object.freeze({ shares: Object.freeze(shares.slice()) });
    }

    static of(weights) {
        if (!Array.isArray(weights) || weights.length === 0) throw new Error("[Rail] a rail has a box or more");
        var n = weights.length, sum = 0;
        for (var i = 0; i < n; i++) {
            var w = weights[i];
            if (typeof w !== "number" || !(w > 0) || w === Infinity) throw new Error("[Rail] a weight is a positive number, not " + w);
            sum += w;
        }
        var units = [], rem = [], got = 0;
        for (var j = 0; j < n; j++) {
            var q = _WHOLE * weights[j] / sum, f = Math.floor(q);
            units.push(f);
            rem.push(q - f);
            got += f;
        }
        return Rail.exact(Rail._settle(units, Rail._order(rem), _WHOLE - got));
    }

    static even(n) {
        if (!Number.isInteger(n) || n < 1) throw new Error("[Rail] a rail has a box or more, not " + n);
        var ones = [];
        for (var i = 0; i < n; i++) ones.push(1);
        return Rail.exact(Rail._apportion(_WHOLE, ones));
    }

    static parse(text) {
        var t = String(text).trim();
        if (!t) throw new Error("[Rail] a rail has a box or more");
        return Rail.exact(t.split(/\s+/).map(function (s) { return Number(s); }));
    }

    static text(r) { return r.shares.join(" "); }

    static equals(a, b) {
        if (a === b) return true;
        if (!a || !b || a.shares.length !== b.shares.length) return false;
        for (var i = 0; i < a.shares.length; i++) if (a.shares[i] !== b.shares[i]) return false;
        return true;
    }

    static at(r, i) {
        Rail._boundary(r, i);
        var sum = 0;
        for (var k = 0; k <= i; k++) sum += r.shares[k];
        return sum;
    }

    static lay(r, room, least) {
        var s = r.shares, n = s.length, sizes = new Array(n), frozen = new Array(n), left = Math.max(0, room);
        for (var i = 0; i < n; i++) frozen[i] = false;
        for (var pass = 0; pass <= n; pass++) {
            var free = 0;
            for (var a = 0; a < n; a++) if (!frozen[a]) free += s[a];
            var under = -1;
            for (var b = 0; b < n; b++) {
                if (frozen[b]) continue;
                sizes[b] = free > 0 ? s[b] / free * left : 0;
                if (sizes[b] < Rail._least(r, least, b) && under < 0) under = b;
            }
            if (under < 0) break;
            frozen[under] = true;
            sizes[under] = Rail._least(r, least, under);
            left = Math.max(0, left - Rail._least(r, least, under));
        }
        return sizes;
    }

    static move(r, i, d, room, least) {
        Rail._boundary(r, i);
        if (!(room > 0) || Number.isNaN(d)) return r;
        var a0 = r.shares[i], b0 = r.shares[i + 1], t = a0 + b0;
        var raw = Math.max(-t, Math.min(t, d * _WHOLE / room));
        var step = raw < 0 ? -Math.round(-raw) : Math.round(raw);       // halves away from nought, so -d undoes d
        var loA = Math.min(a0, Math.max(1, Math.ceil(Rail._least(r, least, i) * _WHOLE / room)));
        var loB = Math.min(b0, Math.max(1, Math.ceil(Rail._least(r, least, i + 1) * _WHOLE / room)));
        var a = Math.max(loA, Math.min(t - loB, a0 + step));
        if (a === a0) return r;
        var out = r.shares.slice();
        out[i] = a;
        out[i + 1] = t - a;
        return Rail.exact(out);
    }

    static insert(r, i, before) {
        var s = Rail._box(r, i);
        if (s < 2) throw new Error("[Rail] a box of one millionth cannot be divided");
        var first = s - Math.floor(s / 2), second = Math.floor(s / 2), out = r.shares.slice();
        out[i] = before ? second : first;
        out.splice(before ? i : i + 1, 0, before ? first : second);
        return Rail.exact(out);
    }

    static remove(r, i, toward) {
        Rail._box(r, i);
        var n = r.shares.length;
        if (n < 2) throw new Error("[Rail] the last box of a rail cannot go");
        var to = toward == null ? (i > 0 ? i - 1 : i + 1) : toward;
        if ((to !== i - 1 && to !== i + 1) || to < 0 || to >= n) throw new Error("[Rail] box " + i + "'s share goes to a neighbour, not to " + toward);
        var out = r.shares.slice();
        out[to] += out[i];
        out.splice(i, 1);
        return Rail.exact(out);
    }

    static absorb(r, i, inner) {
        var s = Rail._box(r, i);
        if (s < inner.shares.length) throw new Error("[Rail] a box of " + s + " millionths cannot hold " + inner.shares.length + " boxes");
        var parts = Rail._apportion(s, inner.shares);
        return Rail.exact(r.shares.slice(0, i).concat(parts, r.shares.slice(i + 1)));
    }

    // ── the one rounding ──────────────────────────────────────────────────

    /** A whole total shared by whole weights: the floors exact, the remainders compared over one denominator. */
    static _apportion(total, weights) {
        var n = weights.length, sum = 0, units = [], rem = [], got = 0;
        for (var i = 0; i < n; i++) sum += weights[i];
        for (var j = 0; j < n; j++) {
            var q = total * weights[j], u = Math.floor(q / sum);
            units.push(u);
            rem.push(q - u * sum);
            got += u;
        }
        return Rail._settle(units, Rail._order(rem), total - got);
    }

    /** The indices by remainder, the largest first, the earlier on a tie. */
    static _order(rem) {
        var order = [];
        for (var i = 0; i < rem.length; i++) order.push(i);
        order.sort(function (a, b) { return rem[a] !== rem[b] ? (rem[b] > rem[a] ? 1 : -1) : a - b; });
        return order;
    }

    static _settle(units, order, left) {
        var n = units.length;
        for (var k = 0; k < left; k++) units[order[k % n]]++;
        for (var i = 0; i < n; i++) {
            if (units[i] > 0) continue;
            var big = 0;
            for (var j = 1; j < n; j++) if (units[j] > units[big]) big = j;
            units[big]--;
            units[i] = 1;
        }
        return units;
    }

    static _box(r, i) {
        if (!Number.isInteger(i) || i < 0 || i >= r.shares.length) throw new Error("[Rail] a rail of " + r.shares.length + " has no box " + i);
        return r.shares[i];
    }

    static _boundary(r, i) {
        if (!Number.isInteger(i) || i < 0 || i >= r.shares.length - 1) throw new Error("[Rail] a rail of " + r.shares.length + " has no boundary after box " + i);
    }

    static _least(r, least, i) {
        if (!least || least.length === 0) return 0;
        if (least.length !== r.shares.length) throw new Error("[Rail] a least for each of " + r.shares.length + " boxes, not " + least.length);
        return least[i];
    }
}
