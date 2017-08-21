package in.yunyul.vertx.console.pools;

import in.yunyul.vertx.console.base.ConsolePage;
import io.vertx.core.Vertx;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.Router;

@SuppressWarnings("unused")
public class PoolsConsolePage implements ConsolePage {
    private static final String JSON_CONTENT_TYPE = "application/json";

    private final MetricsService metricsService;

    /**
     * Creates the pools console page, using the provided metrics service
     *
     * @param metricsService the metrics service to retrieve data from
     * @return the console page
     */
    public static PoolsConsolePage create(MetricsService metricsService) {
        return new PoolsConsolePage(metricsService);
    }

    private PoolsConsolePage(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public void mount(Vertx vertx, Router router, String basePath) {
        router.route(basePath + "/pools").produces(JSON_CONTENT_TYPE).handler(ctx ->
                ctx.response().putHeader("content-type", JSON_CONTENT_TYPE).end(
                        metricsService.getMetricsSnapshot("vertx.pools").toBuffer()
                ));
    }

    @Override
    public String getLoaderFileName() {
        return "/js/pools.js";
    }
}
