package edu.matc.controller;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/activities")
public class ActivitiesRest {
    private static final double LESS_MODIFIER = 0.5;
    private static final double EXTRA_MODIFIER = 2.0;
    private ActivityDao activityDao= new ActivityDao();
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

    //Three parameters required to determine calories burned. Activity, Weight and Duration in hours
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getActivitiesJSON() {
        Map <Integer, String> output = Maps.newHashMap();

        for (Activity activity: activityList
             ) {
            output.put(activity.getId(), activity.getName());
        }

        return Response.status(200).entity(output).type(MediaType.APPLICATION_JSON).build();
    }

    //TODO add 4th request param called Unit as string = lb or kg

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/text/{activity}/{weight}/{duration}")
    public Response getCaloriesBurnedText(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("duration") double duration) {

        String output = "Calories Burned\t\tDuration\n";

        Map<Double, Double> results = buildResults(activityID, weight, duration);

        for (Map.Entry result: results.entrySet()
             ) {
            output += result.getValue() + " calorie(s)\t\tin " + result.getKey() + " hour(s)\n";
        }

        return Response.status(200).entity(output).type(MediaType.TEXT_PLAIN).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json/{activity}/{weight}/{duration}")
    public Response getCaloriesBurnedJSON(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("duration") double duration) {

        Map<String, Map> output = Maps.newHashMap();
        int i = 0;

        Map<Double, Double> results = buildResults(activityID, weight, duration);

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
    @Path("/html/{activity}/{weight}/{duration}")
    public Response getCaloriesBurnedHTML(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("duration") double duration) {

        String output = "<html><body><h1><table>";
        output += "<tr><th>Calories Burned</th><th>Duration</th></tr>";

        Map<Double, Double> results = buildResults(activityID, weight, duration);

        for (Map.Entry result: results.entrySet()
                ) {
            output += "<tr><td>" + result.getValue() + "</td><td>" + result.getKey() + "</td>";
        }

        output += "</table></h1></body></html>";
        return Response.status(200).entity(output).type(MediaType.TEXT_HTML).build();
    }

    private Map<Double, Double> buildResults(int activityID, double weight, double duration) {
        Map<Double, Double> calulatedBurns = new HashMap<>();

        CaloriesBurnedRequest requestLess = new CaloriesBurnedRequest();
        requestLess.setWeight(weight);
        requestLess.setDuration(duration * LESS_MODIFIER);
        //requestLess.setUnit()
        calulatedBurns.put(duration * LESS_MODIFIER, burned(requestLess, activityID));

        CaloriesBurnedRequest request = new CaloriesBurnedRequest();
        request.setDuration(duration);
        request.setWeight(weight);
        calulatedBurns.put(duration, burned(request, activityID));

        CaloriesBurnedRequest requestExtra = new CaloriesBurnedRequest();
        requestExtra.setWeight(weight);
        requestExtra.setDuration(duration * EXTRA_MODIFIER);
        calulatedBurns.put(duration * EXTRA_MODIFIER, burned(requestExtra, activityID));

        return calulatedBurns;
    }

    private double burned(CaloriesBurnedRequest request, int activityID) {
        CalculatorService service = new CalculatorService(request);
        return service.getCaloriesBurned(activityID);
    }
}
