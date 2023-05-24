package org.acme;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    private static final Logger LOG = Logger.getLogger(FruitResource.class);
    private List<Fruit> fruits = new ArrayList<>();

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @GET
    public Set<Fruit> list() {
        LOG.info("SYNC Request received");
        return new LinkedHashSet<>(fruits);
    }

    @GET
    @Path("{id}")
    public Fruit getOne(@PathParam("id") String id) throws BadRequestException {
        int idNum = Integer.parseInt(id) - 1;
        try {
            return fruits.get(idNum);
        } catch(Exception err) {
            throw new BadRequestException("Fruit doens't exists");
        }
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return new LinkedHashSet<>(fruits);
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return new LinkedHashSet<>(fruits);
    }
}
