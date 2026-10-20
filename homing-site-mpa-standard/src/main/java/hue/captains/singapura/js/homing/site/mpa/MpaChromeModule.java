package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.component.keyboard.KeyboardStewardModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SiteBarModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;

import java.util.List;

/**
 * The site's chrome: a widget, with no design of its own - the site's bar at its head (the brand, the
 * trail, the preferences button), over the slot the page's app is mounted in, laid out by the chrome's
 * own layout. It stands on the page's ground, which the page wears; it makes no element and touches
 * none, so every look on it is a designed component's.
 *
 * <p>The page ({@link AppPage}) constructs it with what the server stamped - the brand, the trail the
 * router wrote, the preferences, and whether the page fills the viewport - attaches its root, and
 * mounts the app in its slot.</p>
 */
public record MpaChromeModule() implements Widget<MpaChromeModule.Chrome, MpaChromeModule> {

    /**
     * What the server stamps into the page for the chrome, as a frozen object: {@code { brand: { label,
     * home }, crumbs: [ { text, to } ], preferences: { module, view }, fill }}.
     */
    public record Chrome() implements Widget._Param {}

    /** The class: {@code new MpaChrome(branch, params)}; {@code root}, {@code main}, {@code keyboard}, {@code trail}, {@code preferences}. */
    public record MpaChrome() implements Widget._Class<Chrome, MpaChromeModule> {
        @Override public String summary() { return "The site's chrome: its bar - the brand, the trail, the preferences - over the slot the page's app is mounted in."; }
    }

    public static final MpaChromeModule INSTANCE = new MpaChromeModule();

    @Override public String title() { return "Site chrome"; }

    @Override public Class<Chrome> paramsType() { return Chrome.class; }

    @Override
    public ImportsFor<MpaChromeModule> imports() {
        return ImportsFor.<MpaChromeModule>builder()
                .add(new ModuleImports<>(List.of(new ChromeLayoutModule.ChromeLayout()), ChromeLayoutModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SiteBarModule.SiteBar()), SiteBarModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferencesButtonModule.PreferencesButton()), PreferencesButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeyboardStewardModule.KeyboardStewardInstance()), KeyboardStewardModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MpaChromeModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new MpaChrome()));
    }
}
