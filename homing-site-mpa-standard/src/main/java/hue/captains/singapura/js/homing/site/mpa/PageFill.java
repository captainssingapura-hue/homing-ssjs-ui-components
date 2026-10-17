package hue.captains.singapura.js.homing.site.mpa;

/**
 * How a page takes the room the chrome leaves it - the page's to say, never its app's: a reading
 * column, the standard setting; or the whole of the viewport, the document pinned to it and what
 * scrolls the page's own, for an app that lays itself out - a workspace, a shell of panes.
 */
public enum PageFill {

    /** A reading column: the layout's standard width and room, the document scrolling. */
    COLUMN,

    /** The whole viewport: the document pinned to it, what scrolls the page's own. */
    VIEWPORT
}
