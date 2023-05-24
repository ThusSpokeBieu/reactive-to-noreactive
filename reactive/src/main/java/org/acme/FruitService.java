package org.acme;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/fruits")
@RegisterRestClient
public interface FruitService {

    @GET
    CompletionStage<Set<Fruit>> getAll();
}
