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

    private CalculatorService calculatorService;

    @Before
    public void setup() {
        Double weight = 85.0;
        Double duration = 1.5;
        CaloriesBurnedRequest caloriesBurnedRequest = new CaloriesBurnedRequest(weight, duration);
        calculatorService = new CalculatorService(caloriesBurnedRequest);
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
}

