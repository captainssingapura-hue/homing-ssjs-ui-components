package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The smallest JSON writer the registry needs: strings, numbers, booleans,
 * null, lists, maps with string keys, and a {@link Raw} fragment already
 * written — the rigid tree's canonical JSON, say. A map's keys are written
 * sorted, so its text follows from what it holds and not from how it was built:
 * a {@code Map.of} comes in a different order on every start of the JVM.
 */
record Json() implements StatelessFunctionalObject {

    static final Json INSTANCE = new Json();

    /** A fragment that is already JSON and is written as it is. */
    record Raw(String json) {}

    String write(Object v) {
        var sb = new StringBuilder();
        write(v, sb);
        return sb.toString();
    }

    void write(Object v, StringBuilder sb) {
        switch (v) {
            case null -> sb.append("null");
            case Raw r -> sb.append(r.json());
            case String s -> string(s, sb);
            case Boolean b -> sb.append(b);
            case Number n -> sb.append(n);
            case Map<?, ?> m -> {
                var sorted = new TreeMap<String, Object>();
                m.forEach((key, value) -> sorted.put(String.valueOf(key), value));
                sb.append('{');
                boolean first = true;
                for (var e : sorted.entrySet()) {
                    if (!first) sb.append(',');
                    first = false;
                    string(e.getKey(), sb);
                    sb.append(':');
                    write(e.getValue(), sb);
                }
                sb.append('}');
            }
            case List<?> l -> {
                sb.append('[');
                boolean first = true;
                for (var e : l) {
                    if (!first) sb.append(',');
                    first = false;
                    write(e, sb);
                }
                sb.append(']');
            }
            default -> string(String.valueOf(v), sb);
        }
    }

    void string(String s, StringBuilder sb) {
        sb.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"'  -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                case '<'  -> sb.append("\\u003c");   // never closes a script, wherever this lands
                default -> {
                    if (c < 0x20) sb.append(String.format("\\u%04x", (int) c));
                    else sb.append(c);
                }
            }
        }
        sb.append('"');
    }
}
