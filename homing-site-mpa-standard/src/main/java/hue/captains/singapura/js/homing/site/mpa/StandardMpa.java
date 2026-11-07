package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.AppModule;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.DesignRenderer;
import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.ExternalModule;
import hue.captains.singapura.js.homing.core.ModuleNameResolver;
import hue.captains.singapura.js.homing.core.NoDesign;
import hue.captains.singapura.js.homing.core.SimpleAppResolver;
import hue.captains.singapura.js.homing.core.util.ResourceReader;
import hue.captains.singapura.js.homing.core.util.ResourceReaders;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.design.styling.StylingRenderer;
import hue.captains.singapura.js.homing.preferences.PreferencesRegistry;
import hue.captains.singapura.js.homing.server.AppMeta;
import hue.captains.singapura.js.homing.server.HomingActionRegistry;
import hue.captains.singapura.js.homing.server.QueryParamResolver;
import hue.captains.singapura.js.homing.server.ServedModules;
import hue.captains.singapura.js.homing.server.ThemeRegistry;
import hue.captains.singapura.js.homing.site.Site;
import hue.captains.singapura.js.homing.site.SiteGetAction;
import hue.captains.singapura.js.homing.site.SiteHost;
import hue.captains.singapura.tao.http.action.ActionRegistry;
import hue.captains.singapura.tao.http.action.GetAction;
import hue.captains.singapura.tao.http.action.PostAction;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The standard MPA for one site: its brand, the themes it offers, the crates
 * it serves. From these, {@link #page} makes a JS app a page under the
 * chrome, and {@link #registry} puts the framework's routes beside the
 * site's own — {@code /module} for the JS, {@code /css-content} for the
 * sheets, {@code /themes} for the preferences menu, {@code /app} as the
 * flat address — with the site's catch-all last.
 *
 * <p>This is the one declaration a themed site makes. It knows nothing of
 * how the site routes: a page made here is a {@link hue.captains.singapura.js.homing.site.Placed}
 * navigable, and whatever router hands it out may tell it where it is.</p>
 *
 * <p>The standard IMPLEMENTATION of core's {@link Mpa}: the page it makes
 * wears the chrome, and the chrome's button opens the preferences - which is
 * why it lives here, beside the dialog and the preferences it is built of,
 * and core keeps only the definition.</p>
 */
public final class StandardMpa implements Mpa {

    private final Brand brand;
    private final Styling styling;
    private final ThemeRegistry themes;
    private final PreferencesRegistry preferences;
    private final List<Crate> crates;
    private final Map<Class<? extends ExternalModule<?>>, String> libraryUrls;
    private final ModuleNameResolver names;
    private final HomingActionRegistry framework;
    private final ThemesGetAction themesAction;
    private final StyleFamiliesGetAction familiesAction;

    /** @param styling the site's styling; null for a site that names its themes and has none */
    private StandardMpa(Brand brand, Styling styling, ThemeRegistry themes, PreferencesRegistry preferences, List<Crate> crates,
                        Map<Class<? extends ExternalModule<?>>, String> libraryUrls) {
        this.brand  = Objects.requireNonNull(brand, "StandardMpa.brand");
        this.styling = styling;
        this.themes = themes == null ? ThemeRegistry.EMPTY : themes;
        this.preferences = preferences == null ? DefaultPreferences.INSTANCE : preferences;
        var all = new ArrayList<Crate>(crates);
        if (all.stream().noneMatch(c -> c == MpaCrate.INSTANCE)) all.add(MpaCrate.INSTANCE);
        this.crates = List.copyOf(all);
        this.libraryUrls = Map.copyOf(libraryUrls);
        this.names  = new QueryParamResolver("/module", this.libraryUrls);
        var apps = new ArrayList<AppModule<?, ?>>();
        for (EsModule<?> m : new ServedModules(this.crates).byName().values()) {
            if (m instanceof AppModule<?, ?> a) apps.add(a);
        }
        DesignRenderer design = styling == null ? NoDesign.INSTANCE : new StylingRenderer(styling);
        // the modules' scripts, like the family files: in dev mode from the source folders homing.devRoot names, else from the jars
        this.framework = new HomingActionRegistry(names, new SimpleAppResolver(apps), ResourceReaders.INSTANCE.fromSystemProperty(),
                                                  this.themes, new AppMeta(brand.label()), this.crates, design);
        this.themesAction = new ThemesGetAction(this.themes);
        this.familiesAction = styling == null ? null : new StyleFamiliesGetAction(styling);
    }

    /** A site's MPA: the brand on the bar, the themes it offers, the crates whose modules it serves; the default preferences. */
    public StandardMpa(Brand brand, ThemeRegistry themes, Crate... crates) {
        this(brand, null, themes, null, List.of(crates), Map.of());
    }

    /**
     * As {@link #StandardMpa(Brand, ThemeRegistry, Crate...)}, with the site's own
     * preferences: a {@link PreferencesRegistry} the site extends, whose
     * instance the site's crate declares. The bar's button opens it.
     */
    public StandardMpa(Brand brand, ThemeRegistry themes, PreferencesRegistry preferences, Crate... crates) {
        this(brand, null, themes, preferences, List.of(crates), Map.of());
    }

    /**
     * A site's MPA over its styling (RFC 0067, appendix The Style Jars): the brand on the bar, the styling - the styles
     * and palettes the instance includes, registered in their two stages - and the crates whose modules it serves; the
     * default preferences. The styling is handed to the server, for what is made from it, and the themes the picker
     * offers are its.
     */
    public StandardMpa(Brand brand, Styling styling, Crate... crates) {
        this(brand, styling, new StylingThemes(styling), null, List.of(crates), Map.of());
    }

    /** As {@link #StandardMpa(Brand, Styling, Crate...)}, with the site's own preferences. */
    public StandardMpa(Brand brand, Styling styling, PreferencesRegistry preferences, Crate... crates) {
        this(brand, styling, new StylingThemes(styling), preferences, List.of(crates), Map.of());
    }

    /**
     * This site with {@code proxy} importing its 3rd-party library from {@code url}: for a deployment that cannot reach
     * the proxy's default. A new MPA, this one as it was: a site makes its pages with the one it serves.
     */
    public StandardMpa withLibraryAt(Class<? extends ExternalModule<?>> proxy, String url) {
        var urls = new LinkedHashMap<Class<? extends ExternalModule<?>>, String>(libraryUrls);
        urls.put(proxy, url);
        return new StandardMpa(brand, styling, themes, preferences, crates, urls);
    }

    @Override public Brand brand() { return brand; }

    @Override public ThemeRegistry themes() { return themes; }

    /** The preferences the bar's button opens: the site's, or the default. */
    public PreferencesRegistry preferences() { return preferences; }

    /** The address a served module is imported from, without the theme. */
    @Override
    public String moduleUrl(EsModule<?> module) {
        return names.resolve(module).basePath();
    }

    // ── Pages ─────────────────────────────────────────────────────────────────

    /** {@code app} bound to {@code params}, as a page under the chrome. */
    @Override
    public <P extends AppModule._Param, M extends AppModule<P, M>> AppPage<P, M> page(M app, P params) {
        return new AppPage<>(app, params, this);
    }

    /** A paramless app as a page under the chrome. */
    @Override
    public <M extends AppModule<AppModule._None, M>> AppPage<AppModule._None, M> page(M app) {
        return new AppPage<>(app, AppModule._None.INSTANCE, this);
    }

    /** {@code app} bound to {@code params}, as a page that fills the viewport: the chrome's slot the whole of the rest. */
    @Override
    public <P extends AppModule._Param, M extends AppModule<P, M>> AppPage<P, M> fillingPage(M app, P params) {
        return new AppPage<>(app, params, this).filling();
    }

    // ── Serving ───────────────────────────────────────────────────────────────

    /**
     * The framework's routes, then the site's catch-all. Insertion order is
     * the order the host mounts them, and {@code /*} must come last or it
     * would answer for {@code /module} too.
     */
    @Override
    public ActionRegistry<RoutingContext> registry(Site site) {
        var siteActions = SiteHost.INSTANCE.registry(site).getActions();
        var gets = new LinkedHashMap<String, GetAction<RoutingContext, ?, ?, ?>>();
        gets.putAll(framework.getActions());
        gets.put(ThemesGetAction.ROUTE, themesAction);
        if (familiesAction != null) gets.put(StyleFamiliesGetAction.ROUTE, familiesAction);   // a styling's family files, for reading
        siteActions.forEach((route, action) -> { if (!route.equals(SiteGetAction.ROUTE)) gets.put(route, action); });
        gets.put(SiteGetAction.ROUTE, siteActions.get(SiteGetAction.ROUTE));
        var posts = Map.copyOf(framework.postActions());
        return new ActionRegistry<>() {
            @Override public Map<String, GetAction<RoutingContext, ?, ?, ?>> getActions() { return gets; }
            @Override public Map<String, PostAction<RoutingContext, ?, ?, ?>> postActions() { return posts; }
        };
    }
}
