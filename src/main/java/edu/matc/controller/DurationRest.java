package edu.matc.controller;
/**
 * Calculate excercise duration API
 * @Author CaloriesCalculator team
 */

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Activity;
import edu.matc.error.ResponseError;
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
import java.util.HashMap;
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
        Double results;
        try {
            results  = buildResults(activityID, weight, calories, unit);
        } catch (Exception e) {
            ResponseError error = new ResponseError();
            String message = error.getResponseErrorMessage();
            return Response.status(500).entity(message).build();
        }

        String output = "Duration: " + results;

        return Response.status(200).entity(output).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json/{activity}/{weight}/{calories}/{unit}")
    public Response getDurationJSON(
            @PathParam("activity") int activityID,
            @PathParam("weight") double weight,
            @PathParam("calories") int calories,
            @PathParam("unit") String unit) {
        Double results;
        try {
            results  = buildResults(activityID, weight, calories, unit);
        } catch (Exception e) {
            ResponseError error = new ResponseError();
            String message = error.getResponseErrorMessage();
            return Response.status(500).entity(message).build();
        }

        Map <String, Double> output = new HashMap<>();
        output.put("Duration:", results);

        return Response.status(200).entity(output).type(MediaType.APPLICATION_JSON).build();
    }


    /**
     * Call duration calculation methods
     *
     * @param activityID the activity id
     * @param weight     the weight
     * @param calories   the calories
     * @param unit       the unit
     * @return the exercise duration as double
     */
    private Double buildResults(int activityID, double weight, int calories, String unit) {
        DurationRequest durationRequest = new DurationRequest();
        durationRequest.setWeight(weight);
        durationRequest.setUnit(unit);
        durationRequest.setCalories(calories);

        CalculatorService service = new CalculatorService(durationRequest);

        return service.getDuration(activityID);
    }

}
