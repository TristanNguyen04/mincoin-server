package org.example.resources;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.core.CoinRequest;
import org.example.service.CoinService;

import java.util.List;

@Path("/coins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinResource {
    private final CoinService coinService;

    public CoinResource(CoinService coinService){
        this.coinService = coinService;
    }

    @POST
    public List<Double> getMinimumCoins(CoinRequest coinRequest){
        return coinService.getMinimumCoins(coinRequest.getTargetAmount(), coinRequest.getDenominations());
    }
}
