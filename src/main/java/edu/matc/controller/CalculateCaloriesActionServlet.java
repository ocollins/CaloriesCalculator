package edu.matc.controller;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.Set;

/**
 * Admin action selection servlet
 * @author Olena Collins
 */
@WebServlet(
        name = "calculateCaloriesActionServlet",
        urlPatterns = { "/calculateCaloriesActionServlet" }
)
public class CalculateCaloriesActionServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void init() {
        logger.info("In the init of CalculateCaloriesServlet");

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
        //response.setContentType("text/html");

        //Remove the old session
        //HttpSession session = request.getSession(true);
        //session.invalidate();

        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$ Inside Calories action servlet");
        logger.info("$$$$$$$$$$$$$$$$$$$$$$ gender " + request.getParameter("gender"));
        int activity = Integer.parseInt(request.getParameter("activity_select"));


        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://localhost:8080/CaloriesCalculator/activities/text/1/70/1.5");
        String calcResult = target.request().get(String.class);
        //response = target.request().get(String.class);
        logger.info("Returning result " + calcResult);
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$ Inside Calories action servlet");

//        String url = "/add_member_serv.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);

    }
}