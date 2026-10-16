package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;

/**
 * The house's base scheme (RFC 0067, appendix Semantic Colours and Flavours, D45, D49): the Base style's scheme, and every
 * house style's over it - a scheme from scratch, a stateless singleton, living in this jar with its colours, which
 * {@link BaseColours} declares. A scheme on top of it extends it and says so, in a jar of its own beside the colours it adds.
 */
public class BaseScheme implements ColorScheme {

    public static final BaseScheme INSTANCE = new BaseScheme();

    protected BaseScheme() {}
}
