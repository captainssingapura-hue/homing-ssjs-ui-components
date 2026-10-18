package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** JS literals as the generated modules write them (RFC 0071): a string quoted, a list or a map of strings frozen. */
public record JsLiterals() implements StatelessFunctionalObject {

    public static final JsLiterals INSTANCE = new JsLiterals();

    public String string(String s) { return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\""; }

    public String strings(List<String> list) { return "Object.freeze([" + String.join(", ", list.stream().map(this::string).toList()) + "])"; }

    public String map(Map<String, String> map) {
        var out = new ArrayList<String>();
        map.forEach((k, v) -> out.add(string(k) + ": " + string(v)));
        return "Object.freeze({" + (out.isEmpty() ? "" : " " + String.join(", ", out) + " ") + "})";
    }

    /** A token as a JS name: each of its words capitalised, joined - {@code plain-button} is {@code PlainButton}. */
    public String name(String token) {
        var out = new StringBuilder();
        for (String w : token.split("-")) if (!w.isEmpty()) out.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
        return out.toString();
    }
}
