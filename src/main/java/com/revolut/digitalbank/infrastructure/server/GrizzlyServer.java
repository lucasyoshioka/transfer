package com.revolut.digitalbank.infrastructure.server;

import com.revolut.digitalbank.infrastructure.PropertiesUtil;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

public class GrizzlyServer {

    private static final Logger log = Logger.getLogger(GrizzlyServer.class.getName());

    private HttpServer server;

    private String url;

    private String resourcePackage;

    public GrizzlyServer() throws IOException {
        url = PropertiesUtil.get("grizzly.url");
        resourcePackage = PropertiesUtil.get("grizzly.resource.package");
    }

    public void start() throws IOException {
        startServer();
        System.in.read();
        stopServer();
    }

    public void startServer() {
        ResourceConfig config = new ResourceConfig().packages(resourcePackage);
        URI uri = URI.create(url);
        server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }

    public void stopServer() {
        server.stop();
    }

}
