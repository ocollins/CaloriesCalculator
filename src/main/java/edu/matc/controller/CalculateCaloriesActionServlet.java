package edu.matc.controller;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession(true);
        session.invalidate();

        //Create new session
        session = request.getSession(true);

        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$ Getting into Calories action servlet");
        int activity = Integer.parseInt(request.getParameter("activity_select"));
        int weight = Integer.parseInt(request.getParameter("weight_text"));
        double duration = Double.parseDouble(request.getParameter("duration_select"));

        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8080/CaloriesCalculator/activities/text/";
        url = url + activity + "/" + weight + "/" + duration;
        WebTarget target =
                client.target(url);
        logger.info(url);
        String calcResult = target.request().get(String.class);
        ServletContext context = getServletContext();
        context.setAttribute("CaloriesResult",  calcResult);
        logger.info("Checking context attribute " + context.getAttribute("CaloriesResult"));
        //response = target.request().get(String.class);
        logger.info("Returning result " + calcResult);

        String responceUrl = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responceUrl);
        dispatcher.forward(request, response);

    }
}