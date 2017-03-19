package edu.matc.controller;

/**
 * Display servlet, wich will call index.jsp page
 * It will call /activities resource to retrieve
 * a list of activities so the user can select one.
 * @author Calories Calculator team
 */

import edu.matc.persistence.ActivityDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
/**import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;*/
import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@WebServlet(
        name = "indexDispServlet",
        urlPatterns = { "/indexDispServlet" }
        //loadOnStartup = 1
)

public class IndexDispServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void init() throws ServletException{
        logger.info("******************In the INIT of the index display servlet************");
        ServletContext context = getServletContext();
        context.setAttribute("test", "TestString");

    }

    /**
     * Handles HTTP GET requests.
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        //Create AdminActions instance
        //ActivityDao dao = new ActivityDao();
        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&Inside display servlet&&&&&&&&&&&&&&&&&&&&&&&&&");

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://localhost:8080/CaloriesCalculator/activities");
        String activitiesList = target.request().get(String.class);
        //response = target.request().get(String.class);
        logger.info("Returning activities " + response);

        //Get a list of all activities and store it in the request
        request.setAttribute("activitiesList", activitiesList);
        request.setAttribute("test", "TestString");

        String url = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}