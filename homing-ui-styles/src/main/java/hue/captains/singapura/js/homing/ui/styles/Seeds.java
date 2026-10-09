package hue.captains.singapura.js.homing.ui.styles;

/**
 * The colours a house palette is written from, light: as many as the branches answered so far ask
 * for - the button's. The old seed palettes have thirteen; the page's ground, the recessed layer,
 * the muted ink and the muted ink on inverted come with the branches that need them.
 *
 * @param raised     a layer lifted off the page: the plain button's ground, and the lit side of a shadow
 * @param inverted   the darkest layer: what a box casts
 * @param text       the ink of what is written
 * @param onInverted the ink written on the title colour: the primary button's
 * @param title      the house's own colour: the primary button's ground, and the glow
 * @param accent     the accent: the secondary button's ground, and the ring of attention
 * @param accentEdge the accent's edge: the secondary button's border
 * @param onAccent   the ink written on the accent
 * @param border     the hairline: a button's border, and where the ramp of a coloured button ends at 0
 */
record Seeds(String raised, String inverted, String text, String onInverted, String title, String accent, String accentEdge,
             String onAccent, String border) {}
