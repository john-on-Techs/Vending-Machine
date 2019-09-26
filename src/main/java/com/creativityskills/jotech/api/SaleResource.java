package com.creativityskills.jotech.api;

import com.creativityskills.jotech.bean.product.ProductBeanI;
import com.creativityskills.jotech.bean.sale.SaleBeanI;
import com.creativityskills.jotech.model.Product;
import com.creativityskills.jotech.model.Sale;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sales")
public class SaleResource {
    @EJB
    private SaleBeanI saleBeanI;

    //one for listing all sales
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSales() {
        return Response.ok().entity(saleBeanI.getSaleList()).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSale(@PathParam("id") int id) {
       Sale sale = new Sale();
       sale.setId(id);
        return Response.ok().entity(saleBeanI.read(sale)).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSale(String saleString) {
        return Response.ok().entity(saleBeanI.create(new Gson().fromJson(saleString, Sale.class))).build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSale(String saleString) {
        return Response.ok().entity(saleBeanI.update(new Gson().fromJson(saleString, Sale.class))).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSale(String saleString) {
        return Response.ok().entity(saleBeanI.delete(new Gson().fromJson(saleString, Sale.class))).build();
    }
}
