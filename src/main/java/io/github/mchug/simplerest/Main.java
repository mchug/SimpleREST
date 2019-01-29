package io.github.mchug.simplerest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main
{

    public static String BASE_URI;
    public static String protocol;
    public static String host;
    public static String path;
    public static String port;

    static
    {
        protocol = "http://";
        host = System.getenv("HOSTNAME");
        port = System.getenv("PORT");
        path = "myapp";
    }

    public static HttpServer startServer()
    {

        BASE_URI = protocol
                + (host == null ? "localhost" : host)
                + ":"
                + (port == null ? "8080" : port)
                + "/" + path + "/";

        final ResourceConfig rc = new ResourceConfig().packages(Main.class.getPackage().getName());

        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler(System.getProperty("user.dir") + "/static/");
        httpServer.getServerConfiguration().addHttpHandler(staticHttpHandler, "/static/");

        System.out.println("static root = " + staticHttpHandler.getDefaultDocRoot());

        return httpServer;
    }

    public static void main(String[] args) throws Exception
    {

        if (args.length > 1)
        {
            host = args[0];
            port = args[1];
        }

        final HttpServer server = startServer();

        System.out.println("static url = " + BASE_URI.replace(path, "static"));

        System.out.println();

        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl", BASE_URI));
        System.out.println("Hit enter to stop server...");

        System.in.read();
        server.stop();
    }
}
