package com.creativityskills.jotech.api;

import com.creativityskills.jotech.bean.stock.StockBeanI;
import com.creativityskills.jotech.model.Stock;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stock")
public class StockResource {
    @EJB
    private StockBeanI stockBeanI;

    //one for listing all stock
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok().entity(stockBeanI.getStockList()).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        Stock stock = new Stock();
        stock.setId(id);
        return Response.ok().entity(stockBeanI.read(stock)).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStock(String stockString) {
        return Response.ok().entity(stockBeanI.create(new Gson().fromJson(stockString, Stock.class))).build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStock(String stockString) {
        return Response.ok().entity(stockBeanI.update(new Gson().fromJson(stockString, Stock.class))).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStock(String stockString) {
        return Response.ok().entity(stockBeanI.delete(new Gson().fromJson(stockString, Stock.class))).build();
    }

}
