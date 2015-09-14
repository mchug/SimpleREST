package io.github.mchug.simplerest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static String BASE_URI;
    public static String protocol;
    public static String host;
    public static String path;
    public static String port;

    static {
        protocol = "http://";
        host = System.getenv("HOSTNAME");
        port = System.getenv("PORT");
        path = "myapp";
    }

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {

        BASE_URI = protocol
                + (host == null ? "localhost" : host)
                + ":"
                + (port == null ? "8080" : port)
                + "/" + path + "/";

        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages(Main.class.getPackage().getName());

//        rc.register(NotFoundException.class);
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        if (args.length > 1) {
            host = args[0];
            port = args[1];
        }

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
