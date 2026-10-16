package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;

/**
 * The house's base scheme (RFC 0067, appendix Semantic Colours and Flavours, D45): the Base style's scheme, and every
 * house style's over it - a scheme from scratch, living in this jar with its colours, which {@link HouseColours} reads.
 * A scheme on top of it extends it, in a jar of its own beside the colours it adds.
 */
public abstract class BaseScheme implements ColorScheme {}
