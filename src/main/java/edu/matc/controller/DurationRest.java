package edu.matc.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import edu.matc.service.calculator.CalculatorService;
import edu.matc.service.calculator.CaloriesBurnedRequest;
import edu.matc.service.calculator.DurationRequest;
import jersey.repackaged.com.google.common.collect.Maps;
import org.apache.log4j.Logger;

import javax.json.stream.JsonGenerationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Path("/duration")
public class DurationRest {
    private ActivityDao activityDao = new ActivityDao();;
    private List<Activity> activityList = activityDao.getAllActivities();;
    private Logger logger = Logger.getLogger(this.getClass());

    //Four parameters required to determine duration to burn calories. Activity, Weight, calories,
    //and Weight Unit (pounds or kilograms)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/text/{activity}/{weight}/{calories}/{unit}")
    public Response getDurationText(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("calories") int calories,
            @PathParam("unit") String unit) {
        Double results = buildResults(activityID, weight, calories, unit);

        String output = "Duration:\tresults\n";

        return Response.status(200).entity(output).type(MediaType.TEXT_PLAIN).build();
    }


//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/json/{activity}/{weight}/{duration}/{unit}")
//    public Response getCaloriesBurnedJSON(
//            @PathParam("activity") int activityID,
//            @PathParam("weight") double weight,
//            @PathParam("duration") double duration,
//            @PathParam("unit") String unit) {
//
//        Map<String, Map> output = Maps.newTreeMap();
//        int i = 0;
//
//        Map<Double, Double> results = buildResults(activityID, weight, duration, unit);
//
//        for (Map.Entry result: results.entrySet()
//                ) {
//            Map<String, Double> detail = Maps.newHashMap();
//            detail.put("Calories Burned:", (double) result.getValue());
//            detail.put("Duration:", (double) result.getKey());
//            output.put("Calculation " + i + ":" , detail);
//            i++;
//        }
//
//        return Response.status(200).entity(output).type(MediaType.APPLICATION_JSON).build();
//    }

//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    @Path("/html/{activity}/{weight}/{duration}/{unit}")
//    public Response getCaloriesBurnedHTML(
//            @PathParam("activity") int activityID,
//            @PathParam("weight") double weight,
//            @PathParam("duration") double duration,
//            @PathParam("unit") String unit) {
//
//        String output = "<html><body><h1><table>";
//        output += "<tr><th>Calories Burned</th><th>Duration</th></tr>";
//
//        Map<Double, Double> results = buildResults(activityID, weight, duration, unit);
//
//        for (Map.Entry result: results.entrySet()
//                ) {
//            output += "<tr><td>" + result.getValue() + "</td><td>" + result.getKey() + "</td>";
//        }
//
//        output += "</table></h1></body></html>";
//        return Response.status(200).entity(output).type(MediaType.TEXT_HTML).build();
//    }

    private Double buildResults(int activityID, double weight, int calories, String unit) {
        DurationRequest durationRequest = new DurationRequest();
        durationRequest.setWeight(weight);
        durationRequest.setUnit(unit);
        durationRequest.setCalories(calories);

        CalculatorService service = new CalculatorService(durationRequest);

        return service.getCaloriesBurned(activityID);
    }

}
