// =============================================================================
// DegreeScale — a degree read on a caller's own scale, and back: pure
// arithmetic, holding nothing and changing nothing (RFC 0069, ranges case by
// case). A degree's −1 is the scale's least, its 1 the scale's most, the two in
// proportion between: on 0 … 100, the degree −0.2 is 40.
//
//   var volume = new DegreeScale(0, 100);
//   volume.at(range.degree)    the volume a degree stands at: 40
//   volume.degreeOf(40)        the degree a volume stands at: −0.2
//   volume.stepOf(5)           a step of 5 on the scale, as a degree's: 0.1
// =============================================================================

class DegreeScale {
    /** A scale from its least to its most, each a finite number, the least below the most. */
    constructor(least, most) {
        if (!Number.isFinite(least) || !Number.isFinite(most) || !(least < most))
            throw new TypeError("DegreeScale: finite bounds, its least below its most, not " + least + " to " + most);
        this.least = least;
        this.most = most;
        Object.freeze(this);
    }

    /** The value on the scale a degree stands at. */
    at(degree) {
        if (!(degree >= -1 && degree <= 1)) throw new RangeError("DegreeScale: a degree is -1 to 1, not " + degree);
        return this.least + (degree + 1) / 2 * (this.most - this.least);
    }

    /** The degree a value on the scale stands at. */
    degreeOf(value) {
        if (!(value >= this.least && value <= this.most)) throw new RangeError("DegreeScale: " + value + " is not on " + this.least + " to " + this.most);
        return (value - this.least) / (this.most - this.least) * 2 - 1;
    }

    /** A step on the scale, as a degree's step. */
    stepOf(step) {
        if (!(step > 0 && step <= this.most - this.least)) throw new RangeError("DegreeScale: a step is above 0 and at most the scale's length, not " + step);
        return step / (this.most - this.least) * 2;
    }
}
