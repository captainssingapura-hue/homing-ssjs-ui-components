package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-site-mpa-standard's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - none since its preferences button retired (owner, 21 Oct): the chrome opens the preferences from an
 * icon button.
 */
public record MpaStyleDeclaration() implements StyleDeclaration {

    public static final MpaStyleDeclaration INSTANCE = new MpaStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of()));
    }
}
