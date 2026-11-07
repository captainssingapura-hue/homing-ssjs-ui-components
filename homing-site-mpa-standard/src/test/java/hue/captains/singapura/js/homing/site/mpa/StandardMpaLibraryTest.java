package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ExternalModule;
import hue.captains.singapura.js.homing.core.ModuleNameResolver;
import hue.captains.singapura.js.homing.core.SelfContent;
import hue.captains.singapura.js.homing.server.EsModuleGetAction;
import hue.captains.singapura.js.homing.server.ThemeRegistry;
import hue.captains.singapura.js.homing.site.Router;
import hue.captains.singapura.js.homing.site.Site;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** A site points a proxy at another address where it is built, for its own modules and no one else's. */
class StandardMpaLibraryTest {

    private static final String DEFAULT = "https://cdn.example/lib.mjs";
    private static final String MIRROR = "https://mirror.internal/lib.mjs";

    record Proxy() implements ExternalModule<Proxy>, SelfContent {
        static final Proxy INSTANCE = new Proxy();

        @Override public ExportsOf<Proxy> exports() { return new ExportsOf<>(INSTANCE, List.of()); }

        @Override public List<String> selfContent(ModuleNameResolver names) {
            return List.of("import lib from \"" + names.libraryUrl(Proxy.class, DEFAULT) + "\";");
        }
    }

    record Libs() implements Crate {
        static final Libs INSTANCE = new Libs();

        @Override public String name() { return "libs"; }
        @Override public List<CrateEntry> entries() { return List.of(new CrateEntry(Proxy.INSTANCE)); }
    }

    private static final Site SITE = new Site() {
        @Override public String name() { return "library-test"; }
        @Override public Router router() { return path -> Optional.empty(); }
    };

    private static StandardMpa mpa() {
        return new StandardMpa(new Brand("Library test"), ThemeRegistry.EMPTY, Libs.INSTANCE);
    }

    private static String served(StandardMpa mpa) {
        return ((EsModuleGetAction) mpa.registry(SITE).getActions().get("/module")).render(Proxy.INSTANCE);
    }

    @Test
    void aProxyImportsItsDefaultUntilTheSiteSays() {
        assertTrue(served(mpa()).contains("import lib from \"" + DEFAULT + "\";"));
    }

    @Test
    void theSiteServesTheAddressItWasBuiltWith() {
        assertTrue(served(mpa().withLibraryAt(Proxy.class, MIRROR)).contains("import lib from \"" + MIRROR + "\";"));
    }

    @Test
    void anotherSiteInTheProcessKeepsItsDefault() {
        var plain = mpa();
        var mirrored = plain.withLibraryAt(Proxy.class, MIRROR);
        assertTrue(served(mirrored).contains(MIRROR));
        assertTrue(served(plain).contains(DEFAULT), "the MPA it was made from, as it was");
    }
}
