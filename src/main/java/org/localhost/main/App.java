package org.localhost.main;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.localhost.chat.WebSocketChatServlet;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ctx.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        ResourceHandler rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);
        rHandler.setResourceBase("public_html");

        HandlerList hList = new HandlerList();
        hList.setHandlers(new Handler[] {rHandler, ctx});
        server.setHandler(hList);

        server.start();
        System.out.println("Started servlet");
        server.join();
    }
}
