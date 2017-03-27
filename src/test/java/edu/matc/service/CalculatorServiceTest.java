package edu.matc.service;

import edu.matc.controller.ActivitiesRest;
import edu.matc.service.calculator.CalculatorService;
import edu.matc.service.calculator.CaloriesBurnedRequest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorServiceTest {

    private Logger logger = Logger.getLogger(this.getClass());

    private Double duration;
    private CalculatorService calculatorService;
    private CaloriesBurnedRequest caloriesBurnedRequest;

    @Before
    public void setup() {
        duration = 1.5;
        caloriesBurnedRequest = new CaloriesBurnedRequest();
        caloriesBurnedRequest.setDuration(duration);
    }

    @Test
    public void testGetCaloriesBurnedKg() {
        int id = 1;
        Double expected = 318.75;
        String unit = "kg";
        Double weight = 85.0;

        caloriesBurnedRequest.setUnit(unit);
        caloriesBurnedRequest.setWeight(weight);

        calculatorService = new CalculatorService(caloriesBurnedRequest);

        Double actual = calculatorService.getCaloriesBurned(id);
        logger.info("Calories burned (kg input): " + actual.toString());
        Assert.assertTrue("Calories burned calculated incorrectly for id=" + id, actual.equals(expected));
    }
    
//    @Test
//    public void testGetCaloriesBurned() {
//        int id = 1;
//        Double expected = 318.75;
//        Double actual = calculatorService.getCaloriesBurned(id);
//        logger.info("Calories burned:::::::::::: " + actual.toString());
//        Assert.assertTrue("Calories burned calculated incorrectly for id=" + id, actual.equals(expected));
//    }
//
//

    @Test
    public void testGetCaloriesBurnedLb() {
        int id = 1;
        Double expected = 318.75;
        String unit = "lb";
        Double weight = 187.0;

        caloriesBurnedRequest.setUnit(unit);
        caloriesBurnedRequest.setWeight(weight);

        calculatorService = new CalculatorService(caloriesBurnedRequest);

        Double actual = calculatorService.getCaloriesBurned(id);
        logger.info("Calories burned (lb input): " + actual.toString());
        Assert.assertTrue("Calories burned calculated incorrectly for id=" + id, actual.equals(expected));
    }

    @Test
    public void testConvertToKg() {
        Double weightLb = 187.0;
        Double weightKg = 85.0;

        calculatorService = new CalculatorService();

        Double actual = calculatorService.convertToKg(weightLb);
        logger.info("Conversion from lb to kg: " + actual.toString());
        Assert.assertTrue("Incorrect conversion", actual.equals(weightKg));
    }
}

