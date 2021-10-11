import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.*;
import org.eclipse.jetty.util.resource.*;

import java.net.*;

public class WebServer {

    public static void main(String[] args) throws Exception {
        var server = new Server(8082);
        ClassLoader cl = WebServer.class.getClassLoader();
        URL f = cl.getResource("spinner.html");
        if (f == null) {
            throw new RuntimeException("Unable to find resource directory");
        }
        // Resolve file to directory
        URI webRootUri = f.toURI().resolve("./").normalize();
        System.err.println("WebRoot is " + webRootUri);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource(webRootUri));
        server.setHandler(context);
        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed", "true");
        context.addServlet(holderPwd, "/");
        server.start();
        server.join();
    }
}
