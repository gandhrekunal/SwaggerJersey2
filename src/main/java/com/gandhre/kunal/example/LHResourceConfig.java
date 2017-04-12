package com.gandhre.kunal.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class load Swagger API Docs
 *
 * @author Kunal Gandhre
 * @since 1.0
 * @Created Nov 9, 2016
 */
public class LHResourceConfig extends ResourceConfig {
    private static final Logger log = LoggerFactory
            .getLogger(LHResourceConfig.class);

    /**
     * This method check and load Swagger API doc
     */
    @SuppressWarnings("unused")
    public LHResourceConfig() {
        if (true) {
            packages("io.swagger.jaxrs.listing");
            register(io.swagger.jaxrs.listing.ApiListingResource.class);
            register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
            if (log.isInfoEnabled()) {
                log.info("Swagger API Doc is ENABLED");
            }
        } else {
            register(
                    org.glassfish.jersey.media.multipart.MultiPartFeature.class);
            if (log.isInfoEnabled()) {
                log.info("Swagger API Doc is NOT ENABLED");
            }
        }
    }
}
