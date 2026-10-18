package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * An implementation (RFC 0067, D53): how a component in a role is drawn in a style - with CSS of its own
 * ({@link CssImplementation}), or by delegation to another implementation ({@link Delegation}); which, its type says.
 * Nothing else: it holds no parts - those are a declaration's ({@link CastingDeclaration}) - and no CSS: that is content,
 * a resource at its canonical name. Its style, component and role are in its type - the role root's for the component
 * standalone.
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the component it draws
 * @param <R>  the role it draws it in: {@code RoleRoot} standalone
 */
public sealed interface CastingImplementation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends StatelessFunctionalObject permits CssImplementation, Delegation {}
