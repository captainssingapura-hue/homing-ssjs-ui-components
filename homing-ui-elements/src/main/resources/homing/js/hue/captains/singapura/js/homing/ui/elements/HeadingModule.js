// =============================================================================
// Heading — what the heading leaves share: words that say what a container
// holds, read first. A Text (a simple component); never made on its own - each
// level of a page's outline is a leaf of its own, on its own element, in its own
// look: Heading1 on an <h1>, a page's own name, down to Heading6 on an <h6>.
//
//   var h = new Heading4(Heading4Factory.create(branch, "title"), { text: "Books" }, ROLE.TITLE);
//   h.text = "Shelves"   the words, its whole face
//   h.size = 0.5         −1 the smallest … 1 the biggest; 0 its level's own
// =============================================================================

class Heading extends Text {}
