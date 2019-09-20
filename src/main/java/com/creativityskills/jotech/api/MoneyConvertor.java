package com.creativityskills.jotech.api;

import com.creativityskills.jotech.bean.util.MoneyConvertorI;
import com.creativityskills.jotech.model.Denomination;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Path("/mc")
public class MoneyConvertor {
    @EJB
    private MoneyConvertorI moneyConvertorI;

    @GET
    @Path("/get-denominations/{amount}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDenominationsForMoney(@PathParam("amount") BigDecimal amount) {
        Map m = moneyConvertorI.getDenominationsForMoney(amount);
        return Response.ok().entity(m).build();
    }

    @POST
    @Path("/get-money-value")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoneyValueFromDenominations(String moneyMap) {
        System.out.println(moneyMap);
        Gson gson = new Gson();
        Map<String, Integer> m = gson.fromJson(moneyMap, Map.class);
        Map<Denomination, Integer> map = new HashMap<>();
        for (Map.Entry m_ : m.entrySet()) {
            double value = (double) m_.getValue();
            map.put(Denomination.valueOf(m_.getKey().toString()), (int) value);
        }
        BigDecimal amount = moneyConvertorI.getMoneyValueFromDenominations(map);
        Map<String, BigDecimal> m_ = new HashMap<>();
        m_.put("amount", amount);

        return Response.ok().entity(gson.toJson(m_)).build();
    }

}
