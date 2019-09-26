package com.creativityskills.jotech.api;

import com.creativityskills.jotech.bean.product.ProductBeanI;
import com.creativityskills.jotech.model.Product;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductResource {
    @EJB
    private ProductBeanI productBeanI;

    //one for listing all products
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok().entity(productBeanI.getProductsList()).build();
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        Product product = new Product();
        product.setId(id);
        return Response.ok().entity(productBeanI.read(product)).build();
    }
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(String productString) {
        return Response.ok().entity(productBeanI.create(new Gson().fromJson(productString, Product.class))).build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(String productString) {
        return Response.ok().entity(productBeanI.update(new Gson().fromJson(productString, Product.class))).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(String productString) {
        return Response.ok().entity(productBeanI.delete(new Gson().fromJson(productString, Product.class))).build();
    }
}
