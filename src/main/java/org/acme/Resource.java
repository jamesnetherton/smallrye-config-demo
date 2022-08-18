package org.acme;

import org.eclipse.microprofile.config.Config;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/test")
public class Resource {

    private static final Logger LOG = Logger.getLogger(Resource.class.getName());

    @Inject
    Config config;

    @GET
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        config.getPropertyNames().forEach(name -> {
            if (name.startsWith("camel")) {
                config.getOptionalValue(name, String.class).ifPresentOrElse(value -> {
                    properties.put(name, value);
                }, () -> {
                    LOG.warnf("No value present for %s", name);
                });
            }
        });
        return properties;
    }
}
