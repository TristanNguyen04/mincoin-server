package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.server.ServerFactory;
import org.eclipse.jetty.server.Server;

public class CoinConfiguration extends Configuration {
    private ServerFactory serverFactory = new DefaultServerFactory();

    @JsonProperty("server")
    public ServerFactory getServerFactory(){
        return serverFactory;
    }

    @JsonProperty("server")
    public void setServerFactory(ServerFactory serverFactory){
        this.serverFactory = serverFactory;
    }
}
