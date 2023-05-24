package org.acme;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
    import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    private final FruitService service;
    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    public GreetingResource() {
        this.service = RestClientBuilder.newBuilder()
            .baseUri(URI.create("http://localhost:8080"))
            .build(FruitService.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Set<Fruit>> hello() {
        LOG.info("ASYNC Request received");
        return service.getAll();
    }
}
