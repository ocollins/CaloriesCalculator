package edu.matc.controller;

/**
 * Created by student on 3/18/17.
 */
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@WebListener
public class ContextListener implements ServletContextListener {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("####################The application started");

        ServletContext context = event.getServletContext();
        //context.setAttribute("test", "TestString");

//        Client client = ClientBuilder.newClient();
//        String url = "http://localhost:8080/CaloriesCalculator/activities";
//        WebTarget target = client.target(url);
//        String response = target.request().get(String.class);
//        context.setAttribute("AllActivities", response);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("##################The application stopped");

    }
}
