package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The kit a component's fixed type checks its values with (RFC 0071 port, owner 23 Oct): one class for each type of
 * value a component declares in Java - a whole number, yes or no, a text, one of a set of names, an element of the page,
 * a group of values - each made by its constructor, which checks its name and its default, and frozen. Given what a
 * caller gives for it, each answers what the fixed object keeps: its default where nothing is given, what is given where
 * it is of its type, refused otherwise; a group a fresh object of its own, frozen. Apart from the kit, which imports its
 * group.
 */
public record FixedValuesKit() implements StatelessFunctionalObject {

    public static final FixedValuesKit INSTANCE = new FixedValuesKit();

    /** Its address: its class's canonical name, as every module's is. */
    public String address() { return "/module?class=" + getClass().getCanonicalName(); }

    /** The classes it gives, in its order. */
    public List<String> classes() { return List.of("FixedWhole", "FixedYesNo", "FixedText", "FixedOneOf", "FixedElement", "FixedGroup"); }

    /** Its module. */
    public String script() {
        return """
                // A value a component is made with, immutable for its life (RFC 0071 port, owner 23 Oct): declared in Java, each
                // named as a field, typed, with its default - none only where it may be none. take(given, said) answers what a fixed
                // object keeps: its default where nothing is given, what is given where it is of its type, refused otherwise.
                class FixedWhole {
                    constructor(name, byDefault, orNone) {
                        valueNamed(name, "FixedWhole"); this.name = name; this.orNone = orNone;
                        this.byDefault = valueDefault(this, byDefault); Object.freeze(this);
                    }
                    allows(v) { return Number.isInteger(v) && v >= 0; }
                    says() { return "a whole number, none below 0"; }
                    take(given, said) { return valueTaken(this, given, said); }
                }
                class FixedYesNo {
                    constructor(name, byDefault, orNone) {
                        valueNamed(name, "FixedYesNo"); this.name = name; this.orNone = orNone;
                        this.byDefault = valueDefault(this, byDefault); Object.freeze(this);
                    }
                    allows(v) { return typeof v === "boolean"; }
                    says() { return "true or false"; }
                    take(given, said) { return valueTaken(this, given, said); }
                }
                class FixedText {
                    constructor(name, byDefault, orNone) {
                        valueNamed(name, "FixedText"); this.name = name; this.orNone = orNone;
                        this.byDefault = valueDefault(this, byDefault); Object.freeze(this);
                    }
                    allows(v) { return typeof v === "string"; }
                    says() { return "a text"; }
                    take(given, said) { return valueTaken(this, given, said); }
                }
                class FixedOneOf {
                    constructor(name, names, byDefault, orNone) {
                        valueNamed(name, "FixedOneOf"); this.name = name; this.orNone = orNone;
                        if (!Array.isArray(names) || names.length === 0 || !names.every(n => typeof n === "string")) throw new TypeError("FixedOneOf " + name + ": one of a list of names");
                        this.names = Object.freeze([...names]);
                        this.byDefault = valueDefault(this, byDefault); Object.freeze(this);
                    }
                    allows(v) { return this.names.includes(v); }
                    says() { return "one of " + this.names.join(", "); }
                    take(given, said) { return valueTaken(this, given, said); }
                }
                // An element of the page, given by the component's caller - where what it makes is drawn: none by default, always. It
                // is kept as it is given: an element is never frozen.
                class FixedElement {
                    constructor(name) { valueNamed(name, "FixedElement"); this.name = name; this.orNone = true; this.byDefault = null; Object.freeze(this); }
                    allows(v) { return typeof Element !== "undefined" && v instanceof Element; }
                    says() { return "an element of the page"; }
                    take(given, said) { return valueTaken(this, given, said); }
                }
                // A group of values, each named once: never none - given as a plain object of its own, every name in it one of its
                // values', a value it is not given its default; kept as a fresh object, frozen.
                class FixedGroup {
                    constructor(name, values) {
                        valueNamed(name, "FixedGroup"); this.name = name; this.orNone = false;
                        const kinds = [FixedWhole, FixedYesNo, FixedText, FixedOneOf, FixedElement, FixedGroup];
                        if (!Array.isArray(values) || values.length === 0 || !values.every(v => kinds.some(K => v instanceof K))) throw new TypeError("FixedGroup " + name + ": a list of values, each a fixed value");
                        if (new Set(values.map(v => v.name)).size !== values.length) throw new TypeError("FixedGroup " + name + ": names a value twice");
                        this.values = Object.freeze([...values]);
                        this.byDefault = Object.freeze(Object.fromEntries(values.map(v => [v.name, v.byDefault])));
                        Object.freeze(this);
                    }
                    take(given, said) {
                        if (given === undefined) return this.byDefault;
                        if (given === null || typeof given !== "object" || Object.getPrototypeOf(given) !== Object.prototype)
                            throw new TypeError(said + ": a plain object of " + this.values.map(v => v.name).join(", "));
                        const strange = Object.keys(given).filter(k => !this.values.some(v => v.name === k));
                        if (strange.length > 0) throw new TypeError(said + ": has no value " + strange.join(", ") + " - its values are " + this.values.map(v => v.name).join(", "));
                        return Object.freeze(Object.fromEntries(this.values.map(v => [v.name, v.take(given[v.name], said + "." + v.name)])));
                    }
                }
                function valueNamed(name, kind) {
                    if (typeof name !== "string" || !/^[a-z][A-Za-z0-9]*$/.test(name)) throw new TypeError(kind + ": its name is a field's - a lower-case letter, then letters and digits");
                }
                function valueDefault(value, byDefault) {
                    if (byDefault === null ? !value.orNone : !value.allows(byDefault)) throw new TypeError(value.constructor.name + " " + value.name + ": its default is " + value.says() + (value.orNone ? ", or none" : ""));
                    return byDefault;
                }
                function valueTaken(value, given, said) {
                    if (given === undefined) return value.byDefault;
                    if (given === null && value.orNone) return null;
                    if (given === null || !value.allows(given)) throw new TypeError(said + ": " + value.says() + (value.orNone ? ", or null for none" : ""));
                    return given;
                }
                export { FixedWhole, FixedYesNo, FixedText, FixedOneOf, FixedElement, FixedGroup };
                """;
    }
}
