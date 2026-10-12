package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.semantic.FlattenLexicon;
import hue.captains.singapura.js.homing.design.semantic.WriteWord;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.ResolveSemanticClass;
import hue.captains.singapura.js.homing.design.semantic.Resolution;
import hue.captains.singapura.js.homing.design.semantic.SemanticTokens;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house styles: every one written over Base, which answers every root that is not colour and
 * what they all say alike; each style answering the button branch's minimal set and nothing more -
 * a silence costs nothing, so a style that has nothing to say of a class leaves it to Base - and
 * every theme flattening whole over the house's taxonomy.
 */
class HouseStylesTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    /** What a style must decide for the button branch: the button's frame and motion, the control's ring and how it is when it cannot be used. */
    private static final Set<String> BUTTON_BRANCH = Set.of(
            "button shape-corner", "button shape-border", "button size-inset", "button size-gap", "button size-extent",
            "button type-face", "button motion-ease", "button shape-shadow", "button motion-transform",
            "control shape-outline", "control effect-opacity", "control affordance-cursor",
            "toggle-button shape-shadow", "toggle-button motion-transform",
            "close-button shape-border", "close-button size-inset", "close-button size-extent", "close-button shape-shadow", "close-button motion-transform",
            "icon-button shape-border", "icon-button size-inset", "icon-button size-extent", "icon-button shape-shadow", "icon-button motion-transform");

    /** What a style may decide for the text branch: its faces, its weights, how a text is set apart. */
    private static final Set<String> TEXT_BRANCH = Set.of(
            "text type-face", "heading type-face", "heading type-weight", "kicker type-face", "kicker type-weight", "kicker type-treatment",
            "lede type-treatment", "caption type-treatment", "label type-weight", "readout type-face", "readout type-weight", "readout type-treatment",
            "wordmark type-face", "wordmark type-treatment",
            "heading1 type-weight", "heading1 type-treatment", "heading1 type-scale");

    /** What a style may decide for the ways somewhere, the marks between things and the veil: how a way eases and is marked under the hand, how thick a separator is ruled, how the page behind a dialog is veiled. */
    private static final Set<String> WAYS_MARKS_AND_VEIL = Set.of(
            "link motion-ease", "crumb motion-ease", "link type-decoration", "crumb type-decoration", "scrim effect-filter", "separator shape-border",
            "action-bar shape-border", "pane-thumb shape-border");

    /** What every style decides for its slider: its measure, its lines and corners, and how its knob is ringed when it is a group's current one; its marks and a knob that is its mark alone where it says so. */
    private static final Set<String> SLIDER = Set.of("slider size-extent", "groove size-extent", "groove shape-corner", "groove shape-border",
            "knob size-extent", "knob shape-corner", "knob shape-border", "knob shape-outline", "knob effect-opacity", "detent size-extent", "tick size-extent");

    /** What every style decides for its context menu - the frame its measure, its lift and what it does to the page behind - and its rows; and how an item that cannot be used is, as a control is. */
    private static final Set<String> MENU = Set.of("context-menu shape-corner", "context-menu shape-border", "context-menu size-inset", "context-menu size-gap",
            "context-menu size-extent", "context-menu shape-shadow", "context-menu effect-filter", "menu-item shape-corner", "menu-item size-inset", "item effect-opacity", "item affordance-cursor");

    /** What every style decides for its cards: their measure, their frame, how they move and are lifted, and what a frosted one does to the page behind. */
    private static final Set<String> CARDS = Set.of("card size-extent", "card size-inset", "card size-gap", "card shape-corner", "card shape-border",
            "card motion-ease", "card motion-transform", "card shape-shadow", "card effect-filter");

    /** What every style decides for its page: the face everything on it is written in, and the site bar's rule and backdrop. */
    private static final Set<String> SITE = Set.of("ground type-face", "site-bar shape-border", "site-bar effect-filter");

    /** What every style decides for its floating pane - a dialog's window: its shadows and its lift; its title bar's face, room and rule. */
    private static final Set<String> PANE = Set.of("floating-pane shape-shadow", "floating-pane motion-transform", "title-bar type-face",
            "title-bar size-inset", "title-bar size-gap", "title-bar shape-border", "label_title type-face", "pane shape-outline");

    /** What every style decides for its panel: the room between its head and its body, and its depth; its head's room and the line under it; a section's room - its body's. */
    private static final Set<String> PANEL = Set.of("panel size-gap", "panel shape-shadow", "panel-bar size-inset", "panel-bar size-gap", "panel-bar shape-border",
            "section size-inset", "section size-gap");

    /** What every style decides for its tabs: a tab's measure, its frame, how it moves and is lifted; its strip's height and the rule along its foot. */
    private static final Set<String> TABS = Set.of("tab size-extent", "tab size-inset", "tab size-gap", "tab shape-corner", "tab shape-border",
            "tab motion-ease", "tab motion-transform", "tab shape-shadow", "tab-strip size-extent", "tab-strip shape-border");

    /** What every style decides for its text, at least: the body's face, and the display's where a text is set apart. */
    private static final Set<String> TEXT_SET_APART = Set.of(
            "text type-face", "heading type-face", "heading type-weight", "kicker type-face", "kicker type-weight", "kicker type-treatment",
            "readout type-face", "readout type-weight", "wordmark type-face");

    @Test
    void theStyles_areSeven_eachOverBase_andBaseOverNothing() {
        assertEquals(List.of("editorial", "neo-brutalism", "neo-futurism", "neumorphism", "glassmorphism", "retro-futurism", "sketchy"),
                HouseStyles.STYLES.stream().map(DesignStyle::token).toList(), "the old slugs, Editorial first");
        for (DesignStyle s : HouseStyles.STYLES)
            assertTrue(s instanceof Derives<?> d && d.base() == Base.INSTANCE, s.token() + " is written over Base");
        assertEquals(List.of("base"), new Dressed(Base.INSTANCE, Harbour.INSTANCE).chain(), "Base is written over nothing");
        assertEquals(List.of("editorial", "base"), HouseStyles.THEMES.get("editorial").chain());
    }

    @Test
    void eachStyle_answersTheButtonBranchsMinimalSet_andLeavesOnlyItsSilencesToBase() {
        Map<String, Set<String>> left = new java.util.LinkedHashMap<>();
        for (DesignStyle s : HouseStyles.STYLES) {
            Set<String> answered = classes(s.implementations());
            var allowed = new TreeSet<>(BUTTON_BRANCH);
            allowed.addAll(TEXT_BRANCH);
            allowed.addAll(WAYS_MARKS_AND_VEIL);
            allowed.addAll(SLIDER);
            allowed.addAll(MENU);
            allowed.addAll(TABS);
            allowed.addAll(CARDS);
            allowed.addAll(SITE);
            allowed.addAll(PANE);
            allowed.addAll(PANEL);
            var beyond = new TreeSet<>(answered);
            beyond.removeAll(allowed);
            assertTrue(beyond.isEmpty(), () -> s.token() + " answers beyond the button and text branches, the ways, the marks, the veil, a pane's thumbnail's rule, its slider, its menu, its tabs, its cards, its page, its floating pane and its panel: " + beyond);
            var missing = new TreeSet<>(BUTTON_BRANCH);
            missing.removeAll(answered);
            left.put(s.token(), missing);
        }
        assertEquals(Map.of("editorial", Set.of(), "neo-brutalism", Set.of(), "neo-futurism", Set.of(),
                        "neumorphism", Set.of("button shape-border", "toggle-button motion-transform"), "glassmorphism", Set.of(), "retro-futurism", Set.of(),
                        "sketchy", Set.of("button shape-shadow", "toggle-button shape-shadow")), left,
                "neumorphism draws no border and its press does not move; sketchy casts no shadow: each leaves it to Base, whose root says none");
    }

    /** Every style sets its text in its own faces: the body's on every text, the display's where a text is set apart. */
    @Test
    void eachStyle_setsItsTextInItsOwnFaces() {
        for (DesignStyle s : HouseStyles.STYLES) {
            var missing = new TreeSet<>(TEXT_SET_APART);
            missing.removeAll(classes(s.implementations()));
            assertEquals(Set.of(), missing, s.token() + " leaves its text's faces to Base");
        }
    }

    @Test
    void base_answersEveryRootThatIsNotColour_andWhatEveryStyleSaysAlike() {
        var roots = Base.INSTANCE.implementations().stream().filter(i -> i.node().component() instanceof Root).map(i -> i.target().token()).toList();
        var semantic = SemanticTokens.INSTANCE.leaves();
        assertEquals(semantic.stream().filter(t -> !(t instanceof Target.Color)).map(Target::token).collect(Collectors.toSet()), Set.copyOf(roots),
                "the twenty-seven roots that are not colour, the box's line and its ring apart, and what is drawn over its ground");
        assertEquals(27, roots.size());
        var alike = new TreeSet<>(classes(Base.INSTANCE.implementations()));
        alike.removeIf(c -> c.startsWith("root "));
        var marks = new TreeSet<String>();
        for (String mark : List.of("close-button", "icon-button"))
            for (String t : List.of("shape-border", "size-inset", "size-extent", "shape-shadow", "motion-transform", "type-scale")) marks.add(mark + " " + t);
        var expected = new TreeSet<>(Set.of("control affordance-cursor", "button type-scale", "control shape-shadow", "control motion-transform"));
        expected.addAll(marks);
        for (String text : List.of("heading1", "heading2", "heading3", "heading4", "heading5", "heading6", "lede", "kicker", "caption", "label", "readout", "badge", "pill", "wordmark")) expected.add(text + " type-scale");
        expected.addAll(Set.of("readout type-treatment", "badge type-treatment", "badge size-inset", "badge shape-corner",
                "pill type-treatment", "pill size-inset", "pill shape-corner"));
        expected.addAll(Set.of("link type-scale", "crumb type-weight", "separator shape-border", "delimiter type-glyph", "boundary-line size-extent", "pane-thumb shape-border"));
        expected.addAll(Set.of("slider size-inset", "slider size-gap", "slider affordance-select", "knob type-scale", "knob affordance-cursor", "detent size-extent", "tick size-extent"));
        expected.addAll(Set.of("context-menu type-face", "context-menu affordance-select", "menu-item size-gap", "menu-item type-scale", "menu-item affordance-cursor"));
        expected.addAll(Set.of("tab type-scale", "tab affordance-select", "tab affordance-cursor", "tab-strip affordance-select", "tab-strip size-gap", "tab-strip size-inset"));
        expected.addAll(Set.of("switch size-inset", "switch size-gap", "knob motion-ease", "groove motion-ease"));
        expected.addAll(Set.of("item shape-corner", "item shape-border", "item shape-outline", "item size-inset", "item size-gap", "item type-face", "item type-scale", "item motion-ease",
                "choice-option size-inset", "choice-option affordance-cursor", "list-row size-inset", "list-row size-gap", "list-row type-scale", "list-row affordance-cursor"));
        expected.addAll(Set.of("lamp shape-corner", "lamp shape-border", "lamp size-inset", "lamp type-scale", "lamp motion-ease"));
        expected.addAll(Set.of("action-bar shape-border", "action-bar size-inset", "action-bar size-gap", "trail size-gap", "trail type-scale"));
        expected.addAll(Set.of("edge-strip size-inset", "edge-strip size-gap", "edge-strip shape-border", "edge-strip shape-shadow"));
        expected.addAll(Set.of("pane shape-outline", "floating-pane shape-outline"));
        expected.addAll(Set.of("card size-proportion", "card shape-outline", "card affordance-cursor", "slider-group shape-corner", "slider-group shape-border",
                "slider-group size-gap", "slider-group shape-outline", "slider-group size-inset", "slider-group affordance-cursor", "slider-group affordance-select"));
        expected.addAll(Set.of("site-bar size-inset", "site-bar size-gap", "site-bar shape-border", "ground type-face"));
        expected.addAll(Set.of("floating-pane shape-corner", "floating-pane shape-border", "floating-pane motion-ease", "title-bar size-inset",
                "title-bar size-gap", "title-bar shape-border", "title-bar affordance-cursor", "title-bar affordance-select"));
        expected.addAll(Set.of("panel shape-corner", "panel shape-border", "panel motion-ease"));
        for (String box : List.of("text-box", "select"))
            for (String t : List.of("shape-corner", "shape-border", "type-scale", "type-face", "size-inset", "motion-ease")) expected.add(box + " " + t);
        expected.add("text-box affordance-cursor");
        expected.addAll(Set.of("brand size-gap", "brand type-decoration", "preferences-button shape-corner", "preferences-button shape-border",
                "preferences-button size-inset", "preferences-button size-gap", "preferences-button type-face", "preferences-button type-scale"));
        assertEquals(expected, alike,
                "a control is pointed at, a button's type grows alike, a control that cannot be used neither lifts nor casts a shadow,"
                + " and a mark alone goes without a frame, in the size of the text it sits in; every text's measure, its figures and its chip;"
                + " a link the size of the text it sits in, and the crumb that is the page set heavier; a separator a hairline, a delimiter a slash, a layout's boundary a hairline;"
                + " a pane's thumbnail ruled round with a hairline; a slider's room, its text kept from the hand, its knob grabbed and its mark's size, the detent and the ticks as most styles mark them; a menu's text the label's face, kept from the hand, its rows pointed at in the label's size; a tab's text the caption's size, kept from the hand, pointed at and grabbed, and its strip's kept from the hand, its tail's room eight pixels; a switch with no room inside, its word kept apart, its knob and groove eased; an item a button's corner and rule, ringed as a control, a choice and a listed row set in and pointed at; a lamp's corner, line, room and size; a row of actions capped as groups are ruled apart, its room; a trail's crumbs apart, in the caption's size; an edge strip's room, its hairline along its top and the floating pane's shadow; a pane ringed inside its edge while the keys are on it and while a tab is offered to it, never a floating one for that; a card's proportion, ring and pointer, a group of sliders framed as a card; a brand's mark apart from its name, a preferences button framed as a button; the site bar's room and its hairline, the page in the system's face; a floating pane framed as a card, eased, its title bar's room, its hairline and its hand; a panel framed as a card, eased; the browser's own boxes - a text box, a select - framed and set as a button is, a text box pointed at as text");
        var palette = Harbour.INSTANCE.implementations().stream().filter(i -> i.node().component() instanceof Root).map(i -> i.target().token()).toList();
        assertEquals(semantic.stream().filter(t -> t instanceof Target.Color).map(Target::token).collect(Collectors.toSet()), Set.copyOf(palette),
                "the palette answers every semantic colour at the root");
        assertEquals(List.of("color-ink", "color-fill", "color-stroke", "color-scrollbar", "color-background", "color-border", "color-outline",
                        "color-column-rule", "color-box-shadow"),
                semantic.stream().filter(t -> t instanceof Target.Color).map(Target::token).toList(), "nine semantic colours");
    }

    /** No style names a colour: a shadow is drawn in the box shadow's colours, which the palette gives. */
    @Test
    void theStylesNameNoColour_theirShadowsReadThePalettes() {
        String editorial = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("editorial"), "editorial").css();
        assertTrue(editorial.contains("/* from harbour: root__color-box-shadow */\n.primary-button__color-box-shadow {\n"
                + "    --box-shadow-cast: #111936; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #1E2761;\n}\n"), "the palette's, on the element itself");
        assertTrue(editorial.contains("box-shadow: 0 6px 16px color-mix(in srgb, var(--box-shadow-cast) 12%, transparent);"), "the style's, reading it");
        assertTrue(editorial.contains("/* from harbour: control__color-outline--focus */\n.primary-button__color-outline--focus {\n"), "the ring's colour on focus");
        assertTrue(editorial.contains("/* from harbour: primary-button__color-border */\n.primary-button_go__color-border {\n    border-color: #1E2761;\n}\n"),
                "the box's line, its own");
    }

    @Test
    void everyTheme_flattensWhole_andAnswersOnlyWhatTheTaxonomyAsks() {
        for (Dressed theme : HouseStyles.THEMES.values()) {
            assertEquals(List.of(), theme.check(HOUSE), theme.toString());
            var flat = FlattenSheet.INSTANCE.flatten(HOUSE, theme, theme.toString());
            assertEquals(List.of(), flat.unanswered(), () -> theme + ": every target answered at the root");
            long marked = HOUSE.castings().stream().filter(c -> c.component() == hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks.Delimiter.INSTANCE).count();
            assertEquals(HOUSE.castings().size() * 32 + marked, flat.looks(),
                    () -> theme + ": every casting on every CSS target the semantic classes answer; the glyph a silence but the delimiter's sign - a close button's cross is the lexicon's word");
        }
        String sketchy = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("sketchy"), "sketchy").css();
        assertTrue(sketchy.contains("/* from base: root__shape-shadow */\n.danger-button__shape-shadow {\n    box-shadow: none;\n}\n"), "ink casts no shadow");
        assertTrue(sketchy.contains("/* from sketchy: control__shape-outline--focus */\n.danger-button__shape-outline--focus {\n"), "its own ring");
    }

    /**
     * Every house style writes every house word - Base all of them, as most styles draw them, and each style
     * those it draws otherwise: its own cross, and Sketchy its marks in pen, its emoji now text. A word is the
     * style's, never the palette's: the sheet is the same in every palette.
     */
    @Test
    void everyStyle_writesEveryWord_andItsCrossIsItsOwn() {
        var lexicon = HouseLexicon.INSTANCE.read();
        var write = WriteWord.INSTANCE;
        for (DesignStyle s : HouseStyles.STYLES) {
            assertEquals(List.of(), write.problems(lexicon, s), s.token());
            var sheet = FlattenLexicon.INSTANCE.flatten(lexicon, s, s.token());
            assertEquals(List.of(), sheet.unwritten(), s.token());
            assertEquals(15, sheet.written(), s.token());
        }
        assertEquals(List.of(), write.problems(lexicon, Base.INSTANCE), "Base writes them all");
        var crosses = new java.util.LinkedHashMap<String, String>();
        for (DesignStyle s : HouseStyles.STYLES) crosses.put(s.token(), write.write(s, HouseWords.Close.INSTANCE).orElseThrow().glyph());
        assertEquals(Map.of("editorial", "✕", "neo-brutalism", "╳", "neo-futurism", "⨯", "neumorphism", "✗", "glassmorphism", "×",
                "retro-futurism", "✘", "sketchy", "✖"), crosses, "each style's own cross, the close button's and every close row's alike");
        String sketchy = FlattenLexicon.INSTANCE.flatten(lexicon, Sketchy.INSTANCE, "sketchy").css();
        assertTrue(sketchy.contains(".word-pin::before {\n    content: \"⚲\";\n}\n"), "a pin in pen, no longer an emoji");
        assertTrue(sketchy.contains("/* from base */\n.word-size::before {\n    content: \"⤢\";\n}\n"), "what it does not draw itself, Base's");
    }

    @Test
    void aPageIsWornInTheStyleAndPaletteItsSlugNames_theStylesOwnOtherwise() {
        var styles = HouseStyles.INSTANCE;
        assertEquals("sketchy in marker", styles.dressed("sketchy").toString(), "a style alone is worn in its own palette");
        assertEquals("glassmorphism in frost", styles.dressed("glassmorphism").toString());
        assertEquals("neo-brutalism in neo-brutalism", styles.dressed("neo-brutalism").toString(), "the old design's own colours, a palette of their own");
        assertEquals("sketchy in forest", styles.dressed("sketchy_forest").toString());
        assertEquals("glassmorphism in harbour", styles.dressed("glassmorphism_harbour").toString(), "any style in any palette, asked for by name");
        assertEquals("sketchy in marker", styles.dressed("sketchy_sunset").toString(), "a palette the house has not yet: the style's own");
        assertEquals("editorial in harbour", styles.dressed("flat-morphism").toString(), "retired: the default");
        assertEquals("editorial in forest", styles.dressed("flat-morphism_forest").toString(), "the default style, in the palette named");
        assertEquals("editorial in harbour", styles.dressed(null).toString());
        assertEquals("neumorphism_forest", styles.slug(styles.dressed("neumorphism_forest")));
        assertEquals("neumorphism", styles.slug(styles.dressed("neumorphism_clay")), "a style in its own palette is named alone");
        assertEquals(56, HouseStyles.THEMES.size(), "seven styles, each in eight palettes");
        assertEquals("editorial", HouseStyles.THEMES.keySet().iterator().next(), "the default first");
        for (DesignStyle s : HouseStyles.STYLES)
            assertTrue(HouseStyles.PALETTES.contains(styles.own(s)), s.token() + " has a palette of its own among the house's");
    }

    /**
     * A palette says which styles it suits - as the old palettes said which designs - and a change of
     * style keeps the palette worn when it suits the new style, else takes the new style's own.
     */
    @Test
    void aChangeOfStyle_keepsThePaletteWhenItSuits_elseTakesTheStylesOwn() {
        var styles = HouseStyles.INSTANCE;
        var editorialInForest = styles.dressed("editorial_forest");
        assertEquals("sketchy in forest", styles.switchTo(editorialInForest, Sketchy.INSTANCE).toString(), "Forest suits Sketchy: kept");
        assertEquals("glassmorphism in frost", styles.switchTo(editorialInForest, Glassmorphism.INSTANCE).toString(), "Forest does not suit Glassmorphism: its own");
        assertEquals("neo-futurism in frost", styles.switchTo(styles.dressed("glassmorphism"), NeoFuturism.INSTANCE).toString(), "Frost suits Neo-futurism");
        assertEquals("retro-futurism in synthwave", styles.switchTo(styles.dressed("editorial"), RetroFuturism.INSTANCE).toString(), "Harbour does not suit Retro-futurism");
        assertEquals("editorial in harbour", styles.switchTo(styles.dressed("sketchy_harbour"), Editorial.INSTANCE).toString(), "a style's own always suits it");
        assertTrue(styles.fits(Neumorphism.INSTANCE, Harbour.INSTANCE));
        assertFalse(styles.fits(Glassmorphism.INSTANCE, Harbour.INSTANCE));
        assertEquals(java.util.Set.of(), NeoBrutalismColours.INSTANCE.suits(), "the old design offered its own colours to no other");
    }

    /**
     * The palettes are written by one rule, so they answer the same semantic colours in the same
     * places and differ only in what the colours are; and a style casts its shadow in whatever
     * palette it is worn in - its own sheet unchanged, only the colours the shadow reads.
     */
    @Test
    void theHousePalettes_answerAlike_andAStyleCastsInWhicheverItIsWornIn() {
        for (var p : HouseStyles.PALETTES)
            assertEquals(classes(Harbour.INSTANCE.implementations()), classes(p.implementations()), "one rule, eight sets of seeds: " + p.token());
        String harbour = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("editorial"), "editorial").css();
        String forest = FlattenSheet.INSTANCE.flatten(HOUSE, HouseStyles.THEMES.get("editorial_forest"), "editorial_forest").css();
        String shadow = "/* from editorial: button__shape-shadow--hover */\n.primary-button__shape-shadow--hover {\n";
        assertTrue(harbour.contains(shadow) && forest.contains(shadow), "the style's shadow, the same in both");
        assertTrue(harbour.contains("--box-shadow-cast: #111936; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #1E2761;"), "cast in the harbour's navy");
        assertTrue(forest.contains("/* from forest: root__color-box-shadow */\n.primary-button__color-box-shadow {\n"
                + "    --box-shadow-cast: #1A3829; --box-shadow-lit: #FFFFFF; --box-shadow-glow: #2D5F3F;\n}\n"), "cast in the forest's pine");
        assertTrue(forest.contains("/* from forest: control__color-outline--focus */"), "the ring, the forest's honey");
        assertTrue(forest.contains("outline-color: color-mix(in srgb, #D4A04C 55%, #D4DFCC);"));
    }

    /**
     * A mark alone is always a part, and flat in every state: its owner's morphism is the only one.
     * No border, no shadow, no lift - at rest, under the hand, pressed - in any style; under the hand
     * only its colours change. A close turns to danger, in every palette; an icon button is washed
     * in the ink it is drawn in; off, neither does.
     */
    @Test
    void aMarkAlone_isFlatInEveryState_onlyItsColoursChange_andACloseTurnsToDanger() {
        var close = new Casting(HouseControls.CloseButton.INSTANCE);
        var plus = new Casting(HouseControls.IconButton.INSTANCE);
        var lifts = List.<Target>of(Target.Shape.Border.INSTANCE, Target.Shape.Shadow.INSTANCE, Target.Motion.Transform.INSTANCE);
        for (var e : HouseStyles.THEMES.entrySet()) {
            Dressed theme = e.getValue();
            for (Casting mark : List.of(close, plus)) {
                for (Target t : lifts) {
                    assertEquals("root__" + t.token() + " from base", said(theme, mark, State.REST, t), e.getKey() + " " + mark + " at rest");
                    for (State state : List.of(State.HOVER, State.ACTIVE, State.DISABLED))
                        assertEquals("none", said(theme, mark, state, t), e.getKey() + " " + mark + " " + state + ": flat");
                }
            }
            String palette = theme.palette().token();
            assertEquals("close-button__color-background--hover from " + palette, said(theme, close, State.HOVER, Target.Color.Background.INSTANCE));
            assertTrue(body(theme, close, State.HOVER, Target.Color.Background.INSTANCE).contains("background-color: #DC2626;"), "danger, in every palette");
            assertTrue(body(theme, close, State.HOVER, Target.Color.Ink.INSTANCE).contains("color: #FFFFFF;"));
            assertTrue(body(theme, close, State.DISABLED, Target.Color.Background.INSTANCE).contains("background-color: transparent;"), "off: no danger");
            assertTrue(body(theme, plus, State.HOVER, Target.Color.Background.INSTANCE).contains("background-color: color-mix(in srgb, currentColor 12%, transparent);"),
                    "a wash of its own ink, on whatever holds it");
        }
    }

    private static String said(Dressed theme, Casting node, State state, Target target) {
        return switch (ResolveSemanticClass.INSTANCE.resolve(HOUSE, theme, node, state, target).outcome()) {
            case Resolution.Answered a -> a.className() + " from " + a.layer();
            case Resolution.Silent s -> "none";
            case Resolution.Unanswered u -> "unanswered";
        };
    }

    private static String body(Dressed theme, Casting node, State state, Target target) {
        return ((Resolution.Answered) ResolveSemanticClass.INSTANCE.resolve(HOUSE, theme, node, state, target).outcome()).body();
    }

    private static Set<String> classes(List<? extends Implementation<?>> implementations) {
        var tokens = SemanticTokens.INSTANCE;
        return implementations.stream().map(i -> tokens.casting(i.node()) + " " + i.target().token()).collect(Collectors.toSet());
    }
}
