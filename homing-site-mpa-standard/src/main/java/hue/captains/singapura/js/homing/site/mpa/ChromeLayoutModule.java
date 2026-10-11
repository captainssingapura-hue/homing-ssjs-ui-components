package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The chrome's layout, curated by hand - a layout of its own purpose (RFC 0068), not a term of the
 * algebra: a head along the top, kept in view, over the page's slot, which takes the rest - a reading
 * column, the standard setting, or the whole of the viewport where the page says it fills. Flat: no
 * look, nothing drawn; it places what its owner hands it. It implements no node of the taxonomy: it is
 * the chrome's code.
 */
public record ChromeLayoutModule() implements DomModule<ChromeLayoutModule> {

    /** The class: {@code new ChromeLayout(branch, { fill })}; {@code root}, {@code head(c)}, {@code slot()} - the chrome's code, no component. */
    public record ChromeLayout() implements Exportable._Class<ChromeLayoutModule> {}

    public static final ChromeLayoutModule INSTANCE = new ChromeLayoutModule();

    @Override
    public ImportsFor<ChromeLayoutModule> imports() {
        return ImportsFor.<ChromeLayoutModule>builder()
                .add(new ModuleImports<>(List.of(
                        new MpaStyles.mpa_page(),
                        new MpaStyles.mpa_root(),
                        new MpaStyles.mpa_head(),
                        new MpaStyles.mpa_main(),
                        new MpaStyles.mpa_main_full(),
                        new MpaStyles.mpa_page_app()
                ), MpaStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ChromeLayoutModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ChromeLayout()));
    }
}
