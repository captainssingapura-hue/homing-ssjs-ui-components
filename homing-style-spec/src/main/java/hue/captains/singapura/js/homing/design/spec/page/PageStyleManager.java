package hue.captains.singapura.js.homing.design.spec.page;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * Where the page's one style manager is (RFC 0071): the module that makes it - on the page's surface and clock, in its
 * first style - and exports it as {@code styleManager}. The site's to say, and to hand the stamp: each top-level
 * component's header imports it from there, and binds it to the component ({@code HeaderStamp}), so that no component
 * module imports it itself.
 *
 * @param address the module's address: {@code /module?class=} and its class's canonical name
 */
public record PageStyleManager(String address) implements ValueObject {

    /** The name the module exports it under, and every header imports it as. */
    public static final String EXPORTED = "styleManager";

    public PageStyleManager {
        Objects.requireNonNull(address, "PageStyleManager.address");
        if (!address.startsWith("/module?class=") || address.contains("$") || address.contains("&"))
            throw new IllegalArgumentException("PageStyleManager: an address is /module?class= and a canonical class name, nothing after it - not " + address);
    }
}
