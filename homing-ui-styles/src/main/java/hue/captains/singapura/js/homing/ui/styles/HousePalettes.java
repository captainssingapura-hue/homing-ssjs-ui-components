package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Accent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentEdge;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentTint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Border;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowBright;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowDim;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowFull;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowMedium;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowStrong;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ground;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HouseColour;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Inverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnAccent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnInverted;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Raised;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Recessed;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Ring;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeFirm;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeMedium;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Sheen;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Text;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Title;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.Wash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WashFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WashSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WayHovered;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.TextFaded;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HeadingInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KickerInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.OnDanger;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.DangerInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.WarningInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.SuccessInk;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWash;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashSoft;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashLight;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentWashStrong;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.AccentTintFaint;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeStrong;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeDeep;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.ShadeHeavy;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowWarm;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GlowFlare;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysCandidate;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.KeysLent;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.GrooveEdge;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.LineHovered;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * How a house palette is designed (RFC 0067, appendix Semantic Colours and Flavours, D32): from its {@link Seeds}, every
 * house colour - a seed as it is, a tint worked out from the seeds once, when the palette is read - so every house
 * palette curates the same colours, each the same way. How a palette arrives at its colours is its own concern, kept
 * here and nowhere in the stack: a sheet sees concrete colours only. A palette may answer a colour of its own before
 * this rule, and so curate it by hand.
 *
 * <p>A tint is two colours mixed in sRGB, as the house's old sheets mixed them, alpha premultiplied, so a seed that is
 * glass stays glass; a shadow's strength is its colour at that alpha.</p>
 */
public record HousePalettes() implements StatelessFunctionalObject {

    public static final HousePalettes INSTANCE = new HousePalettes();

    private static final ConcreteColor.Srgb CLEAR = new ConcreteColor.Srgb("transparent");

    /** Every house colour, from a palette's seeds: the compiler holds the switch to every one. */
    public ConcreteColor of(Seeds s, HouseColour colour) {
        return switch (colour) {
            case Raised c -> seed(s.raised());
            case Ground c -> seed(s.ground());
            case Recessed c -> seed(s.recessed());
            case Inverted c -> seed(s.inverted());
            case Wash c -> mix(seed(s.text()), 0.12, CLEAR);
            case WashFaint c -> mix(seed(s.text()), 0.08, CLEAR);
            case WashSoft c -> mix(seed(s.text()), 0.10, CLEAR);
            case Text c -> seed(s.text());
            case TextFaded c -> mix(seed(s.text()), 0.80, CLEAR);
            case OnInverted c -> seed(s.onInverted());
            case OnAccent c -> seed(s.onAccent());
            case WayHovered c -> mix(seed(s.title()), 0.50, mix(seed(s.text()), 0.80, CLEAR));
            case HeadingInk c -> mix(seed(s.title()), 0.50, seed(s.text()));
            case KickerInk c -> mix(seed(s.accentEdge()), 0.50, seed(s.text()));
            case OnDanger c -> seed("#FFFFFF");
            case DangerInk c -> seed("#7F1D1D");
            case WarningInk c -> seed("#713F12");
            case SuccessInk c -> seed("#1B5E20");
            case Border c -> seed(s.border());
            case GrooveEdge c -> mix(seed(s.text()), 0.45, CLEAR);
            case LineHovered c -> mix(seed(s.accent()), 0.50, seed(s.border()));
            case Title c -> seed(s.title());
            case Accent c -> seed(s.accent());
            case AccentEdge c -> seed(s.accentEdge());
            case Ring c -> mix(seed(s.accent()), 0.55, seed(s.border()));
            case AccentTint c -> mix(seed(s.accent()), 0.22, seed(s.raised()));
            case AccentTintFaint c -> mix(seed(s.accent()), 0.12, seed(s.raised()));
            case AccentWash c -> mix(seed(s.accent()), 0.18, CLEAR);
            case AccentWashFaint c -> mix(seed(s.accent()), 0.08, CLEAR);
            case AccentWashSoft c -> mix(seed(s.accent()), 0.15, CLEAR);
            case AccentWashLight c -> mix(seed(s.accent()), 0.12, CLEAR);
            case AccentWashStrong c -> mix(seed(s.accent()), 0.28, CLEAR);
            case KeysCandidate c -> mix(seed(s.accent()), 0.45, CLEAR);
            case KeysLent c -> mix(seed(s.accent()), 0.25, CLEAR);
            case ShadeFaint c -> mix(seed(s.inverted()), 0.04, CLEAR);
            case ShadeLight c -> mix(seed(s.inverted()), 0.08, CLEAR);
            case ShadeSoft c -> mix(seed(s.inverted()), 0.10, CLEAR);
            case ShadeMedium c -> mix(seed(s.inverted()), 0.12, CLEAR);
            case ShadeFirm c -> mix(seed(s.inverted()), 0.16, CLEAR);
            case ShadeStrong c -> mix(seed(s.inverted()), 0.18, CLEAR);
            case ShadeDeep c -> mix(seed(s.inverted()), 0.20, CLEAR);
            case ShadeHeavy c -> mix(seed(s.inverted()), 0.22, CLEAR);
            case Sheen c -> mix(seed(s.raised()), 0.60, CLEAR);
            case GlowFaint c -> mix(seed(s.title()), 0.14, CLEAR);
            case GlowDim c -> mix(seed(s.title()), 0.25, CLEAR);
            case GlowSoft c -> mix(seed(s.title()), 0.30, CLEAR);
            case GlowMedium c -> mix(seed(s.title()), 0.40, CLEAR);
            case GlowWarm c -> mix(seed(s.title()), 0.45, CLEAR);
            case GlowStrong c -> mix(seed(s.title()), 0.55, CLEAR);
            case GlowBright c -> mix(seed(s.title()), 0.60, CLEAR);
            case GlowFull c -> mix(seed(s.title()), 0.70, CLEAR);
            case GlowFlare c -> mix(seed(s.title()), 0.80, CLEAR);
        };
    }

    /**
     * One colour so much over another, worked out as the house's own tints are - in sRGB, alpha premultiplied: for a
     * module grafting colours of its own, its sub-palette's word on them from what a palette gives the house's.
     */
    public ConcreteColor.Srgb tint(ConcreteColor.Srgb colour, double share, ConcreteColor.Srgb over) { return mix(colour, share, over); }

    private static ConcreteColor.Srgb seed(String written) { return new ConcreteColor.Srgb(written); }

    /** {@code a} at {@code share}, {@code b} the rest, in sRGB, alpha premultiplied - CSS's {@code color-mix(in srgb, a p%, b)}. */
    static ConcreteColor.Srgb mix(ConcreteColor.Srgb a, double share, ConcreteColor.Srgb b) {
        double alpha = a.alpha() * share + b.alpha() * (1 - share);
        if (alpha == 0) return CLEAR;
        int red = channel(a.red() * a.alpha() * share + b.red() * b.alpha() * (1 - share), alpha);
        int green = channel(a.green() * a.alpha() * share + b.green() * b.alpha() * (1 - share), alpha);
        int blue = channel(a.blue() * a.alpha() * share + b.blue() * b.alpha() * (1 - share), alpha);
        return new ConcreteColor.Srgb(red, green, blue, Math.round(alpha * 1000) / 1000.0);
    }

    private static int channel(double premultiplied, double alpha) { return (int) Math.max(0, Math.min(255, Math.round(premultiplied / alpha))); }
}
