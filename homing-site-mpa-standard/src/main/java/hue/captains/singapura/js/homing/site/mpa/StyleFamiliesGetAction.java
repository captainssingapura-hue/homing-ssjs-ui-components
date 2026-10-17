package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.design.styling.InstalledStyling;
import hue.captains.singapura.js.homing.design.styling.StyleFamilies;
import hue.captains.singapura.js.homing.server.EmptyParam;
import hue.captains.singapura.js.homing.server.ResourceNotFound;
import hue.captains.singapura.tao.http.action.GetAction;
import hue.captains.singapura.tao.http.action.Param;
import hue.captains.singapura.tao.http.action.ParamMarshaller;
import hue.captains.singapura.tao.http.action.TypedContent;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * GET {@code /style-families} - the installed styling's family files, for reading (RFC 0067, appendix The Style Jars):
 * in dev mode made afresh from the casting files on the file system on every request, deployed the ones the
 * instance's jar was built with ({@link StyleFamilies}).
 *
 * <pre>
 * /style-families                        { "dev": bool, "styles": [token], "families": [token] }
 * /style-families?style=&lt;s&gt;&amp;family=&lt;f&gt;   the style's file of the family, text/css
 * </pre>
 */
public final class StyleFamiliesGetAction
        implements GetAction<RoutingContext, StyleFamiliesGetAction.Query, EmptyParam.NoHeaders, StyleFamiliesGetAction.Served> {

    public static final String ROUTE = "/style-families";

    /** What is asked for: both, a file; neither, what there is. */
    public record Query(String style, String family) implements Param._QueryString {}

    /** A file, or the list. */
    public record Served(String body, String contentType) implements TypedContent {}

    /** Made once, from the styling installed at bootstrap. */
    private final AtomicReference<StyleFamilies> families = new AtomicReference<>();

    @Override
    public ParamMarshaller._QueryString<RoutingContext, Query> queryStrMarshaller() {
        return ctx -> new Query(ctx.request().getParam("style"), ctx.request().getParam("family"));
    }

    @Override
    public ParamMarshaller._Header<RoutingContext, EmptyParam.NoHeaders> headerMarshaller() {
        return ctx -> new EmptyParam.NoHeaders();
    }

    @Override
    public CompletableFuture<Served> execute(Query query, EmptyParam.NoHeaders headers) {
        try {
            StyleFamilies read = families.updateAndGet(f -> f != null ? f : StyleFamilies.of(InstalledStyling.get()));
            boolean style = query.style() != null && !query.style().isBlank(), family = query.family() != null && !query.family().isBlank();
            if (!style && !family) return CompletableFuture.completedFuture(new Served(list(read), "application/json; charset=utf-8"));
            if (!style || !family) return CompletableFuture.failedFuture(notFound(ROUTE, "a file is asked for by both its style and its family"));
            return CompletableFuture.completedFuture(new Served(read.read(query.style(), query.family()), "text/css; charset=utf-8"));
        } catch (RuntimeException e) {
            return CompletableFuture.failedFuture(notFound(query.style() + "/" + query.family(), e.getMessage()));
        }
    }

    private static String list(StyleFamilies read) {
        return "{\"dev\":" + read.dev() + ",\"styles\":" + array(read.styles()) + ",\"families\":" + array(read.families()) + "}";
    }

    private static String array(List<String> tokens) {
        return tokens.stream().map(t -> "\"" + t.replace("\\", "\\\\").replace("\"", "\\\"") + "\"").collect(Collectors.joining(",", "[", "]"));
    }

    private static ResourceNotFound notFound(String resource, String why) {
        return new ResourceNotFound(new ResourceNotFound._InternalError(null, why), new ResourceNotFound._ExternalError(resource, why));
    }
}
