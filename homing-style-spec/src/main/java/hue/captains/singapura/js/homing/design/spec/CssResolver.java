package hue.captains.singapura.js.homing.design.spec;

/**
 * Finds the CSS content of an implementation with CSS of its own (RFC 0067, D53) - provided apart from any content, and
 * registered explicitly with the registry ({@link ReadSpec}). Content is a resource at the implementation's canonical
 * name, one to one; where it is looked for is the resolver's: the classpath ({@link ClasspathCssResolver}), or another a
 * registry is given.
 */
public interface CssResolver {

    /** What there is of an implementation's content: found, nothing there, or there and not to be had. */
    CssResolution resolve(CssImplementation<?, ?, ?> impl);
}
