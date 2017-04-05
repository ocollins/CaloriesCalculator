package edu.matc.controller;
/* Activities Rest service.
*  Calculates calories burned during exercises.
*  Returns results in text, JSON, or HTML format
*/

import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import edu.matc.service.calculator.CalculatorService;
import edu.matc.service.calculator.CaloriesBurnedRequest;
import jersey.repackaged.com.google.common.collect.Maps;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/activities")
public class ActivitiesRest {
    private static final double LESS_MODIFIER = 0.5;
    private static final double EXTRA_MODIFIER = 0.333; //20 minutes
    private ActivityDao activityDao = new ActivityDao();
    private List<Activity> activityList = activityDao.getAllActivities();

    @GET
    @Produces("text/plain")
    public Response getAllActivities() {
        String output = "";
        output += "\nActivity\t\tMET(s)\n";
        for (Activity activity: activityList
                ) {
            output += activity.getName() + "\t\t" + activity.getMets() + "\n";
        }
        return Response.status(200).entity(output).build();
    }

    //Return a list of possible activities in JSON format
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getActivitiesJSON() {
        Map<String, ArrayList> output = Maps.newTreeMap();

            ArrayList<Map> activities = new ArrayList<>();
            for (Activity activity : activityList) {

                Map<String, String> act = Maps.newHashMap();
                act.put("activityID", String.valueOf(activity.getId()));
                act.put("activityTitle", activity.getName());

                activities.add(act);
            }

            output.put("Activities", activities);


        return Response.status(200).entity(output).type(MediaType.APPLICATION_JSON).build();
    }


    //Four parameters required to determine calories burned. Activity, Weight, Duration in hours
    //and Weight Unit (pounds or kilograms)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/text/{activity}/{weight}/{duration}/{unit}")
    public Response getCaloriesBurnedText(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("duration") double duration,
            @PathParam("unit") String unit) {

        String output = "Calories Burned\t\tDuration\n";

        Map<Double, Double> results = buildResults(activityID, weight, duration, unit);

        for (Map.Entry result: results.entrySet()
             ) {
            output += result.getValue() + " calorie(s)\t\tin " + result.getKey() + " hour(s)\n";
        }

        return Response.status(200).entity(output).type(MediaType.TEXT_PLAIN).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json/{activity}/{weight}/{duration}/{unit}")
    public Response getCaloriesBurnedJSON(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("duration") double duration,
            @PathParam("unit") String unit) {

        Map<String, Map> output = Maps.newTreeMap();
        int i = 0;

        Map<Double, Double> results = buildResults(activityID, weight, duration, unit);

        for (Map.Entry result: results.entrySet()
                ) {
            Map<String, Double> detail = Maps.newHashMap();
            detail.put("Calories Burned:", (double) result.getValue());
            detail.put("Duration:", (double) result.getKey());
            output.put("Calculation " + i + ":" , detail);
            i++;
        }

        return Response.status(200).entity(output).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/html/{activity}/{weight}/{duration}/{unit}")
    public Response getCaloriesBurnedHTML(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("duration") double duration,
            @PathParam("unit") String unit) {

        String output = "<html><body><h1><table>";
        output += "<tr><th>Calories Burned</th><th>Duration</th></tr>";

        Map<Double, Double> results = buildResults(activityID, weight, duration, unit);

        for (Map.Entry result: results.entrySet()
                ) {
            output += "<tr><td>" + result.getValue() + "</td><td>" + result.getKey() + "</td>";
        }

        output += "</table></h1></body></html>";
        return Response.status(200).entity(output).type(MediaType.TEXT_HTML).build();
    }

    private Map<Double, Double> buildResults(int activityID, double weight, double duration, String unit) {
        Map<Double, Double> calulatedBurns = new TreeMap<>();

        CaloriesBurnedRequest requestLess = new CaloriesBurnedRequest();
        requestLess.setWeight(weight);
        requestLess.setDuration(duration * LESS_MODIFIER);
        requestLess.setUnit(unit);
        calulatedBurns.put(duration * LESS_MODIFIER, burned(requestLess, activityID));

        CaloriesBurnedRequest request = new CaloriesBurnedRequest();
        request.setDuration(duration);
        request.setWeight(weight);
        request.setUnit(unit);
        calulatedBurns.put(duration, burned(request, activityID));

        CaloriesBurnedRequest requestExtra = new CaloriesBurnedRequest();
        requestExtra.setWeight(weight);
        requestExtra.setDuration(duration + EXTRA_MODIFIER);
        requestExtra.setUnit(unit);
        calulatedBurns.put(duration + EXTRA_MODIFIER, burned(requestExtra, activityID));

        return calulatedBurns;
    }

    private double burned(CaloriesBurnedRequest request, int activityID) {
        CalculatorService service = new CalculatorService(request);
        return service.getCaloriesBurned(activityID);
    }
}
