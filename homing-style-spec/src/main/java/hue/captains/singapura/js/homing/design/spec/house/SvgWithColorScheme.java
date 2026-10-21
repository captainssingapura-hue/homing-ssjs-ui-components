package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * An SVG picture in a colour scheme (RFC 0067, D56; owner, 21 Oct), as a look is an implementation with CSS of its own:
 * its content an SVG resource at its class's name - {@code homing-svg/…/styles/base/PacmanSvg.svg} - in a jar of content;
 * what it keeps here, typed by its scheme, are the palette's colours it names. A colour its scheme cannot see does not
 * compile; the file is held to the list both ways ({@link IconSvgs#problems}). An icon palette's SVG picture imports one
 * ({@link IconPicture.Svg}).
 *
 * <pre>{@code
 * public record PacmanSvg() implements SvgWithColorScheme<BaseScheme> {
 *     @Override public List<Palettable<? super BaseScheme, ?>> colours() {
 *         return List.of(new BaseColours.Accent(), new BaseColours.Text(), new BaseColours.DangerInk());
 *     }
 * }
 * }</pre>
 *
 * @param <SC> the colour scheme it is drawn in
 */
public interface SvgWithColorScheme<SC extends ColorScheme> extends StatelessFunctionalObject {

    /** The palette's colours its file names, each once - colours its scheme sees, by the compiler. None for a picture whose file is not there yet. */
    default List<Palettable<? super SC, ?>> colours() { return List.of(); }

    /** Its name, as the page says it: its class's, without {@code Svg}, in words joined by hyphens - {@code pacman}. */
    default String name() { return NodeName.ofType(getClass(), "Svg").value(); }
}
