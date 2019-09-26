package com.creativityskills.jotech.api;

import com.creativityskills.jotech.bean.cash.CashDrawerBeanI;
import com.creativityskills.jotech.model.CashDrawer;
import com.creativityskills.jotech.model.Product;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cash-drawers")
public class CashDrawerResource {
    @EJB
    private CashDrawerBeanI cashDrawerBeanI;

    //one for listing cash-drawers
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCashDrawers() {
        return Response.ok().entity(cashDrawerBeanI.getCashDrawerList()).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCashDrawer(@PathParam("id") int id) {
        CashDrawer cashDrawer = new CashDrawer();
        cashDrawer.setId(id);
        return Response.ok().entity(cashDrawerBeanI.read(cashDrawer)).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCashDrawer(String cashDrawerString) {
        return Response.ok().entity(cashDrawerBeanI.create(new Gson().fromJson(cashDrawerString, CashDrawer.class))).build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCashDrawer(String cashDrawerString) {
        return Response.ok().entity(cashDrawerBeanI.update(new Gson().fromJson(cashDrawerString, CashDrawer.class))).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(String cashDrawerString) {
        return Response.ok().entity(cashDrawerBeanI.delete(new Gson().fromJson(cashDrawerString, CashDrawer.class))).build();
    }
}
